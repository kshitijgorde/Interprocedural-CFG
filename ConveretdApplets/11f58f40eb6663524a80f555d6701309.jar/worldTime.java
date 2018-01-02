import java.awt.Graphics;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.awt.Component;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;
import java.awt.Color;
import java.util.Locale;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class worldTime extends Applet implements Runnable
{
    Locale currentLocale;
    Color backColor;
    Color cityColor;
    Color timeColor;
    Color dateColor;
    Color dstColor;
    Color dstBackColor;
    SimpleTimeZone[] cityTimeZone;
    Calendar[] calendar;
    DateFormat[] timeFormat;
    DateFormat[] dateFormat;
    Date runTime;
    Label[] lblTime;
    Label[] lblDate;
    int numCities;
    Thread timer;
    
    public void init() {
        this.numCities = this.getNumCities();
        this.cityTimeZone = new SimpleTimeZone[this.numCities];
        this.calendar = new Calendar[this.numCities];
        this.timeFormat = new DateFormat[this.numCities];
        this.dateFormat = new DateFormat[this.numCities];
        this.lblTime = new Label[this.numCities];
        this.lblDate = new Label[this.numCities];
        this.runTime = new Date();
        this.setLocale();
        this.setColors();
        this.buildGUI();
    }
    
    private int getNumCities() {
        return Integer.decode(this.getParameter("numCities"));
    }
    
    private void setLocale() {
        try {
            this.currentLocale = new Locale(this.getParameter("Language"), this.getParameter("Country"));
        }
        catch (Exception e) {
            System.out.println("No Locale Provided.  Defaulted to English/US");
            this.currentLocale = new Locale("en", "US");
        }
    }
    
    private void setColors() {
        try {
            this.backColor = new Color(Integer.decode("0x" + this.getParameter("backgroundColor")));
        }
        catch (Exception e) {
            this.backColor = new Color(16777215);
            System.out.println("Problem setting backgroundColor - defaulted to white");
        }
        try {
            this.cityColor = new Color(Integer.decode("0x" + this.getParameter("cityColor")));
        }
        catch (Exception e) {
            this.cityColor = new Color(0);
            System.out.println("Problem setting cityColor - defaulted to black");
        }
        try {
            this.timeColor = new Color(Integer.decode("0x" + this.getParameter("timeColor")));
        }
        catch (Exception e) {
            this.timeColor = new Color(0);
            System.out.println("Problem setting timeColor - defaulted to black");
        }
        try {
            this.dateColor = new Color(Integer.decode("0x" + this.getParameter("dayColor")));
        }
        catch (Exception e) {
            this.dateColor = new Color(0);
            System.out.println("Problem setting dayColor - defaulted to black");
        }
        try {
            this.dstColor = new Color(Integer.decode("0x" + this.getParameter("dstColor")));
        }
        catch (Exception e) {
            this.dstColor = new Color(0);
            System.out.println("Problem setting dstColor - defaulted to black");
        }
        try {
            this.dstBackColor = new Color(Integer.decode("0x" + this.getParameter("dstBackColor")));
        }
        catch (Exception e) {
            this.dstBackColor = new Color(16777215);
            System.out.println("Problem setting dstBackColor - defaulted to white");
        }
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        final Panel p = new Panel(new GridLayout(3, this.numCities));
        this.setBackground(this.backColor);
        final Label[] lblCity = new Label[this.numCities];
        for (int inx = 0; inx < this.numCities; ++inx) {
            (lblCity[inx] = new Label(this.getCity(inx))).setForeground(this.cityColor);
            p.add(lblCity[inx]);
        }
        this.buildCalendar();
        for (int inx2 = 0; inx2 < this.numCities; ++inx2) {
            String timeLabel = this.timeFormat[inx2].format(this.runTime);
            if (this.cityTimeZone[inx2].inDaylightTime(this.runTime)) {
                timeLabel += " *";
            }
            (this.lblTime[inx2] = new Label(timeLabel)).setForeground(this.timeColor);
            p.add(this.lblTime[inx2]);
        }
        for (int inx3 = 0; inx3 < this.numCities; ++inx3) {
            (this.lblDate[inx3] = new Label(this.dateFormat[inx3].format(this.runTime))).setForeground(this.dateColor);
            p.add(this.lblDate[inx3]);
        }
        this.add(p, "Center");
        final String dstString = this.getParameter("dstLabel");
        if (dstString != null) {
            final Panel pnlDst = new Panel();
            pnlDst.setBackground(this.dstBackColor);
            final Label dstLabel = new Label(dstString);
            dstLabel.setForeground(this.dstColor);
            pnlDst.add(dstLabel);
            this.add(pnlDst, "South");
        }
    }
    
    private void buildCalendar() {
        this.runTime = new Date();
        for (int inx = 0; inx < this.numCities; ++inx) {
            this.cityTimeZone[inx] = this.buildZone(inx);
            (this.calendar[inx] = new GregorianCalendar(this.cityTimeZone[inx], this.currentLocale)).setTime(this.runTime);
            (this.timeFormat[inx] = this.setTimeFormat()).setCalendar(this.calendar[inx]);
            (this.dateFormat[inx] = this.setDateFormat()).setCalendar(this.calendar[inx]);
        }
    }
    
    private DateFormat setTimeFormat() {
        DateFormat returnFormat;
        try {
            returnFormat = DateFormat.getTimeInstance(Integer.decode(this.getParameter("timeFormat")), this.currentLocale);
        }
        catch (Exception e) {
            System.out.println("Invalid Time Format.  Defaulted to Style 2.");
            returnFormat = DateFormat.getTimeInstance(2, this.currentLocale);
        }
        return returnFormat;
    }
    
    private DateFormat setDateFormat() {
        DateFormat returnFormat;
        try {
            returnFormat = DateFormat.getDateInstance(Integer.decode(this.getParameter("dateFormat")), this.currentLocale);
        }
        catch (Exception e) {
            System.out.println("Invalid Date Format.  Defaulted to Style 2.");
            returnFormat = DateFormat.getDateInstance(2, this.currentLocale);
        }
        return returnFormat;
    }
    
    private void updateTimeLabels() {
        for (int inx = 0; inx < this.numCities; ++inx) {
            String timeLabel = this.timeFormat[inx].format(this.runTime);
            if (this.cityTimeZone[inx].inDaylightTime(this.runTime)) {
                timeLabel += " *";
            }
            this.lblTime[inx].setText(timeLabel);
        }
    }
    
    private void updateDateLabels() {
        for (int inx = 0; inx < this.numCities; ++inx) {
            this.lblDate[inx].setText(this.dateFormat[inx].format(this.runTime));
        }
    }
    
    private String getCity(final int city) {
        return (this.getParameter("city" + (city + 1)) == null) ? "Unknown" : this.getParameter("city" + (city + 1));
    }
    
    private SimpleTimeZone buildZone(final int city) {
        int offset = 0;
        final String id = "" + TimeZone.getAvailableIDs().length + city;
        try {
            offset = (int)(new Double(this.getParameter("offset" + (city + 1))) * 60.0 * 60.0 * 1000.0);
        }
        catch (Exception e) {
            offset = 0;
            System.out.println("Problem setting Offset - defaulted to 0");
        }
        final SimpleTimeZone returnZone = new SimpleTimeZone(offset, id);
        if ("true".equals(this.getParameter("dst" + (city + 1)))) {
            try {
                returnZone.setStartRule(Integer.decode(this.getParameter("dstStartMonth" + (city + 1))), Integer.decode(this.getParameter("dstStartWeek" + (city + 1))), Integer.decode(this.getParameter("dstStartDay" + (city + 1))), 0);
                returnZone.setEndRule(Integer.decode(this.getParameter("dstEndMonth" + (city + 1))), Integer.decode(this.getParameter("dstEndWeek" + (city + 1))), Integer.decode(this.getParameter("dstEndDay" + (city + 1))), 0);
            }
            catch (Exception e2) {
                System.out.println("Invalid DST Rules provided");
            }
        }
        return returnZone;
    }
    
    public void start() {
        (this.timer = new Thread(this)).start();
    }
    
    public void run() {
        final Thread me = Thread.currentThread();
        while (this.timer == me) {
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void paint(final Graphics g) {
        this.buildCalendar();
        this.updateTimeLabels();
        this.updateDateLabels();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void stop() {
        this.timer = null;
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "Title: worldTime Applet\nAuthor: Larry Bullock\nAn applet to put a world time clock on your website";
    }
    
    public String[][] getParameterInfo() {
        final String[][] paramInfo = { { "numCities", "number", "Number of cities to display" }, { "Language", "2 character code", "ISO 2 character language code as specified at http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt" }, { "Country", "2 character code", "ISO 2 character country code as specified at http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html" }, { "backColor", "hex value (no # sign)", "Hex value for Background Color" }, { "cityColor", "hex value (no # sign)", "Hex value for City Label Color" }, { "timeColor", "hex value (no # sign)", "Hex value for Time Label Color" }, { "dayColor", "hex value (no # sign)", "Hex value for Day Label Color" }, { "dstLabel", "text", "What you want displayed for a DST Message" }, { "dstColor", "hex value (no # sign)", "Hex value for Daylight Savings Time description Label Color" }, { "dstBackColor", "hex value (no # sign)", "Hex value for Daylight Savings Time description area background Color" }, { "city", "text", "Text label for city" }, { "offset", "decimal value", "Time offset from GMT" }, { "dst", "true/false", "Daylight Savings Rules provided?" }, { "dstStartMonth", "0-11", "number 0-11 which says what month DST begins (0=January, etc.)" }, { "dstStartDay", "number", "number 1-7 which describes which day of the week DST starts (1=Sunday, etc.)" }, { "dstStartWeek", "number", "number representing ordinal position of Start Day (1=first, 2=second, -1=last" }, { "dstEndMonth", "0-11", "number 0-11 which says what month DST ends (0=January, etc.)" }, { "dstEndDay", "number", "number 1-7 which describes which day of the week DST ends (1=Sunday, etc.)" }, { "dstEndWeek", "number", "number representing ordinal position of End Day (1=first, 2=second, -1=last, etc.)" }, { "dateFormat", "number", "number representing stye (0=FULL, 1=LONG, 2=MEDIUM, 3=SHORT)" }, { "timeFormat", "number", "number representing stye (0=FULL, 1=LONG, 2=MEDIUM, 3=SHORT)" } };
        return paramInfo;
    }
}
