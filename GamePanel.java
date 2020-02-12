import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    public static int RUNNING = 1;
    public static int PAUSED = 0;
    public static int FAILED = -1;
    public int interval = 200;
    public int status = PAUSED;
    public double time = 0;
    private static final long serialVersionUID = 1L;
    public SnakeData snake = new SnakeData();
    public JLabel scoreLabel;
    Timer gameTimer = new Timer(interval, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (snake.process()) {
                time += (double) interval / 1000;
                repaint();
            } else {
                pause();
                // 游戏结束了
                status = FAILED;
            }
        }
    });

    @Override
    public void paint(Graphics g) {
        Point snakeHead = snake.body.get(snake.body.size() - 1);
        super.paint(g);
        // grids for test=========================================
        // for (int y = 0; y <= 950; y += 25) {
        // g.drawLine(0, y, 1500, y);
        // }
        // for (int x = 0; x <= 1500; x += 25) {
        // g.drawLine(x, 0, x, 950);
        // }
        // grids for test=========================================
        if (snake.direction == SnakeData.UP) {
            Sources.HEAD_UP_ICON.paintIcon(this, g, snakeHead.x * 25, snakeHead.y * 25);
        } else if (snake.direction == SnakeData.LEFT) {
            Sources.HEAD_LEFT_ICON.paintIcon(this, g, snakeHead.x * 25, snakeHead.y * 25);
        } else if (snake.direction == SnakeData.DOWN) {
            Sources.HEAD_DOWN_ICON.paintIcon(this, g, snakeHead.x * 25, snakeHead.y * 25);
        } else if (snake.direction == SnakeData.RIGHT) {
            Sources.HEAD_RIGHT_ICON.paintIcon(this, g, snakeHead.x * 25, snakeHead.y * 25);
        }
        for (int index = 0; index < snake.body.size() - 1; ++index) {
            Sources.BODY_ICON.paintIcon(this, g, snake.body.get(index).x * 25, snake.body.get(index).y * 25);
        }
        Sources.HEART_ICON.paintIcon(this, g, snake.heart.x * 25, snake.heart.y * 25);
        // show the "BODY" block===================================
        // for (int x = 0; x < 60; ++x) {
        // for (int y = 0; y < 38; ++y) {
        // if (snake.bodyMap[x][y] == SnakeData.BODY)
        // Sources.GAME_PAUSE_ICON.paintIcon(this, g, x * 25, y * 25);
        // }
        // }
        // =========================================================
        scoreLabel.setText("Score: " + (snake.body.size() - 3) + "        Time: " + (int) time + " s");
    }

    public GamePanel() {
        setSize(1500, 950);
        status = PAUSED;
    }

    public void start() {
        gameTimer.start();
        status = RUNNING;
    }

    public void pause() {
        gameTimer.stop();
        status = PAUSED;
    }

    public void restart() {
        setVisible(false);
        gameTimer.stop();
        snake = new SnakeData();
        repaint();
        setVisible(true);
        gameTimer.start();
        status = PAUSED;
    }

    public GamePanel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
        scoreLabel.setFont(new Font("simyou", Font.PLAIN, 24));
        setBackground(Color.GRAY);
    }
}