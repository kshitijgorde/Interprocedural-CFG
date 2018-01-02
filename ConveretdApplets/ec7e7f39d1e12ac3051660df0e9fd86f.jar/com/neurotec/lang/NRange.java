// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.sun.jna.Structure;

public final class NRange
{
    private NRangeData data;
    
    public NRange() {
        this.data = new NRangeData();
    }
    
    public NRange(final int from, final int to) {
        this.data = new NRangeData(from, to);
    }
    
    public NRange(final NRangeData data) {
        this.data = data;
    }
    
    public int getFrom() {
        return this.data.from;
    }
    
    public void setFrom(final int value) {
        this.data.from = value;
    }
    
    public int getTo() {
        return this.data.to;
    }
    
    public void setTo(final int value) {
        this.data.to = value;
    }
    
    public NRangeData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{From={%s}, To={%s}}}", this.getFrom(), this.getTo());
    }
    
    public static class NRangeData extends Structure
    {
        public int from;
        public int to;
        
        public NRangeData() {
        }
        
        public NRangeData(final int from, final int to) {
            this.from = from;
            this.to = to;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NRangeData newInstance() {
            return new NRangeData();
        }
        
        public static class ByReference extends NRangeData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NRangeData implements Structure.ByValue
        {
        }
    }
}
