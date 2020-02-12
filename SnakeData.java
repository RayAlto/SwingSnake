import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.lang.Math;

public class SnakeData {
    public static int UP = 0;
    public static int DOWN = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    public static int BLANK = 0;
    public static int BODY = 1;
    public static int HEART = 2;
    public int length = 3;
    public List<Point> body = new ArrayList<Point>();
    public int bodyMap[][] = new int[60][38];
    public int direction = RIGHT;
    public Point heart = new Point(0, 0);

    public SnakeData() {
        body.add(new Point(1, 1));
        bodyMap[1][1] = BODY;
        body.add(new Point(2, 1));
        bodyMap[2][1] = BODY;
        body.add(new Point(3, 1));
        bodyMap[3][1] = BODY;
        refreshHeartLocation();
    }

    public boolean refreshHeartLocation() {
        if (body.size() == 2280) {// 蛇达到了理论最大长度
            return false;
        }
        boolean conflict = false;
        int randX, randY;
        do {
            randX = (int) (Math.random() * 60);
            randY = (int) (Math.random() * 38);
            conflict = bodyMap[randX][randY] == BODY ? true : false;
        } while (conflict);
        bodyMap[randX][randY] = HEART;// 设置新的心的位置
        heart.x = randX;
        heart.y = randY;
        return true;
    }

    public boolean process() {
        Point snakeHead = body.get(body.size() - 1);
        int nextPositionItem = 0;
        Point nextPoint = new Point();

        if (direction == UP) {
            nextPoint.x = snakeHead.x;
            nextPoint.y = snakeHead.y - 1;
            if (!checkNextPoint(nextPoint))
                return false;
            nextPositionItem = bodyMap[snakeHead.x][snakeHead.y - 1];
        } else if (direction == LEFT) {
            nextPoint.x = snakeHead.x - 1;
            nextPoint.y = snakeHead.y;
            if (!checkNextPoint(nextPoint))
                return false;
            nextPositionItem = bodyMap[snakeHead.x - 1][snakeHead.y];
        } else if (direction == DOWN) {
            nextPoint.x = snakeHead.x;
            nextPoint.y = snakeHead.y + 1;
            if (!checkNextPoint(nextPoint))
                return false;
            nextPositionItem = bodyMap[snakeHead.x][snakeHead.y + 1];
        } else if (direction == RIGHT) {
            nextPoint.x = snakeHead.x + 1;
            nextPoint.y = snakeHead.y;
            if (!checkNextPoint(nextPoint))
                return false;
            nextPositionItem = bodyMap[snakeHead.x + 1][snakeHead.y];
        }

        if (nextPositionItem == BLANK) {
            // 什么也没有发生
            bodyMap[body.get(0).x][body.get(0).y] = BLANK;
            for (int index = 0; index < body.size() - 1; ++index) {
                body.get(index).x = body.get(index + 1).x;
                body.get(index).y = body.get(index + 1).y;
            }
            snakeHead.x = nextPoint.x;
            snakeHead.y = nextPoint.y;
            bodyMap[nextPoint.x][nextPoint.y] = BODY;
        } else if (nextPositionItem == HEART) {
            // 蛇吃到了心
            body.add(new Point(heart));
            bodyMap[heart.x][heart.y] = BODY;
            refreshHeartLocation();
        } else if (nextPositionItem == BODY) {
            // 蛇撞到了自己
            return false;
        } else {
            return false;
        }
        return true;
    }

    private boolean checkNextPoint(Point nextPoint) {
        if (nextPoint.x >= 60 || nextPoint.y >= 38 || nextPoint.x < 0 || nextPoint.y < 0)
            return false;
        return true;
    }

    public void turnUp() {
        Point snakeHead = body.get(body.size() - 1);
        Point snakeHeadPreviousBody = body.get(body.size() - 2);
        if (snakeHead.x == snakeHeadPreviousBody.x && snakeHead.y - 1 == snakeHeadPreviousBody.y) {
            // 不能回头
            return;
        }
        direction = UP;
    }

    public void turnLeft() {
        Point snakeHead = body.get(body.size() - 1);
        Point snakeHeadPreviousBody = body.get(body.size() - 2);
        if (snakeHead.x - 1 == snakeHeadPreviousBody.x && snakeHead.y == snakeHeadPreviousBody.y) {
            // 不能回头
            return;
        }
        direction = LEFT;
    }

    public void turnDown() {
        Point snakeHead = body.get(body.size() - 1);
        Point snakeHeadPreviousBody = body.get(body.size() - 2);
        if (snakeHead.x == snakeHeadPreviousBody.x && snakeHead.y + 1 == snakeHeadPreviousBody.y) {
            // 不能回头
            return;
        }
        direction = DOWN;
    }

    public void turnRight() {
        Point snakeHead = body.get(body.size() - 1);
        Point snakeHeadPreviousBody = body.get(body.size() - 2);
        if (snakeHead.x + 1 == snakeHeadPreviousBody.x && snakeHead.y == snakeHeadPreviousBody.y) {
            // 不能回头
            return;
        }
        direction = RIGHT;
    }
}