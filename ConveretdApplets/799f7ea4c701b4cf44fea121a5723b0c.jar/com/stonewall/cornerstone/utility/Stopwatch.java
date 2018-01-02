// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

public class Stopwatch
{
    private boolean running;
    private long start;
    private long end;
    
    public Stopwatch() {
        this.running = false;
        this.start = 0L;
        this.end = 0L;
    }
    
    public Stopwatch start() {
        this.running = true;
        this.start = System.currentTimeMillis();
        this.end = 0L;
        return this;
    }
    
    public Stopwatch stop() {
        if (this.running) {
            this.running = false;
            this.end = System.currentTimeMillis();
        }
        return this;
    }
    
    public long duration() {
        return this.running ? 0L : (this.end - this.start);
    }
    
    @Override
    public String toString() {
        return "duration: " + this.duration() + "(ms)";
    }
}
