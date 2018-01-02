// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.gui;

import java.awt.event.ItemEvent;
import duc.cal.astro.RiseSet;
import duc.cal.LunarDate;
import duc.cal.CalUtil;
import duc.cal.LunarYear;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Choice;
import duc.cal.astro.Location;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class CalendarTool extends Applet implements ActionListener
{
    static final String ABOUT = "Lunar calendar tool v1.3. (c) 2002-2004 Ho Ngoc Duc\n";
    static final String LEAP_MONTHS_OFFICIAL = "Leap Months (Official)";
    static final String LEAP_MONTHS_ASTRO = "Leap Months (Astro)";
    static final String NEW_MOONS = "Show New Moons";
    static final String NEW_YEARS_OFFICIAL = "New Year's (Official)";
    static final String NEW_YEARS_ASTRO = "New Year's (Astro)";
    static final String SOLAR_TERMS = "Show Solar Terms";
    static final String RISE_SET = "Sun Rise/Transit/Set";
    static final Location[] LOCATIONS;
    static int[] MONTH_LENGTH;
    Choice action;
    Choice location;
    Choice monthChoice;
    TextArea ta;
    TextField tf;
    TextField tz;
    TextField kinh;
    TextField vi;
    Global calculator;
    
    static {
        LOCATIONS = new Location[] { Location.HANOI, Location.HUE, Location.TPHCM, Location.PEKING, Location.TOKYO, Location.SYDNEY, Location.PARIS, Location.LA, Location.HOUSTON, Location.CUSTOM };
        CalendarTool.MONTH_LENGTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    }
    
    public CalendarTool() {
        this.calculator = Global.getSingleton();
        final int yyyy = Calendar.getInstance().get(1);
        final int mm = Calendar.getInstance().get(2);
        (this.tf = new TextField("" + yyyy, 3)).addActionListener(this);
        this.monthChoice = new Choice();
        for (int i = 0; i < 12; ++i) {
            this.monthChoice.add("" + (i + 1));
        }
        this.monthChoice.select(mm);
        (this.action = new Choice()).add("Show Solar Terms");
        this.action.add("Show New Moons");
        this.action.add("New Year's (Official)");
        this.action.add("New Year's (Astro)");
        this.action.add("Leap Months (Official)");
        this.action.add("Leap Months (Astro)");
        this.action.add("Sun Rise/Transit/Set");
        this.location = new Choice();
        for (int i = 0; i < CalendarTool.LOCATIONS.length; ++i) {
            this.location.add(CalendarTool.LOCATIONS[i].name);
        }
        this.location.addItemListener(new LocationListener());
        final Button ok = new Button("Compute");
        ok.addActionListener(this);
        final Button clear = new Button("Clear");
        clear.addActionListener(new Clear());
        this.tz = new TextField("420", 2);
        this.kinh = new TextField("105.85", 4);
        this.vi = new TextField("21.30", 4);
        final Panel north = new Panel(new FlowLayout());
        north.setBackground(Color.lightGray);
        north.add(this.action);
        north.add(new Label("Year", 2));
        north.add(this.tf);
        north.add(new Label("Month", 2));
        north.add(this.monthChoice);
        north.add(ok);
        north.add(clear);
        final Panel south = new Panel(new FlowLayout());
        south.setBackground(Color.lightGray);
        south.add(this.location);
        south.add(new Label("Longitude", 2));
        south.add(this.kinh);
        south.add(new Label("Latitude", 2));
        south.add(this.vi);
        south.add(new Label("GMT+(min)", 2));
        south.add(this.tz);
        (this.ta = new TextArea("", 20, 80)).setFont(new Font("Courier", 0, 12));
        this.setLayout(new BorderLayout());
        this.add(north, "North");
        this.add(this.ta, "Center");
        this.add(south, "South");
    }
    
    public void actionPerformed(final ActionEvent e) {
        int year = Calendar.getInstance().get(1);
        try {
            year = Integer.parseInt(this.tf.getText());
            this.calculator.setTimeZone(Integer.parseInt(this.tz.getText()));
        }
        catch (Throwable t) {}
        final int month = this.monthChoice.getSelectedIndex() + 1;
        final String sel = this.action.getSelectedItem();
        final int pos = this.ta.getText().length();
        if ("New Year's (Official)".equalsIgnoreCase(sel)) {
            this.showNewYears(year, 100, false);
        }
        if ("New Year's (Astro)".equalsIgnoreCase(sel)) {
            this.showNewYears(year, 100, true);
        }
        else if ("Leap Months (Official)".equalsIgnoreCase(sel)) {
            this.showLeapMonths(year, 100, false);
        }
        else if ("Leap Months (Astro)".equalsIgnoreCase(sel)) {
            this.showLeapMonths(year, 100, true);
        }
        else if ("Show Solar Terms".equalsIgnoreCase(sel)) {
            this.showMinorTerms(year);
        }
        else if ("Show New Moons".equalsIgnoreCase(sel)) {
            this.showNewMoons(year);
        }
        else if ("Sun Rise/Transit/Set".equalsIgnoreCase(sel)) {
            this.showRiseSet(year, month);
        }
        this.ta.setCaretPosition(pos);
    }
    
    private void clear() {
        this.ta.setText("");
    }
    
    public String getAppletInfo() {
        return "Lunar calendar tool v1.3. (c) 2002-2004 Ho Ngoc Duc\n";
    }
    
    LunarYear getLunarYear(final int yy, final boolean astro) {
        if (astro) {
            return this.calculator.getConverterAstro().getLunarYear(yy);
        }
        return this.calculator.getConverterVN().getLunarYear(yy);
    }
    
    String showLeapMonths(final int start, final int count, final boolean astro) {
        this.ta.append("Leap months between " + start + " and " + (start + 99));
        if (astro) {
            this.ta.append("\n(Astronomical lunar calendar for specified location) ");
        }
        else {
            this.ta.append("\n(Official lunar calendar used in Vietnam) ");
        }
        this.ta.append("\n\n");
        final StringBuffer sb0 = new StringBuffer();
        for (int i = 0; i < count; ++i) {
            final int yy = start + i;
            final LunarYear ly = this.getLunarYear(yy, astro);
            final LunarDate[] arr = ly.getMonths();
            LunarDate ld = null;
            int monthEnd = ly.getJdNextLunarNY() - 1;
            for (int j = 0; j < arr.length; ++j) {
                if (arr[j].isLeap()) {
                    ld = arr[j];
                    monthEnd = ((j < arr.length - 1) ? (arr[j + 1].getJd() - 1) : monthEnd);
                }
            }
            if (ld != null) {
                final StringBuffer sb2 = new StringBuffer();
                sb2.append(start + i);
                sb2.append(": tha'ng ").append(ld.getMonth());
                sb2.append(" nhua^.n (");
                int[] a = CalUtil.jdToDate(ld.getJd());
                CalUtil.printDate(a[0], a[1], a[2], sb2, true);
                sb2.append(" - ");
                a = CalUtil.jdToDate(monthEnd);
                CalUtil.printDate(a[0], a[1], a[2], sb2, true);
                sb2.append(")\n");
                final String ret = sb2.toString();
                this.ta.append(ret);
                sb0.append(ret);
            }
        }
        this.ta.append("\n\n");
        return sb0.toString();
    }
    
    String showMinorTerms(final int year) {
        final double[] arr = this.calculator.getSolarTerms(year);
        final StringBuffer sb = new StringBuffer();
        sb.append("Solar terms in " + year + ":\n\n");
        for (int i = 0; i < arr.length; ++i) {
            final boolean roundToMin = false;
            final int day = (int)Math.floor(arr[i] + 0.5);
            final int time = (int)Math.floor(86400.0 * (arr[i] + 0.5 - day));
            final int[] dd = CalUtil.jdToDate(day);
            final int[] tt = CalUtil.parseTime(time, roundToMin);
            final String name = MonthModel.TIETKHI[i % 24];
            sb.append(name);
            for (int j = name.length(); j < 14; ++j) {
                sb.append(" ");
            }
            CalUtil.printTime(tt[0], tt[1], tt[2], sb, !roundToMin);
            sb.append(" GMT ");
            CalUtil.printDate(dd[0], dd[1], dd[2], sb, !roundToMin);
            sb.append("    ").append(arr[i]);
            sb.append('\n');
        }
        sb.append("\n\n");
        final String ret = sb.toString();
        this.ta.append(ret);
        return ret;
    }
    
    String showNewMoons(final int year) {
        final double[] arr = this.calculator.getNewMoons(year);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            final int day = (int)Math.floor(arr[i] + 0.5);
            final int time = (int)Math.floor(86400.0 * (arr[i] + 0.5 - day));
            final int[] dd = CalUtil.jdToDate(day);
            final int[] tt = CalUtil.parseTime(time, false);
            CalUtil.printTime(tt[0], tt[1], tt[2], sb, true);
            sb.append(" GMT ");
            CalUtil.printDate(dd[0], dd[1], dd[2], sb, true);
            sb.append(" (JDN: ").append(arr[i]).append(")");
            sb.append('\n');
        }
        sb.append("\n\n");
        final String ret = sb.toString();
        this.ta.append("New Moons in " + year + ":\n\n");
        this.ta.append(ret);
        return ret;
    }
    
    String showNewYears(final int start, final int count, final boolean astro) {
        this.ta.append("New Year's days between " + start + " and " + (start + 99));
        if (astro) {
            this.ta.append("\n(Astronomical lunar calendar for specified location)");
        }
        else {
            this.ta.append("\n(Official lunar calendar used in Vietnam)");
        }
        this.ta.append("\n\n");
        final StringBuffer sb0 = new StringBuffer();
        for (int i = 0; i < count; ++i) {
            final LunarYear ly = this.getLunarYear(start + i, astro);
            final StringBuffer sb2 = new StringBuffer();
            sb2.append(start + i).append(": ");
            final int[] a = CalUtil.jdToDate(ly.getMonths()[0].getJd());
            CalUtil.printDate(a[0], a[1], a[2], sb2, false);
            if (i % 5 == 4) {
                sb2.append('\n');
            }
            else {
                sb2.append("  ");
            }
            final String ret = sb2.toString();
            this.ta.append(ret);
            sb0.append(ret);
        }
        this.ta.append("\n\n");
        return sb0.toString();
    }
    
    String showRiseSet(final int year, final int month) {
        final StringBuffer sb = new StringBuffer();
        double longitude = 0.0;
        double latitude = 0.0;
        double zone = 0.0;
        try {
            longitude = Double.valueOf(this.kinh.getText());
            latitude = Double.valueOf(this.vi.getText());
            zone = Double.valueOf(this.tz.getText()) / 60.0;
        }
        catch (Throwable e) {
            final String msg = "Longitude, latitude or time zone in wrong format!";
            this.ta.append(msg);
        }
        int len = CalendarTool.MONTH_LENGTH[month - 1];
        if (month == 2 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
            len = 29;
        }
        String where = this.location.getSelectedItem();
        if (where.equals(Location.CUSTOM.name)) {
            where = "specified location";
        }
        final Location ort = new Location(where, latitude, longitude, 0.0, zone);
        final String head = "Time of sunrise, transit and sunset at " + where + " for " + month + "/" + year + "\n(Add Daylight saving time if applicable!)\n\n";
        sb.append(head);
        final int fixed = CalUtil.jdFromDate(1, month, year) - 1721425;
        for (int i = 0; i < len; ++i) {
            final int date = fixed + i;
            final double rise = RiseSet.sunrise(date, ort);
            final double set = RiseSet.sunset(date, ort);
            final double transit = (rise + set) / 2.0;
            if (i < 9) {
                sb.append(" ");
            }
            sb.append(i + 1).append("/").append(month).append("\t");
            final boolean roundToMin = true;
            final int time = 60 * (int)Math.round(1440.0 * (rise - Math.floor(rise)));
            int[] tt = CalUtil.parseTime(time, roundToMin);
            CalUtil.printTime(tt[0], tt[1], tt[2], sb, !roundToMin);
            sb.append("\t");
            tt = CalUtil.parseTime((int)Math.round(86400.0 * (transit - Math.floor(transit))), roundToMin);
            CalUtil.printTime(tt[0], tt[1], tt[2], sb, !roundToMin);
            sb.append("\t");
            tt = CalUtil.parseTime((int)Math.round(86400.0 * (set - Math.floor(set))), roundToMin);
            CalUtil.printTime(tt[0], tt[1], tt[2], sb, !roundToMin);
            sb.append("\n");
        }
        sb.append("\n");
        this.ta.append(sb.toString());
        return sb.toString();
    }
    
    class Clear implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            CalendarTool.this.clear();
        }
    }
    
    class LocationListener implements ItemListener
    {
        public void itemStateChanged(final ItemEvent e) {
            final int i = CalendarTool.this.location.getSelectedIndex();
            final Location loc = CalendarTool.LOCATIONS[i];
            CalendarTool.this.kinh.setText("" + loc.longitude);
            CalendarTool.this.vi.setText("" + loc.latitude);
            CalendarTool.this.tz.setText("" + (int)(loc.zone * 60.0));
        }
    }
}
