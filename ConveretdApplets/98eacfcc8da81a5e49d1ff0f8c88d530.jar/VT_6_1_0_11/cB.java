// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;

final class cB extends AbstractAction
{
    private JTextComponent a;
    
    public cB(final dl dl, final JTextComponent a, final String s) {
        super(s);
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.replaceSelection(null);
    }
    
    public final boolean isEnabled() {
        return this.a.isEditable() && this.a.isEnabled() && this.a.getSelectedText() != null;
    }
}
