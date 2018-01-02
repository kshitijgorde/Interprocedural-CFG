// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Canvas;

class TextStatusCanvas extends Canvas implements Runnable, SessionListener
{
    String stateStr;
    String posString;
    String eventString;
    boolean needRepaint;
    int numLines;
    ControlSession session;
    boolean quit;
    long ptzUpdates;
    Rectangle statusIndicator;
    Thread thread;
    String[] curStatus;
    NuSiteProxyController applet;
    
    TextStatusCanvas(final NuSiteProxyController applet) {
        this.stateStr = "";
        this.posString = "";
        this.eventString = "";
        this.needRepaint = true;
        this.numLines = 2;
        this.quit = false;
        this.ptzUpdates = 0L;
        this.applet = applet;
        this.numLines = 2;
        this.curStatus = new String[this.numLines];
        for (int x = 0; x < this.numLines; ++x) {
            this.curStatus[x] = new String();
        }
        this.statusIndicator = new Rectangle(0, 15, 16, 16);
    }
    
    String createStatusString() {
        switch (this.session.cameraState()) {
            case 0: {
                this.stateStr = "Connecting";
                break;
            }
            case 2: {
                this.stateStr = "In Queue";
                break;
            }
            case 1: {
                this.stateStr = "Watching";
                break;
            }
            case 3: {
                this.stateStr = "Controlling";
                break;
            }
            case 5: {
                this.stateStr = "Camera Too Busy";
                break;
            }
            case 4: {
                this.stateStr = "Camera unavailable";
                break;
            }
            case 7: {
                this.stateStr = "Disconnected";
                break;
            }
            case 8: {
                this.stateStr = "Starting...";
                break;
            }
            case 6: {
                this.stateStr = "Controls Unavailable";
                break;
            }
            default: {
                final Exception e = new Exception("state change error:" + this.session.cameraState());
                Debug.report(e);
                break;
            }
        }
        final long msRemain = this.session.msRemaining();
        String timeRemain = "";
        if (msRemain >= 0L) {
            timeRemain = String.valueOf(timeRemain) + msRemain / 1000L;
            timeRemain = String.valueOf(timeRemain) + "." + msRemain % 1000L / 100L;
        }
        else {
            timeRemain = new StringBuffer().append(msRemain).toString();
        }
        final int p = this.session.pan;
        final int t = this.session.tilt;
        final int z = this.session.zoom;
        this.posString = "{" + p / 100 + "." + Math.abs(p % 100) + "," + t / 100 + "." + Math.abs(t % 100) + "," + z / 100 + "." + Math.abs(z % 100) + "}";
        try {
            final String scroll = " " + this.session.imagesReceived;
            final String theStatus = " " + this.stateStr + " time: " + timeRemain + " Evt:" + this.eventString + scroll;
            return theStatus;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public synchronized void paint(final Graphics g) {
        for (int x = 0; x < this.numLines; ++x) {
            g.drawString(this.curStatus[x], 10, 12 * (x + 1));
        }
        Color c = Color.black;
        switch (this.session.cameraState()) {
            case 4:
            case 5:
            case 7: {
                c = Color.red;
                break;
            }
            case 1: {
                if (this.session.queueLength == 0) {
                    c = Color.blue;
                    break;
                }
                c = Color.yellow;
                break;
            }
            case 3: {
                c = Color.green;
                break;
            }
            case 2: {
                c = Color.yellow;
                break;
            }
        }
        g.setColor(c);
        final int xx = this.getBounds().width - 32;
        g.fillRect(xx, 10, 16, 16);
        g.setColor(Color.black);
        g.drawRect(xx, 10, 17, 17);
    }
    
    public void setSession(final ControlSession session) {
        this.session = session;
        if (session != null) {
            session.addListener(this);
            this.quit = false;
            (this.thread = new Thread(this, "TextStatusCanvas")).start();
            this.show();
        }
    }
    
    public void stateChanged(final int state, final int queuePos, final int queueLen) {
        this.setEnabled(this.session.inControl());
        if (state == 7) {
            this.quit = true;
        }
        this.needRepaint = true;
        if (this.thread != null) {
            this.thread.interrupt();
        }
    }
    
    public void cameraChanged(final int p, final int t, final int z, final int backlight) {
        ++this.ptzUpdates;
        this.posString = "{" + p / 100 + "." + Math.abs(p % 100) + "," + t / 100 + "." + Math.abs(t % 100) + "," + z / 100 + "." + Math.abs(z % 100) + "}";
        this.needRepaint = true;
    }
    
    public void run() {
        try {
            while (!this.quit) {
                if (this.needRepaint) {
                    final String newStatus = this.createStatusString();
                    if (!newStatus.equals(this.curStatus[0])) {
                        this.curStatus[0] = newStatus;
                        this.needRepaint = true;
                    }
                }
                if (this.numLines > 1) {
                    this.needRepaint = true;
                    if (!this.posString.equals(this.curStatus[1])) {
                        this.curStatus[1] = String.valueOf(this.posString) + " qp=" + this.session.queuePos + " ql=" + this.session.queueLength;
                        final String[] curStatus = this.curStatus;
                        final int n = 1;
                        curStatus[n] = String.valueOf(curStatus[n]) + " " + this.applet.getScrollInfo();
                        this.needRepaint = true;
                    }
                }
                if (this.needRepaint) {
                    this.needRepaint = false;
                    this.repaint();
                }
                long delay = System.currentTimeMillis();
                try {
                    Thread.sleep(1000L);
                    this.needRepaint = true;
                }
                catch (InterruptedException ex) {}
                delay -= System.currentTimeMillis();
                if (delay > 5000L) {
                    Debug.println("WARNING: DELAY OF " + delay + " ms. in applet");
                }
            }
        }
        catch (Exception e) {
            Debug.report(e, "run exception");
            this.curStatus[1] = e.toString();
        }
        this.curStatus[0] = "Disconnected.";
    }
    
    public void moveRequested(final int p, final int t, final int z, final int backlight) {
    }
}
