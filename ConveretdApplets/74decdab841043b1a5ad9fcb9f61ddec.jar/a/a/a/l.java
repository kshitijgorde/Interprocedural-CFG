// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public final class l
{
    private boolean a;
    private Thread b;
    
    public l(final Runnable runnable) {
        this.a = false;
        (this.b = new Thread(new b(this, runnable))).start();
    }
    
    public final void a() {
        this.a = true;
        this.b.interrupt();
    }
}
