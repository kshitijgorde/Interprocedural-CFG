// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import java.awt.event.ItemEvent;
import com.mindprod.common13.HybridJ;
import java.util.Comparator;
import java.util.Arrays;
import com.mindprod.common11.ST;
import java.awt.GridBagConstraints;
import java.io.InputStream;
import java.io.IOException;
import com.mindprod.common11.Misc;
import com.mindprod.common11.BigDate;
import com.mindprod.fastcat.FastCat;
import java.awt.Component;
import com.mindprod.common15.FontFactory;
import java.awt.Insets;
import com.mindprod.common11.CMPAboutBox;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import com.mindprod.common13.JEButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class Holidays extends JApplet
{
    private static final int APPLET_HEIGHT = 480;
    private static final int APPLET_WIDTH = 720;
    private static final int defaultYear;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-02-16";
    private static final String TITLE_STRING = "CMP Holiday Calculator";
    private static final String VERSION_STRING = "4.7";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color FOREGROUND_FOR_DISPLAY;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private HolInfo[] holidayDelegate;
    private JComboBox shift;
    private JComboBox terse;
    private JEButton about;
    private JLabel instructions;
    private JLabel theYearLabel;
    private JLabel title;
    private JLabel title2;
    private JScrollPane scroller;
    private JTextArea display;
    private JTextField theYear;
    
    public void destroy() {
        this.about = null;
        this.display = null;
        this.holidayDelegate = null;
        this.instructions = null;
        this.scroller = null;
        this.shift = null;
        this.terse = null;
        this.theYear = null;
        this.theYearLabel = null;
        this.title = null;
        this.title2 = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(Holidays.BACKGROUND_FOR_APPLET);
        contentPane.setLayout(new GridBagLayout());
        this.buildComponents();
        this.findHolidays();
        this.refresh();
        this.layoutComponents(contentPane);
        final TheListener theListener = new TheListener();
        this.theYear.addActionListener(theListener);
        this.shift.addItemListener(theListener);
        this.terse.addItemListener(theListener);
    }
    
    private static String yearToDisplay(final int year) {
        if (year < 0) {
            return Integer.toString(-year) + " BCE";
        }
        if (year == 0) {
            return "";
        }
        return Integer.toString(year);
    }
    
    private void buildComponents() {
        (this.title = new JLabel("CMP Holiday Calculator 4.7")).setFont(Holidays.FONT_FOR_TITLE);
        this.title.setForeground(Holidays.FOREGROUND_FOR_TITLE);
        this.title.setFont(Holidays.FONT_FOR_TITLE);
        this.title.setForeground(Holidays.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-02-16 build:9411")).setFont(Holidays.FONT_FOR_TITLE2);
        this.title2.setForeground(Holidays.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("about")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutBox("CMP Holiday Calculator", "4.7", "Calculate when holidays occur", "in any given year.", "freeware", "2011-02-16", 1999, "Roedy Green", "HOLIDAYS", "1.5");
            }
        });
        (this.shift = new JComboBox()).addItem("actual");
        this.shift.addItem("nearest weekday");
        this.shift.setSelectedIndex(0);
        (this.terse = new JComboBox()).addItem("terse");
        this.terse.addItem("verbose");
        this.terse.setSelectedIndex(0);
        (this.theYear = new JTextField(10)).setMargin(new Insets(4, 4, 4, 4));
        this.theYear.setHorizontalAlignment(4);
        this.theYear.setText(Integer.toString(Holidays.defaultYear));
        (this.theYearLabel = new JLabel("year", 0)).setFont(FontFactory.build("Dialog", 1, 15));
        this.theYearLabel.setForeground(Holidays.FOREGROUND_FOR_LABEL);
        (this.display = new JTextArea("", 0, 0)).setEditable(false);
        this.display.setMargin(new Insets(4, 4, 4, 4));
        this.display.setFont(FontFactory.build("Serif", 0, 15));
        this.display.setForeground(Holidays.FOREGROUND_FOR_DISPLAY);
        this.scroller = new JScrollPane(this.display, 22, 31);
        (this.instructions = new JLabel("Select options, enter year (negative for BCE) and hit enter.", 0)).setForeground(Holidays.FOREGROUND_FOR_INSTRUCTIONS);
    }
    
    private String displayHolidays(final int year, final boolean shifted, final boolean verbose) {
        final FastCat sb = new FastCat(this.holidayDelegate.length * (verbose ? 25 : 11) + 5);
        sb.append("Holidays in year ");
        sb.append(year);
        sb.append(".");
        if (shifted) {
            sb.append("  nearest weekday");
        }
        sb.append("\n\n");
        for (final HolInfo h : this.holidayDelegate) {
            if (h != null) {
                final BigDate d = new BigDate(h.when(year, shifted));
                if (d.getOrdinal() != Integer.MIN_VALUE) {
                    sb.append(d.toString());
                    sb.append(" ");
                    sb.append(BigDate.dayName(d.getDayOfWeek()));
                    sb.append(" ");
                    sb.append(BigDate.monthName(d.getMM()));
                    sb.append(" ");
                    sb.append(d.getDD());
                    sb.append(" is ");
                    sb.append(shifted ? " nearest weekday to " : "");
                    sb.append(h.getName());
                    sb.append(".\n");
                    if (verbose) {
                        sb.append(h.getRule());
                        sb.append("\n");
                        final int yearFirstObserved = h.getFirstYear(1);
                        final int yearFirstProclaimed = h.getFirstYear(0);
                        sb.append("first observed ");
                        sb.append(yearToDisplay(yearFirstObserved));
                        sb.append(". ");
                        if (yearFirstObserved != yearFirstProclaimed) {
                            sb.append("first proclaimed ");
                            sb.append(yearToDisplay(yearFirstProclaimed));
                            sb.append(".");
                        }
                        sb.append("\n");
                        final String authority = h.getAuthority();
                        if (authority.length() > 0) {
                            sb.append("authority: ");
                            sb.append(authority);
                            sb.append("\n");
                        }
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }
    
    private void findHolidays() {
        String[][] result = null;
        try {
            final InputStream fis = Holidays.class.getResourceAsStream("Holiday.properties");
            result = Misc.loadProperties(fis);
        }
        catch (IOException oops) {
            System.out.println(oops + " Problem accessing Holiday.properties file.");
            System.exit(1);
        }
        final int length = result[0].length;
        int j = 0;
        this.holidayDelegate = new HolInfo[length];
        for (int i = 0; i < length; ++i) {
            try {
                if (result[1][i].equalsIgnoreCase("yes")) {
                    this.holidayDelegate[j++] = (HolInfo)Class.forName("com.mindprod.holidays." + result[0][i]).newInstance();
                }
            }
            catch (Exception oops2) {
                System.out.println(oops2 + " Bug in Holiday.properties or class file for " + result[0][i]);
                System.exit(1);
            }
        }
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 30.0, 0.0, 10, 2, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(1, 0, 1, 1, 30.0, 0.0, 10, 2, new Insets(10, 5, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(2, 0, 1, 1, 20.0, 0.0, 13, 0, new Insets(10, 5, 10, 10), 10, 2));
        contentPane.add(this.shift, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 13, 2, new Insets(0, 10, 0, 5), 150, 0));
        contentPane.add(this.terse, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 13, 2, new Insets(0, 5, 0, 5), 150, 0));
        contentPane.add(this.theYear, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, 13, 2, new Insets(0, 5, 0, 10), 0, 0));
        contentPane.add(this.theYearLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(0, 5, 10, 10), 0, 0));
        contentPane.add(this.scroller, new GridBagConstraints(0, 3, 3, 1, 0.0, 100.0, 10, 1, new Insets(0, 10, 10, 10), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0, 10, 0, new Insets(0, 10, 10, 10), 0, 0));
    }
    
    private void refresh() {
        int year = Holidays.defaultYear;
        try {
            year = (int)Math.min(ST.parseDirtyLong(this.theYear.getText()), 999999L);
        }
        catch (NumberFormatException ex) {}
        if (year == 0) {
            year = Holidays.defaultYear;
        }
        this.theYear.setText(Integer.toString(year));
        final boolean shifted = this.shift.getSelectedIndex() != 0;
        final boolean verbose = this.terse.getSelectedIndex() != 0;
        Arrays.sort(this.holidayDelegate, new HolidaySort(year, shifted));
        this.display.setText(this.displayHolidays(year, shifted, verbose));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Holidays(), "CMP Holiday Calculator 4.7", 720, 480);
    }
    
    static {
        defaultYear = Misc.thisYear();
        BACKGROUND_FOR_APPLET = Color.WHITE;
        FOREGROUND_FOR_DISPLAY = Color.BLACK;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
    
    private class TheListener implements ActionListener, ItemListener
    {
        public void actionPerformed(final ActionEvent event) {
            final Object object = event.getSource();
            if (object == Holidays.this.theYear) {
                Holidays.this.refresh();
            }
        }
        
        public void itemStateChanged(final ItemEvent event) {
            final Object object = event.getSource();
            if (object == Holidays.this.shift || object == Holidays.this.terse) {
                Holidays.this.refresh();
            }
        }
    }
}
