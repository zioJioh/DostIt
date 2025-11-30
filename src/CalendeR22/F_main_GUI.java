package CalendeR22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class F_main_GUI {
    public static Box horizontalBox2; // 일반 수평 상자
    JPanel frame = new JPanel(); // 달력 전체 프레임
    JPanel inner;
    public static JPanel panel2;
    JPanel outer;
    private static JLabel totalAmountLabel;
    JLabel month; // 달력 표시 달
    JButton nextMonth; // 다음달 넘기기 버튼
    JButton prevMonth; // 이전달 뒤로가기 버튼
    static LocalDate localDate;
    static ArrayList<F_Event_GUI> events; // 이벤트 처리

    public F_main_GUI() {
        events = populateEventList();

        outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.PAGE_AXIS));

        horizontalBox2 = Box.createHorizontalBox();
        horizontalBox2.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) horizontalBox2.getMinimumSize().getHeight()));

        inner = new JPanel();

        panel2 = new JPanel();

        GridLayout gg = new GridLayout(5,7);
        panel2.setLayout(gg);
        panel2.setOpaque(true);

        Date date = new Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        initMonth(localDate);

        month = new JLabel(localDate.getMonth().toString());
        month.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        month.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextMonth = new JButton("다음 달");
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate newDate = localDate.plusMonths(1);
                initMonth(newDate);
                month.setText(newDate.getMonth().toString());
                localDate = newDate;
            }
        });

        totalAmountLabel = new JLabel("이번 달 총 금액: ");
        totalAmountLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        totalAmountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalAmountLabel.setText("이번 달 총 금액: " + getTotalAmount());

        prevMonth = new JButton("이전 달");
        prevMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate newDate = localDate.plusMonths(-1);
                initMonth(newDate);
                month.setText(newDate.getMonth().toString());
                localDate = newDate;
            }
        });

        horizontalBox2.add(Box.createHorizontalStrut(3));
        horizontalBox2.add(prevMonth);
        horizontalBox2.add(Box.createGlue());
        horizontalBox2.add(month);
        horizontalBox2.add(Box.createGlue());
        horizontalBox2.add(totalAmountLabel);
        horizontalBox2.add(Box.createGlue());
        horizontalBox2.add(nextMonth);
        horizontalBox2.add(Box.createHorizontalStrut(3));

        outer.add(horizontalBox2);
        outer.add(panel2);

        frame.setLayout(new BorderLayout());
        frame.add(outer, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static int getTotalAmount() {
        int totalIncome = 0;
        int totalExpose = 0;

        for (F_Event_GUI event : events) {
            totalIncome += event.getIncomeAmount();
            totalExpose += event.getExposeAmount();
        }

        int totalAmount = totalIncome - totalExpose;
        return totalAmount;
    }

    public static void updateTotalAmountLabel() {
        HashMap<LocalDate, ArrayList<F_Event_GUI>> eventMap = getEventMap(events);
        int totalAmount = 0;

        // 이번 달의 모든 이벤트를 가져옴
        ArrayList<F_Event_GUI> eventsOfMonth = eventMap.get(localDate);

        // 이번 달의 모든 이벤트의 income과 expose 값을 더함
        if (eventsOfMonth != null) {
            for (F_Event_GUI event : eventsOfMonth) {
                totalAmount += event.getIncomeAmount() - event.getExposeAmount();
            }
        }
        // 전체 날짜에 입력된 값으로 총 금액을 업데이트
        for (Component component : panel2.getComponents()) {
            if (component instanceof F_Day_GUI) {
                F_Day_GUI dayGUI = (F_Day_GUI) component;
                dayGUI.setEventMap(eventMap);
            }
        }
        // 총 금액 표시 업데이트
        totalAmountLabel.setText("이번 달 총 금액: " + getTotalAmount());
    }

    protected ArrayList<F_Event_GUI> populateEventList() {
        ArrayList<F_Event_GUI> tempEvents = new ArrayList<>();
        return tempEvents;
    }

    public static void addEvent(F_Event_GUI event) {
        events.add(event);
        updateTotalAmountLabel();
    }

    private static HashMap<LocalDate, ArrayList<F_Event_GUI>> getEventMap(ArrayList<F_Event_GUI> events) {
        HashMap<LocalDate, ArrayList<F_Event_GUI>> eventMap = new HashMap<>();

        for (F_Event_GUI event : events) {
            LocalDate date = event.getDate();
            if (!eventMap.containsKey(date)) {
                eventMap.put(date, new ArrayList<>());
            }
            eventMap.get(date).add(event);
        }

        return eventMap;
    }

    public static void initMonth(LocalDate date) {
        panel2.removeAll();

        HashMap<LocalDate, ArrayList<F_Event_GUI>> eventMap = getEventMap(events);

        for (int i = 1; i <= date.lengthOfMonth(); i++) {
            F_Day_GUI dayGUI = new F_Day_GUI(date, i, eventMap);
            panel2.add(dayGUI);
        }
        panel2.revalidate();
        panel2.repaint();
    }

    public JPanel getPanel(){
        return frame;
    }
}
