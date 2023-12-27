package com.example;

import com.example.account.AccountDetails;
import com.example.customer.CustomerDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataConnImpl implements DataConn {
    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "vasudeva");
        if (conn != null) {
            return conn;
        } else {
            throw new RuntimeException("Connection error");
        }
    }

 public void addCustomer(CustomerDetails cd){
        try{
            String mysql="INSERT INTO CUSTOMERDETAILS(customerId,firstName,lastName,email,city,phoneNumber)values(?,?,?,?,?,?)";
            PreparedStatement ps=getConnection().prepareStatement(mysql);
            ps.setLong(1,cd.getCustomer());
            ps.setString(2,cd.getFirstName());
            ps.setString(3,cd.getLastName());
            ps.setString(4,cd.getEmail());
            ps.setString(5,cd.getCity());
            ps.setLong(6,cd.getPhoneNumber());
            ps.execute();
            String getCID="Select customerId from CustomerDetails WHERE phoneNumber=?";
            PreparedStatement ps1=getConnection().prepareStatement(getCID);
            ps1.setLong(1,cd.getPhoneNumber());
            ResultSet rs=ps1.executeQuery();
            if(rs.next())
                System.out.println("Successfully created new customer  and CUSTOMER ID : "+rs.getLong("customerId"));
        } catch(Exception e) {
            System.out.println("ERROR AT CUID : "+ e.getMessage());
        }

    }

    @Override
    public void addSavingAccount(AccountDetails ad)  {
        try {
            String getCID = "Select customerId from CustomerDetails";
            Statement sm = getConnection().createStatement();
            ResultSet rs = sm.executeQuery(getCID);
            boolean f = false;
            while (rs.next()) {
                if (rs.getLong("customerId") == ad.getCustomerId()) {
                    f = true;
                }
            }
            if (f) {
                String up="UPDATE customerdetails set IS_STATUS=1 where customerId=?";
                PreparedStatement ps1=getConnection().prepareStatement(up);
                ps1.setLong(1,ad.getCustomerId());
                ps1.execute();
                String mysql="INSERT INTO SAVINGACCOUNTS(customerId,accountNo,type,balance)values(?,?,?,?)";
                PreparedStatement ps=getConnection().prepareStatement(mysql);
                ps.setLong(1,ad.getCustomerId());
                ps.setLong(2,ad.getAccountNo());
                ps.setString(3,ad.getType());
                ps.setLong(4,ad.getBalance());
                ps.execute();
                System.out.println("Succesfully Add Saving Account");
            } else {
                System.out.println("Customer  details are not found! Please add Customer First");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addCurrentAccount(AccountDetails ad) {
        try {
            String getCID = "Select customerId from CustomerDetails";
            Statement sm = getConnection().createStatement();
            ResultSet rs = sm.executeQuery(getCID);
            boolean f = false;
            while (rs.next()) {
                if (rs.getLong("customerId") == ad.getCustomerId()) {
                    f = true;

                }
            }
            if (f) {
                String up="UPDATE customerdetails set IS_STATUS=1 where customerId=?";
                PreparedStatement ps1=getConnection().prepareStatement(up);
                ps1.setLong(1,ad.getCustomerId());
                ps1.execute();
                String mysql="INSERT INTO currentaccounts(customerId,accountNo,type,balance)values(?,?,?,?)";
                PreparedStatement ps=getConnection().prepareStatement(mysql);
                ps.setLong(1,ad.getCustomerId());
                ps.setLong(2,ad.getAccountNo());
                ps.setString(3,ad.getType());
                ps.setLong(4,ad.getBalance());
                ps.execute();
                System.out.println("Succesfully Add Current Account");
            } else {
                System.out.println("Customer  details are not found! Please add Customer First");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Object> showAllDetails() {
        List<Object> t=new ArrayList<>();
        try{
            String getCID = "Select customerId from CustomerDetails";
            Statement sm1 = getConnection().createStatement();
            ResultSet rs1 = sm1.executeQuery(getCID);
            boolean f = false;
            while (rs1.next()) {
                f= true;
            }
        if (f) {
                String mysql = "SELECT cd.customerId,CONCAT( cd.firstName,' ',cd.lastName) as 'Full_Name', cd.email, cd.city,cd.phoneNumber,(sa.accountNo) as 'Account Number',sa.type,(sa.balance) as'Account_Balance'FROM customerdetails cd join savingaccounts sa on sa.customerId=cd.customerId union all SELECT cd.customerId,CONCAT( cd.firstName,' ',cd.lastName) as 'Full_Name', cd.email, cd.city,cd.phoneNumber,(ca.accountNo) as 'Account Number',ca.type,(ca.balance) as'Current_Account_Balance'  FROM customerdetails cd join currentaccounts ca on cd.customerId=ca.customerId order by customerId";
                Statement sm = getConnection().createStatement();
                ResultSet rs = sm.executeQuery(mysql);
                while (rs.next()) {
                    t.add("------------------Customer Details-----------------------");
                    t.add("*********************************************************");
                    t.add("CustomerId      :" + rs.getLong("customerId"));
                    t.add("Full_Name       :" + rs.getString("Full_Name"));
                    t.add("Email           :" + rs.getString("email"));
                    t.add("City            :" + rs.getString("city"));
                    t.add("Phone Number    :" + rs.getLong("PhoneNumber"));
                    t.add("Account Number  :" + rs.getLong("Account Number"));
                    t.add("Account Type    :" + rs.getString("Type"));
                    t.add("Account Balance :" + rs.getLong("Account_Balance"));
                    t.add("*********************************************************");
                }

            }
        else {
            System.out.println("No Customer is added! Please add Customer First");
        }
        }
        catch (Throwable e){
            System.out.println("Error at showAllDetails :"+e.getMessage());
        }
        return t;
    }

    @Override
    public List<Object> searchDetails(long cid) {
        List<Object> t =new ArrayList<>();
        try {
            String getCID = "Select customerId from CustomerDetails";
            Statement sm1 = getConnection().createStatement();
            ResultSet rs1 = sm1.executeQuery(getCID);
            boolean f = false;
            while (rs1.next()) {
                if (rs1.getLong("customerId") == cid) {
                    f = true;
                }
            }
            if (f) {
                String mysql = "SELECT cd.customerId,CONCAT( cd.firstName,' ',cd.lastName) as 'Full_Name', cd.email, cd.city,cd.phoneNumber,(sa.accountNo) as 'Account Number',sa.type,(sa.balance) as'Account_Balance'FROM customerdetails cd join savingaccounts sa on sa.customerId=cd.customerId where cd.customerId=? union all SELECT cd.customerId,CONCAT( cd.firstName,' ',cd.lastName) as 'Full_Name', cd.email, cd.city,cd.phoneNumber,(ca.accountNo) as 'Account Number',ca.type,(ca.balance) as'Current_Account_Balance'  FROM customerdetails cd join currentaccounts ca on cd.customerId=ca.customerId where cd.customerId=? union all select customerId,CONCAT(firstName,' ',lastName) as 'Full_Name',email,city,phoneNumber,null,null,null from customerdetails where customerId=? AND IS_STATUS=0 order  by customerId ";
                PreparedStatement ps=getConnection().prepareStatement(mysql);
                ps.setLong(1,cid);
                ps.setLong(2,cid);
                ps.setLong(3,cid);
                ResultSet rs =ps.executeQuery();
                while (rs.next()) {
                    t.add("------------------Customer of +" + cid + "-----------------------");
                    t.add("*********************************************************");
                    t.add("CustomerId      :" + rs.getLong("customerId"));
                    t.add("Full_Name       :" + rs.getString("Full_Name"));
                    t.add("Email           :" + rs.getString("email"));
                    t.add("City            :" + rs.getString("city"));
                    t.add("Phone Number    :" + rs.getLong("PhoneNumber"));
                    t.add("Account Number  :" + rs.getLong("Account Number"));
                    t.add("Account Type    :" + rs.getString("Type"));
                    t.add("Account Balance :" + rs.getLong("Account_Balance"));
                    t.add("*********************************************************");
                }
                return t;
            } else {
                System.out.println("No Such Customer is added! Please add Customer First");
            }
        }
        catch (Exception e){
            System.out.println("Error at searchDetails :" + e.getMessage());
        }
        return t;
    }

    @Override
    public void deleteDetails(long cid) {
        try {
            String getCID = "Select customerId from CustomerDetails";
            Statement sm1 = getConnection().createStatement();
            ResultSet rs1 = sm1.executeQuery(getCID);
            boolean f = false;
            while (rs1.next())
                f = true;
            if (f) {
                String mysql1 = "update customerdetails set is_status=0 where customerid=?";
                String mysql2 = "DELETE from  savingaccounts where customerid=?";
                String mysql3 = "DELETE from  currentaccounts where customerid=?";
                PreparedStatement ps1=getConnection().prepareStatement(mysql1);
                PreparedStatement ps2=getConnection().prepareStatement(mysql2);
                PreparedStatement ps3=getConnection().prepareStatement(mysql3);
                ps1.setLong(1,cid);
                ps2.setLong(1,cid);
                ps3.setLong(1,cid);
                ps1.execute();
                ps2.execute();
                ps3.execute();
                System.out.println("Successfully Deleted accounts of "+cid);
            }
            else {
                System.out.println("No Customer is added to detele! ");
            }
        }
        catch (Throwable t){
            System.out.println( "Error at deleteDetails :="+t.getMessage());
        }



    }

}
