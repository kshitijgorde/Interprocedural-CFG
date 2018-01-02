// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class ac implements ChangeListener
{
    private webphone a;
    
    ac(final webphone a) {
        this.a = a;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.a.jSlider2_stateChanged(changeEvent);
    }
}
