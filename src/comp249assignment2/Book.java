/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp249assignment2;

/**
 *
 * @author louis
 */
public class Book {
    private int issueYear;
    private int numberOfPages;
    private long isbn;
    private double price;
    private String title;
    private String authorName;
    
    public Book() {
        this.issueYear = 0;
        this.numberOfPages = 0;
        this.isbn = 0;
        this.price = 0.0;
        this.title = "No_Title";
        this.authorName = "No_Author_Name";
    }
    public Book(int issueYear, int numberOfPages, long isbn, double price, String title, String authorName) {
        this.issueYear = issueYear;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.price = price;
        this.title = title;
        this.authorName = authorName;
    }
    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }
    public int getIssueYear() {
        return this.issueYear;
    }
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public int getNumberOfPages() {
        return this.numberOfPages;
    }
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    public long getIsbn() {
        return isbn;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return this.price;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorName() {
        return this.authorName;
    }
    @Override public String toString() {
        return (this.isbn + " " + this.title + " " + this.issueYear + " " + this.authorName + " " + this.price + " " + this.numberOfPages);
    }
    @Override public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        return (this.price == ((Book)object).price 
                && this.numberOfPages == ((Book)object).numberOfPages 
                && this.issueYear == ((Book)object).issueYear
                && this.isbn == ((Book)object).isbn
                && this.authorName.equals(((Book)object).authorName)
                && this.title.equals(((Book)object).authorName));
    }
}
