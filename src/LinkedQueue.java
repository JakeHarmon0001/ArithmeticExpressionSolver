
/**
 * LinkedQueue Class
 * Code Fragments 6.11
 * from
 * Data Structures & Algorithms, 6th edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author jakeharmon
 * @version 2/25/2021
 */
public class LinkedQueue<E> implements Queue<E> {
    
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();    // an empty list
    
    public LinkedQueue() {};    // new queue relies on initially empty list
    
    /**
     * 
     * @return number of elements in queue
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * 
     * @return true if queue is empty, else return false
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    /**
     * Adds element e to the end of the queue
     * @param e 
     */
    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }
    /**
     * 
     * @return first element in the queue
     */
    @Override
    public E first() {
        return list.first();
    }
    
    /**
     * removes and returns the first element of the queue
     * @return first element
     */
    @Override
    public E dequeue() {
        return list.removeFirst();
    }
    
}
