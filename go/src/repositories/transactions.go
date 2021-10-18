package repositories

import (
	"github.com/rartner/transactions-api/src/database"
	"github.com/rartner/transactions-api/src/domain"
)

type TransactionRepository struct {
}

func NewTransactionRepository() *TransactionRepository {
	return &TransactionRepository{}
}

func (r TransactionRepository) Save(trn domain.Transaction) (int64, error) {
	db, err := database.OpenConnection()
	if err != nil {
		return 0, err
	}
	defer db.Close()

	tx, err := db.Begin()
	if err != nil {
		return 0, err
	}

	stmt, err := tx.Prepare("insert into transaction(operation_type, amount, account_id) values (?, ?, ?)")
	if err != nil {
		return 0, err
	}
	defer stmt.Close()

	res, err := stmt.Exec(trn.OperationType, trn.Amount, trn.AccountID)
	if err != nil {
		tx.Rollback()
		return 0, err
	}

	ID, _ := res.LastInsertId()
	tx.Commit()

	return ID, nil
}
