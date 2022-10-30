import java.util.*;
import java.awt.*;
import javax.swing.*; 
import java.io.*;
import javax.imageio.ImageIO;
import java.net.*;
import java.awt.image.BufferedImage;

public class Main extends javax.swing.JPanel {

  public static void printSlow(String type) {
    String text = type;
    for(int i = 0; i < text.length(); i++){
      System.out.printf("%c", text.charAt(i));
      try { Thread.sleep(60); } 
      catch(InterruptedException ex){ Thread.currentThread().interrupt(); }
    }
  }

  public static void main(String[] args) throws IOException, Exception { 
    Scanner myScan = new Scanner(System.in);

    //Intro Message & Signup
    File file = new File("IDGen.png");
    BufferedImage bufferedImage = ImageIO.read(file);
    ImageIcon imageIcon = new ImageIcon(bufferedImage);
    JFrame jFrame = new JFrame();
    jFrame.setLayout(new FlowLayout());
    jFrame.setSize(500, 500);
    JLabel jLabel = new JLabel();
    jLabel.setIcon(imageIcon);
    jFrame.add(jLabel);
    jFrame.setVisible(true);

    printSlow("\nWelcome to ID Generator. Press enter to create an account for your school: ");
    try { System.in.read(); } 
    catch(Exception e) {}
    jFrame.dispose();

    System.out.print(ConsoleColors.BLUE_BOLD + "Account Holder Name: " + ConsoleColors.RESET);
    String user = myScan.nextLine();
    System.out.print(ConsoleColors.BLUE_BOLD + "School Name: " + ConsoleColors.RESET);
    String schoolName = myScan.nextLine();
    School yourSchool = new School(schoolName, schoolName);
    printSlow("\nYour username is: " + ConsoleColors.GREEN_BOLD + yourSchool.getSchoolName() + ConsoleColors.RESET + "\nYour password is: " + ConsoleColors.GREEN_BOLD + yourSchool.getPassword() + ConsoleColors.RESET);
    printSlow("\nPlease save your password. It will be needed to access private information.\n");

    //Demo
    printSlow("\nThank you for registering with IDGen. Enter (1) to view a demo of our services or (2) to proceed: ");
    String ans = myScan.nextLine();
    if (ans.equals("1")) {
      IdInfo[] one = {new IdInfo("Mr. Holmer",999999999,"Oct. 20. 1954", "Kindergarten")};
      School demoSchool = new School("X Y Z Elementary School", one);
      System.out.println("\nDemo Student At " + ConsoleColors.GREEN_BOLD + "X Y Z Elementary School" + ConsoleColors.RESET);
      System.out.println(ConsoleColors.BLUE_BOLD + "Name: " + ConsoleColors.RESET + one[0].getName());
      System.out.println(ConsoleColors.BLUE_BOLD + "Osis: " + ConsoleColors.RESET + one[0].getOsis());
      System.out.println(ConsoleColors.BLUE_BOLD + "Date of Birth: " + ConsoleColors.RESET + one[0].getBirthDate());
      System.out.println(ConsoleColors.BLUE_BOLD + "Grade: " + ConsoleColors.RESET + one[0].getGrade());
      NewJPanel demoPanel = new NewJPanel(demoSchool, one[0]);
      printSlow("\nDemo Student's ID is generated. You can add a photo to the ID, print ID, or email ID. Close ID to exit demo.");
      one[0].setFrameStatus(true);
      boolean check = true;
      while (check == true) {
        check = one[0].getFrameStatus();
        System.out.print("");
      }
    }

    //Create Students
    System.out.print("\nPress enter to add students to your school: ");
    try { System.in.read(); } 
    catch(Exception e) {}
    System.out.print("Number of Students: ");
    int arrayLen = Integer.parseInt(myScan.nextLine());
    IdInfo[] studentInfo = new IdInfo[arrayLen];
    for (int i = 0; i < arrayLen; i++) {
      System.out.print(ConsoleColors.BLUE_BOLD + "\nStudent #" + (i+1) + " Name: " + ConsoleColors.RESET);
      String studentName = myScan.nextLine();
      System.out.print(ConsoleColors.BLUE_BOLD + "Student #" + (i+1) + " Date of Birth: " + ConsoleColors.RESET);
      String studentDOB = myScan.nextLine();
      System.out.print(ConsoleColors.BLUE_BOLD + "Student #" + (i+1) + " Osis: " + ConsoleColors.RESET);
      int studentOsis = Integer.parseInt(myScan.nextLine());
      System.out.print(ConsoleColors.BLUE_BOLD + "Student #" + (i+1) + " Grade Level: " + ConsoleColors.RESET);
      String studentGrade = myScan.nextLine();
      IdInfo student = null;
      if (IdInfo.osisWorks(studentOsis)) {
        try { student = new IdInfo(studentName, studentOsis, studentDOB, studentGrade); studentInfo[i] = student; }
        catch (NullPointerException exception) {
          printSlow("Something went wrong. Please reenter this student later.\n");
        }
      } else {
        printSlow(ConsoleColors.RED_BOLD + "Invalid osis. Please retry later.\n" + ConsoleColors.RESET);
      }
    }

    yourSchool.changeArray(studentInfo);
    printSlow("\nHere are your options: " + ConsoleColors.GREEN_BOLD + "\n(1) " + ConsoleColors.BLUE_BOLD + "Add Student" + ConsoleColors.GREEN_BOLD + "\n(2) " + ConsoleColors.BLUE_BOLD + "Delete Student" + ConsoleColors.GREEN_BOLD + "\n(3) " + ConsoleColors.BLUE_BOLD + "Edit Student Information" + ConsoleColors.GREEN_BOLD + "\n(4) " + ConsoleColors.BLUE_BOLD + "Access Student ID" + ConsoleColors.GREEN_BOLD + "\n(5) " + ConsoleColors.BLUE_BOLD + "Download All Student Information" + ConsoleColors.GREEN_BOLD + "\n(6) " + ConsoleColors.BLUE_BOLD + "Terminate Your License" + ConsoleColors.RESET + "\n\nEnter the corresponding number: ");

    String userFinalAns = "";
    int userAnswer = Integer.parseInt(myScan.nextLine());
    while (userAnswer != Integer.MAX_VALUE) {
      if (userAnswer == 1) {
        System.out.print(ConsoleColors.BLUE_BOLD + "\nStudent Name: " + ConsoleColors.RESET);
        String studentName = myScan.nextLine();
        System.out.print(ConsoleColors.BLUE_BOLD + "Student Date of Birth: " + ConsoleColors.RESET);
        String studentDOB = myScan.nextLine();
        System.out.print(ConsoleColors.BLUE_BOLD + "Student Osis: " + ConsoleColors.RESET);
        int studentOsis = Integer.parseInt(myScan.nextLine());
        System.out.print(ConsoleColors.BLUE_BOLD + "Student Grade Level: " + ConsoleColors.RESET);
        String studentGrade = myScan.nextLine();
        IdInfo student = null;
        if (IdInfo.osisWorks(studentOsis)) {
          try { student = new IdInfo(studentName, studentOsis, studentDOB, studentGrade);  yourSchool.addItem(student); }
          catch (NullPointerException exception) {
            printSlow("Something went wrong. Please reenter this student later.\n");
          }
        } else {
          printSlow(ConsoleColors.RED_BOLD + "Invalid Osis. Please retry later.\n" + ConsoleColors.RESET);
        }
        printSlow("Action finished. Press enter to proceed.");
        try { System.in.read(); } catch(Exception e) {}
      }
      if (userAnswer == 2) {
        printSlow("Osis of the student to remove: ");
        int osisRemove = Integer.parseInt(myScan.nextLine());
        String result = yourSchool.findStudent(osisRemove);
        try {
          int re = Integer.parseInt(result);
          yourSchool.deleteItem(re);
        } catch (NullPointerException e) {  printSlow(ConsoleColors.RED_BOLD + "Invalid Osis. Please retry.\n" + ConsoleColors.RESET); }
        catch (NumberFormatException e) {  printSlow(ConsoleColors.RED_BOLD + "Invalid Osis. Please retry.\n" + ConsoleColors.RESET); }
        printSlow("Action finished. Press enter to proceed.\n");
        try { System.in.read(); } catch(Exception e) {}
      }
      if (userAnswer == 3) {
        printSlow("Osis of the student to edit: ");
        int osisEdit = Integer.parseInt(myScan.nextLine());
        String result = yourSchool.findStudent(osisEdit);
        try {
          int re = Integer.parseInt(result);
          System.out.print(ConsoleColors.BLUE_BOLD + "\nStudent Name: " + ConsoleColors.RESET);
          String studentName = myScan.nextLine();
          System.out.print(ConsoleColors.BLUE_BOLD + "Student Date of Birth: " + ConsoleColors.RESET);
          String studentDOB = myScan.nextLine();
          System.out.print(ConsoleColors.BLUE_BOLD + "Student Osis: " + ConsoleColors.RESET);
          int studentOsis = Integer.parseInt(myScan.nextLine());
          System.out.print(ConsoleColors.BLUE_BOLD + "Student Grade Level: " + ConsoleColors.RESET);
          String studentGrade = myScan.nextLine();
          IdInfo student = null;
          if (IdInfo.osisWorks(studentOsis)) {
            try { student = new IdInfo(studentName, studentOsis, studentDOB, studentGrade);  
            //yourSchool.addItem(student); 
            yourSchool.changeItem(re, student); }
            catch (NullPointerException exception) {
              printSlow("Something went wrong. Please reenter this student later.\n");
            }
            catch (NumberFormatException e) {  printSlow(ConsoleColors.RED_BOLD + "Invalid Osis. Please retry.\n" + ConsoleColors.RESET); }
          } else {
            printSlow(ConsoleColors.RED_BOLD + "Invalid Osis. Please retry." + ConsoleColors.RESET);
          }
        } catch(NumberFormatException e) { printSlow(result); }
        printSlow("Action finished. Press enter to proceed.");
        try { System.in.read(); } catch(Exception e) {}
      }
      if (userAnswer == 4) {
        NewJPanel panel = new NewJPanel();
        printSlow("Osis of the student to access: ");
        int osisAccess = Integer.parseInt(myScan.nextLine());
        String result = yourSchool.findStudent(osisAccess);
        try {
          int re = Integer.parseInt(result);
          IdInfo student = yourSchool.accessStudent(re);
          panel = new NewJPanel(yourSchool, student);
          printSlow("Close ID to proceed.");
          student.setFrameStatus(true);
          boolean check = student.getFrameStatus();
          while (check == true) {
            check = student.getFrameStatus();
            System.out.print("");
          }
         } catch (NumberFormatException e) {System.out.println(result);}
         catch (NullPointerException exception) {
              printSlow("Something went wrong. Please reenter this student later.\n");
            }
      }
      if (userAnswer == 5) {
        printSlow("Enter your password to proceed: ");
        String pass = myScan.nextLine();
        if (pass.equals(yourSchool.getPassword())) {
          printSlow("CSV is being generated...");
          yourSchool.createCSV();
        } else {
          printSlow(ConsoleColors.RED_BOLD + "Your password was incorrect. Please retry." + ConsoleColors.RESET);
        }
        printSlow("\nAction finished. Press enter to proceed.");
        try { System.in.read(); } catch(Exception e) {}
      } 
      if (userAnswer == 6) {
      System.out.print("Are you sure you want to terminate your license? Enter \"yes\" to confirm and any other number to keep your license: ");
      userFinalAns = myScan.nextLine();
      if (userFinalAns.equals("yes")) {
        break;
      } else {
        printSlow("We are glad you are staying! Press enter to proceed.");
        try { System.in.read(); } catch(Exception e) {}
      }
      } 
      if (userAnswer != 1 && userAnswer != 2 && userAnswer != 3 && userAnswer != 4 && userAnswer != 5 && userAnswer != 6) {
        printSlow(ConsoleColors.RED_BOLD + "\nYou inputted an incorrect option. Please try again." + ConsoleColors.RESET);
      }
       System.out.print("\nHere are your options: " + ConsoleColors.GREEN_BOLD + "\n(1) " + ConsoleColors.BLUE_BOLD + "Add Student" + ConsoleColors.GREEN_BOLD + "\n(2) " + ConsoleColors.BLUE_BOLD + "Delete Student" + ConsoleColors.GREEN_BOLD + "\n(3) " + ConsoleColors.BLUE_BOLD + "Edit Student Information" + ConsoleColors.GREEN_BOLD + "\n(4) " + ConsoleColors.BLUE_BOLD + "Access Student ID" + ConsoleColors.GREEN_BOLD + "\n(5) " + ConsoleColors.BLUE_BOLD + "Download All Student Information" + ConsoleColors.GREEN_BOLD + "\n(6) " + ConsoleColors.BLUE_BOLD + "Terminate Your License" + ConsoleColors.RESET);
       printSlow("\n\nEnter the corresponding number: ");
      userAnswer = Integer.parseInt(myScan.nextLine());
    }

    printSlow("\nWe are sad to see you go. We hope you will use IDGen in the future again. Please fill out the following feedback form to better inform our services.");
    //Works on vscode
   // Desktop desk = Desktop.getDesktop();
 //   desk.browse(new URI("https://forms.gle/MbpmqevWLbJegptK6"));
    
  }
}
