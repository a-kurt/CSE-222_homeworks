import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class CSE222Map {
    public static void main(String[] args) {
        CSE222Map m = new CSE222Map("/home/atakan/Desktop/CSE-222_homeworks/HW8_200104004044/src/test.txt");
        int[][] c = m.getMap();
        System.out.println(m.getStart().toString());
        System.out.println(m.getEnd().toString());
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++)
                System.out.print(c[i][j]);
            System.out.println();
        }

    }
    private int[][] map;
    private Coordinate start;
    private Coordinate end;
    private String fileName;

    public CSE222Map(String fileName) {
        this.fileName = fileName;
        this.readMapFromFile();
    }
    private void readMapFromFile() {
        try {
            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);
            List<int[]> lines = new ArrayList<>();
            int lineCount = 0;


            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] paths = data.split(",");
                int[] row = new int[paths.length];

                for (int i = 0; i < paths.length; i++) {
                    if (paths[i].equals("-1")) {
                        row[i] = 1;
                    } else {
                        row[i] = Integer.parseInt(paths[i]);
                    }
                }

                if (lineCount == 0) {
                    this.start = new Coordinate(row[0], row[1]);
                } else if (lineCount == 1) {
                    this.end = new Coordinate(row[0], row[1]);
                } else {
                    lines.add(row);
                }
                lineCount++;
            }

            this.map = new int[lines.size()][lines.get(0).length];
            for (int i = 0; i < lines.size(); i++)
                this.map[i] = lines.get(i);


        } catch (FileNotFoundException e) {
            System.out.println("ERROR! An error occurred in file.");
            e.printStackTrace();
        }
    }

    public int[][] getMap() {
        return map;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
}
