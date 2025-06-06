package com.quizapplication;

import java.util.Scanner;

public class QuizMain {

	public static void startQuiz() {
		System.out.println("======================-----------Welcome to java quiz system-----------======================\n");
		System.out.println("Who you are?");
		System.out.println("1. Strudent");
		System.out.println("2. Admin");
		System.out.println("3. Exit");

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("Enter your choice..");
			int option= sc.nextInt();
			switch (option) {
			case 1:
				Student.studentStart();
				break;
			case 2:
				Admin.adminStart();
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Wrong choice, enter correct option...");
			}
		}
		
	}
	public static void main(String[] args) {
		
		QuizMain.startQuiz();
		
	}

}
