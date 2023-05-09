import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The MyMap class represents a map of letters to information about word containing those letters.
 * It has methods to build and manipulate the map and display the map.
 */
public class MyMap {
    /**
     * The map storing the letter-word information.
     */
    private LinkedHashMap<String, Info> map;
    /**
     * The size of the map.
     */
    private int mapSize;
    /**
     * The input string.
     */
    private String str;

    /**
     * Constructs a MyMap object with an empty map and initializes input string.
     * @param inputStr
     */
    public MyMap(String inputStr) {
        if (inputStr == null || inputStr.isEmpty()) {
            throw new IllegalArgumentException("Input string can't be null");
        }

        this.map = new LinkedHashMap<String, Info>();
        this.mapSize = 0;
        this.str = inputStr;
    }

    /**
     * Removes non letter characters excluding spaces.
     * @throws NullPointerException When parameter string is null.
     */
    public String convertString() throws NullPointerException {
        String cstr = new String(this.str);
        if (cstr == null) {
            throw new NullPointerException("String can't be null!");
        }
        cstr = cstr.toLowerCase();
        cstr = cstr.replaceAll("[^a-z ]", "");

        return cstr;
    }

    /**
     * Builds the map with input string and letters word occurrences.
     */
    public void buildMap() {
        String[] myWords = this.convertString().split(" ");

        for (String word : myWords) {
            for (int i = 0; i < word.length(); i++) {
                String key = Character.toString(word.charAt(i));

                if (this.map.containsKey(key)) {
                    Info info = this.map.get(key);
                    info.push(word);
                } else {
                    this.mapSize++;
                    Info info = new Info();
                    info.push(word);
                    this.map.put(key, info);
                }
            }
        }
    }

    /**
     * Returns the map string letter-Info information.
     * @return The map.
     */
    public LinkedHashMap<String, Info> getMap() {
        return map;
    }

    /**
     * Returns input string.
     * @return The input string.
     */
    public String getStr() {
        return str;
    }

    /**
     * Returns the size of map.
     * @return The size of map.
     */
    public int getMapSize() {
        return mapSize;
    }

    /**
     * Sets the map to the given map.
     * @param map The map to set.
     */
    public void setMap(LinkedHashMap<String, Info> map) {
        if (map == null) {
            throw new IllegalArgumentException("Map can't be null");
        }
        this.map = map;
    }

    /**
     * Displays the original and preprocessed string, and the unsorted map.
     */
    public void display() {
        System.out.println("Original String:        " + this.str);
        System.out.println("Preprocessed String:    " + this.convertString());

        System.out.println();
        System.out.println();

        System.out.println("The original (unsorted) map:");
        for (Map.Entry<String, Info> item : map.entrySet()) {
            String key = item.getKey();
            Info value = item.getValue();
            System.out.println("Letter: " + key + " - " + value.toString());
        }
    }

    /**
     * Displays the map.
     */
    public void displayMap() {
        for (Map.Entry<String, Info> item : map.entrySet()) {
            String key = item.getKey();
            Info value = item.getValue();
            System.out.println("Letter: " + key + " - " + value.toString());
        }
    }
}
