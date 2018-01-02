// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class BdifFPMinutiaNeighbor
{
    private BdifFPMinutiaNeighborData data;
    public static final BdifFPMinutiaNeighbor EMPTY;
    
    BdifFPMinutiaNeighbor() {
        this.data = new BdifFPMinutiaNeighborData();
    }
    
    BdifFPMinutiaNeighbor(final BdifFPMinutiaNeighborData data) {
        this.data = data;
    }
    
    public BdifFPMinutiaNeighbor(final int index, final byte ridgeCount) {
        this.data = new BdifFPMinutiaNeighborData(index, ridgeCount);
    }
    
    public int getIndex() {
        return this.data.index;
    }
    
    public void setIndex(final int index) {
        this.data.index = index;
    }
    
    public byte getRidgeCount() {
        return this.data.ridgeCount;
    }
    
    public void setRidgeCount(final byte ridgeCount) {
        this.data.ridgeCount = ridgeCount;
    }
    
    BdifFPMinutiaNeighborData getData() {
        return this.data;
    }
    
    public String toString() {
        return "{{Index=" + this.getIndex() + ", RidgeCount=" + this.getRidgeCount() + "}}";
    }
    
    static {
        EMPTY = new BdifFPMinutiaNeighbor(-1, (byte)(-1));
    }
    
    protected static class BdifFPMinutiaNeighborData extends Structure
    {
        public int index;
        public byte ridgeCount;
        
        public BdifFPMinutiaNeighborData() {
        }
        
        public BdifFPMinutiaNeighborData(final int index, final byte ridgeCount) {
            this.index = index;
            this.ridgeCount = ridgeCount;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected BdifFPMinutiaNeighborData newInstance() {
            return new BdifFPMinutiaNeighborData();
        }
        
        public static class ByReference extends BdifFPMinutiaNeighborData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends BdifFPMinutiaNeighborData implements Structure.ByValue
        {
        }
    }
}
