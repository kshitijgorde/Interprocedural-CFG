// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IirImageFormat
{
    MONO_RAW(2), 
    RGB_RAW(4), 
    MONO_JPEG(6), 
    RGB_JPEG(8), 
    MONO_JPEG_LS(10), 
    RGB_JPEG_LS(12), 
    MONO_JPEG_2000(14), 
    RGB_JPEG_2000(16);
    
    private int value;
    private static final Map<Integer, IirImageFormat> lookup;
    
    private IirImageFormat(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static IirImageFormat get(final int value) {
        return IirImageFormat.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, IirImageFormat>();
        for (final IirImageFormat s : EnumSet.allOf(IirImageFormat.class)) {
            IirImageFormat.lookup.put(s.getValue(), s);
        }
    }
}
