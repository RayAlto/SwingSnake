import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainFrame(String title) {
        super(title);
        Container contentPane = getContentPane();
        setBounds(100, 100, 1000, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}