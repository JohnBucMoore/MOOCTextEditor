package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		add(size,element);
		/*
		if (element == null) {
			throw new NullPointerException("MyLinkedList object cannot store null pointers.");
		}
		LLNode<E> addedNode = new LLNode<E>(element);
		addedNode.next = tail;
		addedNode.prev = tail.prev;
		tail.prev.next = addedNode;
		tail.prev = addedNode;
		
		/* You may want to generalize like this
		addedNode.prev = tail.prev;
		addedNode.next = addedNode.prev.next;
		addedNode.prev.next = addedNode;
		tail.prev = addedNode;
		*/
		//size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (size == 0 || index > (size-1) || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> node = head.next;
		for (int i = 0; i < size; i++) {
			if (i != index) {
				node = node.next;
			} else if (i == index) {
				return node.data;
			}
		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) {
			throw new NullPointerException("MyLinkedList object cannot store null pointers.");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> insertedNode = new LLNode<E>(element);
		LLNode<E> currNode = head.next;
		for (int i = 0; i < index+1; i++) {
			if (i == index) {
				insertedNode.next = currNode;
				insertedNode.prev = currNode.prev;
				currNode.prev.next = insertedNode;
				currNode.prev = insertedNode;
				size++;
				break;
			}
			currNode = currNode.next;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> removedNode = new LLNode<E>(null);
		LLNode<E> currNode = head.next;
		for (int i = 0; i < index+1; i++) {
			if (i == index) {
				currNode.prev.next = currNode.next;
				currNode.next.prev = currNode.prev;
				currNode.next = null;
				currNode.prev = null;
				removedNode = currNode;
				size--;
				break;
			}
			currNode = currNode.next;
		}
		return removedNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		LLNode<E> currNode = head.next;
		for (int i = 0; i < index+1; i++) {
			if (i == index) {
				E oldVal = currNode.data;
				currNode.data = element;
				return oldVal;
			}
			currNode = currNode.next;
		}
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
