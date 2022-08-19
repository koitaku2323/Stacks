
/**
 * Stack class - singly-linked list version
 * @author Ryan Yee
 * @author Vy Truong
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

public class Stack<T> implements LIFO<T> {
	private class Node {
		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node top;
	private int size;

	/**** CONSTRUCTORS ****/

	/**
	 * Default constructor for the Stack class
	 * 
	 * @postcondition a new Stack object with all fields assigned default values
	 */
	public Stack() {
		top = null;
		size = 0;
	}

	/**
	 * Constructor for the Stack class Converts an array into a Stack in the same
	 * order
	 * 
	 * @param an array of elements to copy e.g. [1,2,3] becomes 1->2->3->null
	 */
	public Stack(T[] array) {
		if (array == null) {
			return;
		} else if (array.length == 0) {
			top = null;
			size = 0;
		} else {
			for (int i = array.length - 1; i >= 0; i--) {
				push(array[i]);
			}
		}

	}

	/**
	 * Copy constructor for the Stack class
	 * 
	 * @param original the Stack to copy
	 * @postcondition a new Stack object which is an identical, but distinct, copy
	 *                of original REQUIRED: THIS METHOD MUST BE IMPLEMENTED IN O(N)
	 *                TIME
	 */
	public Stack(Stack<T> original) {
		if (original == null) {
			return;
		} else if (original.size == 0) {
			top = null;
			size = 0;
		} else {
			Node temp = original.top;
			while (temp != null) {
				push(temp.data);
				temp = temp.next;
			}
			temp = top;
			temp = temp.next;
			top.next = null;
			while (temp != null) {
				push(temp.data);
				temp = temp.next;
			}
		}
	}

	/**** ACCESSORS ****/

	public T peek() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("The stack is empty: cannot peek!");
		} else {
			return top.data;
		}
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**** MUTATORS ****/

	public void push(T data) {
		if (size == 0) {
			top = new Node(data);
		} else {
			Node newNode = new Node(data);
			newNode.next = top;
			top = newNode;
		}
		size++;
	}

	public void pop() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("The stack is empty: cannot pop!");
		}
		top = top.next;
		size--;
	}

	/**** ADDITONAL OPERATIONS ****/

	/**
	 * Returns the values stored in the Stack as a String, separated by a blank
	 * space with a new line character at the end
	 * 
	 * @return a String of Stack values
	 */
	@Override
	public String toString() {
		if (top == null) {
			return "\n";
		} else {
			String result = "";
			Node temp = top;
			while (temp != null) {
				result += temp.data + " ";
				temp = temp.next;
			}
			result += "\n";
			return result;
		}
	}

	/**
	 * Determines whether two Stacks contain the same values in the same order
	 * 
	 * @param obj the Object to compare to this Stack
	 * @return whether obj and this Stack are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Stack)) {
			return false;
		} else {
			Stack<T> L = (Stack<T>) obj;
			if (this.size != L.size) {
				return false;
			} else {
				Node node1 = this.top;
				Node node2 = L.top;
				while (node1 != null) {
					if (!(node1.data.equals(node2.data))) {
						return false;
					}
					node1 = node1.next;
					node2 = node2.next;
				}
				return true;
			}
		}
	}

	/** RECURSIVE HELPER METHOD */
	public String reverseStack() {
		return reverseStack(top) + "\n";
	}

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order
	 * 
	 * @param n the current node
	 */
	private String reverseStack(Node n) {
		StringBuilder sb = new StringBuilder();
		if (n == null) {
			return sb.toString();
		}
		if (null != n.next) {
			sb.append(reverseStack(n.next));
		}
		sb.append(n.data).append(" ");
		return sb.toString();
	}
}
