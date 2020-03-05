# LeetCode
There are algorithms and solutions



import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.*;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{ 
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	ArrayList<Integer> IDsOfSongs(int rideDuration, 
	                              ArrayList<Integer> songDurations)
	{
	    List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < songDurations.size(); ++i) {
        	for (int j = 0; j < songDurations.size(); ++j) {
        	    if(i == j) continue;
        	    
                pairs.add(new Pair(i,j, songDurations.get(i)>songDurations.get(j) ? songDurations.get(i) : songDurations.get(j), songDurations.get(i) + songDurations.get(j)));
                
                                
        	}
		}
		
		 Optional<ArrayList<Integer>> result = pairs.stream()
                .filter(i->(rideDuration - i.sum == 30))
                .sorted((o1,o2) -> o2.max.compareTo(o1.max))
                .findFirst()
                .map(i-> {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i.song1);
                    list.add(i.song2);
                    return list;
                });


        return result.get();
	}
	
}

class Pair {
    public Integer song1;
    public Integer song2;
    public Integer max;
    public Integer sum;
    
    public Pair(Integer song1, Integer song2, Integer max, Integer sum) {
        this.song1 = song1;
        this.song2 = song2;
        this.max = max;
        this.sum = sum;
    }
}




// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int getMinimumCostToRepair(int numTotalAvailableCities, 
                               int numTotalAvailableRoads, 
                               List<List<Integer>> roadsAvailable, 
                               int numRoadsToBeRepaired, 
                               List<List<Integer>> costRoadsToBeRepaired)
	{
       //calculate all combinations of costRoadsToBeRepaired
       List<Combintion> combinations = combination(costRoadsToBeRepaired, numRoadsToBeRepaired);
   
       //sort all combination by sumCost ASC
       
       //roadsPossibleToUse = roadsAvailable - roadsTobeRapaired
       
       //add one combination to roadsPossibleToUse 
       //until all cities will be connected
       
       //return sum of added combination cost
    }
    
    public boolean areAllCitiesConnected(List<List<Integer>> roads) {
        //TODO: impl it
        return true;
    }
    
    public List<Combination> combination(List<List<Integer>> costRoadsToBeRepaired, int num) {
        
            List<Combination> list = new ArrayList<>();
            for(List<Integer> item: costRoadsToBeRepaired) {
                list.add(new Combination(item), item.get(2));
            }
            return list;
        
        
        
    }
    
    public List<Combination> combination(List<Combination> combinations, List<List<Integer>> costRoadsToBeRepaired, int num) {
        
            List<Combination> list = new ArrayList<>();
            for(List<Integer> item: costRoadsToBeRepaired) {
                for(List)
                list.add(new Combination(item), item.get(2));
            }
            return list;
        
        
        
    }
}

class Combination {
    public List<List<Integer>> cities;
    public Integer sumCost;
    
    public Combination(List<List<Integer>> cities, Integer sumCost) {
        this.cities = cities;
        this.sumCost = sumCost;
    }
}

