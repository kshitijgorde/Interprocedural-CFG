// 
// Decompiled by Procyon v0.5.30
// 

package duc.calendar;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.CardLayout;
import duc.cal.gui.VietLabel;
import java.awt.Choice;
import duc.cal.gui.CalendarTool;
import duc.cal.gui.MonthApplet;
import java.awt.Panel;
import java.applet.Applet;

public class LunarCalendar extends Applet
{
    static String MONTH;
    static String HIST_CAL;
    static String ASTRO_CAL;
    static String CALCULATOR;
    static String COPYRIGHT;
    static String VERSION;
    Panel controlPanel;
    Panel displayPanel;
    MonthApplet astroCalPanel;
    MonthApplet histCalPanel;
    CalendarTool toolPanel;
    Choice selector;
    VietLabel author;
    CardLayout cl;
    
    static {
        LunarCalendar.MONTH = "Month Display";
        LunarCalendar.HIST_CAL = "Official calendar";
        LunarCalendar.ASTRO_CAL = "Astro calendar";
        LunarCalendar.CALCULATOR = "Calendar tool";
        LunarCalendar.COPYRIGHT = "H\u1ed3 Ng\u1ecdc \u0110\u1ee9c";
        LunarCalendar.VERSION = "Version 4.0";
    }
    
    public LunarCalendar() {
        (this.selector = new Choice()).add(LunarCalendar.HIST_CAL);
        this.selector.add(LunarCalendar.ASTRO_CAL);
        this.selector.add(LunarCalendar.CALCULATOR);
        this.selector.addItemListener(new DisplayMonth());
        (this.author = new VietLabel()).setText(LunarCalendar.COPYRIGHT);
        (this.controlPanel = new Panel()).setBackground(new Color(180, 220, 240));
        this.controlPanel.setFont(new Font("Arial", 1, 12));
        this.controlPanel.setLayout(new GridLayout(15, 1));
        this.controlPanel.add(new Label("VNCal - " + LunarCalendar.VERSION, 1));
        this.controlPanel.add(this.selector);
        this.controlPanel.add(new Label("+++", 1));
        this.controlPanel.add(new Label("© 1998, 2003", 1));
        this.controlPanel.add(this.author);
        this.controlPanel.add(new Label("http://come.to/duc", 1));
        this.astroCalPanel = new MonthApplet(true);
        this.histCalPanel = new MonthApplet(false);
        this.toolPanel = new CalendarTool();
        (this.displayPanel = new Panel()).setLayout(this.cl = new CardLayout());
        this.displayPanel.add(this.histCalPanel, LunarCalendar.HIST_CAL);
        this.displayPanel.add(this.astroCalPanel, LunarCalendar.ASTRO_CAL);
        this.displayPanel.add(this.toolPanel, LunarCalendar.CALCULATOR);
        this.setLayout(new BorderLayout());
        this.add(this.displayPanel, "Center");
        this.add(this.controlPanel, "West");
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 450);
    }
    
    public static void main(final String[] args) {
        final Frame f = new Frame("VNCal - Vietnamese lunar calendar");
        f.setSize(600, 480);
        f.addWindowListener(new WL());
        final LunarCalendar ma = new LunarCalendar();
        f.add(ma);
        f.pack();
        f.show();
        ma.start();
    }
    
    public void start() {
        this.histCalPanel.start();
    }
    
    class DisplayMonth implements ItemListener
    {
        public void itemStateChanged(final ItemEvent e) {
            final String current = LunarCalendar.this.selector.getSelectedItem();
            if (current.equals(LunarCalendar.HIST_CAL)) {
                LunarCalendar.this.histCalPanel.start();
            }
            else if (current.equals(LunarCalendar.ASTRO_CAL)) {
                LunarCalendar.this.astroCalPanel.start();
            }
            LunarCalendar.this.cl.show(LunarCalendar.this.displayPanel, current);
        }
    }
    
    static class WL extends WindowAdapter
    {
        public void windowClosing(final WindowEvent e) {
            System.exit(0);
        }
    }
}
