// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class ap implements ChangeListener
{
    private int a;
    private final bq b;
    
    private ap(final bq b, final byte b2) {
        this.b = b;
        this.a = -1;
    }
    
    public final void stateChanged(final ChangeEvent changeEvent) {
        int value;
        if ((value = this.b.b().getValue()) > 100) {
            value = 100;
        }
        if (value < 0) {
            value = 0;
        }
        if (this.a != value) {
            if ((this.a = value) < 5) {
                bq.a(this.b, 0);
                return;
            }
            if (value < 35) {
                bq.a(this.b, 1);
                return;
            }
            if (value < 70) {
                bq.a(this.b, 2);
                return;
            }
            bq.a(this.b, 3);
        }
    }
    
    ap(final bq bq) {
        this(bq, (byte)0);
    }
}
