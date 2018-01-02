// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFAmputation
{
    private ANFAmputationData data;
    
    ANFAmputation() {
        this.data = new ANFAmputationData();
    }
    
    ANFAmputation(final ANFAmputationData data) {
        this.data = data;
    }
    
    public ANFAmputation(final BdifFPPosition position, final ANFAmputationType type) {
        this.data = new ANFAmputationData(position.getValue(), type.getValue());
    }
    
    public BdifFPPosition getPosition() {
        return BdifFPPosition.get(this.data.position);
    }
    
    public void setPosition(final BdifFPPosition position) {
        this.data.position = position.getValue();
    }
    
    public ANFAmputationType getType() {
        return ANFAmputationType.get(this.data.type);
    }
    
    public void setType(final ANFAmputationType type) {
        this.data.type = type.getValue();
    }
    
    ANFAmputationData getData() {
        return this.data;
    }
    
    protected static class ANFAmputationData extends Structure
    {
        public int position;
        public int type;
        
        public ANFAmputationData() {
        }
        
        public ANFAmputationData(final int position, final int type) {
            this.position = position;
            this.type = type;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFAmputationData newInstance() {
            return new ANFAmputationData();
        }
        
        public static class ByReference extends ANFAmputationData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFAmputationData implements Structure.ByValue
        {
        }
    }
}
