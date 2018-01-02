// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

class ve implements Runnable
{
    private final /* synthetic */ do a;
    
    ve(final do a) {
        this.a = a;
    }
    
    public void run() {
        this.a.paint(this.a.getGraphics());
    }
}
