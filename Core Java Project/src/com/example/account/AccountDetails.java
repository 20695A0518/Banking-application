package com.example.account;

public class AccountDetails {
    private long customerId;
    private long accountNo;

    private String type;
    private long balance;

    public AccountDetails(long customerId, long accountNo, String type, long balance) {
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.type = type;
        this.balance = balance;
    }

    public AccountDetails(long customerId) {
        this.customerId = customerId;
    }

    public AccountDetails(long customerId, String type, long balance) {
        this.customerId = customerId;
        this.type = type;
        this.balance = balance;
    }

    public AccountDetails() {
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "customerId=" + customerId +
                ", accountNo=" + accountNo +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                '}';
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
