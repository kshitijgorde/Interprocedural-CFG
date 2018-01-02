// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

import java.beans.PropertyChangeListener;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.beans.PropertyChangeSupport;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.io.Serializable;
import java.awt.Canvas;

public class AnalogDisplay extends Canvas implements ClockDisplay, Serializable, Cloneable
{
    public static final int TICK_STYLE_ALL = 0;
    public static final int TICK_STYLE_MAJOR = 1;
    public static final int TICK_STYLE_MINIMAL = 2;
    public static final int TICK_STYLE_NONE = 3;
    public static final int NUMERAL_ROMAN = 0;
    public static final int NUMERAL_PLAIN = 1;
    private static final int DEFAULT_BORDER_WIDTH = 6;
    private static final Color DEFAULT_BORDER_COLOR;
    private static final Color DEFAULT_DIAL_COLOR;
    private static final Color DEFAULT_TICK_COLOR;
    private static final int DEFAULT_TICK_STYLE = 1;
    private static final int DEFAULT_TICK_WIDTH = 3;
    private static final int DEFAULT_NUMERAL_STYLE = 1;
    private static final Color DEFAULT_NUMERAL_COLOR;
    private static final Color DEFAULT_HOUR_HAND_COLOR;
    private static final Color DEFAULT_MINUTE_HAND_COLOR;
    private static final Color DEFAULT_SECOND_HAND_COLOR;
    private static final int DEFAULT_HOUR_HAND_THICKNESS = 6;
    private static final int DEFAULT_MINUTE_HAND_THICKNESS = 6;
    private static final int DEFAULT_SECOND_HAND_THICKNESS = 1;
    private static final int DEFAULT_NUMERAL_INSET = 8;
    private static final int DEFAULT_DIAL_RADIUS = 100;
    private static final String[] plainNumerals;
    private static final String[] romanNumerals;
    private transient ClockHand hourHand;
    private transient ClockHand minuteHand;
    private transient ClockHand secondHand;
    private Color borderColor;
    private int borderWidth;
    private Color dialColor;
    private Color tickColor;
    private int tickStyle;
    private int numeralStyle;
    private Color numeralColor;
    private Font numeralFont;
    private Color hourHandColor;
    private boolean hourHandVisible;
    private Color minuteHandColor;
    private boolean minuteHandVisible;
    private Color secondHandColor;
    private boolean secondHandVisible;
    private int tickWidth;
    private transient Image dialImage;
    private transient int hour;
    private transient int minute;
    private transient int second;
    private transient double[] sinTheta;
    private transient double[] cosTheta;
    private transient int sideLength;
    private transient PropertyChangeSupport changesNotifier;
    private transient Image offscreen;
    private static final boolean DEBUG = false;
    
    public AnalogDisplay() {
        this.borderWidth = 6;
        this.tickStyle = -1;
        this.numeralStyle = -1;
        this.hourHandVisible = true;
        this.minuteHandVisible = true;
        this.secondHandVisible = true;
        this.tickWidth = 3;
    }
    
    public AnalogDisplay(final Color borderColor, final int borderWidth, final Color dialColor, final Color tickColor, final int tickStyle, final int numeralStyle, final Color numeralColor, final Font numeralFont, final Color hourHandColor, final boolean hourHandVisible, final Color minuteHandColor, final boolean minuteHandVisible, final Color secondHandColor, final boolean secondHandVisible) throws IllegalArgumentException {
        this();
        if (borderColor == null || dialColor == null || tickColor == null || numeralColor == null || numeralFont == null || hourHandColor == null || minuteHandColor == null || secondHandColor == null) {
            throw new IllegalArgumentException();
        }
        if (!this.isProperBorderWidth(borderWidth) || !this.isProperTickStyle(tickStyle) || !this.isProperNumeralStyle(numeralStyle)) {
            throw new IllegalArgumentException();
        }
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.dialColor = dialColor;
        this.tickColor = tickColor;
        this.tickStyle = tickStyle;
        this.numeralStyle = numeralStyle;
        this.numeralColor = numeralColor;
        this.numeralFont = numeralFont;
        this.hourHandColor = hourHandColor;
        this.hourHandVisible = hourHandVisible;
        this.minuteHandColor = minuteHandColor;
        this.minuteHandVisible = minuteHandVisible;
        this.secondHandColor = secondHandColor;
        this.secondHandVisible = secondHandVisible;
    }
    
