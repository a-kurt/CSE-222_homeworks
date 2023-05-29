import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;
import java.io.File;

public class PNGGenerator {
    private int[][] map;
    private List<Coordinate> path;

    public PNGGenerator(int[][] map, List<Coordinate> path) {
        this.map = map;
        this.path = path;
    }

    public void convertPNG() {
        int width = this.map[0].length, height = this.map.length;
        int imageSize = 1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int x = col * imageSize;
                int y = row * imageSize;
                int value = this.map[row][col];

                Color color = (value == 1) ? Color.BLACK : Color.WHITE;
                graphics.setColor(color);
                graphics.fillRect(x, y, imageSize, imageSize);
            }
        }

        graphics.setColor(Color.RED);
        for (Coordinate coor : this.path) {
            int y = coor.getY() * imageSize;
            int x = coor.getX() * imageSize;
            graphics.fillRect(x, y, imageSize, imageSize);
        }

        graphics.dispose();

        try {
            ImageIO.write(image, "png", new File("output.png"));
            System.out.println("Image saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }
}
