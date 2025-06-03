package com.quizapplication;

import java.util.Scanner;

public class Admin {
	
	
	public static void adminStart() {
		Scanner sc =new Scanner(System.in);
		if(adminVerify()) {
			System.out.println("Welcoe admin");
			System.out.println("1. Display all student score");
			System.out.println("2. Fetch student score");
			System.out.println("3. Add question");
			System.out.println("4. Main mainu");
			System.out.println("5. Exit");

			while(true) {
				System.out.println("Enter your choice admin..");
				int option=sc.nextInt();
				switch(option) {
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
			
			
		}else {
			System.out.println("Enter correct admin password");
		}
	}
	
	public static boolean adminVerify() {
		
		
		
		return false;
	}
	public static void displayAllStudent() {
		
		
		
	}
	public static void fetchStudent() {
		
		
		
	}
	public static void addQuestion() {
		
		
		
		
	}

}
