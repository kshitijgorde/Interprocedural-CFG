// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.RubyModule;
import org.jruby.Ruby;

public class NoImplFactory extends Factory
{
    private final String msg;
    
    public NoImplFactory(final String msg) {
        this.msg = "FFI not available: " + msg;
    }
    
    public void init(final Ruby runtime, final RubyModule ffi) {
        throw runtime.newNotImplementedError(this.msg);
    }
    
    public AllocatedDirectMemoryIO allocateDirectMemory(final Ruby runtime, final int size, final boolean clear) {
        throw runtime.newNotImplementedError(this.msg);
    }
    
    public AllocatedDirectMemoryIO allocateDirectMemory(final Ruby runtime, final int size, final int align, final boolean clear) {
        throw runtime.newNotImplementedError(this.msg);
    }
    
    public DirectMemoryIO wrapDirectMemory(final Ruby runtime, final long address) {
        throw runtime.newNotImplementedError(this.msg);
    }
    
    public CallbackManager getCallbackManager() {
        throw new UnsupportedOperationException(this.msg);
    }
    
    public AbstractInvoker newFunction(final Ruby runtime, final Pointer address, final CallbackInfo cbInfo) {
        throw new UnsupportedOperationException(this.msg);
    }
    
    public int sizeOf(final NativeType type) {
        throw new UnsupportedOperationException(this.msg);
    }
    
    public int alignmentOf(final NativeType type) {
        throw new UnsupportedOperationException(this.msg);
    }
}
