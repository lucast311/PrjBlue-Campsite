import campground_data.CabinType;
import campground_data.Site;
import campground_data.SiteType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

//richies code
public class SiteTest {
    private static ValidatorFactory vf;
    private static Validator validator;

    private Site site;


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
    public void setUpValidSite() {
        site = new Site(1, true, 75.55, Site.SiteType.Group,false, 4);
    }

    /**
     * B1.0
     * VALID: Serviced is set to True
     */
    @Test
    public void testServicedTrue() {
        site.setServiced(true);

        assertTrue(site.isServiced());
    }

    /**
     * B1.1
     * VALID: Serviced is set to False
     */
    @Test
    public void testServicedFalse() {
        site.setServiced(false);

        assertFalse(site.isServiced());
    }

    /**
     * B1.2
     * VALID: Type is set to Group
     */
    @Test
    public void testTypeGroup() {
        site.setSiteType(Site.SiteType.Group);

        assertEquals(SiteType.Group, site.getSiteType());
    }



    /**
     * B1.3
     * VALID: Type is set to Individual
     */
    @Test
    public void testTypeIndividual() {
        site.setSiteType(Site.SiteType.Individual);

        assertEquals(SiteType.Individual, site.getSiteType());
    }

    /***
     * B1.4
     * VALID: siteNum is one
     */
    @Test
    public void testSiteNumLowerBound() {
        site.setPlotID(1);

        assertEquals(0, validator.validate(site).size());

    }

    /***
     * B1.5
     * INVALID: siteNum is less than one
     */
    @Test
    public void testSiteNumIsLessThanOne() {
        site.setPlotID(0);

        assertInvalid(site, "plotID", "ID must be greater than or equal to 1", 0);

    }



    /***
     * B1.6
     * INVALID: Occupancy is less than one
     */
    @Test
    public void testOccupancyIsLessThanOne() {
        site.setOccupancy(0);

        assertInvalid(site, "occupancy", "Occupancy must be greater than or equal to 1", 0);

    }

    /***
     * B1.7
     * INVALID: Occupancy is greater than eight
     */
    @Test
    public void testOccupancyIsGreaterThanEight() {
        site.setOccupancy(9);

        assertInvalid(site, "occupancy", "Occupancy must be less than or equal to 8", 9);

    }

    /***
     * B1.8
     * VALID: Occupancy lower bound is one
     */
    @Test
    public void testOccupancyLowerBound() {
        site.setOccupancy(1);

        assertEquals(0, validator.validate(site).size());

    }

    /***
     * B1.9
     * VALID: Occupancy upper bound is eight
     */
    @Test
    public void testOccupancyUpperBound() {
        site.setOccupancy(8);

        assertEquals(0, validator.validate(site).size());
    }

    /***
     * B2.0
     * INVALID: Price is less than one
     */
    @Test
    public void testPriceIsLessThanOne() {
        site.setPrice(0.6);

        assertInvalid(site, "price", "Price must be greater than or equal to 1", 0.6);

    }

    /***
     * B2.1
     * VALID: Price lower bound is one
     */
    @Test
    public void testPriceLowerBound() {
        site.setPrice(1);

        assertEquals(0, validator.validate(site).size());

    }

    /**
     * B2.2
     * VALID: underReno is set to True
     */
    @Test
    public void testUnderRenoTrue() {
        site.setUnderReno(true);

        assertTrue(site.isUnderReno());
    }

    /**
     * B2.3
     * VALID: underReno is set to False
     */
    @Test
    public void testUnderRenoFalse() {
        site.setUnderReno(false);

        assertFalse(site.isUnderReno());
    }

    /**
     * B2.4
     * VALID: Booked is set to True
     */
    @Test
    public void testBookedTrue() {
        site.setBooked(true);

        assertTrue(site.isBooked());
    }

    /**
     * B2.5
     * VALID: Booked is set to False
     */
    @Test
    public void testBookedFalse() {
        site.setBooked(false);

        assertFalse(site.isBooked());
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
