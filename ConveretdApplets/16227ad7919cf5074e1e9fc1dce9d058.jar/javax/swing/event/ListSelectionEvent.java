// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventObject;

public class ListSelectionEvent extends EventObject
{
    private int firstIndex;
    private int lastIndex;
    private boolean isAdjusting;
    
    public ListSelectionEvent(final Object o, final int firstIndex, final int lastIndex, final boolean isAdjusting) {
        super(o);
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.isAdjusting = isAdjusting;
    }
    
    public int getFirstIndex() {
        return this.firstIndex;
    }
    
    public int getLastIndex() {
        return this.lastIndex;
    }
    
    public boolean getValueIsAdjusting() {
        return this.isAdjusting;
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + "[" + (" source=" + this.getSource() + " firstIndex= " + this.firstIndex + " lastIndex= " + this.lastIndex + " isAdjusting= " + this.isAdjusting + " ") + "]";
    }
}
