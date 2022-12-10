import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 * Class: CMSC204
 *  Program: Assignment #6
 *  Instructor: Dr. Kuijt
 * Description: The implementation of hash table and the use of linked list 
 * Due: 12/10/2022 
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Shengquan Yang
*/
public class Graph implements GraphInterface<Town, Road> {
	
	private ArrayList<LinkedList<Town>> aList;
	private Set<Town> TownSet;
	private Set<Road> RoadSet;
	private Map<Town, Integer> distances;
	private Map<Town, Town> previous;
	

	public Graph(){
		aList = new ArrayList<>();
		TownSet = new HashSet<>();
		RoadSet = new HashSet<>();
	}
	
	@Override
	public boolean addVertex(Town v) {	
		for(LinkedList<Town> list : aList){
			if(list.getFirst().getName().equals(v.getName())) {
				return false;
			}
		}
		LinkedList<Town> list = new LinkedList<>();
		list.add(v);
		aList.add(list);
		TownSet.add(v);
		
		return true;
	}
	
	@Override
	public boolean containsVertex(Town v) {
		for(LinkedList<Town> list : aList){
			if(list.getFirst().getName().equals(v.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
	    if (containsEdge(sourceVertex, destinationVertex)) {
	        return null;
	    }

	    Road road = new Road(destinationVertex,sourceVertex, weight, description);
	    RoadSet.add(road);

	    for(int i = 0; i < aList.size(); i++) {
	        if(aList.get(i).get(0).getName().equals(sourceVertex.getName())) {
	            LinkedList<Town> list = aList.get(i);
	            list.add(destinationVertex);
	        }
	    }
	    return road;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for(int i = 0; i < aList.size(); i++) {
			if(aList.get(i).get(0).getName().equals(sourceVertex.getName())){
				LinkedList<Town> list = aList.get(i);
				for(Town town1 : list) {
					if(town1.equals(destinationVertex)) {
						return true;
					}
				}
			}
		}
		return false;	
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (Road r : RoadSet) {
			if (r.contains(sourceVertex) && r.contains(destinationVertex)) {
				return r;
			}
		}
		return null;
	}
	
	@Override
	public Set<Road> edgeSet() {
		return RoadSet;
	}

	@Override
	public Set<Town> vertexSet() {
		return TownSet;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		
		Set<Road> edges = new HashSet<>();
	    for (Road road : RoadSet) {
	        if (road.getSource().equals(vertex) || road.getDestination().equals(vertex)) {
	            edges.add(road);
	        }
	    }
	    return edges;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		for(LinkedList<Town> list : aList){
			if(list.getFirst().getName().equals(sourceVertex.getName())) {
				if(list.contains(destinationVertex)){
					list.remove(destinationVertex);
				}
			}
		}
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		if(RoadSet.contains(road)) {
			RoadSet.remove(road);
			return road;
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		for(LinkedList<Town> list : aList){
			if(list.getFirst().getName().equals(v.getName())) {
				aList.remove(list);
				return true;
			}
		}
		return false;	
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
	    
	    //create a list to store the path
	    ArrayList<String> path = new ArrayList<>();
	    
	    //add the destination vertex to the path
	    path.add(destinationVertex.getName());
	    
	    //get the previous vertex in the path
	    Town prev = previous.get(destinationVertex);
	    
	    //iterate through the previous vertices and add them to the path
	    while (prev != null) {
	        path.add(prev.getName());
	        prev = previous.get(prev);
	    }
	    
	    //reverse the path and return it
	    Collections.reverse(path);
	    return path;
		
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		//initialize the distance and previous map
		distances = new HashMap<>();
		previous = new HashMap<>();

		//initialize all distances as infinity
	    for (Town town : TownSet) {
	        distances.put(town, Integer.MAX_VALUE);
	    }
	    
	    //set the distance of the source vertex to 0
	    distances.put(sourceVertex, 0);
	    
	    //create a set to store unvisited vertices
	    Set<Town> unvisited = new HashSet<>();
	    unvisited.addAll(TownSet);
	    
	    //while there are unvisited vertices
	    while (!unvisited.isEmpty()) {
	        //get the vertex with the minimum distance
	        Town current = getMinimum(unvisited);
	        unvisited.remove(current);
	        
	        //iterate through all neighboring vertices of the current vertex
	        for (Town neighbor : getNeighbors(current)) {
	            //calculate the distance to the neighbor vertex
	            int distance = distances.get(current) + getEdge(current, neighbor).getWeight();
	            
	            //if the calculated distance is less than the current distance of the neighbor vertex, update it
	            if (distance < distances.get(neighbor)) {
	                distances.put(neighbor, distance);
	                previous.put(neighbor, current);
	            }
	        }
	    }
		
	}
	
	private Town getMinimum(Set<Town> unvisited) {
	    Town minimum = null;
	    int minDistance = Integer.MAX_VALUE;
	    for (Town town : unvisited) {
	        if (distances.get(town) < minDistance) {
	            minimum = town;
	            minDistance = distances.get(town);
	        }
	    }
	    return minimum;
	}
	
	private Set<Town> getNeighbors(Town vertex) {
	    Set<Town> neighbors = new HashSet<>();
	    for (Road road : edgeSet()) {
	        if (road.contains(vertex)) {
	            neighbors.add(road.getDestination());
	        }
	    }
	    return neighbors;
	}
}
