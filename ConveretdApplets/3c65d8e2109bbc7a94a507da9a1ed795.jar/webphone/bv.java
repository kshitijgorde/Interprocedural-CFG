// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class bv implements ChangeListener
{
    private webphone a;
    
    bv(final webphone a) {
        this.a = a;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.a.jSlider1_stateChanged(changeEvent);
    }
}
