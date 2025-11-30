package CalendeR22;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class C_Day_GUI extends JPanel implements MouseListener {
    public static int calorieTemp;
    public static int calorieAmount;
    Color background;
    Color foreground;
    JLabel date; // 캘린더 날짜 표기 라벨
    Border blackline;
    LocalDate localDate;
    JLabel calorie;
    int dayNum;
    ArrayList<C_Event_GUI> events;
    Box dateBox;
    // 수정된 부분: HashMap 추가
    HashMap<LocalDate, ArrayList<C_Event_GUI>> eventMap;
    public C_Day_GUI(LocalDate localdate, int day, HashMap<LocalDate, ArrayList<C_Event_GUI>> eventMap) {
        background = Color.WHITE;
        foreground = Color.BLACK;
        blackline = BorderFactory.createLineBorder(Color.black);
        date = new JLabel("" + day);
        localDate = localdate.of(localdate.getYear(), localdate.getMonth(), day);
        dayNum = day;
        setEventMap(eventMap); // eventMap을 설정하는 메서드 호출
        dateBox = Box.createHorizontalBox();

        JPanel caloriePanel = new JPanel();
        caloriePanel.setLayout(new BorderLayout());
        calorie = new JLabel("칼로리 : " + getCalorieAmount());
        caloriePanel.add(calorie, BorderLayout.CENTER);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BorderLayout());
        labelsPanel.add(date, BorderLayout.NORTH);
        labelsPanel.add(caloriePanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(dateBox, BorderLayout.CENTER);
        add(labelsPanel, BorderLayout.SOUTH);

        populateEvents();
    }

    // 수정된 부분: getIncomeAmount 메서드 수정
    public int getCalorieAmount() {
        int amount = 0;
        if (events != null) {
            for (C_Event_GUI event : events) {
                amount += event.getCalorieAmount();
            }
            if (C_EventDialog_GUI.isUndoButtonClicked()) {
                amount -= getLastCalorie();
            }
        }
        return amount;
    }

    private int getLastCalorie() {
        if (events != null && events.size() > 0) {
            C_Event_GUI lastEvent = events.get(events.size() - 1);
            return lastEvent.getCalorieAmount();
        }
        return 0;
    }

    public HashMap<LocalDate, ArrayList<C_Event_GUI>> getEventMap() {
        HashMap<LocalDate, ArrayList<C_Event_GUI>> eventMap = new HashMap<>();
        try {
            File file = new File("events.txt");
            if (file.exists()) {
                Scanner scan = new Scanner(file);
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    String[] parts = line.split(",");
                    String dateString = parts[0].trim();
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 날짜 형식 지정
                    String event = parts[1].trim();
                    String amountString = parts[2].trim();
                    int amount = Integer.parseInt(amountString);
                    C_Event_GUI fEvent = new C_Event_GUI(date, amount, 0); // 수정된 부분
                    if (eventMap.containsKey(date)) {
                        eventMap.get(date).add(fEvent);
                    } else {
                        ArrayList<C_Event_GUI> events = new ArrayList<>();
                        events.add(fEvent);
                        eventMap.put(date, events);
                    }
                }
                scan.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventMap;
    }
    public void populateEvents() {
        GridLayout layout = new GridLayout(5, 1);
        setLayout(layout);
        setBackground(background);
        date.setForeground(foreground);
        dateBox.add(Box.createGlue());
        dateBox.add(date);
        dateBox.add(Box.createGlue());
        add(dateBox);
        setBorder(blackline);
        addMouseListener(this);

        if (events != null) {
            for (C_Event_GUI event : events) {
                if (event != null) {
                    String eventDateStr = event.getLocalDate();
                    if (eventDateStr != null) {
                        LocalDate eventDate = LocalDate.parse(eventDateStr);

                        if (eventDate.equals(localDate)) {
                            int num = events.indexOf(event);
                            Box eventBox = Box.createHorizontalBox();
                            eventBox.setOpaque(true);
                            eventBox.add(Box.createGlue());
                            eventBox.add(Box.createGlue());
                            eventBox.addMouseListener(new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (e.getButton() == MouseEvent.BUTTON3) {
                                        C_EventDialog_GUI eventDialog = new C_EventDialog_GUI(num);
                                        if (eventDialog.isEventDeleted()) {
                                            events.remove(num);
                                            C_main_GUI.initMonth(localDate);
                                        }
                                    } else {
                                        new C_EventDialog_GUI(num);
                                    }
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
                            add(eventBox);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new C_EventDialog_GUI(localDate, dayNum);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        background = Color.BLACK;
        foreground = Color.WHITE;
        setBackground(background);
        date.setForeground(foreground);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        background = Color.WHITE;
        foreground = Color.BLACK;
        setBackground(background);
        date.setForeground(foreground);
    }

    // 수정된 부분: setEventMap 메서드 추가
    public void setEventMap(HashMap<LocalDate, ArrayList<C_Event_GUI>> eventMap) {
        this.eventMap = eventMap;
        if (eventMap != null) {
            events = eventMap.get(this.localDate);
        } else {
            events = null;
        }

        if (calorie != null) {
            calorie.setText("칼로리 : " + getCalorieAmount());
        }
    }
}
