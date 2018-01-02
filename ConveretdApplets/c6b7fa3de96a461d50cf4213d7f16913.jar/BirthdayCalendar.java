import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BirthdayCalendar extends Applet
{
    String[] navn;
    int maanedVIS;
    Date[] data;
    Image offimage;
    Image flag;
    Graphics off;
    Choice valg;
    Choice navnVALG;
    int[] maanedL;
    int width;
    int height;
    int dataCount;
    String strChoose;
    String strChooseUk;
    String strYear;
    String strYearUk;
    String strFont;
    String[] arrMonths;
    String[] arrDays;
    String[] arrMonthsUk;
    String[] arrDaysUk;
    
    public BirthdayCalendar() {
        this.valg = new Choice();
        this.navnVALG = new Choice();
        this.maanedL = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.dataCount = 0;
        this.strChoose = "- V\u00e6lg -";
        this.strChooseUk = "- Choose -";
        this.strYear = "\u00e5r";
        this.strYearUk = "years";
        this.strFont = "Courier";
        this.arrMonths = new String[] { "Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "Oktober", "November", "December" };
        this.arrDays = new String[] { "S\u00f8", "Ma", "Ti", "On", "To", "Fr", "L\u00f8" };
        this.arrMonthsUk = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        this.arrDaysUk = new String[] { "Sun", "Mon", "Tue", "Web", "Thu", "Fri", "Sat" };
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void init() {
        this.width = this.size().width;
        this.height = this.size().height;
        for (int n = 0; this.getParameter(n + "") != null; ++n) {
            ++this.dataCount;
        }
        this.navn = new String[this.dataCount];
        this.data = new Date[this.dataCount];
        if (this.getParameter("font") != null) {
            this.strFont = this.getParameter("font");
        }
        if (this.getParameter("language").equals("uk")) {
            this.arrMonths = this.arrMonthsUk;
            this.arrDays = this.arrDaysUk;
            this.strYear = this.strYearUk;
            this.strChoose = this.strChooseUk;
        }
        for (int i = 0; i < this.dataCount; ++i) {
            final String parameter = this.getParameter(i + "");
            if (parameter == null) {
                break;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
            this.navn[i] = stringTokenizer.nextToken();
            final String nextToken = stringTokenizer.nextToken();
            this.data[i] = new Date(Integer.parseInt(nextToken.substring(4, 8)) - 1900, Integer.parseInt(nextToken.substring(2, 4)) - 1, Integer.parseInt(nextToken.substring(0, 2)));
        }
        this.navnVALG.addItem(this.strChoose);
        for (int j = 0; j < this.dataCount; ++j) {
            this.navnVALG.addItem(this.navn[j]);
        }
        this.add(this.navnVALG);
        this.navnVALG.reshape(100, 5, 200, 30);
        for (int k = 0; k < 12; ++k) {
            this.valg.addItem(this.arrMonths[k]);
        }
        this.add(this.valg);
        this.valg.reshape(302, 5, 85, 30);
        this.setLayout(null);
        this.maanedVIS = new Date().getMonth() + 1;
        this.valg.select(this.maanedVIS - 1);
        this.offimage = this.createImage(400, 800);
        this.off = this.offimage.getGraphics();
        this.flag = this.getImage(this.getCodeBase(), "flag.gif");
    }
    
    public void paint(final Graphics graphics) {
        this.off.setColor(new Color(191, 191, 191));
        this.off.fillRect(0, 0, 400, 800);
        this.off.setColor(new Color(0, 0, 0));
        final Date date = new Date(new Date().getYear(), this.maanedVIS - 1, 1);
        this.off.setFont(new Font(this.strFont, 0, 22));
        this.off.drawString(this.arrMonths[this.maanedVIS - 1], 68, 60);
        this.off.setFont(new Font(this.strFont, 0, 12));
        final int n = 20;
        final int n3;
        final int n2 = n3 = 70;
        this.off.drawLine(0, n3, this.width, n3);
        int n4 = n3 + n;
        for (int i = 1; i <= 32; ++i) {
            String s = "";
            int n5 = 0;
            final String string = this.arrDays[date.getDay()] + "-" + date.getDate();
            this.off.setColor(new Color(200, 130, 110));
            if (date.getDay() == 0) {
                this.off.fillRect(11, n4, this.width, 20);
            }
            if (date.getDay() == 6) {
                this.off.fillRect(11, n4, 52, 20);
            }
            this.off.setColor(new Color(0, 0, 0));
            this.off.drawString(string, 16, n4 - 4);
            for (int j = 0; j < this.dataCount; ++j) {
                if (date.getDate() == this.data[j].getDate() && date.getMonth() == this.data[j].getMonth() && n5 > 0) {
                    n4 += n - 4;
                    this.off.setColor(new Color(200, 130, 110));
                    if (date.getDay() == 0) {
                        this.off.fillRect(11, n4, this.width, 20);
                    }
                    if (date.getDay() == 6) {
                        this.off.fillRect(11, n4, 52, 20);
                    }
                    this.off.setColor(new Color(0, 0, 0));
                    this.off.drawString(s + this.navn[j] + ", " + (date.getYear() - this.data[j].getYear()) + " " + this.strYear, 68, n4 - 4);
                    s = "";
                }
                if (date.getDate() == this.data[j].getDate() && date.getMonth() == this.data[j].getMonth() && n5 < 1) {
                    final String string2 = s + this.navn[j] + ", " + (date.getYear() - this.data[j].getYear()) + " " + this.strYear;
                    n5 = 1;
                    this.off.drawString(string2, 68, n4 - 4);
                    s = "";
                }
            }
            this.off.drawLine(0, n4, 400, n4);
            date.setDate(date.getDate() + 1);
            if (date.getMonth() != this.maanedVIS - 1) {
                break;
            }
            n4 += n;
        }
        this.off.drawLine(10, n2, 10, this.height);
        this.off.drawLine(63, n2, 63, this.height);
        graphics.drawImage(this.offimage, 0, 0, this);
    }
    
    public boolean action(final Event event, final Object o) {
        System.out.println("Event detected:" + event);
        System.out.println("arg:" + o);
        if (event.target.equals(this.valg)) {
            this.maanedVIS = this.valg.getSelectedIndex() + 1;
        }
        if (event.target.equals(this.navnVALG)) {
            this.maanedVIS = this.data[this.navnVALG.getSelectedIndex()].getMonth() + 1;
        }
        this.repaint(0L);
        return true;
    }
}
