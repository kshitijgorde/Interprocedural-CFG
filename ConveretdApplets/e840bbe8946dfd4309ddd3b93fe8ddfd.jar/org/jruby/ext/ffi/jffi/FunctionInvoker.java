// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Function;
import org.jruby.runtime.ThreadContext;

interface FunctionInvoker
{
    IRubyObject invoke(final ThreadContext p0, final Function p1, final HeapInvocationBuffer p2);
}
