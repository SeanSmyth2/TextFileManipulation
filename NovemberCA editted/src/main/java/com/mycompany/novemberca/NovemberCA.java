/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.novemberca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Sean
 */
public class NovemberCA {
    // The above line defines the class which I have chosen to name NovemberCA
    private static String studentName;
    private static String studentFirstName;
    private static String studentLastName;
    private static String classNumber;
    private static String studentNumber;
    private static int studentNumberLength;
    private static String studentWorkLoad;
    private static int classInt;
    private static boolean fNameCheck;
    private static boolean lNameCheck;
    
    
    // The variables I used are listed above
    // I defined them outside of the method in case I find a need to call them within a separate method down the line
  public static void main(String[] args) throws IOException{
      //This is my main method, named main, below which is my try/catch which simply pulls the necesarry data from the file
      //For now all the data is being read as strings for convenience, conversion occours later on
      //I also used var.trim() to remove any potential spaces before or after the strings
     
      
      
      try{
          Scanner myReader = new Scanner(new FileReader("student.txt"));
          studentName = myReader.nextLine();
          studentName = studentName.trim();
        //System.out.println("The students name is " + studentName);
          classNumber = myReader.nextLine();
          classNumber = classNumber.trim();
          //System.out.println("The student is taking " + classNumber + " classes");
          studentNumber = myReader.nextLine();
          studentNumber = studentNumber.trim();
          //System.out.println("The students number is " + studentNumber);
        }catch(Exception e)
      {
          System.out.println("File access error - Unable to find specified");
          //This is my error message should the code within the try fail to run, there may be errors other than a file not found
          //Come back later to rewrite error message - Fixed
      }
     try{
           classInt = Integer.parseInt(classNumber);
           //System.out.println(classInt);
           
             if (1==classInt) {
               studentWorkLoad="minimal";
           } else if (2==classInt) {               //These compare the students classes to various values and assign them their determined workload
               studentWorkLoad="light";
           } else if (classInt>=3 && classInt<=5) {
               studentWorkLoad="part time";
           } else if (classInt>=6) {
               studentWorkLoad="Full time";
           }
           
           //System.out.println("The students workload is " + studentWorkLoad);
     }catch(Exception e){
         studentWorkLoad="Number of classes must be between 1 and 6 inclusive";
         System.out.println("Unable to detmine student workload, unexpected number of classes");
     }
     //This try/catch converts the classNumber variable from a string to an integrer, this allows us them then compare it mathmatically.
     //I used a number of else if statemts to define the workload by the value of the classInt variable

     try{
         String[] studentNames = studentName.split(" ");
         studentFirstName=studentNames[0];
         studentFirstName=studentFirstName.trim();     
         //System.out.println(studentFirstName);
         studentLastName=studentNames[1];
         studentLastName=studentLastName.trim();
         //System.out.println(studentLastName);
     }catch(Exception e){
          System.out.println("Unknown regex error");
     }
     if (studentLastName == " "){
         System.out.println("Student name must contain only a single space");
         studentName = "Error in student name, see error code for details";
     }else{
         studentName = studentFirstName + " " + studentLastName;
     }
     //The above is used to split the studentName string using regex. When it encounters a space it splits the string making an array with two entries
     //This proves the name has a space in it, otherwise it would fail. I then trim both to ensure there's no more than a single space which will be added
     //when I recombine them later back to a single string
     try{
        fNameCheck = studentFirstName.matches("[A-Za-z]+");
        //System.out.println(fNameCheck);
        lNameCheck = studentLastName.matches("[a-zA-Z0-9]+");
        //System.out.println(lNameCheck);
     }catch(Exception e){
         System.out.println("boolean error");
     }
     if (fNameCheck==true && lNameCheck==true){
         studentName = studentFirstName + " " + studentLastName;
        }else System.out.println("Student name format incompatible, please ensure your first name contains only letters, last name contains no special characters, and both are divided by a single space");
     //This code checks the first name contains only alphabetical character both cases, then the last name checks to also allow number
     //These are stored as booleans, the if/else then confirms if they are both true, if so re-adds the space and sets the studentName variable once mor
     //if either result is false the variable becomes a string that doubles as our error message
     try{
         studentNumberLength = studentNumber.length();
         //System.out.println(studentNumberLength);
     }catch(Exception e){
         System.out.println("Issue with string length command");
     }
     if (studentNumberLength <= 6){
         System.out.println("The student number must be at least 8 characters long");
     } else{
         String studentNumberYear = studentNumber.substring(0,2);  //We split the student number into a substring containing the first two characters
         int studentNumericalYear = Integer.parseInt(studentNumberYear); //Next we convert it to an integer from a string
         String studentNumberRemaining = studentNumber.substring(2); //This is the second substring of all text following the first 2 characters
         boolean studentNumberFirst = false; //A boolean used to check the validity of the student numbers year
         String[] studentNumberRemainingSegmented = studentNumberRemaining.split("[0-9]"); // the splits the segment of the student number following the inital 2 digits, it contains everything up to where it finds a digit using regex
         String studentNumberCharacters = studentNumberRemainingSegmented[0]; //This calls the string from the array containing middle portion of the student number, that being the portion between the numbers
         boolean studentEndingBoolean;
         //System.out.println(studentNumberCharacters);
         String[] studentNumberEnding = studentNumberRemaining.split("[A-Za-z]+"); // This finally isolates the end numbers
         String studentNumberFinalNumbers = studentNumberEnding[1]; // This makes a string from the split above
         //System.out.println(studentNumberFinalNumbers);
         
         
         if (studentNumericalYear >= 20 && studentNumericalYear <= 22){
             studentNumberFirst = true;
         } else{
             
         }
         
         
         int studentEndingDigits = Integer.parseInt(studentNumberFinalNumbers);
         if (studentEndingDigits >= 1 && studentEndingDigits <= 200){
             studentEndingBoolean = true;
         } else{
             studentEndingBoolean = false;
            // System.out.println("The student number format is incorrect");
         }
        if (studentEndingBoolean && studentNumberFirst == true){
            studentNumber = studentNumberYear+studentNumberRemaining;
        } else{
            studentNumber = "The student number format is incorrect";
            System.out.println(studentEndingBoolean);
            System.out.println(studentNumberFirst);
        }
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("studentoutput.txt"));
            myWriter.write(studentName);
            myWriter.write(System.lineSeparator());
            //System.out.println(studentName + classNumber + studentNumber);
            myWriter.write(classNumber);
            myWriter.write(System.lineSeparator());
            myWriter.write(studentNumber);
            myWriter.close();
     }catch(Exception e){
         System.out.println("Error writing to directory, please ensure you have permissions to write to the location");
     }
         
         
     }
          }
  }

        
    

