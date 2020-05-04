package api

import (
	"encoding/json"
	"io/ioutil"
	"math/rand"
	"net/http"
)

// Book type with Name, Author, ISBN
type Book struct {
	Title       string `json:"title"`
	Author      string `json:"author"`
	ISBN        string `json:"isbn"`
	Description string `json:"description,omitempty"`
	// define the book
}

// ToJSON to be used for marshalling of Book type
func (b Book) ToJSON() []byte {
	ToJSON, err := json.Marshal(b)
	if err != nil {
		panic(err)
	}
	return ToJSON
}

// FromJSON - We will implement after sometime
func FromJSON(data []byte) Book {
	book := Book{}
	err := json.Unmarshal(data, &book)
	if err != nil {
		panic(err)
	}
	return book
}

// Books - slices of all known books
var Books = map[string]Book{
	"0345698671": Book{Title: "The Hitchhiker's Guide to Galaxy", Author: "Douglas Adams", ISBN: "0345698671"},
	"0123456789": Book{Title: "CLoud Native GO", Author: "M.-L. Reimer", ISBN: "0123456789"},
}

// BookHandleFunc - We will implement after sometime
func BookHandleFunc(w http.ResponseWriter, r *http.Request) {

	// isbn := r.URL.Path[len("/api/books/")]

	switch method := r.Method; method {
	case http.MethodGet:
		books := AllBooks()
		writeJSON(w, &books)
	case http.MethodPost:
		body, err := ioutil.ReadAll(r.Body)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
		}
		book := FromJSON(body)
		isbn, created := CreateBook(book)
		if created {
			w.Header().Add("Location", "/api/books/"+isbn)
			w.WriteHeader(http.StatusCreated)

		} else {
			w.WriteHeader(http.StatusConflict)
		}

	default:
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte("Unsupported request method"))
	}
}

// AllBooks returns array of books from incache map
func AllBooks() []Book {

	sample := make([]Book, 0, len(Books))
	for _, b := range Books {
		sample = append(sample, b)
	}
	return sample
}

func writeJSON(w http.ResponseWriter, j *[]Book) {
	json, err := json.Marshal(j)
	if err != nil {
		panic(err)
	}
	w.Header().Add("Content-Type", "application/json; charset=utf-8")
	w.Write(json)
}

// CreateBook adds book in parameter to existing mapdb
func CreateBook(book Book) (string, bool) {
	isbn := string(rand.Int63n(9999999999))
	_, alreadyExist := Books[isbn]
	if alreadyExist {
		panic("ISBN alreadyExist, could not generate unique one. Not adding new book record.")
	}
	Books[isbn] = book
	return isbn, true
}
