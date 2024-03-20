package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedEDList<T> implements IEDList<T> {

	//	referencia al primer  de la lista
	private Node<T> front;

	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA

	private class Node<T> {

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}
	///////
	///// ITERADOR normal //////////

	//@SuppressWarnings("hiding")
	private class LinkedListIterator<T> implements Iterator<T> {
		// declarar atributos del iterador
		
		public LinkedListIterator(Node<T> aux) {
			//TODO
		}

		@Override
		public boolean hasNext() {
			//TODO
			
			return false;
		}

		@Override
		public T next() {
	  // TODO
			
			return null;

			
		}	
	}
	
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	
		// FIN ITERADORES
		
	


	@Override
	public int size() {
		int count = 0;
		Node <T> aux = front;
		while(aux != null) {
			count++;
			aux = aux.next;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public void addFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		Node<T> nodeAdd = new Node<T>(elem);
		nodeAdd.next = front;
		front = nodeAdd;
	}

	@Override
	public void addLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(isEmpty()) {
			addFirst(elem);
		} else {
			Node<T>current = front;
			while (current.next != null) {
				current = current.next;
			}
			Node<T> nodeAdd = new Node<T>(elem);
			current.next = nodeAdd;
		}
	}

	@Override
	public void addPenult(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(isEmpty()) {
			addFirst(elem);
		} else {
			Node<T> current = front;
			while (current.next != null && current.next.next != null) {
				current = current.next;
			}
			Node<T> nodeAdd = new Node<T>(elem);
			nodeAdd.next = current.next;
			current.next = nodeAdd;
		}
	}

	@Override
	public void addPos(T elem, int position) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(position < 0) {
			throw new IllegalArgumentException();
		}
		if(position == 0) {
			addFirst(elem);
			return;
		}
		Node<T> current = front;
		Node<T> nodeAdd = new Node<T>(elem);
		int i = 0;
		while (current != null && i < position - 2) {
			current = current.next;
			i++;
		}
		if(current == null) {
			throw new IllegalArgumentException();
		}
		nodeAdd.next = current.next;
		current.next = nodeAdd;
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista está vacía.");
		} 
		T removeElement = front.elem;
		front = front.next;
	    
		return removeElement;
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista está vacía.");
		} 
		if(front.next == null) {
			removeFirst();
		}
		Node<T> current = front;
		while (current.next.next != null) {
			current = current.next;
		}
		T removeElement = current.next.elem;
		current.next = null;
	    
		return removeElement;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		if(isEmpty()){
			throw new EmptyCollectionException("La lista esta vacía.");
		}
		if(front.next == null) {
			throw new NoSuchElementException();
		}
		Node<T> current = front;
		while (current.next.next.next != null) {
			current = current.next;
		}
		T removeElement = current.next.elem;
		current.next = current.next.next;
		return removeElement;
		
	}
	
	@Override
	public T getElemPos(int position) {
		if(position < 0) {
			throw new IllegalArgumentException();
		}
		Node<T> current = front;
		for(int i = 0; i == position; i++) {
			if(current == null) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		return current.elem;
	}
	@Override
	public int getPosLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		Node<T> current = front;
		int lastPosition = -1;
		for(int i = 0; current != null; i++) {
			if(current.elem.equals(elem)) {
				lastPosition = i + 1;
			}
			current = current.next;
		}
		if(lastPosition == -1) {
			throw new NoSuchElementException();
		}	
		return lastPosition;
	 }
	

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO 	
		return 0;

	}

	
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("(");
		Node<T> current = front;
		while (current != null) {
			result.append(current.elem);
			result.append(" ");
			current = current.next;
		}
		result.append(")");
		return result.toString();
	}


		@Override
		public int removeElem(T elem) throws EmptyCollectionException {
			// TODO Auto-generated method stub
			return 0;
		}

	
		@Override
		public Iterator<T> iterator() {
			// TODO 
			return new LinkedListIterator<T>(front);
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