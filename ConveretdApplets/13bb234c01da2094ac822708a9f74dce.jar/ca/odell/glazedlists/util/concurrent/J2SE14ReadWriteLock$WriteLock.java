// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.util.concurrent;

import java.io.Serializable;

public class J2SE14ReadWriteLock$WriteLock implements Lock, Serializable
{
    final J2SE14ReadWriteLock a;
    
    protected J2SE14ReadWriteLock$WriteLock(final J2SE14ReadWriteLock a) {
        if (a == null) {
            throw new NullPointerException();
        }
        this.a = a;
    }
    
    public void a() {
        synchronized (this) {
            if (this.a.c.b()) {
                return;
            }
            boolean interrupted = Thread.interrupted();
            try {
                do {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {
                        interrupted = true;
                    }
                } while (!this.a.c.d());
            }
            finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    public void b() {
        switch (this.a.c.i()) {
            case 0: {}
            case 1: {
                this.a.a.c();
            }
            case 2: {
                this.a.b.c();
            }
            default: {}
        }
    }
    
    synchronized void c() {
        this.notify();
    }
    
    public String toString() {
        final Thread c = this.a.c();
        return super.toString() + ((c == null) ? "[Unlocked]" : ("[Locked by thread " + c.getName() + "]"));
    }
}
