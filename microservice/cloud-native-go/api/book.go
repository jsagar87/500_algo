package api

import (
	"encoding/json"
	"fmt"
	"strconv"

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

// BooksHandleFunc to be used as http.HandleFunc for Book APi
func BooksHandleFunc(w http.ResponseWriter, r *http.Request) {
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

// BookHandleFunc same as above for liitle different routing
func BookHandleFunc(w http.ResponseWriter, r *http.Request) {
	fmt.Println("BookHandleFunc for get, put and delete")
	isbn := r.URL.Path[len("/api/books/")]
	switch method := r.Method; method {
	case http.MethodGet:
		book, found := GetBook(string(isbn))
		if found {
			// w.Header().Add("Location", "/api/books/"+isbn)
			// w.WriteHeader(http.StatusAccepted)
			// w.Write(book.ToJSON())
			writeJSON(w, &[]Book{book})
		} else {
			w.WriteHeader(http.StatusNotFound)
		}
	case http.MethodPut:
		body, err := ioutil.ReadAll(r.Body)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
		}
		book := FromJSON(body)
		exists := UpdateBook(string(isbn), book)
		if exists {
			w.WriteHeader(http.StatusOK)
		} else {
			w.WriteHeader(http.StatusNotFound)
		}
	case http.MethodDelete:
		DeleteBook(string(isbn))
		w.WriteHeader(http.StatusOK)
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

// GetBook returns book for a given ISBN
func GetBook(isbn string) (Book, bool) {
	v, ok := Books[isbn]
	fmt.Println(v, ok)
	if !ok {
		panic("Book does not exist")
	}
	return v, ok
}

// CreateBook adds book in parameter to existing mapdb
func CreateBook(book Book) (string, bool) {
	isbn := GenerateRandomString()
	_, alreadyExist := Books[isbn]
	if alreadyExist {
		return isbn, false
	}
	book.ISBN = isbn
	Books[isbn] = book
	return isbn, true
}

// UpdateBook adds book in parameter to existing mapdb
func UpdateBook(isbn string, book Book) bool {
	_, alreadyExist := Books[isbn]
	if alreadyExist {
		Books[isbn] = book
		return true
	}
	return false
}

// DeleteBook adds book in parameter to existing mapdb
func DeleteBook(isbn string) {
	_, alreadyExist := Books[isbn]
	if alreadyExist {
		delete(Books, isbn)
	}
}

func writeJSON(w http.ResponseWriter, j *[]Book) {
	json, err := json.Marshal(j)
	if err != nil {
		panic(err)
	}
	w.Header().Add("Content-Type", "application/json; charset=utf-8")
	w.Write(json)
}

// GenerateRandomString generates random number of 10 digit and covert and return in string format
func GenerateRandomString() string {
	rNum := rand.Intn(999999)
	strDigit := strconv.Itoa(rNum)
	return strDigit
}
