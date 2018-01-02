import java.awt.Graphics;
import DatePickerBean.DatePickerException;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Date;
import DatePickerBean.DatePicker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZCalendar extends Applet
{
    DatePicker dp;
    Date date;
    String but_color;
    String ar_color;
    String t_color;
    String text;
    StringTokenizer st;
    Color color_button;
    Color color_arrow;
    Color color_text;
    
    public void init() {
        this.but_color = this.getParameter("button_color");
        this.ar_color = this.getParameter("arrow_color");
        this.t_color = this.getParameter("text_color");
        this.text = this.getParameter("text");
        this.st = new StringTokenizer(this.ar_color, ",");
        this.color_arrow = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.st = new StringTokenizer(this.but_color, ",");
        this.color_button = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.st = new StringTokenizer(this.t_color, ",");
        this.color_text = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.date = new Date();
        (this.dp = new DatePicker()).setCalendarTitle("Unregistered.  Register: pgelev@abv.bg");
        this.dp.setForeground(this.color_text);
        this.dp.setLabel(this.text);
        this.setLayout(new BorderLayout());
        this.add(this.dp, "North");
        this.dp.setArrowColor(this.color_arrow);
        this.dp.setBackground(this.color_button);
        try {
            this.dp.setMinDate(1900, 1, 1);
            this.dp.setMaxDate(2100, 12, 30);
        }
        catch (DatePickerException ex) {}
    }
    
    public void paint(final Graphics graphics) {
    }
}
