// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import com.screencastomatic.play.stream.k;
import java.util.concurrent.LinkedBlockingQueue;
import com.screencastomatic.play.n;

public abstract class s implements Runnable
{
    private n a;
    private LinkedBlockingQueue b;
    private k c;
    
    protected s(final n a) {
        this.b = new LinkedBlockingQueue();
        this.c = new k(0L);
        this.a = a;
    }
    
    public void a(final k k) {
        this.b.add(k);
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
            k k;
            while ((k = this.b.take()) != this.c) {
                long a;
                while ((a = this.a()) != -1L && k.b() > a) {
                    Thread.sleep(n);
                }
                if (a - k.b() > 200L) {
                    System.out.println("frame: " + k.b() + " pos: " + a + " diff: " + (a - k.b()) + " numparts: " + k.a().size() + " fs: " + k.c());
                }
                this.a.a(k);
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Frame play thread inturupted.");
        }
        System.out.println("Frame play thread is exiting.");
    }
}
