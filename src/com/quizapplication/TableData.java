package com.quizapplication;

import java.sql.Connection;
import java.sql.Statement;

public class TableData {
	
	public static void createTable(Connection con) {
		
		String studentTable="CREATE TABLE student( username varchar(10) PRIMARY KEY, "
				+ "fname varchar(10), "
				+ "lname varchar(10), "
				+ "password varchar(10), "
				+ "city varchar(20), "
				+ "mailid varchar(20) UNIQUE KEY, "
				+ "mobno varchar(10) UNIQUE KEY)";
		
		String quizTable="CREATE TABLE quiz(id INT PRIMARY KEY AUTO_INCREMENT, "
				+ "question VARCHAR(70), "
				+ "OPT1 VARCHAR(40), "
				+ "OPT2 VARCHAR(40), "
				+ "OPT3 VARCHAR(40), "
				+ "OPT4 VARCHAR(40), "
				+ "ANS  INT )";
		
		String resultTable="CREATE TABLE result(id INT PRIMARY KEY AUTO_INCREMENT, "
				+ "username VARCHAR(10), "
				+ "score INT, "
				+ "defaultDate DATE DEFAULT (CURRENT_DATE),"
				+ "FOREIGN KEY (username) REFERENCES student(username) )";
				
		try(Statement st= con.createStatement()){
			st.execute(studentTable);
			st.execute(quizTable);
			st.execute(resultTable);
			System.out.println("Tables are successfully created in database");
		}catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public static void insertData(Connection con) {
		String quizData = "INSERT INTO quiz (question, OPT1, OPT2, OPT3, OPT4, ANS) VALUES\n"
		        + "('Which keyword is used to define a class in Java?', '1. define', '2. class', '3. Class', '4. new', 2),\n" 
		        + "('What is the size of an int data type in Java?', '1. 8 bits', '2. 16 bits', '3. 32 bits', '4. 64 bits', 3),\n"
		        + "('Which method is the entry point of a Java program?', '1. main()', '2. start()', '3. run()', '4. init()', 1),\n"
		        + "('What is the default value of a boolean variable in Java?', '1. true', '2. false', '3. 0', '4. null', 2),\n"
		        + "('Which of the following is not a Java keyword?', '1. static', '2. Boolean', '3. void', '4. private', 2),\n"
		        + "('What is the result of 5 + 3 * 2 in Java?', '1. 11', '2. 16', '3. 13', '4. 10', 3),\n"
		        + "('Which operator is used for comparison in Java?', '1. =', '2. ==', '3. :=', '4. equals', 2),\n"
		        + "('What will System.out.println(\"Hello\" + 5 + 3); output?', '1. Hello53', '2. Hello8', '3. 8Hello', '4. Compilation error', 1),\n"
		        + "('Which of the following is a valid declaration of an array in Java?', '1. int arr[] = new int(5);', '2. int arr[] = new int[5];', '3. arr{5} = int[];', '4. int arr()=new int[5];', 2),\n"
		        + "('Which exception is thrown when a program divides by zero?', '1. NullPointerException', '2. ArithmeticException', '3. NumberFormatException', '4. ArrayIndexOutOfBoundsException', 2);";
		
		try(Statement st= con.createStatement()){
			st.execute(quizData);
			System.out.println("Quiz question inserted successfully..");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void loadData() {
		Connection con=null;
		try{
			con=DbConnector.makeConnection();
			if(con!=null) {
				try(Statement st= con.createStatement()){
					createTable(con);
					insertData(con);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("Database connection failed while loading data...");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbConnector.closeConnection(con);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableData.loadData();

	}

}
