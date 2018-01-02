// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import com.screencastomatic.play.stream.q;
import java.util.Arrays;
import com.screencastomatic.play.stream.b;
import java.util.List;
import com.screencastomatic.play.stream.r;

public class h implements j, r
{
    private long a;
    private long b;
    private long c;
    private n d;
    private Thread e;
    private k f;
    private Thread g;
    private f h;
    private boolean i;
    private int j;
    
    public h(final n d) {
        this.a = -1L;
        this.b = -1L;
        this.c = -1L;
        this.i = false;
        this.j = 1;
        this.d = d;
        this.f = new e(this, this.d.i());
        this.h = new d(this, this.d.i());
        (this.g = new Thread(this.h)).start();
        (this.e = new Thread(this.f)).start();
    }
    
    public void a(final int j) {
        this.j = j;
    }
    
    public int a() {
        return -1;
    }
    
    public void a(final byte[] array, final com.screencastomatic.play.stream.k k, List list, final List list2) {
        try {
            if (!this.i && !list.isEmpty()) {
                list = Arrays.asList(list.get(list.size() - 1));
                this.i = true;
            }
            this.f.a(list);
            this.h.a(list2);
            while (k.b() > this.e()) {
                Thread.sleep(50L);
            }
            this.c = k.b();
            this.d.i().a(k);
            if (this.d.a((c)null)) {
                this.a = -1L;
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Frame reader interrupted.");
        }
    }
    
    public void a(final Runnable runnable) {
        this.f.a(false);
        runnable.run();
    }
    
    public long b() {
        if (this.c == -1L) {
            return this.d.j();
        }
        return this.c;
    }
    
    public void c() {
        this.a = -1L;
        this.b = -1L;
    }
    
    public void d() {
        this.f.a(true);
        this.h.a(true);
        try {
            this.e.interrupt();
        }
        catch (SecurityException ex) {
            System.out.println("Security Exception when interrupting mouse thread.");
        }
        try {
            this.g.interrupt();
        }
        catch (SecurityException ex2) {
            System.out.println("Security Exception when interrupting webcam thread.");
        }
        q.a(this.e, "Mouse thread");
        q.a(this.g, "WebCam thread");
    }
    
    public long e() {
        if (this.b == -1L) {
            this.b = this.d.j();
        }
        if (this.a == -1L) {
            this.a = this.f();
        }
        if (this.j > 0) {
            this.b += (this.f() - this.a) * this.j;
        }
        else if (this.j < 0) {
            this.b += (this.f() - this.a) / -this.j;
        }
        this.a = this.f();
        return this.b;
    }
    
    private long f() {
        return System.currentTimeMillis();
    }
}
