import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Road_STUDENT_Test {

    @Test
    public void testContains() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        Road road = new Road(town1, town2, 10, "Road 1");
        assertTrue(road.contains(town1));
        assertTrue(road.contains(town2));
        assertFalse(road.contains(new Town("Town 3")));
    }

    @Test
    public void testGetName() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        Road road = new Road(town1, town2, 10, "Road 1");
        assertEquals("Road 1", road.getName());
    }

    @Test
    public void testGetDestination() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        Road road = new Road(town1, town2, 10, "Road 1");
        assertEquals(town2, road.getDestination());
    }

    @Test
    public void testGetSource() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        Road road = new Road(town1, town2, 10, "Road 1");
        assertEquals(town1, road.getSource());
    }

    @Test
    public void testGetWeight() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        Road road = new Road(town1, town2, 10, "Road 1");
        assertEquals(10, road.getWeight());
    }

    @Test
    public void testEquals() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        Town town3 = new Town("Town 3");
        Road road1 = new Road(town1, town2, 10, "Road 1");
        Road road2 = new Road(town2, town1, 10, "Road 1");
        Road road3 = new Road(town1, town3, 10, "Road 1");
        assertTrue(road1.equals(road2));
        assertFalse(road1.equals(road3));
    }

    @Test
    public void testCompareTo() {
        Town town1 = new Town("Town 1");
        Town town2 = new Town("Town 2");
        Road road1 = new Road(town1, town2, 10, "Road 1");
        Road road2 = new Road(town2, town1, 10, "Road 2");
        assertEquals(-1, road1.compareTo(road2));
    }

}