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

    @Test
    public void givenOneCityInRangeReturnCityInList(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Kiev", "Moscow");

        List<String> citiesInRange = teleporterManager.citiesInRange("Moscow", 1);

        Assert.assertEquals(1, citiesInRange.size());
        Assert.assertTrue(citiesInRange.contains("Kiev"));
    }

    @Test
    public void givenOneCityInRangeOneCityNotInRangeReturnCityInList(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Tokyo", "Los Angeles");
        teleporterManager.addRoute("Los Angeles", "Moscow");

        List<String> citiesInRange = teleporterManager.citiesInRange("Moscow", 1);

        Assert.assertEquals(1, citiesInRange.size());
        Assert.assertTrue(citiesInRange.contains("Los Angeles"));
    }

    @Test
    public void givenTwoCitiesInRangeReturnBothCitiesInList(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Tokyo", "Los Angeles");
        teleporterManager.addRoute("Los Angeles", "Moscow");

        List<String> citiesInRange = teleporterManager.citiesInRange("Los Angeles", 1);

        Assert.assertEquals(2, citiesInRange.size());
        Assert.assertTrue(citiesInRange.contains("Moscow"));
        Assert.assertTrue(citiesInRange.contains("Tokyo"));
    }
}
