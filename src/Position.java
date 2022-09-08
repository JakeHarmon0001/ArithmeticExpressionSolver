

/**
 * * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Section 7.3
 * transcribed by:
 * @author jakeharmon
 */
public interface Position<E> {
    /**
     * Returns the leement stored at this position
     * @return the stored element 
     * @throws IllegalStateException 
     */
    E getElement() throws IllegalStateException;
    
}
