package comp249assignment2;

import java.util.Scanner;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class Comp249assignment2 {

    public static void main(String[] args) {
        String outputFile = "";
        String inputFile = "Initial_Book_Info.txt";
        File readFile = new File(inputFile);
        File writeFile = null;
        BookInventory1 myBookInventory = new BookInventory1();
        boolean validFile = false;
        while (!validFile) {
        try {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter the name of the output file, which will have the correct information: ");
        outputFile = keyboard.next();
        writeFile = new File(outputFile);
        if (writeFile.exists()) {
            throw new FileAlreadyExistsException(outputFile);
        }
            myBookInventory.fixInventory(inputFile, outputFile);
            myBookInventory.displayFileContents(inputFile, outputFile);
            validFile = true;
        }
        catch (FileNotFoundException e2) {
            System.out.println("File could not be opened. Sorry!");
            System.exit(0);
        }
        catch (FileAlreadyExistsException e3) {
            if (writeFile != null) {
            System.out.println("Error: There is an existing file called: " + outputFile);
            System.out.println("That file aleady has a size of " + writeFile.length() + " bytes.");
            }
            else {
                System.out.println("Problem writing to disk. Program will terminate.");
                System.exit(0);
            }
        }
        catch (IOException e1) {
            System.out.println("File not found. Sorry!");
            System.exit(0);
        }
    }
        System.out.println("");
        System.out.println("Thank you for trusting us with your inventory.");
        System.out.println("See you next time!");
    }
    
}
