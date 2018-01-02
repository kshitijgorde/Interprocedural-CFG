// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

final class e implements DocumentListener
{
    private /* synthetic */ g a;
    
    e(final g a) {
        this.a = a;
    }
    
    public final void removeUpdate(final DocumentEvent documentEvent) {
        g.b(this.a);
    }
    
    public final void insertUpdate(final DocumentEvent documentEvent) {
        g.b(this.a);
    }
    
    public final void changedUpdate(final DocumentEvent documentEvent) {
        g.b(this.a);
    }
}
