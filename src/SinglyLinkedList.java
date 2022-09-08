
/**
 * SinglyLinkedList Class
 * Code Fragments 3.14, 3.15
 * from
 * Data Structures & Algorithms, 6th edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author jakeharmon
 * @version 2/21/2021
 */

public class SinglyLinkedList<E>  {
    
    // nested Node class
    private static class Node<E> {
        
        private E element;        //reference to the element stored at this node
        private Node<E> next;     //reference to the subsequent node in the list
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        /**
         * 
         * @return element instance variable
         */
        public E getElement() { 
            return element;
        }
        /**
         * 
         * @return next instance variable
         */
        public Node<E> getNext() {
            return next;
        }
        /**
         * 
         * @param n set to next
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    }
    //end of the nested node class 
    //rest of SinglyLinkedListClass
        
        private Node<E> head = null;      //the head of the list is null if empty
        private Node<E> tail = null;      //the tail of the list is null if empty
        private int size = 0;
         
        public SinglyLinkedList() {};     // constructs a initially empty list
        
        //access methods
        /**
         * 
         * @return size instance variable
         */
        public int size() {
            return size;
        }
        /**
         * 
         * @return true if size == 0
         */
        public boolean isEmpty() {
            return size == 0;
        }
        /**
         * 
         * @return element in head
         */
        public E first() {
            if(isEmpty()) {
                return null;
            }
            return head.getElement();
        }
        /**
         * 
         * @return element in tail
         */
        public E last() {
            if(isEmpty()) {
                return null;
            }
             return tail.getElement();
        
        }
        
        //mutator/update methods
        /**
         * 
         * @param e is added into a Node that is added to the start of the list
         */
        public void addFirst(E e) {        //adds element to the front of the list
                                            
            head = new Node<>(e,head);     //create and link a new Node 
            
            if(size == 0) {
                tail = head;               //special case: new node becomes tail also 
            }
            size++;
        }
        /**
         * 
         * @param e is added into a Node that is added to the end of the list
         */
        public void addLast(E e) {                  //adds element e to the end of the list
            
            Node<E> newest = new Node<>(e,null);    //Node that will eventually be the tail
            if(isEmpty( )) {
                head = newest;                      //special case: previously empty list
            }
            else {
                tail.setNext(newest);               //new node after existing tail
            }
            tail = newest;                          //new node becomes tail
            size++;
        }
        /**
         * 
         * @return first element is removed and returned
         */
        public E removeFirst() {                    //removes and returns the first element
            
            if (isEmpty( )) {                       //nothing to remove
                return null;
            }
            E answer = head.getElement();
            head = head.getNext();                  //will become null if list had only one node
            size --;
            
            if(size == 0) {
                tail = null;                        //special case as list is now empty
            }
            return answer;
        }
    
}
