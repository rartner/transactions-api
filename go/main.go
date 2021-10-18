package main

import (
	"log"
	"net/http"

	"github.com/rartner/transactions-api/src/controllers"
	"github.com/rartner/transactions-api/src/repositories"
	"github.com/rartner/transactions-api/src/router"
	"github.com/rartner/transactions-api/src/services"
)

func main() {
	accRepo := repositories.NewAccountRepository()
	trnRepo := repositories.NewTransactionRepository()

	accService := services.NewAccountService(accRepo)
	trnService := services.NewTransactionService(trnRepo, accService)

	accController := controllers.NewAccountsController(accService)
	trnController := controllers.NewTransactionsController(trnService)

	r := router.NewRouter(accController, trnController)

	log.Fatal(http.ListenAndServe(":8080", r))
}
