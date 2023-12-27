package com.example;

import com.example.account.AccountDetails;
import com.example.account.Accounts;
import com.example.account.CurrentAccount;
import com.example.account.SavingAccount;
import com.example.customer.CustomerDetails;


import javax.lang.model.type.NullType;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    Scanner sc=new Scanner(System.in);
    DataConn dc=new DataConnImpl();
    public void drive(){

            while (true){
                showMenu();

                try{
                    int n =Integer.parseInt(sc.nextLine());
                    switch (n){
                        case 1:{
                            this.newCustomer();
                            break;
                        }
                        case 2:{
                            this.addAccount();
                            break;
                        }
                        case 3:{
                            this.showAList();
                            break;
                        }
                        case 4:{
                            this.search();
                            break;
                        }
                        case 5:{
                            this.delete();
                            break;
                        }
                        case 6:{
                            System.out.println("Exited");
                            return;
                        }
                        default:{
                            System.out.println("Invalid Option");
                        }
                    }
                }
                catch (Exception e){
                    System.out.println(" Error at Menu :" +e.getMessage());
                }
            }
    }

    private void showMenu() {
        System.out.println("*********************************************************");
        System.out.println("-------------------------MENU----------------------------");
        System.out.println("*********************************************************");
        System.out.println("1. Create NewCustomer ");
        System.out.println("2. Add Account");
        System.out.println("3. ShowList");
        System.out.println("4. Search");
        System.out.println("5. Delete");
        System.out.println("6. To Exit");
        System.out.println("*********************************************************");
    }

    private void delete() {
        try {
            System.out.println("*********************************************************");
            System.out.println("Enter CUSTOMER ID to detele ");
            long cid = Long.parseLong(sc.nextLine());
            dc.deleteDetails(cid);
        }catch (Exception e){
            System.out.println("Error at search :"+e.getMessage());
        }
    }

    private void search() {
        try {
            System.out.println("*********************************************************");
            System.out.println("Enter CUSTOMER ID ");
            long cid = Long.parseLong(sc.nextLine());
            List<Object> s=dc.searchDetails(cid);
            while (s!=null) {
                s.forEach(System.out::println);
                s=null;
            }
        }catch (Exception e){
            System.out.println("Error at search :"+e.getMessage());
        }

    }

    private void showAList() {
        try {
            System.out.println("------------------Showing all Details--------------------");
            System.out.println("*********************************************************");
            List<Object> d= dc.showAllDetails();
            while (d!=null) {
                d.forEach(System.out::println);
                d=null;
            }
        }
        catch (Exception e){
            System.out.println("Error at showAList : " +e.getMessage());
        }

    }

    private void addAccount() {
        try {
            System.out.println("*********************************************************");
            System.out.println("Enter CUSTOMER ID ");
            long cid=Long.parseLong(sc.nextLine());
            System.out.println("Enter Account Type  : 1. Saving Account 2. Current Account" );
            int s= Integer.parseInt(sc.nextLine());
            if(s==1){
                Accounts a=new SavingAccount();
                a.addAccount(new AccountDetails(cid));
            }
            if (s==2){
                Accounts a=new CurrentAccount();
                a.addAccount(new AccountDetails(cid));
            }
        }catch (Exception E){
            System.out.println("Error at addAccount: "+ E.getMessage());
        }
    }

    private void newCustomer() {
        try {
            System.out.println("*********************************************************");
            System.out.println("------------------ENTER THE CUSTOMER DETAILS-------------");
            System.out.println("*********************************************************");
            System.out.println("Enter First Name of the Customer");
            String fn=sc.nextLine();
            System.out.println("Enter Last Name of the Customer");
            String ln=sc.nextLine();
            System.out.println("Enter City of the Customer");
            String city=sc.nextLine();
            while (true) {
                System.out.println("Enter Email of the Customer");
                String email=sc.nextLine();
                if (Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()) {
                    while (true) {
                        System.out.println("Enter Phone Number of the Customer");
                        long ph = Long.parseLong(sc.nextLine());
                        if ((int) (Math.log10(ph) + 1) == 10) {
                            dc.addCustomer(new CustomerDetails(fn, ln, email, city, ph));
                            return;
                        } else {
                            System.out.println("Enter Valid Phone number");
                        }
                    }
                } else {
                    System.out.println("Enter Valid Email");
                }
            }
        }
        catch (RuntimeException e){
            System.out.println("Error at new customer :"+ e.getMessage());
        }
    }

}
