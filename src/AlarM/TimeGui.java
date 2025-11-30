package AlarM;
import MainGUI.MainGui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeGui extends JFrame {

    private JTextField settingTimeField;        // 안쓰던거
    private Timer timer;                        // 달력 api
    private JComboBox<String> hourComboBox;     // 콤보박스 (시간)
    private JComboBox<String> minuteComboBox;   // 콤보박스 (분)
    private JComboBox<String> secComboBox;      // 콤보박스 (초)
    private JList<String> alarmList;            // 알람 저장위치?? 저장 제네릭
    private DefaultListModel<String> listModel; // 디폴트 리스트 모델
    public List<String> alarms;                //
    static public JComboBox<String> quizComboBox;

    // alarm으로 보낼 panel
    private JPanel mainPanel = new JPanel(new GridLayout(1,2));

    public static JPanel comboPanel = new JPanel(new FlowLayout());
    public static JPanel btnPanel = new JPanel(new FlowLayout());
    public static JPanel quizPanel = new JPanel(new FlowLayout());
    private Clip clip;
    public TimeGui() {
        alarms = new ArrayList<>();
//        alarms.set(new Font("",1,20));
        createUI();
        timer = new Timer(1000, new ActionListener() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

            @Override
            public void actionPerformed(ActionEvent e) {
                String currentTime = simpleDateFormat.format(Calendar.getInstance().getTime());

                if (alarms.contains(currentTime)) {
                    timer.stop();
                    //리스트에서 알람삭제
                    alarms.remove(currentTime);
                    //GUI리스트 삭제
                    listModel.removeElement(currentTime);
                    if(!alarms.isEmpty()){
                        timer.restart();
                    }
                    alarmSound();
                    quizGui();
                }
            }
        });
    }

    private void createUI() {
        JPanel inputPanel1 = new JPanel(new GridLayout(3,1));   // 좌
        JPanel inputPanel2 = new JPanel();                                // 우
        //1번 패널 그리드 3행
        listModel = new DefaultListModel<>();
        alarmList = new JList<>(listModel);
        alarmList.setFont(new Font("",1,60));
        alarmList.setCellRenderer(new DefaultListCellRenderer(){
            public int getHorizontalAlignment(){
                return CENTER;
            }
        });

        //알람설정라벨
        JLabel hourSettingLabel = new JLabel("시");
        JLabel minuteSettingLabel = new JLabel("분");
        JLabel secSettingLabel = new JLabel("초");
        hourSettingLabel.setFont(new Font("", Font.TRUETYPE_FONT, 15));
        minuteSettingLabel.setFont(new Font("", Font.TRUETYPE_FONT, 15));
        secSettingLabel.setFont(new Font("", Font.TRUETYPE_FONT, 15));


        //알람 설정 콤보
        String hour[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"};
        hourComboBox = new JComboBox<>(hour);
        String minute[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"};
        minuteComboBox = new JComboBox<>(minute);
        String sec[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"};
        secComboBox = new JComboBox<>(sec);

        hourComboBox.setFont(new Font("Default", Font.TRUETYPE_FONT, 15));
        minuteComboBox.setFont(new Font("Default", Font.TRUETYPE_FONT, 15));
        secComboBox.setFont(new Font("Default", Font.TRUETYPE_FONT, 15));


        //콤보패널
        comboPanel.add(hourSettingLabel);
        comboPanel.add(hourComboBox);
        comboPanel.add(minuteSettingLabel);
        comboPanel.add(minuteComboBox);
        comboPanel.add(secSettingLabel);
        comboPanel.add(secComboBox);

        //알람저장 버튼
        JButton settingBtn = new JButton("알람 저장");
        settingBtn.setFont(new Font("", Font.TRUETYPE_FONT, 15));
        //알람저장
        settingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //알람 최대 사이즈
                if(alarms.size() < 6){
                    String alarmSetTime = hourComboBox.getSelectedItem() + ":" + minuteComboBox.getSelectedItem() + ":" + secComboBox.getSelectedItem();

                    //알람 추가
                    alarms.add(alarmSetTime);
                    listModel.addElement(alarmSetTime);
//                    listModel.setSize(50);

                    if(!timer.isRunning()){
                        timer.start();
                    }
                    JOptionPane.showMessageDialog(null, "알람 설정은 " + hourComboBox.getSelectedItem() + ":" + minuteComboBox.getSelectedItem(), "저장 완료", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "알람 최대 6개 설정 가능합니다.", "최대 6개 설정 가능!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //알람 삭제
        JButton removeBtn = new JButton("알람 삭제");
        removeBtn.setFont(new Font("", Font.TRUETYPE_FONT, 15));
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlarm  = alarmList.getSelectedValue();
                if(selectedAlarm != null){
                    //알람 삭제
                    alarms.remove(selectedAlarm);
                    listModel.removeElement(selectedAlarm);
                    //빈 경우 멈춤
                    if(alarms.isEmpty()){
                        timer.stop();
                    }
                }
            }
        });

        //알람 전체 삭제
        JButton deleteAllBtn = new JButton("알람 전체 삭제");
        deleteAllBtn.setFont(new Font("", Font.TRUETYPE_FONT, 15));
        deleteAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarms.clear();
                listModel.clear();
                timer.stop();
            }
        });

        //저장삭제 버튼 패널
        btnPanel.add(settingBtn);
        btnPanel.add(removeBtn);
        btnPanel.add(deleteAllBtn);

        String[] quizOptions = {"덧셈 문제", "과목 문제"};
        quizComboBox = new JComboBox<>(quizOptions);
        quizComboBox.setFont(new Font("", Font.TRUETYPE_FONT, 15));

        JLabel quizGenreLabel = new JLabel("퀴즈 유형");
        quizGenreLabel.setFont(new Font("", Font.BOLD, 20));
        quizComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    quizGenreLabel.setText((String) e.getItem());
                }
            }
        });

        quizPanel.add(quizComboBox);
        quizPanel.add(quizGenreLabel);

        //1번 패널
        inputPanel1.add(comboPanel);
        inputPanel1.add(btnPanel);
        inputPanel1.add(quizPanel);

        mainPanel.add(inputPanel1);
        mainPanel.setVisible(true);

        inputPanel2.setVisible(true);
        mainPanel.add(inputPanel2);

        //

        inputPanel2.setLayout(new BorderLayout());
        inputPanel2.add(new JScrollPane(alarmList), BorderLayout.CENTER);    //2번



    }

    public String getSelectedOption(){  // 콤보박스 값 가져오는 것
        return (String) quizComboBox.getSelectedItem();
    }
    public void alarmSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/siren.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("에러");
            ex.printStackTrace();
        }
    }
    public void alarmStop(){
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    public void quizGui(){

        QuizGenre quizGenre = new QuizGenre(this);
        // Layout components
        JPanel quizMainPanel = new JPanel();
        quizMainPanel.add(quizGenre.questionLabel);
        quizMainPanel.add(quizGenre.answerField);
        quizMainPanel.add(quizGenre.submitButton);

        quizGenre.quizFrame.add(quizMainPanel);

        quizGenre.quizFrame.setLocationRelativeTo(null); // Center window
        quizGenre.quizFrame.setVisible(true);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}

