package cryptoTrader.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Authentication.java
 * The class is used to authenticate the users's username and password
 * @author Subodh Neupane, Shehroz Bajwa, Thanish Premarajan and Isabella Lu
 */
public class Authentication{
	
	public Authentication() {
		
	}
	
	/*
	 * Authenticates the user's username and password. Returns true if valid username/password and false otherwise
	 * @param username - user's username
	 * @param password - user's password
	 * @return returns true if username/password is valid, false otherwise
	 */
	
	public boolean checkAuthentication(String username, String password) {
		boolean authenticate = false;
		
		try {
			// Reads the text file that stores the user's login data
			BufferedReader bf = new BufferedReader(new FileReader("src/main/java/cryptoTrader/loginDB.txt"));
			//First line is the username
			String name = bf.readLine();
			String pass;
			while(name!=null){
				if(name.equals(username)) {
					//If the inputted username matches the username in the database, the password is checked
					pass = bf.readLine();
					if(pass.equals(password)) {
						authenticate = true;
					}
				}
				//If the username is not matched, it goes to the next username in the database
				name = bf.readLine();
				name = bf.readLine();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Returns whether it was valid or invalid
		return authenticate;
	}
}

