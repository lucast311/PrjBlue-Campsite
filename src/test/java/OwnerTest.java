import campground_data.*;
import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.*;

public class OwnerTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private ArrayList<Owner> ownersTest;
    private Owner owner1;
    private Owner owner2;
    private Owner owner3;
    private Owner owner;
    private OwnerHelper ownerHelper = new OwnerHelper();

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
    public void setUpValidOwner()
    {
        ownersTest = new ArrayList<>();
        owner1 = new Owner("harry", "louis", "Mounta1nM@n", "555-555-5555", "hlouis@cestlake.ca", 3, true);
        owner2 = new Owner("mary", "louis", "F1uffyC@ts", "555-555-5555", "mlouis@cestlake.ca", 3, true);
        owner3 = new Owner("guest", "login", "Pa$$w0rd", "n/a", "info@cestlake.ca", 1, false);
        ownersTest.add(owner1);
        ownersTest.add(owner2);
        owner = new Owner();
        owner.setFirstName("test");
        owner.setLastName("tester");
        owner.setPassword("Pa$$w0rd");
        owner.setUserId("test.tester");
        owner.setPermissions(3);
        owner.setEmail("example@test.ca");
        owner.setPhone("5435552389");
        owner.setOnSite(true);

    }

    /***
     * VALID: UserID is created properly from the Owner constructor
     */
    @Test
    public void testUserIdIsCreatedFromConstructor() {

        assertEquals(owner1.getUserId(), "harry.louis");
        assertEquals(owner2.getUserId(), "mary.louis");

    }

    /***
     * INVALID: UserId entered does not exist in the system
     */
    @Test
    public void testUserIdDoesNotExist() {

        String userInput = "harrylouis";

        assertEquals(ownerHelper.validateUser(userInput, "Mounta1nM@n"), null);

    }

    /***
     * VALID: UserId entered does exist in the system
     */
    @Test
    public void testUserIdExists() {

        String userInput = "guest.login";
        Owner testUser = ownerHelper.validateUser(userInput, "Pa$$w0rd");
        assertEquals(testUser.getPassword(), owner3.getPassword());
        assertEquals(testUser.getFirstName(), owner3.getFirstName());
        assertEquals(testUser.getLastName(), owner3.getLastName());
        assertEquals(testUser.getEmail(), owner3.getEmail());
        assertEquals(testUser.getPermissions(), owner3.getPermissions());
        assertEquals(testUser.getPhoneNumber(), owner3.getPhoneNumber());
        assertEquals(testUser.getOnSite(), owner3.getOnSite());


    }

    /***
     * VALID: password exists in the Owner object that is logging in
     */
    @Test
    public void testPasswordExists() {
        String userInput = "Mounta1nM@n";
        Owner testUser = ownerHelper.validateUser("harry.louis", userInput);
        assertEquals(testUser.getPassword(), owner1.getPassword());
        assertEquals(testUser.getFirstName(), owner1.getFirstName());
        assertEquals(testUser.getLastName(), owner1.getLastName());
        assertEquals(testUser.getEmail(), owner1.getEmail());
        assertEquals(testUser.getPermissions(), owner1.getPermissions());
        assertEquals(testUser.getPhoneNumber(), owner1.getPhoneNumber());
        assertEquals(testUser.getOnSite(), owner1.getOnSite());


    }

    /***
     * INVALID: password does not exists in the Owner object that is logging in
     */
    @Test
    public void testPasswordInvalid() {
        String userInput = "Pa$$w0rd";
        Owner testUser = ownerHelper.validateUser("harry.louis", userInput);
        assertEquals(testUser, null);

    }

    /**
     * INVALID: UserID field cannot be left blank when logging in to the system
     */
    @Test
    public void testUserIdIsNotBlank() {
        Owner testUser = ownerHelper.validateUser("", "Pa$$w0rd");
                assertEquals(testUser, null);
    }

    /**
     * INVALID: Password field cannot be left blank when logging into the system
     */
    @Test
    public void testPasswordIsNotBlank() {
        Owner testUser = ownerHelper.validateUser("harry.louis", "");
        assertEquals(testUser, null);
    }

    /**
     * 1t.1
     * VALID:
     */
    @Test
    public void testFirstNameLowerBound() {
        owner.setFirstName(repeatM(2));
        assertEquals(0, validator.validate(owner).size());
    }

    /**
     * 1t.2
     * INVALID: First name must be at least 2 characters
     */
    @Test
    public void testFirstNameMinLength() {
        owner.setFirstName("T");
        assertInvalid(owner, "firstName", "First name must be between 2 and 20 characters", "T");
    }

    /**
     * 1t.3
     * VALID: First name must be 20 chars or less
     */
    @Test
    public void testFirstNameUpperBound() {
        owner.setFirstName(repeatM(20));
        assertEquals(0, validator.validate(owner).size());
    }

    /**
     * 1t.4
     * INVALID: First name max length is 20 characters
     */
    @Test
    public void testFirstNameMaxLength()
    {
        String invalid = repeatM(21);
        owner.setFirstName(invalid);
        assertInvalid(owner, "firstName", "First name must be between 2 and 20 characters", invalid);
    }

    /**
     * 1t.5
     * INVALID: Last name must be at least 2 characters
     */
    @Test
    public void testLastNameMinLength()
    {
        owner.setLastName("L");
        assertInvalid(owner, "lastName", "Last name must be between 2 and 30 characters", "L");
    }

    /**
     * 1t.6
     * VALID: Last name at least 2 characters
     */
    @Test
    public void testLastNameLowerBound()
    {
        owner.setLastName(repeatM(2));
        assertEquals(0, validator.validate(owner).size());
    }

    /**
     * 1t.7
     * VALID: Last name less than 30 characters
     */
    @Test
    public void testLastNameUpperBound()
    {
        owner.setLastName(repeatM(30));
        assertEquals(0, validator.validate(owner).size());
    }

    /**
     * 1t.8
     * INVALID: Last name max length is 30 characters
     */
    @Test
    public void testLastNameMaxLength()
    {
        String invalid = repeatM(31);
        owner.setLastName(invalid);
        assertInvalid(owner, "lastName", "Last name must be between 2 and 30 characters", invalid);
    }

    /**
     * 1t.9
     * INVALID: Password must be at least 8 characters in length
     */
    @Test
    public void testPasswordMinLength()
    {
        String invalid = repeatM(7);
        owner.setPassword(invalid);
        assertInvalid(owner, "password", "Password must be at least 8 characters in length", invalid);
    }

    /**
     * 1t.10
     * VALID: Password can be 8 or more characters in length
     */
    @Test
    public void testPasswordLowerBound()
    {
        String valid = repeatM(8);
        owner.setPassword(valid);
        assertEquals(0, validator.validate(owner).size());
    }

    /**
     * 1t.11
     * VALID: new passwords must match in order to change password
     */
    @Test
    public void testPasswordChange()
    {
        String pass1 = "Pa$$w0rd";
        String pass2 = "Pa$$w0rd";
        boolean success = ownerHelper.changePassword(owner, pass1, pass2);

    }

    /**
     * 1t.12
     * VALID: email in valid form of example@test.ca
     */
    @Test
    public void testEmailPatternValid()
    {
        owner.setEmail("tomlouis@cestlake.ca");
        assertEquals(0, validator.validate(owner).size());
    }

    /**
     *
     * INVALID: email must be in format example@test.ca
     */
    @Test
    public void testEmailPatternInvalid()
    {
        owner.setEmail("tomlouis#cestlake.ca");
        assertInvalid(owner, "email", "Email addresses must be in the format example@test.ca", "tomlouis#cestlake.ca");
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

