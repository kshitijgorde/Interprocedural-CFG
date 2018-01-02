// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.util.PhantomReferenceReaper;
import java.util.concurrent.ConcurrentHashMap;
import org.jruby.Ruby;
import java.util.Map;
import org.jruby.ext.ffi.AllocatedDirectMemoryIO;

final class AllocatedNativeMemoryIO extends BoundedNativeMemoryIO implements AllocatedDirectMemoryIO
{
    private static final Map<MemoryHolder, Boolean> referenceSet;
    private final MemoryHolder holder;
    
    static final AllocatedNativeMemoryIO allocate(final Ruby runtime, final int size, final boolean clear) {
        return allocateAligned(runtime, size, 1, clear);
    }
    
    static final AllocatedNativeMemoryIO allocateAligned(final Ruby runtime, final int size, final int align, final boolean clear) {
        final long address = AllocatedNativeMemoryIO.IO.allocateMemory(size + align - 1, clear);
        if (address == 0L) {
            throw runtime.newRuntimeError("failed to allocate " + size + " bytes of native memory");
        }
        try {
            return new AllocatedNativeMemoryIO(runtime, address, size, align);
        }
        catch (Throwable t) {
            AllocatedNativeMemoryIO.IO.freeMemory(address);
            throw new RuntimeException(t);
        }
    }
    
    private AllocatedNativeMemoryIO(final Ruby runtime, final long address, final int size, final int align) {
        super(runtime, (address - 1L & ~(align - 1)) + align, size);
        AllocatedNativeMemoryIO.referenceSet.put(this.holder = new MemoryHolder(this, address), Boolean.TRUE);
    }
    
    public void free() {
        if (this.holder.released) {
            throw this.getRuntime().newRuntimeError("memory already freed");
        }
        this.holder.free();
        AllocatedNativeMemoryIO.referenceSet.remove(this.holder);
    }
    
    public void setAutoRelease(final boolean release) {
        this.holder.autorelease = release;
    }
    
    static {
        referenceSet = new ConcurrentHashMap<MemoryHolder, Boolean>();
    }
    
    private static final class MemoryHolder extends PhantomReferenceReaper<AllocatedNativeMemoryIO> implements Runnable
    {
        private final long storage;
        private volatile boolean released;
        private volatile boolean autorelease;
        
        MemoryHolder(final AllocatedNativeMemoryIO mem, final long storage) {
            super(mem);
            this.released = false;
            this.autorelease = true;
            this.storage = storage;
        }
        
        public final void run() {
            AllocatedNativeMemoryIO.referenceSet.remove(this);
            if (this.autorelease) {
                this.free();
            }
        }
        
        final void free() {
            if (!this.released) {
                this.released = true;
                BoundedNativeMemoryIO.IO.freeMemory(this.storage);
            }
        }
    }
}
