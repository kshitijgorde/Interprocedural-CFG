// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;

public class JCAdjustmentEvent extends AdjustmentEvent
{
    public static final int ADJUSTMENT_VALUE_CHANGED = 601;
    public static final int UNIT_INCREMENT = 1;
    public static final int UNIT_DECREMENT = 2;
    public static final int BLOCK_DECREMENT = 3;
    public static final int BLOCK_INCREMENT = 4;
    public static final int TRACK = 5;
    private static Adjustable adj;
    
    public JCAdjustmentEvent(final Object source, final int n, final int n2, final int n3) {
        super(JCAdjustmentEvent.adj, n, n2, n3);
        super.source = source;
    }
    
    static {
        JCAdjustmentEvent.adj = new AdjustableObject();
    }
}
