package com.quizapplication;

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
		
		
		
	}
	
		
	public static void studentLogin() {
		
		
		
		
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
				Student.startQuiz();
				break;
			case 2:
				Student.displayResult();
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
	public static void startQuiz() {
		
		
		
	}
	
	public static void displayResult() {
		
		
	}
	 

}
