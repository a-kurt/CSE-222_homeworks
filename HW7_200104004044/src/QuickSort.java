import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The QuickSort class is responsible for sorting a map using the insertion sort algorithm.
 */
public class QuickSort {
    private MyMap originalMap;
    private MyMap sortedMap;

    /**
     * Constructs an QuickSort object with the specified original map.
     * @param originalMap The original map to be sorted.
     * @throws IllegalArgumentException if the originalMap parameter is null.
     */
    public QuickSort(MyMap originalMap) throws IllegalArgumentException {
        if (originalMap == null) {
            throw new IllegalArgumentException("Original map can't be null.");
        }
        this.originalMap = originalMap;
        this.sortedMap = new MyMap(originalMap.getStr());
    }

    /**
     * Sorts the original map using the Quick sort algorithm.
     *
     * @throws IllegalArgumentException if the original map is empty or contains invalid data.
     */
    public void quickSort() throws IllegalArgumentException {
        Set<String> mapKeys = this.originalMap.getMap().keySet();
        String[] keysList = mapKeys.toArray(new String[mapKeys.size()]);
        if (keysList.length == 0) {
            throw new IllegalArgumentException("Invalid string.");
        }
        long startTime = System.nanoTime();
        this.sort(keysList, 0, keysList.length-1);
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000;
        System.out.println("Quick Sort: Input: " + this.originalMap.getStr() + ", Execution Time: " + executionTime);

        this.setSortedMap(keysList);
    }

    /**
     * Recursively sorts the array of keys using Quick sort algorithm.
     * @param arr The array of keys.
     * @param startIndex The starting index of the subarray.
     * @param pivotIndex The index of the pivot element.
     */
    private void sort(String[] arr, int startIndex, int pivotIndex) {
        if (startIndex < pivotIndex) {
            int newPivotIndex = this.divideAndConquer(arr, startIndex, pivotIndex);
            sort(arr, startIndex, newPivotIndex-1);
            sort(arr, newPivotIndex+1, pivotIndex);
        }
    }

    /**
     * Compares elements with the pivot element and if the element is smaller or equal to the pivot arranges it to it left if its bigger arranges it to its right.
     * @param keyList The array of keys.
     * @param startIndex The starting index of the subarray.
     * @param pivotIndex The index of the pivot element.
     * @return The new index of the pivot element after comparing.
     */
    private int divideAndConquer(String[] keyList, int startIndex, int pivotIndex) {
        String pivot = keyList[pivotIndex];
        Info pivotInfo = this.originalMap.getMap().get(pivot);

        int j = startIndex - 1;
        for (int i = startIndex; i < pivotIndex; i++) {
            Info curr = this.originalMap.getMap().get(keyList[i]);
            if (pivotInfo.getCount() >= curr.getCount()) {
                j++;
                if (curr.getCount() == pivotInfo.getCount()) {
                    String currKey = keyList[i];
                    String swapKey = keyList[pivotIndex];

                    for (int k = 0; k < this.originalMap.getStr().length(); k++) {
                        if (currKey.equals(String.valueOf(this.originalMap.getStr().charAt(k)))) {
                            this.swap(keyList, i, j);
                            break;
                        }
                        if (swapKey.equals(String.valueOf(this.originalMap.getStr().charAt(k)))) {
                            this.swap(keyList, i, pivotIndex);
                            pivotInfo = this.originalMap.getMap().get(keyList[pivotIndex]);
                            this.swap(keyList, i, j);
                            break;
                        }
                    }
                } else {
                    this.swap(keyList, i, j);
                }
            }
        }
        this.swap(keyList, j+1, pivotIndex);
        return j+1;
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
