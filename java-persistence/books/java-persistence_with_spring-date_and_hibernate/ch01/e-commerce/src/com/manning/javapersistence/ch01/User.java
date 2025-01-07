import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String address;
    private Set<BillingDetails> billingDetails = new HashSet<>();
    
    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Set<BillingDetails> getBillingDetails() {
        return Collections.unmodifiableSet(billingDetails);
    }
    public void addBillingDetails(BillingDetails billingDetail) {
        billingDetails.add(billingDetail);
    }
}