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
     * Test A1.1
     * INVALID: FirstName null
     */
    @Test
    public void testFirstNameIsEmpty() {

        //Setting first name to null
        guest.setFirstName("");

        assertInvalid(guest, "firstName", "First name must be greater than 0 characters", "");

    }

    /***
     * Test A1.2
     * INVALID: First Name 21 characters is too long
     */
    @Test
    public void testFirstNameIsTooLong() {

        String invalid = repeatM(21);

        guest.setFirstName(invalid);

        assertInvalid(guest, "firstName", "First name must be less than or equal to 20 characters", invalid);

    }

    /***
     * Test A1.3
     * INVALID: Last Name is null
     */
    @Test
    public void testLastNameIsEmpty() {

        guest.setLastName("");

        assertInvalid(guest, "lastName", "Last name must be greater than 0 characters", "");

    }

    /***
     * Test A1.4
     * INVALID: Last Name is 31 characters too long
     */
    @Test
    public void testLastNameIsTooLong(){
        String invalid = repeatM(31);
        guest.setLastName(invalid);
        assertInvalid(guest, "lastName", "Last name must be less than or equal to 30 characters", invalid);
    }

    /***
     * Test A1.5
     * INVALID: Email is null
     */
    @Test
    public void testEmailIsEmpty() {

        guest.setEmail("");
        assertInvalid(guest, "email", "Email must be greater than 0 characters", "");

    }

    /***
     * Test A1.6
     * INVALID: Email is 51 characters too long
     */
    @Test
    public void testEmailIsTooLong() {
        String invalid = repeatM(51);
        guest.setEmail(invalid);
        assertInvalid(guest, "email", "Email must be less than or equal to 50 characters", invalid);
    }


    /***
     * Test A1.7
     * VALID: First name lowerbound is 1 character
     */
    @Test
    public void testFirstNameLowerBound() {
        guest.setFirstName(repeatM(1));

        assertEquals(0, validator.validate(guest).size());

    }

    /***
     * Test A1.8
     * VALID: First name upperbound is 20 characters
     */
    @Test
    public void testFirstNameUpperBound() {
        guest.setFirstName(repeatM(20));

        assertEquals(0, validator.validate(guest).size());

    }

    /***
     * Test A1.8
     * VALID: Last name lowerbound is 1 character
     */
    @Test
    public void testLastNameLowerBound() {
        guest.setLastName(repeatM(1));

        assertEquals(0, validator.validate(guest).size());
    }

    /***
     * Test A1.9
     * VALID: Last name upperbound is 30 characters
     */
    @Test
    public void testLastNameUpperBound() {
        guest.setLastName(repeatM(30));

        assertEquals(0, validator.validate(guest).size());

    }

    /***
     * Test A2.1
     * VALID: email lowerbound is 1 character
     */
    @Test
    public void testEmailLowerBound() {
        guest.setEmail(repeatM(1));

        assertEquals(0, validator.validate(guest).size());
    }

    /***
     * Test A2.2
     * VALID: email upperbound is 50 characters
     */
    @Test
    public void testEmailUpperBound() {
        guest.setEmail(repeatM(50));

        assertEquals(0, validator.validate(guest).size());
    }

    /***
     * Test A2.3
     * VALID: phone number is 10 digits
     */
    @Test
    public void testPhoneNumberIsValid() {

        String[] valid = { "1234567891", "9874563215", "5488796542"};

        for (String validPhone : valid)
        {
            guest.setPhoneNumber(validPhone);
            assertEquals(0, validator.validate(guest).size());
        }

    }

    /***
     * Test A2.4
     * VALID: phone number is invalid with non digits
     */
    @Test
    public void testPhoneNumberIsInvalid() {

        String[] invalid = { "asdewqfsas", "12s356f489", "123", "saff554812"};

        for (String invalidPhone : invalid)
        {
            guest.setPhoneNumber(invalidPhone);
            assertInvalid(guest, "phoneNumber", "Phone number must be exactly 10 digits", invalidPhone);
        }

    }


    /*** Test A2.5
     * INVALID: Credit card Invalid pattern
     */
    @Test
    public void testCreditCardPatternInvalid() {
        String[] invalid = {"a12s4f58995422sgdaa", "123456789112345s", "123456478978998789789999999", "asdqwewqradgagasg"};

        for (String invalidCard : invalid)
        {
            guest.setCreditCardNum(invalidCard);
            assertInvalid(guest, "creditCardNum", "Credit card number must only contain digits and be 16 digits long", invalidCard);
        }
    }

    /*** Test A2.6
     * INVALID: Credit card valid pattern
     */
    @Test
    public void testCreditCardPatternValid() {
        String[] valid = {"1234567891123456", "9876543212345678", "4567893215698856", "1547896532154789"};

        for (String validCard : valid)
        {
            guest.setCreditCardNum(validCard);
            assertEquals(0, validator.validate(guest).size());
        }
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
