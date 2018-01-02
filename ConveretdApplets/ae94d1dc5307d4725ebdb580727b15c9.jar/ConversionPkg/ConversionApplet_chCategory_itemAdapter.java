// 
// Decompiled by Procyon v0.5.30
// 

package ConversionPkg;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class ConversionApplet_chCategory_itemAdapter implements ItemListener
{
    ConversionApplet adaptee;
    
    ConversionApplet_chCategory_itemAdapter(final ConversionApplet adaptee) {
        this.adaptee = adaptee;
    }
    
    public void itemStateChanged(final ItemEvent e) {
        this.adaptee.chCategory_itemStateChanged(e);
    }
}
