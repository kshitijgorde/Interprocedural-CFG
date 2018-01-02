// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFCore
{
    private ANFCoreData data;
    
    ANFCore() {
        this.data = new ANFCoreData();
    }
    
    ANFCore(final ANFCoreData data) {
        this.data = data;
    }
    
    public ANFCore(final short x, final short y) {
        this.data = new ANFCoreData(x, y);
    }
    
    public short getX() {
        return this.data.x;
    }
    
    public void setX(final short value) {
        if (value < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        this.data.x = value;
    }
    
    public short getY() {
        return this.data.y;
    }
    
    public void setY(final short value) {
        if (value < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data.y = value;
    }
    
    ANFCoreData getData() {
        return this.data;
    }
    
    public String toString() {
        return "{{X=" + this.getX() + ", Y=" + this.getY() + "}}";
    }
    
    protected static class ANFCoreData extends Structure
    {
        public short x;
        public short y;
        
        public ANFCoreData() {
        }
        
        public ANFCoreData(final short x, final short y) {
            this.x = x;
            this.y = y;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFCoreData newInstance() {
            return new ANFCoreData();
        }
        
        public static class ByReference extends ANFCoreData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFCoreData implements Structure.ByValue
        {
        }
    }
}
