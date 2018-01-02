// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;

public final class al extends AbstractAction
{
    private JTextComponent a;
    
    public al(final dl dl, final JTextComponent a, final String s) {
        super(s);
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.selectAll();
        SwingUtilities.invokeLater(new ay(this));
    }
    
    public final boolean isEnabled() {
        return this.a.isEnabled() && this.a.getText().length() > 0;
    }
    
    static JTextComponent a(final al al) {
        return al.a;
    }
}
