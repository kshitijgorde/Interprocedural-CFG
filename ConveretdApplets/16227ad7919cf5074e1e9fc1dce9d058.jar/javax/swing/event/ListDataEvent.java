// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventObject;

public class ListDataEvent extends EventObject
{
    public static final int CONTENTS_CHANGED = 0;
    public static final int INTERVAL_ADDED = 1;
    public static final int INTERVAL_REMOVED = 2;
    private int type;
    private int index0;
    private int index1;
    
    public ListDataEvent(final Object o, final int type, final int index0, final int index2) {
        super(o);
        this.type = type;
        this.index0 = index0;
        this.index1 = index2;
    }
    
    public int getIndex0() {
        return this.index0;
    }
    
    public int getIndex1() {
        return this.index1;
    }
    
    public int getType() {
        return this.type;
    }
}
