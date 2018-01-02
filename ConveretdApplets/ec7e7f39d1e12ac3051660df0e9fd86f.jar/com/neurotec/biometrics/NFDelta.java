// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;

public final class NFDelta
{
    private NFDeltaData data;
    
    public NFDelta(final short x, final short y) {
        this(x, y, -1, -1, -1);
    }
    
    public NFDelta(final short x, final short y, final double angle1, final double angle2, final double angle3) {
        this(x, y, NFRecord.angleFromDoubleN(angle1), NFRecord.angleFromDoubleN(angle2), NFRecord.angleFromDoubleN(angle3));
    }
    
    public NFDelta(final short x, final short y, final int angle1, final int angle2, final int angle3) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data = new NFDeltaData(x, y, angle1, angle2, angle3);
    }
    
    NFDelta() {
        this.data = new NFDeltaData();
    }
    
    NFDelta(final NFDeltaData data) {
        this.data = data;
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
    
    public int getRawAngle1() {
        return this.data.angle1;
    }
    
    public void setRawAngle1(final int angle) {
        this.data.angle1 = angle;
    }
    
    public double getAngle1() {
        return NFRecord.angleToDoubleN(this.data.angle1);
    }
    
    public void setAngle1(final double angle) {
        this.data.angle1 = NFRecord.angleFromDoubleN(angle);
    }
    
    public int getRawAngle2() {
        return this.data.angle2;
    }
    
    public void setRawAngle2(final int angle) {
        this.data.angle2 = angle;
    }
    
    public double getAngle2() {
        return NFRecord.angleToDoubleN(this.data.angle2);
    }
    
    public void setAngle2(final double angle) {
        this.data.angle2 = NFRecord.angleFromDoubleN(angle);
    }
    
    public int getRawAngle3() {
        return this.data.angle3;
    }
    
    public void setRawAngle3(final int angle) {
        this.data.angle3 = angle;
    }
    
    public double getAngle3() {
        return NFRecord.angleToDoubleN(this.data.angle3);
    }
    
    public void setAngle3(final double angle) {
        this.data.angle3 = NFRecord.angleFromDoubleN(angle);
    }
    
    NFDeltaData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{X={%s}, Y={%s}, Angle1={%s}, Angle2={%s}, Angle3={%s}}}", this.getX(), this.getY(), NFRecord.angleToString(this.data.angle1), NFRecord.angleToString(this.data.angle2), NFRecord.angleToString(this.data.angle3));
    }
    
    protected static class NFDeltaData extends Structure
    {
        public short x;
        public short y;
        public int angle1;
        public int angle2;
        public int angle3;
        
        public NFDeltaData() {
        }
        
        public NFDeltaData(final short x, final short y, final int Angle1, final int Angle2, final int Angle3) {
            this.x = x;
            this.y = y;
            this.angle1 = Angle1;
            this.angle2 = Angle2;
            this.angle3 = Angle3;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NFDeltaData newInstance() {
            return new NFDeltaData();
        }
        
        public static class ByReference extends NFDeltaData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NFDeltaData implements Structure.ByValue
        {
        }
    }
}
