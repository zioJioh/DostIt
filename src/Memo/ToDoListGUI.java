package Memo;

import MainGUI.MainGui;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Random;

import static MainGUI.MainGui.*;

public class ToDoListGUI extends JFrame implements ActionListener {
    // ToDoListGUI 필드
    private JTextField textField;           //할 일 입력 필드
    private JButton addButton;              //할 일 추가 버튼
    private static JPanel panel;            // goToList_panel 속 체크박스 담을 페널
    private static int count = 0;           // 할 일의 개수 count
    private static JLabel namhalLabel;      // 남은 할일 : count 보여주는 라벨

    //private static JLabel mileageLabel;     // 마일리지 값 표시 라벨
    private JPanel goToList_panel = new JPanel();   // center페널 ToDoList 넣을 페널

    // RandomArrayGUI 필드
    private JLabel morningLabel;            // 추천 1
    private JLabel dailyLabel;              // 추천 2
    private JLabel nightLabel;              // 추천 3
    private JCheckBox morningCheckBox;      // 추천 1 체크박스
    private JCheckBox dailyCheckBox;        // 추천 2 체크박스
    private JCheckBox nightCheckBox;        // 추천 3 체크박스
    public ToDoListGUI toDoListGUI;   // 값을 넘겨주기 위한 객체
    private String[][] array = {
            {"잠자리 정리하기", "아침 명상하기", "조깅하기", "명상하기", "신문 읽기", "수면시간 체크하기", "아침밥 챙겨먹기", "스트레칭하기"},
            {"포스트잇에 할 일 적기", "컴퓨터 바탕화면 정리하기", "주변 정리하기", "비타민 챙겨먹기", "하루 세번 고마움 표현하기"},
            {"일기 쓰기", "부모님께 안부전화하기", "반신욕하기", "잠들기 전 독서하기", "내일 입을 옷 고르기"}
    };
    private JPanel goToRandom_panel = new JPanel(); // center페널 추천 루틴 넣을 페널

