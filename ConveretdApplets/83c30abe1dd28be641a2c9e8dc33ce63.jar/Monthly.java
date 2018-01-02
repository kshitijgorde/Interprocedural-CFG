import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.Locale;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Monthly extends Panel
{
    private final String[] days;
    private final String[] months;
    private int x;
    private int y;
    private GregorianCalendar calendar;
    private String entry;
    private Date dina;
    
    public Monthly(final Dimension dimension) {
        this.days = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        this.months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        this.x = 0;
        this.y = 0;
        this.calendar = null;
        this.entry = null;
        this.dina = null;
        this.setSize(new Dimension(dimension.width, dimension.height - 60));
    }
    
    public void setEntry(final String entry) {
        this.entry = entry;
    }
    
    public Point nextPoint(final int n, final int n2) {
        final int n3 = this.getSize().width / 8;
        int n4 = n2;
        int n5 = n + n3;
        if (n5 > 7 * n3) {
            n5 = n3 / 2;
            n4 += 35;
        }
        return new Point(n5, n4);
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.getSize().width / 8;
        try {
            if (this.dina == null) {
                this.dina = new Date();
                (this.calendar = new GregorianCalendar()).setTime(this.dina);
                this.entry = "01/" + (this.calendar.get(2) + 1) + "/" + this.calendar.get(1);
            }
            this.dina = DateFormat.getDateInstance(3, Locale.FRANCE).parse(this.entry);
            (this.calendar = new GregorianCalendar()).setTime(this.dina);
        }
        catch (ParseException ex) {
            System.out.println(ex);
        }
        final int value = this.calendar.get(2);
        final int value2 = this.calendar.get(1);
        final int value3 = this.calendar.get(7);
        System.out.println("MONTH " + value);
        System.out.println("YEAR " + value2);
        System.out.println("WEEK DAY " + value3);
        final String string = "Calendar for " + this.months[value] + " " + value2;
        final Font font = new Font("Serif", 2, 22);
        graphics.setFont(font);
        graphics.setColor(Color.blue);
        graphics.drawString(string, (this.getSize().width - this.getFontMetrics(font).stringWidth(string)) / 2, 25);
        graphics.setFont(new Font("Serif", 1, 18));
        graphics.setColor(Color.green);
        for (int i = 0, n2 = n / 2; i < 7; ++i, n2 += n) {
            graphics.drawString(this.days[i], n2, 65);
        }
        int n3 = 0;
        switch (value) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11: {
                n3 = 31;
                break;
            }
            case 1: {
                if (this.calendar.isLeapYear(value2) && value2 % 1000 != 0) {
                    n3 = 29;
                    break;
                }
                n3 = 28;
                break;
            }
            default: {
                n3 = 30;
                break;
            }
        }
        final Point[] array = new Point[n3];
        graphics.setFont(new Font("Roman", 1, 20));
        graphics.setColor(Color.black);
        array[0] = new Point(n / 2 + (value3 - 1) * n, 100);
        graphics.drawString("1", array[0].x, array[0].y);
        for (int j = 0; j < array.length - 1; ++j) {
            array[j + 1] = this.nextPoint(array[j].x, array[j].y);
            graphics.drawString("" + (j + 2), array[j + 1].x, array[j + 1].y);
        }
    }
}
