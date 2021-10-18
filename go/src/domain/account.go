package domain

type Account struct {
	ID             int64
	DocumentNumber string
}

type CreateAccountDTO struct {
	DocumentNumber string `json:"document_number" validate:"required"`
}

type AccountDTO struct {
	ID             int64  `json:"account_id" validate:"required"`
	DocumentNumber string `json:"document_number" validate:"required"`
}

func (dto *CreateAccountDTO) ToEntity() Account {
	return Account{DocumentNumber: dto.DocumentNumber}
}

func (a *Account) ToDTO() AccountDTO {
	return AccountDTO{
		ID:             a.ID,
		DocumentNumber: a.DocumentNumber,
	}
}
