package teleporter.manager;

import org.junit.Assert;
import org.junit.Test;

public class TeleportManagementServiceLoopTest {

    @Test
    public void givenTightLoopReturnTrue(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Kiev", "Moscow");
        teleporterManager.addRoute("Warsaw", "Moscow");
        teleporterManager.addRoute("Warsaw", "Kiev");

        boolean loopExists = teleporterManager.loopPoosible("Kiev");

        Assert.assertTrue(loopExists);
    }

}
