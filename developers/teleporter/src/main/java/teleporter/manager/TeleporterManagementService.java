package teleporter.manager;


import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeleporterManagementService implements TeleporterManager {

    private Map<String, Set<String>> cityToDestinationsMap = new HashMap<>();

    @Override
    public void addRoute(String city1, String city2) {
         addCityToCityRoute(city1, city2);
         addCityToCityRoute(city2, city1);
    }

    private void addCityToCityRoute(String startingCity, String destinationCity){
        if(cityToDestinationsMap.containsKey(startingCity)){
            cityToDestinationsMap.get(startingCity).add(destinationCity);
        } else {
            Set<String> destinations = new HashSet<>();
            destinations.add(destinationCity);
            cityToDestinationsMap.put(startingCity, destinations);
        }
    }

    @Override
    public List<String> citiesInRange(String startingCity, int numOfJumps) {
        List<String> citiesInRange = new ArrayList<>();

        if(cityToDestinationsMap.get(startingCity) != null) {
            citiesInRange.addAll(cityToDestinationsMap.get(startingCity));
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

