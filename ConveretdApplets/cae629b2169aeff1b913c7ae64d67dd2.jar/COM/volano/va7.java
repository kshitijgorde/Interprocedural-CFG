// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import javax.swing.text.BadLocationException;
import javax.swing.JTextArea;

public class va7 implements Runnable
{
    private JTextArea a;
    private int b;
    private int c;
    
    public va7(final JTextArea a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void run() {
        try {
            final int length = this.a.getText().length();
            if (length > this.b) {
                this.a.replaceRange("", 0, this.a.getLineEndOffset(this.a.getLineOfOffset(this.c + (length - this.b))));
            }
        }
        catch (BadLocationException ex) {
            System.err.println(ex);
        }
    }
}
