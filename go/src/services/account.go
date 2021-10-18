package services

import (
	"github.com/rartner/transactions-api/src/domain"
	"github.com/rartner/transactions-api/src/repositories"
)

type AccountServiceI interface {
	Create(acc domain.Account) (domain.Account, error)
	Find(ID int64) (domain.Account, error)
	List() ([]domain.Account, error)
}

type AccountService struct {
	accRepository *repositories.AccountRepository
}

func NewAccountService(repo *repositories.AccountRepository) AccountServiceI {
	return AccountService{accRepository: repo}
}

func (s AccountService) Create(acc domain.Account) (domain.Account, error) {
	ID, err := s.accRepository.Save(acc)
	if err != nil {
		return domain.Account{}, err
	}

	acc.ID = ID
	return acc, nil
}

func (s AccountService) Find(ID int64) (domain.Account, error) {
	acc, err := s.accRepository.FindByID(ID)
	if err != nil {
		return domain.Account{}, nil
	}

	return acc, nil
}

func (s AccountService) List() ([]domain.Account, error) {
	accounts, err := s.accRepository.List()
	if err != nil {
		return []domain.Account{}, err
	}

	return accounts, nil
}
