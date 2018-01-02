// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

class xd implements Runnable
{
    private final /* synthetic */ c6 a;
    
    xd(final c6 a) {
        this.a = a;
    }
    
    public void run() {
        this.a.d.repaint();
        this.a.e.repaint();
    }
}
