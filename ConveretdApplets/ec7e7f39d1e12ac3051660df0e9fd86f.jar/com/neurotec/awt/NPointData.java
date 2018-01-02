// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.awt;

import com.sun.jna.Structure;

public class NPointData extends Structure
{
    public int x;
    public int y;
    
    public NPointData() {
    }
    
    public NPointData(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    protected ByReference newByReference() {
        return new ByReference();
    }
    
    protected ByValue newByValue() {
        return new ByValue();
    }
    
    protected NPointData newInstance() {
        return new NPointData();
    }
    
    public static class ByReference extends NPointData implements Structure.ByReference
    {
    }
    
    public static class ByValue extends NPointData implements Structure.ByValue
    {
    }
}
