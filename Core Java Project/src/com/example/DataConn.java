package com.example;

import com.example.account.AccountDetails;
import com.example.customer.CustomerDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface DataConn {
    void addCustomer(CustomerDetails cd);
    void addSavingAccount(AccountDetails ad);
    void addCurrentAccount(AccountDetails ad);
    List<Object> showAllDetails();
    List<Object> searchDetails(long cd);
    void deleteDetails(long cd);
}
