package services

import (
	"errors"

	"github.com/rartner/transactions-api/src/domain"
	"github.com/rartner/transactions-api/src/repositories"
)

type TransactionServiceI interface {
	Create(transaction domain.Transaction) (domain.Transaction, error)
}

type TransactionService struct {
	trnRepository *repositories.TransactionRepository
	accService    AccountServiceI
}

func NewTransactionService(repo *repositories.TransactionRepository, accService AccountServiceI) *TransactionService {
	return &TransactionService{trnRepository: repo, accService: accService}
}

func (s TransactionService) Create(transaction domain.Transaction) (domain.Transaction, error) {
	if _, err := s.accService.Find(transaction.AccountID); err != nil {
		return domain.Transaction{}, errors.New("Cannot create transaction for this account")
	}

	return domain.Transaction{}, nil
}
