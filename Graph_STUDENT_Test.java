import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class Graph_STUDENT_Test {

    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph();
    }

    @Test
    public void testAddVertex() {
        Town town1 = new Town("Town 1");
        assertTrue(graph.addVertex(town1));

        assertFalse(graph.addVertex(town1));
    }

    @Test
    public void testContainsVertex() {
        Town town1 = new Town("Town 1");
        graph.addVertex(town1);
        assertTrue(graph.containsVertex(town1));

        Town town2 = new Town("Town 2");
        assertFalse(graph.containsVertex(town2));
    }

    @Test
    public void testAddEdge() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        Road road1 = graph.addEdge(town1, town2, 10, "Road 1");
        assertNotNull(road1);

        Road road2 = graph.addEdge(town1, town2, 10, "Road 2");
        assertNull(road2);
    }

    @Test
    public void testContainsEdge() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        graph.addEdge(town1, town2, 10, "Road 1");
        assertTrue(graph.containsEdge(town1, town2));

        Town town3 = new Town("Town 3");
        assertFalse(graph.containsEdge(town1, town3));
    }

    @Test
    public void testGetEdge() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        graph.addVertex(town1);
        graph.addVertex(town2);
        Road road1 = graph.addEdge(town1, town2, 10, "Road 1");
        assertEquals(road1, graph.getEdge(town1, town2));

        Town town3 = new Town("Town 3");
        assertNull(graph.getEdge(town1, town3));
    }
    
    @Test
    public void testVertexSet() {
    	Town town1 = new Town("Town 1");
    	Town town2 = new Town("Town 2");
    	Town town3 = new Town("Town 3");
    	graph.addVertex(town1);
    	graph.addVertex(town2);
    	graph.addVertex(town3);
    	Set<Town> expectedSet = new HashSet<>();
    	expectedSet.add(town1);
    	expectedSet.add(town2);
    	expectedSet.add(town3);
    	assertEquals(expectedSet, graph.vertexSet());
    }
}
