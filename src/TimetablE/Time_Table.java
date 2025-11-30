package TimetablE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Time_Table extends JFrame implements ActionListener{
    public static JPanel sideTimetable_panel = new JPanel();
    private JPanel mainTimetable_panel = new JPanel();
    private CardLayout timeCardLayout;      // 버튼 2개이니 각각의 layout 카드레이아웃
    public static JButton[] t = new JButton[2];
    public Time_Table() {
        TimetableApp tta = new TimetableApp();
        GridLayout gl = new GridLayout(5,1);  // 2행 1열
        sideTimetable_panel.setLayout(gl);    // 사이드메모 layout : gridlayout
        for(int i=0; i<2; i++){
            t[i] = new JButton();
            sideTimetable_panel.add(t[i]);
            t[i].setFont(new Font("",1,20));
            t[i].setOpaque(true);       // 버튼 투명도 설정
            t[i].addActionListener(new ActionListener() {   // 클릭 이벤트
                public void actionPerformed(ActionEvent e) {
                    if(e.getActionCommand().equals("추가")){      // 버튼 이름 같은지 비교
                        tta.showInputWindow();
                    }else if(e.getActionCommand().equals("삭제")){
                        tta.showDeleteWindow();
                    }
                }
            });
        }
        t[0].setText("추가"); // 버튼 이름 정해주기
        t[1].setText("삭제");
//        b[0].setBackground(Color.YELLOW);    // 버튼 배경색 정하기
//        b[1].setBackground(Color.YELLOW);

        timeCardLayout = new CardLayout();
        mainTimetable_panel.setLayout(timeCardLayout);
        mainTimetable_panel.setVisible(true);

        // MAIN center TIMETABLE 페널 생성.
        mainTimetable_panel = tta.toCenter();
        mainTimetable_panel.setVisible(true);
    }
    public JPanel getPanel() {
        return sideTimetable_panel;
    }
    public JPanel getPanel2() { return mainTimetable_panel; }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
