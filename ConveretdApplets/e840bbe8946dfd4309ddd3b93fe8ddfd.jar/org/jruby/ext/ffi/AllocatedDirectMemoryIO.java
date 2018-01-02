// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

public interface AllocatedDirectMemoryIO extends DirectMemoryIO
{
    void free();
    
    void setAutoRelease(final boolean p0);
}
