// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Canvas;

public class QueueStatusCanvas extends Canvas implements Runnable, SessionListener
{
    boolean needRepaint;
    ControlSession session;
    boolean quit;
    long ptzUpdates;
    Rectangle statusIndicator;
    Thread thread;
    String curStatus;
    String curTime;
    boolean switchingDocument;
    NuApplet applet;
    final int font_size = 18;
    Font sf;
    Font tf;
    private Image background;
    boolean hadControl;
    Color inControl;
    Color disconnected;
    Color waiting;
    Color busy;
    Color c;
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    public QueueStatusCanvas(final NuApplet applet) {
        this.needRepaint = true;
        this.quit = false;
        this.ptzUpdates = 0L;
        this.curStatus = "";
        this.curTime = "";
        this.switchingDocument = false;
        this.background = null;
        this.hadControl = false;
        this.inControl = Color.green;
        this.disconnected = Color.red;
        this.waiting = Color.yellow;
        this.busy = Color.orange;
        this.c = Color.black;
        this.applet = applet;
        try {
            this.tf = new Font("SansSerif", 1, 18);
        }
        catch (Exception e) {
            this.tf = null;
            Debug.report(e, "Creating fonts...");
        }
        if (this.tf == null) {
            try {
                this.tf = new Font("Helvetica", 1, 18);
            }
            catch (Exception e) {
                Debug.report(e, "Creating Helvetica...");
                this.tf = null;
            }
        }
        this.statusIndicator = new Rectangle(0, 15, 16, 16);
    }
    
    String timeString() {
        if (this.session == null) {
            return "";
        }
        final long msRemain = this.session.msRemaining();
        String timeRemain = "";
        if (msRemain >= 0L) {
            final int ts = (int)msRemain / 1000;
            final int s = ts % 60;
            final int m = ts / 60;
            timeRemain = m + ":";
            if (s < 10) {
                timeRemain = String.valueOf(timeRemain) + "0";
            }
            timeRemain = String.valueOf(timeRemain) + s;
        }
        return timeRemain;
    }
    
