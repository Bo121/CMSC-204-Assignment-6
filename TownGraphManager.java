import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph graph;
	ArrayList<String> allRoads;
	ArrayList<String> allTowns;
	
	public TownGraphManager(){
		graph = new Graph();
		allRoads = new ArrayList<String>();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
	    Town source = this.getTown(town1);
	    Town dest = this.getTown(town2);

	    if (source == null || dest == null) {
	        return false; 
	    }

	    if (!this.graph.containsEdge(source, dest)) {
	        this.graph.addEdge(source, dest, weight, roadName);
	        this.allRoads.add(roadName);
	    }

	    return true;
	}
	
	@Override
	public ArrayList<String> allRoads() {
		allRoads = new ArrayList<>();
	    Road[] roads = this.graph.edgeSet().toArray(new Road[0]);
	    Arrays.sort(roads, Comparator.comparing(Road::getName));

	    for (Road road : roads) {
	        allRoads.add(road.getName());
	    }

	    return allRoads;
	}
	
	@Override
	public ArrayList<String> allTowns() {
		allTowns = new ArrayList<>();
	    Set<Town> townSet = this.graph.vertexSet();
	    for (Town town : townSet) {
	    	allTowns.add(town.getName());
	    }

	    Collections.sort(allTowns, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Collator.getInstance().compare(s1, s2);
			}
		});
	    
	    return allTowns;
	}

	@Override
	public String getRoad(String town1, String town2) {
		Road result = this.graph.getEdge(new Town(town1), new Town(town2));
				
		return result == null ? "" : result.getName();
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		Town town = new Town(name);
		return town;
	}

	@Override
	public boolean containsTown(String v) {
		return this.graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		 Town t1 = this.getTown(town1);
		    Town t2 = this.getTown(town2);
		    if (t1 == null || t2 == null) {
		        return false;
		    }
		    return this.graph.containsEdge(t1, t2);
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town source = this.getTown(town1);
	    Town destination = this.getTown(town2);
		
	    if (source == null || destination == null) {
	        return false;
	    }
	    if(this.graph.removeEdge(source, destination, -1, road) == null)
	    	return false;
	    
	    return true;
	}

	@Override
	public boolean deleteTown(String v) {
		Town town = this.getTown(v);
	    if (town == null) {
	        return false;
	    }

	    this.graph.removeVertex(town);
	    return true;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return null;
	}

	public void populateTownGraph(File selectedFile) throws IOException {	
		List<String> lines = Files.readAllLines(selectedFile.toPath());

		for (String line : lines) {
			String[] parts = line.split(";");
			
			String roadName = parts[0].split(",")[0];
			int weight = Integer.parseInt(parts[0].split(",")[1]);
			String source = parts[1].trim();
			String destination = parts[2].trim();

			addTown(source);
			addTown(destination);
			addRoad(source, destination, weight, roadName);
		}
	}
}
