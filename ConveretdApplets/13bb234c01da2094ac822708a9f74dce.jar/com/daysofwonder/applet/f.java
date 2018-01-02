// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.req.r;
import java.net.Socket;
import java.net.SocketException;
import com.daysofwonder.util.s;
import com.daysofwonder.util.t;
import com.daysofwonder.req.k;
import java.io.OutputStream;
import java.io.BufferedOutputStream;

public class f extends Thread implements I
{
    private BufferedOutputStream b;
    private com.daysofwonder.util.f c;
    private int d;
    private boolean e;
    final /* synthetic */ aM a;
    
    public f(final aM a, final OutputStream outputStream) {
        this.a = a;
        this.c = new com.daysofwonder.util.f();
        this.b = new BufferedOutputStream(outputStream);
    }
    
    public synchronized void a() {
        this.interrupt();
    }
    
    public synchronized void b() {
        ++this.d;
        while (!this.e && !this.a.e()) {
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {}
            finally {
                --this.d;
            }
        }
    }
    
    public synchronized void c() {
        this.e = true;
        if (this.d > 0) {
            this.notifyAll();
        }
    }
    
    public synchronized void a(final k k) {
        k.j(this.a.b++);
        try {
            this.e = false;
            t.a("queueing: " + k);
            this.c.a(k);
        }
        catch (s s) {}
    }
    
    public void run() {
        while (!this.isInterrupted()) {
            k k;
            try {
                k = (k)this.c.a();
            }
            catch (InterruptedException ex3) {
                System.out.println("Got and InterruptException while waiting for a request");
                break;
            }
            try {
                k.b(this.b);
                t.a("wrote: " + k);
                k.B();
                this.c();
            }
            catch (SocketException ex) {
                t.a(ex);
                t.b("Connection reset by peer!");
                if (this.a.e != null) {
                    this.a.e.aH();
                }
                return;
            }
            catch (Exception ex2) {
                if (!k.A()) {
                    try {
                        this.a.e.ag().b(1, new Object[] { ex2.getMessage() });
                    }
                    catch (Exception ex4) {}
                }
                System.out.println("Got and exception while writing: " + ex2.getMessage());
                t.a(ex2);
                y.a("IOerror writing", ex2);
                break;
            }
        }
        t.a("Writer thread shutdowned succesfully");
    }
}