    public Font getNumeralFont() {
        if (this.numeralFont == null) {
            this.numeralFont = this.getFont();
        }
        return this.numeralFont;
    }
    
    public void setNumeralFont(final Font numeralFont) throws IllegalArgumentException {
        if (numeralFont == null) {
            throw new IllegalArgumentException();
        }
        final Font numeralFont2 = this.numeralFont;
        this.numeralFont = numeralFont;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("numeralFont", numeralFont2, numeralFont);
    }
    
    public Color getHourHandColor() {
        if (this.hourHandColor == null) {
            this.setHourHandColor(AnalogDisplay.DEFAULT_HOUR_HAND_COLOR);
        }
        return this.hourHandColor;
    }
    
    public void setHourHandColor(final Color color) throws IllegalArgumentException {
        if (color == null) {
            throw new IllegalArgumentException();
        }
        final Color hourHandColor = this.hourHandColor;
        this.hourHandColor = color;
        this.getHourHand().setColor(color);
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("hourHandColor", hourHandColor, color);
    }
    
    public Color getMinuteHandColor() {
        if (this.minuteHandColor == null) {
            this.setMinuteHandColor(AnalogDisplay.DEFAULT_HOUR_HAND_COLOR);
        }
        return this.minuteHandColor;
    }
    
    public void setMinuteHandColor(final Color color) throws IllegalArgumentException {
        if (color == null) {
            throw new IllegalArgumentException();
        }
        final Color minuteHandColor = this.minuteHandColor;
        this.minuteHandColor = color;
        this.getMinuteHand().setColor(color);
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("minuteHandColor", minuteHandColor, color);
    }
    
    public Color getSecondHandColor() {
        if (this.secondHandColor == null) {
            this.setSecondHandColor(AnalogDisplay.DEFAULT_HOUR_HAND_COLOR);
        }
        return this.secondHandColor;
    }
    
    public void setSecondHandColor(final Color color) throws IllegalArgumentException {
        if (color == null) {
            throw new IllegalArgumentException();
        }
        final Color secondHandColor = this.secondHandColor;
        this.secondHandColor = color;
        this.getSecondHand().setColor(color);
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("secondHandColor", secondHandColor, color);
    }
    
    public boolean isHourHandVisible() {
        return this.hourHandVisible;
    }
    
    public void setHourHandVisible(final boolean b) {
        final boolean hourHandVisible = this.hourHandVisible;
        this.hourHandVisible = b;
        this.getHourHand().setVisible(b);
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("hourHandVisible", new Boolean(hourHandVisible), new Boolean(b));
    }
    
    public boolean isMinuteHandVisible() {
        return this.minuteHandVisible;
    }
    
    public void setMinuteHandVisible(final boolean b) {
        final boolean minuteHandVisible = this.minuteHandVisible;
        this.minuteHandVisible = b;
        this.getMinuteHand().setVisible(b);
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("minuteHandVisible", new Boolean(minuteHandVisible), new Boolean(b));
    }
    
    public boolean isSecondHandVisible() {
        return this.secondHandVisible;
    }
    
    public void setSecondHandVisible(final boolean b) {
        final boolean secondHandVisible = this.secondHandVisible;
        this.secondHandVisible = b;
        this.getSecondHand().setVisible(b);
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("secondHandVisible", new Boolean(secondHandVisible), new Boolean(b));
    }
    
    private int computeHourHandLength() {
        int n = (int)(0.75 * (this.computeMinuteHandLength() - this.getTickWidth()));
        if (n < 0) {
            n = 0;
        }
        return n;
    }
    
    private int computeSecondHandLength() {
        int n = this.getSideLength() / 2 - this.getBorderWidth() - this.getBorderInset();
        if (n < 0) {
            n = 0;
        }
        return n;
    }
    
    private int computeMinuteHandLength() {
        int n = this.computeSecondHandLength() - this.getNumeralInset() - this.getMinuteHand().getWidth();
        if (--n < 0) {
            n = 0;
        }
        return n;
    }
    
    private void setHour(final int hour) {
        this.hour = hour;
    }
    
    private int getHour() {
        return this.hour;
    }
    
    private void setMinute(final int minute) {
        this.minute = minute;
    }
    
