// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.Graphics;
import java.awt.Event;
import java.applet.Applet;

public abstract class Gamelet extends Applet implements Runnable
{
    public Thread runner;
    public long currentTickTimeMillis;
    public long lastTickTimeMillis;
    public ActorManager actorManager;
    public DisplayManager displayManager;
    public ScoreManager scoreManager;
    public EventManager eventManager;
    public int SLEEP_MILLIS;
    
    public static double randBetween(double n, double n2) {
        if (n > n2) {
            final double n3 = n;
            n = n2;
            n2 = n3;
        }
        return n + (n2 - n) * Math.random();
    }
    
    public void init() {
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public long sleepMillis() {
        return this.SLEEP_MILLIS;
    }
    
    public void run() {
        while (this.runner != null) {
            try {
                Thread.sleep(this.sleepMillis());
            }
            catch (InterruptedException ex) {}
            this.tick();
        }
        this.runner = null;
    }
    
    public void tick() {
        this.lastTickTimeMillis = this.currentTickTimeMillis;
        this.currentTickTimeMillis = System.currentTimeMillis();
        this.actorManager.tick();
        this.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        boolean handleEvent = false;
        if (this.eventManager != null) {
            handleEvent = this.eventManager.handleEvent(event);
        }
        return handleEvent;
    }
    
    public double deltaTickTimeMillis() {
        return this.currentTickTimeMillis - this.lastTickTimeMillis;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.displayManager.paint(graphics);
    }
    
    public String getAppletInfo() {
        return "The Gamelet Toolkit\nVersion 0.8\nWritten by Mark Tacchi, mtacchi@NeXT.COM\n\nYou are free to use, copy, and modify the source without restriction.  However, it is requested that the author is mentioned in any pertinent credit sections released with code developed with the Gamelet Toolkit.";
    }
    
    public Gamelet() {
        this.currentTickTimeMillis = System.currentTimeMillis();
        this.actorManager = new ActorManager(this);
        this.displayManager = new DisplayManager(this);
        this.eventManager = new EventManager();
        this.SLEEP_MILLIS = 50;
    }
}
