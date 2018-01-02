// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class SoundOut_sndCKBox_itemAdapter implements ItemListener
{
    SoundOut adaptee;
    
    SoundOut_sndCKBox_itemAdapter(final SoundOut adaptee) {
        this.adaptee = adaptee;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.adaptee.sndCKBox_itemStateChanged(itemEvent);
    }
}
