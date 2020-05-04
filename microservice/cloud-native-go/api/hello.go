package api

import (
	"fmt"
	"net/http"
)

// Index just hello world implementation
func Index(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprint(w, "Hello Cloud Native Go.")
}
