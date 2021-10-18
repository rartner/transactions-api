package database

import (
	"database/sql"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func OpenConnection() (*sql.DB, error) {
	db, err := sql.Open("mysql", "root:root@/transactions?charset=utf8&parseTime=True&loc=Local")
	if err != nil {
		log.Fatal("Error connecting to database")
		return nil, err
	}

	if err := db.Ping(); err != nil {
		db.Close()
		return nil, err
	}

	return db, nil
}
