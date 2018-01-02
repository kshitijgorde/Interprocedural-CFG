// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.ItemEvent;
import java.awt.Choice;
import java.awt.event.ItemListener;

class ChoiceTranslator implements ItemListener
{
    DoActionListener C;
    String S;
    public Choice Ch;
    
    public ChoiceTranslator(final Choice ch, final DoActionListener c, final String s) {
        this.C = c;
        this.S = s;
        this.Ch = ch;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.C.itemAction(this.S, itemEvent.getStateChange() == 1);
    }
}
