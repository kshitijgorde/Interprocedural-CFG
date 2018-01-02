// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import jagoclient.Global;
import java.awt.event.ItemListener;
import java.awt.Checkbox;

public class CheckboxAction extends Checkbox
{
    public CheckboxAction(final DoActionListener doActionListener, final String s) {
        super(s);
        this.addItemListener(new CheckboxActionTranslator(this, doActionListener, s));
        this.setFont(Global.SansSerif);
    }
    
    public CheckboxAction(final DoActionListener doActionListener, final String s, final String s2) {
        super(s);
        this.addItemListener(new CheckboxActionTranslator(this, doActionListener, s2));
    }
}
