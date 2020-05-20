import campground_data.*;
import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

public class AddressTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private Address address;

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

    @Before
    public void setUpValidAddress()
    {
        address = new Address();
        address.setStreetNum(231);
        address.setAptNum(111);
        address.setCity_Town("Saskatoon");
        address.setCountry("Canada");
        address.setProvince("SK");
        address.setPostalCode("S1M2T8");
        address.setStreetName("10th Street");
    }

    /***
     * INVALID: StreetNum is 0
     */
    @Test
    public void testStreetNumIsZero() {

        //Setting first name to null
        address.setStreetNum(0);
        assertInvalid(address, "streetNum", "Street number must be at least 1", 0);
    }

    /***
     * INVALID: AptNum is less than 0
     */
    @Test
    public void testAptNumIsLessThanZero() {

        //Setting first name to null
        address.setAptNum(-1);
        assertInvalid(address, "aptNum", "Apt number must be at least 1, 0 means it does not exist", -1);
    }

    /***
     * INVALID: Street name is 31 characters too long
     */
    @Test
    public void testStreetNumberContainCharacters() {

        String invalid = repeatM(31);
        address.setStreetName(invalid);
        assertInvalid(address, "streetName", "The street name must be less than or equal to 30 characters", invalid);
    }



    /***
     * INVALID: Street Name is null
     */
    @Test
    public void testStreetNameIsEmpty() {
        address.setStreetName("");
        assertInvalid(address, "streetName", "The street name must be greater than 0 characters", "");
    }

    /***
     * INVALID: City_town is 31 characters too long
     */
    @Test
    public void testCityTownIsTooLong() {

        String invalid = repeatM(31);
        address.setCity_Town(invalid);
        assertInvalid(address, "city_Town", "The city or town must be less than or equal to 30 characters", invalid);

    }

    /***
     * INVALID: City_Town is null
     */
    @Test
    public void testCityTownIsEmpty() {
        address.setCity_Town("");
        assertInvalid(address, "city_Town", "The city or town must be greater than 0 characters", "");
    }

    /***
     * INVALID: Province is 31 characters too long
     */
    @Test
    public void testProvinceIsTooLong() {

        String invalid = repeatM(31);
        address.setProvince(invalid);
        assertInvalid(address, "province", "The province must be less than or equal to 30 characters", invalid);

    }

    /***
     * INVALID: Province is null
     */
    @Test
    public void testProvinceIsEmpty() {
        address.setProvince("");
        assertInvalid(address, "province", "The province must be greater than 0 characters", "");
    }


    /***
     * INVALID: Country is 31 characters too long
     */
    @Test
    public void testCountryIsTooLong() {

        String invalid = repeatM(31);
        address.setCountry(invalid);
        assertInvalid(address, "country", "The country must be less than or equal to 30 characters", invalid);

    }

    /***
     * INVALID: Country is null
     */
    @Test
    public void testCountryIsEmpty() {
        address.setCountry("");
        assertInvalid(address, "country", "The country must be greater than 0 characters", "");
    }


    /***
     * INVALID: Invalid postal code
     */
    @Test
    public void testPostalCodeInvalidLong() {
        String[] invalid = {"AAD5e3", "00T1J8", "R233T5", "s0kr34", "21sf"};
        for (String invalidCode : invalid)
        {
            address.setPostalCode(invalidCode);

            assertInvalid(address, "postalCode", "Postal code must be in the format A1A1A1", invalidCode);
        }

    }

    /***
     * VALID: Valid postal code
     */
    @Test
    public void testPostalCodeValidPattern() {
        String[] valid = {"A1D5G3", "S0T1J8", "R2h3T5", "t0k3e4"};
        for (String validCode : valid)
        {
            address.setPostalCode(validCode);

            assertEquals(0, validator.validate(address).size());
        }
    }



    /***
     * VALID: Streetnumber lowerbound is 1 characters
     */
    @Test
    public void testStreetNumberLowerBound() {

        address.setStreetNum(1);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: Aptnumber upperbound is 0 characters
     */
    @Test
    public void testAptNumberLowerBound() {

        address.setAptNum(0);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: Streetnumber lowerbound is 1 character
     */
    @Test
    public void testStreetNameLowerBound() {

        String valid = repeatM(1);
        address.setStreetName(valid);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: Streetnumber upperbound is 30 characters
     */
    @Test
    public void testStreetNameUpperBound() {

        String valid = repeatM(30);
        address.setStreetName(valid);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: CityTown lowerbound is 1 character
     */
    @Test
    public void testCityTownLowerBound() {

        String valid = repeatM(1);
        address.setCity_Town(valid);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: CityTown upperbound is 30 characters
     */
    @Test
    public void testCityTownUpperBound() {

        String valid = repeatM(30);
        address.setCity_Town(valid);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: Province lowerbound is 1 character
     */
    @Test
    public void testProvinceLowerBound() {

        String valid = repeatM(1);
        address.setProvince(valid);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: Province upperbound is 30 characters
     */
    @Test
    public void testProvinceUpperBound() {

        String valid = repeatM(30);
        address.setProvince(valid);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: Country lowerbound is 1 character
     */
    @Test
    public void testCountryLowerBound() {

        String valid = repeatM(1);
        address.setCountry(valid);

        assertEquals(0, validator.validate(address).size());

    }

    /***
     * VALID: Country upperbound is 30 characters
     */
    @Test
    public void testCountryUpperBound() {

        String valid = repeatM(30);
        address.setCountry(valid);

        assertEquals(0, validator.validate(address).size());

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
