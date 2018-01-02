// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import com.kenai.jaffl.Platform;
import com.kenai.jaffl.Address;
import com.kenai.jaffl.MemoryIO;

public abstract class AbstractMemoryIO extends MemoryIO
{
    protected static final void checkBounds(final long size, final long off, final long len) {
        if ((off | len | off + len | size - (off + len)) < 0L) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    public int indexOf(final long offset, final byte value) {
        return this.indexOf(offset, value, Integer.MAX_VALUE);
    }
    
    public long getAddress(final long offset) {
        return AddressIO.INSTANCE.getAddress(this, offset);
    }
    
    public void putAddress(final long offset, final long value) {
        AddressIO.INSTANCE.putAddress(this, offset, value);
    }
    
    public void putAddress(final long offset, final Address value) {
        AddressIO.INSTANCE.putAddress(this, offset, value.longValue());
    }
    
    public final long getNativeLong(final long offset) {
        return NativeLongIO.INSTANCE.getLong(this, offset);
    }
    
    public MemoryIO slice(final long offset) {
        return new ShareMemoryIO(this, offset);
    }
    
    public MemoryIO slice(final long offset, final long size) {
        return new BoundedMemoryIO(this, offset, size);
    }
    
    public void putNativeLong(final long offset, final long value) {
        NativeLongIO.INSTANCE.putLong(this, offset, value);
    }
    
    public void transferTo(final long offset, final MemoryIO other, final long otherOffset, final long count) {
        for (long i = 0L; i < count; ++i) {
            other.putByte(otherOffset + i, this.getByte(offset + i));
        }
    }
    
    public void transferFrom(final long offset, final MemoryIO other, final long otherOffset, final long count) {
        for (long i = 0L; i < count; ++i) {
            this.putByte(offset + i, other.getByte(otherOffset + i));
        }
    }
    
    private interface AddressIO
    {
        public static final AddressIO INSTANCE = (Platform.getPlatform().addressSize() == 32) ? AddressIO32._INSTANCE : AddressIO64._INSTANCE;
        
        long getAddress(final MemoryIO p0, final long p1);
        
        void putAddress(final MemoryIO p0, final long p1, final long p2);
        
        public static class AddressIO32 implements AddressIO
        {
            public static final AddressIO _INSTANCE;
            
            public long getAddress(final MemoryIO io, final long offset) {
                return io.getInt(offset);
            }
            
            public void putAddress(final MemoryIO io, final long offset, final long address) {
                io.putInt(offset, (int)address);
            }
            
            static {
                _INSTANCE = new AddressIO32();
            }
        }
        
        public static class AddressIO64 implements AddressIO
        {
            public static final AddressIO _INSTANCE;
            
            public long getAddress(final MemoryIO io, final long offset) {
                return io.getLong(offset);
            }
            
            public void putAddress(final MemoryIO io, final long offset, final long address) {
                io.putLong(offset, address);
            }
            
            static {
                _INSTANCE = new AddressIO64();
            }
        }
    }
    
    private interface NativeLongIO
    {
        public static final NativeLongIO INSTANCE = (Platform.getPlatform().longSize() == 32) ? LongIO32._INSTANCE : LongIO64._INSTANCE;
        
        long getLong(final MemoryIO p0, final long p1);
        
        void putLong(final MemoryIO p0, final long p1, final long p2);
        
        public static class LongIO32 implements NativeLongIO
        {
            public static final NativeLongIO _INSTANCE;
            
            public long getLong(final MemoryIO io, final long offset) {
                return io.getInt(offset);
            }
            
            public void putLong(final MemoryIO io, final long offset, final long value) {
                io.putInt(offset, (int)value);
            }
            
            static {
                _INSTANCE = new LongIO32();
            }
        }
        
        public static class LongIO64 implements NativeLongIO
        {
            public static final NativeLongIO _INSTANCE;
            
            public long getLong(final MemoryIO io, final long offset) {
                return io.getLong(offset);
            }
            
            public void putLong(final MemoryIO io, final long offset, final long value) {
                io.putLong(offset, value);
            }
            
            static {
                _INSTANCE = new LongIO64();
            }
        }
    }
}
