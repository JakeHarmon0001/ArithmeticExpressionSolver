

/**
 * Queue interface
 * Code Fragments 6.9
 * from
 * Data Structures & Algorithms, 6th edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author jakeharmon
 * @version 2/25/2021
 */
public interface Queue<E> {
    /**
     * returns the number of elements int the queue
     * @return 
     */
    int size();
    /**
     * Tests whether the queue is empty
     * @return 
     */
    boolean isEmpty();
    /**
     * Inserts an element at the rear of the queue
     */
    void enqueue(E e);
    /**
     * Returns, but does not remove, the first element of teh queue (null if empty).
     * @return 
     */
    E first();
    
    /**
     * Removes and returns the first element of the queue (null if empty).
     * @return 
     */
    E dequeue();
}
