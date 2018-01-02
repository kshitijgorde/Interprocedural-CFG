// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;

public final class NFCore
{
    private NFCoreData data;
    
    NFCore() {
        this.data = new NFCoreData();
    }
    
    NFCore(final NFCoreData data) {
        this.data = data;
    }
    
    public NFCore(final short x, final short y) {
        this(x, y, -1);
    }
    
    public NFCore(final short x, final short y, final double angle) {
        this(x, y, NFRecord.angleFromDoubleN(angle));
    }
    
    public NFCore(final short x, final short y, final int angle) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data = new NFCoreData(x, y, angle);
    }
    
    public short getX() {
        return this.data.x;
    }
    
    public void setX(final short x) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        this.data.x = x;
    }
    
    public short getY() {
        return this.data.y;
    }
    
    public void setY(final short y) {
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data.y = y;
    }
    
    public int getRawAngle() {
        return this.data.angle;
    }
    
    public void setRawAngle(final int angle) {
        this.data.angle = angle;
    }
    
    public double getAngle() {
        return NFRecord.angleToDoubleN(this.data.angle);
    }
    
    public void setAngle(final double angle) {
        this.data.angle = NFRecord.angleFromDoubleN(angle);
    }
    
    NFCoreData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{X={%s}, Y={%s}, Angle={%s}}}", this.getX(), this.getY(), NFRecord.angleToString(this.data.angle));
    }
    
    protected static class NFCoreData extends Structure
    {
        public short x;
        public short y;
        public int angle;
        
        public NFCoreData() {
        }
        
        public NFCoreData(final short x, final short y, final int angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NFCoreData newInstance() {
            return new NFCoreData();
        }
        
        public static class ByReference extends NFCoreData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NFCoreData implements Structure.ByValue
        {
        }
    }
}
