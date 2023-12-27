package com.example.account;

import com.example.DataConn;
import com.example.DataConnImpl;

import java.sql.SQLException;

public class SavingAccount implements Accounts {
    private String a1 ="Saving Account";
    private long balance=500;

    @Override
    public void addAccount(AccountDetails ad){
        DataConn dc=new DataConnImpl();
        dc.addSavingAccount(new AccountDetails(ad.getCustomerId(),ad.getAccountNo(),a1, balance));

    }
}
