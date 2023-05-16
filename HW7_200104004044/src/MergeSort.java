import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

/**
 * This class implements the merge sort algorithm for sorting a custom map.
 */
public class MergeSort {
    private MyMap originalMap;
    private MyMap sortedMap;

    /**
     * Constructs a MergeSort object with the original map.
     *
     * @param originalMap The original map to be sorted.
     */
    public MergeSort(MyMap originalMap) throws IllegalArgumentException {
        if (originalMap == null) {
            throw new IllegalArgumentException("Original map can't be null.");
        }
        this.originalMap = originalMap;
        this.sortedMap = new MyMap(originalMap.getStr());
    }

    /**
     * Sorts the original map using the merge sort algorithm.
     */
    public void mergeSort() {
        LinkedHashMap<String, Info> sortedMap = this.sort(this.originalMap.getMap());
        this.sortedMap.setMap(sortedMap);
    }

    /**
     * Recursively sorts the given map using the merge sort algorithm.
     *
     * @param myMap The map to be sorted.
     * @return The sorted map.
     */
    private LinkedHashMap<String, Info> sort(LinkedHashMap<String, Info> myMap) throws NullPointerException {
        if (myMap == null) {
            throw new NullPointerException("Input map can't be null");
        }
        if (myMap.size() <= 1) {
            return myMap;
        }
        LinkedHashMap<String, Info> left = new LinkedHashMap<>();
        LinkedHashMap<String, Info> right = new LinkedHashMap<>();

        int i = 0;
        for (Map.Entry<String, Info> entry : myMap.entrySet()) {
            String key = entry.getKey();
            Info value = entry.getValue();
            if (i < myMap.size() / 2)
                left.put(key, value);
            else
                right.put(key, value);
            i++;
        }

        left = this.sort(left);
        right = this.sort(right);

        return this.merge(left, right);
    }

    /**
     * Merges two sorted maps into a single sorted map.
     *
     * @param mapLeft  The left map to be merged.
     * @param mapRight The right map to be merged.
     * @return The merged sorted map.
     */
    private LinkedHashMap<String, Info> merge(LinkedHashMap<String, Info> mapLeft,
                                              LinkedHashMap<String, Info> mapRight) {
        if (mapLeft == null || mapRight == null) {
            throw new NullPointerException("Input maps can't be null");
        }
        LinkedHashMap<String, Info> outputMap = new LinkedHashMap<>();

        Set<String> leftMapKeys = mapLeft.keySet();
        Set<String> rightMapKeys = mapRight.keySet();

        List<String> leftKeysList = new ArrayList<>(leftMapKeys);
        List<String> rightKeysList = new ArrayList<>(rightMapKeys);

        int leftIndex = 0;
        int rightIndex = 0;
        int numOfOperations = mapLeft.size() + mapRight.size();

        while (numOfOperations > 0) {
            if (leftIndex == mapLeft.size()) {
                outputMap.put(rightKeysList.get(rightIndex) ,mapRight.get(rightKeysList.get(rightIndex)));
                rightIndex++;
            } else if (rightIndex == mapRight.size()) {
                outputMap.put(leftKeysList.get(leftIndex) ,mapLeft.get(leftKeysList.get(leftIndex)));
                leftIndex++;
            } else if (mapLeft.get(leftKeysList.get(leftIndex)).getCount() <= mapRight.get(rightKeysList.get(rightIndex)).getCount()) {
                outputMap.put(leftKeysList.get(leftIndex) ,mapLeft.get(leftKeysList.get(leftIndex)));
                leftIndex++;
            } else {
                outputMap.put(rightKeysList.get(rightIndex) ,mapRight.get(rightKeysList.get(rightIndex)));
                rightIndex++;
            }

            numOfOperations--;
        }

        return outputMap;
    }

    /**
     * Returns sortedMap object.
     * @return The sorted map.
     */
    public MyMap getSortedMap() {
        return sortedMap;
    }
}
