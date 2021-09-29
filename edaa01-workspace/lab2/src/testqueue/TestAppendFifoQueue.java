package testqueue;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue;
	private FifoQueue<Integer> mySecondIntQueue;
	
	@BeforeEach
	void setUp() throws Exception {
		myIntQueue = new FifoQueue<>();
		mySecondIntQueue = new FifoQueue<>();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		myIntQueue = null;
		mySecondIntQueue = null;
	}
	
	//Två tomma
	@Test
	public void appendEmpty() {
		assertEquals( "Wrong queuesize",myIntQueue.size(), 0);
		assertEquals( "Wrong queuesize",myIntQueue.size(), 0);
		myIntQueue.append(mySecondIntQueue);
	}
	
	// tom med icke-tom
	@Test
	public void appendEmptyWithNonEmpty() {
		myIntQueue.add(5);
        myIntQueue.add(7);
        myIntQueue.add(1);

        myIntQueue.append(mySecondIntQueue);

        assertEquals(3, myIntQueue.size(), "Wrong queuesize");
        assertEquals(0, mySecondIntQueue.size(), "Wrong queuesize");

        assertEquals(5, (int) myIntQueue.poll(), "Element in wrong order");
        assertEquals(7, (int) myIntQueue.poll(), "Element in wrong order");
        assertEquals(1, (int) myIntQueue.poll(), "Element in wrong order");
	}
	
	//Två icketomma
	@Test
	public void twoNonEmpty() {
		myIntQueue.add(1);
        myIntQueue.add(2);
        myIntQueue.add(3);
        mySecondIntQueue.add(1);
        mySecondIntQueue.add(2);
        mySecondIntQueue.add(3);

        myIntQueue.append(mySecondIntQueue);

        assertEquals("Wrong size of queue", 6, myIntQueue.size());
        assertEquals("Wrong size of queue", 0, mySecondIntQueue.size());

        assertEquals("Element in wrong order", 1, (int) myIntQueue.poll());
        assertEquals("Element in wrong order", 2, (int) myIntQueue.poll());
        assertEquals("Element in wrong order", 3, (int) myIntQueue.poll());
        assertEquals("Element in wrong order", 1, (int) myIntQueue.poll());
        assertEquals("Element in wrong order", 2, (int) myIntQueue.poll());
        assertEquals("Element in wrong order", 3, (int) myIntQueue.poll());
	}
	
	// Test med sig själv
	@Test
	void appendSame() {
		assertThrows(IllegalArgumentException.class, () -> myIntQueue.append(myIntQueue));
	}
}
