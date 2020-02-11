import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class SnakeData {
    public static int UP = 0;
    public static int DOWN = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    public int length = 3;
    public List<Point> body = new ArrayList<Point>();
    public int direction = RIGHT;

    public SnakeData() {
        body.add(new Point(1, 1));
        body.add(new Point(2, 1));
        body.add(new Point(3, 1));
    }
}