    private int getMinute() {
        return this.minute;
    }
    
    private void setSecond(final int second) {
        this.second = second;
    }
    
    private int getSecond() {
        return this.second;
    }
    
    public int getTickStyle() {
        if (this.tickStyle < 0) {
            this.tickStyle = 1;
        }
        return this.tickStyle;
    }
    
    public void setTickStyle(final int tickStyle) throws IllegalArgumentException {
        if (!this.isProperTickStyle(tickStyle)) {
            throw new IllegalArgumentException();
        }
        final int tickStyle2 = this.tickStyle;
        this.tickStyle = tickStyle;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("tickStyle", new Integer(tickStyle2), new Integer(tickStyle));
    }
    
    protected boolean isProperTickStyle(final int n) {
        boolean b = false;
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public int getNumeralStyle() {
        if (this.numeralStyle < 0) {
            this.numeralStyle = 1;
        }
        return this.numeralStyle;
    }
    
    public void setNumeralStyle(final int numeralStyle) {
        if (!this.isProperNumeralStyle(numeralStyle)) {
            throw new IllegalArgumentException();
        }
        final int numeralStyle2 = this.numeralStyle;
        this.numeralStyle = numeralStyle;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("numeralStyle", new Integer(numeralStyle2), new Integer(numeralStyle));
    }
    
    protected boolean isProperNumeralStyle(final int n) {
        boolean b = false;
        switch (n) {
            case 0:
            case 1: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public void setBounds(final int n, final int n2, final int sideLength, final int sideLength2) {
        super.setBounds(n, n2, sideLength, sideLength2);
        if (sideLength <= sideLength2) {
            this.sideLength = sideLength;
        }
        else {
            this.sideLength = sideLength2;
        }
        this.getHourHand().setLength(this.computeHourHandLength());
        this.getMinuteHand().setLength(this.computeMinuteHandLength());
        this.getSecondHand().setLength(this.computeSecondHandLength());
        this.invalidateDialImage();
    }
    
    private void invalidateDialImage() {
        this.dialImage = null;
    }
    
    private int getSideLength() {
        return this.sideLength;
    }
    
    protected String getNumberString(final int n) {
        switch (this.getNumeralStyle()) {
            case 1: {
                return AnalogDisplay.plainNumerals[n];
            }
            case 0: {
                return AnalogDisplay.romanNumerals[n];
            }
            default: {
                return null;
            }
        }
    }
    
    public Color getNumeralColor() {
        if (this.numeralColor == null) {
            this.numeralColor = AnalogDisplay.DEFAULT_NUMERAL_COLOR;
        }
        return this.numeralColor;
    }
    
    public void setNumeralColor(final Color numeralColor) throws IllegalArgumentException {
        if (numeralColor == null) {
            throw new IllegalArgumentException();
        }
        final Color numeralColor2 = this.numeralColor;
        this.numeralColor = numeralColor;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("numeralColor", numeralColor2, numeralColor);
    }
    
    private double getSinTheta(final int n) {
        if (this.sinTheta == null) {
            this.sinTheta = new double[60];
            for (int i = 0; i < 60; ++i) {
                this.sinTheta[i] = Math.sin(i * 0.10471975511965977);
            }
        }
        return this.sinTheta[n];
    }
    
    private double getCosTheta(final int n) {
        if (this.cosTheta == null) {
            this.cosTheta = new double[60];
            for (int i = 0; i < 60; ++i) {
                this.cosTheta[i] = Math.cos(i * 0.10471975511965977);
            }
        }
        return this.cosTheta[n];
    }
    
    public Color getTickColor() {
        if (this.tickColor == null) {
            this.tickColor = AnalogDisplay.DEFAULT_TICK_COLOR;
        }
        return this.tickColor;
    }
    
    public void setTickColor(final Color tickColor) throws IllegalArgumentException {
        if (tickColor == null) {
            throw new IllegalArgumentException();
        }
        final Color tickColor2 = this.tickColor;
        this.tickColor = tickColor;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("tickColor", tickColor2, tickColor);
    }
    
    public Color getBorderColor() {
        if (this.borderColor == null) {
            this.borderColor = AnalogDisplay.DEFAULT_BORDER_COLOR;
        }
        return this.borderColor;
    }
    
    public void setBorderColor(final Color borderColor) {
        if (borderColor == null) {
            throw new IllegalArgumentException();
        }
        final Color borderColor2 = this.borderColor;
        this.borderColor = borderColor;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("borderColor", borderColor2, borderColor);
    }
    
    public Color getDialColor() {
        if (this.dialColor == null) {
            this.dialColor = AnalogDisplay.DEFAULT_DIAL_COLOR;
        }
        return this.dialColor;
    }
    
    public void setDialColor(final Color dialColor) throws IllegalArgumentException {
        if (dialColor == null) {
            throw new IllegalArgumentException();
        }
        final Color dialColor2 = this.dialColor;
        this.dialColor = dialColor;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("dialColor", dialColor2, dialColor);
    }
    
    public int getBorderWidth() {
        return this.borderWidth;
    }
    
    public void setBorderWidth(final int borderWidth) {
        if (!this.isProperBorderWidth(borderWidth)) {
            throw new IllegalArgumentException();
        }
        final int borderWidth2 = this.borderWidth;
        this.borderWidth = borderWidth;
        this.invalidateDialImage();
        this.repaint();
        this.getPropertyChangeNotifier().firePropertyChange("borderWidth", new Integer(borderWidth2), new Integer(borderWidth));
    }
    
    protected boolean isProperBorderWidth(final int n) {
        return n >= 1;
    }
    
    private int getTickWidth() {
        return this.tickWidth;
    }
    
    public synchronized void setTime(final int hour, final int minute, final int second) {
        if (this.checkTime(hour, minute, second)) {
            this.setHour(hour);
            this.setMinute(minute);
            this.setSecond(second);
            this.repaint();
            return;
        }
        throw new IllegalArgumentException();
    }
    
    private boolean checkTime(final int n, final int n2, final int n3) {
        return n >= 0 && n <= 23 && n2 >= 0 && n2 <= 59 && n3 >= 0 && n3 <= 59;
    }
    
    public Dimension getPreferredSize() {
        final int n = 0 + this.getBorderWidth() + this.getBorderInset() + this.getNumeralInset() + this.getTickWidth() + this.getPreferredDialRadius();
        return new Dimension(n, n);
    }
    
    private int getPreferredDialRadius() {
        return 100;
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreen = null;
        this.invalidateDialImage();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen == null) {
            this.offscreen = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics graphics2 = this.offscreen.getGraphics();
        final Color color = graphics2.getColor();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics2.setColor(color);
        graphics2.setClip(0, 0, this.getSize().width, this.getSize().height);
        graphics2.drawImage(this.getDialImage(), 0, 0, null);
        this.getBounds();
        final int n = (this.getSecond() + 45) % 60;
        final int n2 = (this.getMinute() + 45) % 60;
        final int n3 = (this.getHour() + 9) % 12;
        final double n4 = n * 0.10471975511965977;
        final double n5 = n2 * 0.10471975511965977;
        final double n6 = n3 * 0.5235987755982988 + this.getMinute() / 12 * 0.10471975511965977;
        final int n7 = this.getSideLength() / 2;
        final int n8 = this.getSideLength() / 2;
        this.getHourHand().paint(graphics2, n7, n8, n6);
        this.getMinuteHand().paint(graphics2, n7, n8, n5);
        this.getSecondHand().paint(graphics2, n7, n8, n4);
        graphics.drawImage(this.offscreen, 0, 0, null);
        graphics2.dispose();
    }
    
    private int getBorderInset() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getNumeralFont());
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(this.getNumberString(0));
        final int stringWidth2 = fontMetrics.stringWidth(this.getNumberString(6));
        final int n = (stringWidth > stringWidth2) ? stringWidth : stringWidth2;
        return (((n > height) ? n : height) + 1) / 2;
    }
    
    private int getNumeralInset() {
        return this.getBorderInset();
    }
    
    private Image getDialImage() {
        if (this.dialImage == null) {
            final int n = 0;
            final int n2 = 0;
            final int sideLength = this.getSideLength();
            this.dialImage = this.createImage(sideLength, sideLength);
            final Graphics graphics = this.dialImage.getGraphics();
            final Color color = graphics.getColor();
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, sideLength, sideLength);
            graphics.setColor(color);
            this.drawBorder(graphics, n, n2, sideLength);
            final int n3 = this.getBorderWidth() + this.getBorderInset();
            final int n4 = n + n3;
            final int n5 = n2 + n3;
            final int n6 = sideLength - 2 * n3;
            this.drawNumbers(graphics, n4, n5, n6);
            final int n7 = this.getTickWidth() + this.getNumeralInset();
            this.drawTicks(graphics, n4 + n7, n5 + n7, n6 - 2 * n7);
            this.getHourHand().setLength(this.computeHourHandLength());
            this.getMinuteHand().setLength(this.computeMinuteHandLength());
            this.getSecondHand().setLength(this.computeSecondHandLength());
        }
        return this.dialImage;
    }
    
