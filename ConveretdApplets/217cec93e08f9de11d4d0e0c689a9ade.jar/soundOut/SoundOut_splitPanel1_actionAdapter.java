// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SoundOut_splitPanel1_actionAdapter implements ActionListener
{
    SoundOut adaptee;
    
    SoundOut_splitPanel1_actionAdapter(final SoundOut adaptee) {
        this.adaptee = adaptee;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.adaptee.splitPanel1_actionPerformed(actionEvent);
    }
}
