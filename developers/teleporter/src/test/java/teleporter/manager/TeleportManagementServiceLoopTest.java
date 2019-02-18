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

    @Test
    public void givenLoopDoesNotExistReturnFalse(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Kiev", "Moscow");
        teleporterManager.addRoute("Warsaw", "Moscow");
        teleporterManager.addRoute("Warsaw", "Baltimore");

        boolean loopExists = teleporterManager.loopPoosible("Kiev");

        Assert.assertFalse(loopExists);
    }

    @Test
    public void givenLoopDoesNotExistWithMulitlpeDestinationsReturnFalse(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Kiev", "Moscow");
        teleporterManager.addRoute("Kiev", "Tokyo");
        teleporterManager.addRoute("Warsaw", "Moscow");
        teleporterManager.addRoute("Warsaw", "Baltimore");
        
        boolean loopExists = teleporterManager.loopPoosible("Kiev");

        Assert.assertFalse(loopExists);
    }
    
    @Test
    public void givenLargeLoopExistsReturnTrue(){
        TeleporterManager teleporterManager = new TeleporterManagementService();
        teleporterManager.addRoute("Kiev", "Moscow");
        teleporterManager.addRoute("Warsaw", "Moscow");
        teleporterManager.addRoute("Warsaw", "Tokyo");
        teleporterManager.addRoute("Warsaw", "Baltimore");
        teleporterManager.addRoute("Baltimore", "Seattle");
        teleporterManager.addRoute("Seattle", "Chinasi");
        teleporterManager.addRoute("Chinasi", "Kiev");

        boolean loopExists = teleporterManager.loopPoosible("Warsaw");

        Assert.assertTrue(loopExists);
    }
}
