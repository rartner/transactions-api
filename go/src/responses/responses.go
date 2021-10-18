package responses

import (
	"encoding/json"
	"log"
	"net/http"
)

func JSON(resp http.ResponseWriter, statusCode int, body interface{}) {
	resp.WriteHeader(statusCode)

	if body != nil {
		resp.Header().Add("Content-Type", "application/json")
		if err := json.NewEncoder(resp).Encode(body); err != nil {
			log.Fatal("Cannot write response body")
		}
	}
}

func Error(resp http.ResponseWriter, statusCode int, err error) {
	JSON(resp, statusCode, struct {
		Error string `json:"error"`
	}{
		Error: err.Error(),
	})
}
