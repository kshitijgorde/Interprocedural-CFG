// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANQualityMetric
{
    private ANQualityMetricData data;
    
    ANQualityMetric() {
        this.data = new ANQualityMetricData();
    }
    
    ANQualityMetric(final ANQualityMetricData data) {
        this.data = data;
    }
    
    public ANQualityMetric(final byte score, final short algorithmVendorId, final short algorithmProductId) {
        this.data = new ANQualityMetricData(score, algorithmVendorId, algorithmProductId);
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
    
    ANQualityMetricData getData() {
        return this.data;
    }
    
    protected static class ANQualityMetricData extends Structure
    {
        public byte score;
        public short algorithmVendorId;
        public short algorithmProductId;
        
        public ANQualityMetricData() {
        }
        
        public ANQualityMetricData(final byte score, final short algorithmVendorId, final short algorithmProductId) {
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
        
        protected ANQualityMetricData newInstance() {
            return new ANQualityMetricData();
        }
        
        public static class ByReference extends ANQualityMetricData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANQualityMetricData implements Structure.ByValue
        {
        }
    }
}
