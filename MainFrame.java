import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = new Dimension(1000, 800);
    JMenuBar menuBar = new JMenuBar();
    JMenu menuGame = new JMenu("游戏(G)");
    JMenuItem menuItemStart = new JMenuItem("开始(S)", KeyEvent.VK_S);
    JMenuItem menuItemPause = new JMenuItem("暂停(P)", KeyEvent.VK_P);
    JMenuItem menuItemExit = new JMenuItem("退出(E)", KeyEvent.VK_E);

    public MainFrame(String title) {
        super(title);
        setSize(frameSize);
        setLocation((int) (screenSize.getWidth() - frameSize.getWidth()) / 2,
                (int) (screenSize.getHeight() - frameSize.getHeight()) / 2);
        menuGame.setMnemonic(KeyEvent.VK_G);
        menuItemStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItemStart.setIcon(Sources.GAME_START_ICON);
        menuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuItemPause.setIcon(Sources.GAME_PAUSE_ICON);
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menuItemExit.setIcon(Sources.EXIT_ICON);
        menuGame.add(menuItemStart);
        menuGame.add(menuItemPause);
        menuGame.addSeparator();
        menuGame.add(menuItemExit);
        menuBar.add(menuGame);
        menuBar.setLocation(0, 0);
        menuBar.setSize((int) frameSize.getWidth(), 20);
        add(menuBar);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}