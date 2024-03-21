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
		private Node<T> current;
		
		public LinkedListIterator(Node<T> aux) {
			current = aux;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = current.elem;
			current = current.next;
			return element;

			
		}	
	}
	
	private class EvenPositionsIterator<T> implements Iterator<T> {
		private Node<T> current;

		public EvenPositionsIterator(Node<T> aux){
			current = aux;
		}

		@Override
		public boolean hasNext() {
			return current != null && current.next != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = current.next.elem;
			current = current.next.next;
			return element;
		}
		
	}

	private class OddPositionsIterator<T> implements Iterator<T> {
		private Node<T> current;
		
		public OddPositionsIterator(Node<T> aux) {
			current = aux;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = current.elem;
			current = current.next.next;
			return element;
		}
		
	}

	private class OddEvenIterator<T> implements Iterator<T> {
		private Node<T> current;
		private boolean isOddTurn;

		public OddEvenIterator(Node<T> aux) {
			current = aux;
			isOddTurn = true;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}

			T element = current.elem;
			current = current.next;
			if(isOddTurn && current != null){
				current = current.next;
			}
			isOddTurn = !isOddTurn;
			return element;
		}
		
	}
	
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
		if(size() < 2) {
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
		if(position == 1) {
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
		for(int i = 0; i < position - 1; i++) {
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
		if(elem == null) {
			throw new NullPointerException();
		}
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista esta vacía.");
		}
		Node<T> current = front;
		Node<T> previous = null;
		int count = 0;
		while(current != null){
			if(current.elem.equals(elem)) {
				if(previous == null) {
					front = current.next;
				} else {
					previous.next = current.next;
				}
				count++;
			} else {
				previous = current;
			}
			current = current.next;
		}
		if(count == 0) {
			throw new NoSuchElementException();
		}
		return count;

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
			if(isEmpty()) {
				throw new EmptyCollectionException("La lista esta vacía.");
			}
			Node<T> current = front;
			Node<T> previous = null;
			int position = 0;
			for(int i = 0; current != null; i++) {
				if(current.elem.equals(elem)) {
					if(previous == null) {
						front = current.next;
					} else {
						previous.next = current.next;
					}
				position = i + 1;
				break;
				} else {
						previous = current;
				}
				current = current.next;
				}
			if(position == 0) {
				throw new NoSuchElementException();
			}
			return position;
		}

	
		@Override
		public Iterator<T> iterator(){ 
			return new LinkedListIterator<T>(front);
		}
		

		@Override
		public void clear() {
			Node<T> current = front;
			Node<T> previous = null;
			while(current != null) {
				if(previous == null) {
					front = current.next;
				} else {
					previous.next = current.next;
				}
				current = current.next;
			}
			
		}

		@Override
		public int getPosFirst(T elem) {
			if(elem == null) {
				throw new NullPointerException();
			}
			Node<T> current = front;
			int firstPosition = -1;
			for(int i = 0; current != null; i++) {
				if(current.elem.equals(elem)) {
					firstPosition = i + 1;
					break;
				}
				current = current.next;
			}
			if(firstPosition == -1) {
				throw new NoSuchElementException();
			}	
			return firstPosition;
		}

		@Override
		public IEDList<T> listOfRepeatedElems() {
			LinkedEDList<T> repeatedElementList = new LinkedEDList<>(); 
			Node<T> current = front;
			while(current != null) {
				if(countElem(current.elem) > 1 && !repeatedElementList.contains(current.elem)){
					repeatedElementList.addLast(current.elem);
				}
				current = current.next;
			}
			return repeatedElementList;
		}

		private boolean contains(T element) {
			boolean contains = false;
			Node<T> current = front;
			while(current != null && !contains) {
				if(current.elem.equals(element)){
					contains = true;
				}
				current = current.next;
			}
			return contains;
		}

		@Override
		public int countElem(T elem) {
			if(elem == null) {
				throw new NullPointerException();
			}
			Node<T> current = front;
			int count = 0;
			while(current != null) {
				if(current.elem.equals(elem)) {
					count++;
				}
				current = current.next;
			}
			return count;
		}

		@Override
		public Iterator<T> evenPositionsIterator() {
			return new EvenPositionsIterator<>(front);
		}

		@Override
		public Iterator<T> oddPositionsIterator() {
			return new OddPositionsIterator<>(front);
		}

		@Override
		public Iterator<T> OddEvenIterator() {
			return new OddEvenIterator<>(front);
		}
}