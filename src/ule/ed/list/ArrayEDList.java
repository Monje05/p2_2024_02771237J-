package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayEDList<T> implements IEDList<T> {
	static final int DEFAULT_CAPACITY=10;

    private T[] data;
	private int count;
	
	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA IMPLEMENTADA CON ARRAYS

	

	private class ArrayEDListIterator implements Iterator<T> {
		private int current=0;
		private int size = count;

		@Override
		public boolean hasNext() {
			return (current < size);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = data[current];
			current++;
			return item;
		}
	}

	private class EvenPositionsIterator implements Iterator<T> {
		private int current = 1;
		private int size = count;

		@Override
		public boolean hasNext() {
			return current < size;
		}
		
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = data[current];
			current += 2;
			return item;
		}
		
	}

	private class OddPositionsIterator implements Iterator<T> {
		private int current = 0;
		private int size = count;

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = data[current];
			current += 2;
			return item;
		}
	}

	private class OddEvenIterator implements Iterator<T> {
		private int current = 0;
		private int size = count;
		private boolean isOddTurn = true;

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = data[current];
			if(isOddTurn) {
				current += 2;
			} else {
				current += 1;
			}
			isOddTurn = !isOddTurn;
			return item;
		}
	}


	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	
	// FIN ITERADORES
	
	
	@SuppressWarnings("unchecked")
	public  ArrayEDList() {
	   count = 0;
	   data = (T[])(new Object[DEFAULT_CAPACITY]); 
	}

	@SuppressWarnings("unchecked")
	public  ArrayEDList(int capacity) {	
	  count = 0;
	  data = (T[])(new Object[capacity]); 
	}
	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@SuppressWarnings("unchecked")
	private void checkNullExpandCapacityIfNeed(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(count < data.length) {
			return;
		}
		T[] larger = (T[])(new Object[data.length * 2]);
		for(int index = 0; index < data.length; index++) {
			larger[index] = data[index];
		}
		data = larger;
	}
	
	@Override
	public void addFirst(T elem) {
		checkNullExpandCapacityIfNeed(elem);
		for(int i = count; i > 0; i--) {
			data[i] = data [i - 1];
		}
		data[0] = elem;
		count++;
	}


	@Override
	public void addLast(T elem) {
		checkNullExpandCapacityIfNeed(elem);
		data[count] = elem;
		count++; 
	}

	@Override
	public void addPenult(T elem) {
		checkNullExpandCapacityIfNeed(elem);
		if(isEmpty()) {
			addFirst(elem);
		} else {
			data[count] = data[count - 1];
			data[count - 1] = elem;
			count++;
		} 
	}

	@Override
	public void addPos(T elem, int position) {
		checkNullExpandCapacityIfNeed(elem);
		if(position <= 0) {
			throw new IllegalArgumentException();
		}
		if(position > count) {
			addLast(elem);
		}
		for(int i = count; i > position - 1; i--) {
			data[i] = data[i - 1];
		}
		data[position - 1] = elem;
		count++;
	}

	private void checkIsEmpty() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista esta vácia");
		}
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		checkIsEmpty();
		T dataaux = data[0];
		data[0] = null;
		for(int i = 0; i < count - 1; i++) {
			data[i] = data[i + 1];
		}
		count--;
		return dataaux;
		
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		checkIsEmpty();
		T dataaux = data[count - 1];
		data[count - 1] = null;
		count--;
		return dataaux;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		checkIsEmpty();
		T dataaux = data[count - 2];
		data[count - 2] = null;
		for(int i = count - 2; i < count - 1; i++) {
			data[i] = data[i + 1];
		}
		count--;
		return dataaux;
	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		checkIsEmpty();
		for(int i = 0; i < count; i++) {
			if(data[i] == elem) {
				data[i] = null;
				for(int j = i; j < count; j++) {
					data[j] = data[j + 1];
				}
				count--;
				return i + 1;
			}
		}
		throw new NoSuchElementException();
			
	}
	
	@Override
	public T getElemPos(int position) {
		if(position < 1 || position > count) {
			throw new IllegalArgumentException("Posición fuera de rango");
		}
		T element = data[position - 1];
		return element;
	}

	@Override
	public int getPosLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		int pos = 0;
		for(int i = count; i > 0; i--) {
			if(data[i] == elem) {
				pos = i + 1;
				return pos;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		if(elem == null) {
			throw new NullPointerException();
		}
		int contador = 0;
		boolean found = false;
		checkIsEmpty();
		for(int i = 0; i < count; i++) {
			if(data[i] == elem) {
				found = true;
				data[i] = null;
				contador++;
				for(int j = i; j < count - 1; j++) {
					data[j] = data[j + 1];
				}
				count--;
				i--;
			}
		}
		if(!found) {
			throw new NoSuchElementException();
		}
		return contador; 
	}

	@Override
	public String toString() {
		String result ="(";
		for (int index=0; index < count; index++) {
			result = result + data[index].toString();
			result = result + " ";
		}
		result = result + ")";
		return result;
	}
   
	
	
	@Override
	public void clear() {
		for(int i = 0; i < count; i++) {
			data[i] = null;
		}
		count = 0;
		
	}

	@Override
	public int getPosFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		int pos = 0;
		for(int i = 0; i < count; i++) {
			if(data[i] == elem) {
				pos = i + 1;
				return pos;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public IEDList<T> listOfRepeatedElems() {
		ArrayEDList<T> lista = new ArrayEDList<T>(count);
		for(int i = 0; i < count; i++) {
			if(countElem(data[i]) > 1 && !lista.contains(data[i])) {
				lista.addLast(data[i]);
			}
		}
		return lista;
	}

	@Override
	public int countElem(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		int contador = 0;
		for(int i = 0; i < count; i++) {
			if(data[i].equals(elem)) {
				contador++;
			}
		}
		return contador;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayEDListIterator();
	}


	@Override
	public Iterator<T> evenPositionsIterator() {
		return new EvenPositionsIterator();
	}

	@Override
	public Iterator<T> oddPositionsIterator() {
		return new OddPositionsIterator();
	}

	@Override
	public Iterator<T> OddEvenIterator() {
		return new OddEvenIterator();
	}

	private boolean contains(T element) {
		boolean contains = false;
		for(int i = 0; i < count && !contains; i++) {
			if(element.equals(data[i])) {
				contains = true;
			}
		}
		return contains;
	}
}
