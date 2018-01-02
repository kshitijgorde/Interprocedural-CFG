// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class FmrDelta
{
    private FmrDeltaData data;
    
    FmrDelta() {
        this.data = new FmrDeltaData();
    }
    
    FmrDelta(final FmrDeltaData data) {
        this.data = data;
    }
    
    public FmrDelta(final short x, final short y) {
        this(x, y, -1, -1, -1);
    }
    
    public FmrDelta(final short x, final short y, final double angle1, final double angle2, final double angle3, final BdifStandard standard) {
        this(x, y, FmrFingerView.angleFromDoubleN(angle1, standard), FmrFingerView.angleFromDoubleN(angle2, standard), FmrFingerView.angleFromDoubleN(angle3, standard));
    }
    
    public FmrDelta(final short x, final short y, final int angle1, final int angle2, final int angle3) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data = new FmrDeltaData(x, y, angle1, angle2, angle3);
    }
    
    public double getAngle1(final BdifStandard standard) {
        return FmrFingerView.angleToDoubleN(this.data.angle1, standard);
    }
    
    public void setAngle1(final double value, final BdifStandard standard) {
        this.data.angle1 = FmrFingerView.angleFromDoubleN(value, standard);
    }
    
    public double getAngle2(final BdifStandard standard) {
        return FmrFingerView.angleToDoubleN(this.data.angle2, standard);
    }
    
    public void setAngle2(final double value, final BdifStandard standard) {
        this.data.angle2 = FmrFingerView.angleFromDoubleN(value, standard);
    }
    
    public double getAngle3(final BdifStandard standard) {
        return FmrFingerView.angleToDoubleN(this.data.angle3, standard);
    }
    
    public void setAngle3(final double value, final BdifStandard standard) {
        this.data.angle3 = FmrFingerView.angleFromDoubleN(value, standard);
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
    
    public double getAnsiAngle1() {
        return FmrFingerView.angleToDoubleN(this.data.angle1, BdifStandard.ANSI);
    }
    
    public void setAnsiAngle1(final double angle) {
        this.data.angle1 = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ANSI);
    }
    
    public double getIsoAngle1() {
        return FmrFingerView.angleToDoubleN(this.data.angle1, BdifStandard.ISO);
    }
    
    public void setIsoAngle1(final double angle) {
        this.data.angle1 = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ISO);
    }
    
    public int getRawAngle2() {
        return this.data.angle2;
    }
    
    public void setRawAngle2(final int angle) {
        this.data.angle2 = angle;
    }
    
    public double getAnsiAngle2() {
        return FmrFingerView.angleToDoubleN(this.data.angle2, BdifStandard.ANSI);
    }
    
    public void setAnsiAngle2(final double angle) {
        this.data.angle2 = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ANSI);
    }
    
    public double getIsoAngle2() {
        return FmrFingerView.angleToDoubleN(this.data.angle2, BdifStandard.ISO);
    }
    
    public void setIsoAngle2(final double angle) {
        this.data.angle2 = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ISO);
    }
    
    public int getRawAngle3() {
        return this.data.angle3;
    }
    
    public void setRawAngle3(final int angle) {
        this.data.angle3 = angle;
    }
    
    public double getAnsiAngle3() {
        return FmrFingerView.angleToDoubleN(this.data.angle3, BdifStandard.ANSI);
    }
    
    public void setAnsiAngle3(final double angle) {
        this.data.angle3 = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ANSI);
    }
    
    public double getIsoAngle3() {
        return FmrFingerView.angleToDoubleN(this.data.angle3, BdifStandard.ISO);
    }
    
    public void setIsoAngle3(final double angle) {
        this.data.angle3 = FmrFingerView.angleFromDoubleN(angle, BdifStandard.ISO);
    }
    
    FmrDeltaData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{X={%s}, Y={%s}, AnsiAngle1={%s}, IsoAngle1={%s}, AnsiAngle2={%s}, IsoAngle2={%s}, AnsiAngle3={%s}, IsoAngle3={%s}}}", this.getX(), this.getY(), FmrFingerView.angleToString(this.data.angle1, BdifStandard.ANSI), FmrFingerView.angleToString(this.data.angle1, BdifStandard.ISO), FmrFingerView.angleToString(this.data.angle2, BdifStandard.ANSI), FmrFingerView.angleToString(this.data.angle2, BdifStandard.ISO), FmrFingerView.angleToString(this.data.angle3, BdifStandard.ANSI), FmrFingerView.angleToString(this.data.angle3, BdifStandard.ISO));
    }
    
    public String toString(final BdifStandard standard) {
        return String.format("{{X={%s}, Y={%s}, Angle={%s}, Angle2={%s}, Angle3={%s}}}", this.getX(), this.getY(), FmrFingerView.angleToString(this.data.angle1, standard), FmrFingerView.angleToString(this.data.angle2, standard), FmrFingerView.angleToString(this.data.angle3, standard));
    }
    
    protected static class FmrDeltaData extends Structure
    {
        public short x;
        public short y;
        public int angle1;
        public int angle2;
        public int angle3;
        
        public FmrDeltaData() {
        }
        
        public FmrDeltaData(final short x, final short y, final int angle1, final int angle2, final int angle3) {
            this.x = x;
            this.y = y;
            this.angle1 = angle1;
            this.angle2 = angle2;
            this.angle3 = angle3;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected FmrDeltaData newInstance() {
            return new FmrDeltaData();
        }
        
        public static class ByReference extends FmrDeltaData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends FmrDeltaData implements Structure.ByValue
        {
        }
    }
}
