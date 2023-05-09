public class Main {
    public static void main(String[] args) {
        MyMap m = new MyMap("'Hush, hush!' whispered the rushing wind.");
        MergeSort s = new MergeSort(m);
        m.buildMap();
        m.display();
        s.mergeSort();

        System.out.println();
        System.out.println("The sorted map:");
        s.getSortedMap().displayMap();
    }
}
