package CalendeR22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calenders extends JFrame implements ActionListener {
    public static JPanel sideCalender_panel = new JPanel();
    private JPanel mainCalender_panel = new JPanel();
    private CardLayout CalCardLayout;      // 버튼 2개이니 각각의 layout 카드레이아웃
    public static JButton[] b = new JButton[3];
    public Calenders() {
        GridLayout gl = new GridLayout(5,1);  // 2행 1열
        sideCalender_panel.setLayout(gl);    // 사이드메모 layout : gridlayout
        for(int i=0; i<3; i++){
            b[i] = new JButton();
            sideCalender_panel.add(b[i]);
            b[i].setFont(new Font("",1,20));
            b[i].setOpaque(true);       // 버튼 투명도 설정
            b[i].addActionListener(new ActionListener() {   // 클릭 이벤트
                public void actionPerformed(ActionEvent e) {
                    if(e.getActionCommand().equals("스케줄")){      // 버튼 이름 같은지 비교
                        CalCardLayout.show(mainCalender_panel,"스케줄");   // 메인 보여주기.
                    }else if(e.getActionCommand().equals("가계부")){
                        CalCardLayout.show(mainCalender_panel,"가계부");
                    }else if(e.getActionCommand().equals("칼로리")){
                        CalCardLayout.show(mainCalender_panel,"칼로리");
                    }
                }
            });
        }
        b[0].setText("스케줄"); // 버튼 이름 정해주기
        b[1].setText("가계부");
        b[2].setText("칼로리");

        CalCardLayout = new CardLayout();
        mainCalender_panel.setLayout(CalCardLayout);
        mainCalender_panel.setVisible(true);

        // MAIN center CALENDER 페널 생성.
        Cal_main_GUI smg = new Cal_main_GUI();
        C_main_GUI cmg = new C_main_GUI();
        F_main_GUI fmg = new F_main_GUI();
        JPanel S1_panel = new JPanel(new BorderLayout());
        JPanel F1_panel = new JPanel(new BorderLayout());
        JPanel C1_panel = new JPanel(new BorderLayout());
        S1_panel.add(smg.getPanel(), BorderLayout.CENTER);
        C1_panel.add(cmg.getPanel(), BorderLayout.CENTER);
        F1_panel.add(fmg.getPanel(), BorderLayout.CENTER);

        mainCalender_panel.add(S1_panel,"스케줄");
        mainCalender_panel.add(F1_panel,"가계부");
        mainCalender_panel.add(C1_panel,"칼로리");
    }

    public JPanel getPanel() { return sideCalender_panel; }
    public JPanel getPanel2() { return mainCalender_panel; }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
