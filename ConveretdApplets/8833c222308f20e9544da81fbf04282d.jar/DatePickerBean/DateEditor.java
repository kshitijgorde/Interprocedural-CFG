// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.beans.PropertyEditorSupport;

public class DateEditor extends PropertyEditorSupport implements DatePickerListener
{
    private DatePicker dp;
    private DatePicker dpmin;
    private DatePicker dpmax;
    private boolean flag;
    private Panel p;
    
    public DateEditor() {
        this.flag = false;
        this.p = new Panel();
        (this.dp = new DatePicker()).setEditableYearVisible(false);
        this.dp.setCalendar_Modal(true);
        (this.dpmin = new DatePicker()).setEditableYearVisible(false);
        this.dpmin.setCalendar_Modal(true);
        (this.dpmax = new DatePicker()).setEditableYearVisible(false);
        this.dpmax.setCalendar_Modal(true);
        this.p.setLayout(new GridLayout(3, 3, 15, 15));
        this.p.add(new Label("Select Date : "));
        this.dp.setCalendarTitle("Select Date");
        this.p.add(this.dp);
        this.p.add(new Label("Select MinDate : "));
        this.dpmin.setCalendarTitle("Select MinDate");
        this.dpmin.setCalendarBackground(Color.orange);
        this.dpmin.setBackground(Color.orange);
        this.p.add(this.dpmin);
        this.p.add(new Label("Select MaxDate : "));
        this.dpmax.setCalendarTitle("Select MaxDate");
        this.dpmax.setBackground(Color.yellow);
        this.dpmax.setCalendarBackground(Color.yellow);
        this.p.add(this.dpmax);
        this.dp.addDatePickerListener(this);
        this.dpmin.addDatePickerListener(this);
        this.dpmax.addDatePickerListener(this);
    }
    
    public void dateChanged(final DatePicker datePicker) {
        this.flag = true;
        try {
            if (datePicker == this.dpmin) {
                this.dp.setMinDate(this.dpmin.getDate());
                this.dpmax.setMinDate(this.dp.getMinDate());
            }
            else if (datePicker == this.dpmax) {
                this.dp.setMaxDate(this.dpmax.getDate());
                this.dpmin.setMaxDate(this.dp.getMaxDate());
            }
        }
        catch (DatePickerException ex) {}
        this.firePropertyChange();
    }
    
    public Component getCustomEditor() {
        return this.p;
    }
    
    public String getJavaInitializationString() {
        return String.valueOf(new StringBuffer(String.valueOf(new StringBuffer().append(this.dp.getYear()).append(",").append(this.dp.getMonth()).append(",").append(this.dp.getDay()).toString())).append(",").append(this.dpmin.getYear()).append(",").append(this.dpmin.getMonth()).append(",").append(this.dpmin.getDay()).toString()) + "," + this.dpmax.getYear() + "," + this.dpmax.getMonth() + "," + this.dpmax.getDay();
    }
    
    public Object getValue() {
        return this.dp;
    }
    
    public boolean isPaintable() {
        return true;
    }
    
    public void paintValue(final Graphics graphics, final Rectangle rectangle) {
        graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.translate(rectangle.x, rectangle.y);
        graphics.drawString(this.dp.getFormattedDate(this.dp.getDate(), this.dp.getDateFormat()), 2, 14);
    }
    
    public void setValue(final Object o) {
        if (this.flag) {
            return;
        }
        this.dp.removeDatePickerListener(this);
        this.dpmin.removeDatePickerListener(this);
        this.dpmax.removeDatePickerListener(this);
        try {
            final DatePicker datePicker = (DatePicker)o;
            datePicker.addDatePickerListener(new DatePickerListener() {
                public void dateChanged(final DatePicker datePicker) {
                    try {
                        DateEditor.this.dp.setDate(datePicker.getDate());
                    }
                    catch (DatePickerException ex) {}
                }
            });
            this.dp.setDate(datePicker.getDate());
            this.dp.setMinDate(datePicker.getMinDate());
            this.dp.setMaxDate(datePicker.getMaxDate());
            this.dpmin.setMinDate(this.dp.getYear() - 200, 1, 1);
            this.dpmin.setDate(datePicker.getMinDate());
            this.dpmin.setMaxDate(datePicker.getMaxDate());
            this.dpmax.setDate(datePicker.getMaxDate());
            this.dpmax.setMinDate(datePicker.getMinDate());
            this.dpmax.setMaxDate(this.dp.getYear() + 200, 12, 31);
        }
        catch (DatePickerException ex) {}
        this.dp.addDatePickerListener(this);
        this.dpmin.addDatePickerListener(this);
        this.dpmax.addDatePickerListener(this);
    }
    
    public boolean supportsCustomEditor() {
        return true;
    }
}
