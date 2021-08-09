package com.store;

import java.util.Scanner;

public class Login {

	public static boolean login() {
		System.out.println("Hi!");

		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("Please choose one of the Option:");

		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Enter Username:");
		String username = scan.nextLine();
		System.out.println("Enter Password");
		String password = scan.nextLine();

		switch (option) {
		case 1:
			if(User.verifyLogin(username, password)) {
				return true;
			}
			break;

		case 2:
			String userrole;
			boolean userRoleExists = false;
			do {
				System.out.println("Choose any Role: admin/user/manager : ");
				userrole = scan.nextLine();
				userRoleExists = User.checkIfRoleExists(userrole);
				if(userRoleExists) {
					System.out.println("Role already exists Please select a new Role ");
					userRoleExists = true;
				}else {
					userRoleExists = false;
				}
				
			}while(userRoleExists);
			System.out.println("User Created");
			return false;
		default:
			return false;
		}
		return false;

	}
}
