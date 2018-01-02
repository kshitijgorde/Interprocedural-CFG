// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class cj implements ChangeListener
{
    private final C a;
    
    private cj(final C a, final byte b) {
        this.a = a;
    }
    
    public final void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource() == this.a.b() && this.a.g()) {
            final cJ a;
            if ((a = cJ.a) != null) {
                a.a(this.a.b().getValue());
                return;
            }
            C.a(this.a, true);
        }
    }
    
    cj(final C c) {
        this(c, (byte)0);
    }
}
