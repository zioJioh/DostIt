package CalendeR22;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Cal_Day_GUI extends JPanel implements MouseListener {
    Color background;
    Color foreground;
    JLabel date; // 캘린더 날짜 표기 라벨
    Border blackline;
    LocalDate localDate;
    int dayNum;
    ArrayList<Cal_Event_GUI> events;
    Box dateBox;

    public Cal_Day_GUI(LocalDate localdate, int day, ArrayList<Cal_Event_GUI> list) {

        background = Color.WHITE;
        foreground = Color.BLACK;
        blackline = BorderFactory.createLineBorder(Color.black);
        date = new JLabel("" + day);
        localDate = localdate.of(localdate.getYear(), localdate.getMonth(), day);
        dayNum = day;
        events = list;
        dateBox = Box.createHorizontalBox();

        populateEvents();
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

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getDateTime().toLocalDate().equals(localDate)) {
                String result = LocalTime.parse(events.get(i).getDateTime().toLocalTime().toString(), DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
                int num = i;
                Box event = Box.createHorizontalBox();
                event.setOpaque(true);
                Color chosenColor = getColorForEvent(events.get(i));
                event.setBackground(chosenColor);
                event.add(Box.createGlue());
                JLabel eventBlurb = new JLabel(events.get(i).getName() + " - " + result);
                eventBlurb.setForeground(Color.WHITE);
                event.add(eventBlurb);
                event.add(Box.createGlue());
                event.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            Cal_EventDialog_GUI eventDialog = new Cal_EventDialog_GUI(num);
                            eventDialog.deleteEvent(localDate);
                            if (eventDialog.isEventDeleted()) {
                                events.remove(num);
                                Cal_main_GUI.initMonth(localDate);
                            }
                        } else {
                            new Cal_EventDialog_GUI(num);
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
                        event.setBackground(Color.BLACK);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        event.setBackground(chosenColor);
                    }
                });
                add(event);
            }
        }
    }

    private Color getColorForEvent(Cal_Event_GUI event) {
        return Color.BLUE;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        new Cal_EventDialog_GUI(localDate, dayNum);
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
}
