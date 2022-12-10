


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManager_STUDENT_Test {
	private TownGraphManager townGraphManager;

	@Before
	public void setUp() throws Exception {
		townGraphManager = new TownGraphManager();
	}
	
	@After
	public void tearDown() throws Exception {
		townGraphManager = null;
	}

	@Test
	public void testAddRoad() {
		assertTrue(townGraphManager.addRoad("Town_1", "Town_2", 10, "Road_1"));
	}

	@Test
	public void testAllRoads() {
		assertTrue(townGraphManager.addRoad("Town_1", "Town_2", 10, "Road_1"));
		assertTrue(townGraphManager.addRoad("Town_2", "Town_3", 10, "Road_2"));
		List<String> expectedRoads = new ArrayList<>();
		expectedRoads.add("Road_1");
		expectedRoads.add("Road_2");
		assertEquals(expectedRoads, townGraphManager.allRoads());
	}

	@Test
	public void testAllTowns() {
		assertTrue(townGraphManager.addTown("Town_1"));
		assertTrue(townGraphManager.addTown("Town_2"));
		List<String> expectedTowns = new ArrayList<>();
		expectedTowns.add("Town_1");
		expectedTowns.add("Town_2");
		assertEquals(expectedTowns, townGraphManager.allTowns());
	}

	@Test
	public void testGetRoad() {
		assertTrue(townGraphManager.addRoad("Town_1", "Town_2", 10, "Road_1"));
		assertEquals("Road_1", townGraphManager.getRoad("Town_1", "Town_2"));
		assertEquals("", townGraphManager.getRoad("Town_1", "Town_3"));
	}

	@Test
	public void testAddTown() {
		assertTrue(townGraphManager.addTown("Town_1"));
		assertFalse(townGraphManager.addTown("Town_1"));
	}

	@Test
	public void testGetTown() {
		assertTrue(townGraphManager.addTown("Town_1"));
		assertEquals("Town_1", townGraphManager.getTown("Town_1").getName());
	}
	
	@Test
	public void testContainsTown() {
		assertTrue(townGraphManager.addTown("Town_1"));
		assertTrue(townGraphManager.containsTown("Town_1"));
		assertFalse(townGraphManager.containsTown("Town_2"));
	}
	@Test
	public void testContainsRoadConnection() {
		assertTrue(townGraphManager.addTown("Town_1"));
		assertTrue(townGraphManager.addTown("Town_2"));
		assertTrue(townGraphManager.addRoad("Town_1", "Town_2", 1, "Road_1"));
		assertTrue(townGraphManager.containsRoadConnection("Town_1", "Town_2"));
		assertFalse(townGraphManager.containsRoadConnection("Town_1", "Town_3"));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertTrue(townGraphManager.addTown("Town_1"));
		assertTrue(townGraphManager.addTown("Town_2"));
		assertTrue(townGraphManager.addRoad("Town_1", "Town_2", 1, "Road_1"));
	}

	@Test
	public void testDeleteTown() {
		assertTrue(townGraphManager.addTown("Town_1"));
		assertTrue(townGraphManager.addTown("Town_2"));
		assertTrue(townGraphManager.addRoad("Town_1", "Town_2", 1, "Road_1"));
		assertTrue(townGraphManager.deleteTown("Town_2"));
		assertFalse(townGraphManager.containsTown("Town_2"));
	}
}