    private void drawBorder(final Graphics graphics, int n, int n2, int n3) {
        final Color color = graphics.getColor();
        graphics.setColor(this.getBorderColor());
        graphics.fillOval(n, n2, n3, n3);
        final int borderWidth = this.getBorderWidth();
        n += borderWidth;
        n2 += borderWidth;
        n3 -= 2 * borderWidth;
        graphics.setColor(this.getDialColor());
        graphics.fillOval(n, n2, n3, n3);
        graphics.setColor(color);
    }
    
    private void drawTicks(final Graphics graphics, final int n, final int n2, final int n3) {
        switch (this.getTickStyle()) {
            case 0: {
                this.drawTicks(graphics, n, n2, n3, 60);
            }
            case 1: {
                this.drawTicks(graphics, n, n2, n3, 12);
            }
            case 2: {
                this.drawTicks(graphics, n, n2, n3, 4);
            }
            default: {}
        }
    }
    
    private void drawTicks(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n + n3 / 2;
        final int n6 = n2 + n3 / 2;
        graphics.translate(n5, n6);
        final Color color = graphics.getColor();
        final int tickWidth = this.getTickWidth();
        final int n7 = n3 / 2;
        graphics.setColor(this.getTickColor());
        final int n8 = 60 / n4;
        for (int i = 0; i < n4; ++i) {
            graphics.fillOval((int)(n7 * this.getCosTheta(i * n8)) - tickWidth / 2, (int)(n7 * this.getSinTheta(i * n8)) - tickWidth / 2, tickWidth, tickWidth);
        }
        graphics.setColor(color);
        graphics.translate(-n5, -n6);
    }
    
