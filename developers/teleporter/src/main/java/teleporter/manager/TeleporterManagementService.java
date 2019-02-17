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
        Set<String> citiesInRange = new HashSet<>();

        if(cityToDestinationsMap.get(startingCity) != null) {
            citiesInRange.addAll(cityToDestinationsMap.get(startingCity));
            if(numOfJumps > 1){
                for(String city : cityToDestinationsMap.get(startingCity)){
                   citiesInRange.addAll(citiesInRange(city, numOfJumps-1));
                }
            }
        }

        citiesInRange.remove(startingCity);
        return new ArrayList<>(citiesInRange);
    }

    @Override
    public boolean cityReachable(String startingCity, String destinationCity) {
        if(cityToDestinationsMap.get(startingCity) == null){
            return false;
        }
        return cityToDestinationsMap.get(startingCity).contains(destinationCity);
    }

    @Override
    public boolean loopPoosible(String startingCity) {
        return false;
    }
}

