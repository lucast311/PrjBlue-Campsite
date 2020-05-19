import campground_data.*;
import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

public class GuestTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private Guest guest;


    /***
     * Run once at class creation to set up validator
     * or any other static objects
     */
    @BeforeClass
    public static void setUpValidator() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    /***
     * Run once at class destruction to tear down validator
     * or any other static objects
     */
    @AfterClass
    public static void tearDownValidator()
    {
        //gracefully teardown the validator factory
        vf.close();
    }

    /***
     * Run before each @Test to create a valid car object for test cases
     * or any other settings/variables needed in multiple test cases
     */
    @Before
    public void setUpValidGuest()
    {
        guest = new Guest();
        guest.setFirstName("John");
        guest.setLastName("Smith");
        guest.setEmail("jsmith@hotmail.com");
        guest.setPaymentMethod(PaymentType.Credit);
        guest.setPhoneNumber("3061111111");
        guest.setAddress(new Address(111, 111, "Street", "Saskatoon", "SK", "Canada", "111111"));

    }

    /***
     * INVALID: FirstName null
     */
    @Test
    public void testFirstNameIsEmpty() {

        //Setting first name to null
        guest.setFirstName("");

        assertInvalid(guest, "firstName", "First name must be greater than 0 characters", "");

    }

    /***
     * INVALID: First Name 21 characters is too long
     */
    @Test
    public void testFirstNameIsTooLong() {

        String invalid = repeatM(21);

        guest.setFirstName(invalid);

        assertInvalid(guest, "firstName", "First name must be shorter than or equal to 20 characters", invalid);

    }

    /***
     * INVALID: Last Name is null
     */
    @Test
    public void testLastNameIsEmpty() {

        guest.setLastName("");

        assertInvalid(guest, "lastName", "Last name must be greater than 0 characters", "");

    }

    /***
     * INVALID: Last Name is 31 characters too long
     */
    @Test
    public void testLastNameIsTooLong(){
        String invalid = repeatM(31);
        guest.setLastName(invalid);
        assertInvalid(guest, "lastName", "Last name must be less than or equal to 30 characters", invalid);
    }

    /***
     * INVALID: Email is null
     */
    @Test
    public void testEmailIsEmpty() {

        guest.setEmail("");
        assertInvalid(guest, "email", "Email must be greater than 0 characters", "");

    }

    /***
     * INVALID: Email is 51 characters too long
     */
    @Test
    public void testEmailIsTooLong() {
        String invalid = repeatM(51);
        guest.setEmail(invalid);
        assertInvalid(guest, "email", "Email must be less than or equal to 50 characters", invalid);
    }

    /***
     * INVALID: Phone number is null
     */
    @Test
    public void testPhoneNumberIsEmpty() {
        guest.setPhoneNumber("");
        assertInvalid(guest, "phoneNumber", "Phone number must be greater than 0 characters", "");
    }

    /***
     * INVALID: Phone number is 11 characters too long
     */
    @Test
    public void testPhoneNumberIsTooLong() {
        String invalid = repeatM(11);
        guest.setPhoneNumber(invalid);
        assertInvalid(guest, "phoneNumber", "Phone number must be less than or equal to 10 characters", invalid);
    }

    /***
     * VALID: First name is 10 characters
     */
    @Test
    public void testFirstNameIsValid() {
        guest.setFirstName(repeatM(10));

        assertEquals(0, validator.validate(guest).size());

    }

    /***
     * VALID: Last name is 20 characters
     */
    @Test
    public void testLastNameIsValid() {
        guest.setLastName(repeatM(20));

        assertEquals(0, validator.validate(guest).size());
    }

    /***
     * VALID: email is 25 characters
     */
    @Test
    public void testEmailIsValid() {
        guest.setEmail(repeatM(25));

        assertEquals(0, validator.validate(guest).size());
    }

    /***
     * VALID: phone number is 10 characters
     */
    @Test
    public void testPhoneNumberIsValid() {
        guest.setPhoneNumber(repeatM(10));
        assertEquals(0, validator.validate(guest).size());
    }

    /***
     * VALID: Credit card number is 16 characters
     */
    @Test
    public void testCreditCardNumIsValid() {
        guest.setCreditCardNum(repeatM(16));
        assertEquals(0, validator.validate(guest).size());
    }

    /***
     * Helper: quickly create a string of letter M's of any size for testing string length bounds
     * @param count - number of times the letter M is repeated in a string
     * @return string of the letter M repeated the specified number of times
     */
    private String repeatM(int count){
        return new String(new char[count]).replace('\0','M');
    }


    /***
     * Helper: performs the same 4 asserts for invalid field / violations
     *      1.assert only 1 violation (multiple violations may indicate poor choice of test values)
     *      2.assert the property with the violation is the one we expect
     *      3.assert that the error message we get is the expected error message
     *      4.assert the invalid value is what was set to ensure th setter dod not change the value somehow
     * @param expectedProperty - the string name of the property/attribute in the cst1.color.carfleet.pink.cars_data.Car class we are testing
     * @param expectedErrMsg - the EXACT expected error message
     * @param expectedValue - the EXACT invalid value (ensure the value was not changed by the class or setter)
     */
    private void assertInvalid(Object obj, String expectedProperty, String expectedErrMsg, Object expectedValue){
        //run validator on car object and store the resulting violations in a collection
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate( obj );

        //count how many violations - SHOULD ONLY BE 1
        assertEquals( 1, constraintViolations.size() );

        //get first violation from constraintViolations collection
        ConstraintViolation<Object> violation = constraintViolations.iterator().next();

        //ensure that expected property has the violation
        assertEquals( expectedProperty, violation.getPropertyPath().toString() );

        //ensure error message matches what is expected
        assertEquals( expectedErrMsg, violation.getMessage() );

        //ensure the invalid value is what was set
        assertEquals( expectedValue, violation.getInvalidValue() );
    }

}
