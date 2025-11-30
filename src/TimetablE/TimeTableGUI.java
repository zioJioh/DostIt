//package TimetablE;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class TimeTableGUI extends JFrame {
//    private static final int ROW_COUNT = 9; // 세로 줄 몇 줄 할건지 (교시 줄)
//    private static final int COL_COUNT = 5;// 가로 줄 몇 줄 할건지 (요일 줄)
//
//    private JTextField[][] textFields; //2차열 배열 쓸거임
//
//    private String[] daysOfWeek = {"월", "화", "수", "목", "금"};
//    private String[] classTimes = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시", "9교시"};
//    private JPanel timetablePanel = new JPanel();
//    private JFrame inputFrame = new JFrame();
//
//    public TimeTableGUI() { // 생성자, 기본 창 만들기
////        setTitle("대학생 전용 시간표");
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        setSize(1000, 1000);
////        setLocationRelativeTo(null);
//
////        JPanel timetablePanel = new JPanel(new GridLayout(ROW_COUNT + 1, COL_COUNT + 1)); //timetablePanel 생성.
//        GridLayout gl = new GridLayout(ROW_COUNT+1,COL_COUNT+1);
//        timetablePanel.setLayout(gl);
//        // 요일 칸 생성
//        JLabel emptyLabel = new JLabel(""); //  가장 왼쪽 위에 위치한 빈..
//        // 요일을 표시하는 라벨과 교시를 표시하는 라벨이 겹치지 않도록 공간을 확보하기 위해서 만든 것.
//        timetablePanel.add(emptyLabel); //만든 emptyLabel 을  timetablePanel 에 추가함.
//        for (String day : daysOfWeek) {
//            timetablePanel.add(new JLabel(day, SwingConstants.CENTER)); //요일을 표시하는 JLabel 추가
//        }
//
//        // 시간표 칸 생성
//        textFields = new JTextField[ROW_COUNT][COL_COUNT]; //교시 칸 생성
//        for (int i = 0; i < ROW_COUNT; i++) {
//            String timeHeader = String.format("%d교시", i + 1);
//            timetablePanel.add(new JLabel(timeHeader, SwingConstants.CENTER));
//            for (int j = 0; j < COL_COUNT; j++) {
//                textFields[i][j] = new JTextField(); // JTextField 만들기
//                textFields[i][j].setEditable(false); // 편집 불가능하도록 설정
//                textFields[i][j].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12)); // 폰트 설정..
//                timetablePanel.add(textFields[i][j]); // 만든 JTextField 를 시간표 패널(timetablePanel) 에 추가!
//            }
//        }
//
////        JButton addButton = new JButton("추가"); //'추가' 버튼 만들기
////        addButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                showInputWindow();
////            } // "추가" 버튼 클릭하면 입력창을 보여주는 함수 호출하기
////        });
//    }
//
//    public void showInputWindow() {
//        inputFrame.setTitle("시간표 입력");
//        inputFrame.setSize(300, 300);
//        inputFrame.setLocationRelativeTo(null);
//
//        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
//        // 6줄의 입력 패널 생성, 7로 해놔야 6줄로 되더라
//
//        JLabel classNameLabel = new JLabel("수업 이름:");
//        JTextField classNameTextField = new JTextField();  //수업이름 입력 칸
//        inputPanel.add(classNameLabel);
//        inputPanel.add(classNameTextField);
//
//        JLabel professorLabel = new JLabel("교수 이름:");
//        JTextField professorTextField = new JTextField(); //교수 이름 입력 칸
//        inputPanel.add(professorLabel);
//        inputPanel.add(professorTextField);
//
//        JLabel roomLabel = new JLabel("수업 호실:");
//        JTextField roomTextField = new JTextField(); //수업호실 입력 칸
//        inputPanel.add(roomLabel);
//        inputPanel.add(roomTextField);
//
//        JLabel dayLabel = new JLabel("요일:");
//        JComboBox<String> dayComboBox = new JComboBox<>(daysOfWeek); //요일 선택 박스 칸
//        inputPanel.add(dayLabel);
//        inputPanel.add(dayComboBox);
//
//        JLabel startTimeLabel = new JLabel("시작 교시:");
//        JComboBox<String> startTimeComboBox = new JComboBox<>(classTimes); //시작 교시 선택 박스 칸
//        inputPanel.add(startTimeLabel);
//        inputPanel.add(startTimeComboBox);
//
//        JLabel endTimeLabel = new JLabel("끝 교시:");
//        JComboBox<String> endTimeComboBox = new JComboBox<>(classTimes); //끝 교시 선택 박스 칸
//        inputPanel.add(endTimeLabel);
//        inputPanel.add(endTimeComboBox);
//
//        JButton confirmButton = new JButton("확인");
//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) { //입력 값 가져오는 함수
//                String className = classNameTextField.getText();
//                String professor = professorTextField.getText();
//                String room = roomTextField.getText();
//                String selectedDay = (String) dayComboBox.getSelectedItem();
//                String selectedStartTime = (String) startTimeComboBox.getSelectedItem();
//                String selectedEndTime = (String) endTimeComboBox.getSelectedItem();
//
//                int startRow = getSelectedRow(selectedStartTime); // 요일과 교시에 해당하는 인덱스 선택하고 설정
//                int endRow = getSelectedRow(selectedEndTime);
//                int selectedColumn = getSelectedColumn(selectedDay);
//
//                if (selectedColumn != -1 && startRow != -1 && endRow != -1) {
//                    for (int i = startRow; i <= endRow; i++) { // 설정한 인덱스 칸에 입력값을 저장한다
//                        textFields[i][selectedColumn-1].setText(className + "\n" + professor + "\n" + room);
//                        //수업이름, 교수이름, 호실 순서대로. 근데 "\n" 넣어도 한줄씩 안써지노;;
//                    }
//                }
//
//                inputFrame.dispose(); //입력 창 닫기
//            }
//        });
//        inputPanel.add(confirmButton);
//
//        inputFrame.getContentPane().add(inputPanel);
//        inputFrame.setVisible(true);
//    }
//
//    private int getSelectedColumn(String day) {
//        switch (day) {
//            case "월":
//                return 1;
//            case "화":
//                return 2;
//            case "수":
//                return 3;
//            case "목":
//                return 4;
//            case "금":
//                return 5;
//            default:
//                return -1;
//        }
//    }
//
//    private int getSelectedRow(String classTime) {
//        switch (classTime) {
//            case "1교시":
//                return 0;
//            case "2교시":
//                return 1;
//            case "3교시":
//                return 2;
//            case "4교시":
//                return 3;
//            case "5교시":
//                return 4;
//            case "6교시":
//                return 5;
//            case "7교시":
//                return 6;
//            case "8교시":
//                return 7;
//            case "9교시":
//                return 8;
//            default:
//                return -1;
//        }
//    }
//    public JPanel getTimetablePanel(){ return timetablePanel; }
//}
//
