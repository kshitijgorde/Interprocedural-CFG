// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.Color;

public class Timer extends Thread
{
    long \u00d5;
    long \u00d6;
    public boolean timerRunning;
    public boolean timerStarted;
    TimerCanvas \u00d8;
    
    public Timer(final TimerCanvas \u00f8) {
        this.\u00d6 = 0L;
        this.\u00d8 = \u00f8;
    }
    
    public void startTimer() {
        this.\u00d5 = 0L;
        this.\u00d6 = System.currentTimeMillis();
        this.timerRunning = true;
        this.timerStarted = true;
    }
    
    public void startTimer(final long \u00f5) {
        this.\u00d5 = \u00f5;
        this.\u00d6 = System.currentTimeMillis();
        this.timerRunning = true;
        this.timerStarted = true;
    }
    
    public void setTime(final long \u00f5) {
        this.\u00d5 = \u00f5;
        this.\u00d6 = System.currentTimeMillis();
        this.\u00d8.setText(this.\u00d5());
        this.\u00d8.repaint();
        this.timerRunning = false;
        this.timerStarted = true;
    }
    
    public void repaint() {
        this.\u00d8.repaint();
    }
    
    public void stopTimer() {
        this.timerRunning = false;
    }
    
    public void run() {
        this.\u00d8.setText("00");
        this.timerRunning = false;
    Label_0014_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        if (this.timerRunning) {
                            this.\u00d8.setText(this.\u00d5());
                        }
                        Thread.sleep(1000L);
                    }
                }
                catch (Exception ex) {
                    continue Label_0014_Outer;
                }
                continue;
            }
        }
    }
    
    public void setForeground(final Color color) {
        this.\u00d8.setForeground(color);
        this.\u00d8.setFontColor(color);
    }
    
    public void startpauze() {
        this.timerRunning = false;
    }
    
    public void stoppauze() {
        this.timerRunning = true;
    }
    
    String \u00d5() {
        final long n = (System.currentTimeMillis() - this.\u00d6 + this.\u00d5) / 1000L;
        final long n2 = n / 3600L;
        final long n3 = n - n2 * 3600L;
        final long n4 = n3 / 60L;
        final long n5 = n3 - n4 * 60L;
        String s = "";
        if (n2 == 0L) {
            s = "";
        }
        if (n2 > 0L && n2 < 10L) {
            s = "0" + n2 + ":";
        }
        if (n2 >= 10L) {
            s = n2 + ":";
        }
        String s2 = "";
        if (n4 == 0L) {
            s2 = "";
        }
        if (n4 > 0L && n4 < 10L) {
            s2 = "0" + n4 + ":";
        }
        if (n4 >= 10L) {
            s2 = n4 + ":";
        }
        String s3 = "";
        if (n5 < 10L) {
            s3 = "0" + n5;
        }
        if (n5 >= 10L) {
            s3 = String.valueOf(n5);
        }
        return String.valueOf(s) + s2 + s3;
    }
}
