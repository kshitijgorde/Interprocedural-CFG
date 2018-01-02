// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import jagoclient.Global;
import java.awt.event.ActionListener;

public class FormTextField extends GrayTextField implements DoActionListener
{
    public FormTextField(final String text) {
        this.addActionListener(new TextFieldActionListener(this, ""));
        this.setFont(Global.SansSerif);
        this.setText(text);
    }
    
    public void doAction(final String s) {
        this.transferFocus();
    }
    
    public void itemAction(final String s, final boolean b) {
    }
}
