import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The BubbleSort class is responsible for sorting a map using the bubble sort algorithm.
 */
public class BubbleSort {
    private MyMap originalMap;
    private MyMap sortedMap;

    /**
     * Constructs and BubbleSort object with the specified original map.
     * @param originalMap The original map to be sorted.
     * @throws IllegalArgumentException if the originalMap parameter is null.
     */
    public BubbleSort(MyMap originalMap) throws IllegalArgumentException {
        if (originalMap == null) {
            throw new IllegalArgumentException("Original map can't be null.");
        }
        this.originalMap = originalMap;
        this.sortedMap = new MyMap(originalMap.getStr());
    }

    /**
     * Sorts the original map using the bubble sort algorithm.
     * @throws NullPointerException if the original map is null.
     */
    public void bubbleSort() throws NullPointerException {
        if (originalMap == null || originalMap.getMap() == null) {
            throw new NullPointerException("Map is null");
        }
        Set<String> mapKeys = this.originalMap.getMap().keySet();
        String[] keysList = mapKeys.toArray(new String[mapKeys.size()]);

        long startTime = System.nanoTime();

        for (int i = 0; i < keysList.length; i++) {
            int currKeyIndex = 0;
            Info currInfo = this.originalMap.getMap().get(keysList[currKeyIndex]);

            for (int j = 1; j < keysList.length; j++) {
                if (this.originalMap.getMap().get(keysList[j]).getCount() < currInfo.getCount()) {
                    this.swap(keysList, currKeyIndex, j);
                    currKeyIndex = j;
                } else {
                    currKeyIndex = j;
                    currInfo = this.originalMap.getMap().get(keysList[currKeyIndex]);
                }
            }
        }
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000;
        System.out.println("Bubble Sort: Input: " + this.originalMap.getStr() + ", Execution Time: " + executionTime);


        this.setSortedMap(keysList);
    }

    /**
     * Fills an LinkedHashMap with given array of keys and sets it to a map.
     * @param arr Array of map keys.
     */
    private void setSortedMap(String[] arr) {
        LinkedHashMap<String, Info> sorted = new LinkedHashMap<>();

        for (String key : arr) {
            sorted.put(key, originalMap.getMap().get(key));
        }

        this.sortedMap.setMap(sorted);
    }

    /**
     * Swap the elements in given index.
     * @param arr Array to be manipulated
     * @param i first element index
     * @param j second element index
     */
    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Returns the sorted map.
     * @return the sorted map.
     */
    public MyMap getSortedMap() {
        return sortedMap;
    }
}
