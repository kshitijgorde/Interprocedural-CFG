// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.sun.jna.Structure;

public final class NIndexPair
{
    private NIndexPairData data;
    
    public NIndexPair() {
        this.data = new NIndexPairData();
    }
    
    public NIndexPair(final int index1, final int index2) {
        this.data = new NIndexPairData(index1, index2);
    }
    
    public NIndexPair(final NIndexPairData data) {
        this.data = data;
    }
    
    public int getIndex1() {
        return this.data.index1;
    }
    
    public void setFrom(final int value) {
        this.data.index1 = value;
    }
    
    public int getIndex2() {
        return this.data.index2;
    }
    
    public void setIndex2(final int value) {
        this.data.index2 = value;
    }
    
    public NIndexPairData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{Index1={%s}, Index2={%s}}}", this.getIndex1(), this.getIndex2());
    }
    
    public static class NIndexPairData extends Structure
    {
        public int index1;
        public int index2;
        
        public NIndexPairData() {
        }
        
        public NIndexPairData(final int index1, final int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NIndexPairData newInstance() {
            return new NIndexPairData();
        }
        
        public static class ByReference extends NIndexPairData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NIndexPairData implements Structure.ByValue
        {
        }
    }
}
