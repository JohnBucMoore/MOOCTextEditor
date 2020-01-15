/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

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
	public void testGet()
	{
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
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		String b = shortList.remove(1);
		assertEquals("Remove: check b is correct ", "B", b);
		assertEquals("Remove: check element 0 is correct ", "A", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			shortList.add(null);
			fail("Check null pointer");
		} catch (NullPointerException e) {
			
		}
		try {
			list1.add(null);
			fail("Check null pointer");
		} catch (NullPointerException e) {
			
		}
		shortList.add("C");
		String C = shortList.get(2);
		assertEquals("Add: check C is correct ", "C", C);
		list1.add(84);
		int a = list1.get(3);
		assertEquals("Add: check a is correct ", 84, a);
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		int a = emptyList.size();
		assertEquals("Size: check a is correct ", 0, a);
		int b = shortList.size();
		assertEquals("Size: check b is correct ", 2, b);
		int c = longerList.size();
		assertEquals("Size: check c is correct ", 10, c);
		int d = list1.size();
		assertEquals("Size: check d is correct ", 3, d);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			shortList.add(1, null);
			fail("Check null pointer");
		} catch (NullPointerException e) {
			
		}
		try {
			shortList.add(-1, "D");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.add(3, "D");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.add(1, null);
			fail("Check null pointer");
		} catch (NullPointerException e) {
			
		}
		try {
			list1.add(-1, 99);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.add(5, 99);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		shortList.add(1, "D");
		String D = shortList.get(1);
		assertEquals("Add: check D is correct ", "D", D);
		list1.add(2, 35); // list1 should be 65, 21, 35, 42
		int a = list1.get(2);
		assertEquals("Add: check a is correct ", 35, a);
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    try {
	    	emptyList.set(0, 42);
	    	fail("Check out of bounds");
	    } catch (IndexOutOfBoundsException e) {
	    	
	    }
	    try {
	    	shortList.set(1, null);
	    	fail("Check null pointer");
	    } catch (NullPointerException e) {
	    	
	    }
	    try {
	    	list1.set(3,84);
	    	fail("Check out of bounds");
	    } catch (IndexOutOfBoundsException e) {
	    	
	    }
	    try {
	    	list1.set(2,null);
	    	fail("Check null pointer");
	    } catch (NullPointerException e) {
	    	
	    }
	    
	    int a = list1.set(1,12);
	    assertEquals("Check a is correct ", 21, a);
	    assertEquals("Check size is correct ", 3, list1.size());
	    assertEquals("Check data is correct ", (Integer)12, list1.get(1));
	    
	    String b = shortList.set(1, "C");
	    assertEquals("Check b is correct ", "B", b);
	    assertEquals("Check size is correct ", 2, shortList.size());
	    assertEquals("Check data is correct ", "C", shortList.get(1));
	}
	
	
	// TODO: Optionally add more test methods.
	
}
