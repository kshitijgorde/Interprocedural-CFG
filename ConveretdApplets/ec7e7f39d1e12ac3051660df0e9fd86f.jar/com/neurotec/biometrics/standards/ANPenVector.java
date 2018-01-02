// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANPenVector
{
    private ANPenVectorData data;
    
    ANPenVector() {
        this.data = new ANPenVectorData();
    }
    
    ANPenVector(final ANPenVectorData data) {
        this.data = data;
    }
    
    public ANPenVector(final short x, final short y, final byte pressure) {
        this.data = new ANPenVectorData(x, y, pressure);
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
    
    public byte getPressure() {
        return this.data.pressure;
    }
    
    public void setPressure(final byte value) {
        this.data.pressure = value;
    }
    
    ANPenVectorData getData() {
        return this.data;
    }
    
    public String toString() {
        return "{{X=" + this.getX() + ", Y=" + this.getY() + ", Pressure=" + this.getPressure() + "}}";
    }
    
    protected static class ANPenVectorData extends Structure
    {
        public short x;
        public short y;
        public byte pressure;
        
        public ANPenVectorData() {
        }
        
        public ANPenVectorData(final short x, final short y, final byte pressure) {
            this.x = x;
            this.y = y;
            this.pressure = pressure;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANPenVectorData newInstance() {
            return new ANPenVectorData();
        }
        
        public static class ByReference extends ANPenVectorData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANPenVectorData implements Structure.ByValue
        {
        }
    }
}
