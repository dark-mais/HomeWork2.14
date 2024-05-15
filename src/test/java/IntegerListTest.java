import org.example.IntegerList;
import org.example.Sortable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class IntegerListTest {

    private IntegerList integerList;
    private Sortable mockSortable;

    @BeforeEach
    void setUp() {
        mockSortable = mock(Sortable.class);
        integerList = new IntegerList(mockSortable);
    }

    @Test
    void testAdd() {
        assertEquals("10", integerList.add("10"));
        assertEquals(1, integerList.size());
        assertEquals("10", integerList.get(0));
    }

    @Test
    void testAddAtIndex() {
        integerList.add("10");
        integerList.add(0, "5");
        assertEquals(2, integerList.size());
        assertEquals("5", integerList.get(0));
        assertEquals("10", integerList.get(1));
    }

    @Test
    void testSet() {
        integerList.add("10");
        integerList.add("20");
        assertEquals("10", integerList.set(0, "5"));
        assertEquals("5", integerList.get(0));
        assertEquals("20", integerList.get(1));
    }

    @Test
    void testRemove() {
        integerList.add("10");
        integerList.add("20");
        assertEquals("10", integerList.remove("10"));
        assertEquals(1, integerList.size());
        assertEquals("20", integerList.get(0));
    }

    @Test
    void testRemoveAtIndex() {
        integerList.add("10");
        integerList.add("20");
        assertEquals("10", integerList.remove(0));
        assertEquals(1, integerList.size());
        assertEquals("20", integerList.get(0));
    }

    @Test
    void testContains() {
        integerList.add("10");
        integerList.add("20");
        doAnswer(invocation -> {
            Integer[] array = invocation.getArgument(0);
            Arrays.sort(array, 0, integerList.size());
            return null;
        }).when(mockSortable).sort(any(Integer[].class), anyInt());
        assertTrue(integerList.contains("10"));
        assertFalse(integerList.contains("30"));
    }

    @Test
    void testIndexOf() {
        integerList.add("10");
        integerList.add("20");
        assertEquals(0, integerList.indexOf("10"));
        assertEquals(1, integerList.indexOf("20"));
        assertEquals(-1, integerList.indexOf("30"));
    }

    @Test
    void testLastIndexOf() {
        integerList.add("10");
        integerList.add("20");
        integerList.add("10");
        assertEquals(2, integerList.lastIndexOf("10"));
        assertEquals(1, integerList.lastIndexOf("20"));
        assertEquals(-1, integerList.lastIndexOf("30"));
    }

    @Test
    void testGet() {
        integerList.add("10");
        assertEquals("10", integerList.get(0));
    }

    @Test
    void testEquals() {
        IntegerList otherList = new IntegerList(mockSortable);
        integerList.add("10");
        otherList.add("10");
        assertTrue(integerList.equals(otherList));
        otherList.add("20");
        assertFalse(integerList.equals(otherList));
    }

    @Test
    void testSize() {
        assertEquals(0, integerList.size());
        integerList.add("10");
        assertEquals(1, integerList.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(integerList.isEmpty());
        integerList.add("10");
        assertFalse(integerList.isEmpty());
    }

    @Test
    void testClear() {
        integerList.add("10");
        integerList.add("20");
        integerList.clear();
        assertEquals(0, integerList.size());
        assertTrue(integerList.isEmpty());
    }

    @Test
    void testToArray() {
        integerList.add("10");
        integerList.add("20");
        String[] array = integerList.toArray();
        assertArrayEquals(new String[]{"10", "20"}, array);
    }
}
