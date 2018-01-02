// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

final class DirectClosureBuffer implements Closure.Buffer
{
    private static final MemoryIO IO;
    private static final NativeWordIO WordIO;
    private static final long PARAM_SIZE;
    private final long retval;
    private final long parameters;
    private final CallContext callContext;
    
    public DirectClosureBuffer(final CallContext callContext, final long retval, final long parameters) {
        this.callContext = callContext;
        this.retval = retval;
        this.parameters = parameters;
    }
    
    public final byte getByte(final int index) {
        return DirectClosureBuffer.IO.getByte(DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE));
    }
    
    public final short getShort(final int index) {
        return DirectClosureBuffer.IO.getShort(DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE));
    }
    
    public final int getInt(final int index) {
        return DirectClosureBuffer.IO.getInt(DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE));
    }
    
    public final long getLong(final int index) {
        return DirectClosureBuffer.IO.getLong(DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE));
    }
    
    public final float getFloat(final int index) {
        return DirectClosureBuffer.IO.getFloat(DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE));
    }
    
    public final double getDouble(final int index) {
        return DirectClosureBuffer.IO.getDouble(DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE));
    }
    
    public final long getAddress(final int index) {
        return DirectClosureBuffer.IO.getAddress(DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE));
    }
    
    public final long getStruct(final int index) {
        return DirectClosureBuffer.IO.getAddress(this.parameters + index * DirectClosureBuffer.PARAM_SIZE);
    }
    
    public final void setByteReturn(final byte value) {
        DirectClosureBuffer.WordIO.put(this.retval, value);
    }
    
    public final void setShortReturn(final short value) {
        DirectClosureBuffer.WordIO.put(this.retval, value);
    }
    
    public final void setIntReturn(final int value) {
        DirectClosureBuffer.WordIO.put(this.retval, value);
    }
    
    public final void setLongReturn(final long value) {
        DirectClosureBuffer.IO.putLong(this.retval, value);
    }
    
    public final void setFloatReturn(final float value) {
        DirectClosureBuffer.IO.putFloat(this.retval, value);
    }
    
    public final void setDoubleReturn(final double value) {
        DirectClosureBuffer.IO.putDouble(this.retval, value);
    }
    
    public final void setAddressReturn(final long address) {
        DirectClosureBuffer.IO.putAddress(this.retval, address);
    }
    
    public void setStructReturn(final long value) {
        DirectClosureBuffer.IO.copyMemory(value, this.retval, this.callContext.getReturnType().size());
    }
    
    public void setStructReturn(final byte[] data, final int offset) {
        DirectClosureBuffer.IO.putByteArray(this.retval, data, offset, this.callContext.getReturnType().size());
    }
    
    static {
        IO = MemoryIO.getInstance();
        WordIO = NativeWordIO.getInstance();
        PARAM_SIZE = Platform.getPlatform().addressSize() / 8;
    }
    
    private abstract static class NativeWordIO
    {
        public static final NativeWordIO getInstance() {
            return (Platform.getPlatform().addressSize() == 32) ? NativeWordIO32.INSTANCE : NativeWordIO64.INSTANCE;
        }
        
        abstract void put(final long p0, final int p1);
        
        abstract int get(final long p0);
    }
    
    private static final class NativeWordIO32 extends NativeWordIO
    {
        private static final MemoryIO IO;
        static final NativeWordIO INSTANCE;
        
        void put(final long address, final int value) {
            NativeWordIO32.IO.putInt(address, value);
        }
        
        int get(final long address) {
            return NativeWordIO32.IO.getInt(address);
        }
        
        static {
            IO = MemoryIO.getInstance();
            INSTANCE = new NativeWordIO32();
        }
    }
    
    private static final class NativeWordIO64 extends NativeWordIO
    {
        private static final MemoryIO IO;
        static final NativeWordIO INSTANCE;
        
        void put(final long address, final int value) {
            NativeWordIO64.IO.putLong(address, value);
        }
        
        int get(final long address) {
            return (int)NativeWordIO64.IO.getLong(address);
        }
        
        static {
            IO = MemoryIO.getInstance();
            INSTANCE = new NativeWordIO64();
        }
    }
}
