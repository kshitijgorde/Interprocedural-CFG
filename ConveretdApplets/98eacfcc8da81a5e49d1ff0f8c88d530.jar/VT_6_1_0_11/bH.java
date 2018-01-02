// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Component;
import com.hw.client.util.c;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class bH implements ChangeListener
{
    private F a;
    
    public bH(final F f, final F a) {
        this.a = a;
    }
    
    public final void stateChanged(final ChangeEvent changeEvent) {
        c.a(this.a);
    }
}
