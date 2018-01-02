// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.awt;

import com.sun.jna.Structure;

public class NRectangleData extends Structure
{
    public int x;
    public int y;
    public int width;
    public int height;
    
    public NRectangleData() {
    }
    
    public NRectangleData(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    protected ByReference newByReference() {
        return new ByReference();
    }
    
    protected ByValue newByValue() {
        return new ByValue();
    }
    
    protected NRectangleData newInstance() {
        return new NRectangleData();
    }
    
    public static class ByReference extends NRectangleData implements Structure.ByReference
    {
    }
    
    public static class ByValue extends NRectangleData implements Structure.ByValue
    {
    }
}
