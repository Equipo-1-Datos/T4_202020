package test.data_structures;

import model.data_structures.BST;
import model.data_structures.Queue;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestBST {

	private BST bst;
	private final static int TAMANO_INICIAL = 3;

	@Before 
	public void setUp1() {
		bst = new BST();
	}

	public void setUp2() {
		String key = "Uno";
		for (int i = 0; i < TAMANO_INICIAL; i++) 
		{
			key += ",Uno";
			bst.put(key, "Uno");
		}
	}

	@Test
	public void testBST() {
		assertTrue(bst != null);
		assertTrue(bst.isEmpty());
	}

	@Test
	public void testSize() {
		setUp2();
		assertEquals(3, bst.size());
	}

	@Test
	public void testIsEmpty() {
		setUp1();
		assertTrue(bst.isEmpty());
		setUp2();
		assertFalse(bst.isEmpty());
	}
	
	@Test
	public void testGet()
	{
		setUp2();
		ArrayList<String> x = new ArrayList<String>();
		x.add("Uno");
		assertTrue(bst.get("Uno,Uno").equals(x));
	}
	
	@Test 
	public void testGetHeight() {
		setUp1();
		assertEquals(0, bst.getHeight("Uno,Uno"));
		setUp2();
		assertEquals(1, bst.getHeight("Uno,Uno,Uno"));
	}
	
	@Test
	public void testContains() {
		setUp2();
		assertTrue(bst.contains("Uno,Uno"));
		assertFalse(bst.contains("Uno"));
	}
	
	@Test
	public void testPut() {
		setUp1();
		bst.put("Uno", "Uno");
		ArrayList<String> x = new ArrayList<String>();
		x.add("Uno");
		assertEquals(x, bst.get("Uno"));
	}
	
	@Test
	public void testHeight() {
		setUp2();
		assertEquals(2, bst.height());
	}
	
	@Test
	public void testMin() {
		setUp2();
		assertEquals("Uno,Uno", bst.min());
	}
	
	@Test
	public void testMax() {
		setUp2();
		assertEquals("Uno,Uno,Uno,Uno", bst.max());
	}
	
	@Test
	public void testKeySet() {
		setUp2();
		ArrayList<String> x = new ArrayList<String>();
		x.add("Uno,Uno");
		x.add("Uno,Uno,Uno");
		x.add("Uno,Uno,Uno,Uno");
		ArrayList<String> w = new ArrayList<String>();
		Queue<String> z = (Queue<String>)bst.keySet();
		w.add(z.dequeue());
		w.add(z.dequeue());
		w.add(z.dequeue());
		assertEquals(x, w);
		//expected: model.data_structures.Queue<Uno,Uno Uno,Uno,Uno Uno,Uno,Uno,Uno >
		//but was:  model.data_structures.Queue<Uno,Uno Uno,Uno,Uno Uno,Uno,Uno,Uno >
	}
	
	@Test 
	public void testKeysInRange() {
		setUp2();
		ArrayList<String> x = new ArrayList<String>();
		x.add("Uno,Uno");
		x.add("Uno,Uno,Uno");
		ArrayList<String> w = new ArrayList<String>();
		Queue<String> z = (Queue<String>)bst.keysInRange("Uno,Uno", "Uno,Uno,Uno");
		w.add(z.dequeue());
		w.add(z.dequeue());
		assertEquals(x, w);
		//expected: model.data_structures.Queue<Uno,Uno Uno,Uno,Uno >
		//but was:  model.data_structures.Queue<Uno,Uno Uno,Uno,Uno >
	}
	
	@Test 
	public void testValuesInRange() {
		setUp2();
		ArrayList<String> x = new ArrayList<String>();
		x.add("Uno");
		x.add("Uno");
		assertEquals(x, bst.valuesInRange("Uno,Uno", "Uno,Uno,Uno"));
	}
}