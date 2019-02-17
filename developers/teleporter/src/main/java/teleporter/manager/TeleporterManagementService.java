package teleporter.manager;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeleporterManagementService implements TeleporterManager {


    @Override
    public void addRoute(String city1, String city2) {

    }

    @Override
    public List<String> citiesInRange(String startingCity, int numOfJumps) {
        return null;
    }

    @Override
    public boolean cityReachable(String startingCity, String destinationCity) {
        return false;
    }

    @Override
    public boolean loopPoosible(String startingCity) {
        return false;
    }
}