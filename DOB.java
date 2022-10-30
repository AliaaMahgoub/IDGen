public class DOB{
  
  private String month;
  private String date;
  private int year;

  public DOB(String dob) {
    String temp = "";
    for (int i = 0; i < dob.length(); i++) {
      if (dob.substring(i,i+1).equals(" ")) {
        i++;
        if (i == 1) {
          this.month = temp;
        } else if (i == 2) {
          this.date = temp;
        } else if (i == 2) {
          this.year = Integer.parseInt(temp);;
        } 
        temp = "";
      } else {
        temp += dob.substring(i,i+1);
      }
    }
  }

  public DOB(String month, String date, int year) {
    this.month = month;
    this.date = date;
    this.year = year;
  }

  public DOB (){}
  
  //Accessors
  public String getMonth (){
    return month;
  }

  public String getDate (){
    return date;
  }

  public int getYear (){
    return year;
  }

  //Mutators
  public void changeMonth (String month){
    this.month = month;
  }

  public void changeDate (String date){
    this.date = date;
  }

  public void changeYear (int year){
    this.year = year;
  }

  //Checkers
  public boolean status (){
    return monthWorks() && dateWorks() && yearWorks(); 
  }

  public String DOBCheck (){
    String report = "";
    boolean [] allParts = {monthWorks(), dateWorks (), yearWorks ()};
    String [] rep = {"Month must be in the form XX. Please enter a valid month.", "Date must be in the form XX. Please enter a valid date.", "Year must be in the form XXXX. Please enter a valid year."};
    for (int i = 0; i < 3; i ++){
      if (!allParts [i]){
        report += "\n" + ConsoleColors.RED + rep [i];
      }
    }  
    return report;
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

  private boolean monthWorks(){
    int mon = Integer.parseInt(month);
    return !checkString(month) && (mon > 0 && mon <= 12) ;
  }

  private boolean dateWorks(){
    int dat = Integer.parseInt(date);
    boolean days = false;
    if (month == "04" || month == "06" || month == "09" || month == "11"){
      days = (dat < 31);
    }
    else if (month == "02"){
      days = dat < 29;
      if (((year % 4 == 0) && (year % 100!= 0)) || (year % 400 == 0))
      {
        days = (dat <= 29);
      }
    }
    else if (monthWorks()){
      days = dat <= 31;
    }
    return !checkString(date) && (dat > 0 && days);
  }

  private boolean yearWorks(){
    return (year >= 1000 && year <= 9999);
  }

  public String toString (){
    return month + "/" + date + "/" + year;
  }
}