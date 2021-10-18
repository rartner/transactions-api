package router

import (
	"net/http"

	"github.com/gorilla/mux"
	"github.com/rartner/transactions-api/src/controllers"
)

type Route struct {
	URI    string
	Method string
	Func   func(http.ResponseWriter, *http.Request)
}

func ConfigureRoutes(r *mux.Router, acc controllers.AccountsController, trn controllers.TransactionsController) *mux.Router {
	routes := getAccountRoutes(acc)
	routes = append(routes, getTransactionsRoutes(trn)...)

	for _, route := range routes {
		r.HandleFunc(route.URI, route.Func).Methods(route.Method)
	}

	return r
}

func getAccountRoutes(accountsController controllers.AccountsController) []Route {
	accountRoutes := []Route{
		{
			URI:    "/accounts",
			Method: "GET",
			Func:   accountsController.List,
		},
		{
			URI:    "/accounts",
			Method: "POST",
			Func:   accountsController.Create,
		},
		{
			URI:    "/accounts/{id}",
			Method: "GET",
			Func:   accountsController.Find,
		},
	}

	return accountRoutes
}

func getTransactionsRoutes(transactionsController controllers.TransactionsController) []Route {
	var transactionsRoutes = []Route{
		{
			URI:    "/transactions",
			Method: "POST",
			Func:   transactionsController.Create,
		},
	}

	return transactionsRoutes
}
