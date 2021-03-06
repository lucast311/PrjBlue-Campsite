package campground_data;

import java.io.Serializable;
import javax.validation.constraints.*;


/***
 * Java Bean standard is a class that is Serializable, with all private attributes,  and a NO PARAMETER Constructor
 */
public class Guest  implements Serializable {

    private static int nGuestIdCount = 1;

    @NotEmpty(message = "First name must be greater than 0 characters")
    @Size(max=20, message = "First name must be less than or equal to 20 characters")
    private String firstName;

    @NotEmpty(message = "Last name must be greater than 0 characters")
    @Size(max = 30, message = "Last name must be less than or equal to 30 characters")
    private String lastName;

    @NotEmpty(message = "Email must be greater than 0 characters")
    @Size(max = 50, message = "Email must be less than or equal to 50 characters")
    private String email;

    @Pattern(regexp = "^(\\d{10})", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    private PaymentType paymentMethod;

    @Pattern(regexp = "^\\d{4}[ ]\\d{4}[ ]\\d{4}[ ]\\d{4}$", message = "Credit card number must be in the format 1234 1234 1234 1234")
    private String creditCardNum;

    private int guestID;

    private Address address;

    public Guest(String firstName, String lastName, String email, String phoneNumber, PaymentType paymentMethod,
                 String creditCardNum, Address address) {
        this.guestID = nGuestIdCount++;
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

    public int getGuestID() { return this.guestID; }

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

    /**
     * This method will used in the toString method to mask the credit card number of the guest
     * @param creditCardNum
     * @return
     */
    public String maskCreditCardNumber(String creditCardNum)
    {
        String sRet = "";

        if (creditCardNum.equalsIgnoreCase("0000 0000 0000 0000"))
        {
           sRet += "N/A";
           return sRet;
        }

        for (int i=0; i < creditCardNum.length(); i++)
        {
            if (creditCardNum.charAt(i) == ' ')
            {
                sRet += "";
            }

            if (i >= 0 && i < creditCardNum.length() - 4 && creditCardNum.charAt(i) != ' ')
            {
                sRet += "*";
            }
            else
            {
                sRet += creditCardNum.charAt(i);
            }
        }
        return sRet;
    }



    @Override
    public String toString() {
        return "Name:" + this.firstName + " " + this.lastName +" \n"+ "Email: " + this.email +" \n" + "Phone Number: " + this.phoneNumber +" \n"+ "Payment Method: " + this.paymentMethod +" \n" + "Credit Card Number: " + maskCreditCardNumber(this.creditCardNum) + "\n"
                    + "Address: " + (this.address.getAptNum() > 0 ? String.valueOf(this.address.getAptNum()) + " " : "") + this.address.getStreetNum() + " " + this.address.getStreetName() + " " + this.address.getCity_Town() +
                    " " + this.address.getProvince() + " " + this.address.getCountry() + " " + this.address.getPostalCode();

    }

}
