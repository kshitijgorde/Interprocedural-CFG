// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import javax.swing.BorderFactory;
import org.jfree.date.SerialDate;
import java.util.Vector;
import java.util.Enumeration;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.util.Calendar;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class DateChooserPanel extends JPanel implements ActionListener
{
    private Calendar chosenDate;
    private Color chosenDateButtonColor;
    private Color chosenMonthButtonColor;
    private Color chosenOtherButtonColor;
    private int firstDayOfWeek;
    private int yearSelectionRange;
    private Font dateFont;
    private JComboBox monthSelector;
    private JComboBox yearSelector;
    private JButton todayButton;
    private JButton[] buttons;
    private boolean refreshing;
    
    public DateChooserPanel() {
        this(Calendar.getInstance(), false);
    }
    
    public DateChooserPanel(final Calendar chosenDate, final boolean b) {
        super(new BorderLayout());
        this.chosenDate = null;
        this.chosenDateButtonColor = Color.red;
        this.chosenMonthButtonColor = Color.lightGray;
        this.chosenOtherButtonColor = Color.darkGray;
        this.firstDayOfWeek = 1;
        this.yearSelectionRange = 20;
        this.dateFont = new Font("SansSerif", 0, 10);
        this.monthSelector = null;
        this.yearSelector = null;
        this.todayButton = null;
        this.buttons = null;
        this.refreshing = false;
        this.add(this.constructSelectionPanel(), "North");
        this.add(this.getCalendarPanel(), "Center");
        if (b) {
            this.add(this.constructControlPanel(), "South");
        }
        this.chosenDate = chosenDate;
        this.setDate(chosenDate.getTime());
    }
    
    public void setDate(final Date time) {
        this.chosenDate.setTime(time);
        this.monthSelector.setSelectedIndex(this.chosenDate.get(2));
        this.refreshYearSelector();
        this.refreshButtons();
    }
    
    public Date getDate() {
        return this.chosenDate.getTime();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("monthSelectionChanged")) {
            this.chosenDate.set(2, ((JComboBox)actionEvent.getSource()).getSelectedIndex());
            this.refreshButtons();
        }
        else if (actionEvent.getActionCommand().equals("yearSelectionChanged")) {
            if (!this.refreshing) {
                this.chosenDate.set(1, (int)((JComboBox)actionEvent.getSource()).getSelectedItem());
                this.refreshYearSelector();
                this.refreshButtons();
            }
        }
        else if (actionEvent.getActionCommand().equals("todayButtonClicked")) {
            this.setDate(new Date());
        }
        else if (actionEvent.getActionCommand().equals("dateButtonClicked")) {
            final int int1 = Integer.parseInt(((JButton)actionEvent.getSource()).getName());
            final Calendar firstVisibleDate = this.getFirstVisibleDate();
            firstVisibleDate.add(5, int1);
            this.setDate(firstVisibleDate.getTime());
        }
    }
    
    private JPanel getCalendarPanel() {
        final JPanel panel = new JPanel(new GridLayout(7, 7));
        panel.add(new JLabel("Sun", 0));
        panel.add(new JLabel("Mon", 0));
        panel.add(new JLabel("Tue", 0));
        panel.add(new JLabel("Wed", 0));
        panel.add(new JLabel("Thu", 0));
        panel.add(new JLabel("Fri", 0));
        panel.add(new JLabel("Sat", 0));
        this.buttons = new JButton[42];
        for (int i = 0; i < 42; ++i) {
            final JButton button = new JButton("");
            button.setMargin(new Insets(1, 1, 1, 1));
            button.setName(new Integer(i).toString());
            button.setFont(this.dateFont);
            button.setFocusPainted(false);
            button.setActionCommand("dateButtonClicked");
            button.addActionListener(this);
            panel.add(this.buttons[i] = button);
        }
        return panel;
    }
    
    private Color getButtonColor(final Calendar calendar) {
        if (this.equalDates(calendar, this.chosenDate)) {
            return this.chosenDateButtonColor;
        }
        if (calendar.get(2) == this.chosenDate.get(2)) {
            return this.chosenMonthButtonColor;
        }
        return this.chosenOtherButtonColor;
    }
    
    private boolean equalDates(final Calendar calendar, final Calendar calendar2) {
        return calendar.get(5) == calendar2.get(5) && calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1);
    }
    
    private Calendar getFirstVisibleDate() {
        final Calendar instance = Calendar.getInstance();
        instance.set(this.chosenDate.get(1), this.chosenDate.get(2), 1);
        instance.add(5, -1);
        while (instance.get(7) != this.getFirstDayOfWeek()) {
            instance.add(5, -1);
        }
        return instance;
    }
    
    private int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }
    
    private void refreshButtons() {
        final Calendar firstVisibleDate = this.getFirstVisibleDate();
        for (int i = 0; i < 42; ++i) {
            final JButton button = this.buttons[i];
            button.setText(new Integer(firstVisibleDate.get(5)).toString());
            button.setBackground(this.getButtonColor(firstVisibleDate));
            firstVisibleDate.add(5, 1);
        }
    }
    
    private void refreshYearSelector() {
        if (!this.refreshing) {
            this.refreshing = true;
            this.yearSelector.removeAllItems();
            final Enumeration<Object> elements = this.getYears(this.chosenDate.get(1)).elements();
            while (elements.hasMoreElements()) {
                this.yearSelector.addItem(elements.nextElement());
            }
            this.yearSelector.setSelectedItem(new Integer(this.chosenDate.get(1)));
            this.refreshing = false;
        }
    }
    
    private Vector getYears(final int n) {
        final Vector<Integer> vector = new Vector<Integer>();
        for (int i = n - this.yearSelectionRange; i <= n + this.yearSelectionRange; ++i) {
            vector.addElement(new Integer(i));
        }
        return vector;
    }
    
    private JPanel constructSelectionPanel() {
        final JPanel panel = new JPanel();
        (this.monthSelector = new JComboBox((E[])SerialDate.getMonths())).addActionListener(this);
        this.monthSelector.setActionCommand("monthSelectionChanged");
        panel.add(this.monthSelector);
        (this.yearSelector = new JComboBox(this.getYears(0))).addActionListener(this);
        this.yearSelector.setActionCommand("yearSelectionChanged");
        panel.add(this.yearSelector);
        return panel;
    }
    
    private JPanel constructControlPanel() {
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        (this.todayButton = new JButton("Today")).addActionListener(this);
        this.todayButton.setActionCommand("todayButtonClicked");
        panel.add(this.todayButton);
        return panel;
    }
}
