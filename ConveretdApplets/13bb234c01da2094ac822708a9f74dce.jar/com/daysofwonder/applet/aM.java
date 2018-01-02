// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.util.t;
import com.daysofwonder.req.r;
import com.daysofwonder.req.k;
import java.net.Socket;

public class aM implements aq
{
    private boolean a;
    private int b;
    private aK c;
    private f d;
    private volatile aG e;
    private Socket f;
    private com.daysofwonder.util.f g;
    private boolean h;
    
    public aM(final Socket f) {
        this.a = false;
        this.g = new com.daysofwonder.util.f();
        (this.f = f).setSoTimeout(120000);
        this.c = new aK(this, this.f.getInputStream());
        this.d = new f(this, this.f.getOutputStream());
        this.c.start();
        this.d.start();
    }
    
    public k a() {
        return (k)this.g.a(0L);
    }
    
    public void a(final k k) {
        this.g.a(k);
    }
    
    public void b() {
    }
    
    public I c() {
        return this.d;
    }
    
    public v d() {
        return this.c;
    }
    
    public synchronized boolean f() {
        return this.h;
    }
    
    public synchronized void a(final aG e) {
        if (this.e != null) {
            this.g.b();
        }
        this.e = e;
    }
    
    public synchronized void g() {
        this.a = true;
        if (this.c != null) {
            this.c.a();
        }
        if (this.d != null) {
            this.d.a(new r());
            this.d.b();
            System.out.println("Waiting disconnect request...");
        }
        try {
            this.wait(1000L);
        }
        catch (InterruptedException ex) {}
        System.out.println("Shutdown Writer...");
        if (this.d != null) {
            this.d.a();
        }
        System.out.println("Closing receipt queue...");
        if (this.g != null) {
            this.g.c();
        }
        System.out.println("Bailing out...");
    }
    
    public synchronized boolean e() {
        return this.a;
    }
    
    public synchronized void b(final k k) {
        if (this.a && k instanceof r) {
            System.out.println("Got the disco");
            this.notify();
            System.out.println("notified");
            return;
        }
        if (k.y() != 12) {
            t.a("netlayer: transmitting req: " + k + " to game: " + this.e);
        }
        if (this.e != null && !this.a) {
            this.e.d(k);
        }
        if (k.y() != 12) {
            t.a("netlayer: req transmitted");
        }
    }
}
