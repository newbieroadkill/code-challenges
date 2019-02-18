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
        if(cityToDestinationsMap.get(startingCity) == null || cityToDestinationsMap.get(destinationCity) == null){
            return false;
        }
        // return cityReachable(startingCity, destinationCity, new HashSet<String>());
        Set<String> reachableCities = new HashSet<>();
        reachableCitiesWithJumpLimit(startingCity, reachableCities, cityToDestinationsMap.size() -1);
        return reachableCities.contains(destinationCity);
    }

    private void reachableCitiesWithJumpLimit(String currentCity, Set reachableCities, int numOfJumps){
        if(reachableCities.containsAll(cityToDestinationsMap.get(currentCity))){
            return;
        }

        reachableCities.addAll(cityToDestinationsMap.get(currentCity));

        if(numOfJumps > 1) {
            for(String city : cityToDestinationsMap.get(currentCity)){
                reachableCitiesWithJumpLimit(city, reachableCities, numOfJumps - 1);
            }
        }
    }

    private boolean cityReachable(String startingCity, String destinationCity, Set visitedCities) {
        boolean foundDestinationCity = false;
        if (cityToDestinationsMap.get(startingCity).contains(destinationCity)) {
            foundDestinationCity = true;
        } else if (visitedCities.containsAll(cityToDestinationsMap.get(startingCity))) {
            foundDestinationCity = false;
        } else {
            visitedCities.addAll(cityToDestinationsMap.get(startingCity));
            for (String city : cityToDestinationsMap.get(startingCity)) {
                foundDestinationCity = foundDestinationCity || cityReachable(city, destinationCity, visitedCities);
            }
        }
        return foundDestinationCity;
    }

    @Override
    public boolean loopPoosible(String startingCity) {
        return false;
    }
}

