/**
 * The Info class represents an array of words along with their count.
 * It provides methods to add words, retrieve the count, and retrieve the words.
 */
public class Info {
    private int count;
    private String[] words;

    /**
     * Constructs an Info object with an initial count of 0 and an empty array of words.
     */
    public Info() {
        this.count = 0;
        this.words = new String[0];
    }

    /**
     * Adds a word to the collection and increments the count.
     * @param word The word to be added.
     */
    public void push(String word) {
        if (word == null || word.equals("")) {
            throw new IllegalArgumentException("Word can't be null or empty.");
        }
        this.count++;
        String[] newWords = new String[this.count];

        for (int i = 0; i < this.words.length; i++) {
            newWords[i] = this.words[i];
        }
        newWords[this.count-1] = word;
        this.words = newWords;
    }

    /**
     * Returns the number of words in the array.
     * @return The count of words.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Returns an array of all the words in the array.
     * @return An array containing the words.
     */
    public String[] getWords() {
        return this.words;
    }

    /**
     * Returns a string representation of the Info object.
     * @return A string containing the count and the words in the array.
     */
    public String toString() {
        String result = "";
        result += "Count: " + count;

        if (words.length > 0) {
            result += " - Words: [";

            for (int i = 0; i < words.length; i++) {
                result += words[i];

                if (i < words.length - 1) {
                    result += ", ";
                }
            }

            result += "]";
        }
        return result;
    }
}
