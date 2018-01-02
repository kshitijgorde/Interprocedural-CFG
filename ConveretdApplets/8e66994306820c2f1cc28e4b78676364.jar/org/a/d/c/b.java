// 
// Decompiled by Procyon v0.5.30
// 

package org.a.d.c;

public class b extends Thread
{
    private boolean[] a;
    
    public b() {
        (this.a = new boolean[1])[0] = false;
    }
    
    public b(final Runnable runnable) {
        super(runnable);
        (this.a = new boolean[1])[0] = false;
    }
    
    public b(final Runnable runnable, final String s) {
        super(runnable, s);
        (this.a = new boolean[1])[0] = false;
    }
    
    public b(final ThreadGroup threadGroup, final Runnable runnable) {
        super(threadGroup, runnable);
        (this.a = new boolean[1])[0] = false;
    }
    
    public b(final ThreadGroup threadGroup, final String s) {
        super(threadGroup, s);
        (this.a = new boolean[1])[0] = false;
    }
    
    public b(final ThreadGroup threadGroup, final Runnable runnable, final String s) {
        super(threadGroup, runnable, s);
        (this.a = new boolean[1])[0] = false;
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        this.a();
    }
    
    public void a() {
        this.a[0] = true;
        try {
            this.interrupt();
        }
        catch (Exception ex) {}
    }
    
    public boolean b() {
        return this.a[0];
    }
}
