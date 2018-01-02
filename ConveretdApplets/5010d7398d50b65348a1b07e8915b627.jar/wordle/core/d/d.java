// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import javax.swing.event.DocumentListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

final class d implements FocusListener
{
    private /* synthetic */ g a;
    
    d(final g a) {
        this.a = a;
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
        this.a.a.selectAll();
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        this.a.b();
    }
}
