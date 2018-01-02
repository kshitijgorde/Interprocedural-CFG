// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

final class b implements Runnable
{
    private /* synthetic */ Runnable a;
    private /* synthetic */ l b;
    
    b(final l b, final Runnable a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        try {
            Thread.sleep(30758400000L);
        }
        catch (InterruptedException ex) {
            f.b("Watch thread is interrupted and ignore is: " + this.b.a);
            if (!this.b.a) {
                this.a.run();
            }
        }
    }
}
