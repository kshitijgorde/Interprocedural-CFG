// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.Ruby;
import org.jruby.ext.ffi.Pointer;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.ext.ffi.InvalidMemoryIO;

public final class CodeMemoryIO extends InvalidMemoryIO implements DirectMemoryIO
{
    private final Pointer pointer;
    private final long address;
    
    public CodeMemoryIO(final Ruby runtime, final long address) {
        super(runtime, "code region is inaccessible");
        this.pointer = null;
        this.address = address;
    }
    
    public CodeMemoryIO(final Ruby runtime, final Pointer pointer) {
        super(runtime, "code region is inaccessible");
        this.pointer = pointer;
        this.address = pointer.getAddress();
    }
    
    public long getAddress() {
        return this.address;
    }
    
    public boolean isDirect() {
        return true;
    }
    
    public boolean isNull() {
        return this.address == 0L;
    }
}
