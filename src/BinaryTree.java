
import java.util.Iterator;
import java.lang.Iterable;
/**
 * * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code fragment 8.6
 * transcribed by:
 * @author jakeharmon
 * @version 3/11/2021
 */
public interface BinaryTree<E> extends Tree<E> {
    
    Position<E> left(Position<E> p) throws IllegalArgumentException;
    
    Position<E> right(Position<E> p) throws IllegalArgumentException;
    
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
    
    
}
