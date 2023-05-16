import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The SelectionSort class represents a selection sort algorithm for sorting a map based on Info object values.
 */
public class SelectionSort {
    private MyMap originalMap;
    private MyMap sortedMap;

    /**
     * Constructs a SelectionSort object with specified original map.
     * @param originalMap the original map to be sorted (can't be null)
     * @throws IllegalArgumentException if the original map is null.
     */
    public SelectionSort(MyMap originalMap) throws IllegalArgumentException {
        if (originalMap == null) {
            throw new IllegalArgumentException("Original map can't be null.");
        }
        this.originalMap = originalMap;
        this.sortedMap = new MyMap(originalMap.getStr());
    }

    /**
     * Sorts the original map using the selection sort algorithm.
     * @throws NullPointerException if the original map is null.
     */
    public void selectionSort() throws NullPointerException {
        if (originalMap == null || originalMap.getMap() == null) {
            throw new NullPointerException("Map is null");
        }
        Set<String> mapKeys = this.originalMap.getMap().keySet();
        String[] keysList = mapKeys.toArray(new String[mapKeys.size()]);
        int minKeyIndex;

        for (int i = 0; i < keysList.length; i++) {
            minKeyIndex = i;
            Info minKeyInfo = originalMap.getMap().get(keysList[minKeyIndex]);

            for (int j = i; j < keysList.length; j++) {
                Info currentInfo = originalMap.getMap().get(keysList[j]);

                if (currentInfo.getCount() < minKeyInfo.getCount()) {
                    minKeyIndex = j;
                    minKeyInfo = currentInfo;
                }
                if (currentInfo.getCount() == minKeyInfo.getCount()) {
                    String minKeyLetter = keysList[minKeyIndex];
                    String currentKeyLetter = keysList[j];

                    for (int k = 0; k < this.originalMap.getStr().length(); k++) {
                        if (minKeyLetter.equals(String.valueOf(this.originalMap.getStr().charAt(k)))) {
                            break;
                        }
                        if (currentKeyLetter.equals(String.valueOf(this.originalMap.getStr().charAt(k)))) {
                            minKeyIndex = j;
                            minKeyInfo = currentInfo;
                            break;
                        }
                    }
                }
            }

            swap(keysList, i, minKeyIndex);
        }

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
