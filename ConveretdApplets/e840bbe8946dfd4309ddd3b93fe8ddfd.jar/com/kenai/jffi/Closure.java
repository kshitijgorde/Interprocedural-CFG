// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public interface Closure
{
    void invoke(final Buffer p0);
    
    public interface Handle
    {
        long getAddress();
        
        void setAutoRelease(final boolean p0);
        
        void dispose();
        
        @Deprecated
        void free();
    }
    
    public interface Buffer
    {
        byte getByte(final int p0);
        
        short getShort(final int p0);
        
        int getInt(final int p0);
        
        long getLong(final int p0);
        
        float getFloat(final int p0);
        
        double getDouble(final int p0);
        
        long getAddress(final int p0);
        
        long getStruct(final int p0);
        
        void setByteReturn(final byte p0);
        
        void setShortReturn(final short p0);
        
        void setIntReturn(final int p0);
        
        void setLongReturn(final long p0);
        
        void setFloatReturn(final float p0);
        
        void setDoubleReturn(final double p0);
        
        void setAddressReturn(final long p0);
        
        void setStructReturn(final long p0);
        
        void setStructReturn(final byte[] p0, final int p1);
    }
}
