package services

import (
	"errors"

	"github.com/rartner/transactions-api/src/domain"
	"github.com/rartner/transactions-api/src/repositories"
)

type TransactionService interface {
	Create(transaction domain.Transaction) (domain.Transaction, error)
}

type Transaction struct {
	trnRepository *repositories.TransactionRepository
	accService    AccountService
}

func NewTransactionService(repo *repositories.TransactionRepository, accService AccountService) TransactionService {
	return &Transaction{trnRepository: repo, accService: accService}
}

func (s Transaction) Create(transaction domain.Transaction) (domain.Transaction, error) {
	if _, err := s.accService.Find(transaction.AccountID); err != nil {
		return domain.Transaction{}, errors.New("Cannot create transaction for this account")
	}

	s.setTransactionAmount(&transaction)

	ID, err := s.trnRepository.Save(transaction)
	if err != nil {
		return domain.Transaction{}, err
	}

	transaction.ID = ID

	return transaction, nil
}

func (s Transaction) setTransactionAmount(transaction *domain.Transaction) error {
	isNegative, err := domain.IsNegative(transaction.OperationType)
	if err != nil {
		return err
	}

	if isNegative {
		transaction.Amount = -transaction.Amount
	}

	return nil
}
