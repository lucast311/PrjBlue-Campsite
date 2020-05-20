import campground_data.*;
import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

public class CabinTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private Cabin cabin;


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
    public static void tearDownValidator() {
        //gracefully teardown the validator factory
        vf.close();
    }

    /***
     * Run before each @Test to create a valid car object for test cases
     * or any other settings/variables needed in multiple test cases
     */
    @Before
    public void setUpValidCabin() {
        cabin = new Cabin(1, 4, CabinType.Deluxe, 150.55, false, false);
    }

    /***
     * A1.1
     * INVALID: CabinID is less than one
     */
    @Test
    public void testCabinNumIsLessThanOne() {
        cabin.setPlotID(0);

        assertInvalid(cabin, "plotID", "ID must be greater than or equal to 1", 0);

    }

    /***
     * A1.2
     * INVALID: CabinID is one
     */
    @Test
    public void testCabinNumLowerBound() {
        cabin.setPlotID(1);

        assertEquals(0, validator.validate(cabin).size());

    }

    /***
     * A1.3
     * INVALID: Occupancy is less than one
     */
    @Test
    public void testOccupancyIsLessThanOne() {
        cabin.setOccupancy(0);

        assertInvalid(cabin, "occupancy", "Occupancy must be greater than or equal to 1", 0);

    }

    /***
     * A1.4
     * INVALID: Occupancy is greater than eight
     */
    @Test
    public void testOccupancyIsGreaterThanEight() {
        cabin.setOccupancy(9);

        assertInvalid(cabin, "occupancy", "Occupancy must be greater than or equal to 1", 0);

    }

    /***
     * A1.5
     * VALID: Occupancy lower bound is one
     */
    @Test
    public void testOccupancyLowerBound() {
        cabin.setOccupancy(1);

        assertEquals(0, validator.validate(cabin).size());

    }

    /***
     * A1.6
     * VALID: Occupancy upper bound is eight
     */
    @Test
    public void testOccupancyUpperBound() {
        cabin.setOccupancy(8);

        assertEquals(0, validator.validate(cabin).size());
    }

    /***
     * A1.7
     * INVALID: Price is less than one
     */
    @Test
    public void testPriceIsLessThanOne() {
        cabin.setPrice(0.6);

        assertInvalid(cabin, "price", "Price must be greater than or equal to 1", 0.6);

    }

    /***
     * A1.8
     * VALID: Price lower bound is one
     */
    @Test
    public void testPriceLowerBound() {
        cabin.setPrice(1);

        assertEquals(0, validator.validate(cabin).size());

    }

    /**
     * A1.9
     * VALID: underReno is set to True
     */
    @Test
    public void testUnderRenoTrue() {
        cabin.setUnderReno(true);

        assertTrue(cabin.isUnderReno());
    }

    /**
     * A2.0
     * VALID: underReno is set to False
     */
    @Test
    public void testUnderRenoFalse() {
        cabin.setUnderReno(false);

        assertFalse(cabin.isUnderReno());
    }

    /**
     * A2.1
     * VALID: Booked is set to True
     */
    @Test
    public void testBookedTrue() {
        cabin.setBooked(true);

        assertTrue(cabin.isBooked());
    }

    /**
     * A2.2
     * VALID: Booked is set to True
     */
    @Test
    public void testBookedFalse() {
        cabin.setBooked(false);

        assertFalse(cabin.isBooked());
    }

    /**
     * A2.3
     * VALID: Type is set to Deluxe
     */
    @Test
    public void testTypeDeluxe() {
        cabin.setCabinType(CabinType.Deluxe);

        assertEquals(CabinType.Deluxe, cabin.getCabinType());
    }

    /**
     * A2.3
     * VALID: Type is set to Basic
     */
    @Test
    public void testTypeBasic() {
        cabin.setCabinType(CabinType.Basic);

        assertEquals(CabinType.Basic, cabin.getCabinType());
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
    private void assertInvalid(Object obj, String expectedProperty, String expectedErrMsg, Object expectedValue) {
        //run validator on car object and store the resulting violations in a collection
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);

        //count how many violations - SHOULD ONLY BE 1
        assertEquals(1, constraintViolations.size());

        //get first violation from constraintViolations collection
        ConstraintViolation<Object> violation = constraintViolations.iterator().next();

        //ensure that expected property has the violation
        assertEquals(expectedProperty, violation.getPropertyPath().toString());

        //ensure error message matches what is expected
        assertEquals(expectedErrMsg, violation.getMessage());

        //ensure the invalid value is what was set
        assertEquals(expectedValue, violation.getInvalidValue());
    }
}
