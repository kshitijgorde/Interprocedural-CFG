// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;

final class bY extends AbstractAction
{
    private JTextComponent a;
    
    public bY(final dl dl, final JTextComponent a, final String s) {
        super(s);
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.a instanceof aP) {
            ((aP)this.a).b();
            return;
        }
        final aV av;
        if (this.a instanceof aV && (av = (aV)this.a).a.canUndo()) {
            av.a.undo();
        }
    }
    
    public final boolean isEnabled() {
        if (this.a instanceof aP) {
            return this.a.isEnabled() && this.a.isEditable() && ((aP)this.a).a();
        }
        if (this.a instanceof aV) {
            return this.a.isEnabled() && this.a.isEditable() && ((aV)this.a).a();
        }
        return this.a.isEnabled() && this.a.isEditable();
    }
}
