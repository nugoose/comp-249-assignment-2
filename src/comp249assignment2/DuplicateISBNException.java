package comp249assignment2;

import java.util.Scanner;

public class DuplicateISBNException extends Exception{
    public DuplicateISBNException() {
        super("Duplicate ISBN.");
    }
    public DuplicateISBNException(String message) {
        super(message);
    }
    public DuplicateISBNException(long isbn) {
        super("Attempt of duplicate entry to a previous record.");
        System.out.println(this.getMessage());
        
    }
    
}
