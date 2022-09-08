
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A class that contains a collection of methods that lets a client read in
 * expressions and solves them and then converts them into a binary tree
 *
 * @version 03/26/21
 * @author jakeharmon
 */
public class HelperMethods {

    /**
     * returns true if two chars make a negative string
     *
     * @param c
     * @param c2
     * @return
     */
    public static boolean isNegative(char c, char c2) {

        if (c == '-' && c2 != ' ') {

            return true;

        } else {
            return false;
        }

    }

    /**
     * converts a string line to a queue
     *
     * @param line
     * @return
     */
    public static LinkedQueue<String> toQueue(String line) {
        //variables
        Scanner scan = new Scanner(line);
        LinkedQueue<String> returnQueue = new LinkedQueue();    //queue to be returned

        while (scan.hasNext()) {

            returnQueue.enqueue(scan.next());
        }

        return returnQueue;
    }

    //start of helper methods --------------------------------------------------
    /**
     * returns true if the string is a number
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {

        final String NUMBER = "^0?-?\\+?\\d+(\\.\\d+)?$";
        return str.matches(NUMBER) ? true : false;

    }

    /**
     * returns a number based on the precedence of the provided operator
     *
     * @param str
     * @return
     */
    public static int getPrecedence(String str) {

        if (str.equals("/") || str.equals("*")) {
            return 2;
        } else if (str.equals("+") || str.equals("-")) {
            return 1;
        } else {
            return -1;
        }

    }

