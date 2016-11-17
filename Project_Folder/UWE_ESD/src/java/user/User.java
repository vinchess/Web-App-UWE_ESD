/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
      public boolean userValid;
      private String address;
      private int DOB;
      private int DOR;
      private double balance;
      
	
	
      public String getAddress(){
          return address;
      }
      
      public void setAddress(String newAddress){
          address = newAddress;
      }
      
      public int getDOB(){
          return DOB;
      }
      
      public void setDOB(int newDOB){
          DOB = newDOB;
      }
      
      public int getDOR(){
          return DOR;
      }
      
      public void setDOR(int newDOR){
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
				
      public boolean isUserValid() {
         return userValid;
	}

      public void setUserValid(boolean newUserValid) {
         userValid = newUserValid;
	}
      
      public double getBalance(){
          return balance;
      }
      
      public void setBalance(double balance){
          this.balance = balance;
      }
}
