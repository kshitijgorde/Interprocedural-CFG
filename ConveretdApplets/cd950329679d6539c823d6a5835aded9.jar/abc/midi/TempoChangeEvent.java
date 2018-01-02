// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import java.util.EventObject;

public class TempoChangeEvent extends EventObject
{
    private int m_newTempoValue;
    
    public TempoChangeEvent(final Object source, final int newTempoValue) {
        super(source);
        this.m_newTempoValue = 0;
        this.m_newTempoValue = newTempoValue;
    }
    
    public int getNewTempoValue() {
        return this.m_newTempoValue;
    }
}
