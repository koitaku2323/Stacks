
/**
 * Stack class - Two Queue Version
 * @author Ryan Yee
 * @author Vy Truong
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements LIFO<T> {
	private Queue<T> queue1;
	private Queue<T> queue2;

	/**** CONSTRUCTORS ****/

	/**
	 * Default constructor for the Stack class
	 */
	public Stack() {
		queue1 = new Queue<T>();
		queue2 = new Queue<T>();
	}

	/**
	 * Converts an array into a Stack in the same order
	 * 
	 * @param array the array to copy
	 */
	public Stack(T[] array) {
		if (array == null) {
			queue1 = new Queue<T>();
			queue2 = new Queue<T>();
		} else if (array.length == 0) {
			queue1 = new Queue<T>();
			queue2 = new Queue<T>();
		} else {
			queue1 = new Queue<T>();
			queue2 = new Queue<T>();
			for (int i = 0; i < array.length; i++) {
				queue1.enqueue(array[i]);
			}
		}
	}

	/**
	 * Copy constructor for the Stack class
	 * 
	 * @param original the Stack to copy
	 * @postcondition a new Stack object which is an identical, but distinct, copy
	 *                of original
	 */
	public Stack(Stack<T> original) {
		if (original == null) {
			queue1 = new Queue<T>();
			queue2 = new Queue<T>();
		} else if (original.getSize() == 0) {
			queue1 = new Queue<T>();
			queue2 = new Queue<T>();
		} else {
			int size = original.getSize();
			queue1 = new Queue<T>();
			queue2 = new Queue<T>();
			for (int i = 0; i < size; i++) {
				this.push(original.peek());
				if (i < 1) {
					original.pop();
				}
			}
		}
	}

	/**** ACCESSORS ****/

	// Add methods here
	public T peek() throws NoSuchElementException {
		if (queue1.getSize() == 0) {
			throw new NoSuchElementException("The stack is empty: cannot peek!");
		} else {
			return queue1.getFront();
		}
	}

	public int getSize() {
		return queue1.getSize();
	}

	public boolean isEmpty() {
		return queue1.getSize() == 0;
	}

	/**** MUTATORS ****/

	// Add methods here
	public void push(T data) {
		if (queue1 == null) {
			queue1.enqueue(data);
			;
		} else {
			for (int i = queue1.getSize(); i > 0; i--) {
				queue2.enqueue(queue1.getFront());
				queue1.dequeue();
			}
			queue1.enqueue(data);
			for (int j = queue2.getSize(); j > 0; j--) {
				queue1.enqueue(queue2.getFront());
				queue2.dequeue();
			}
		}
	}

	public void pop() throws NoSuchElementException {
		if (queue1 == null) {
			throw new NoSuchElementException("The stack is empty: cannot pop!");
		} else {
			queue1.dequeue();
		}
	}

	/**** ADDITONAL OPERATIONS ****/

	/**
	 * Returns the values stored in the Queue as a String, separated by a blank
	 * space with a new line character at the end
	 * 
	 * @return a String of Stack values
	 */
	@Override
	public String toString() {
		int size = queue1.getSize();
		if (queue1.getSize() == 0) {
			return "\n";
		} else {
			String result = "";
			for (int i = 0; i < size; i++) {
				result += queue1.getFront() + " ";
				queue1.dequeue();
			}
			result += "\n";
			return result;
		}
	}

	/**
	 * Determines whether two objects are Stacks and contain the same values in the
	 * same order
	 * 
	 * @param obj the Object to compare to this Stack
	 * @return whether obj and this are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Stack)) {
			return false;
		} else {
			int size = queue1.getSize();
			@SuppressWarnings("unchecked")
			Stack<T> L = (Stack<T>) obj;
			if (this.getSize() != L.getSize()) {
				return false;
			}
			for (int i = 0; i < size; i++) {
				if (!(this.peek().equals(L.peek()))) {
					return false;
				}
				if (i < 1) {
					this.pop();
					L.pop();
				}
			}
			return true;
		}
	}

	public String reverseStack() {
		return queue1.reverseQueue();
	}

}
