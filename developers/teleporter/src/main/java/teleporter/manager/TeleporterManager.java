package teleporter.manager;


import java.util.List;

public interface TeleporterManager {
    public void addRoute(String city1, String city2);

    public List<String> citiesInRange(String startingCity, int numOfJumps);

    public boolean cityReachable(String startingCity, String destinationCity);

    public boolean loopPoosible(String startingCity);
}