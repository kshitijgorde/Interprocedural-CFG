// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class b5 implements ChangeListener
{
    private e a;
    
    b5(final e a) {
        this.a = a;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.a.if(changeEvent);
    }
}
