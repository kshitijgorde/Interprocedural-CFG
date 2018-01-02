// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

import java.awt.Font;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.util.Date;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.TimeZone;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.awt.Panel;

public class IClock extends Panel implements Serializable, PropertyChangeListener
{
    public static final int ANALOG = 4;
    public static final int DIGITAL = 5;
    private static final long MILLISECONDS_IN_DAY = 86400000L;
    private transient PropertyChangeSupport changesNotifier;
    private transient PropertyChangeListener propertyChangeListener;
    private DigitalDisplay digitalDisplay;
    private transient Timer timer;
    private transient ClockRefresher refresher;
    private int displayMode;
    private long timeOffset;
    private TimeZone currentTimeZone;
    private transient Calendar calendar;
    private AnalogDisplay analogDisplay;
    private transient ClockDisplay clockDisplay;
    private transient boolean clockStarted;
    private transient boolean inConstructor;
    private static final boolean DEBUG = false;
    
    public IClock() {
        this.displayMode = 5;
        this.clockStarted = false;
        this.inConstructor = true;
        this.inConstructor = true;
        super.setLayout(new BorderLayout());
        this.setDisplayMode(4);
        this.start();
        this.inConstructor = false;
    }
    
    public void setLayout(final LayoutManager layoutManager) {
    }
    
    public void start() {
        if (this.clockStarted) {
            return;
        }
        this.getTimer().addTimerListener(this.getRefresher());
        this.getTimer().startTicking();
        this.clockStarted = true;
    }
    
    public void stop() throws SecurityException {
        if (!this.clockStarted) {
            return;
        }
        this.getTimer().stopTicking();
        this.getTimer().removeTimerListener(this.getRefresher());
        this.timer = null;
        this.clockStarted = false;
    }
    
    private ClockRefresher getRefresher() {
        if (this.refresher == null) {
            this.refresher = new ClockRefresher(this);
        }
        return this.refresher;
    }
    
    private DigitalDisplay getDigitalDisplay() {
        if (this.digitalDisplay == null) {
            (this.digitalDisplay = new DigitalDisplay()).addPropertyChangeListener(this);
        }
        return this.digitalDisplay;
    }
    
    private AnalogDisplay getAnalogDisplay() {
        if (this.analogDisplay == null) {
            (this.analogDisplay = new AnalogDisplay()).addPropertyChangeListener(this);
        }
        return this.analogDisplay;
    }
    
    private Timer getTimer() {
        if (this.timer == null) {
            this.timer = new Timer();
        }
        return this.timer;
    }
    
    public TimeZone getTimeZone() {
        if (this.currentTimeZone == null) {
            this.currentTimeZone = TimeZone.getDefault();
        }
        return this.currentTimeZone;
    }
    
    public void setTimeZone(final TimeZone currentTimeZone) throws IllegalArgumentException {
        if (currentTimeZone == null) {
            throw new IllegalArgumentException();
        }
        if (currentTimeZone.equals(this.getTimeZone())) {
            return;
        }
        this.currentTimeZone = currentTimeZone;
        this.getDigitalDisplay().setTimeZone(this.currentTimeZone);
        this.invalidateCalendar();
    }
    
    private void invalidateCalendar() {
        this.calendar = null;
    }
    
