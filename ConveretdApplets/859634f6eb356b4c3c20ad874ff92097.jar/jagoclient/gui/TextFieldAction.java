// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.ActionListener;
import jagoclient.Global;
import java.awt.TextField;

public class TextFieldAction extends TextField
{
    TextFieldActionListener T;
    
    public TextFieldAction(final DoActionListener doActionListener, final String s, final String text) {
        this.setBackground(Global.gray);
        this.setFont(Global.SansSerif);
        this.addActionListener(this.T = new TextFieldActionListener(doActionListener, s));
        this.setText(text);
    }
    
    public TextFieldAction(final DoActionListener doActionListener, final String s) {
        this.setBackground(Global.gray);
        this.setFont(Global.SansSerif);
        this.addActionListener(this.T = new TextFieldActionListener(doActionListener, s));
    }
    
    public TextFieldAction(final DoActionListener doActionListener, final String s, final int n) {
        super(n);
        this.setBackground(Global.gray);
        this.setFont(Global.SansSerif);
        this.addActionListener(this.T = new TextFieldActionListener(doActionListener, s));
    }
    
    public TextFieldAction(final DoActionListener doActionListener, final String s, final String s2, final int n) {
        super(s2, n);
        this.setFont(Global.SansSerif);
        this.setBackground(Global.gray);
        this.addActionListener(this.T = new TextFieldActionListener(doActionListener, s));
    }
}
