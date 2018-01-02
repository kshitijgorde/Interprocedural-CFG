// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import jagoclient.Global;
import java.awt.TextField;

public class GrayTextField extends TextField
{
    public GrayTextField(final String s) {
        super(s, 25);
        this.setBackground(Global.gray);
        this.setFont(Global.SansSerif);
    }
    
    public GrayTextField() {
        super(25);
        this.setBackground(Global.gray);
        this.setFont(Global.SansSerif);
    }
    
    public GrayTextField(final int n) {
        super(n);
        this.setBackground(Global.gray);
        this.setFont(Global.SansSerif);
    }
}
