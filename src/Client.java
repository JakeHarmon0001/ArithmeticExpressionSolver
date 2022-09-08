
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * A class used for testing my HelperMethods class
 * @version 3/26/21
 * @author jakeharmon
 */
public class Client {

    public static void main(String[] args) throws FileNotFoundException {
        //a sample expression file is supplied called SampleTextFile
        HelperMethods.solveExpressions("/Users/jakeharmon/Documents/lab108Test.txt");
    }
}
