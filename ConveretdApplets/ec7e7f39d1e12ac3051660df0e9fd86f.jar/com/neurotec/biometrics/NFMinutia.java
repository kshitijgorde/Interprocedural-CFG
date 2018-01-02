// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;

public final class NFMinutia
{
    private NFMinutiaData data;
    
    public NFMinutia(final int x, final int y, final NFMinutiaType type, final byte angle) {
        this(x, y, type, angle, 0, 255, 255);
    }
    
    public NFMinutia(final int x, final int y, final NFMinutiaType type, final double angle) {
        this(x, y, type, (byte)angle, 0, 255, 255);
    }
    
    public NFMinutia(final int x, final int y, final NFMinutiaType type, final byte angle, final int quality, final int curvature, final int g) {
        if (x < 0 || x > 65535) {
            throw new IllegalArgumentException("x must be in range [0..65535]");
        }
        if (y < 0 || x > 65535) {
            throw new IllegalArgumentException("x must be in range [0..65535]");
        }
        if (quality < 0 || quality > 255) {
            throw new IllegalArgumentException("quality must be in range [0..255].");
        }
        if (curvature < 0 || curvature > 255) {
            throw new IllegalArgumentException("curvature must be in range [0..255].");
        }
        if (g < 0 || g > 255) {
            throw new IllegalArgumentException("g must be in range [0..255].");
        }
        this.data = new NFMinutiaData((short)x, (short)y, type.getValue(), angle, (byte)quality, (byte)curvature, (byte)g);
    }
    
    public NFMinutia(final int x, final int y, final NFMinutiaType type, final double angle, final int quality, final int curvature, final int g) {
        this(x, y, type, NFRecord.angleFromDouble(angle), quality, curvature, g);
    }
    
    NFMinutia(final NFMinutiaData data) {
        this.data = data;
    }
    
    public NFMinutia() {
        this.data = new NFMinutiaData();
    }
    
    public int getX() {
        return this.data.x & 0xFFFF;
    }
    
    public void setX(final int value) {
        if (value < 0 || value > 65535) {
            throw new IllegalArgumentException("value must be in range [0..65535]");
        }
        this.data.x = (short)value;
    }
    
    public int getY() {
        return this.data.y & 0xFFFF;
    }
    
    public void setY(final int value) {
        if (value < 0 || value > 65535) {
            throw new IllegalArgumentException("value must be in range [0..65535]");
        }
        this.data.y = (short)value;
    }
    
    public NFMinutiaType getType() {
        return NFMinutiaType.get(this.data.type);
    }
    
    public void setType(final NFMinutiaType type) {
        this.data.type = type.getValue();
    }
    
    public byte getRawAngle() {
        return this.data.angle;
    }
    
    public void setRawAngle(final byte angle) {
        this.data.angle = angle;
    }
    
    public double getAngle() {
        return NFRecord.angleToDouble(this.data.angle);
    }
    
    public void setAngle(final double angle) {
        this.data.angle = NFRecord.angleFromDouble(angle);
    }
    
    public int getQuality() {
        return this.data.quality & 0xFF;
    }
    
    public void setQuality(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.data.quality = (byte)value;
    }
    
    public int getCurvature() {
        return this.data.curvature & 0xFF;
    }
    
    public void setCurvature(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.data.curvature = (byte)value;
    }
    
    public int getG() {
        return this.data.g & 0xFF;
    }
    
    public void setG(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.data.g = (byte)value;
    }
    
    NFMinutiaData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{X={%s}, Y={%s}, Type={%s}, Angle={%s}, Quality={%s}, Curvature={%s}, G={%s}}}", this.getX(), this.getY(), this.getType(), NFRecord.angleToString(this.data.angle), (this.getQuality() > 100) ? String.valueOf(this.getQuality()) : (String.valueOf(this.getQuality()) + '%'), this.getCurvature(), this.getG());
    }
    
    protected static class NFMinutiaData extends Structure
    {
        public short x;
        public short y;
        public int type;
        public byte angle;
        public byte quality;
        public byte curvature;
        public byte g;
        
        public NFMinutiaData() {
        }
        
        NFMinutiaData(final short x, final short y, final int type, final byte angle, final byte quality, final byte curvature, final byte g) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.angle = angle;
            this.quality = quality;
            this.curvature = curvature;
            this.g = g;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NFMinutiaData newInstance() {
            return new NFMinutiaData();
        }
        
        static class ByReference extends NFMinutiaData implements Structure.ByReference
        {
        }
        
        static class ByValue extends NFMinutiaData implements Structure.ByValue
        {
        }
    }
}
