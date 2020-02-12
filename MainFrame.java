import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = new Dimension(1516, 1008);
    GamePanel gamePanel = new GamePanel();
    JLabel startLabel = new JLabel("按下空格开始/暂停");
    JMenuBar menuBar = new JMenuBar();
    JMenu menuGame = new JMenu("游戏(G)");
    SnakeMenuItem menuItemStart = new SnakeMenuItem("开始(S)", KeyEvent.VK_S,
            KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK), Sources.GAME_START_ICON);
    SnakeMenuItem menuItemPause = new SnakeMenuItem("暂停(P)", KeyEvent.VK_P,
            KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK), Sources.GAME_PAUSE_ICON);
    SnakeMenuItem menuItemRestart = new SnakeMenuItem("重新开始(R)", KeyEvent.VK_R,
            KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK), Sources.EXIT_ICON);
    SnakeMenuItem menuItemExit = new SnakeMenuItem("退出(E)", KeyEvent.VK_E,
            KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK), Sources.EXIT_ICON);
    JMenu menuAbout = new JMenu("关于(A)");
    SnakeMenuItem menuItemHow = new SnakeMenuItem("玩法(H)", KeyEvent.VK_H,
            KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK), Sources.HOW_ICON);
    SnakeMenuItem menuItemAuthor = new SnakeMenuItem("作者(M)", KeyEvent.VK_M,
            KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK), Sources.AUTHOR_ICON);

    public class SnakeMenuItem extends JMenuItem {
        private static final long serialVersionUID = 1L;

        public SnakeMenuItem(String text, int mnemonic, KeyStroke accelerator, ImageIcon icon) {
            super(text, mnemonic);
            setAccelerator(accelerator);
            setIcon(icon);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("开始(S)") && gamePanel.status != GamePanel.FAILED) {
                        startGame();
                    } else if (e.getActionCommand().equals("暂停(P)")) {
                        pauseGame();
                    } else if (e.getActionCommand().equals("重新开始(R)")) {
                        restartGame();
                    } else if (e.getActionCommand().equals("退出(E)")) {
                        exitGame();
                    } else if (e.getActionCommand().equals("玩法(H)")) {
                        howToPlayMessage();
                    } else if (e.getActionCommand().equals("作者(M)")) {
                        authorMessage();
                    } else {
                        return;
                    }
                }
            });
        }
    }

    public void howToPlayMessage() {
        JOptionPane.showMessageDialog(this, "控制snake的移动，使snake吃到更多的红心，碰到墙壁或snake自己都会导致游戏结束。祝你好运", "弱智提示",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void authorMessage() {
        JOptionPane.showMessageDialog(this, "苦逼数学学院大学生，暗中观察→www.rayalto.top", "垃圾介绍", JOptionPane.INFORMATION_MESSAGE);
    }

    public void startGame() {
        gamePanel.setVisible(true);
        gamePanel.start();
    }

    public void pauseGame() {
        gamePanel.setVisible(false);
        gamePanel.pause();
    }

    public void restartGame() {
        gamePanel.restart();
    }

    public void exitGame() {
        gamePanel.setVisible(false);
        gamePanel.pause();
        System.exit(0);
    }

    public MainFrame(String title) {
        super(title);
        setSize(frameSize);
        setLocation((int) (screenSize.getWidth() - frameSize.getWidth()) / 2,
                (int) (screenSize.getHeight() - frameSize.getHeight()) / 2);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // 按下了空格键
                    if (gamePanel.status == GamePanel.PAUSED) {
                        startGame();
                    } else if (gamePanel.status == GamePanel.RUNNING) {
                        pauseGame();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    // 按下了向上箭头
                    gamePanel.snake.turnUp();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    // 按下了向左箭头
                    gamePanel.snake.turnLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    // 按下了向下箭头
                    gamePanel.snake.turnDown();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    // 按下了向右箭头
                    gamePanel.snake.turnRight();
                }
            }
        });
        gamePanel.setBounds(0, 19, 1500, 950);
        menuGame.setMnemonic(KeyEvent.VK_G);
        menuAbout.setMnemonic(KeyEvent.VK_A);
        menuGame.add(menuItemStart);
        menuGame.add(menuItemPause);
        menuGame.add(menuItemRestart);
        menuGame.addSeparator();
        menuGame.add(menuItemExit);
        menuAbout.add(menuItemHow);
        menuAbout.add(menuItemAuthor);
        menuBar.add(menuGame);
        menuBar.add(menuAbout);
        menuBar.setBounds(0, 0, (int) frameSize.getWidth(), 20);
        startLabel.setFont(new Font("simyou", Font.PLAIN, 40));
        startLabel.setBackground(Color.CYAN);
        startLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLabel.setBounds(0, 0, (int) frameSize.getWidth(), (int) frameSize.getHeight());
        add(menuBar);
        add(gamePanel);
        add(startLabel);
        gamePanel.setVisible(false);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}