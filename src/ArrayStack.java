
/**
 * ArrayStack Class
 * Code Fragments 6.2
 * from
 * Data Structures & Algorithms, 6th edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author jakeharmon
 * @version 2/21/2021
 */
public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000;   // default array capacity
    private E[] data;                         // generic array used for storage
    private int t = -1;                      // index of the top element in stack

    public ArrayStack() {                   // constructs stack with default capacity
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];   // safe cast; compiler may give warning
    }

    @Override
    public int size() {
        return (t + 1);
    }

    @Override
    public boolean isEmpty() {
        return (t == -1);
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[++t] = e;                      // increment t ebefore storing new item
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[t];
        data[t] = null;        // dereference to help garbage collection
        t--;
        return answer;
    }

}
