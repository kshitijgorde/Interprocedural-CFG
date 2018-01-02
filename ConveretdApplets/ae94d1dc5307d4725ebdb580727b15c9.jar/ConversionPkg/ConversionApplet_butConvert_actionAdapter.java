// 
// Decompiled by Procyon v0.5.30
// 

package ConversionPkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConversionApplet_butConvert_actionAdapter implements ActionListener
{
    ConversionApplet adaptee;
    
    ConversionApplet_butConvert_actionAdapter(final ConversionApplet adaptee) {
        this.adaptee = adaptee;
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.adaptee.butConvert_actionPerformed(e);
    }
}
