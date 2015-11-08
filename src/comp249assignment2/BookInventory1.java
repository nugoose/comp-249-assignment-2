package comp249assignment2;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.nio.file.FileAlreadyExistsException;
import java.io.*;

public class BookInventory1 {
    private static Book[] bookArray;
    
    //Default constructor
    public BookInventory1() {
        bookArray = null;
    }
    
    //Takes an inputFile and fixes any duplicate ISBN's this file might have
    //Writes corrected inputName file to outputName file
    public void fixInventory(String inputName, String outputName) throws FileNotFoundException, IOException, FileAlreadyExistsException {
        PrintWriter outputStream = new PrintWriter(new FileOutputStream(outputName));
        bookArray = stringToBook(inputName);
        duplicateFinder(bookArray);
        for (int i = 0; i < bookArray.length; i++) {
            outputStream.println(bookArray[i]);
        }
        outputStream.close();
    }
    
    //Prints inputFile (initial inventory) followed by outputFile (corrected inventory) to console
    public void displayFileContents(String inputFile, String outputFile) throws FileNotFoundException, IOException {
        Scanner inputStream = new Scanner(new FileInputStream(inputFile));
        System.out.println("");
        System.out.println("Here are the contents of file " + inputFile + " AFTER copying operation");
        System.out.println("=======================================================================");
        while (inputStream.hasNextLong()) {
            System.out.println(inputStream.nextLine());
        }
        inputStream.close();
        System.out.println("");
        System.out.println("Here are the contents of file " + outputFile);
        System.out.println("=======================================================================");
        inputStream = new Scanner(new FileInputStream(outputFile));
        while (inputStream.hasNextLong()) {
            System.out.println(inputStream.nextLine());
        }
        inputStream.close();
    }
    
    //Converts lines from inputFile to Book objects
    private Book[] stringToBook(String inputName) throws FileNotFoundException, IOException {
        int bookCount;
        
        //Call private helper method to get number of books or lines in text file
        bookCount = getBookQuantity(inputName);
        //If count is less than 2, close program because no work is needed (always no duplicates)
        if (bookCount < 2) {
            System.out.println("This file contains no duplicates. No operations needed.");
            System.exit(0);
        }
        try {
        Scanner inputStream = new Scanner(new FileInputStream(inputName));
        //Set bookArray attribute to size bookCount
        bookArray = new Book[bookCount];
        for (int i = 0; i < bookArray.length; i++) {
            Book myBook = new Book();
            myBook.setIsbn(inputStream.nextLong());
            myBook.setTitle(inputStream.next());
            myBook.setIssueYear(inputStream.nextInt());
            myBook.setAuthorName(inputStream.next());
            myBook.setPrice(inputStream.nextDouble());
            myBook.setNumberOfPages(inputStream.nextInt());
            bookArray[i] = myBook;
        } 
        inputStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(inputName + " not found.");
            System.exit(0);
        }
        return bookArray;
    }
    
    //Determines number of books in inputFile
    private int getBookQuantity(String inputName) throws FileNotFoundException, IOException {
        System.out.println("Attempting to open file: " + inputName);
        int bookCount = 0;
        Scanner inputStream;
        try {
        System.out.println("Detecting number of records in file: " + inputName);
        inputStream = new Scanner(new FileInputStream(inputName));
      
        while (inputStream.hasNextInt()) {
            bookCount++;
            inputStream.nextLine();
            }
        System.out.println("The file has " + bookCount + " records.");
        inputStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
        return bookCount;
    }
    
    //Determines whether given ISBN is unique
    //Returns true if unique, false if !unique
    private boolean isbnIsUnique(long isbn) {
        if (bookArray.length < 2) {
            return true;
        }
        for (int i = 0; i < bookArray.length; i++) {
            if (isbn == bookArray[i].getIsbn()) {
                return false;
            }
        }
        return true;
    }
    
    //Iterates through inventory looking for duplicate ISBN's
    //Prompts the user to correct duplicate ISBN
    private void duplicateFinder(Book[] bookArray) {
        Scanner keyboard = new Scanner(System.in);
        long userIsbn = 0;
        boolean done;
        
        for (int i = 0; i < bookArray.length; i++) {
            long isbn = bookArray[i].getIsbn();
            for (int j = 0; j < bookArray.length; j++) {
                done = false;
                if (isbn == bookArray[j].getIsbn() && j != i) {
                    while (!done) {
                    try {
                        System.out.print("Duplicate ISBN detected in record #" + (j+1) + ". Please enter the correct ISBN:");
                        userIsbn = keyboard.nextLong();
                        if (!isbnIsUnique(userIsbn)) {
                        throw new DuplicateISBNException(userIsbn);
                        }
                        bookArray[j].setIsbn(userIsbn);
                        done = true;
                    }
                    catch (DuplicateISBNException e1) {
                        if (!isbnIsUnique(userIsbn)) {
                            e1.getMessage();
                            for (int k = 0; k < bookArray.length; k++) {
                                if (userIsbn == bookArray[k].getIsbn()) {
                                    System.out.println("Initial appearance of ISBN " + userIsbn + " was found at record #" + (k+1));
                                    break;
                                }
                            }
                            
                        }
                    }
                    catch (InputMismatchException e2) {
                        keyboard.nextLine();
                        System.out.println("Invalid entry. Please enter a valid ISBN.");
                    }
                    }
                }
            }
        }
    }
    
}
