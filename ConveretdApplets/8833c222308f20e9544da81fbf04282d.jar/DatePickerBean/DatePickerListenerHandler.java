// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionListener;

class DatePickerListenerHandler implements ActionListener
{
    DatePicker dp;
    int CalWinWidth;
    int CalWinHeight;
    Vector DateListenerVector;
    
    public DatePickerListenerHandler(final DatePicker dp) {
        this.DateListenerVector = new Vector(10, 1);
        this.dp = dp;
    }
    
    void FireNewDateSelected() {
        final Enumeration<DatePickerListener> elements = this.DateListenerVector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().dateChanged(this.dp);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.showCalendar();
    }
    
    int addDatePickerListener(final DatePickerListener datePickerListener) {
        boolean b = false;
        final Enumeration<DatePickerListener> elements = (Enumeration<DatePickerListener>)this.DateListenerVector.elements();
        while (elements.hasMoreElements()) {
            if (datePickerListener == elements.nextElement()) {
                b = true;
                break;
            }
        }
        if (!b) {
            this.DateListenerVector.addElement(datePickerListener);
        }
        return this.DateListenerVector.size();
    }
    
    int removeDatePickerListener(final DatePickerListener datePickerListener) {
        final Enumeration<DatePickerListener> elements = this.DateListenerVector.elements();
        while (elements.hasMoreElements()) {
            if (datePickerListener == elements.nextElement()) {
                this.DateListenerVector.removeElement(datePickerListener);
                break;
            }
        }
        return this.DateListenerVector.size();
    }
    
    public void showCalendar() {
        this.CalWinHeight = this.dp.getCalendarHeight();
        this.CalWinWidth = this.dp.getCalendarWidth();
        int height = 600;
        int width = 800;
        int calendar_y;
        int x;
        try {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            height = screenSize.height;
            width = screenSize.width;
            if (this.dp.getAutoCalendarPosition()) {
                final Point locationOnScreen = this.dp.getLocationOnScreen();
                final Dimension size = this.dp.getSize();
                if (this.CalWinHeight + 3 <= height - (locationOnScreen.y + size.height)) {
                    calendar_y = locationOnScreen.y + size.height + 3;
                }
                else {
                    calendar_y = locationOnScreen.y - this.CalWinHeight - 3;
                }
                x = locationOnScreen.x;
                if (this.CalWinWidth > width - locationOnScreen.x) {
                    x = x - (this.CalWinWidth - (width - locationOnScreen.x)) - 3;
                }
                else if (locationOnScreen.x <= 1) {
                    x = 3;
                }
            }
            else {
                x = (width - this.CalWinWidth) / 2;
                calendar_y = (height - this.CalWinHeight) / 2;
            }
            if (calendar_y <= 1) {
                x = (width - this.CalWinWidth) / 2;
                calendar_y = (height - this.CalWinHeight) / 2;
            }
        }
        catch (Exception ex) {
            x = (width - this.CalWinWidth) / 2;
            calendar_y = (height - this.CalWinHeight) / 2;
        }
        this.dp.calendar_x = x;
        this.dp.calendar_y = calendar_y;
        this.dp.cwin.setBounds(x, calendar_y, this.CalWinWidth, this.CalWinHeight);
        this.dp.calpan.setColors(this.dp.getCalendarBackground(), this.dp.getCalendarArrowColor(), this.dp.getCalendarWeekdaysColor(), this.dp.getCalendarDaysColor(), this.dp.getCalendarMonthColor(), this.dp.getCalendarYearColor(), this.dp.getCalendarMouseOverYear_Color(), this.dp.getCalendarSelectionColor());
        this.dp.cwin.setVisible(true);
    }
    
    public void showCalendar(final int calendar_x, final int calendar_y) {
        this.CalWinHeight = this.dp.getCalendarHeight();
        this.CalWinWidth = this.dp.getCalendarWidth();
        this.dp.calendar_x = calendar_x;
        this.dp.calendar_y = calendar_y;
        this.dp.cwin.setBounds(calendar_x, calendar_y, this.CalWinWidth, this.CalWinHeight);
        this.dp.calpan.setColors(this.dp.getCalendarBackground(), this.dp.getCalendarArrowColor(), this.dp.getCalendarWeekdaysColor(), this.dp.getCalendarDaysColor(), this.dp.getCalendarMonthColor(), this.dp.getCalendarYearColor(), this.dp.getCalendarMouseOverYear_Color(), this.dp.getCalendarSelectionColor());
        this.dp.cwin.setVisible(true);
    }
}
