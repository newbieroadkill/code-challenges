#### Problem Space

This problem seems to be a graph theory type problem.  The queries seem to correlate to the following graph problems:

city X with maximum N jumps - Breadth First Search
can I teleport to a city from another city - Depth First Search
loop possible - Largest Cycle

The public interface for supporting the teleportation mapping and queries is as such:

public void addRoute(String city1, String city2)
public List<String> citiesInRange(String startingCity, int numOfJumps)
public boolean cityReachable(String startingCity, String destinationCity)
public boolean loopPoosible(String startingCity)

Some assumptions being made on this implementation:

If asked if you can get to a city from itself, the answer should be true as you are already there!

There cannot be two teleportation routes between the same two cities.  Duplicate routes on input will be ignored.  This would alter the logic of loopPossible calculation if this is possible.