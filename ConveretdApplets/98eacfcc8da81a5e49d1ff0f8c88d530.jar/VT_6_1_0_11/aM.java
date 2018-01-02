// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;

public class aM extends JInternalFrame
{
    private T a;
    
    public aM() {
        (this.a = new T((JComponent)null)).a(true);
        this.a.setVisible(false);
        this.setVisible(false);
    }
    
    public final T e() {
        return this.a;
    }
    
    public void setVisible(final boolean b) {
        super.setVisible(b);
        if (this.a != null) {
            this.a.setVisible(b);
        }
    }
}
