/**
 * @Author: Atakan Kurt - 200104004044
 * Homework-7
 */
public class Main {
    public static void main(String[] args) {
        String orderedCase = "abbcccddddeeeee";
        String randomCase = "bcdeedcdcebadee";
        String reverseOrderedCase = "eeeeeddddcccbba";

//        testMergeSort(orderedCase);
//        testMergeSort(randomCase);
//        testMergeSort(reverseOrderedCase);
//
//        testSelectionSort(orderedCase);
//        testSelectionSort(randomCase);
//        testSelectionSort(reverseOrderedCase);
//
//        testInsertionSort(orderedCase);
//        testInsertionSort(randomCase);
//        testInsertionSort(reverseOrderedCase);
//
//        testBubbleSort(orderedCase);
//        testBubbleSort(randomCase);
//        testBubbleSort(reverseOrderedCase);
//
//        testQuickSort(orderedCase);
//        testQuickSort(randomCase);
//        testQuickSort(reverseOrderedCase);
    }

    public static void testMergeSort(String str) {
        MyMap m = new MyMap(str);
        MergeSort s = new MergeSort(m);
        m.buildMap();
        m.display();
        s.mergeSort();

        System.out.println();
        System.out.println("The sorted map:");
        s.getSortedMap().displayMap();
        System.out.println("============================================");
    }

    public static void testSelectionSort(String str) {
        MyMap m = new MyMap(str);
        SelectionSort s = new SelectionSort(m);
        m.buildMap();
        m.display();
        s.selectionSort();

        System.out.println();
        System.out.println("The sorted map:");
        s.getSortedMap().displayMap();
        System.out.println("============================================");
    }

    public static void testInsertionSort(String str) {
        MyMap m = new MyMap(str);
        InsertionSort s = new InsertionSort(m);
        m.buildMap();
        m.display();
        s.insertionSort();

        System.out.println();
        System.out.println("The sorted map:");
        s.getSortedMap().displayMap();
        System.out.println("============================================");
    }

    public static void testBubbleSort(String str) {
        MyMap m = new MyMap(str);
        BubbleSort s = new BubbleSort(m);
        m.buildMap();
        m.display();
        s.bubbleSort();

        System.out.println();
        System.out.println("The sorted map:");
        s.getSortedMap().displayMap();
        System.out.println("============================================");
    }

    public static void testQuickSort(String str) {
        MyMap m = new MyMap(str);
        QuickSort s = new QuickSort(m);
        m.buildMap();
        m.display();
        s.quickSort();

        System.out.println();
        System.out.println("The sorted map:");
        s.getSortedMap().displayMap();
        System.out.println("============================================");
    }
}
