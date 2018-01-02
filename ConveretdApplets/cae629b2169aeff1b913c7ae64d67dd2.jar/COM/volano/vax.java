// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;

public class vax implements DocumentListener
{
    private JTextArea a;
    private int b;
    private int c;
    
    public vax(final JTextArea a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void insertUpdate(final DocumentEvent documentEvent) {
        SwingUtilities.invokeLater(new va7(this.a, this.b, this.c));
    }
    
    public void removeUpdate(final DocumentEvent documentEvent) {
    }
    
    public void changedUpdate(final DocumentEvent documentEvent) {
    }
}
