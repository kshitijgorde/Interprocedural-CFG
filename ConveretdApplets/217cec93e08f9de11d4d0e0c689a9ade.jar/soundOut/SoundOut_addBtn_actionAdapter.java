// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SoundOut_addBtn_actionAdapter implements ActionListener
{
    SoundOut adaptee;
    
    SoundOut_addBtn_actionAdapter(final SoundOut adaptee) {
        this.adaptee = adaptee;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.adaptee.addBtn_actionPerformed(actionEvent);
    }
}
