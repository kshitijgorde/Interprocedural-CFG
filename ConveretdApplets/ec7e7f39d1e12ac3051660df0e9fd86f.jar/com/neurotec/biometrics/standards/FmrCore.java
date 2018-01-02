// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class FmrCore
{
    private FmrCoreData data;
    
    FmrCore() {
        this.data = new FmrCoreData();
    }
    
    FmrCore(final FmrCoreData data) {
        this.data = data;
    }
    
    public FmrCore(final short x, final short y) {
        this(x, y, -1);
    }
    
    public FmrCore(final short x, final short y, final double angle, final BdifStandard standard) {
        this(x, y, FmrFingerView.angleFromDoubleN(angle, standard));
    }
    
    public FmrCore(final short x, final short y, final int angle) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data = new FmrCoreData(x, y, angle);
    }
    
    public double getAngle(final BdifStandard standard) {
        return FmrFingerView.angleToDoubleN(this.data.angle, standard);
    }
    
    public void setAngle(final double value, final BdifStandard standard) {
        this.data.angle = FmrFingerView.angleFromDoubleN(value, standard);
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
    
    public void setRawAngle(final byte angle) {
        this.data.angle = angle;
    }
    
    public double getAnsiAngle() {
        return FmrFingerView.angleToDoubleN(this.data.angle, BdifStandard.ANSI);
    }
    
    public void setAnsiAngle(final double angle) {
        this.data.angle = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ANSI);
    }
    
    public double getIsoAngle() {
        return FmrFingerView.angleToDoubleN(this.data.angle, BdifStandard.ISO);
    }
    
    public void setIsoAngle(final double angle) {
        this.data.angle = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ISO);
    }
    
    FmrCoreData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{X={%s}, Y={%s}, AnsiAngle={%s}, IsoAngle={%s}}}", this.getX(), this.getY(), FmrFingerView.angleToString(this.data.angle, BdifStandard.ANSI), FmrFingerView.angleToString(this.data.angle, BdifStandard.ISO));
    }
    
    public String toString(final BdifStandard standard) {
        return String.format("{{X={%s}, Y={%s}, Angle={%s}}}", this.getX(), this.getY(), FmrFingerView.angleToString(this.data.angle, standard));
    }
    
    protected static class FmrCoreData extends Structure
    {
        public short x;
        public short y;
        public int angle;
        
        public FmrCoreData() {
        }
        
        public FmrCoreData(final short x, final short y, final int angle) {
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
        
        protected FmrCoreData newInstance() {
            return new FmrCoreData();
        }
        
        public static class ByReference extends FmrCoreData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends FmrCoreData implements Structure.ByValue
        {
        }
    }
}
