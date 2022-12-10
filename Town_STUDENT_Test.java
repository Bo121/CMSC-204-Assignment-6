import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

public class Town_STUDENT_Test {

    private Town[] town;

    @Before
    public void setUp() throws Exception {
        town = new Town[12];
        for (int i = 1; i < 12; i++) {
            town[i] = new Town("Town_" + i);
        }
    }

    @Test
    public void testName() {
        assertEquals("Town_1", town[1].getName());
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, town[1].compareTo(town[1]));
        assertEquals(1, town[2].compareTo(town[1]));
        assertEquals(-1, town[1].compareTo(town[2]));
    }

    @Test
    public void testEquals() {
        assertTrue(town[1].equals(town[1]));
        assertFalse(town[1].equals(town[2]));
    }
}