    /**
     * returns true if the string is an operator
     *
     * @param s
     * @return
     */
    private static boolean isOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * returns true if the string is an opening parenthesize
     *
     * @param s
     * @return
     */
    private static boolean isOpeningParenthesize(String s) {

        if (s.equals("(") || s.equals("[") || s.equals("{")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns true if the string is an closing parenthesize
     *
     * @param s
     * @return
     */
    private static boolean isClosingParenthesize(String s) {

        if (s.equals(")") || s.equals("]") || s.equals("}")) {
            return true;
        } else {
            return false;
        }
    }

    //end of helper methods ----------------------------------------------------
    /**
     * Converts infix expressions to postfix notation using the shunting yard
     * algorithm
     *
     * @param infix
     * @return
     */
    public static LinkedQueue shuntingYard(LinkedQueue<String> infix) throws ArithmeticException {

        LinkedQueue<String> postFix = new LinkedQueue();    //queue to store post fix expression

        LinkedStack<String> operators = new LinkedStack(); //stack to store operators temporarily
        int size = infix.size();
        // main for loop to iterate over the infix queue
        for (int i = 0; i < size; i++) {
            //assings token to the first string in the queue
            String token = infix.first();
            if (!token.equals("(") && !token.equals(")") && !isOperator(token)) {
                postFix.enqueue(token);
            } else if (token.equals("(")) { // if the token is an opening parenthesize "(" then it is pushed onto the operator stack
                operators.push(token);
            } else if (token.equals(")")) { // if the token is a closing parenthesize

                while (!operators.isEmpty() && !(operators.top().equals("("))) { // If the scanned Token is an ')' pop and append it to output from the stack until an '(' is encountered
                    postFix.enqueue(operators.pop());
                }

                operators.pop();
            } // If an operator is encountered furthur action is taken based on the precedence of the operator
            else {

                while (!operators.isEmpty() && (getPrecedence(token) <= getPrecedence(operators.top()))) {
                    postFix.enqueue(operators.pop());
                }

                operators.push(token);

            }

            //end of loop dont DELETE!!!
            //assings the first value to the end of the queue and removes the first one
            infix.enqueue(infix.first());
            infix.dequeue();

        }
        while (!operators.isEmpty()) {

            if (operators.top().equals("(")) {
                System.out.println("This expression is invalid");
                throw new ArithmeticException();
            }
            postFix.enqueue(operators.pop());
        }
        return postFix;
    }

    /**
     * Solves expressions that are in postfix notation and are stored in a
     * linkedQueue
     *
     * @param postfix
     * @return
     */
    public static double solvePostfix(LinkedQueue<String> postfix) {
        //variables
        LinkedQueue<String> copy = postfix; //copy of the postfix queue
        int size = copy.size(); // size of postFix queue
        LinkedStack<String> numStack = new LinkedStack(); // stores operands
        

        // main loop 
        for (int i = 0; i < size; i++) {

            String token = copy.first();

            if (!(isOperator(token))) {
                numStack.push(token);

            } else {

                double first = Double.valueOf(numStack.top()); // assings the top of the num stack to first
                numStack.pop(); // pops off the top of the numstack

                double second = Double.valueOf(numStack.top()); // assings the top of the num stack to second
                numStack.pop(); // pops off the top of the numstack

                if (token.equals("+")) {
                    Double temp = second + first; // operation is added to a variable and pushed to the top of the stack
                    numStack.push(String.valueOf(temp));
                } else if (token.equals("-")) {
                    Double temp = second - first; // operation is added to a variable and pushed to the top of the stack
                    numStack.push(String.valueOf(temp));
                } else if (token.equals("*")) {
                    Double temp = second * first; // operation is added to a variable and pushed to the top of the stack
                    numStack.push(String.valueOf(temp));
                } else if (token.equals("/")) {
                    Double temp = second / first; // operation is added to a variable and pushed to the top of the stack
                    numStack.push(String.valueOf(temp));
                }
            }
            copy.enqueue(token);
            copy.dequeue();
        }

        Double returnValue = Double.valueOf(numStack.top());
        return returnValue;
    }

    /**
     * Converts a postfix expression to a LinkedBinaryTree
     *
     * @param postfix
     * @return
     */
    public static LinkedBinaryTree postFixToLinkedBinaryTree(LinkedQueue<String> postfix) {

        LinkedQueue<String> copy = postfix; //copys the postfix into a new linked queue
        int size = copy.size(); //instance variable for the size of the queue
        LinkedStack treeStack = new LinkedStack();    //stack to store the binary trees

        for (int i = 0; i < size; i++) {
            String token = copy.first();

            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) { // if token is an operand, creates a one node binary tree and pushes it onto the stack
                LinkedBinaryTree temp = new LinkedBinaryTree();
                temp.addRoot(token);
                treeStack.push(temp);

            } else { // if token is an operator then a new tree is created with it as the root,
                // then its left and right children are assigned by poping off the top of the tree stack
                //the tree is then pushed onto the treeStack
                LinkedBinaryTree temp = new LinkedBinaryTree();
                temp.addRoot(token);
                temp.addRight(temp.root, treeStack.pop());
                temp.addLeft(temp.root, treeStack.pop());
                treeStack.push(temp);
            }

            copy.enqueue(token);
            copy.dequeue();
        }

        LinkedBinaryTree returnTree = (LinkedBinaryTree) treeStack.top();
        return returnTree;
    }

    /**
     * Solves expressions that are located in a .txt file
     *
     * @param filePath
     */
    public static void solveExpressions(String filePath) {

        File f = new File(filePath); //creates new file at the filePath

        try {
            Scanner scan = new Scanner(f);

            while (scan.hasNextLine()) {
                System.out.println("--------------------------------------------|");
                String expression = scan.nextLine();
                System.out.println("Infix Expression: " + expression);
                LinkedQueue<String> infix = toQueue(expression);
                System.out.println("");

                LinkedQueue<String> postFix = shuntingYard(infix);

                System.out.println("Evaluated form: " + solvePostfix(postFix));
                System.out.println("");

                LinkedBinaryTree tree = HelperMethods.postFixToLinkedBinaryTree(postFix);

                System.out.println("\nInorder traversal of binarytree:");
                HelperMethods.inorder(tree);
                System.out.println("\nPreorder traversal of binarytree:");
                HelperMethods.preorder(tree);
                System.out.println("\nPostorder traversal of binaryTree:");
                HelperMethods.postorder(tree);
                System.out.println("\nEuler's tour of binaryTree:");
                HelperMethods.eulersTour(tree, tree.root);
                System.out.println("\n");
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found!");
        } catch (ArithmeticException ae) {
            System.out.println();
        }

    }

    /**
     * Iterates over a binary expression tree and prints out values
     *
     * @param tree
     */
    public static void iterate(LinkedBinaryTree tree) {

        Iterator iterator = tree.positions().iterator();

        while (iterator.hasNext()) {

            Position p = (Position) iterator.next();

            if (p.getElement() instanceof LinkedBinaryTree) {
                LinkedBinaryTree inside = (LinkedBinaryTree) p.getElement();

                iterate(inside);

            } else {

                System.out.print(p.getElement());

            }
        }

    }

    /**
     * preorder iterates over binary tree and prints out values
     *
     * @param tree
     */
    public static void preorder(LinkedBinaryTree tree) {

        Iterator iterator = tree.preorder().iterator();

        while (iterator.hasNext()) {

            Position p = (Position) iterator.next();
            if (p.getElement() instanceof LinkedBinaryTree) {
                LinkedBinaryTree inside = (LinkedBinaryTree) p.getElement();

                preorder(inside);

            } else {

                System.out.print(p.getElement());

            }
        }

    }

    /**
     * postorder iterates over a binary tree
     *
     * @param tree
     */
    public static void postorder(LinkedBinaryTree tree) {

        Iterator iterator = tree.postorder().iterator();

        while (iterator.hasNext()) {

            Position p = (Position) iterator.next();

            if (p.getElement() instanceof LinkedBinaryTree) {
                LinkedBinaryTree inside = (LinkedBinaryTree) p.getElement();

                postorder(inside);

            } else {
                System.out.print(p.getElement());

            }
        }

    }

    /**
     * Inorder iterates over a binary tree
     *
     * @param tree
     */
    public static void inorder(LinkedBinaryTree tree) {

        Iterator iterator = tree.inorder().iterator();

        while (iterator.hasNext()) {

            Position p = (Position) iterator.next();

            if (p.getElement() instanceof LinkedBinaryTree) {
                LinkedBinaryTree inside = (LinkedBinaryTree) p.getElement();

                inorder(inside);

            } else {
                System.out.print(p.getElement());

            }
        }

    }

    /**
     * Does an eulers tour traversal of a binary tree
     *
     * @param tree
     * @param p
     */
    public static void eulersTour(LinkedBinaryTree tree, Position p) {
        if (p.getElement() instanceof LinkedBinaryTree) {

            LinkedBinaryTree t = (LinkedBinaryTree) p.getElement();
            System.out.print(t.root.getElement());
            if (t.isRoot(p)) {
                System.out.println(p.getElement());
            }
            if (t.left(t.root) != null) {
                eulersTour(t, t.root.getLeft());
            }
            if (t.right(t.root) != null) {
                eulersTour(t, t.root.getRight());
            }

        }

        if (tree.isRoot(p)) {
            System.out.print(p.getElement());
        }
        if (tree.left(p) != null) {
            eulersTour(tree, tree.left(p));
        }
        if (tree.right(p) != null) {
            eulersTour(tree, tree.right(p));
        }

    }

}
