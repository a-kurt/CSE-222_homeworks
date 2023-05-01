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

public class MyTree {
    public String[][] data;
    private DefaultMutableTreeNode root;
    public static void main(String[] args) {
        MyTree m = new MyTree();
        try {
            m.readFile("./src/tree.txt");
            // print the contents of the data array using Arrays.deepToString()
            System.out.println(Arrays.deepToString(m.data));
            m.displayTreeStructure();
            // m.breadthFirstSearch("CSE2332"); // TODO: Make it user imputable
            m.depthFirstSearch("CSE2332");

        } catch (FileNotFoundException e) {
            // print the exception message to the console
            System.out.println(e.getMessage());
        }
    }

    public MyTree() {
        this.root = new DefaultMutableTreeNode("Root");
    }

    public void readFile(String fileName) throws FileNotFoundException {
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

        myReader.close();

        this.data = new String[rows][];
        myReader = new Scanner(myFile);

        for (int i = 0; i < rows; i++) {
            String line = myReader.nextLine();
            String[] words = line.split(";");
            this.data[i] = words;
        }

        myReader.close();
    }

    public void displayTreeStructure() {
        JFrame f = new JFrame();

        for (String[] ele : this.data) {
            addNode(this.root, ele, 0);
        }

        JTree jt = new JTree(this.root);
        f.add(jt);
        f.setSize(400,800);
        f.setVisible(true);
    }

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

    private int getChildIndex(DefaultMutableTreeNode parentNode, DefaultMutableTreeNode childNode) {
        for (int i = 0; i < parentNode.getChildCount(); i++) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);

            if (currentNode.getUserObject().equals(childNode.getUserObject())) {
                return i;
            }
        }

        return -1;
    }

    public void breadthFirstSearch(String target) {
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

    public void depthFirstSearch(String target) {
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

}

// TODO: Ödevin sözel açıklaması - rapor