    private boolean updateStatus() {
        boolean changed = false;
        String newStatus = this.curStatus;
        String newTime = "";
        Color newColor = this.c;
        Label_0360: {
            if (this.session == null) {
                if (this.switchingDocument) {
                    newColor = Color.white;
                }
                else {
                    newColor = this.disconnected;
                }
            }
            else {
                final String ts = this.timeString();
                final int state = this.session.cameraState();
                newTime = "";
                switch (state) {
                    case 4: {
                        newStatus = "Camera offline";
                        newColor = this.disconnected;
                        break;
                    }
                    case 7: {
                        newStatus = "Disconnected";
                        newColor = this.disconnected;
                        break;
                    }
                    case 5: {
                        newStatus = "Camera busy";
                        newColor = this.busy;
                        break;
                    }
                    case 6: {
                        newStatus = "Controls unavailable";
                        newColor = this.busy;
                        break;
                    }
                    case 1: {
                        if (this.session.queueLength == 0) {
                            if (this.hadControl) {
                                newStatus = "In control";
                            }
                            else {
                                newStatus = "Controls available";
                            }
                            newColor = this.inControl;
                            break;
                        }
                        newStatus = "Camera in use";
                        newColor = this.waiting;
                        this.hadControl = false;
                        break;
                    }
                    case 3: {
                        newStatus = "In control";
                        this.hadControl = true;
                        if (this.session.queueLength > 1) {
                            newTime = ts;
                        }
                        newColor = this.inControl;
                        break;
                    }
                    case 2: {
                        newColor = this.waiting;
                        this.hadControl = false;
                        newTime = ts;
                        switch (this.session.queuePos) {
                            case 0: {
                                newStatus = "";
                            }
                            case 1: {
                                newStatus = "Next in line...";
                                break Label_0360;
                            }
                            case 2: {
                                newStatus = "2nd in line...";
                                break Label_0360;
                            }
                            case 3: {
                                newStatus = "3rd in line...";
                                break Label_0360;
                            }
                            default: {
                                newStatus = this.session.queuePos + "th in line...";
                                break Label_0360;
                            }
                        }
                        break;
                    }
                    default: {
                        newStatus = "Unknown state " + state;
                        break;
                    }
                }
            }
        }
        if (!this.c.equals(newColor)) {
            changed = true;
            this.c = newColor;
        }
        if (!newStatus.equals(this.curStatus)) {
            this.curStatus = newStatus;
            changed = true;
        }
        if (!newTime.equals(this.curTime)) {
            this.curTime = newTime;
            changed = true;
        }
        return changed;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public synchronized void setBackground(final Image i) {
        this.background = i;
    }
    
    public synchronized void paint(final Graphics g) {
        if (this.background != null) {
            final Dimension d = this.getSize();
            final Point l = this.getLocation();
            g.drawImage(this.background, 0, 0, d.width, d.height, l.x, l.y, l.x + d.width, l.y + d.height, Color.white, null);
        }
        else {
            super.paint(g);
        }
        if (this.tf == null) {
            this.tf = g.getFont();
            if (this.tf != null) {
                g.setFont(this.tf = new Font(this.tf.getName(), 1, 18));
            }
        }
        else {
            g.setFont(this.tf);
        }
        final int size = 16;
        int x = 32;
        final int y = 20;
        g.setColor(Color.gray);
        g.drawString(this.curStatus, x, y);
        g.setColor(Color.black);
        g.drawString(this.curStatus, x - 1, y - 1);
        if (this.curTime.length() > 0) {
            final int sw = g.getFontMetrics().stringWidth(this.curTime);
            x = this.getWidth() - (sw + 15);
            g.setColor(Color.gray);
            g.drawString(this.curTime, x, y);
            g.setColor(Color.black);
            g.drawString(this.curTime, x - 1, y - 1);
        }
        g.setColor(this.c);
        final int xx = 4;
        final int yy = this.getHeight() / 2 - 8 - 2;
        g.fillRect(4, yy, 16, 16);
        g.setColor(Color.black);
        g.drawRect(4, yy, 17, 17);
        g.setColor(Color.gray);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
    }
    
    public void setSession(final ControlSession session) {
        this.session = session;
        if (session == null) {
            if (this.updateStatus()) {
                this.repaint();
            }
            return;
        }
        this.quit = false;
        (this.thread = new Thread(this, "QueueStatusCanvas")).start();
        session.addListener(this);
    }
    
    public void stateChanged(final int i, final int queuePos, final int queueLen) {
        if (this.session == null) {
            this.quit = true;
        }
        this.setEnabled(this.session != null && this.session.inControl());
        if (i == 7) {
            this.quit = true;
        }
        this.needRepaint = true;
        if (this.thread != null) {
            this.thread.interrupt();
        }
    }
    
    public void cameraChanged(final int p, final int t, final int z, final int backlight) {
        ++this.ptzUpdates;
        final String posString = "{" + p / 100 + "." + Math.abs(p % 100) + "," + t / 100 + "." + Math.abs(t % 100) + "," + z / 100 + "." + Math.abs(z % 100) + "}";
        this.needRepaint = true;
    }
    
    public void run() {
        try {
            while (!this.quit) {
                final boolean changed = this.updateStatus();
                if (changed) {
                    this.needRepaint = true;
                }
                if (this.needRepaint) {
                    this.needRepaint = false;
                    this.repaint();
                }
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (Exception e) {
            Debug.report(e, "run exception");
        }
        Debug.println("Exiting QueueStatusCanvas...");
        if (this.updateStatus()) {
            this.repaint();
        }
        this.thread = null;
    }
    
    public void moveRequested(final int p, final int t, final int z, final int backlight) {
    }
}
