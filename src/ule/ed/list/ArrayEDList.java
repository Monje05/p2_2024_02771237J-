package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayEDList<T> implements IEDList<T> {
	static final int DEFAULT_CAPACITY=10;

    private T[] data;
	private int count;
	
	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA IMPLEMENTADA CON ARRAYS

	

	private class ArrayEDListIterator<T> implements Iterator<T> {
		private int current=0;
		private int count;
		private T[] items;

		@Override
		public boolean hasNext() {
		return (current < count);
		
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			current++;
		return items[current - 1];
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
		for(int i = 0; i <= count; i++) {
			data[i] = data[i + 1];
		}
		data[count] = elem;
		count++; 
	}

	@Override
	public void addPenult(T elem) {
		checkNullExpandCapacityIfNeed(elem);
		if(isEmpty()) {
			addFirst(elem);
		}
		for(int i = 0; i < count; i++) {
			data[i] = data[i + 1];
		}
		data[count - 1] = elem;
		count++; 
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
		for(int i = 0; i <= count; i++) {
			if(i == position) {
				data[i] = elem;
			}
		}
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
		// TODO 
		return 0;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPosFirst(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IEDList<T> listOfRepeatedElems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayEDListIterator<T>();
	}


	@Override
	public Iterator<T> evenPositionsIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> oddPositionsIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> OddEvenIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
