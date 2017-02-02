/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}
	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet() {
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

        try {
            emptyList.remove(0);
            fail("Can't remove from empty list");
        }
        catch (IndexOutOfBoundsException e) {
        }

        try {
            emptyList.remove(-1);
            fail("Can't remove from empty list");
        }
        catch (IndexOutOfBoundsException e) {
        }

        try {
            shortList.remove(shortList.size());
            fail("Can't remove from empty list");
        }
        catch (IndexOutOfBoundsException e) {
        }
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd() {
		try {
			shortList.add(null);
            fail("No exception for null input");
		}
		catch (NullPointerException e) {
		}

		shortList.add("C");
        assertEquals("C", shortList.get(shortList.size - 1));
	}

	/** Test the size of the list */
	@Test
	public void testSize() {
        int ogSize = shortList.size();
        shortList.add("C");
        assertEquals(ogSize + 1, shortList.size());

        ogSize = shortList.size();
        shortList.add(0, "D");
        assertEquals(ogSize + 1, shortList.size());
	}
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex() {
        try {
            shortList.add(0, null);
            fail("Cannot add node for null data");
        }
        catch (NullPointerException e) {
        }

        try {
            shortList.add(-1, "z");
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

        try {
            shortList.add(shortList.size() + 1, "z");
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

		String oldFirstItem = shortList.get(0);
        String oldSecondItem = shortList.get(1);
        shortList.add(0, "z");
        assertEquals("z", shortList.get(0));
        assertEquals(oldFirstItem, shortList.get(1));
        assertEquals(oldSecondItem, shortList.get(2));

        shortList.add(3, "y");
        assertEquals("y", shortList.get(3));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet() {
        try {
            shortList.set(0, null);
            fail("Cannot modify node for null data");
        }
        catch (NullPointerException e) {
        }

        try {
            shortList.set(-1, "sss");
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

        try {
            shortList.set(shortList.size(), "sss");
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {
        }

        int a = list1.set(0, 99);
        assertEquals("Set: check a is correct ", 65, a);
        assertEquals((Integer)99, list1.get(0));
	}
}
