package domain

type OperationTypeEnum int

const (
	Unknown OperationTypeEnum = iota
	PurchaseCash
	PurchaseInstallments
	Withdraw
	Payments
)

type Transaction struct {
	ID            int64
	AccountID     int64
	Amount        float64
	OperationType OperationTypeEnum
}

type CreateTransactionDTO struct {
	AccountID       int64             `json:"account_id" validate:"required"`
	OperationTypeID OperationTypeEnum `json:"operation_type_id" validate:"required,min=1,max=4"`
	Amount          float64           `json:"amount" validate:"required,min=0"`
}

type TransactionDTO struct {
	ID              int64
	AccountID       int64
	OperationTypeID OperationTypeEnum
	Amount          float64
}

func (t *CreateTransactionDTO) ToEntity() Transaction {
	return Transaction{
		AccountID:     t.AccountID,
		OperationType: t.OperationTypeID,
		Amount:        t.Amount,
	}
}
