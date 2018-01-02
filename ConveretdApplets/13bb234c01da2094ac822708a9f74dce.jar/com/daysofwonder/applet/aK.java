// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.net.Socket;
import java.io.IOException;
import java.net.SocketTimeoutException;
import com.daysofwonder.req.r;
import com.daysofwonder.req.k;
import com.daysofwonder.util.t;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class aK extends Thread implements v
{
    private boolean b;
    private BufferedInputStream c;
    final /* synthetic */ aM a;
    
    public aK(final aM a, final InputStream inputStream) {
        this.a = a;
        this.b = true;
        this.c = new BufferedInputStream(inputStream);
    }
    
    public synchronized void a() {
        t.a("Reader thread asked for shutdown...");
        this.b = false;
    }
    
    public void run() {
        while (!this.b()) {
            int n = 0;
            try {
                final k k = new k();
                int a;
                while ((a = k.a(this.c)) != -2 && !this.b()) {
                    if (a == -1) {
                        t.a("Connection to server is closed");
                        if (this.a.e != null) {
                            this.a.e.aH();
                        }
                        return;
                    }
                }
                if (!this.b()) {
                    try {
                        this.a.b(k);
                    }
                    catch (Exception ex) {
                        t.a(ex);
                    }
                    n = 0;
                }
                else {
                    if (k instanceof r) {
                        System.out.println("Got the disco in reader");
                        break;
                    }
                    continue;
                }
            }
            catch (SocketTimeoutException ex2) {
                t.a(ex2);
                t.b("Possible Timeout here: " + n);
                if (n++ > 2) {
                    if (this.a.e != null) {
                        t.b("Resetting connection...");
                        this.a.e.aH();
                    }
                    return;
                }
                t.b("Let's try again");
            }
            catch (IOException ex3) {
                t.a(ex3);
                y.a("IOerror reading", ex3);
                break;
            }
        }
        synchronized (this.a) {
            this.a.notify();
        }
        t.a("Reader thread shutdowned succesfully");
    }
    
    public synchronized boolean b() {
        return !this.b;
    }
}
