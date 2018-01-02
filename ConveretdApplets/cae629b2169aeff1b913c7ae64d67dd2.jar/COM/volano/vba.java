// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class vba implements Runnable
{
    private JTextArea a;
    private vaw b;
    private JScrollBar c;
    private String d;
    
    public vba(final vj vj, final String d) {
        this.a = vj.b;
        this.b = (vaw)this.a.getCaret();
        this.c = vj.a.getVerticalScrollBar();
        this.d = d;
    }
    
    public void run() {
        this.b.a(this.c);
        this.a.append(((this.a.getText().length() > 0) ? "\n" : "") + this.d);
    }
}
