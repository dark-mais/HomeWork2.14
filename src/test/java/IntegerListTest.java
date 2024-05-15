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
    void testAddAndGet() {
        assertEquals("1", integerList.add("1"));
        assertEquals("1", integerList.get(0));
        assertEquals(1, integerList.size());
    }

    @Test
    void testAddAtIndex() {
        integerList.add("1");
        integerList.add(0, "2");
        assertEquals("2", integerList.get(0));
        assertEquals("1", integerList.get(1));
        assertEquals(2, integerList.size());
    }

    @Test
    void testSet() {
        integerList.add("1");
        assertEquals("1", integerList.set(0, "2"));
        assertEquals("2", integerList.get(0));
    }

    @Test
    void testRemoveByItem() {
        integerList.add("1");
        assertEquals("1", integerList.remove("1"));
        assertEquals(0, integerList.size());
    }

    @Test
    void testRemoveByIndex() {
        integerList.add("1");
        assertEquals("1", integerList.remove(0));
        assertEquals(0, integerList.size());
    }

    @Test
    void testContains() {
        integerList.add("1");
        integerList.add("2");

        // Настраиваем мок для вызова сортировки
        doAnswer(invocation -> {
            Integer[] array = invocation.getArgument(0);
            Arrays.sort(array, 0, integerList.size());
            return null;
        }).when(mockSortable).sort(any(Integer[].class), anyInt());

        assertTrue(integerList.contains("1"));
        assertTrue(integerList.contains("2"));
        assertFalse(integerList.contains("3"));
    }

    @Test
    void testIndexOf() {
        integerList.add("1");
        integerList.add("2");
        assertEquals(0, integerList.indexOf("1"));
        assertEquals(1, integerList.indexOf("2"));
        assertEquals(-1, integerList.indexOf("3"));
    }

    @Test
    void testLastIndexOf() {
        integerList.add("1");
        integerList.add("2");
        integerList.add("1");
        assertEquals(2, integerList.lastIndexOf("1"));
        assertEquals(1, integerList.lastIndexOf("2"));
        assertEquals(-1, integerList.lastIndexOf("3"));
    }

    @Test
    void testEquals() {
        IntegerList anotherList = new IntegerList(mockSortable);
        integerList.add("1");
        anotherList.add("1");
        assertTrue(integerList.equals(anotherList));

        anotherList.add("2");
        assertFalse(integerList.equals(anotherList));
    }

    @Test
    void testSizeAndIsEmpty() {
        assertEquals(0, integerList.size());
        assertTrue(integerList.isEmpty());

        integerList.add("1");
        assertEquals(1, integerList.size());
        assertFalse(integerList.isEmpty());
    }

    @Test
    void testClear() {
        integerList.add("1");
        integerList.clear();
        assertEquals(0, integerList.size());
        assertTrue(integerList.isEmpty());
    }

    @Test
    void testToArray() {
        integerList.add("1");
        integerList.add("2");
        String[] array = integerList.toArray();
        assertArrayEquals(new String[]{"1", "2"}, array);
    }

    @Test
    void testGrow() {
        for (int i = 0; i < 15; i++) {
            integerList.add(String.valueOf(i));
        }
        assertEquals(15, integerList.size());
        assertEquals("14", integerList.get(14));
    }
}
