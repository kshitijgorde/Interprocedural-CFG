import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class MoonCalendar extends BufferedCanvas
{
    protected static final int EXTRA_ASCENT = 2;
    protected static final int H_INSET = 4;
    protected static final int V_INSET = 4;
    protected static final int MIN_FONT_ASCENT = 10;
    protected MoonPhaseLookup lookup;
    protected int month;
    protected int year;
    protected int firstDay;
    protected int days;
    protected int numWeeks;
    protected int prevDays;
    protected int markedDate;
    protected Font smallFont;
    protected Font largeFont;
    protected Font headerFont;
    protected Font timeFont;
    protected Color moonColor;
    protected String[] dayNames;
    
    public MoonCalendar(final MoonPhaseLookup lookup) {
        this.month = 0;
        this.year = 0;
        this.firstDay = -1;
        this.markedDate = 0;
        this.smallFont = new Font(FontNameConvertor.serif(), 0, 12);
        this.largeFont = new Font(FontNameConvertor.serif(), 1, 18);
        this.headerFont = new Font(FontNameConvertor.serif(), 1, 14);
        this.timeFont = new Font(FontNameConvertor.monospaced(), 0, 10);
        this.moonColor = new Color(16777088);
        this.dayNames = new String[] { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
        this.lookup = lookup;
    }
    
    public void setMonthAndYear(final int month, final int year) {
        this.month = month;
        this.year = year;
        this.firstDay = (CalendarUtil.julianDate(month, 1, year) + 1) % 7;
        this.days = CalendarUtil.daysInMonth(month, year);
        this.numWeeks = (this.firstDay + this.days + 6) / 7;
        if (month > 1) {
            this.prevDays = CalendarUtil.daysInMonth(month - 1, year);
        }
        else {
            this.prevDays = 31;
        }
    }
    
    public int getMonth() {
        return this.month;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public void setMarkedDate(final int date) {
        this.markedDate = date;
    }
    
    public int getMarkedDate() {
        return this.markedDate;
    }
    
    public void draw(final Graphics g) {
        final int width = this.size().width;
        final int height = this.size().height;
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        if (this.month == 0 || this.year < 1583) {
            return;
        }
        g.setColor(Color.cyan);
        g.setFont(this.headerFont);
        final int headerLine = g.getFontMetrics().getHeight() + 2;
        final int colWidth = width / 7;
        final int rowHeight = (height - headerLine) / this.numWeeks;
        final int moonDiam = colWidth * 2 / 5;
        final int[] time = new int[2];
        for (int j = 0; j < 7; ++j) {
            g.drawString(this.dayNames[j], j * colWidth + colWidth / 2 - g.getFontMetrics().stringWidth(this.dayNames[j]) / 2, g.getFontMetrics().getAscent() + 2);
        }
        int date = -this.firstDay + 1;
        for (int j = 0; j < this.numWeeks; ++j) {
            final int v = j * rowHeight + headerLine;
            for (int k = 0; k < 7; ++k) {
                final int h = k * colWidth;
                Integer n;
                Font f;
                if (date < 1) {
                    n = new Integer(this.prevDays + date);
                    f = this.smallFont;
                }
                else if (date <= this.days) {
                    final int phase;
                    if (this.lookup != null && (phase = this.lookup.enumeratedPhase(this.month, date, this.year, time)) >= 0) {
                        final int h2 = h + colWidth - 4 - moonDiam;
                        final int v2 = v + rowHeight / 2 - moonDiam / 2;
                        g.setColor(Color.gray);
                        g.fillOval(h2, v2, moonDiam, moonDiam);
                        g.setColor(this.moonColor);
                        switch (phase) {
                            case 1: {
                                g.fillArc(h2, v2, moonDiam, moonDiam, 90, -180);
                                break;
                            }
                            case 2: {
                                g.fillOval(h2, v2, moonDiam, moonDiam);
                                break;
                            }
                            case 3: {
                                g.fillArc(h2, v2, moonDiam, moonDiam, 90, 180);
                                break;
                            }
                        }
                        g.setColor(Color.magenta);
                        g.setFont(this.timeFont);
                        if (g.getFontMetrics().getAscent() < 10) {
                            g.setFont(this.timeFont = new Font(this.timeFont.getName(), this.timeFont.getStyle(), 12));
                        }
                        String timeStr;
                        if (time[0] < 10) {
                            timeStr = "0";
                        }
                        else {
                            timeStr = "";
                        }
                        timeStr = String.valueOf(timeStr) + time[0] + ":";
                        if (time[1] < 10) {
                            timeStr = String.valueOf(timeStr) + "0";
                        }
                        timeStr = String.valueOf(timeStr) + time[1];
                        g.drawString(timeStr, h + 4, v + rowHeight - 4);
                    }
                    n = new Integer(date);
                    f = this.largeFont;
                }
                else {
                    n = new Integer(date - this.days);
                    f = this.smallFont;
                }
                if (this.markedDate != 0 && this.markedDate == date) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.blue);
                }
                g.setFont(f);
                g.drawString(n.toString(), h + 4, v + g.getFontMetrics().getAscent() + 2);
                ++date;
            }
        }
        g.setColor(Color.green);
        for (int j = 1; j <= 6; ++j) {
            g.drawLine(j * colWidth, 0, j * colWidth, height);
        }
        for (int j = 0; j < this.numWeeks; ++j) {
            g.drawLine(0, headerLine + j * rowHeight, width, headerLine + j * rowHeight);
        }
    }
}
