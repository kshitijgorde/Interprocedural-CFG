// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.util.concurrent;

import java.io.Serializable;

public class J2SE14ReadWriteLock$ReadLock implements Lock, Serializable
{
    final J2SE14ReadWriteLock a;
    
    protected J2SE14ReadWriteLock$ReadLock(final J2SE14ReadWriteLock a) {
        if (a == null) {
            throw new NullPointerException();
        }
        this.a = a;
    }
    
    public void a() {
        synchronized (this) {
            if (this.a.c.a()) {
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
                } while (!this.a.c.c());
            }
            finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    public void b() {
        switch (this.a.c.h()) {
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
        this.notifyAll();
    }
    
    public String toString() {
        return super.toString() + "[Read locks = " + this.a.d() + "]";
    }
}
