// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class bo implements ChangeListener
{
    private webphone a;
    
    bo(final webphone a) {
        this.a = a;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.a.jSlider3_stateChanged(changeEvent);
    }
}
