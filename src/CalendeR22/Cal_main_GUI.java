package CalendeR22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Cal_main_GUI {
    JMenuBar menuBar; // 달력 전체 메뉴
    public static Box horizontalBox; // 일반 수평 상자
    JPanel frame = new JPanel(); // 달력 전체 프레임
    JPanel inner;
    public static JPanel panel;
    JPanel outer;
    JLabel month; // 달력 표시 달
    JButton nextMonth; // 다음달 넘기기 버튼
    JButton prevMonth; // 이전달 뒤로가기 버튼
    static LocalDate localDate;
    static ArrayList<Cal_Event_GUI> events; // 이벤트 처리

    public Cal_main_GUI() {
        events = populateEventList();

        outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.PAGE_AXIS));

        horizontalBox = Box.createHorizontalBox();
        horizontalBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) horizontalBox.getMinimumSize().getHeight()));

        inner = new JPanel();

        panel = new JPanel();

        GridLayout layout = new GridLayout(5, 7);

        panel.setLayout(layout);
        panel.setOpaque(true);

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
        horizontalBox.add(Box.createHorizontalStrut(3));
        horizontalBox.add(prevMonth);
        horizontalBox.add(Box.createGlue());
        horizontalBox.add(month);
        horizontalBox.add(Box.createGlue());
        horizontalBox.add(nextMonth);
        horizontalBox.add(Box.createHorizontalStrut(3));


        outer.add(horizontalBox);
        outer.add(panel);
        frame.setLayout(new BorderLayout());
        frame.add(outer,BorderLayout.CENTER);
        frame.setVisible(true);
    }
    protected ArrayList<Cal_Event_GUI> populateEventList() {
        ArrayList<Cal_Event_GUI> tempEvents = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Events.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        tempEvents.add(new Cal_Event_GUI(parts[0], LocalDateTime.parse(parts[1], formatter), parts[2]));
                    } else {
                        System.out.println("잘못된 방식입니다.: " + line);
                    }
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Events.txt file not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempEvents;
    }


    public static void addEvent(Cal_Event_GUI event) {
        if (events.isEmpty()) {
            events.add(event);
            System.out.println(events.toString());
            return;
        }

        int index = 0;
        while (index < events.size() && event.getDateTime().isBefore(events.get(index).getDateTime())) {
            index++;
        }

        events.add(index, event);
        System.out.println(events.toString());
    }

    public static void removeEvent(Cal_Event_GUI event) {
        events.remove(event);
    }

    public static void initMonth(LocalDate date) {
        panel.removeAll();
        for (int i = 1; i < date.lengthOfMonth() + 1; i++) {
            panel.add(new Cal_Day_GUI(date, i, events));
        }
        panel.revalidate();
        panel.repaint();
    }

    public JPanel getPanel(){
        return frame;
    }

}