package services

import (
	"github.com/rartner/transactions-api/src/domain"
	"github.com/rartner/transactions-api/src/repositories"
)

type AccountService interface {
	Create(acc domain.Account) (domain.Account, error)
	Find(ID int64) (domain.Account, error)
	List() ([]domain.Account, error)
}

type Account struct {
	accRepository *repositories.AccountRepository
}

func NewAccountService(repo *repositories.AccountRepository) AccountService {
	return Account{accRepository: repo}
}

func (s Account) Create(acc domain.Account) (domain.Account, error) {
	ID, err := s.accRepository.Save(acc)
	if err != nil {
		return domain.Account{}, err
	}

	acc.ID = ID
	return acc, nil
}

func (s Account) Find(ID int64) (domain.Account, error) {
	acc, err := s.accRepository.FindByID(ID)
	if err != nil {
		return domain.Account{}, err
	}

	return acc, nil
}

func (s Account) List() ([]domain.Account, error) {
	accounts, err := s.accRepository.List()
	if err != nil {
		return []domain.Account{}, err
	}

	return accounts, nil
}
