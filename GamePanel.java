import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public SnakeData snake = new SnakeData();

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // grids for test=========================================
        for (int y = 0; y <= 950; y += 25) {
            g.drawLine(0, y, 1500, y);
        }
        for (int x = 0; x <= 1500; x += 25) {
            g.drawLine(x, 0, x, 950);
        }
        // grids for test=========================================
        if (snake.direction == SnakeData.UP) {
            Sources.HEAD_UP_ICON.paintIcon(this, g, snake.body.get(snake.body.size() - 1).x * 25,
                    snake.body.get(snake.body.size() - 1).y * 25);
        } else if (snake.direction == SnakeData.LEFT) {
            Sources.HEAD_LEFT_ICON.paintIcon(this, g, snake.body.get(snake.body.size() - 1).x * 25,
                    snake.body.get(snake.body.size() - 1).y * 25);
        } else if (snake.direction == SnakeData.DOWN) {
            Sources.HEAD_DOWN_ICON.paintIcon(this, g, snake.body.get(snake.body.size() - 1).x * 25,
                    snake.body.get(snake.body.size() - 1).y * 25);
        } else if (snake.direction == SnakeData.RIGHT) {
            Sources.HEAD_RIGHT_ICON.paintIcon(this, g, snake.body.get(snake.body.size() - 1).x * 25,
                    snake.body.get(snake.body.size() - 1).y * 25);
        }
        for (int index = snake.body.size() - 2; index >= 0; --index) {
            Sources.BODY_ICON.paintIcon(this, g, snake.body.get(index).x * 25, snake.body.get(index).y * 25);
        }
    }

    public GamePanel() {
        setSize(1500, 950);
    }
}