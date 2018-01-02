// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import jagoclient.Global;
import java.awt.event.ItemListener;
import java.awt.Choice;

public class ChoiceAction extends Choice
{
    public ChoiceAction(final DoActionListener doActionListener, final String s) {
        this.addItemListener(new ChoiceTranslator(this, doActionListener, s));
        this.setFont(Global.SansSerif);
    }
}
