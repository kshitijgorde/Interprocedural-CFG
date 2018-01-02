// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

interface IntParameterConverter
{
    boolean isConvertible(final ThreadContext p0, final IRubyObject p1);
    
    int intValue(final ThreadContext p0, final IRubyObject p1);
}
