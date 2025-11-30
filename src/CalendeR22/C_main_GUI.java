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

public class C_main_GUI {
    public static Box horizontalBox3; // 일반 수평 상자
    JPanel frame = new JPanel(); // 달력 전체 프레임
    JPanel inner;
    public static JPanel panel3;
    JPanel outer;
    private static JLabel totalAmountLabel;
    JLabel month; // 달력 표시 달
    JButton nextMonth; // 다음달 넘기기 버튼
    JButton prevMonth; // 이전달 뒤로가기 버튼
    static LocalDate localDate;
    static ArrayList<C_Event_GUI> events; // 이벤트 처리

    public C_main_GUI() {
        events = populateEventList();

        outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.PAGE_AXIS));

        horizontalBox3 = Box.createHorizontalBox();
        horizontalBox3.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) horizontalBox3.getMinimumSize().getHeight()));

        inner = new JPanel();

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(5, 7));

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

        totalAmountLabel = new JLabel("이번 달 총 칼로리: ");
        totalAmountLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        totalAmountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalAmountLabel.setText("이번 달 총 칼로리: " + getTotalAmount());

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

        horizontalBox3.add(Box.createHorizontalStrut(3));
        horizontalBox3.add(prevMonth);
        horizontalBox3.add(Box.createGlue());
        horizontalBox3.add(month);
        horizontalBox3.add(Box.createGlue());
        horizontalBox3.add(totalAmountLabel);
        horizontalBox3.add(Box.createGlue());
        horizontalBox3.add(nextMonth);
        horizontalBox3.add(Box.createHorizontalStrut(3));

        outer.add(horizontalBox3);
        outer.add(panel3);

        frame.setLayout(new BorderLayout());
        frame.add(outer, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static int getTotalAmount() {
        int totalCalorie = 0;

        for (C_Event_GUI event : events) {
            totalCalorie += event.getCalorieAmount();
        }

        int totalAmount = totalCalorie;
        return totalAmount;
    }

    public static void updateTotalAmountLabel() {
        HashMap<LocalDate, ArrayList<C_Event_GUI>> eventMap = getEventMap(events);
        int totalAmount = 0;

        // 이번 달의 모든 이벤트를 가져옴
        ArrayList<C_Event_GUI> eventsOfMonth = eventMap.get(localDate);

        // 이번 달의 모든 이벤트의 income과 expose 값을 더함
        if (eventsOfMonth != null) {
            for (C_Event_GUI event : eventsOfMonth) {
                totalAmount += event.getCalorieAmount();
            }
        }
        // 전체 날짜에 입력된 값으로 총 금액을 업데이트
        for (Component component : panel3.getComponents()) {
            if (component instanceof C_Day_GUI) {
                C_Day_GUI dayGUI = (C_Day_GUI) component;
                dayGUI.setEventMap(eventMap);
            }
        }
        // 총 금액 표시 업데이트
        totalAmountLabel.setText("이번 달 총 칼로리: " + getTotalAmount());
    }

    protected ArrayList<C_Event_GUI> populateEventList() {
        ArrayList<C_Event_GUI> tempEvents = new ArrayList<>();
        return tempEvents;
    }

    public static void addEvent(C_Event_GUI event) {
        events.add(event);
        updateTotalAmountLabel();
    }

    private static HashMap<LocalDate, ArrayList<C_Event_GUI>> getEventMap(ArrayList<C_Event_GUI> events) {
        HashMap<LocalDate, ArrayList<C_Event_GUI>> eventMap = new HashMap<>();

        for (C_Event_GUI event : events) {
            LocalDate date = event.getDate();
            if (!eventMap.containsKey(date)) {
                eventMap.put(date, new ArrayList<>());
            }
            eventMap.get(date).add(event);
        }

        return eventMap;
    }

    public static void initMonth(LocalDate date) {
        panel3.removeAll();

        HashMap<LocalDate, ArrayList<C_Event_GUI>> eventMap = getEventMap(events);

        for (int i = 1; i <= date.lengthOfMonth(); i++) {
            C_Day_GUI dayGUI = new C_Day_GUI(date, i, eventMap);
            panel3.add(dayGUI);
//            System.out.println(i);
        }

        panel3.revalidate();
        panel3.repaint();
    }

    public JPanel getPanel(){
        return frame;
    }
}
