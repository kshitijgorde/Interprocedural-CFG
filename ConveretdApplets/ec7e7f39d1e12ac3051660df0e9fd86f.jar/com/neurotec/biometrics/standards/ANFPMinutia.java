// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFPMinutia
{
    private ANFPMinutiaData data;
    
    ANFPMinutia() {
        this.data = new ANFPMinutiaData();
    }
    
    ANFPMinutia(final ANFPMinutiaData data) {
        this.data = data;
    }
    
    public ANFPMinutia(final short x, final short y, final short theta, final byte quality, final BdifFPMinutiaType type) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data = new ANFPMinutiaData(x, y, theta, quality, type.getValue());
    }
    
    public ANFPMinutia(final short x, final short y, final short theta, final byte quality) {
        this(x, y, theta, quality, BdifFPMinutiaType.UNSPECIFIED);
    }
    
    public ANFPMinutia(final short x, final short y, final short theta, final BdifFPMinutiaType type) {
        this(x, y, theta, (byte)(-1), type);
    }
    
    public ANFPMinutia(final short x, final short y, final short theta) {
        this(x, y, theta, (byte)(-1), BdifFPMinutiaType.UNSPECIFIED);
    }
    
    public ANFPMinutia(final short x, final short y, final double theta, final byte quality, final BdifFPMinutiaType type) {
        this(x, y, angleFromDouble(theta), quality, type);
    }
    
    public ANFPMinutia(final short x, final short y, final double theta, final byte quality) {
        this(x, y, theta, quality, BdifFPMinutiaType.UNSPECIFIED);
    }
    
    public ANFPMinutia(final short x, final short y, final double theta, final BdifFPMinutiaType type) {
        this(x, y, theta, (byte)(-1), type);
    }
    
    public ANFPMinutia(final short x, final short y, final double theta) {
        this(x, y, theta, (byte)(-1), BdifFPMinutiaType.UNSPECIFIED);
    }
    
    private static double angleToDouble(final short value) {
        return 6.283185307179586 - value * 3.141592653589793 / 180.0;
    }
    
    private static short angleFromDouble(double value) {
        value -= Math.floor(value / 6.283185307179586) * 6.283185307179586;
        value = Math.round((6.283185307179586 - value) * 180.0 / 3.141592653589793);
        return (short)((int)value % 360);
    }
    
    public int getX() {
        return this.data.x;
    }
    
    public void setX(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        this.data.x = value;
    }
    
    public int getY() {
        return this.data.y;
    }
    
    public void setY(final short value) {
        if (value < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data.y = value;
    }
    
    public short getRawTheta() {
        return this.data.theta;
    }
    
    public void setRawTheta(final short value) {
        this.data.theta = value;
    }
    
    public double getTheta() {
        return angleToDouble(this.data.theta);
    }
    
    public void setTheta(final double value) {
        this.data.theta = angleFromDouble(value);
    }
    
    public byte getQuality() {
        return this.data.quality;
    }
    
    public void setQuality(final byte value) {
        this.data.quality = value;
    }
    
    public BdifFPMinutiaType getType() {
        return BdifFPMinutiaType.get(this.data.type);
    }
    
    public void setType(final BdifFPMinutiaType value) {
        this.data.type = value.getValue();
    }
    
    ANFPMinutiaData getData() {
        return this.data;
    }
    
    protected static class ANFPMinutiaData extends Structure
    {
        public int x;
        public int y;
        public short theta;
        public byte quality;
        public int type;
        
        public ANFPMinutiaData() {
        }
        
        public ANFPMinutiaData(final int x, final int y, final short theta, final byte quality, final int type) {
            this.x = x;
            this.y = y;
            this.theta = theta;
            this.quality = quality;
            this.type = type;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFPMinutiaData newInstance() {
            return new ANFPMinutiaData();
        }
        
        static class ByReference extends ANFPMinutiaData implements Structure.ByReference
        {
        }
        
        static class ByValue extends ANFPMinutiaData implements Structure.ByValue
        {
        }
    }
}
