// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFAlternateSegment
{
    private ANFAlternateSegmentData data;
    
    ANFAlternateSegment() {
        this.data = new ANFAlternateSegmentData();
    }
    
    ANFAlternateSegment(final ANFAlternateSegmentData data) {
        this.data = data;
    }
    
    public ANFAlternateSegment(final BdifFPPosition position) {
        this.data = new ANFAlternateSegmentData(position.getValue());
    }
    
    public BdifFPPosition getPosition() {
        return BdifFPPosition.get(this.data.position);
    }
    
    public void setPosition(final BdifFPPosition position) {
        this.data.position = position.getValue();
    }
    
    ANFAlternateSegmentData getData() {
        return this.data;
    }
    
    protected static class ANFAlternateSegmentData extends Structure
    {
        public int position;
        
        public ANFAlternateSegmentData() {
        }
        
        public ANFAlternateSegmentData(final int position) {
            this.position = position;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFAlternateSegmentData newInstance() {
            return new ANFAlternateSegmentData();
        }
        
        public static class ByReference extends ANFAlternateSegmentData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFAlternateSegmentData implements Structure.ByValue
        {
        }
    }
}
