// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollBar;
import java.awt.event.ComponentAdapter;

public class vay extends ComponentAdapter
{
    private vaw a;
    private JScrollBar b;
    
    public vay(final vj vj) {
        this.a = (vaw)vj.b.getCaret();
        this.b = vj.a.getVerticalScrollBar();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final Object source = componentEvent.getSource();
        if (source instanceof JScrollPane) {
            this.a.a = true;
            final vaw a = this.a;
            vaw.b(this.b);
        }
        else if (source instanceof JTextArea && this.a.a) {
            final vaw a2 = this.a;
            vaw.b(this.b);
        }
    }
}
