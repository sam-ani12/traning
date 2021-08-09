package com.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Store{
	
	
    // jdbc Connection
    private static Connection connection = null;
    private static Statement stmt = null;

    private static void createConnection()
    {
        try
        {
       //     Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
        //    conn = DriverManager.getConnection(dbURL);
        	Class.forName("org.apache.derby.jdbc.ClientDriver");
			connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample;create=true","user","user");
			
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
    private static void insertInvoicedetails(String userid, String Product,float Quantity,float billamount)
    {
        try
        {
            stmt = connection.createStatement();
            String query ="INSERT INTO APP.INVOICEDETAILS values('"+userid+"','"+Product+"',"+Quantity+","+billamount+")";
            
//            String query ="INSERT INTO APP.INVOICEDETAILS values('+userid+','+Product+','+Quantity+','+billamount+')";
			/*
			 * String query = "INSERT INTO invoicedetails(" +
			 * "userid, product, Quantity,billamount) VALUES "
			 * +"("+userid+", "+Product+", "+Quantity+","+billamount+")";
			 */
            System.err.println(query);
                 stmt.executeUpdate(query);
                 System.out.println("Values inserted");
          //  stmt.execute("insert into userid,product,Quantity,billamount " + tableName + " values (" +
           // 		userid + ",'" + Product + "','" + Quantity +"','"+billamount+"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
	@SuppressWarnings("resource")
	public static void main(String[] args)  {
		
		//login and register
		
		boolean doLogin = true;
		do {
			doLogin = Login.login();
		}while(!doLogin);
		
		//Store
		
		String productTypes[] = { "Jeans", "shoes", "shirts" };
		String products[] = { "A", "B", "C" };
		float amount[] = { 100, 200, 300 };
		int quantity[] = { 1, 2, 3 };

		System.out.println("Hi There, Welcome to Clothing Store");
		System.out.println("please select clothing for M/W: ");
		Scanner sc = new Scanner(System.in);
		switch (sc.next().toLowerCase()) {
		case "m":
			System.out.println("******Displaying Men Clothing*******");
			break;
		case "w":
			System.out.println("******Displaying Women Clothing*****");
			break;
		default:
			System.err.println("Invalid input");
			System.exit(1);
			break;
		}
		for (int i = 0; i < productTypes.length; i++) {
			
			System.out.printf("%1$s %2$10s %3$s %4$" + (22 - productTypes[i].length()) + "s%n", "*", i + 1,
					productTypes[i], "*");
		}
		System.out.printf("%1$s %2$5s %3$4s%n", "*", "Please choose clothing type: ", "*");
		System.out.println("************************************");
		switch (sc.next()) {
		case "1":
		case "2":
		case "3":
			
			System.out.println("----------------------------");
			System.out.println("products  quantity  Amount ");
			System.out.println("----------------------------");
			for (int i = 0; i < products.length; i++) {
				System.out.printf("%1$4s %2$10s %3$10.2f%n", products[i], quantity[i], amount[i]);
			}
			System.out.println("----------------------------");
			System.out.println("select product:");
			String product = sc.next();
			System.out.println("select quantity:");
			int quant = sc.nextInt();
			int index = 0;
			switch (product.toLowerCase()) {
			case "a":
				index = 0;
				break;
			case "b":
				index = 1;
				break;
			case "c":
				index = 2;
				break;
			default:
				System.err.println("Invalid product");
				System.exit(3);
				break;
			}
			int originalQuant = quantity[index];
			if (quant > originalQuant) {
				System.err.println("qunatity is not valid");
				System.exit(3);
			} else {
				System.out.println("*****Invoice generated******");
				System.out.println("----------------------------");
				System.out.println("products  quantity  Bill ");
				System.out.println("----------------------------");
				BillTest bill = new BillTest();
				float originalBill = bill.calculateBill(quant, amount[index]);
				int gst = 0;
				if (originalBill > 100 && originalBill <= 200) {
					gst = 10;
				} else if (originalBill > 200 && originalBill <= 500) {
					gst = 20;
				} else if (originalBill > 500) {
					gst = 30;
				}
				float finalBill = bill.calculateBill(quant, amount[index], gst);
				
				System.out.printf("%1$4s %2$10s %3$10.2f%n", products[index], quant, originalBill);
				System.out.println("----------------------------");
				System.out.println("Total: " + finalBill + " (" + originalBill + "Rs + " + gst + "% GST i.e. "
						+ bill.calculateGstBill(originalBill,gst)+"Rs)");
				
			
			
		Scanner sd =new Scanner(System.in);
		System.out.println("Do you want to generate invoice(y/n):");
		
		
		switch(sd.next()){
		case "y":
			try{
			File f1 =new File("invoicegenerate.txt");
			FileWriter fw =new FileWriter(f1);
			fw.write("\nGenerated Invoice:\n ");
			fw.write("-------------------------\n");	
			fw.write("Products     quantity     Bill\n");
			fw.write("-------------------------\n");
			
			String str =  "     "+ products[index]+ "      " +  quant +  "       "  + originalBill ;
			fw.write(str);
			fw.write("\n");
			fw.write("-------------------------\n");
			fw.write("Total: " + finalBill + " (" + originalBill + "Rs + " + gst + "% GST i.e. "
					+ bill.calculateGstBill(originalBill, gst)+"Rs)");
			fw.close();
			
			createConnection();
			insertInvoicedetails("admin", product, originalQuant, finalBill);
			}catch(IOException io){
				
			}
			break;
		case "n":
			System.out.println("Invoice not generated");
			break;
			default:
				System.exit(0);
				break;
				
		}
			}
            default:
			System.exit(2);
			System.err.println("Invalid input");
			break;
			
		
		}
	     
		sc.close();
		
	}

}