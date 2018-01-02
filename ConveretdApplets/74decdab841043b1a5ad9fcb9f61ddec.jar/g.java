// 
// Decompiled by Procyon v0.5.30
// 

final class g implements Runnable
{
    private /* synthetic */ RunApplet a;
    
    g(final RunApplet a) {
        this.a = a;
    }
    
    public final void run() {
        this.a.destroy();
    }
}
