import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The InsertionSort class is responsible for sorting a map using insertion sort algorithm.
 */
public class InsertionSort {
    private MyMap originalMap;
    private MyMap sortedMap;

    /**
     * Constructs an InsertionSort object with the specified original map.
     * @param originalMap The original map to be sorted.
     * @throws IllegalArgumentException if the originalMap parameter is null.
     */
    public InsertionSort(MyMap originalMap) throws IllegalArgumentException {
        if (originalMap == null) {
            throw new IllegalArgumentException("Original map can't be null.");
        }
        this.originalMap = originalMap;
        this.sortedMap = new MyMap(originalMap.getStr());
    }

    /**
     * Sorts the original map using the insertion sort algorithm.
     * @throws NullPointerException if the original map is null.
     */
    public void insertionSort() throws NullPointerException {
        if (originalMap == null || originalMap.getMap() == null) {
            throw new NullPointerException("Map is null");
        }
        Set<String> mapKeys = this.originalMap.getMap().keySet();
        String[] keysList = mapKeys.toArray(new String[mapKeys.size()]);

        long startTime = System.nanoTime();
        for (int i = 0; i < keysList.length; i++) {
            for (int j = i; j >= 1; j--) {
                Info currInfo = this.originalMap.getMap().get(keysList[j]);
                Info beforeInfo = this.originalMap.getMap().get(keysList[j-1]);
                if (currInfo.getCount() < beforeInfo.getCount()) {
                    this.swap(keysList, j, j-1);
                }
            }
        }

        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000;
        System.out.println("Insertion Sort: Input: " + this.originalMap.getStr() + ", Execution Time: " + executionTime);

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
