// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFPositionDescriptor
{
    private ANFPositionDescriptorData data;
    
    ANFPositionDescriptor() {
        this.data = new ANFPositionDescriptorData();
    }
    
    ANFPositionDescriptor(final ANFPositionDescriptorData data) {
        this.data = data;
    }
    
    public ANFPositionDescriptor(final BdifFPPosition position, final ANFMajorCase portion) {
        this.data = new ANFPositionDescriptorData(position.getValue(), portion.getValue());
    }
    
    public BdifFPPosition getPosition() {
        return BdifFPPosition.get(this.data.position);
    }
    
    public void setPosition(final BdifFPPosition value) {
        this.data.position = value.getValue();
    }
    
    public ANFMajorCase getPortion() {
        return ANFMajorCase.get(this.data.portion);
    }
    
    public void setPortion(final ANFMajorCase value) {
        this.data.portion = value.getValue();
    }
    
    ANFPositionDescriptorData getData() {
        return this.data;
    }
    
    protected static class ANFPositionDescriptorData extends Structure
    {
        public int position;
        public int portion;
        
        public ANFPositionDescriptorData() {
        }
        
        public ANFPositionDescriptorData(final int position, final int portion) {
            this.position = position;
            this.portion = portion;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFPositionDescriptorData newInstance() {
            return new ANFPositionDescriptorData();
        }
        
        public static class ByReference extends ANFPositionDescriptorData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFPositionDescriptorData implements Structure.ByValue
        {
        }
    }
}
