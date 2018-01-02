// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventObject;

public abstract class CaretEvent extends EventObject
{
    public CaretEvent(final Object o) {
        super(o);
    }
    
    public abstract int getDot();
    
    public abstract int getMark();
}
