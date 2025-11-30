package TimetablE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TimetableApp extends JFrame {
    private static final int ROW_COUNT = 9; // 교시 (세로)
    private static final int COL_COUNT = 5; // 요일 (가로)
    private JPanel[][] panels;  //2차원 배열 페널 사용 예정
    public static JPanel timetablePanel = new JPanel(new GridLayout(ROW_COUNT + 1, COL_COUNT + 1));
    private JLabel emptyLabel = new JLabel("");

    private String[] daysOfWeek = {"월", "화", "수", "목", "금"};
    private String[] classTimes = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시", "9교시"};

//  메인에 넘길 페널.
    private JPanel gotoTimeCenter = new JPanel();   // center

    public TimetableApp() {
        timetablePanel.add(emptyLabel);

        for (String day : daysOfWeek) {     // 월 ~ 금 라벨 추가.
            timetablePanel.add(new JLabel(day, SwingConstants.CENTER));
        }
// 2차원 배열 생성 <완>
        panels = new JPanel[ROW_COUNT][COL_COUNT];
// 틀 만들기
        for (int i = 0; i < ROW_COUNT; i++) {
            String timeHeader = String.format("%d교시", i + 1);
            timetablePanel.add(new JLabel(timeHeader, SwingConstants.CENTER));
            for (int j = 0; j < COL_COUNT; j++) {
                panels[i][j] = new JPanel(new BorderLayout());
                panels[i][j].setPreferredSize(new Dimension(100, 50));
                panels[i][j].setBackground(Color.WHITE);
                panels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JLabel label = new JLabel();
                label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                panels[i][j].add(label, BorderLayout.CENTER);
                timetablePanel.add(panels[i][j]);
            }
        }
        gotoTimeCenter.setLayout(new BorderLayout());
        gotoTimeCenter.add(timetablePanel, BorderLayout.CENTER);

    }

    public void showInputWindow() {
        JFrame inputFrame = new JFrame("시간표 입력");
        inputFrame.setSize(300, 300);
        inputFrame.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        JLabel classNameLabel = new JLabel("수업 이름:");
        JTextField classNameTextField = new JTextField();
        inputPanel.add(classNameLabel);
        inputPanel.add(classNameTextField);

        JLabel professorLabel = new JLabel("교수 이름:");
        JTextField professorTextField = new JTextField();
        inputPanel.add(professorLabel);
        inputPanel.add(professorTextField);

        JLabel roomLabel = new JLabel("수업 호실:");
        JTextField roomTextField = new JTextField();
        inputPanel.add(roomLabel);
        inputPanel.add(roomTextField);

        JLabel dayLabel = new JLabel("요일:");
        JComboBox<String> dayComboBox = new JComboBox<>(daysOfWeek);
        inputPanel.add(dayLabel);
        inputPanel.add(dayComboBox);

        JLabel startTimeLabel = new JLabel("시작 교시:");
        JComboBox<String> startTimeComboBox = new JComboBox<>(classTimes);
        inputPanel.add(startTimeLabel);
        inputPanel.add(startTimeComboBox);

        JLabel endTimeLabel = new JLabel("끝 교시:");
        JComboBox<String> endTimeComboBox = new JComboBox<>(classTimes);
        inputPanel.add(endTimeLabel);
        inputPanel.add(endTimeComboBox);

        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = classNameTextField.getText();
                String professor = professorTextField.getText();
                String room = roomTextField.getText();
                String selectedDay = (String) dayComboBox.getSelectedItem();
                String selectedStartTime = (String) startTimeComboBox.getSelectedItem();
                String selectedEndTime = (String) endTimeComboBox.getSelectedItem();

                int startRow = getSelectedRow(selectedStartTime);
                int endRow = getSelectedRow(selectedEndTime);
                int selectedColumn = getSelectedColumn(selectedDay);

                if (selectedColumn != -1 && startRow != -1 && endRow != -1) {
                    Color randomColor = generateRandomColor(); //랜덤으로 색 골라줄 randomColor
                    for (int i = startRow; i <= endRow; i++) {
                        JLabel label = (JLabel) panels[i][selectedColumn - 1].getComponent(0);
                        label.setText("<html><center>" +
                                "<b>" + className + "</b><br>" +
                                professor + "<br>" +
                                room + "</center></html>");
                        panels[i][selectedColumn - 1].setBackground(randomColor); //판넬의 배경에 랜덤 색 넣음
                    }
                }
                inputFrame.dispose();
            }
        });
        inputPanel.add(confirmButton);
        inputFrame.getContentPane().add(inputPanel);
        inputFrame.setVisible(true);
    }

    public void showDeleteWindow() {       //삭제 창 만들었어요, 추가 창 베껴온거라 거의 비슷해요.
        JFrame deleteFrame = new JFrame("시간표 삭제");
        deleteFrame.setSize(300, 300);
        deleteFrame.setLocationRelativeTo(null);

        JPanel deletePanel = new JPanel(new GridLayout(4, 5, 10, 10));

        JLabel dayLabel = new JLabel("요일:");
        JComboBox<String> dayComboBox = new JComboBox<>(daysOfWeek);
        deletePanel.add(dayLabel);
        deletePanel.add(dayComboBox);

        JLabel startTimeLabel = new JLabel("시작 교시:");
        JComboBox<String> startTimeComboBox = new JComboBox<>(classTimes);
        deletePanel.add(startTimeLabel);
        deletePanel.add(startTimeComboBox);

        JLabel endTimeLabel = new JLabel("끝 교시:");
        JComboBox<String> endTimeComboBox = new JComboBox<>(classTimes);
        deletePanel.add(endTimeLabel);
        deletePanel.add(endTimeComboBox);

        JButton deleteConfirmButton = new JButton("확인"); // 확인 버튼 맨아랫줄 중간에 위치하게 하고 싶은데
        deleteFrame.getContentPane().add(deleteConfirmButton, BorderLayout.CENTER);//^^ㅂ 가운데에 안되노
        deleteConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDay = (String) dayComboBox.getSelectedItem();
                String selectedStartTime = (String) startTimeComboBox.getSelectedItem();
                String selectedEndTime = (String) endTimeComboBox.getSelectedItem();

                int startRow = getSelectedRow(selectedStartTime);
                int endRow = getSelectedRow(selectedEndTime);
                int selectedColumn = getSelectedColumn(selectedDay);

                if (selectedColumn != -1 && startRow != -1 && endRow != -1) {
                    for (int i = startRow; i <= endRow; i++) {
                        JLabel label = (JLabel) panels[i][selectedColumn - 1].getComponent(0);
                        label.setText(""); //빈칸으로 만들거에요
                        panels[i][selectedColumn - 1].setBackground(Color.WHITE);
                    }
                }
                deleteFrame.dispose();
            }
        });
        deletePanel.add(deleteConfirmButton);
        deleteFrame.getContentPane().add(deletePanel);
        deleteFrame.setVisible(true);
    }

    private Color generateRandomColor() { //랜덤으로 색 정해주는...
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

    private int getSelectedColumn(String day) {
        switch (day) {
            case "월":
                return 1;
            case "화":
                return 2;
            case "수":
                return 3;
            case "목":
                return 4;
            case "금":
                return 5;
            default:
                return -1;
        }
    }

    private int getSelectedRow(String classTime) {
        switch (classTime) {
            case "1교시":
                return 0;
            case "2교시":
                return 1;
            case "3교시":
                return 2;
            case "4교시":
                return 3;
            case "5교시":
                return 4;
            case "6교시":
                return 5;
            case "7교시":
                return 6;
            case "8교시":
                return 7;
            case "9교시":
                return 8;
            default:
                return -1;
        }
    }
    public JPanel toCenter(){ return gotoTimeCenter; }
}


