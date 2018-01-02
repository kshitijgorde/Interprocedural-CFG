// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public interface IntResultConverter
{
    IRubyObject fromNative(final ThreadContext p0, final int p1);
}
