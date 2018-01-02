// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.Color;

public class Timer extends Thread
{
    long \u0105;
    long \u0106;
    public boolean timerRunning;
    public boolean timerStarted;
    TimerCanvas \u0107;
    
    public Timer(final TimerCanvas \u0107) {
        this.\u0106 = 0L;
        this.\u0107 = \u0107;
    }
    
    public void startTimer() {
        this.\u0105 = 0L;
        this.\u0106 = System.currentTimeMillis();
        this.timerRunning = true;
        this.timerStarted = true;
    }
    
    public void startTimer(final long \u0105) {
        this.\u0105 = \u0105;
        this.\u0106 = System.currentTimeMillis();
        this.timerRunning = true;
        this.timerStarted = true;
    }
    
    public void setTime(final long \u0105) {
        this.\u0105 = \u0105;
        this.\u0106 = System.currentTimeMillis();
        this.\u0107.setText(this.\u0105());
        this.\u0107.repaint();
        this.timerRunning = false;
        this.timerStarted = true;
    }
    
    public void repaint() {
        this.\u0107.repaint();
    }
    
    public void stopTimer() {
        this.timerRunning = false;
    }
    
    public void run() {
        this.\u0107.setText("00");
        this.timerRunning = false;
    Label_0014_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        if (this.timerRunning) {
                            this.\u0107.setText(this.\u0105());
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
        this.\u0107.setForeground(color);
        this.\u0107.setFontColor(color);
    }
    
    public void startpauze() {
        this.timerRunning = false;
    }
    
    public void stoppauze() {
        this.timerRunning = true;
    }
    
    String \u0105() {
        final long n = (System.currentTimeMillis() - this.\u0106 + this.\u0105) / 1000L;
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
