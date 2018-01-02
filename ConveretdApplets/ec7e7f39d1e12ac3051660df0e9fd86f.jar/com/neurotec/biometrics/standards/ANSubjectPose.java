// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANSubjectPose
{
    UNSPECIFIED(0), 
    FULL_FACE_FRONTAL(1), 
    RIGHT_PROFILE(2), 
    LEFT_PROFILE(3), 
    ANGLED(4), 
    DETERMINED_3D(5);
    
    private int value;
    private static final Map<Integer, ANSubjectPose> lookup;
    
    private ANSubjectPose(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANSubjectPose get(final int value) {
        return ANSubjectPose.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANSubjectPose>();
        for (final ANSubjectPose s : EnumSet.allOf(ANSubjectPose.class)) {
            ANSubjectPose.lookup.put(s.getValue(), s);
        }
    }
}
