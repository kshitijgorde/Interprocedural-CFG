import java.beans.PropertyChangeEvent;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_aI implements Runnable
{
    private /* synthetic */ PropertyChangeEvent a;
    private /* synthetic */ rp_eW a;
    
    rp_aI(final rp_eW a, final PropertyChangeEvent a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final void run() {
        this.a.firePropertyChange(this.a);
    }
}
