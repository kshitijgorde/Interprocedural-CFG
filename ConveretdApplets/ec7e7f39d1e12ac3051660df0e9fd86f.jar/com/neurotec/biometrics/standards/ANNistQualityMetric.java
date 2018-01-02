// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANNistQualityMetric
{
    private ANNistQualityMetricData data;
    
    ANNistQualityMetric() {
        this.data = new ANNistQualityMetricData();
    }
    
    ANNistQualityMetric(final ANNistQualityMetricData data) {
        this.data = data;
    }
    
    public ANNistQualityMetric(final BdifFPPosition position, final byte score) {
        this.data = new ANNistQualityMetricData(position.getValue(), score);
    }
    
    public BdifFPPosition getPosition() {
        return BdifFPPosition.get(this.data.position);
    }
    
    public void setPosition(final BdifFPPosition position) {
        this.data.position = position.getValue();
    }
    
    public byte getScore() {
        return this.data.score;
    }
    
    public void setScore(final byte score) {
        this.data.score = score;
    }
    
    ANNistQualityMetricData getData() {
        return this.data;
    }
    
    protected static class ANNistQualityMetricData extends Structure
    {
        public int position;
        public byte score;
        
        public ANNistQualityMetricData() {
        }
        
        public ANNistQualityMetricData(final int position, final byte score) {
            this.position = position;
            this.score = score;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANNistQualityMetricData newInstance() {
            return new ANNistQualityMetricData();
        }
        
        public static class ByReference extends ANNistQualityMetricData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANNistQualityMetricData implements Structure.ByValue
        {
        }
    }
}