    public ToDoListGUI() {
//        mili.setText("마일리지 : "+mileage);
        panel = new JPanel();   // 체크박스 담는 페널 생성
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        textField = new JTextField(10);
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        // mileageLabel = new JLabel("마일리지: " + main_mileage);  // 마일리지 표시 라벨 초기화
        namhalLabel = new JLabel("남은 할 일: " + count);   // 남은 할 일 표시 라벨 초기화
        JPanel inputPanel = new JPanel();       // goToList_panel 보더 North 위치 넣을 페널
        inputPanel.add(textField);              // 할 일 입력 공간
        inputPanel.add(addButton);              // add버튼
        inputPanel.add(namhalLabel);            // 남은 할 일 개수
        // inputPanel.add(mileageLabel);           // 마일리지 현황

        goToList_panel.setLayout(new BorderLayout());
        goToList_panel.add(inputPanel, BorderLayout.NORTH);
        goToList_panel.add(scrollPane, BorderLayout.CENTER);
        goToList_panel.setVisible(true);
//  RandonArrayGUI 부분
        setToDoListGUI(this);
        goToRandom_panel.setLayout(new GridLayout(4,1));    // 그리드 4행 1열
        GridLayout setglay = new GridLayout(1,3);
        JPanel lootin1_panel = new JPanel(setglay);    // 추천 1 묶을 페널
        JPanel lootin2_panel = new JPanel(setglay);    // 추천 2 묶을 페널
        JPanel lootin3_panel = new JPanel(setglay);    // 추천 3 묶을 페널

//        JPanel panel1234 = new JPanel();
//        panel1234.setLayout(new GridLayout(3, 2));

        JLabel animalTitleLabel = new JLabel("    추천 루틴 1:");
        animalTitleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        animalTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        lootin1_panel.add(animalTitleLabel);

        morningLabel = new JLabel();
        morningLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        morningLabel.setHorizontalAlignment(JLabel.CENTER);
        lootin1_panel.add(morningLabel);

        morningCheckBox = new JCheckBox();
        lootin1_panel.add(morningCheckBox);

        JLabel foodTitleLabel = new JLabel("    추천 루틴 2:");
        foodTitleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        foodTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        lootin2_panel.add(foodTitleLabel);

        dailyLabel = new JLabel();
        dailyLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        dailyLabel.setHorizontalAlignment(JLabel.CENTER);
        lootin2_panel.add(dailyLabel);

        dailyCheckBox = new JCheckBox();
        lootin2_panel.add(dailyCheckBox);

        JLabel plantTitleLabel = new JLabel("    추천 루틴 3:");
        plantTitleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        plantTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        lootin3_panel.add(plantTitleLabel);

        nightLabel = new JLabel();
        nightLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        nightLabel.setHorizontalAlignment(JLabel.CENTER);
        lootin3_panel.add(nightLabel);

        nightCheckBox = new JCheckBox();
        lootin3_panel.add(nightCheckBox);

        setRandomTask();

        JButton generateButton = new JButton("다른 조합 추천받기");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomValues();
            }
        });

        JButton submitButton = new JButton("To-Do List에 추가하기");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToDoList();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(generateButton);
        buttonPanel.add(submitButton);

        goToRandom_panel.add(lootin1_panel);
        goToRandom_panel.add(lootin2_panel);
        goToRandom_panel.add(lootin3_panel);
        goToRandom_panel.add(buttonPanel);

        goToRandom_panel.setVisible(true);
    }

    public void setRandomTask() {
        Random random = new Random();
        int randomIndex = random.nextInt(3);

        String[] tasks = array[randomIndex];
        morningLabel.setText(tasks[0]);
        dailyLabel.setText(tasks[1]);
        nightLabel.setText(tasks[2]);
    }

    public void setToDoListGUI(ToDoListGUI toDoListGUI222) {
        this.toDoListGUI = toDoListGUI222;
    }

    public static void addTask(String task) {
        if (!task.isEmpty()) {
            JCheckBox checkBox = new JCheckBox(task);
            checkBox.setFocusable(false);
            checkBox.setFont(new Font("Dialog", 0, 33));
            checkBox.setBackground(Color.WHITE);
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBox.isSelected()) {
                        Font font = checkBox.getFont();
                        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                        checkBox.setFont(font.deriveFont(attributes));
                        count--;
                        main_mileage += 5;
                    } else {
                        Font font = checkBox.getFont();
                        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                        attributes.put(TextAttribute.STRIKETHROUGH, false);
                        checkBox.setFont(font.deriveFont(attributes));
                        count++;
                        main_mileage -= 5;
                    }
                    //     mileageLabel.setText("마일리지: " + main_mileage);
                    upStatus[1].setText("마일리지: " +main_mileage);
                    for (int i = 0; i < 2; i++) {
                        upPanelC.add(upStatus[i]);
                        upStatus[i].setOpaque(true);//뒷 배경 투명
                    }

                    namhalLabel.setText("남은 할 일: " + count + "개");
                }
            });
            panel.add(checkBox);
            panel.revalidate();
            panel.repaint();
            count++;
            namhalLabel.setText("남은 할 일: " + count + "개");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = textField.getText();
            addTask(task);
            textField.setText("");
        } else if (e.getSource() == morningCheckBox) {
            if (morningCheckBox.isSelected()) {
                addTask(morningLabel.getText());
                morningCheckBox.setSelected(false);
            }
        } else if (e.getSource() == dailyCheckBox) {
            if (dailyCheckBox.isSelected()) {
                addTask(dailyLabel.getText());
                dailyCheckBox.setSelected(false);
            }
        } else if (e.getSource() == nightCheckBox) {
            if (nightCheckBox.isSelected()) {
                addTask(nightLabel.getText());
                nightCheckBox.setSelected(false);
            }
        }
    }

    private void addToDoList() {
        if (toDoListGUI != null) {
            if (morningCheckBox.isSelected()) {
                ToDoListGUI.addTask(morningLabel.getText());
            }
            if (dailyCheckBox.isSelected()) {
                ToDoListGUI.addTask(dailyLabel.getText());
            }
            if (nightCheckBox.isSelected()) {
                ToDoListGUI.addTask(nightLabel.getText());
            }
            JOptionPane.showMessageDialog(this, "선택한 추천 항목들이 To-Do List에 추가되었습니다!");
            morningCheckBox.setSelected(false);
            dailyCheckBox.setSelected(false);
            nightCheckBox.setSelected(false);
        }
    }

    private void generateRandomValues() {
        Random random = new Random();
        int morningIndex = random.nextInt(array[0].length);
        int dailyIndex = random.nextInt(array[1].length);
        int nightIndex = random.nextInt(array[2].length);

        morningLabel.setText(array[0][morningIndex]);
        dailyLabel.setText(array[1][dailyIndex]);
        nightLabel.setText(array[2][nightIndex]);
    }

    public JPanel getPanel(){
        return goToList_panel;
    }
    public JPanel getPanel2(){
        return goToRandom_panel;
    }

}