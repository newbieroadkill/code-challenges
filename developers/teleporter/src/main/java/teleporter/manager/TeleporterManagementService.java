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

        if(cityToDestinationsMap.containsKey(startingCity)){
            reachableCitiesWithJumpLimit(startingCity, citiesInRange, numOfJumps);
            citiesInRange.remove(startingCity);
        }

        return new ArrayList<>(citiesInRange);
    }

    @Override
    public boolean cityReachable(String startingCity, String destinationCity) {
        if(cityToDestinationsMap.get(startingCity) == null || cityToDestinationsMap.get(destinationCity) == null){
            return false;
        }

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

    @Override
    public boolean loopPoosible(String startingCity) {
        return false;
    }
}

