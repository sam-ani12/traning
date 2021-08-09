package com.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BillTest {
	public static void main(String[] args) {
		try {
			FileWriter myWriter = new FileWriter("invoice.txt");
			Scanner sc = new Scanner(System.in);

			System.out.println("Hi There, Invoice");
			System.out.println("");
			System.out.println("Please Select Invoice y/n");

			String invoice = sc.nextLine();

			System.out.println();
			if (invoice.equalsIgnoreCase("y")) {

				System.out.println("Please enter Quantity");
				String quantity = sc.nextLine();
				System.out.println("");
				System.out.println("Please enter Price");
				String price = sc.nextLine();
				System.out.println("");
				System.out.println("Please enter GST");
				String gst = sc.nextLine();

				float bills = calculateBill(Float.valueOf(quantity), Float.valueOf(price));
				float calculategst = calculateBill(Float.valueOf(quantity), Float.valueOf(price), Integer.valueOf(gst));

				myWriter.write("Quantity:"+Float.valueOf(quantity)+"price"+Float.valueOf(price)+"GST:"+Integer.valueOf(gst)+"Bill Amount:" + bills + "GST Calculation:" + calculategst);
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} else {
				System.err.println("file not saved");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	static float calculateBill(float quantity, float price) {
		float bill = quantity * price;
		return bill;
	}

	static float calculateBill(float quantity, float price, int gst) {
		float amount = calculateBill(quantity, price);
		return amount += (amount * gst / 100);
	}

	static float calculateGstBill(float amount, int gst) {
		//float amount = calculateBill(quantity, price);
		return amount += (amount * gst / 100);
	}

	
}
