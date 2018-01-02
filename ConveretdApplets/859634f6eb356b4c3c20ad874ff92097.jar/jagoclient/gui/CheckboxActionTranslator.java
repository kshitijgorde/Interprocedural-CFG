// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.ItemEvent;
import java.awt.Checkbox;
import java.awt.event.ItemListener;

class CheckboxActionTranslator implements ItemListener
{
    DoActionListener C;
    String S;
    public Checkbox CB;
    
    public CheckboxActionTranslator(final Checkbox cb, final DoActionListener c, final String s) {
        this.C = c;
        this.S = s;
        this.CB = cb;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.C.itemAction(this.S, this.CB.getState());
    }
}
