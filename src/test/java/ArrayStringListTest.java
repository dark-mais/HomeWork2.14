import org.example.ArrayStringList;
import org.example.StringList;
import org.example.exception.InvalidIndexException;
import org.example.exception.NullElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ArrayStringListTest {
    private ArrayStringList list;

    @BeforeEach
    void setUp() {
        list = new ArrayStringList(10);
    }

    @Test
    void testAdd() {
        assertEquals("Hello", list.add("Hello"));
        assertEquals("World", list.add("World"));
        assertArrayEquals(new String[]{"Hello", "World"}, list.toArray());
    }

    @Test
    void testAddAtIndex() {
        list.add("Hello");
        list.add("World");
        assertEquals("Java", list.add(1, "Java"));
        assertArrayEquals(new String[]{"Hello", "Java", "World"}, list.toArray());
    }

    @Test
    void testSet() {
        list.add("Hello");
        list.add("World");
        assertEquals("World", list.set(1, "Java"));
        assertArrayEquals(new String[]{"Hello", "Java"}, list.toArray());
    }

    @Test
    void testRemoveItem() {
        list.add("Hello");
        list.add("World");
        assertEquals("World", list.remove("World"));
        assertArrayEquals(new String[]{"Hello"}, list.toArray());
    }

    @Test
    void testRemoveAtIndex() {
        list.add("Hello");
        list.add("World");
        assertEquals("World", list.remove(1));
        assertArrayEquals(new String[]{"Hello"}, list.toArray());
    }

    @Test
    void testContains() {
        list.add("Hello");
        assertTrue(list.contains("Hello"));
        assertFalse(list.contains("World"));
    }

    @Test
    void testIndexOf() {
        list.add("Hello");
        list.add("World");
        assertEquals(1, list.indexOf("World"));
        assertEquals(-1, list.indexOf("Java"));
    }

    @Test
    void testLastIndexOf() {
        list.add("Hello");
        list.add("World");
        list.add("Hello");
        assertEquals(2, list.lastIndexOf("Hello"));
        assertEquals(-1, list.lastIndexOf("Java"));
    }

    @Test
    void testGet() {
        list.add("Hello");
        list.add("World");
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
    }

    @Test
    void testEquals() {
        list.add("Hello");
        list.add("World");

        StringList otherList = new ArrayStringList(10);
        otherList.add("Hello");
        otherList.add("World");

        assertTrue(list.equals(otherList));

        otherList.add("Java");
        assertFalse(list.equals(otherList));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add("Hello");
        assertEquals(1, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("Hello");
        assertFalse(list.isEmpty());
    }

    @Test
    void testClear() {
        list.add("Hello");
        list.add("World");
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void testToArray() {
        list.add("Hello");
        list.add("World");
        assertArrayEquals(new String[]{"Hello", "World"}, list.toArray());
    }

    @Test
    void testAddNullThrowsException() {
        assertThrows(NullElementException.class, () -> list.add(null));
    }

    @Test
    void testAddAtIndexNullThrowsException() {
        assertThrows(NullElementException.class, () -> list.add(0, null));
    }

    @Test
    void testSetNullThrowsException() {
        list.add("Hello");
        assertThrows(NullElementException.class, () -> list.set(0, null));
    }

    @Test
    void testRemoveNullThrowsException() {
        assertThrows(NullElementException.class, () -> list.remove((String) null));
    }

    @Test
    void testGetIndexOutOfBoundsThrowsException() {
        assertThrows(InvalidIndexException.class, () -> list.get(0));
    }

    @Test
    void testRemoveIndexOutOfBoundsThrowsException() {
        assertThrows(InvalidIndexException.class, () -> list.remove(0));
    }

    @Test
    void testSetIndexOutOfBoundsThrowsException() {
        assertThrows(InvalidIndexException.class, () -> list.set(0, "Test"));
    }

    @Test
    void testAddAtIndexOutOfBoundsThrowsException() {
        assertThrows(InvalidIndexException.class, () -> list.add(1, "Test"));
    }

    @Test
    void testEnsureCapacity() {
        list = Mockito.spy(new ArrayStringList(2));
        list.add("Hello");
        list.add("World");
        list.add("Java");

        verify(list, times(1)).ensureCapacity();
    }
}
