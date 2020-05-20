package campground_data;

import org.json.simple.JSONObject;
import java.io.Serializable;
import javax.validation.constraints.*;


/***
 * Java Bean standard is a class that is Serializable, with all private attributes,  and a NO PARAMETER Constructor
 */
public class Guest  implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private PaymentType paymentMethod;
    private String creditCardNum;
    private Address address;

    public Guest(String firstName, String lastName, String email, String phoneNumber, PaymentType paymentMethod,
                 String creditCardNum, Address address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.paymentMethod = paymentMethod;
        this.creditCardNum = creditCardNum;
        this.address = address;

    }

    public Guest() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PaymentType getPaymentMethod() {
        return paymentMethod;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public Address getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPaymentMethod(PaymentType paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name:" + this.firstName + " " + this.lastName + "\nEmail: " + this.email + "\nPhone Number: " + this.phoneNumber + "\nPayment Method: " + this.paymentMethod
                    + "\nCredit Card Num: " + this.creditCardNum + "\nAddress: " + this.address.toString();
    }

}
