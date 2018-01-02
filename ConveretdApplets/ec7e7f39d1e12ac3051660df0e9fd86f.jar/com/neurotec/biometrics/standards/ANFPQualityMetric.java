// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFPQualityMetric
{
    private ANFPQualityMetricData data;
    
    ANFPQualityMetric() {
        this.data = new ANFPQualityMetricData();
    }
    
    ANFPQualityMetric(final ANFPQualityMetricData data) {
        this.data = data;
    }
    
    public ANFPQualityMetric(final BdifFPPosition position, final byte score, final short algorithmVendorId, final short algorithmProductId) {
        this.data = new ANFPQualityMetricData(position.getValue(), score, algorithmVendorId, algorithmProductId);
    }
    
    public BdifFPPosition getPosition() {
        return BdifFPPosition.get(this.data.position);
    }
    
    public void setPosition(final BdifFPPosition value) {
        this.data.position = value.getValue();
    }
    
    public byte getScore() {
        return this.data.score;
    }
    
    public void setScore(final byte value) {
        this.data.score = value;
    }
    
    public short getAlgorithmVendorId() {
        return this.data.algorithmVendorId;
    }
    
    public void setAlgorithmVendorId(final short value) {
        this.data.algorithmVendorId = value;
    }
    
    public short getAlgorithmProductId() {
        return this.data.algorithmProductId;
    }
    
    public void setAlgorithmProductId(final short value) {
        this.data.algorithmProductId = value;
    }
    
    ANFPQualityMetricData getData() {
        return this.data;
    }
    
    protected static class ANFPQualityMetricData extends Structure
    {
        public int position;
        public byte score;
        public short algorithmVendorId;
        public short algorithmProductId;
        
        public ANFPQualityMetricData() {
        }
        
        public ANFPQualityMetricData(final int position, final byte score, final short algorithmVendorId, final short algorithmProductId) {
            this.position = position;
            this.score = score;
            this.algorithmVendorId = algorithmVendorId;
            this.algorithmProductId = algorithmProductId;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFPQualityMetricData newInstance() {
            return new ANFPQualityMetricData();
        }
        
        public static class ByReference extends ANFPQualityMetricData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFPQualityMetricData implements Structure.ByValue
        {
        }
    }
}
