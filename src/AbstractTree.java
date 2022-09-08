
import java.util.Iterator;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;

/**
 * * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code fragment 8.2
 * transcribed by:
 * @author jakeharmon
 * @version 3/11/2021
 */
public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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
    }// ---------- end of nested ElementIteratorClass --------------------------

    /**
     * Returns an iterator of the elements stored in the tree.
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    //start of preOrder traversal methods --------------------------------------
    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);      // for preorder, we add position p before exploring subtrees
        for (Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in
     * preorder.
     *
     * @return
     */
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);    // fill the snapshot recursively
        }
        return snapshot;
    }

    //end of preOrder traversal methods ----------------------------------------
    //start of postOrder traversal methods -------------------------------------
    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubtree(c, snapshot);
        }
        snapshot.add(p);      // for postorder, we add position p after exploring subtrees
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in
     * postorder.
     */
    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    //end of postOrder traversal methods ---------------------------------------
    //start of breadthFirst traversal
    /**
     * Returns an iterable collection of positions of the tree in breadth-first
     * order.
     */
    public Iterable<Position<E>> breadthfirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());             // start with the root
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.dequeue(); // remove from front of the queue
                snapshot.add(p);                   // report this position
                for (Position<E> c : children(p)) {
                    fringe.enqueue(c);               // add children to back of queue
                }
            }
        }
        return snapshot;
    }
    
    
    //end of breadthfirst traversal


}