    private void drawNumbers(final Graphics graphics, final int n, final int n2, final int n3) {
        final int n4 = n + n3 / 2;
        final int n5 = n2 + n3 / 2;
        graphics.translate(n4, n5);
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        final int n6 = n3 / 2;
        graphics.setColor(this.getNumeralColor());
        graphics.setFont(this.getNumeralFont());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        for (int i = 0; i < 12; ++i) {
            graphics.drawString(this.getNumberString(i), (int)(n6 * this.getCosTheta(5 * i)) - fontMetrics.stringWidth(this.getNumberString(i)) / 2, (int)(n6 * this.getSinTheta(5 * i)) + (fontMetrics.getAscent() + fontMetrics.getDescent()) / 2);
        }
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.translate(-n4, -n5);
    }
    
    private ClockHand getHourHand() {
        if (this.hourHand == null) {
            (this.hourHand = new ClockHand()).setWidth(6);
        }
        return this.hourHand;
    }
    
    private ClockHand getMinuteHand() {
        if (this.minuteHand == null) {
            (this.minuteHand = new ClockHand()).setWidth(6);
        }
        return this.minuteHand;
    }
    
    private ClockHand getSecondHand() {
        if (this.secondHand == null) {
            (this.secondHand = new ClockHand()).setWidth(1);
        }
        return this.secondHand;
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
    
    private void debug(final String s) {
    }
    
    static {
        DEFAULT_BORDER_COLOR = Color.black;
        DEFAULT_DIAL_COLOR = Color.white;
        DEFAULT_TICK_COLOR = Color.green;
        DEFAULT_NUMERAL_COLOR = Color.black;
        DEFAULT_HOUR_HAND_COLOR = Color.black;
        DEFAULT_MINUTE_HAND_COLOR = Color.black;
        DEFAULT_SECOND_HAND_COLOR = Color.black;
        plainNumerals = new String[] { "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "1", "2" };
        romanNumerals = new String[] { "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "I", "II" };
    }
}
