import java.awt.Dimension;
import java.awt.Container;
import java.awt.Event;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Component;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.LayoutManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MoonPhaseApplet extends Applet implements Runnable, LayoutManager, MyActionListener
{
    protected static final int APPLET_WIDTH = 620;
    protected static final int APPLET_HEIGHT = 350;
    protected static final double SPIN_STEP = 10.0;
    private Thread runner;
    protected MoonView theMoon;
    protected boolean imagesLoaded;
    protected MoonPhaseLookup lookup;
    protected MoonCalendar calendar;
    protected Choice monthMenu;
    protected TextField yearText;
    protected PictureButton leftArrow;
    protected PictureButton rightArrow;
    protected PhaseTimeView timeView;
    protected boolean spinning;
    protected double spinPhase;
    protected static final int MARGIN = 10;
    protected static final int CALENDAR_WIDTH = 324;
    protected static final int CALENDAR_HEIGHT = 300;
    protected static final int CALENDAR_LEFT = 10;
    protected static final int CALENDAR_TOP = 40;
    protected static final int MOON_WIDTH = 256;
    protected static final int MOON_HEIGHT = 256;
    protected static final int MOON_LEFT = 354;
    protected static final int MOON_TOP = 40;
    protected static final int TIME_WIDTH = 256;
    protected static final int TIME_HEIGHT = 30;
    protected static final int TIME_LEFT = 354;
    protected static final int TIME_TOP = 310;
    protected static final int CONTROL_TOP = 8;
    protected static final int MONTH_YEAR_GAP = 10;
    
    public MoonPhaseApplet() {
        this.runner = null;
        this.imagesLoaded = false;
        this.spinning = false;
        ImageCanvas.setAppletEnvironment(this);
    }
    
    public String getAppletInfo() {
        return "Name: MoonPhaseApplet\r\nAuthor: Kerry Shetline\r\nLast Update: 17 May 1999";
    }
    
    public void init() {
        this.setLayout(this);
        this.setBackground(new Color(26265));
        this.lookup = new MoonPhaseLookup(this);
        this.add(this.calendar = new MoonCalendar(this.lookup));
        final Date nowLocal = new Date();
        final Date nowGMT = new Date(nowLocal.getTime() + nowLocal.getTimezoneOffset() * 60000);
        (this.monthMenu = new Choice()).addItem("January");
        this.monthMenu.addItem("February");
        this.monthMenu.addItem("March");
        this.monthMenu.addItem("April");
        this.monthMenu.addItem("May");
        this.monthMenu.addItem("June");
        this.monthMenu.addItem("July");
        this.monthMenu.addItem("August");
        this.monthMenu.addItem("September");
        this.monthMenu.addItem("October");
        this.monthMenu.addItem("November");
        this.monthMenu.addItem("December");
        this.monthMenu.select(nowGMT.getMonth());
        this.add(this.monthMenu);
        (this.yearText = new TextField(4)).setBackground(Color.white);
        this.yearText.setText(String.valueOf(nowGMT.getYear() + 1900));
        this.add(this.yearText);
        this.add(this.leftArrow = new PictureButton("$/images/larrow_*.gif"));
        this.leftArrow.addMyActionListener(this);
        this.add(this.rightArrow = new PictureButton("$/images/rarrow_*.gif"));
        this.rightArrow.addMyActionListener(this);
        this.calendar.setMonthAndYear(nowGMT.getMonth() + 1, nowGMT.getYear() + 1900);
        this.calendar.setMarkedDate(nowGMT.getDate());
        this.calendar.refresh();
        this.add(this.theMoon = new MoonView());
        this.theMoon.initImage(this);
        this.add(this.timeView = new PhaseTimeView());
        this.timeView.setTimeString(nowGMT.toGMTString());
        this.timeView.refresh();
        this.resize(620, 350);
    }
    
    public void paint(final Graphics g) {
        final Color bg = this.getBackground();
        final int w = this.size().width;
        final int h = this.size().height;
        g.setColor(bg.darker().darker());
        g.drawLine(0, 0, 0, h - 1);
        g.drawLine(0, 0, w - 1, 0);
        g.drawLine(1, 1, 1, h - 3);
        g.drawLine(1, 1, w - 3, 1);
        g.setColor(bg.brighter().brighter());
        g.drawLine(w - 1, 0, w - 1, h - 1);
        g.drawLine(0, h - 1, w - 1, h - 1);
        g.drawLine(w - 2, 1, w - 2, h - 2);
        g.drawLine(1, h - 2, w - 2, h - 2);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void run() {
        int lastMonth = -1;
        int lastDate = -1;
        int lastYear = -1;
        int lastHour = -1;
        int lastMinute = -1;
        int lastSecond = -1;
        int calendarRefreshesPending = 0;
        while (true) {
            if (this.theMoon.isReady() && !this.imagesLoaded) {
                this.theMoon.refresh();
                this.imagesLoaded = true;
            }
            else if (this.theMoon.isReady()) {
                final Date nowLocal = new Date();
                final Date nowGMT = new Date(nowLocal.getTime() + nowLocal.getTimezoneOffset() * 60000);
                final int month = nowGMT.getMonth() + 1;
                final int date = nowGMT.getDate();
                final int year = nowGMT.getYear() + 1900;
                final int hour = nowGMT.getHours();
                final int minute = nowGMT.getMinutes();
                final int second = nowGMT.getSeconds();
                if (this.calendar.getMonth() == month && this.calendar.getYear() == year && this.calendar.getMarkedDate() != date) {
                    this.calendar.setMarkedDate(date);
                    this.calendar.refresh();
                }
                else if ((this.calendar.getMonth() != month || this.calendar.getYear() != year) && this.calendar.getMarkedDate() != 0) {
                    this.calendar.setMarkedDate(0);
                    this.calendar.refresh();
                }
                if (this.spinning) {
                    double phase = this.theMoon.getPhase() + 10.0;
                    if (phase >= 360.0) {
                        phase -= 360.0;
                    }
                    this.theMoon.setPhase(phase);
                    this.theMoon.refresh();
                    this.spinPhase += 10.0;
                    if (this.spinPhase >= 360.0) {
                        this.spinning = false;
                        lastMinute = -1;
                    }
                }
                else if (month != lastMonth || date != lastDate || year != lastYear || hour != lastHour || minute != lastMinute) {
                    this.theMoon.setPhase(this.lookup.momentaryPhase(month, date, year, hour, minute));
                    this.theMoon.refresh();
                }
                if (month != lastMonth || date != lastDate || year != lastYear || hour != lastHour || minute != lastMinute || lastSecond != second) {
                    this.timeView.setTimeString(nowLocal.toGMTString());
                    this.timeView.refresh();
                    lastMonth = month;
                    lastDate = date;
                    lastYear = year;
                    lastHour = hour;
                    lastMinute = minute;
                    lastSecond = second;
                }
            }
            if (calendarRefreshesPending > 0) {
                this.calendar.refresh();
                --calendarRefreshesPending;
            }
            final int newYear = to_int(this.yearText.getText());
            if (newYear != this.calendar.getYear() || this.monthMenu.getSelectedIndex() + 1 != this.calendar.getMonth()) {
                this.calendar.setMonthAndYear(this.monthMenu.getSelectedIndex() + 1, newYear);
                this.calendar.setMarkedDate(0);
                this.calendar.refresh();
                calendarRefreshesPending = 2;
            }
            try {
   