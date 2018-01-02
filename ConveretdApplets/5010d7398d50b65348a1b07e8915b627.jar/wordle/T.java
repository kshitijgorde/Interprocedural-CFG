// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;

final class T extends FocusAdapter
{
    private final /* synthetic */ JTextField a;
    
    T(final JTextField a) {
        this.a = a;
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
        this.a.selectAll();
    }
}
