package controllers

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
	"github.com/rartner/transactions-api/src/domain"
	response "github.com/rartner/transactions-api/src/responses"
	"github.com/rartner/transactions-api/src/services"
	"gopkg.in/go-playground/validator.v9"
)

type AccountsController interface {
	Create(resp http.ResponseWriter, req *http.Request)
	List(resp http.ResponseWriter, req *http.Request)
	Find(resp http.ResponseWriter, req *http.Request)
}

type Accounts struct {
	accountService services.AccountService
}

func NewAccountsController(serv services.AccountService) *Accounts {
	return &Accounts{accountService: serv}
}

func (this *Accounts) Create(w http.ResponseWriter, r *http.Request) {
	accountDTO := new(domain.CreateAccountDTO)
	if err := json.NewDecoder(r.Body).Decode(accountDTO); err != nil {
		response.Error(w, http.StatusUnprocessableEntity, errors.New("Cannot process request body"))
		return
	}

	v := validator.New()
	if err := v.Struct(accountDTO); err != nil {
		message := fmt.Sprintf("Invalid request body: %s", err.Error())
		response.Error(w, http.StatusBadRequest, errors.New(message))
		return
	}

	account, err := this.accountService.Create(accountDTO.ToEntity())
	if err != nil {
		response.Error(w, http.StatusInternalServerError, err)
		return
	}

	response.JSON(w, http.StatusCreated, account.ToDTO())
}

func (this *Accounts) List(w http.ResponseWriter, r *http.Request) {
	accounts, err := this.accountService.List()
	if err != nil {
		response.Error(w, http.StatusInternalServerError, err)
		return
	}

	var resp []domain.AccountDTO
	for _, account := range accounts {
		resp = append(resp, account.ToDTO())
	}

	response.JSON(w, http.StatusOK, resp)
}

func (this *Accounts) Find(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	ID, err := strconv.ParseInt(vars["id"], 10, 64)
	if err != nil {
		response.Error(w, http.StatusBadRequest, errors.New("Cannot get account id parameter"))
		return
	}

	account, err := this.accountService.Find(ID)
	if err != nil {
		response.Error(w, http.StatusInternalServerError, err)
		return
	}

	response.JSON(w, http.StatusOK, account.ToDTO())
}
