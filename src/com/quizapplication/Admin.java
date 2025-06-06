package com.quizapplication;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Admin {

	public static boolean adminVerify() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter admin usernmae");
		String username = sc.nextLine();
		System.out.println("Enter admin password");
		String password = sc.nextLine();

		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream("src/db.properties")) {
			prop.load(fis);
			String aduname = prop.getProperty("db.admin");
			String adpswd = prop.getProperty("db.adminpassword");

			if (username.equals(aduname) && password.equals(adpswd)) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static void adminStart() {
		Scanner sc = new Scanner(System.in);
		if (adminVerify()) {
			System.out.println("Welcoe admin");
			System.out.println("1. Display all student score");
			System.out.println("2. Fetch student score");
			System.out.println("3. Add question");
			System.out.println("4. Main mainu");
			System.out.println("5. Exit");

			while (true) {
				System.out.println("Enter your choice admin..");
				int option = sc.nextInt();
				switch (option) {
				case 1:
					Admin.displayAllStudent();
					break;
				case 2:
					Admin.fetchStudent();
					break;
				case 3:
					Admin.addQuestion();
					break;
				case 4:
					QuizMain.startQuiz();
					break;
				case 5:
					System.exit(0);
					break;
				default:
					System.out.println("Enter correct choice..");
				}
			}

		} else {
			System.out.println("Enter correct admin username and password");
		}
	}

	public static void displayAllStudent() {

		String displayScore = "select r.username, s.fname,s.lname, r.score, r.defaultDate from result r "
				+ "join student s  on r.username=s.username order by score asc";
		Connection con = null;
		try {
			con = DbConnector.makeConnection();
			if (con != null) {
				System.out.println("Here is list of students with marksa");
				try (Statement st = con.createStatement()) {
					ResultSet rs = st.executeQuery(displayScore);
					while (rs.next()) {
						String username = rs.getString("username");
						String fname = rs.getString("fname");
						String lname = rs.getString("lname");
						int score = rs.getInt("score");
						String defaultDate = rs.getString("defaultDate");
						System.out.println(username + " " + fname + " " + lname + " " + score + " " + defaultDate);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Database connection failed..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnector.closeConnection(con);
		}

	}

	public static void fetchStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student user id");
		String studentid = sc.nextLine();
		String fetchStudent = "select r.username, s.fname,s.lname, r.score, r.defaultDate "
				+ "from result r join student s  on r.username=s.username where r.username='" + studentid + "'";
		Connection con = null;
		try {
			con = DbConnector.makeConnection();
			if (con != null) {
				System.out.println("Here is list of students with marks");
				try (Statement st = con.createStatement()) {
					ResultSet rs = st.executeQuery(fetchStudent);
					while (rs.next()) {
						String username = rs.getString("username");
						String fname = rs.getString("fname");
						String lname = rs.getString("lname");
						int score = rs.getInt("score");
						String defaultDate = rs.getString("defaultDate");
						System.out.println(username + " " + fname + " " + lname + " " + score + " " + defaultDate);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Database connection failed..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnector.closeConnection(con);
		}

	}

	public static void addQuestion() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a question");
		String question = sc.nextLine();
		System.out.println("Enter a 1st option");
		String opt1 = sc.nextLine();
		opt1="1. "+opt1;
		System.out.println("Enter a 2nd option");
		String opt2 = sc.nextLine();
		opt2="2. "+opt2;
		System.out.println("Enter a 3rd option");
		String opt3 = sc.nextLine();
		opt3="3. "+opt3;
		System.out.println("Enter a 4th option");
		String opt4 = sc.nextLine();
		opt4="4. "+opt4;
		System.out.println("Enter a answer of question");
		int answer = sc.nextInt();
		
		
		String addquestion = "INSERT INTO quiz(question,OPT1,OPT2,OPT3,OPT4,ANS)Values(?,?,?,?,?,?) ";
		
		Connection con = null;
		try {
			con = DbConnector.makeConnection();
			if (con != null) {
				System.out.println("Here is list of students with marks");
				try (PreparedStatement ps = con.prepareStatement(addquestion)) {
					ps.setString(1, question);
					ps.setString(2, opt1);
					ps.setString(3, opt2);
					ps.setString(4, opt3);
					ps.setString(5, opt4);
					ps.setInt(6, answer);
					ps.executeUpdate();
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Database connection failed..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnector.closeConnection(con);
		}

	}

}
