// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Panel;

public abstract class Gamelication extends Panel implements Runnable
{
    public Thread runner;
    public long currentTickTimeMillis;
    public long lastTickTimeMillis;
    public ActorManager actorManager;
    public DisplayManager displayManager;
    public ScoreManager scoreManager;
    public AudioManager audioManager;
    private String codebaseDir;
    public int SLEEP_MILLIS;
    
    public static double randBetween(double n, double n2) {
        if (n > n2) {
            final double n3 = n;
            n = n2;
            n2 = n3;
        }
        return n + (n2 - n) * Math.random();
    }
    
    public void init(final int n, final int n2) {
        this.init(n, n2, ".");
    }
    
    public void init(final int n, final int n2, final String codebaseDir) {
        this.setSize(n, n2);
        this.codebaseDir = codebaseDir;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
            new Thread(this.audioManager).start();
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
    
    public double deltaTickTimeMillis() {
        return this.currentTickTimeMillis - this.lastTickTimeMillis;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.displayManager.paint(graphics);
    }
    
    public String getGamelicationInfo() {
        return "The Gamelication Toolkit\nVersion 0.1\nWritten by Jeremy Hutchins, jgh8962@cs.rit.edu\n\nYou are free to use, copy, and modify the source without restriction.  However, it is requested that the author is mentioned in any pertinent credit sections released with code developed with the Gamelet Toolkit.";
    }
    
    public String getCodeBase() {
        return this.codebaseDir;
    }
    
    public void showStatus(final String s) {
        System.out.println(s);
    }
    
    public Image getImage(final String s, final String s2) {
        return this.getToolkit().getImage(String.valueOf(s) + "/" + s2);
    }
    
    public void play(final String s, final String s2) {
        this.audioManager.play(String.valueOf(s) + s2);
    }
    
    public Gamelication() {
        this.currentTickTimeMillis = System.currentTimeMillis();
        this.actorManager = new ActorManager(this);
        this.displayManager = new DisplayManager(this);
        this.audioManager = new AudioManager();
        this.SLEEP_MILLIS = 50;
    }
}
