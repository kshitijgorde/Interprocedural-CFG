// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;

public final class NFDoubleCore
{
    private NFDoubleCoreData data;
    
    NFDoubleCore() {
        this.data = new NFDoubleCoreData();
    }
    
    NFDoubleCore(final NFDoubleCoreData data) {
        this.data = data;
    }
    
    public NFDoubleCore(final short x, final short y) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data = new NFDoubleCoreData(x, y);
    }
    
    public short getX() {
        return this.data.x;
    }
    
    public void setX(final short x) {
        if (x < 0) {
            throw new IllegalArgumentException("x is less than zero");
        }
        this.data.x = x;
    }
    
    public short getY() {
        return this.data.y;
    }
    
    public void setY(final short y) {
        if (y < 0) {
            throw new IllegalArgumentException("y is less than zero");
        }
        this.data.y = y;
    }
    
    public NFDoubleCoreData getData() {
        return this.data;
    }
    
    public String toString() {
        return String.format("{{X={%s}, Y={%s}}}", this.getX(), this.getY());
    }
    
    protected static class NFDoubleCoreData extends Structure
    {
        public short x;
        public short y;
        
        public NFDoubleCoreData() {
        }
        
        public NFDoubleCoreData(final short x, final short y) {
            this.x = x;
            this.y = y;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NFDoubleCoreData newInstance() {
            return new NFDoubleCoreData();
        }
        
        public static class ByReference extends NFDoubleCoreData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NFDoubleCoreData implements Structure.ByValue
        {
        }
    }
}
