package Memo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeMo extends JFrame{
    public static JPanel sideMemo_panel = new JPanel();   // MAIN 사이드바에 넣을 페널
    private JPanel mainMemo_panel = new JPanel();   // MAIN center에 넣을 페널
    private CardLayout memoCardLayout;      // 버튼 2개이니 각각의 layout 카드레이아웃
    public static JButton[] a = new JButton[2];
    public MeMo(){
        // MAIN 사이드바 Memo 페널 생성.
        GridLayout gl = new GridLayout(5,1);  // 2행 1열
        sideMemo_panel.setLayout(gl);    // 사이드메모 layout : gridlayout

        for (int i = 0; i < 2; i++) {
            a[i] = new JButton();       // a[i] 버튼 생성
            sideMemo_panel.add(a[i]);   // 사이드메모 페널에 버튼 삽입
            a[i].setFont(new Font("",1,20));    // 버튼의 폰트, 크기 설정
            a[i].setOpaque(true);       // 버튼 투명도 설정
            a[i].addActionListener(new ActionListener() {   // 클릭 이벤트
                public void actionPerformed(ActionEvent e) {
                    if(e.getActionCommand().equals("To Do List")){      // 버튼 이름 같은지 비교
                        memoCardLayout.show(mainMemo_panel,"To Do List");   // 메인 보여주기.
                    }else if(e.getActionCommand().equals("루틴 추천")){
                        memoCardLayout.show(mainMemo_panel,"루틴 추천");
                    }
                }
            });
        }
        a[0].setText("To Do List"); // 버튼 이름 정해주기
        a[1].setText("루틴 추천");
//        a[0].setBackground(Color.green);    // 버튼 배경색 정하기
//        a[1].setBackground(Color.green);

        memoCardLayout = new CardLayout();
        mainMemo_panel.setLayout(memoCardLayout);
        mainMemo_panel.setVisible(true);
        // MAIN center Memo 페널 생성.
        ToDoListGUI tdlG = new ToDoListGUI();
        JPanel s1 = tdlG.getPanel();
        JPanel s2 = tdlG.getPanel2();
        mainMemo_panel.add(s1,"To Do List");
        mainMemo_panel.add(s2,"루틴 추천");
        mainMemo_panel.setOpaque(true);
//        mainMemo_panel.setBackground(Color.GRAY);

    }
    public JPanel getPanel() { return sideMemo_panel; }
    public JPanel getPanel2() { return mainMemo_panel; }

}
