// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.arcclock;

import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.util.Date;
import java.util.Map;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.util.Calendar;
import java.text.DateFormat;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class ClockApplet extends Applet implements MouseListener, Runnable
{
    private final String TITLE = "ArcClock Applet";
    private final String VERSION = "1.2";
    private final boolean REGISTERED = false;
    private final int ME_BANNER = 30;
    private final int ME_FULL = 37;
    private final int SHADOW_ALPHA = 88;
    private final int ARC_ALPHA = 88;
    private Thread thread;
    private int me;
    private DateFormat timeFormatter;
    private DateFormat dateFormatter;
    private Calendar calendar;
    private Image background;
    private Color backColor;
    private Color borderColor;
    private Color textColor;
    private Color arcColor;
    private int borderWidth;
    private int shadowOffset;
    private Color shadowColor;
    private Image buffer;
    private static boolean aa;
    
    public ClockApplet() {
        this.me = 0;
        this.background = null;
        this.buffer = null;
    }
    
    public void init() {
        this.setFont(Font.decode(this.getParameter("Font", "Helvetica-bold-24")));
        this.borderWidth = Integer.parseInt(this.getParameter("BorderWidth", "1"));
        this.shadowOffset = Integer.parseInt(this.getParameter("ShadowOffset", "2"));
        this.borderColor = Color.decode(this.getParameter("BorderColor", "#ffffff"));
        this.textColor = Color.decode(this.getParameter("TextColor", "#ffff00"));
        this.backColor = Color.decode(this.getParameter("BackColor", "#a0a0a0"));
        final String parameter = this.getParameter("ShadowColor");
        if (parameter != null) {
            this.shadowColor = Color.decode(parameter);
        }
        else {
            try {
                this.shadowColor = new Color(0, 0, 0, 88);
            }
            catch (Throwable t) {
                this.shadowColor = this.compose(Color.black, this.backColor, 88);
            }
        }
        final String parameter2 = this.getParameter("ArcColor");
        if (parameter2 != null) {
            this.arcColor = Color.decode(parameter2);
        }
        else {
            try {
                this.arcColor = new Color(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue(), 88);
            }
            catch (Throwable t2) {
                this.arcColor = this.compose(this.textColor, (this.shadowOffset == 0) ? this.backColor : this.shadowColor, 88);
            }
        }
        final String parameter3 = this.getParameter("Background");
        if (parameter3 != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.background = this.getImage(this.getDocumentBase(), parameter3), 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (Exception ex) {}
        }
        this.calendar = Calendar.getInstance();
        this.timeFormatter = DateFormat.getTimeInstance(1);
        this.dateFormatter = DateFormat.getDateInstance(1);
        this.timeFormatter.setCalendar(this.calendar);
        this.dateFormatter.setCalendar(this.calendar);
        this.addMouseListener(this);
    }
    
    private String getParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? s2 : parameter;
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        this.thread = null;
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.buffer == null || this.buffer.getWidth(null) != size.width || this.buffer.getHeight(null) != size.height) {
            this.buffer = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.buffer.getGraphics();
        graphics2.setColor(this.backColor);
        graphics2.fillRect(0, 0, size.width, size.height);
        this.paint(graphics2);
        graphics.drawImage(this.buffer, 0, 0, null);
    }
    
    public void paint(final Graphics aa) {
        final Dimension size = this.getSize();
        if (this.background != null) {
            final int width = this.background.getWidth(this);
            final int height = this.background.getHeight(this);
            if (width > 0 && height > 0) {
                for (int i = 0; i < size.width; i += width) {
                    for (int j = 0; j < size.height; j += height) {
                        aa.drawImage(this.background, i, j, this);
                    }
                }
            }
        }
        if (ClockApplet.aa) {
            try {
                new Object() {
                    void setAA(final Graphics graphics) throws Throwable {
                        ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                    }
                }.setAA(aa);
            }
            catch (Throwable t) {
                System.err.println("ArcClock: " + t);
                System.err.println("          Text antialiasing is not supported by your Java VM.");
                ClockApplet.aa = false;
            }
        }
        final Date time = new Date();
        this.calendar.setTime(time);
        if (this.me > 0) {
            final String s = (this.me <= 30) ? this.timeFormatter.format(time) : "ArcClock Applet";
            final String s2 = (this.me <= 30) ? this.dateFormatter.format(time) : "(unregistered)";
            int size2 = aa.getFont().getSize();
            final int style = aa.getFont().getStyle();
            final String name = aa.getFont().getName();
            FontMetrics fontMetrics;
            while (true) {
                aa.setFont(new Font(name, style, size2));
                fontMetrics = aa.getFontMetrics();
                if (size.width - fontMetrics.stringWidth(s) >= 10 + this.borderWidth * 2 && size.width - fontMetrics.stringWidth(s2) >= 10 + this.borderWidth * 2 && size.height - fontMetrics.getHeight() * 2 >= 10 + this.borderWidth * 2) {
                    break;
                }
                --size2;
            }
            final int n = (size.height - fontMetrics.getHeight() * 2) / 2 + fontMetrics.getAscent();
            final int n2 = n + fontMetrics.getHeight();
            if (this.shadowOffset != 0) {
                aa.setColor(this.shadowColor);
                aa.drawString(s, (size.width - fontMetrics.stringWidth(s)) / 2 + this.shadowOffset, n + this.shadowOffset);
                aa.drawString(s2, (size.width - fontMetrics.stringWidth(s2)) / 2 + this.shadowOffset, n2 + this.shadowOffset);
            }
            aa.setColor(this.textColor);
            aa.drawString(s, (size.width - fontMetrics.stringWidth(s)) / 2, n);
            aa.drawString(s2, (size.width - fontMetrics.stringWidth(s2)) / 2, n2);
        }
        else {
            int value = this.calendar.get(11);
            final int value2 = this.calendar.get(12);
            final int value3 = this.calendar.get(13);
            final int value4 = this.calendar.get(14);
            final String string = ((value < 10) ? "0" : "") + value;
            final String string2 = ((value2 < 10) ? "0" : "") + value2;
            final String string3 = ((value3 < 10) ? "0" : "") + value3;
            if (value > 12) {
                value -= 12;
            }
            final FontMetrics fontMetrics2 = aa.getFontMetrics();
            final int stringWidth = fontMetrics2.stringWidth("00");
            final int height2 = fontMetrics2.getHeight();
            final int n3 = (int)(Math.sqrt(stringWidth * stringWidth + height2 * height2) * 1.2);
            final int n4 = (size.width - n3 * 3 - 6) / 2;
            final int n5 = (n3 - fontMetrics2.stringWidth(string)) / 2 + n4;
            final int n6 = n4 + n3 + 3;
            final int n7 = (n3 - fontMetrics2.stringWidth(string2)) / 2 + n6;
            final int n8 = n6 + n3 + 3;
            final int n9 = (n3 - fontMetrics2.stringWidth(string3)) / 2 + n8;
            final int n10 = (size.height - n3) / 2;
            final int n11 = (size.height - fontMetrics2.getHeight()) / 2 + (fontMetrics2.getHeight() - fontMetrics2.getDescent());
            final int n12 = -(value3 * 360) / 60 - value4 * 360 / 60000;
            final int n13 = -(value2 * 360) / 60 - value3 * 360 / 3600;
            final int n14 = -(value * 360) / 12 - value2 * 360 / 720;
            if (this.shadowOffset != 0) {
                aa.setColor(this.shadowColor);
                aa.fillArc(n8 + this.shadowOffset, n10 + this.shadowOffset, n3 + 1, n3 + 1, 90, n12);
                aa.fillArc(n6 + this.shadowOffset, n10 + this.shadowOffset, n3 + 1, n3 + 1, 90, n13);
                aa.fillArc(n4 + this.shadowOffset, n10 + this.shadowOffset, n3 + 1, n3 + 1, 90, n14);
                aa.drawOval(n4 + this.shadowOffset, n10 + this.shadowOffset, n3, n3);
                aa.drawOval(n6 + this.shadowOffset, n10 + this.shadowOffset, n3, n3);
                aa.drawOval(n8 + this.shadowOffset, n10 + this.shadowOffset, n3, n3);
                aa.drawString(string, n5 + this.shadowOffset, n11 + this.shadowOffset);
                aa.drawString(string2, n7 + this.shadowOffset, n11 + this.shadowOffset);
                aa.drawString(string3, n9 + this.shadowOffset, n11 + this.shadowOffset);
            }
            aa.setColor(this.arcColor);
            aa.fillArc(n8, n10, n3 + 1, n3 + 1, 90, n12);
            aa.fillArc(n6, n10, n3 + 1, n3 + 1, 90, n13);
            aa.fillArc(n4, n10, n3 + 1, n3 + 1, 90, n14);
            aa.drawOval(n4, n10, n3, n3);
            aa.drawOval(n6, n10, n3, n3);
            aa.drawOval(n8, n10, n3, n3);
            aa.setColor(this.textColor);
            aa.drawString(string, n5, n11);
            aa.drawString(string2, n7, n11);
            aa.drawString(string3, n9, n11);
        }
        aa.setColor(this.borderColor);
        for (int k = 0; k < this.borderWidth; ++k) {
            aa.drawRect(k, k, size.width - 1 - k * 2, size.height - 1 - k * 2);
        }
    }
    
    public void run() {
        while (Thread.currentThread() == this.thread) {
            this.repaint();
            if (this.me > 0) {
                synchronized (this) {
                    --this.me;
                    if (this.me == 30) {
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this) {
            this.me = 37;
            this.setCursor(new Cursor(12));
        }
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.me <= 30) {
            return;
        }
        try {
            this.getAppletContext().showDocument(new URL("http://bm.org.ru/java/arcclock.html"), "_top");
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public String getAppletInfo() {
        return "ArcClock Applet (1.2 unregistered)\nby Mikhail A. Kryshen\nMore info and download: http://bm.org.ru/java/arcclock.html";
    }
    
    private Color compose(final Color color, final Color color2, final int n) {
        return new Color((int)((color.getRed() * n + color2.getRed() * (255 - n)) / 255L), (int)((color.getGreen() * n + color2.getGreen() * (255 - n)) / 255L), (int)((color.getBlue() * n + color2.getBlue() * (255 - n)) / 255L));
    }
    
    public void destroy() {
        this.removeMouseListener(this);
    }
    
    static {
        ClockApplet.aa = true;
    }
}