    private Calendar getCalendar() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance(this.getTimeZone());
        }
        return this.calendar;
    }
    
    public void setTime(final Time time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final Calendar calendar = this.getCalendar();
        calendar.setTime(new Date(currentTimeMillis));
        this.setTimeOffset(time.getInMillis() - new Time(calendar.get(11), calendar.get(12), calendar.get(13)).getInMillis());
    }
    
    private long getTimeOffset() {
        return this.timeOffset;
    }
    
    private void setTimeOffset(final long timeOffset) {
        this.timeOffset = timeOffset;
    }
    
    void refresh() {
        synchronized (this) {
            final Time time = this.getTime();
            this.getClockDisplay().setTime(time.getHour(), time.getMinute(), time.getSecond());
        }
    }
    
    private ClockDisplay getClockDisplay() {
        if (this.clockDisplay == null) {
            switch (this.getDisplayMode()) {
                case 5: {
                    this.clockDisplay = this.getDigitalDisplay();
                    break;
                }
                case 4: {
                    this.clockDisplay = this.getAnalogDisplay();
                    break;
                }
            }
        }
        return this.clockDisplay;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.getPropertyChangeNotifier().firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }
    
    public Time getTime() {
        final Calendar calendar = this.getCalendar();
        calendar.setTime(new Date(System.currentTimeMillis() + this.getTimeOffset()));
        return new Time(calendar.get(11), calendar.get(12), calendar.get(13));
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
    
    public int getDisplayMode() {
        return this.displayMode;
    }
    
    public void setDisplayMode(final int displayMode) throws IllegalArgumentException {
        if (this.getDisplayMode() == displayMode) {
            return;
        }
        final int displayMode2 = this.displayMode;
        switch (this.displayMode = displayMode) {
            case 5: {
                this.setDisplay(this.getDigitalDisplay());
                break;
            }
            case 4: {
                this.setDisplay(this.getAnalogDisplay());
                break;
            }
            default: {
                throw new IllegalArgumentException(" bad display mode = " + displayMode);
            }
        }
        this.getPropertyChangeNotifier().firePropertyChange("displayMode", new Integer(displayMode2), new Integer(displayMode));
    }
    
    private void setDisplay(final Component component) {
        this.removeAll();
        this.add("Center", component);
        this.clockDisplay = null;
        this.validate();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.start();
    }
    
    private final void debug(final String s) {
    }
    
    public Color getAnalogBorderColor() {
        return this.getAnalogDisplay().getBorderColor();
    }
    
    public void setAnalogBorderColor(final Color borderColor) {
        this.getAnalogDisplay().setBorderColor(borderColor);
    }
    
    public int getAnalogBorderWidth() {
        return this.getAnalogDisplay().getBorderWidth();
    }
    
    public void setAnalogBorderWidth(final int borderWidth) {
        this.getAnalogDisplay().setBorderWidth(borderWidth);
    }
    
    public Color getAnalogDialColor() {
        return this.getAnalogDisplay().getDialColor();
    }
    
    public void setAnalogDialColor(final Color dialColor) {
        this.getAnalogDisplay().setDialColor(dialColor);
    }
    
    public Color getAnalogTickColor() {
        return this.getAnalogDisplay().getTickColor();
    }
    
    public void setAnalogTickColor(final Color tickColor) {
        this.getAnalogDisplay().setTickColor(tickColor);
    }
    
    public int getAnalogTickStyle() {
        return this.getAnalogDisplay().getTickStyle();
    }
    
    public void setAnalogTickStyle(final int tickStyle) {
        this.getAnalogDisplay().setTickStyle(tickStyle);
    }
    
    public int getAnalogNumeralStyle() {
        return this.getAnalogDisplay().getNumeralStyle();
    }
    
    public void setAnalogNumeralStyle(final int numeralStyle) {
        this.getAnalogDisplay().setNumeralStyle(numeralStyle);
    }
    
    public Color getAnalogNumeralColor() {
        return this.getAnalogDisplay().getNumeralColor();
    }
    
    public void setAnalogNumeralColor(final Color numeralColor) {
        this.getAnalogDisplay().setNumeralColor(numeralColor);
    }
    
    public Font getAnalogNumeralFont() {
        return this.getAnalogDisplay().getNumeralFont();
    }
    
    public void setAnalogNumeralFont(final Font numeralFont) {
        this.getAnalogDisplay().setNumeralFont(numeralFont);
    }
    
    public Color getAnalogHourHandColor() {
        return this.getAnalogDisplay().getHourHandColor();
    }
    
    public void setAnalogHourHandColor(final Color hourHandColor) {
        this.getAnalogDisplay().setHourHandColor(hourHandColor);
    }
    
    public boolean getAnalogHourHandVisible() {
        return this.getAnalogDisplay().isHourHandVisible();
    }
    
    public void setAnalogHourHandVisible(final boolean hourHandVisible) {
        this.getAnalogDisplay().setHourHandVisible(hourHandVisible);
    }
    
    public Color getAnalogMinuteHandColor() {
        return this.getAnalogDisplay().getMinuteHandColor();
    }
    
    public void setAnalogMinuteHandColor(final Color minuteHandColor) {
        this.getAnalogDisplay().setMinuteHandColor(minuteHandColor);
    }
    
    public boolean getAnalogMinuteHandVisible() {
        return this.getAnalogDisplay().isMinuteHandVisible();
    }
    
    public void setAnalogMinuteHandVisible(final boolean minuteHandVisible) {
        this.getAnalogDisplay().setMinuteHandVisible(minuteHandVisible);
    }
    
    public Color getAnalogSecondHandColor() {
        return this.getAnalogDisplay().getSecondHandColor();
    }
    
    public void setAnalogSecondHandColor(final Color secondHandColor) {
        this.getAnalogDisplay().setSecondHandColor(secondHandColor);
    }
    
    public boolean getAnalogSecondHandVisible() {
        return this.getAnalogDisplay().isSecondHandVisible();
    }
    
    public void setAnalogSecondHandVisible(final boolean secondHandVisible) {
        this.getAnalogDisplay().setSecondHandVisible(secondHandVisible);
    }
    
    public Font getDigitalNumeralFont() {
        return this.getDigitalDisplay().getNumeralFont();
    }
    
    public void setDigitalNumeralFont(final Font numeralFont) {
        this.getDigitalDisplay().setNumeralFont(numeralFont);
    }
    
    public Color getDigitalForeGroundColor() {
        return this.getDigitalDisplay().getForeGroundColor();
    }
    
    public void setDigitalForeGroundColor(final Color foreGroundColor) {
        this.getDigitalDisplay().setForeGroundColor(foreGroundColor);
    }
    
    public Color getDigitalBackGroundColor() {
        return this.getDigitalDisplay().getBackGroundColor();
    }
    
    public void setDigitalBackGroundColor(final Color backGroundColor) {
        this.getDigitalDisplay().setBackGroundColor(backGroundColor);
    }
    
    public int getDigitalDisplayStyle() {
        return this.getDigitalDisplay().getDisplayStyle();
    }
    
    public void setDigitalDisplayStyle(final int displayStyle) {
        this.getDigitalDisplay().setDisplayStyle(displayStyle);
    }
    
    public boolean getDigitalDisplayAMPM() {
        return this.getDigitalDisplay().getDisplayAMPM();
    }
    
    public void setDigitalDisplayAMPM(final boolean displayAMPM) {
        this.getDigitalDisplay().setDisplayAMPM(displayAMPM);
    }
    
    public boolean getDigitalTwelveHourMode() {
        return this.getDigitalDisplay().getTwelveHourMode();
    }
    
    public void setDigitalTwelveHourMode(final boolean twelveHourMode) {
        this.getDigitalDisplay().setTwelveHourMode(twelveHourMode);
    }
}
