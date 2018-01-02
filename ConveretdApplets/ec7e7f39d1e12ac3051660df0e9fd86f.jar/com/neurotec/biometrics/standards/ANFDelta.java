// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFDelta
{
    private ANFDeltaData data;
    
    ANFDelta() {
        this.data = new ANFDeltaData();
    }
    
    ANFDelta(final ANFDeltaData data) {
        this.data = data;
    }
    
    public ANFDelta(final short x, final short y) {
        this.data = new ANFDeltaData(x, y);
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
    
    ANFDeltaData getData() {
        return this.data;
    }
    
    public String toString() {
        return "{{X=" + this.getX() + ", Y=" + this.getY() + "}}";
    }
    
    protected static class ANFDeltaData extends Structure
    {
        public short x;
        public short y;
        
        public ANFDeltaData() {
        }
        
        public ANFDeltaData(final short x, final short y) {
            this.x = x;
            this.y = y;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFDeltaData newInstance() {
            return new ANFDeltaData();
        }
        
        public static class ByReference extends ANFDeltaData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFDeltaData implements Structure.ByValue
        {
        }
    }
}
