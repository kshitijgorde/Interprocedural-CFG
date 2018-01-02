// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import java.util.Collection;
import java.util.List;
import com.screencastomatic.play.stream.b;
import java.util.concurrent.LinkedBlockingQueue;
import com.screencastomatic.play.n;

public abstract class k implements Runnable
{
    private n a;
    private LinkedBlockingQueue b;
    private b c;
    
    protected k(final n a) {
        this.b = new LinkedBlockingQueue();
        this.c = new b(0L, (short)0, (short)0);
        this.a = a;
    }
    
    public void a(final List list) {
        this.b.addAll(list);
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
            b b;
            while ((b = this.b.take()) != this.c) {
                if (b.a < a) {
                    continue;
                }
                while ((a = this.a()) != -1L && b.a > a + n) {
                    Thread.sleep(n);
                }
                this.a.a(b);
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Mouse play thread inturupted.");
        }
        System.out.println("Mouse play thread is exiting.");
    }
}
