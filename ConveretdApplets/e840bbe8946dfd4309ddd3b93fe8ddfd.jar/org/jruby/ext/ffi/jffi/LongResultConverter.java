// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public interface LongResultConverter
{
    IRubyObject fromNative(final ThreadContext p0, final long p1);
}
