package main

import (
	"fmt"
	"net/http"
	"os"

	"./api"
)

func main() {
	http.HandleFunc("/", api.Index)
	http.HandleFunc("/api/echo", api.Echo)
	http.HandleFunc("/api/books", api.BooksHandleFunc)
	http.HandleFunc("/api/books/", api.BookHandleFunc)
	http.ListenAndServe(port(), nil)
}

func port() string {
	port := os.Getenv("PORT")
	fmt.Println("Running on port : :" + port)
	if len(port) == 0 {
		port = "8080"
	}
	return ":" + port
}
