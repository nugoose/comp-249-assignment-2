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
public class Book2 {
    //Attributes with protected access rights
    private long isbn;
    private String title;
    private int issueYear;
    private String authorName;
    private double price;
    private int numberOfPages;
    
    //Constructors
    public Book2() {
        this.isbn = 0;
        this.issueYear = 0;
        this.authorName = "No author name";
        this.title = "No title";
    }
    public Book2(int issueYear, long isbn, String authorName, double price, int numberOfPages, String title) {
        if (!this.isbnCheck(isbn) || authorName == null || title == null)
        {
            System.out.println("Invalid entry.");
            System.exit(0);
        }
        if (issueYear > 2015 || issueYear < 0)
        {
            System.out.println("Year cannot be in the future or B.C.");
            System.exit(0);
        }
        
        this.isbn = isbn;
        this.issueYear = issueYear;
        this.authorName = authorName;
        this.title = title;
        this.price = price;
        this.numberOfPages = numberOfPages;
    }
    //Accessors and mutators
    public long getIsbn() {
        return this.isbn;
    }
    public void setIsbn(long isbn) {
        if (!this.isbnCheck(isbn))
        {
            System.out.println("Invalid ISBN.");
            System.exit(0);
        }
        this.isbn = isbn;
    }
    public int getIssueYear() {
        return this.issueYear;
    }
    public void setIssueYear(int issueYear) {
        if (issueYear <= 0 || issueYear > 2015)
        {
            System.out.println("Invalid year.");
            System.exit(0);
        }
        this.issueYear = issueYear;
    }
    public String getAuthorName() {
        return this.authorName;
    }
    public void setAuthorName(String authorName) {
        if (authorName == null)
        {
            System.out.println("Author name cannot be empty.");
            System.exit(0);
        }
        this.authorName = authorName;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        if (title == null)
        {
            System.out.println("Title cannot be empty.");
            System.exit(0);
        }
        this.title = title;
    }
    public final boolean isbnCheck(long isbn) {
        //This method performs a check on the ISBN provided by the user
        //The full specifications of ISBN can be found at :
        //https://en.wikipedia.org/wiki/International_Standard_Book_Number#ISBN-13_check_digit_calculation
        //
        //For this exercise, I stuck with the new, 13-digit check and not the old 10-digit check
        //Old ISBN's are converted to 13 character anyways
        
        //Convert long to String object
        String number = String.valueOf(isbn);
        //Convert String to char array
        char[] digits = number.toCharArray();
        
        //Initializing variables
        int asciiValue;
        int numericValue;
        int numericValueEven = 0;
        int numericValueOdd = 0;
        int counter = 0;
        int sumEven = 0;
        int sumOdd = 0;
        
        //Loop for first check
        for (int i = 0; i < digits.length - 1; i++) {
            //Convert ASCII value of char to int value
            asciiValue = digits[i];
            numericValue = Character.getNumericValue(asciiValue);
            //For digits at even index, multiply by 1
            if (i % 2 == 0) {
                counter += numericValue * 1;
            //For digits at odd index, multiply by 3
            } else {
                counter += numericValue * 3;
            }
        }
        //Loop for second check
        for (int i = 0; i < digits.length; i++) {
            if (i % 2 == 0) {
                //Sum of all numbers at even index
                asciiValue = digits[i];
                numericValueEven += Character.getNumericValue(asciiValue);
            } else {
                //Sum of all numbers at odd index
                asciiValue = digits[i];
                numericValueOdd += Character.getNumericValue(asciiValue);
            }
        }
        //Get last digit of ISBN and convert it from ASCII value to int
        int lastDigit = digits[digits.length - 1];
        lastDigit = Character.getNumericValue(lastDigit);
        //ISBN condition 1: Last digit of the ISBN must be equal to (10 - ((x1 + 3 * x2 + x3 + 3 * x4 ...etc)%10)
        //ISBN condition 2: If the sum of the 2nd, 4th, 6th, 8th, 10th, and 12th digits is tripled then added to the remaining digits 
        //(1st, 3rd, 5th, 7th, 9th, 11th, and 13th), the total will always be divisible by 10 
        if (10 - (counter % 10) == lastDigit && (((numericValueOdd * 3) + numericValueEven) % 10 == 0)) {
            return true;
        }
        return false;
    }
    
    //Methods
    @Override public String toString() {
        return (this.isbn + " " + this.title + " " + this.issueYear + " " + this.authorName + " " + this.price + " " + this.numberOfPages);
    }
    
    @Override public boolean equals(Object obj) {
        if (obj == null || this == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return (this.price == ((Book2)obj).price 
                && this.numberOfPages == ((Book2)obj).numberOfPages 
                && this.issueYear == ((Book2)obj).issueYear
                && this.isbn == ((Book2)obj).isbn
                && this.authorName.equals(((Book2)obj).authorName)
                && this.title.equals(((Book2)obj).authorName));
    }

}
