// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class BdifFaceFeaturePoint
{
    private BdifFaceFeaturePointData data;
    
    public BdifFaceFeaturePoint(final byte code, final short x, final short y) {
        this.data = new BdifFaceFeaturePointData(code, x, y);
    }
    
    BdifFaceFeaturePoint() {
        this.data = new BdifFaceFeaturePointData();
    }
    
    BdifFaceFeaturePoint(final BdifFaceFeaturePointData data) {
        this.data = data;
    }
    
    public BdifFaceFeaturePointType getType() {
        return BdifFaceFeaturePointType.get(this.data.type);
    }
    
    public void setType(final BdifFaceFeaturePointType type) {
        this.data.type = type.getValue();
    }
    
    public byte getCode() {
        return this.data.code;
    }
    
    public void setCode(final byte code) {
        this.data.code = code;
    }
    
    public short getX() {
        return this.data.x;
    }
    
    public void setX(final short x) {
        this.data.x = x;
    }
    
    public short getY() {
        return this.data.y;
    }
    
    public void setY(final short y) {
        this.data.y = y;
    }
    
    BdifFaceFeaturePointData getData() {
        return this.data;
    }
    
    protected static class BdifFaceFeaturePointData extends Structure
    {
        private int type;
        private byte code;
        private short x;
        private short y;
        
        public BdifFaceFeaturePointData() {
        }
        
        public BdifFaceFeaturePointData(final byte code, final short x, final short y) {
            this.type = BdifFaceFeaturePointType.POINT_2D.getValue();
            this.code = code;
            this.x = x;
            this.y = y;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected BdifFaceFeaturePointData newInstance() {
            return new BdifFaceFeaturePointData();
        }
        
        public static class ByReference extends BdifFaceFeaturePointData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends BdifFaceFeaturePointData implements Structure.ByValue
        {
        }
    }
}
