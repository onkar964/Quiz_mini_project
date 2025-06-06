package com.quizapplication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;



public class Student {

	public static void studentStart() {
		System.out.println("Welcome student for quiz for that login or register");
		System.out.println("1. Student Registration");
		System.out.println("2. Student Login");
		System.out.println("3. Enter main menu");
		System.out.println("4. Exit");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Hey student enter your choice");
			int option = sc.nextInt();
			switch(option) {
				case 1:
					Student.registerStudent();
					break;
				case 2:
					Student.studentLogin();
					break;
				case 3:
					QuizMain.startQuiz();
					break;
				case 4:
					System.exit(0);
					break;
				case 5:
					System.out.println("Hey student enter correct choice");
			}
		}
	}
	
	public static void registerStudent() {
		Scanner sc =new Scanner(System.in);
		Connection con=null;
		System.out.println("Welcome to Student registration \n Enter Student details...");
		System.out.println("Enter the first name>>");
		String fname = sc.nextLine();
		System.out.println("Enter the last name>>");
		String lname = sc.nextLine();
		System.out.println("Enter the username>>");
		String username = sc.nextLine();
		System.out.println("Enter the password>>");
		String password = sc.nextLine();
		System.out.println("Enter the city>>");
		String city = sc.nextLine();
		System.out.println("Enter the mail id>>");
		String mailid = sc.nextLine();
		System.out.println("Enter the mobile number>>");
		String mobno = sc.nextLine();

		String insertUser = "INSERT INTO student (username, fname, lname, password, city, mailid, mobno) VALUES(?,?,?,?,?,?,?)";
		try {

			con = DbConnector.makeConnection();
			if (con != null) {

				try (PreparedStatement st = con.prepareStatement(insertUser)) {
					st.setString(1, username);
					st.setString(2, fname);
					st.setString(3, lname);
					st.setString(4, password);
					st.setString(5, city);
					st.setString(6, mailid);
					st.setString(7, mobno);
					st.executeUpdate();
					System.out.println("User registered successfully..");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnector.closeConnection(con);
		}
		
		
	}
	
		
	public static void studentLogin() {
		Scanner sc= new Scanner(System.in);
		Connection con=null;
		System.out.println("Welcome to Student Login");
		System.out.println("Enter username");
		String username=sc.nextLine();
		System.out.println("Enter password");
		String password= sc.nextLine();
		String uname="";
		String pswd="";
		String loginString="SELECT username, password FROM student WHERE username=? and password=?";
		
		try {
			con=DbConnector.makeConnection();
			if(con!=null) {
				try(PreparedStatement ps = con.prepareStatement(loginString)){
					ps.setString(1, username);
					ps.setString(2, password);
					ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						uname=rs.getString("username");
						pswd=rs.getString("password");
					}
					if(username.equals(uname)&&password.equals(pswd)) {
						Student.studentAfterLogin(uname);
					}else {
						System.out.println("Wrong usernae or password");
					}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}else {
				System.out.println("Database connection failed");
			}
			
		}catch (Exception e) {
			e.printStackTrace();			
		}finally {
			DbConnector.closeConnection(con);
		}
		
	}
	public static void studentAfterLogin(String username) {
		
		System.out.println("Welcome student "+username+" Enter your choice");
		System.out.println("1. Start Quiz");
		System.out.println("2. Display result");
		System.out.println("3. Main mainu");
		System.out.println("4. Exit");
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Enter your choice..");
			int option = sc.nextInt();
			switch(option){
			case 1:
				Student.startQuiz(username);
				break;
			case 2:
				Student.displayResult(username);
				break;
			case 3:
				QuizMain.startQuiz();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Enter correct choice");
			}
		}
		
	}
	public static void startQuiz(String username) {
		
		Scanner sc= new Scanner(System.in);
		Connection con=null;
		System.out.println("Welcome "+username+" lets start quiz");
		int score=0;
		String quiz="SELECT * FROM quiz ORDER BY RAND() LIMIT 10";
		String result="INSERT INTO result (username, score) values(?,?)";
		try {
			con=DbConnector.makeConnection();
			if(con!=null) {
				try(Statement st=con.createStatement()){
					ResultSet rs= st.executeQuery(quiz);
					while(rs.next()) {
						String question= rs.getString("question");
						String opt1= rs.getString("OPT1");
						String opt2= rs.getString("OPT2");
						String opt3= rs.getString("OPT3");
						String opt4= rs.getString("OPT4");
						int ans=rs.getInt("ANS");
						
						System.out.println(question);
						System.out.println(opt1);
						System.out.println(opt2);
						System.out.println(opt3);
						System.out.println(opt4);
						
						System.out.println("Enter your answer...");
						int answer=sc.nextInt();
						if(ans==answer) {
							score++;
						}
					}
					
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try(PreparedStatement ps= con.prepareStatement(result)){
					
					ps.setString(1, username);
					ps.setInt(2, score);
					ps.executeUpdate();
					System.out.println("Result updated to database");
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}else {
				System.out.println("Database connection failed");
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbConnector.closeConnection(con);
		}
		
	}
	
	public static void displayResult(String username) {
		
		Connection con= null;
		String display="SELECT * FROM result WHERE username=?";
		
		try {

			con = DbConnector.makeConnection();
			if (con != null) {

				try (PreparedStatement ps = con.prepareStatement(display)) {
					ps.setString(1, username);
					ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						int id=rs.getInt("id");
						String uname= rs.getString("username");
						int score = rs.getInt("score") ;
						Date date= rs.getDate("defaultDate");
						System.out.println(id+" "+uname+" "+score+" "+date);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnector.closeConnection(con);
		}
		
		
	}
	 

}
