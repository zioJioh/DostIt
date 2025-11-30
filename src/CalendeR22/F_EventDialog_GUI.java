package CalendeR22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormatSymbols;
import java.time.LocalDate;

import static CalendeR22.F_main_GUI.events;

public class F_EventDialog_GUI extends JFrame {
    private int lastIncome = 0;
    private int lastExpose = 0;
    public static boolean undoButtonClicked = false;
    private static boolean applyButtonClicked = false;
    public int incomeAmount = 0;
    public int exposeAmount = 0;
    public static int incomeTemp=0;
    public static int exposeTemp=0;
    JLabel month;
    JPanel panel;
    JLabel dayOfMonth;
    JButton apply;
    static JButton undo;  // Undo 버튼 추가
    JComboBox monthList;
    private boolean eventDeleted = false;
    private JComboBox daysList;
    private JLabel income;
    private JLabel expose;
    private JTextField incomeText;
    private JTextField exposeText;
    private F_Event_GUI event;

    public F_EventDialog_GUI(LocalDate localDate, int day) {
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);

        GridLayout layout = new GridLayout(9, 1);

        JTextField incomeField = new JTextField(10);
        JTextField exposeField = new JTextField(10);

        incomeField.setText(Integer.toString(incomeAmount));
        exposeField.setText(Integer.toString(exposeAmount));

        income = new JLabel("수입: ");
        expose = new JLabel("지출: ");

        incomeText = incomeField;
        incomeField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(incomeField.getText().equals("0"))
                    incomeField.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        exposeText = exposeField;
        exposeField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(exposeField.getText().equals("0"))
                    exposeField.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        panel = new JPanel();
        panel.setLayout(layout);

        month = new JLabel("월: ");
        dayOfMonth = new JLabel("일: ");


        String[] months = new String[12];
        for (int i = 1; i < 13; i++) {

            months[i - 1] = new DateFormatSymbols().getMonths()[i - 1];
        }

        String[] days = new String[localDate.lengthOfMonth()];

        for (int i = 1; i < localDate.lengthOfMonth() + 1; i++) {

            days[i - 1] = "" + i;
        }

        monthList = new JComboBox(months);
        monthList.setSelectedIndex(localDate.getMonthValue() - 1);

        daysList = new JComboBox<>(days);
        daysList.setSelectedIndex(day - 1);

        Box monthSet = Box.createHorizontalBox();
        monthSet.add(Box.createHorizontalStrut(3));
        monthSet.add(month);
        monthSet.add(Box.createGlue());
        monthSet.add(monthList);
        monthSet.add(Box.createHorizontalStrut(3));

        Box daySet = Box.createHorizontalBox();
        daySet.add(Box.createHorizontalStrut(3));
        daySet.add(dayOfMonth);
        daySet.add(Box.createGlue());
        daySet.add(daysList);
        daySet.add(Box.createHorizontalStrut(3));

        Box applyUndo = Box.createHorizontalBox();  // Apply과 Undo를 담는 Box 생성
        applyUndo.add(Box.createHorizontalStrut(3));
        applyUndo.add(Box.createGlue());
        apply = new JButton("추가");
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int incomeValue = Integer.parseInt(incomeText.getText());
                    int exposeValue = Integer.parseInt(exposeText.getText());

                    int incomeDiff = incomeValue - lastIncome; // 이전 값과의 차이를 계산합니다.
                    int exposeDiff = exposeValue - lastExpose; // 이전 값과의 차이를 계산합니다.

                    incomeAmount += incomeDiff; // 차이를 적용합니다.
                    exposeAmount += exposeDiff; // 차이를 적용합니다.

                    incomeText.setText(Integer.toString(incomeAmount));
                    exposeText.setText(Integer.toString(exposeAmount));

                    F_main_GUI.updateTotalAmountLabel();

                    lastIncome = incomeValue; // 마지막 입력값을 업데이트합니다.
                    lastExpose = exposeValue; // 마지막 입력값을 업데이트합니다.

                    CreateOrUpdateEvent(localDate);
                } catch (NumberFormatException ex) {
                    // 유효한 숫자가 아닌 경우 예외 처리
                }
            }
        });

        applyUndo.add(apply);
        applyUndo.add(Box.createHorizontalStrut(3));

        undo = new JButton("되돌리기");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!events.isEmpty()) {
                    F_Event_GUI lastEvent = events.get(events.size() - 1);
                    int lastIncomeDiff = lastEvent.getIncomeAmount() - lastEvent.getIncomeTemp();
                    int lastExposeDiff = lastEvent.getExposeAmount() - lastEvent.getExposeTemp();

                    incomeAmount -= lastIncomeDiff; // income에서 마지막 입력값을 뺍니다.
                    exposeAmount -= lastExposeDiff; // expose에서 마지막 입력값을 뺍니다.

                    incomeText.setText(Integer.toString(incomeAmount));
                    exposeText.setText(Integer.toString(exposeAmount));

                    F_main_GUI.updateTotalAmountLabel();

                    CreateOrUpdateEvent(localDate);
                    dispose();
                }
            }
        });


        applyUndo.add(undo);  // Undo 버튼을 applyUndo Box에 추가
        applyUndo.add(Box.createHorizontalStrut(3));

        layout.setVgap(10);
        panel.add(monthSet);
        panel.add(daySet);
        panel.add(income);
        panel.add(incomeField);
        panel.add(expose);
        panel.add(exposeField);
        panel.add(applyUndo);

        setTitle("새로운 금액 추가");
        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(200, 400));
    }
    public static boolean isUndoButtonClicked() {
        return undoButtonClicked;
    }
    public F_EventDialog_GUI(int index) {
        super(events.get(index).getLocalDate());
        daysList.setSelectedIndex(events.get(index).getDayOfMonth() - 1);

        for (ActionListener al : apply.getActionListeners()) {
            apply.removeActionListener(al);
        }
    }

    public boolean isEventDeleted() {
        return eventDeleted;
    }

    protected void UpdateEvent(F_Event_GUI event) {
        int index = events.indexOf(event);  // event의 인덱스를 찾습니다.
        if (index != -1) {  // event가 리스트에 있는 경우
            events.set(index, event);  // event를 갱신합니다.
            F_Day_GUI.incomeAmount = event.getIncome();
            F_Day_GUI.exposeAmount = event.getExpose();
            F_Day_GUI.incomeTemp = event.getIncomeTemp();
            F_Day_GUI.exposeTemp = event.getExposeTemp();
        }
        F_main_GUI.initMonth(F_main_GUI.localDate);
        dispose();
    }

    protected void CreateOrUpdateEvent(LocalDate localDate) {
        if (event != null) {
            event.setIncome(incomeAmount);
            event.setExpose(exposeAmount);
            event.setTemp1(incomeTemp);
            event.setTemp2(exposeTemp);
            UpdateEvent(event);
        } else {
            event = new F_Event_GUI(localDate, incomeAmount, exposeAmount, incomeTemp, exposeTemp); // event 변수에 이벤트 저장
            F_main_GUI.addEvent(event); // F_main_GUI에 이벤트 추가
        }
        F_main_GUI.initMonth(F_main_GUI.localDate);
        undoButtonClicked = false;
        dispose();
    }
}