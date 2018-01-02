// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;

public final class NFMinutiaNeighbor
{
    private NFMinutiaNeighborData data;
    public static final NFMinutiaNeighbor EMPTY;
    
    NFMinutiaNeighbor() {
        this.data = new NFMinutiaNeighborData();
    }
    
    public NFMinutiaNeighbor(final int index, final int ridgeCount) {
        if (ridgeCount < 0 || ridgeCount > 255) {
            throw new IllegalArgumentException("ridgeCount must be in range [0..255].");
        }
        this.data = new NFMinutiaNeighborData(index, (byte)ridgeCount);
    }
    
    NFMinutiaNeighbor(final NFMinutiaNeighborData structure) {
        this.data = structure;
    }
    
    public int getIndex() {
        return this.data.index;
    }
    
    public void setIndex(final int index) {
        this.data.index = index;
    }
    
    public int getRidgeCount() {
        return this.data.ridgeCount & 0xFF;
    }
    
    public void setRidgeCount(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.data.ridgeCount = (byte)value;
    }
    
    public NFMinutiaNeighborData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{Index={%s}, RidgeCount={%s}}}", this.getIndex(), this.getRidgeCount());
    }
    
    static {
        EMPTY = new NFMinutiaNeighbor(-1, 255);
    }
    
    protected static class NFMinutiaNeighborData extends Structure
    {
        public int index;
        public byte ridgeCount;
        
        public NFMinutiaNeighborData() {
        }
        
        public NFMinutiaNeighborData(final int index, final byte ridgeCount) {
            this.index = index;
            this.ridgeCount = ridgeCount;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NFMinutiaNeighborData newInstance() {
            return new NFMinutiaNeighborData();
        }
        
        public static class ByReference extends NFMinutiaNeighborData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NFMinutiaNeighborData implements Structure.ByValue
        {
        }
    }
}
