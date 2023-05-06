import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;


/**
 * @Author Atakan Kurt - 200104004044
 * A tree implemented directory representation.
 * It has different kinds of search algorithms implemented, also you can move nodes.
 */
public class MyTree {
    /**
     * 2D String Array holds data from text file.
     */
    private String[][] data;

    /**
     * Root node of the tree.
     */
    private DefaultMutableTreeNode root;

    /**
     * JFrame display variable.
     */
    private JFrame f;
    public static void main(String[] args) {
        MyTree m = new MyTree();
        try {
            m.readFile("./src/tree.txt");
            m.displayTreeStructure();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to continue...");
            scanner.nextLine();

            m.hideTreeStructure();
            while (true) {
                System.out.println("Which part do you want to select? (B, C, D, or E)");
                String part = scanner.nextLine().toUpperCase();


                switch (part) {
                    case "B":
                        String targetB = getTargetString(scanner);
                        m.breadthFirstSearch(targetB);
                        break;
                    case "C":
                        String targetC = getTargetString(scanner);
                        m.depthFirstSearch(targetC);
                        break;
                    case "D":
                        String targetD = getTargetString(scanner);
                        m.traversalSearch(targetD);
                        break;
                    case "E":
                        Stack<String> stack = new Stack<String>();
                        String input = "";
                        while (!input.equals("q")) {
                            System.out.println("Enter the path (type 'q' to finish):");
                            input = scanner.nextLine();
                            if (!input.equals("q")) {
                                stack.push(input);
                            }
                        }

                        String[] arr = new String[stack.size()];
                        for (int i = arr.length-1; i >= 0; i--)
                            arr[i] = stack.pop();

                        String targetE = getTargetString(scanner);
                        m.moveNode(arr, targetE);
                        break;
                    case "Q":
                        return;
                    default:
                        System.out.println("Invalid selection. Please choose B, C, D, or E.");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor
     */
    public MyTree() {
        this.root = new DefaultMutableTreeNode("Root");
        this.f = new JFrame();
    }

    private static String getTargetString(Scanner scanner) {
        String target = "";
        while (target.isEmpty() || !target.equals(target.toUpperCase())) {
            System.out.println("Please enter a target string (fully uppercase):");
            target = scanner.nextLine();
        }
        return target;
    }

    /**
     * Reads file and puts data to 2D Array.
     * @param fileName file to be read
     * @throws FileNotFoundException if file is not exists
     */
    public void readFile(String fileName) throws FileNotFoundException {
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        File myFile = new File(fileName);
        if (!myFile.exists()) {
            throw new FileNotFoundException("There is no file existing with this name: " + fileName);
        }

        Scanner myReader = new Scanner(myFile);
        int rows = 0;

        while (myReader.hasNextLine()) {
            myReader.nextLine();
            rows++;
        }
        if (rows == 0) {
            throw new IllegalArgumentException("File is empty");
        }
        myReader.close();

        this.data = new String[rows][];
        myReader = new Scanner(myFile);

        for (int i = 0; i < rows; i++) {
            String line = myReader.nextLine();
            String[] words = line.split(";");
            this.data[i] = words;
        }
        for (String[] ele : this.data) {
            addNode(this.root, ele, 0);
        }
        myReader.close();
    }

    /**
     * Method to display directories.
     */
    public void displayTreeStructure() {
        JTree jt = new JTree(this.root);
        f.add(jt);
        f.setSize(400,800);
        f.setVisible(true);
    }

    public void hideTreeStructure() {
        f.setVisible(false);
    }
    /**
     * Adds children to root node. Builds tree.
     * @param rootNode parent node.
     * @param arr 2D String data array
     * @param i index
     */
    public void addNode(DefaultMutableTreeNode rootNode, String[] arr, int i) {
        if (arr.length <= i) {
            return;
        } else {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(arr[i]);
            int j = this.getChildIndex(rootNode, child);
            if (j < 0) {
                rootNode.add(child);
                addNode(child, arr, i+1);
            } else {
                DefaultMutableTreeNode insideNode = (DefaultMutableTreeNode) rootNode.getChildAt(j);
                addNode(insideNode, arr, i+1);
            }
        }
    }

    /**
     * Helper function to get child element index inside of its parent node.
     * @param parentNode parent node
     * @param childNode child node to be searched
     * @return index of childNode
     */
    private int getChildIndex(DefaultMutableTreeNode parentNode, DefaultMutableTreeNode childNode) {
        for (int i = 0; i < parentNode.getChildCount(); i++) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);

            if (currentNode.getUserObject().equals(childNode.getUserObject())) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Checks target in the tree. Using BFS algorithm.
     * @param target target Node Name.
     */
    public void breadthFirstSearch(String target) {
        if (target == null) {
            throw new NullPointerException("Target cannot be null.");
        }
        Queue<DefaultMutableTreeNode> searchOrder = new LinkedList<>();
        int count = 1;

        searchOrder.add(this.root);
        while(!searchOrder.isEmpty()) {
            DefaultMutableTreeNode curr = searchOrder.remove();
            System.out.print("Step " + count + " -> " + curr.getUserObject());
            if (curr.getUserObject().equals(target)) {
                System.out.println("(Found!)");
                return;
            }

            for (int i = 0; i < curr.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) curr.getChildAt(i);
                searchOrder.add(child);
            }

            System.out.println();
            count++;
        }

        System.out.println(target + " not found!");
    }

    /**
     * Checks target in the tree. Using DFS algorithm.
     * @param target target Node Name.
     */
    public void depthFirstSearch(String target) {
        if (target == null) {
            throw new NullPointerException("Target cannot be null.");
        }
        Stack<DefaultMutableTreeNode> searchOrder = new Stack<>();
        int count = 1;

        searchOrder.push(this.root);
        while(!searchOrder.empty()) {
            DefaultMutableTreeNode curr = searchOrder.pop();
            System.out.print("Step " + count + " -> " + curr.getUserObject());
            if (curr.getUserObject().equals(target)) {
                System.out.println("(Found!)");
                return;
            }

            for (int i = 0; i < curr.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) curr.getChildAt(i);
                searchOrder.push(child);
            }

            System.out.println();
            count++;
        }

        System.out.println(target + " not found!");
    }

    /**
     * Checks target in the tree. Using post traversal search algorithm.
     * @param target target Node Name.
     */
    public void traversalSearch(String target) {
        if (target == null) {
            throw new NullPointerException("Target cannot be null.");
        }
        Stack<DefaultMutableTreeNode> searchOrder = new Stack<>();
        int count = 1;

        fillOrder(searchOrder, this.root);

        while (searchOrder.size() != 1) { // !searchOrder.empty() to include Root element
            DefaultMutableTreeNode curr = searchOrder.pop();
            System.out.print("Step " + count + " -> " + curr.getUserObject());
            if (curr.getUserObject().equals(target)) {
                System.out.println("(Found!)");
                return;
            }
            System.out.println();
            count++;
        }

        System.out.println(target + " not found!");
    }

    /**
     * Helper function that fills the stack that holds searchOrder.
     * @param searchOrder stack to be filled.
     * @param parent parent node.
     */
    private void fillOrder(Stack<DefaultMutableTreeNode> searchOrder, DefaultMutableTreeNode parent) {
        searchOrder.push(parent);
        for (int i = parent.getChildCount()-1; i >= 0; i--) {
            DefaultMutableTreeNode curr = (DefaultMutableTreeNode) parent.getChildAt(i);
            fillOrder(searchOrder, curr);
        }
    }

    /**
     * Moves node of the given path to given target.
     * @param path path to the element to be removed.
     * @param target target node to be moved to.
     * @throws NullPointerException if path or target is null.
     */
    public void moveNode(String[] path, String target) throws NullPointerException {
        if (path == null) {
            throw new NullPointerException("Path can't be null");
        }
        if (path == null) {
            throw new NullPointerException("Target can't be null");
        }
        if (!this.isNodeExists(path)) {
            System.out.print("Cannot move ");
            for (int i = 0; i < path.length; i++) {
                System.out.print(path[i]);
                if (i != path.length-1) System.out.print("->");
            }
            System.out.println(" because it doesn't exist in the tree.");
            return;
        }

        int targetIndex = this.getChildIndex(this.root, target);
        DefaultMutableTreeNode targetNode;
        if (targetIndex == -1) {
            targetNode = new DefaultMutableTreeNode(target);
            this.root.add(targetNode);
        } else {
            targetNode = (DefaultMutableTreeNode) this.root.getChildAt(targetIndex);
        }

        /* Generating node block to move */
        String[] targetPath = path.clone();
        targetPath[0] = target;
        boolean isOverWrite = this.isNodeExists(targetPath);

        DefaultMutableTreeNode nodeToMove = this.getChildNode(path);
        for (int i = path.length-2; i >= 1; i--) {
            DefaultMutableTreeNode newParent = new DefaultMutableTreeNode(path[i]);
            newParent.add(nodeToMove);
            nodeToMove = newParent;
        }


        /* Adding and printing move */
        if (isOverWrite) {
            System.out.print("Moved ");
            for (int i = 0; i < path.length; i++) {
                System.out.print(path[i]);
                if (i != path.length-1) System.out.print("->");
            }
            System.out.println(" to " + target + ".");

            for (int i = 0; i < targetPath.length; i++) {
                System.out.print(path[i]);
                if (i != path.length-1) System.out.print("->");
            }
            System.out.println(" has been overwritten.");
        } else {
            targetNode.add(nodeToMove);
            System.out.print("Moved ");
            for (int i = 0; i < path.length; i++) {
                System.out.print(path[i]);
                if (i != path.length-1) System.out.print("->");
            }
            System.out.println(" to " + target + ".");
        }

        this.removeNode(path);

        /* Visualizing JFrame again */
        this.f = new JFrame();
        JTree njt = new JTree(this.root);
        this.f.add(njt);
        f.setSize(400, 800);
        f.setVisible(true);
    }

    /**
     * Remove the node of the given path
     * @param path path to be removed.
     */
    private void removeNode(String[] path) {
        DefaultMutableTreeNode curr = this.getChildNode(path);
        while (!curr.getUserObject().equals("Root")) {
            if (curr.getChildCount() == 0) {
                curr.removeFromParent();
                return;
            }
            curr = (DefaultMutableTreeNode) curr.getParent();
        }
    }

    /**
     * Checks if the given path leads to existing node.
     * @param path path to be travelled.
     * @return true if exists else otherwise.
     */
    private boolean isNodeExists(String[] path) {
        DefaultMutableTreeNode curr = this.root;
        for (int i = 0; i < path.length; i++) {
            int x = this.getChildIndex(curr, path[i]);
            if (x == -1) return false;
            curr = (DefaultMutableTreeNode) curr.getChildAt(x);
        }

        return true;
    }

    /**
     * Gets child index based on children string name.
     * @param parentNode root node to be travel.
     * @param childNodeName node string name to be searched
     * @return index of the searched node.
     */
    private int getChildIndex(DefaultMutableTreeNode parentNode, String childNodeName) {
        for (int i = 0; i < parentNode.getChildCount(); i++) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);

            if (currentNode.getUserObject().equals(childNodeName)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Helper function to get last path node.
     * @param path Path to travel.
     * @return last path node.
     */
    private DefaultMutableTreeNode getChildNode(String[] path) {
        DefaultMutableTreeNode curr = this.root;
        for (int i = 0; i < path.length; i++) {
            int x = this.getChildIndex(curr, path[i]);
            if (x == -1) break;
            curr = (DefaultMutableTreeNode) curr.getChildAt(x);
        }

        return curr;
    }

}