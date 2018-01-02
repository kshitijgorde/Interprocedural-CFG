// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public interface ObjectAllocator
{
    public static final ObjectAllocator NOT_ALLOCATABLE_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            throw runtime.newTypeError("allocator undefined for " + klass.getName());
        }
    };
    
    IRubyObject allocate(final Ruby p0, final RubyClass p1);
}
