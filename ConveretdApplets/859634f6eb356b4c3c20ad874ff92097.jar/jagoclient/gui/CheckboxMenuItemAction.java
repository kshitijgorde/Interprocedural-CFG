// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import jagoclient.Global;
import java.awt.event.ItemListener;
import java.awt.CheckboxMenuItem;

public class CheckboxMenuItemAction extends CheckboxMenuItem
{
    public CheckboxMenuItemAction(final DoActionListener doActionListener, final String s) {
        super(s);
        this.addItemListener(new CheckboxTranslator(this, doActionListener, s));
        this.setFont(Global.SansSerif);
    }
}
