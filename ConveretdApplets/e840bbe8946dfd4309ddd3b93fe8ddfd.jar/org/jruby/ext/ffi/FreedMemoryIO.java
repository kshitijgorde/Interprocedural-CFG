// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.Ruby;

public final class FreedMemoryIO extends InvalidMemoryIO implements AllocatedDirectMemoryIO
{
    public FreedMemoryIO(final Ruby runtime) {
        super(runtime, "Attempting to access freed memory");
    }
    
    public boolean isNull() {
        return false;
    }
    
    public boolean isDirect() {
        return true;
    }
    
    public void free() {
        throw this.ex();
    }
    
    public void setAutoRelease(final boolean autorelease) {
        throw this.ex();
    }
    
    public long getAddress() {
        throw this.ex();
    }
}
