package cryptoTrader.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cryptoTrader.utils.Authentication;


/**
 * LoginUI.java
 * The class is used to display the login UI where the user will enter in an username and password
 * @author Subodh Neupane, Shehroz Bajwa, Thanish Premarajan and Isabella Lu
 */
public class LogInUI extends JFrame implements ActionListener {

	
	public static JPanel loginPanel = new JPanel();
	JLabel passwordLabel;
	JLabel userLabel, title;
	JTextField userTextField;
	JPasswordField passwordField;
	JButton loginButton;
	JLabel incorrect;
	String username, password;

	/*
	 * LoginUI contructor class which displays the text boxes for the user's username and password
	 */
	private LogInUI(){
		setContentPane(loginPanel);
		loginPanel.setLayout(null);


		title = new JLabel("Client Login");
		title.setFont(new Font("Serif", title.getFont().getStyle(), 50));
		title.setBounds(70,0,300,50);
		loginPanel.add(title);
		
		//Creates a text field for the user's username
		userLabel=new JLabel("Username:");
		title.setFont(new Font("Serif", title.getFont().getStyle(), 30));
		userLabel.setBounds(30,50,150,20);
		loginPanel.add(userLabel);

		//Creates a password field for the user's password
		passwordLabel=new JLabel("Password:");
		title.setFont(new Font("Serif", title.getFont().getStyle(), 30));
		passwordLabel.setBounds(30,75,100,20);
		loginPanel.add(passwordLabel);


		//Creates a text field for the user's username
		userTextField=new JTextField();
		userTextField.setBounds(100,50,150,20);
		loginPanel.add(userTextField);


		//Creates a password field for the user's password
		passwordField=new JPasswordField();
		passwordField.setBounds(100,75,150,20);
		loginPanel.add(passwordField);

		//Login Button that the user will press to log into the system
		loginButton=new JButton("Login");
		loginButton.setBounds(100,110,70,20);
		loginPanel.add(loginButton);

		setVisible(true);
		setContentPane(loginPanel);

		//Incorrect username message that is initially not displayed
		incorrect = new JLabel("Incorrect username and password.");
		incorrect.setFont(new Font("Serif", incorrect.getFont().getStyle(), 15));
		incorrect.setBounds(30,135,300,23);
		incorrect.setForeground(Color.RED);
		loginPanel.add(incorrect);
		incorrect.setVisible(false);




		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = userTextField.getText();
				password = String.valueOf(passwordField.getPassword());
				Authentication aut = new Authentication();
				//Uses the Authentication class to authenticate the username and password
				boolean bol = aut.checkAuthentication(username, password);
				//Displays the incorrect message if the authentication is false
				if (bol == false) {
					incorrect.setVisible(true);
				}
				//Valid authentication
				else if (bol == true) {
					JFrame frame = MainUI.getInstance();
					frame.setSize(900, 600);
					frame.pack();
					frame.setVisible(true);
					setVisible(false); 
					//Disposes the login UI and continues on to the Main UI
					dispose(); 
				}

			}
		});
	}

	/*
	 * Main method which displays the LoginUI
	 */
	public static void main(String[] args) {

		try {

			LogInUI frame = new LogInUI();
			frame.setVisible(true);
			frame.setSize(300, 200);
			frame.setResizable(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/*
	 * Triggers the function when any action is performed
	 */
	public void actionPerformed(ActionEvent e) {

	}


}
