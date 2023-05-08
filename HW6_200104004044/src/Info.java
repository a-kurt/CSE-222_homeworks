public class Info {
    private int count;
    private String[] words;

    public Info() {
        this.count = 0;
        this.words = new String[0];
    }

    public void push(String word) {
        this.count++;
        String[] newWords = new String[this.count];

        for (int i = 0; i < this.words.length; i++) {
            newWords[i] = this.words[i];
        }
        newWords[this.count-1] = word;
        this.words = newWords;
    }

    public int getCount() {
        return this.count;
    }

    public String[] getWords() {
        return this.words;
    }

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
