
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.lang.Iterable;

/**
 * * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code fragment 8.7
 * transcribed by:
 * @author jakeharmon
 * @version 3/11/2021
 */
public class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    /**
     * Returns the Position of p's sibling (or null if no sibling exists).
     *
     * @param p
     * @return
     */
    @Override
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) {
            return null;            // p must be the root
        }
        if (p == left(parent)) // p is a left child
        {
            return right(parent);                     // (right child might be null)
        } else // p is a right child
        {
            return left(parent);                      // (left child might be null)
        }
    }

    /**
     * Returns the number of children of Position p.
     *
     * @param p
     * @return
     */
    @Override
    public int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }

    /**
     * Returns an iterable collection of the Positions representing p's
     * children.
     *
     * @param p
     * @return
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2);    // max capacity of 2
        if (left(p) != null) {
            snapshot.add(left(p));
        }
        if (right(p) != null) {
            snapshot.add(right(p));
        }
        return snapshot;
    }

    @Override
    public Position<E> root() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


//    @Override
//    public Iterable<Position<E>> positions() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //---------------- nested ElementIterator class ----------------------------
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> posIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        } // return element!

        @Override
        public void remove() {
            posIterator.remove();
        }
    }// ---------- end of nnested ElementIteratorClass -------------------------

    /**
     * Returns an iterator of the elements stored in the tree.
     * @return 
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

        //start of inorderTree traversal methods
    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) {
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if (right(p) != null) {
            inorderSubtree(right(p), snapshot);
        }
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in
     * inorder.
     */
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);    // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Overrides positions to make inorder the default order for binary trees.
     */
    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }
    
    
}
