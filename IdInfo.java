//import com.aspose.barcode.generation;

public class IdInfo{
  private String name;
  private int osis;
  private String birthDate;
  private String grade;
  private static int numOfStudents;
  private boolean frameOpen;

  //Constructors
  public IdInfo (){}
  
  // precondition: 
  public IdInfo (String name, int osis, String birthDate, String grade) {
    if (osisWorks(osis)) {
      this.name = name;
      this.osis = osis;
      this.birthDate = birthDate;
      this.grade = grade.toUpperCase();
      //numOfStudents ++;
      frameOpen = false;
    }
  }

  //Accessors
  public String getName (){
    return name;
  }

  public int getOsis (){
    return osis;
  } 

  public String getBirthDate (){
    return birthDate;
  }

  public String getGrade (){
    return grade;
  }

  public static int getNumOfStudents (){
    return numOfStudents;
  }

  public boolean getFrameStatus() { return frameOpen; }

  //Mutators 
  public void changeName (String name){
    this.name = name;
  }

  public void changeOsis (int newOsis){
    this.osis = newOsis;
  }

  public void changeBirthDate (String dob){
    this.birthDate = dob;
  }

  public void changeGrade (String newGrade){
    this.grade = newGrade.toUpperCase ();
  }

  public void setFrameStatus(boolean set) { frameOpen = set; }

  public static boolean osisWorks (int osis) {
    String o = Integer.toString(osis);
    return o.length() == 9;
  }

  private boolean checkString (String part){
    String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*?-_+=[]{}:;/|\"\\.,><~";
    boolean count = false;
    for (int i = 0; i < all.length(); i ++)    
    {
      int here = part.indexOf (all.substring (i, i + 1));
      if (here != -1)
      {
        i += all.length();
        count = true;
      }
    }
    return count;
  }

  private boolean gradeWorks (){
    if (!checkString(grade)){
      int g = Integer.parseInt(grade);
      return (g > 0) && (g <= 12);
    }
    else{
      return grade.equals ("KINDERGARTEN") || grade.equals ("K");
    }
  }
  
  public String toString (){
    return ConsoleColors.WHITE_BOLD + "\nStudent " + numOfStudents + ":\n" + ConsoleColors.RESET + "Name: " + name + "\nOsis: " + osis + "\nDate of Birth: " + birthDate + "\nGrade: " + grade; 
  } 
}