// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class PropertyChangeEventMulticaster extends AWTEventMulticaster implements PropertyChangeListener
{
    protected PropertyChangeEventMulticaster(final PropertyChangeListener a, final PropertyChangeListener b) {
        super(a, b);
    }
    
    public static PropertyChangeListener add(final PropertyChangeListener a, final PropertyChangeListener b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return new PropertyChangeEventMulticaster(a, b);
    }
    
    public static PropertyChangeListener remove(final PropertyChangeListener a, final PropertyChangeListener b) {
        return (PropertyChangeListener)AWTEventMulticaster.removeInternal(a, b);
    }
    
    public void updTotals(final PropertyChangeEvent event) {
        ((PropertyChangeListener)super.a).updTotals(event);
        ((PropertyChangeListener)super.b).updTotals(event);
    }
}
