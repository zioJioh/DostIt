package ChaT;

import javax.swing.*;
import java.awt.*;

public class Chatting extends JFrame {
    private JPanel mainChat_panel = new JPanel();
    public Chatting() {
        ChatClient cc = new ChatClient();

        // MAIN center CHAT 페널 생성.
        mainChat_panel.setLayout(new BorderLayout());
        mainChat_panel.add(cc.getGotoChatMain(), BorderLayout.CENTER);
        mainChat_panel.setBackground(Color.YELLOW);
    }

    public JPanel getPanel2() { return mainChat_panel; }
}
