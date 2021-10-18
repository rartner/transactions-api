package router

import (
	"github.com/gorilla/mux"
	"github.com/rartner/transactions-api/src/controllers"
)

func NewRouter(acc controllers.AccountsController, trn controllers.TransactionsController) *mux.Router {
	r := mux.NewRouter()

	return ConfigureRoutes(r, acc, trn)
}
