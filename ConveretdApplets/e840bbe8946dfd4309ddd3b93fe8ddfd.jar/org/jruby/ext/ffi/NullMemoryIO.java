// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.RubyClass;
import org.jruby.Ruby;

public class NullMemoryIO extends InvalidMemoryIO implements DirectMemoryIO
{
    public NullMemoryIO(final Ruby runtime) {
        super(runtime, "NULL pointer access");
    }
    
    protected RubyClass getErrorClass(final Ruby runtime) {
        return runtime.fastGetModule("FFI").fastGetClass("NullPointerError");
    }
    
    public long getAddress() {
        return 0L;
    }
    
    public boolean isNull() {
        return true;
    }
    
    public final boolean isDirect() {
        return true;
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof DirectMemoryIO && ((DirectMemoryIO)obj).getAddress() == 0L;
    }
    
    public int hashCode() {
        return 0;
    }
}
