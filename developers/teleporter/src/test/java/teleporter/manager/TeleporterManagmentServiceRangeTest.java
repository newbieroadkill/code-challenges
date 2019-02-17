package teleporter.manager;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class TeleporterManagmentServiceRangeTest {

     @Test
     public void givenNoCitiesInRangeReturnBlankList(){
         TeleporterManager teleporterManager = new TeleporterManagementService();
         teleporterManager.addRoute("Kiev", "Moscow");

         List<String> citiesInRange = teleporterManager.citiesInRange("Tokyo", 1);

         Assert.assertNotNull("Method should not return null", citiesInRange);
         Assert.assertEquals(0, citiesInRange.size());
     }
}
