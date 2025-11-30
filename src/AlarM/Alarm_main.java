package AlarM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Alarm_main extends JFrame  {
    private JPanel mainAlarm_panel = new JPanel();

    public Alarm_main(){
        // MAIN center CHAT 페널 생성.
        mainAlarm_panel.setLayout(new CardLayout());
        mainAlarm_panel.setVisible(true);
        TimeGui tg = new TimeGui();
        JPanel s1 = tg.getMainPanel();

        mainAlarm_panel.add(s1);
        mainAlarm_panel.setOpaque(true);
        mainAlarm_panel.setBackground(Color.YELLOW);
    }

    public JPanel getPanel2() { return mainAlarm_panel; }
}
