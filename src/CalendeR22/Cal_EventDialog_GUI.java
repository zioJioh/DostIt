package CalendeR22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cal_EventDialog_GUI extends JFrame {
    JLabel month; // 월 설정 라벨
    JLabel dayOfMonth; // 일 설정 라벨
    JPanel panel;
    JTextField eventName; // 일정 이름 입력 필드
    SpinnerNumberModel year;
    JComboBox monthList; // 월 리스트 박스
    JComboBox daysList; // 일 리스트 박스
    JComboBox timeList; // 시간 리스트 박스
    JTextField locationField;
    JButton apply; // 추가 버튼
    JButton delete; // 삭제 버튼
    private boolean eventDeleted = false;
    String[] times = new String[97];

    public Cal_EventDialog_GUI(LocalDate localDate, int day) {
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);

        GridLayout layout = new GridLayout(9, 1);

        panel = new JPanel();

        panel.setLayout(layout);
        eventName = new JTextField("일정 입력");
        eventName.setHorizontalAlignment(JTextField.CENTER);
        eventName.selectAll();
        eventName.setBackground(Color.GRAY);
        eventName.setForeground(Color.WHITE);
        eventName.setBorder(null);

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

        daysList = new JComboBox(days);
        daysList.setSelectedIndex(day - 1);

        year =
                new SpinnerNumberModel(localDate.getYear(),
                        localDate.getYear() - 100,
                        localDate.getYear() + 100,
                        1);


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

        Box yearSet = Box.createHorizontalBox();
        yearSet.add(Box.createHorizontalStrut(3));
        yearSet.add(new JLabel("년: "));
        yearSet.add(Box.createGlue());

        JSpinner spinner = new JSpinner(year);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "#");
        spinner.setEditor(editor);
        yearSet.add(spinner);
        yearSet.add(Box.createHorizontalStrut(3));


        int forCount = 0;
        String amPm = " AM";

        for (int z = 1; z < 3; z++) {

            for (int i = 1; i < 13; i++) {

                for (int j = 0; j < 4; j++) {

                    String hour = "00" + String.valueOf(i);
                    String minute = "00" + String.valueOf(j * 15);


                    times[forCount] = hour.substring(hour.length() - 2, hour.length()) + ":" + minute.substring(minute.length() - 2, minute.length()) + amPm;
                    forCount++;
                }

                if (i == 11 && z == 1) {

                    amPm = " PM";
                }

                if (i == 11 && z == 2) {

                    amPm = " AM";
                }
            }

        }

        timeList = new JComboBox(times);
        timeList.setSelectedIndex(24);

        Box timeSet = Box.createHorizontalBox();
        timeSet.add(Box.createHorizontalStrut(3));
        timeSet.add(new JLabel("시간: "));
        timeSet.add(Box.createGlue());
        timeSet.add(timeList);
        timeSet.add(Box.createHorizontalStrut(3));

        Box applyCancel = Box.createHorizontalBox();
        applyCancel.add(Box.createHorizontalStrut(3));
        applyCancel.add(Box.createGlue());
        apply = new JButton("추가");
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createEvent(localDate);
            }
        });
        delete = new JButton("삭제");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEvent(localDate);
            }
        });

        applyCancel.add(delete);
        applyCancel.add(Box.createHorizontalStrut(3));

        applyCancel.add(apply);

        applyCancel.add(Box.createHorizontalStrut(3));

        layout.setVgap(10);
        panel.add(eventName);
        panel.add(monthSet);
        panel.add(daySet);
        panel.add(yearSet);
        panel.add(timeSet);
        panel.add(applyCancel);


        setTitle("새로운 일정 추가");
        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(200, 400));


    }


    public Cal_EventDialog_GUI(int index) {

        this(Cal_main_GUI.events.get(index).getDateTime().toLocalDate(), Cal_main_GUI.events.get(index).getDateTime().getDayOfMonth());

        setTitle("일정 추가");
        eventName.setText(Cal_main_GUI.events.get(index).getName());
        monthList.setSelectedIndex(Cal_main_GUI.events.get(index).getDateTime().getMonthValue() - 1);
        daysList.setSelectedIndex(Cal_main_GUI.events.get(index).getDateTime().getDayOfMonth() - 1);
        timeList.setSelectedIndex(((Cal_main_GUI.events.get(index).getDateTime().getHour() - 1) * 4) + (Cal_main_GUI.events.get(index).getDateTime().getMinute() / 15));

        locationField = new JTextField();
        locationField.setText(Cal_main_GUI.events.get(index).getLocation());

        for (ActionListener al : apply.getActionListeners()) {
            apply.removeActionListener(al);
        }

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateEvent(index);
            }
        });

    }

    public void deleteEvent(LocalDate localDate) {
        int option = JOptionPane.showConfirmDialog(null, "정말로 일정을 삭제하시겠습니까?", "일정 삭제", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            for (int i = Cal_main_GUI.events.size() - 1; i >= 0; i--) {
                Cal_Event_GUI event = Cal_main_GUI.events.get(i);
                if (event.getDateTime().toLocalDate().equals(localDate)) {
                    int confirmOption = JOptionPane.showConfirmDialog(null, "정말로 해당 일정을 삭제하시겠습니까?: " + event.getName(), "일정 삭제", JOptionPane.YES_NO_OPTION);
                    if (confirmOption == JOptionPane.YES_OPTION) {
                        Cal_main_GUI.removeEvent(event);
                    }
                }
            }
            Cal_main_GUI.initMonth(Cal_main_GUI.localDate);
            eventDeleted = true;
            dispose();
        }
    }


    public boolean isEventDeleted() {
        return eventDeleted;
    }

    protected void createEvent(LocalDate localDate) {
        String name = eventName.getText();
        LocalTime time;
        String timeString = times[timeList.getSelectedIndex()];

        if (timeString.contains("AM")) {
            time = LocalTime.of(Integer.valueOf(timeString.substring(0, 2)), Integer.valueOf(timeString.substring(3, 5)));
        } else {
            time = LocalTime.of((12 + Integer.valueOf(timeString.substring(0, 2))) % 24, Integer.valueOf(timeString.substring(3, 5)));
        }

        LocalDateTime dateTime = LocalDateTime.of(localDate, time);
        String location = "";
        if (locationField != null) {
            location = locationField.getText();
        }

        Cal_Event_GUI event = new Cal_Event_GUI(name, dateTime, location);

        Cal_main_GUI.addEvent(event); // Calendar 클래스의 인스턴스에 addEvent 메소드 호출

        Cal_main_GUI.initMonth(Cal_main_GUI.localDate);
        dispose();
    }


    protected void updateEvent(int index) {

        String name = eventName.getText();
        LocalTime time;
        String timeString = times[timeList.getSelectedIndex()];

        if (timeString.contains("AM")) {

            time = LocalTime.of(Integer.valueOf(timeString.substring(0, 2)), Integer.valueOf(timeString.substring(3, 5)));

        } else {

            time = LocalTime.of((12 + Integer.valueOf(timeString.substring(0, 2))) % 24, Integer.valueOf(timeString.substring(3, 5)));

        }

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(year.getNumber().intValue(), monthList.getSelectedIndex() + 1, daysList.getSelectedIndex() + 1), time);

        String location = locationField.getText();


        Cal_main_GUI.events.set(index, new Cal_Event_GUI(name, dateTime, location));
        Cal_main_GUI.initMonth(Cal_main_GUI.localDate);
        dispose();
    }
}
