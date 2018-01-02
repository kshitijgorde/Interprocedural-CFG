// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import java.util.Collection;
import java.util.List;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.screencastomatic.play.stream.i;
import java.util.concurrent.LinkedBlockingQueue;
import com.screencastomatic.play.n;

public abstract class f implements Runnable
{
    private n a;
    private LinkedBlockingQueue b;
    private i c;
    
    protected f(final n a) {
        this.b = new LinkedBlockingQueue();
        this.c = new i(null, null, 0L);
        this.a = a;
    }
    
    public void a(final List list) {
        this.b.addAll(list);
    }
    
    public int b() {
        return this.b.size();
    }
    
    public void a(final boolean b) {
        if (b) {
            this.b.clear();
        }
        this.b.add(this.c);
    }
    
    abstract long a();
    
    public void run() {
        try {
            final int n = 10;
            long a = 0L;
            i i;
            while ((i = this.b.take()) != this.c) {
                if (i.c() < a) {
                    System.out.println("Skipping Web Cam: " + i.c() + " (pos: " + a + ")");
                }
                else {
                    while ((a = this.a()) != -1L && i.c() > a) {
                        Thread.sleep(n);
                    }
                    if (i.c() != -1L && a - i.c() > 100L) {
                        System.out.println("webcam: " + i.c() + " pos: " + a + " diff: " + (a - i.c()));
                    }
                    this.a.a(i);
                }
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Web cam play thread inturupted.");
        }
        System.out.println("Web cam play thread is exiting.");
    }
}
