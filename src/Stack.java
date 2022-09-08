
/**
 * Stack interface
 * Code Fragments 6.1
 * from
 * Data Structures & Algorithms, 6th edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author jakeharmon
 * @version 2/25/2021
 */
public interface Stack<E> {

    /**
     * Returns the number of elements in the stack.
     * @return numbr of elements in the stack
     */
    int size();
    
    /**
     * Tests whether the stack is empty.
     * @return true if the stack is empty, false if otherwise
     */
    boolean isEmpty();
    
    /**
     * Inserts an element at the top of the stack.
     * @param e  the element to be stored
     */
    void push(E e);
    
    /**
     * Returns, but does not remove, the lement at the top of the stack
     * @return top element in the stack (or null if empty)
     */
    E top();
    
    /**
     * Removes and returns the top element from the stack.
     * @return element removed (or null if empty)
     */
    E pop();

}
