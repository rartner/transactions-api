package controllers

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"

	"github.com/rartner/transactions-api/src/domain"
	response "github.com/rartner/transactions-api/src/responses"
	"github.com/rartner/transactions-api/src/services"
	"gopkg.in/go-playground/validator.v9"
)

type TransactionsController interface {
	Create(resp http.ResponseWriter, req *http.Request)
}

type Transactions struct {
	transactionService *services.TransactionService
}

func NewTransactionsController(serv *services.TransactionService) *Transactions {
	return &Transactions{transactionService: serv}
}

func (c *Transactions) Create(resp http.ResponseWriter, req *http.Request) {
	transactionDTO := new(domain.CreateTransactionDTO)
	if err := json.NewDecoder(req.Body).Decode(transactionDTO); err != nil {
		response.Error(resp, http.StatusUnprocessableEntity, errors.New("Cannot process request body"))
	}

	v := validator.New()
	if err := v.Struct(transactionDTO); err != nil {
		message := fmt.Sprintf("Invalid request body: %s", err.Error())
		response.Error(resp, http.StatusBadRequest, errors.New(message))
		return
	}

	_, err := c.transactionService.Create(transactionDTO.ToEntity())
	if err != nil {
		response.Error(resp, http.StatusInternalServerError, err)
	}

	response.JSON(resp, http.StatusCreated, nil)
}
