package com.sagar.priorityqueue.application;

public class Transaction implements Comparable<Transaction> {

    private String line;
    private String  author;
    private String  date;
    private Integer transaction;

    public Transaction(String line){
        this.line = line;
        String elems[] = line.split(" ");
        this.author = elems[0];
        this.date = elems[1];
        this.transaction = Integer.parseInt(elems[2]);
    }
    @Override
    public int compareTo(Transaction that) {
        return this.transaction.compareTo(that.transaction);
    }

    public String toString(){
        return line;
    }
}
