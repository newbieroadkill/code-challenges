package teleporter.manager;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class TeleporterManagementService implements TeleporterManager {

    private HashSet<String> cities = new HashSet<>();

    @Override
    public void addRoute(String city1, String city2) {
         cities.add(city1);
         cities.add(city2);
    }

    @Override
    public List<String> citiesInRange(String startingCity, int numOfJumps) {
        List<String> citiesInRange = new ArrayList<>();

        if(cities.contains(startingCity)){
            citiesInRange.addAll(cities);
            citiesInRange.remove(startingCity);
        }
        
        return citiesInRange;
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