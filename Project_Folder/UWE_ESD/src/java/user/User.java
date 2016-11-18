package user;

/**
 *
 * @author Richard
 */
public class User {
    private String ID;
    private String password;
    private String firstName;
    private String lastName;
    private String userValid;
    private String address;
    private String DOB;
    private String DOR;
    private double balance;
      
    public User(){}
    public User(String ID,String name,String userValid,
                    String address,String DOB,String DOR,double balance){
        this.ID =  ID;
        String[] split = name.split(" ");
        this.firstName = split[0];
        this.lastName = split[1];
        this.userValid = userValid;
        this.address = address;
        this.DOB = DOB;
        this.DOR = DOR;
        this.balance = balance;
    }
	
      public String getAddress(){
          return address;
      }
      
      public void setAddress(String newAddress){
          address = newAddress;
      }
      
      public String getDOB(){
          return DOB;
      }
      
      public void setDOB(String newDOB){
          DOB = newDOB;
      }
      
      public String getDOR(){
          return DOR;
      }
      
      public void setDOR(String newDOR){
          DOR = newDOR;
      }
      
      public String getFirstName() {
         return firstName;
	}

      public void setFirstName(String newFirstName) {
         firstName = newFirstName;
	}

      public String getLastName() {
         return lastName;
			}

      public void setLastName(String newLastName) {
         lastName = newLastName;
			}
			
      public String getPassword() {
         return password;
	}

      public void setPassword(String newPassword) {
         password = newPassword;
	}
			
      public String getID() {
         return ID;
	}

      public void setID(String newID) {
         ID = newID;
			}
				
      public String isUserValid() {
         return userValid;
	}

      public void setUserValid(String newUserValid) {
         userValid = newUserValid;
	}
      
      public double getBalance(){
          return balance;
      }
      
      public void setBalance(double balance){
          this.balance = balance;
      }
}
