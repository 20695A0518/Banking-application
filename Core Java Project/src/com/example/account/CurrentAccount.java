package com.example.account;

import com.example.DataConn;
import com.example.DataConnImpl;

public class CurrentAccount implements Accounts{
    private String a1 ="Current Account";
    private long balance=1000;
    @Override
    public void addAccount(AccountDetails ad) {
        DataConn dc=new DataConnImpl();
        dc.addCurrentAccount(new AccountDetails(ad.getCustomerId(),ad.getAccountNo(),a1,balance));
    }
}
