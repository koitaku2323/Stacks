//import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Stack class - Array Version
 * 
 * @author Ryan Yee
 * @author Vy Truong CIS 22C, Lab 3
 */

public class Stack<T> implements LIFO<T> {
	private T[] stack;
	private int curr_size;

	/**** CONSTRUCTORS ****/

	/**
	 * Default constructor for the Queue class with an initial length of 10 and no
	 * elements
	 */
	@SuppressWarnings("unchecked")
	public Stack() {
		stack = (T[]) new Object[10];
		curr_size = 0;
	}

	/**
	 * Converts an array into a Stack in the same order
	 * 
	 * @param array the array to copy
	 */
	@SuppressWarnings("unchecked")
	public Stack(T[] array) {
		if (array == null) {
			stack = (T[]) new Object[10];
			curr_size = 0;
		} else if (array.length == 0) {
			stack = (T[]) new Object[10];
			curr_size = 0;
		} else {
			stack = (T[]) new Object[10];
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
	 *                of original
	 */
	@SuppressWarnings("unchecked")
	public Stack(Stack<T> original) {
		if (original == null) {
			stack = (T[]) new Object[10];
			curr_size = 0;
		} else if (original.curr_size == 0) {
			stack = (T[]) new Object[10];
			curr_size = 0;
		} else {
			stack = (T[]) new Object[10];
			for (int i = 0; i <= original.getSize() - 1; i++) {
				push(original.stack[i]);
			}

		}
	}

	/**** ACCESSORS ****/

	public T peek() throws NoSuchElementException {
		if (curr_size == 0) {
			throw new NoSuchElementException("The stack is empty: cannot peek!");
		} else {
			return stack[curr_size - 1];
		}
	}

	public int getSize() {
		return curr_size;
	}

	public boolean isEmpty() {
		return curr_size == 0;
	}
	// Add methods here

	/**** MUTATORS ****/

	public void push(T data) {
		if (stack == null) {
			return;
		}
		if (curr_size + 1 == 10) {
			resize();
		}
		if (curr_size == 0) {
			stack[curr_size] = data;
			curr_size++;
		} else {
			stack[curr_size] = data;
			curr_size++;
		}
	}

	public void pop() throws NoSuchElementException {
		if (curr_size == 0) {
			throw new NoSuchElementException("The stack is empty: cannot pop!");
		}
		stack[curr_size - 1] = null;
		curr_size--;
	}

	// Add methods here

	/**** ADDITONAL OPERATIONS ****/

	/**
	 * Returns the values stored in the Stack as a String, separated by a blank
	 * space with a new line character at the end
	 * 
	 * @return a String of Stack values
	 */
	@Override
	public String toString() {
		if (curr_size == 0) {
			return "\n";
		} else {
			String result = "";
			for (int i = curr_size - 1; i >= 0; i--) {
				result += stack[i] + " ";
			}
			result += "\n";
			return result;
		}
	}

	/**
	 * Determines whether two obects are Stacks and contain the same values in the
	 * same order
	 * 
	 * @param obj the Object to compare to this Stack
	 * @return whether obj and this are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Stack)) {
			return false;
		} else {
			@SuppressWarnings("unchecked")
			Stack<T> L = (Stack<T>) obj;
			if (this.curr_size != L.getSize()) {
				return false;
			} else {
				for (int i = 0; i < curr_size - 1; i++) {
					if (!(stack[i].equals(L.stack[i]))) {
						return false;
					}
				}
				return true;
			}
		}
	}

	/** PRIVATE HELPER METHODS */

	public String reverseStack() {
		return reverseStack(curr_size) + "\n";
	}

	/**
	 * private helper method for reverseStack
	 * 
	 * @param index the current index
	 * @return a String of this Stack in reverse order
	 */
	private String reverseStack(int index) {
		StringBuilder sb = new StringBuilder();
		if (index == 0) {
			return sb.toString();
		}
		if (0 != index) {
			sb.append(reverseStack(index - 1));
		}
		sb.append(stack[index - 1]).append(" ");
		return sb.toString();
	}

	/**
	 * Increases the current array size by 10
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		T[] copy = (T[]) new Object[stack.length + 10];
        for (int i = 0; i < stack.length; i++) {
            copy[i] = stack[i];
            
        }
        stack = copy;
	}
}
