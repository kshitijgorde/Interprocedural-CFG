// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class ai implements ChangeListener
{
    private webphone a;
    
    ai(final webphone a) {
        this.a = a;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.a.jSlider4_stateChanged(changeEvent);
    }
}
