import campground_ui.FinancialReportWindow;
import javafx.application.Application;
import org.junit.Test;

public class Story1pTest
{

    /**
     *This test's sole purpose is to run and open up the FinancialReportWindow.
     *Test passes if the window opens
     */
    @Test
    public void testCreateViewBookingWindow()
    {
        //Running this test should launch the FinancialReportWindow class.
        //No way to test for anything other than if it launches or not
        Application.launch(FinancialReportWindow.class);
    }

    //Really nothing to test on this story, other than if it works or not, but I don't know of any way to access a button click handler from a different class
}
