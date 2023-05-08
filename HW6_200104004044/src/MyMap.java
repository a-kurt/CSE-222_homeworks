import java.util.LinkedHashMap;
import java.util.Map;

public class MyMap {
    public static void main(String[] args) {
        MyMap m = new MyMap("abc aba");
        m.buildMap();
        m.display();
    }

    private LinkedHashMap<String, Info> map;
    private int mapSize;
    private String str;

    /**
     * Removes non letter characters excluding spaces.
     * @throws NullPointerException When parameter string is null.
     */
    public String convertString() throws NullPointerException {
        String cstr = new String(this.str);
        if (cstr.equals(null)) {
            throw new NullPointerException("String can't be null!");
        }
        cstr = cstr.toLowerCase();
        cstr.replaceAll("[^a-z ]", "");

        return cstr;
    }

    public MyMap(String inputStr) {
        this.map = new LinkedHashMap<String, Info>();
        this.mapSize = 0;
        this.str = inputStr;
    }

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
}
