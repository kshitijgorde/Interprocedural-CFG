// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifFPPosition
{
    UNKNOWN(0), 
    RIGHT_THUMB(1), 
    RIGHT_INDEX_FINGER(2), 
    RIGHT_MIDDLE_FINGER(3), 
    RIGHT_RING_FINGER(4), 
    RIGHT_LITTLE_FINGER(5), 
    LEFT_THUMB(6), 
    LEFT_INDEX_FINGER(7), 
    LEFT_MIDDLE_FINGER(8), 
    LEFT_RING_FINGER(9), 
    LEFT_LITTLE_FINGER(10), 
    PLAIN_RIGHT_THUMB(11), 
    PLAIN_LEFT_THUMB(12), 
    PLAIN_RIGHT_FOUR_FINGERS(13), 
    PLAIN_LEFT_FOUR_FINGERS(14), 
    PLAIN_THUMBS(15), 
    MAJOR_CASE(19), 
    UNKNOWN_PALM(20), 
    RIGHT_FULL_PALM(21), 
    RIGHT_WRITERS_PALM(22), 
    LEFT_FULL_PALM(23), 
    LEFT_WRITERS_PALM(24), 
    RIGHT_LOWER_PALM(25), 
    RIGHT_UPPER_PALM(26), 
    LEFT_LOWER_PALM(27), 
    LEFT_UPPER_PALM(28), 
    RIGHT_OTHER(29), 
    LEFT_OTHER(30), 
    RIGHT_INTERDIGITAL(31), 
    RIGHT_THENAR(32), 
    RIGHT_HYPOTHENAR(33), 
    LEFT_INTERDIGITAL(34), 
    LEFT_THENAR(35), 
    LEFT_HYPOTHENAR(36), 
    RIGHT_INDEX_MIDDLE(40), 
    RIGHT_MIDDLE_RING(41), 
    RIGHT_RING_LITTLE(42), 
    LEFT_INDEX_MIDDLE(43), 
    LEFT_MIDDLE_RING(44), 
    LEFT_RING_LITTLE(45), 
    RIGHT_INDEX_LEFT_INDEX(46), 
    RIGHT_INDEX_MIDDLE_RING(47), 
    RIGHT_MIDDLE_RING_LITTLE(48), 
    LEFT_INDEX_MIDDLE_RING(49), 
    LEFT_MIDDLE_RING_LITTLE(50);
    
    private int value;
    private static final Map<Integer, BdifFPPosition> lookup;
    
    private BdifFPPosition(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifFPPosition get(final int value) {
        return BdifFPPosition.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifFPPosition>();
        for (final BdifFPPosition s : EnumSet.allOf(BdifFPPosition.class)) {
            BdifFPPosition.lookup.put(s.getValue(), s);
        }
    }
}
