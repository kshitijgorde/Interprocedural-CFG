// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class FmrMinutia
{
    private FmrMinutiaData data;
    
    FmrMinutia() {
        this.data = new FmrMinutiaData();
    }
    
    FmrMinutia(final FmrMinutiaData data) {
        this.data = data;
    }
    
    public FmrMinutia(final short x, final short y, final BdifFPMinutiaType type, final byte angle) {
        this(x, y, type, angle, (byte)0);
    }
    
    public FmrMinutia(final short x, final short y, final BdifFPMinutiaType type, final double angle, final BdifStandard standard) {
        this(x, y, type, angle, (byte)0, standard);
    }
    
    public FmrMinutia(final short x, final short y, final BdifFPMinutiaType type, final byte angle, final byte quality) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data = new FmrMinutiaData(x, y, type.getValue(), angle, quality);
    }
    
    public FmrMinutia(final short x, final short y, final BdifFPMinutiaType type, final double angle, final byte quality, final BdifStandard standard) {
        this(x, y, type, FmrFingerView.angleFromDouble(angle, standard), quality);
    }
    
    public double getAngle(final BdifStandard standard) {
        return FmrFingerView.angleToDouble(this.data.angle, standard);
    }
    
    public void setAngle(final double value, final BdifStandard standard) {
        this.data.angle = FmrFingerView.angleFromDouble(value, standard);
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
    
    public BdifFPMinutiaType getType() {
        return BdifFPMinutiaType.get(this.data.type);
    }
    
    public void setType(final BdifFPMinutiaType type) {
        this.data.type = type.getValue();
    }
    
    public byte getRawAngle() {
        return this.data.angle;
    }
    
    public void setRawAngle(final byte angle) {
        this.data.angle = angle;
    }
    
    public double getAnsiAngle() {
        return FmrFingerView.angleToDouble(this.data.angle, BdifStandard.ANSI);
    }
    
    public void setAnsiAngle(final double angle) {
        this.data.angle = FmrFingerView.angleFromDouble(angle, BdifStandard.ANSI);
    }
    
    public double getIsoAngle() {
        return FmrFingerView.angleToDouble(this.data.angle, BdifStandard.ISO);
    }
    
    public void setIsoAngle(final double angle) {
        this.data.angle = FmrFingerView.angleFromDouble(angle, BdifStandard.ISO);
    }
    
    public byte getQuality() {
        return this.data.quality;
    }
    
    public void setQuality(final byte quality) {
        this.data.quality = quality;
    }
    
    FmrMinutiaData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{X={%s}, Y={%s}, Type={%s}, AnsiAngle={%s}, IsoAngle={%s}, Quality={%s}}}", this.getX(), this.getY(), this.getType(), FmrFingerView.angleToString(this.data.angle, BdifStandard.ANSI), FmrFingerView.angleToString(this.data.angle, BdifStandard.ISO), (this.getQuality() > 100) ? String.valueOf(this.getQuality()) : (String.valueOf(this.getQuality()) + '%'));
    }
    
    public String toString(final BdifStandard standard) {
        return String.format("{{X={%s}, Y={%s}, Type={%s}, Angle={%s}, Quality={%s}}}", this.getX(), this.getY(), this.getType(), FmrFingerView.angleToString(this.data.angle, standard), (this.getQuality() > 100) ? String.valueOf(this.getQuality()) : (String.valueOf(this.getQuality()) + '%'));
    }
    
    protected static class FmrMinutiaData extends Structure
    {
        public short x;
        public short y;
        public int type;
        public byte angle;
        public byte quality;
        
        public FmrMinutiaData() {
        }
        
        public FmrMinutiaData(final short x, final short y, final int type, final byte angle, final byte quality) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.angle = angle;
            this.quality = quality;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected FmrMinutiaData newInstance() {
            return new FmrMinutiaData();
        }
        
        static class ByReference extends FmrMinutiaData implements Structure.ByReference
        {
        }
        
        static class ByValue extends FmrMinutiaData implements Structure.ByValue
        {
        }
    }
}
