package ule.ed.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class ArrayEDListTest {
	private ArrayEDList<String> lista;
	
	@Before
	public void setUp() {
		 lista= new ArrayEDList<String>();
	}

	@Test
	public void ArrayVaciaTest() {
		assertEquals(0,lista.size());
	}
	
	// test addFirst comprueba que dispara excepción
	@Test(expected=NullPointerException.class)
	public void ArrayAddFirstElementoNuloTest() {
			lista.addFirst(null);
	}
	
	@Test
	public void ArrayAddFirstTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	
	@Test
	public void ArrayAddFirstExpandCapacityTest() {
		lista=new ArrayEDList<String>(3);
		
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("10");
		Assert.assertEquals("(10 7 3 2 )", lista.toString());		
	}

	@Test
	public void testAddLast() {
		lista=new ArrayEDList<String>(3);
		
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addLast("10");
		Assert.assertEquals("(2 3 7 10 )", lista.toString());		
	}

	@Test
	public void testAddPenult() {
		lista=new ArrayEDList<String>(3);
		
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addPenult("10");
		Assert.assertEquals("(2 3 10 7 )", lista.toString());		
	}

	@Test
	public void testAddPos() {
		lista=new ArrayEDList<String>(3);
		
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		lista.addPos("10", 3);;
		Assert.assertEquals("(2 3 10 7 )", lista.toString());		
	}

	@Test
	public void testRemoveFirst() throws EmptyCollectionException {
		lista=new ArrayEDList<String>(3);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.removeFirst();
		Assert.assertEquals("(3 2 )", lista.toString());
		
	}

	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		lista=new ArrayEDList<String>(3);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("4");
		lista.addFirst("7");
		lista.removelast();
		Assert.assertEquals("(7 4 3 )", lista.toString());
		
	}

	@Test
	public void testRemovePenult() throws EmptyCollectionException {
		lista=new ArrayEDList<String>(3);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("4");
		lista.addFirst("7");
		lista.removePenult();
		Assert.assertEquals("(7 4 2 )", lista.toString());
		
	}

	@Test
	public void testRemoveElement() throws EmptyCollectionException{
		lista=new ArrayEDList<String>(3);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("5");
		lista.removeElem("7");
		Assert.assertEquals("(5 3 2 )", lista.toString());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveElementException() throws EmptyCollectionException{
		lista=new ArrayEDList<String>(3);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("5");
		lista.removeElem("6");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetElementPosException(){
		lista=new ArrayEDList<String>(3);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.getElemPos(10);
	}

	@Test
	public void testGetElementPos() throws IllegalArgumentException {
		lista=new ArrayEDList<String>(3);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		Assert.assertEquals("7", lista.getElemPos(1));
	}

	@Test
	public void testGetPosLast() throws IllegalArgumentException {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.addFirst("2");
		Assert.assertEquals(5, lista.getPosLast("2"));
	}

	@Test(expected = NullPointerException.class)
	public void testGetPosLastNullException() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.addFirst("2");
		lista.getPosLast(null);
	}

	@Test
	public void testRemoveAll() throws EmptyCollectionException {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.addFirst("2");
		lista.removeAll("2");
		Assert.assertEquals("(7 3 )", lista.toString());
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveAllNullException() throws EmptyCollectionException {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.addFirst("2");
		lista.removeAll(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveAllNotFoundException() throws EmptyCollectionException {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.addFirst("2");
		lista.removeAll("6");
	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveAllEmptyException() throws EmptyCollectionException {
		lista=new ArrayEDList<String>(6);
		lista.removeAll("6");
	}

	@Test
	public void testClear() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.addFirst("2");
		lista.clear();
		Assert.assertEquals("()", lista.toString());
	}

	@Test
	public void testGetPosFirst() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("7");
		lista.addFirst("2");
		Assert.assertEquals(2, lista.getPosFirst("7"));
	}

	@Test(expected = NullPointerException.class)
	public void testGetPosFirstNullException() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.getPosFirst(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetPosFirstNotFoundException() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.getPosLast("8");
	}

	@Test
	public void testListRepeatedElems() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.addFirst("2");
		lista.listOfRepeatedElems();
		Assert.assertEquals("(2 7 )", lista.toString());
	}

	@Test
	public void testCountElem() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("7");
		lista.addFirst("2");
		Assert.assertEquals(2, lista.countElem("7"));
	}

	@Test(expected = NullPointerException.class)
	public void testCountElemException() {
		lista=new ArrayEDList<String>(6);

		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		lista.addFirst("7");
		lista.addFirst("2");
		lista.countElem(null);
	}

	//test de iteradores
	@Test
	public void ArrayIteratorTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		Iterator<String>  iter=lista.iterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void ArrayEvenIteratorNElemesParTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", lista.toString());

		Iterator<String>  iter=lista.evenPositionsIterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testEvenIteratorException() {
		lista.addFirst("2");

		Iterator<String>  iter=lista.evenPositionsIterator();
		iter.next();

	}

	@Test
	public void ArrayOddIteratorNElemesParTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", lista.toString());

		Iterator<String>  iter=lista.oddPositionsIterator();
		assertTrue(iter.hasNext());
		assertEquals("8", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertFalse(iter.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testOddIteratorException() {
		lista.addFirst("2");

		Iterator<String>  iter=lista.oddPositionsIterator();
		iter.next();

	}

	
	// TEST ITERADORES EN LISTA VACÍA
	@Test(expected=NoSuchElementException.class)
	public void ArrayNextListaVaciaTest() {
			lista.iterator().next();	}
	
	
	
}
	
	
