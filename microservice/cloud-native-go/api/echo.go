package api

import (
	"fmt"
	"net/http"
)

// Echo function standard echo implementation
func Echo(w http.ResponseWriter, r *http.Request) {
	message := r.URL.Query()["message"][0]

	w.Header().Add("Content-Type", "text/plain")
	fmt.Fprint(w, message)
}
