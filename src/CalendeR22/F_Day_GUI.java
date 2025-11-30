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

public class F_Day_GUI extends JPanel implements MouseListener {
    public static int incomeTemp;
    public static int exposeTemp;
    public static int incomeAmount;
    public static int exposeAmount;
    Color background;
    Color foreground;
    JLabel date; // 캘린더 날짜 표기 라벨
    Border blackline;
    LocalDate localDate;
    JLabel income;
    JLabel expose;
    int dayNum;
    ArrayList<F_Event_GUI> events;
    Box dateBox;
    // 수정된 부분: HashMap 추가
    HashMap<LocalDate, ArrayList<F_Event_GUI>> eventMap;
    public F_Day_GUI(LocalDate localdate, int day, HashMap<LocalDate, ArrayList<F_Event_GUI>> eventMap) {
        background = Color.WHITE;
        foreground = Color.BLACK;
        blackline = BorderFactory.createLineBorder(Color.black);
        date = new JLabel("" + day);
        localDate = localdate.of(localdate.getYear(), localdate.getMonth(), day);
        dayNum = day;
        setEventMap(eventMap); // eventMap을 설정하는 메서드 호출
        dateBox = Box.createHorizontalBox();

        Box incomeExpose = Box.createHorizontalBox();

        JPanel FPanel = new JPanel();
        FPanel.setLayout(new BorderLayout());
        income = new JLabel("수입 : " + getIncomeAmount() + "   ");
        expose = new JLabel("지출 : " + getExposeAmount());
        /*income.setAlignmentX(Component.LEFT_ALIGNMENT);
        expose.setAlignmentX(Component.RIGHT_ALIGNMENT);*/
        incomeExpose.add(income);
        incomeExpose.add(expose);
        FPanel.add(incomeExpose, BorderLayout.CENTER);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BorderLayout());
        labelsPanel.add(date, BorderLayout.NORTH);
        labelsPanel.add(FPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(dateBox, BorderLayout.CENTER);
        add(labelsPanel, BorderLayout.SOUTH);

        populateEvents();
    }

    // 수정된 부분: getIncomeAmount 메서드 수정
    public int getIncomeAmount() {
        int amount = 0;
        if (events != null) {
            for (F_Event_GUI event : events) {
                amount += event.getIncomeAmount();
            }
            if (F_EventDialog_GUI.isUndoButtonClicked()) {
                amount -= getLastIncome();
            }
        }
        return amount;
    }

    public int getExposeAmount() {
        int amount = 0;
        if (events != null) {
            for (F_Event_GUI event : events) {
                amount += event.getExposeAmount();
            }
            if (F_EventDialog_GUI.isUndoButtonClicked()) {
                amount -= getLastExpose();
            }
        }
        return amount;
    }

    private int getLastIncome() {
        if (events != null && events.size() > 0) {
            F_Event_GUI lastEvent = events.get(events.size() - 1);
            return lastEvent.getIncomeAmount();
        }
        return 0;
    }

    private int getLastExpose() {
        if (events != null && events.size() > 0) {
            F_Event_GUI lastEvents = events.get(events.size() - 1);
            return lastEvents.getExposeAmount();
        }
        return 0;
    }

    public HashMap<LocalDate, ArrayList<F_Event_GUI>> getEventMap() {
        HashMap<LocalDate, ArrayList<F_Event_GUI>> eventMap = new HashMap<>();
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
                    F_Event_GUI fEvent = new F_Event_GUI(date, amount, 0, 0, 0); // 수정된 부분
                    if (eventMap.containsKey(date)) {
                        eventMap.get(date).add(fEvent);
                    } else {
                        ArrayList<F_Event_GUI> events = new ArrayList<>();
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
            for (F_Event_GUI event : events) {
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
                                        F_EventDialog_GUI eventDialog = new F_EventDialog_GUI(num);
                                        if (eventDialog.isEventDeleted()) {
                                            events.remove(num);
                                            F_main_GUI.initMonth(localDate);
                                        }
                                    } else {
                                        new F_EventDialog_GUI(num);
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
        new F_EventDialog_GUI(localDate, dayNum);
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
    public void setEventMap(HashMap<LocalDate, ArrayList<F_Event_GUI>> eventMap) {
        this.eventMap = eventMap;
        if (eventMap != null) {
            events = eventMap.get(this.localDate);
        } else {
            events = null;
        }

        if (income != null) {
            income.setText("수입 : " + getIncomeAmount());
        }

        if (expose != null) {
            expose.setText("지출 : " + getExposeAmount());
        }
    }
}
