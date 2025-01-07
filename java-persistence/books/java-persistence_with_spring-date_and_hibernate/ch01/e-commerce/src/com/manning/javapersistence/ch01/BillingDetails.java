public class BillingDetails {
    private String account;
    private String bankname;
    private User user;
    
    public BillingDetails(String account, String bankname, User user) {
        this.account = account;
        this.bankname = bankname;
        this.user = user;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getBankname() {
        return bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}