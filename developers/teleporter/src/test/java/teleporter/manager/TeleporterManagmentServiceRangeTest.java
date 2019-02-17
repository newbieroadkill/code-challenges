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

    @Test
    public void givenTwoCitiesInTwoJumpsRangeReturnBothCitiesInList(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Tokyo", "Los Angeles");
        teleporterManager.addRoute("Los Angeles", "Moscow");

        List<String> citiesInRange = teleporterManager.citiesInRange("Tokyo", 2);

        Assert.assertEquals(2, citiesInRange.size());
        Assert.assertTrue(citiesInRange.contains("Moscow"));
        Assert.assertTrue(citiesInRange.contains("Los Angeles"));
    }

    @Test
    public void givenMultipleCitiesInTwoJumpsRangeReturnAllCitiesInList(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Tokyo", "Los Angeles");
        teleporterManager.addRoute("Tokyo", "Las Vegas");
        teleporterManager.addRoute("Los Angeles", "Moscow");
        teleporterManager.addRoute("Newark", "Las Vegas");
        teleporterManager.addRoute("Houghton", "Newark");

        List<String> citiesInRange = teleporterManager.citiesInRange("Tokyo", 2);

        Assert.assertEquals(4, citiesInRange.size());
        Assert.assertTrue(citiesInRange.contains("Moscow"));
        Assert.assertTrue(citiesInRange.contains("Los Angeles"));
        Assert.assertTrue(citiesInRange.contains("Newark"));
        Assert.assertTrue(citiesInRange.contains("Las Vegas"));
    }

    @Test
    public void givenMultipleCitiesInFourJumpsRangeReturnAllThreeJumpCitiesInList(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Tokyo", "Los Angeles");
        teleporterManager.addRoute("Los Angeles", "Moscow");
        teleporterManager.addRoute("Newark", "Moscow");
        teleporterManager.addRoute("Houghton", "Newark");

        List<String> citiesInRange = teleporterManager.citiesInRange("Tokyo", 3);

        Assert.assertEquals(3, citiesInRange.size());
        Assert.assertTrue(citiesInRange.contains("Moscow"));
        Assert.assertTrue(citiesInRange.contains("Los Angeles"));
        Assert.assertTrue(citiesInRange.contains("Newark"));
    }
}
