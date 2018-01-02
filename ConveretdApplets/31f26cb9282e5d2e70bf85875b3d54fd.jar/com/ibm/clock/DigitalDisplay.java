// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

import java.beans.PropertyChangeListener;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyChangeSupport;
import java.util.TimeZone;
import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.awt.Canvas;

public class DigitalDisplay extends Canvas implements ClockDisplay, Serializable, Cloneable
{
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int SHORT = 2;
    private static final int ZEROHOUR = 12;
    private boolean displayAMPM;
    private boolean twelveHourMode;
    private int displayStyle;
    private Font numeralFont;
    private Color backGroundColor;
    private Color foreGroundColor;
    private static final int DEFAULT_DISPLAY_STYLE = 0;
    private static final boolean DEFAULT_DISPLAY_AMPM = true;
    private static final boolean DEFAULT_DISPLAY_TWELVEHOURMODE = true;
    private static final Color DEFAULT_FOREGROUND_COLOR;
    private static final Color DEFAULT_BACKGROUND_COLOR;
    private static final String AMstr;
    private static final String PMstr;
    private static final String colonFormat;
    private static final String justBlank = " ";
    private transient int hour24;
    private transient int hour;
    private transient int minute;
    private transient int second;
    private transient int xcoord;
    private transient int ycoord;
    private transient boolean isAMPM;
    private transient String timeZoneStr;
    private transient String formattedMinute;
    private transient String formattedSecond;
    private transient String AMPMstr;
    private transient StringBuffer displayStr;
    private TimeZone currentTimeZone;
    private transient PropertyChangeSupport changesNotifier;
    private transient Image offscreen;
    private transient Image digitalImage;
    private static final boolean DEBUG = false;
    
    public DigitalDisplay() {
        this.displayAMPM = true;
        this.twelveHourMode = true;
        this.isAMPM = true;
        this.AMPMstr = new String(" ");
    }
    
    public DigitalDisplay(final int displayStyle, final Color foreGroundColor, final Color backGroundColor, final Font numeralFont, final boolean displayAMPM, final boolean twelveHourMode) {
        this();
        this.setDisplayStyle(displayStyle);
        this.setForeGroundColor(foreGroundColor);
        this.setBackGroundColor(backGroundColor);
        this.setNumeralFont(numeralFont);
        this.setDisplayAMPM(displayAMPM);
        this.setTwelveHourMode(twelveHourMode);
    }
    
    public Font getNumeralFont() {
        if (this.numeralFont == null) {
            this.numeralFont = new Font("Dialog", 0, 18);
        }
        return this.numeralFont;
    }
    
    public void setNumeralFont(final Font numeralFont) throws NullPointerException {
        if (numeralFont == null) {
            throw new NullPointerException();
        }
        final Font numeralFont2 = this.numeralFont;
        this.numeralFont = numeralFont;
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("numeralFont", numeralFont2, numeralFont);
    }
    
