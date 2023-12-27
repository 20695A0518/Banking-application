package com.example.account;

import java.sql.SQLException;

public interface Accounts {
    void addAccount(AccountDetails ad) throws SQLException, ClassNotFoundException;
}
