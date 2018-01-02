// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.awt;

import com.sun.jna.Structure;

public class NDimensionData extends Structure
{
    public int height;
    public int width;
    
    public NDimensionData() {
    }
    
    public NDimensionData(final int width, final int height) {
        this.height = height;
        this.width = width;
    }
    
    protected ByReference newByReference() {
        return new ByReference();
    }
    
    protected ByValue newByValue() {
        return new ByValue();
    }
    
    protected NDimensionData newInstance() {
        return new NDimensionData();
    }
    
    static class ByReference extends NDimensionData implements Structure.ByReference
    {
    }
    
    static class ByValue extends NDimensionData implements Structure.ByValue
    {
    }
}
