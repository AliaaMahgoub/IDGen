import java.security.SecureRandom;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class School {
  //instance variables
  private String schoolName;
  private String userName;
  private final String password = generateRandomPassword(15);
  private String passwordCheck;
  private IdInfo[] all;
  public static int numberOfStudents;
  private static int numberOfSchools;

  //Constructors
  public School(String schoolName, IdInfo[] all) {
    this.schoolName = makeProper(schoolName);
    this.all = all;
    numberOfSchools ++;
  }

  public School (String schoolName, String userName) {
    this.schoolName = makeProper(schoolName);
    numberOfSchools ++;
    this.userName = userName;
  }
  
  //This method changes the inputted school name to make it look proper with every letter after a space capitalized.
  // precondition: toMake is a String, not an int
  // postcondition: returns correctly capitalized version of toMake, capitalizing the first letter of each word
  private String makeProper (String toMake){
    if (toMake.length() > 0){
      String finalString = ""; 
      for (int i = 0; i < toMake.length(); i ++){
        String current = toMake.substring (i, i + 1);
        if (i == 0 || toMake.substring(i - 1, i).equals(" ")){
          finalString += current.toUpperCase();
        }
        else{
          finalString += current.toLowerCase();
        }
      }
      return finalString;
    }
    else{
      return "";
    }
  }
  // Method to generate a random alphanumeric password of a specific length
  public String generateRandomPassword(int len)
  {
    // ASCII range – alphanumeric (0-9, a-z, A-Z)
    final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    SecureRandom random = new SecureRandom();
    String sb = "";
    // each iteration of the loop randomly chooses a character from the given
    // ASCII range and appends it to the `StringBuilder` instance
    for (int i = 0; i < len; i++)
    {
      int randomIndex = random.nextInt(chars.length());
      sb += chars.charAt(randomIndex);
    }
    return sb;
  }

  //Accessors
  public String getSchoolName () {return schoolName;}
  public IdInfo[] getAll () {return all;}
  public static int getNumberOfSchools () {return numberOfSchools;}
  public String getPassword() { return password; }

  //Mutators and Methods
  public void changeSchoolName (String newSchoolName){
    schoolName = newSchoolName;
  }

  public void deleteItem (int indexToDelete){
    IdInfo[] newArr = new IdInfo[all.length-1];
    int count = 0;
    for (int i = 0; i < all.length; i ++){
      if (i != indexToDelete) {
        newArr[count] = all[i];
        count++;
      }
    }
    all = newArr;
  }

  public IdInfo accessStudent(int i) {
    return all[i];
  }
  
  public void addItem (IdInfo toAdd){
    IdInfo[] newArray = new IdInfo[all.length + 1];
    for (int i = 0; i < all.length; i++){
      newArray[i] = all[i];
    }
    newArray[all.length] = toAdd;
    all = newArray;  
  }

  public void changeItem (int index, IdInfo newItem){
    all[index] = newItem;
  } 

  public void clearArray (){
    IdInfo[] newAll= {};
    all = newAll;
  }

  public void changeArray (IdInfo[] toChange){
    all = toChange;
  }

  //Find a student from the list with the provided osis.
  public String findStudent (int osis){
    IdInfo wanted = new IdInfo();
    for (int i = 0; i < all.length; i++) {
      if (all[i].getOsis() == osis) {
        return "" + i;
      }
    }
    return "Student Not Found";
  }

  //create a CSV
  public void createCSV()
  {
    try (PrintWriter writer = new PrintWriter(schoolName+".csv")) {
      String name;
      int osis;
      String dob;
      String grade;
      
      StringBuilder sb = new StringBuilder();
      sb.append(schoolName);
      sb.append('\n');

      sb.append("Name,");
      sb.append("OSIS,");
      sb.append("Date of Birth,");
      sb.append("Grade\n");

      for(int i=0; i < all.length; i++) {
        name = this.all[i].getName();
        osis = this.all[i].getOsis();
        dob = this.all[i].getBirthDate();
        grade = this.all[i].getGrade();
        sb.append(name+","+osis+","+dob+","+grade+"\n");
      } writer.write(sb.toString());
      System.out.println("csv generated!");
    } catch (FileNotFoundException e)
    {  System.out.println(e.getMessage()); }
  }
  
  //toString
  public String toString (){
    numberOfStudents = all.length;
    return ConsoleColors.BLUE_BOLD + schoolName + ConsoleColors.RESET + "\nNumber of Students: " + numberOfStudents;
  }
}