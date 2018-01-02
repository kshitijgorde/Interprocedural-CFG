// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class a5 implements PropertyChangeListener
{
    private webphone a;
    
    a5(final webphone a) {
        this.a = a;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.a.jSlider1_propertyChange(propertyChangeEvent);
    }
}
