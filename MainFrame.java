import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = new Dimension(1516, 1008);
    GamePanel gamePanel = new GamePanel();
    JMenuBar menuBar = new JMenuBar();
    JMenu menuGame = new JMenu("游戏(G)");
    SnakeMenuItem menuItemStart = new SnakeMenuItem("开始(S)", KeyEvent.VK_S,
            KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK), Sources.GAME_START_ICON);
    SnakeMenuItem menuItemPause = new SnakeMenuItem("暂停(P)", KeyEvent.VK_P,
            KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK), Sources.GAME_PAUSE_ICON);
    SnakeMenuItem menuItemExit = new SnakeMenuItem("退出(E)", KeyEvent.VK_E,
            KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK), Sources.EXIT_ICON);
    JMenu menuAbout = new JMenu("关于(A)");
    SnakeMenuItem menuItemHow = new SnakeMenuItem("玩法(H)", KeyEvent.VK_H,
            KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK), Sources.HOW_ICON);
    SnakeMenuItem menuItemAuthor = new SnakeMenuItem("作者(M)", KeyEvent.VK_M,
            KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK), Sources.AUTHOR_ICON);
    Timer gameTimer = new Timer(300, new GameTimerListener());

    class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("开始(S)")) {
                System.out.println("start");
                gameTimer.start();
            }
            if (e.getActionCommand().equals("暂停(P)")) {
                System.out.println("pause");
            }
            if (e.getActionCommand().equals("退出(E)")) {
                System.out.println("exit");
            }
            if (e.getActionCommand().equals("玩法(H)")) {
                System.out.println("how");
                howToPlayMessage();

            }
            if (e.getActionCommand().equals("作者(M)")) {
                System.out.println("author");
            }
            return;
        }
    }

    class GameTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("timer called this");
        }
    }

    public class SnakeMenuItem extends JMenuItem {
        private static final long serialVersionUID = 1L;

        public SnakeMenuItem(String text, int mnemonic, KeyStroke accelerator, ImageIcon icon) {
            super(text, mnemonic);
            setAccelerator(accelerator);
            setIcon(icon);
            addActionListener(new MenuListener());
        }
    }

    public void howToPlayMessage() {
        JOptionPane.showMessageDialog(this, "控制snake的移动，使snake吃到更多的红心，碰到墙壁或snake自己都会导致游戏结束。祝你好运", "弱智提示",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public MainFrame(String title) {
        super(title);
        setSize(frameSize);
        setLocation((int) (screenSize.getWidth() - frameSize.getWidth()) / 2,
                (int) (screenSize.getHeight() - frameSize.getHeight()) / 2);
        gamePanel.setBounds(0, 19, 1500, 950);
        menuGame.setMnemonic(KeyEvent.VK_G);
        menuAbout.setMnemonic(KeyEvent.VK_A);
        menuGame.add(menuItemStart);
        menuGame.add(menuItemPause);
        menuGame.addSeparator();
        menuGame.add(menuItemExit);
        menuAbout.add(menuItemHow);
        menuAbout.add(menuItemAuthor);
        menuBar.add(menuGame);
        menuBar.add(menuAbout);
        menuBar.setLocation(0, 0);
        menuBar.setSize((int) frameSize.getWidth(), 20);
        add(menuBar);
        add(gamePanel);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}