    public void setTime(final int hour, final int minute, final int second) {
        if (this.checkTime(hour, minute, second)) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            this.isAMPM = true;
            this.repaint();
            return;
        }
        throw new IllegalArgumentException();
    }
    
    private boolean checkTime(final int n, final int n2, final int n3) {
        return n >= 0 && n <= 23 && n2 >= 0 && n2 <= 59 && n3 >= 0 && n3 <= 59;
    }
    
    public Dimension getPreferredSize() {
        final Dimension dimension = new Dimension();
        final FontMetrics fontMetrics = this.getFontMetrics(this.getNumeralFont());
        dimension.width = fontMetrics.stringWidth(this.displayStrFormatter().toString());
        dimension.height = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        return dimension;
    }
    
    private StringBuffer displayStrFormatter() {
        this.getDisplayStr().setLength(0);
        this.formatTimePortion();
        this.formatAMPMPortion();
        return this.displayStr;
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreen = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen == null) {
            this.offscreen = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics graphics2 = this.offscreen.getGraphics();
        graphics2.setClip(0, 0, this.getSize().width, this.getSize().height);
        graphics2.drawImage(this.getDigitalImage(), 0, 0, null);
        graphics2.setFont(this.getNumeralFont());
        final FontMetrics fontMetrics = this.getFontMetrics(this.getNumeralFont());
        final int stringWidth = fontMetrics.stringWidth(this.displayStrFormatter().toString());
        final int n = fontMetrics.getMaxAscent() - fontMetrics.getMaxDescent();
        final Rectangle bounds = this.getBounds();
        graphics2.setColor(this.getBackGroundColor());
        graphics2.fillRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
        graphics2.setColor(this.getForeGroundColor());
        this.xcoord = (bounds.width - stringWidth) / 2;
        this.ycoord = (bounds.height + n) / 2;
        graphics2.drawString(this.displayStrFormatter().toString(), this.xcoord, this.ycoord);
        graphics.drawImage(this.offscreen, 0, 0, null);
        graphics2.dispose();
    }
    
    public Image getDigitalImage() {
        if (this.digitalImage == null) {
            this.digitalImage = this.createImage(this.getSize().width, this.getSize().height);
            final Graphics graphics = this.digitalImage.getGraphics();
            graphics.setFont(this.getNumeralFont());
            final FontMetrics fontMetrics = this.getFontMetrics(this.getNumeralFont());
            final int stringWidth = fontMetrics.stringWidth(this.displayStrFormatter().toString());
            final int n = fontMetrics.getMaxAscent() - fontMetrics.getMaxDescent();
            final Rectangle bounds = this.getBounds();
            graphics.setColor(this.getBackGroundColor());
            graphics.fillRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
            graphics.setColor(this.getForeGroundColor());
            this.xcoord = (bounds.width - stringWidth) / 2;
            this.ycoord = (bounds.height + n) / 2;
        }
        return this.digitalImage;
    }
    
    public void setForeGroundColor(final Color foreGroundColor) {
        if (foreGroundColor == null) {
            throw new IllegalArgumentException();
        }
        final Color foreGroundColor2 = this.foreGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("foreGroundColor", foreGroundColor2, foreGroundColor);
    }
    
    public void setBackGroundColor(final Color backGroundColor) {
        if (backGroundColor == null) {
            throw new IllegalArgumentException();
        }
        final Color backGroundColor2 = this.backGroundColor;
        this.backGroundColor = backGroundColor;
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("backGroundColor", backGroundColor2, backGroundColor);
    }
    
    public Color getForeGroundColor() {
        if (this.foreGroundColor == null) {
            this.foreGroundColor = DigitalDisplay.DEFAULT_FOREGROUND_COLOR;
        }
        return this.foreGroundColor;
    }
    
    public Color getBackGroundColor() {
        if (this.backGroundColor == null) {
            this.backGroundColor = DigitalDisplay.DEFAULT_BACKGROUND_COLOR;
        }
        return this.backGroundColor;
    }
    
    public void setTwelveHourMode(final boolean twelveHourMode) {
        final boolean twelveHourMode2 = this.twelveHourMode;
        this.twelveHourMode = twelveHourMode;
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("twelveHourMode", new Boolean(twelveHourMode2), new Boolean(twelveHourMode));
    }
    
    public boolean getTwelveHourMode() {
        return this.twelveHourMode;
    }
    
    public void setDisplayAMPM(final boolean displayAMPM) {
        final boolean displayAMPM2 = this.displayAMPM;
        this.displayAMPM = displayAMPM;
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("displayAMPM", new Boolean(displayAMPM2), new Boolean(displayAMPM));
    }
    
    public boolean getDisplayAMPM() {
        return this.displayAMPM;
    }
    
    public void setDisplayStyle(final int displayStyle) {
        final int displayStyle2 = this.displayStyle;
        this.displayStyle = displayStyle;
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("displayStyle", new Integer(displayStyle2), new Integer(displayStyle));
    }
    
    public int getDisplayStyle() {
        if (!(this.displayStyle == 0 | this.displayStyle == 1 | this.displayStyle == 2)) {
            this.displayStyle = 0;
        }
        return this.displayStyle;
    }
    
    private void formatTimePortion() {
        if (this.minute < 10) {
            this.formattedMinute = String.valueOf(DigitalDisplay.colonFormat) + "0" + this.minute;
        }
        else {
            this.formattedMinute = String.valueOf(DigitalDisplay.colonFormat) + this.minute;
        }
        if (this.second < 10) {
            this.formattedSecond = String.valueOf(DigitalDisplay.colonFormat) + "0" + this.second;
        }
        else {
            this.formattedSecond = String.valueOf(DigitalDisplay.colonFormat) + this.second;
        }
        if (this.isAMPM) {
            if (this.twelveHourMode) {
                if (this.hour == 12) {
                    this.AMPMstr = DigitalDisplay.PMstr;
                }
                else if (this.hour < 12) {
                    this.AMPMstr = DigitalDisplay.AMstr;
                }
                else if (this.hour > 12) {
                    this.hour -= 12;
                    this.AMPMstr = DigitalDisplay.PMstr;
                }
            }
            this.isAMPM = false;
        }
        String s = String.valueOf(this.hour);
        if (!this.twelveHourMode && this.hour < 10) {
            s = "0" + s;
        }
        this.getDisplayStr().append(s);
        this.getDisplayStr().append(this.formattedMinute);
        if (this.displayStyle != 2) {
            this.getDisplayStr().append(this.formattedSecond);
        }
    }
    
    private void formatAMPMPortion() {
        if (this.twelveHourMode && this.displayAMPM) {
            this.getDisplayStr().append(" ").append(this.getAMPMstr());
        }
        if (this.displayStyle == 0) {
            this.getDisplayStr().append(" ").append(this.getTimeZoneStr());
        }
    }
    
    public void setTimeZone(final TimeZone currentTimeZone) throws IllegalArgumentException {
        if (currentTimeZone == null) {
            throw new IllegalArgumentException();
        }
        if (currentTimeZone.equals(this.currentTimeZone)) {
            return;
        }
        this.currentTimeZone = currentTimeZone;
        this.timeZoneStr = this.currentTimeZone.getID();
        this.repaint();
    }
    
    private TimeZone getTimeZone() {
        if (this.currentTimeZone == null) {
            this.currentTimeZone = TimeZone.getDefault();
        }
        return this.currentTimeZone;
    }
    
    private StringBuffer getDisplayStr() {
        if (this.displayStr == null) {
            this.displayStr = new StringBuffer();
        }
        return this.displayStr;
    }
    
    private String getTimeZoneStr() {
        if (this.timeZoneStr == null) {
            this.timeZoneStr = TimeZone.getDefault().getID();
        }
        return this.timeZoneStr;
    }
    
    private String getAMPMstr() {
        if (this.AMPMstr == null) {
            this.AMPMstr = "AM";
        }
        return this.AMPMstr;
    }
    
    private PropertyChangeSupport getPropertyChangeNotifier() {
        if (this.changesNotifier == null) {
            this.changesNotifier = new PropertyChangeSupport(this);
        }
        return this.changesNotifier;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChangeNotifier().addPropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChangeNotifier().removePropertyChangeListener(propertyChangeListener);
    }
    
    private static final void debug(final String s) {
    }
    
    static {
        DEFAULT_FOREGROUND_COLOR = Color.black;
        DEFAULT_BACKGROUND_COLOR = Color.lightGray;
        AMstr = new String("AM");
        PMstr = new String("PM");
        colonFormat = new String(":");
    }
}
