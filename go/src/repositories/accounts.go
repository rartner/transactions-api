package repositories

import (
	"github.com/rartner/transactions-api/src/database"
	"github.com/rartner/transactions-api/src/domain"
)

type AccountRepository struct {
}

func NewAccountRepository() *AccountRepository {
	return &AccountRepository{}
}

func (r AccountRepository) Save(acc domain.Account) (int64, error) {
	db, err := database.OpenConnection()
	if err != nil {
		return 0, err
	}
	defer db.Close()

	tx, err := db.Begin()
	if err != nil {
		return 0, err
	}

	stmt, err := tx.Prepare("insert into account(document_number) values (?)")
	if err != nil {
		return 0, err
	}
	defer stmt.Close()

	res, err := stmt.Exec(acc.DocumentNumber)
	if err != nil {
		tx.Rollback()
		return 0, err
	}

	ID, _ := res.LastInsertId()
	tx.Commit()

	return ID, nil
}

func (r AccountRepository) FindByID(ID int64) (domain.Account, error) {
	db, err := database.OpenConnection()
	if err != nil {
		return domain.Account{}, err
	}
	defer db.Close()

	stmt, err := db.Prepare("select id, document_number from account where id = ?")
	if err != nil {
		return domain.Account{}, err
	}
	defer stmt.Close()

	acc := domain.Account{}
	err = stmt.QueryRow(ID).Scan(&acc.ID, &acc.DocumentNumber)
	if err != nil {
		return domain.Account{}, err
	}

	return acc, nil
}

func (r AccountRepository) List() ([]domain.Account, error) {
	db, err := database.OpenConnection()
	if err != nil {
		return []domain.Account{}, err
	}
	defer db.Close()

	stmt, err := db.Prepare("select id, document_number from account")
	if err != nil {
		return []domain.Account{}, err
	}
	defer stmt.Close()

	rows, err := stmt.Query()
	if err != nil {
		return []domain.Account{}, err
	}

	var accounts []domain.Account
	for rows.Next() {
		var acc domain.Account

		if err := rows.Scan(&acc.ID, &acc.DocumentNumber); err != nil {
			return nil, err
		}

		accounts = append(accounts, acc)
	}

	return accounts, nil
}
