package teleporter.manager;

import org.junit.Assert;
import org.junit.Test;


public class TeleporterManagementServiceReachableTest {

    @Test
    public void givenReachableCityInOneJumpReturnTrue(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Kiev", "Moscow");

        boolean canReachCity = teleporterManager.cityReachable("Kiev", "Moscow");

        Assert.assertTrue(canReachCity);
    }
}
