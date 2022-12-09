package oop;

import java.util.Scanner;
import java.util.Vector;

public class OOP {

	static Scanner sc = new Scanner(System.in);
	static Vector<Karyawan> karyawanData = new Vector<>();
	static Vector<Customer> customerData = new Vector<>();
	static Vector<Item> itemData = new Vector<>();
	
	String employeeName, customerName, memberType, itemName;
	Integer employeeCode, employeeSalary, soldItem, jumlahBawahan, superiorCode, customerCode, moneySpent, itemQuantity, itemPrice;
	
	public static int menuList() {
		Integer menu = 0;
		do {
		System.out.println("\nSuper Cuan Oke");
		System.out.println("================");
		System.out.println("1. Add Item");
		System.out.println("2. View Item");
		System.out.println("3. Buy Item");
		System.out.println("4. View Employee Data");
		System.out.println("5. View Customer Data");
		System.out.println("6. Exit");
		System.out.print(">> ");
		try {
			menu = sc.nextInt();
		}catch(Exception E){
			System.out.println("Input must be a number!");
		}finally {
			sc.nextLine();
		}
		}while(menu < 1 || menu > 6);
		return menu;
	}
	
	public void addItem() {
		System.out.print("Input Item Name: ");
		itemName = sc.nextLine();
		
		System.out.print("Input Item Quantity: ");
		try {
			itemQuantity = sc.nextInt();
		}catch(Exception E){
			System.out.print("Input must be a number!");
		}finally {
			sc.nextLine();
		}
		
		System.out.print("Input Item Price: ");
		try {
			itemPrice = sc.nextInt();
		}catch(Exception E){
			System.out.print("Input must be a number!");
		}finally {
			sc.nextLine();
		}
		System.out.println("Add Item Success!");
		
		Item item1 = new Item(itemQuantity, itemName, itemPrice);
		itemData.add(item1);
	}
	
	public void viewItem() {
		if(itemData.size() < 1) {
			System.out.println("\nItem List Is Empty!\n");
		}else {
			for (int i = 0; i < itemData.size(); i++) {
				System.out.println("\nNo              : " + (i+1));
				System.out.println("======================");
				System.out.println("1. Item Name   		: " + itemData.get(i).getItemName());
				System.out.println("2. Item Quantity    : " + itemData.get(i).getItemQuantity());
				System.out.println("3. Item Price      	: " + itemData.get(i).getItemPrice());
				System.out.println();
			}
		}
	}
	
	public void buyItem() {
		Integer index = 0;
		viewItem();
		System.out.print("Input index item to purchase: ");
		try {
			index = sc.nextInt();
		}catch(Exception E) {
			System.out.println("Input must be a number!");
		}finally {
			sc.nextLine();
		}
		if(itemQuantity < 1){
			itemData.remove(index - 1);
		}else {
//			itemData.itemQuantity - 1;
		}
		System.out.println("Purchase Item Success!");
	}
	
	public void employee() {
		if(karyawanData.size() < 1) {
			System.out.print("\nEmployee Data Is Empty!\n");
			
			System.out.println("Input Employee Code");
			try {
				employeeCode = sc.nextInt();
			}catch(Exception E) {
				System.out.println("Input must be a number!");
			}finally {
				sc.nextLine();
			}
			
			System.out.print("Input Employee Name: ");
			employeeName = sc.nextLine();
			
			System.out.print("Input Employee Base Salary: ");
			try {
				employeeSalary = sc.nextInt();
			}catch(Exception E) {
				System.out.println("Input must be a number!");
			}finally {
				sc.nextLine();
			}
			
			System.out.print("Input Sold Item: ");
			try {
				soldItem = sc.nextInt();
			}catch(Exception E) {
				System.out.println("Input must be a number!");
			}finally {
				sc.nextLine();
			}
			
			System.out.print("Input Jumlah Bawahan [0 = bukan atasan]:");
			try {
				jumlahBawahan = sc.nextInt();
			}catch(Exception E) {
				System.out.println("Input must be a number!");
			}finally {
				sc.nextLine();
			}
			if(jumlahBawahan > 0) {
				Karyawan employee1 = new Atasan(employeeCode, employeeName, employeeSalary, soldItem, jumlahBawahan);
				karyawanData.add(employee1);
			}else {
				Karyawan employee1 = new Bawahan(employeeCode, employeeName, employeeSalary, soldItem, superiorCode);
				karyawanData.add(employee1);
			}
			
		}else {
			for (int i = 0; i < karyawanData.size(); i++) {
				System.out.println("\nNo              : " + (i+1));
				System.out.println("======================");
				System.out.println("1. Code   		: " + karyawanData.get(i).getEmployeeCode());
				System.out.println("2. Name		    : " + karyawanData.get(i).getEmployeeName());
				System.out.println("3. Base Salary  : " + karyawanData.get(i).getEmployeeSalary());
				System.out.println("4. Sold item  	: " + karyawanData.get(i).getSoldItem());
				System.out.println();
			}
		}
	}
	
	public void customer() {
		if(customerData.size() < 1) {
			System.out.println("\nCustomer Data Is Empty!\n");
			
			System.out.print("Input Customer Code: ");
			try {
				customerCode = sc.nextInt();
			}catch(Exception E) {
				System.out.println("Input must be a number!");
			}finally {
				sc.nextLine();
			}
			
			System.out.print("Input Customer Name: ");
			customerName = sc.nextLine();
			
			System.out.println("Input Money Spent: ");
			try {
				moneySpent = sc.nextInt();
			}catch(Exception E) {
				System.out.print("Input must be a number!");
			}finally {
				sc.nextLine();
			}
			
			if(moneySpent == 0) {
				memberType = "Bronze";
			}else if(moneySpent >= 1000000) {
				memberType = "Silver";
				itemPrice = (int) (itemPrice * 0.10);
			}else if(moneySpent >= 5000000) {
				memberType = "Gold";
				itemPrice = (int) (itemPrice * 0.20);
			}
			
			Customer pelanggan1 = new Customer(customerCode, customerName, moneySpent, memberType);
			
		}else {
			for (int i = 0; i < itemData.size(); i++) {
				System.out.println("\nNo              : " + (i+1));
				System.out.println("======================");
				System.out.println("1. Code   			: " + customerData.get(i).getCustomerCode());
				System.out.println("2. Name    			: " + customerData.get(i).getCustomerName());
				System.out.println("3. Money Spent     	: " + customerData.get(i).getMoneySpent());
				System.out.println("4. Member Type      : " + customerData.get(i).getMemberType());
				System.out.println();
			}
		}
	}

	public OOP() {
		do {
			switch(menuList()) {
			case 1:
				addItem();
				break;
				
			case 2:
				viewItem();
				break;
				
			case 3:
				buyItem();
				break;
				
			case 4:
				employee();
				break;
				
			case 5:
				customer();
				break;
				
			case 6:
				System.out.println("Thanks for using Just DU It Program !");
				System.exit(0);
				break;
			}
		}while(true);
	}

	public static void main(String[] args) {
		new OOP();
	}

}
