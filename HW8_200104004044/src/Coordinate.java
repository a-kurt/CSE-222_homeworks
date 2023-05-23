public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int y, int x) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("ERROR! INVALID COORDINATES!");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Coordinate [");
        sb.append("y=").append(y);
        sb.append(", x=").append(x);
        sb.append(']');
        return sb.toString();
    }
}
