// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class av implements ChangeListener
{
    private e a;
    
    av(final e a) {
        this.a = a;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.a.a(changeEvent);
    }
}
