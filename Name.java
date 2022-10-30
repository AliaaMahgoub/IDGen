public class Name {
  
  private String first;
  private String middleInitial;
  private String last;
  
  public Name(String name) {
    String temp = "";
    for (int i = 0; i < name.length(); i++) {
      if (name.substring(i,i+1).equals(" ")) {
        i++;
        if (i == 1) {
          this.first = makeProper(temp);
        } else if (i == 2) {
          this.middleInitial = makeProper(temp);
        } else if (i == 3) {
          this.last = makeProper(temp);
        } 
        temp = "";
      } else {
        temp += name.substring(i,i+1);
      }
    }
  }

  public Name(String first, String middleInitial, String last){
    this.first = makeProper (first);
    this.middleInitial = makeProper (middleInitial);
    this.last = makeProper (last);
  }

  public Name() {}

  //Make Proper
  private String makeProper (String toMake){
    if (toMake.length() > 0){
      String firstLetter = toMake.substring (0, 1);
      String rest = toMake.substring (1);
      return firstLetter.toUpperCase() + rest.toLowerCase();
    }
    else {
      return "";
    }
  }

  //Accessors
  public String getFirst (){
    return first;
  }

  public String getMiddleInitial (){
    return middleInitial;
  }

  public String getLast (){
    return last;
  }

  //Mutators
  public void changeFirst (String first){
    this.first = first;
  }

  public void changeMiddle (String middle){
    this.middleInitial = middle;
  }

  public void changeLast (String last){
    this.last = last;
  }
  
  //Double Check Information
  public boolean status (){
    return firstWorks() && middleWorks() && lastWorks(); 
  }

  public String nameCheck (){
    String report = "";
    boolean[] toCheck = {firstWorks(), middleWorks(), lastWorks()};
    String[] parts = {"first name", "middle initial", "last name"};
    for (int i = 0; i < toCheck.length; i ++){
      if (!toCheck[i]){
        report += ConsoleColors.RED + "\nDouble check " + parts [i] + ". Remove the digits and special characters. ";
        if (i == 1){
          report += ConsoleColors.RED + "\nYour middle initial can only be one letter.";
        }
      }
    }
    return report;
  }

  private boolean checkString (String part){
    String all = "0123456789!@#$%^&*?-_+=[]{}:;/|\"\\.,><~";
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

  private boolean firstWorks(){
    return first.length() > 0 && !checkString(first);
  }

  private boolean middleWorks(){
    return !(checkString(middleInitial) && (middleInitial.length() == 1));
  }

  private boolean lastWorks(){
    return last.length() > 0 && !checkString(last);
  }

  public String toString (){
    return first + " " + middleInitial + " " + last;
  }
  
}