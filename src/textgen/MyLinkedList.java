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
	public boolean add(E element) {
        add(size, element);
        return false;
	}

    /**
     * Add an element to the list at the specified index
     * @param The index where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element ) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        LLNode<E> nodeToMoveForward = index < size ? getNodeAtIndex(index) : tail;
        new LLNode<>(element, nodeToMoveForward);
        size++;
    }

    private LLNode<E> getNodeAtIndex(int index) {
        E tempData = get(index);
        LLNode<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.data == tempData) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    /** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        LLNode<E> node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
	}

	/** Return the size of the list */
	public int size() {
        return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
        E tempData = get(index);
        LLNode<E> nodeToRemove = getNodeAtIndex(index);
        LLNode<E> nextNode = nodeToRemove.next;
        LLNode<E> prevNode = nodeToRemove.prev;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;

        return tempData;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        E tempData = get(index);
        LLNode<E> node = getNodeAtIndex(index);
        node.data = element;

		return tempData;
	}   
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

    public LLNode(E e, LLNode<E> nodeToMoveForward) {
        LLNode<E> prevNode = nodeToMoveForward.prev;
        prevNode.next = this;
        this.data = e;
        this.prev = nodeToMoveForward.prev;
        this.next = nodeToMoveForward;
        nodeToMoveForward.prev = this;
    }
}
