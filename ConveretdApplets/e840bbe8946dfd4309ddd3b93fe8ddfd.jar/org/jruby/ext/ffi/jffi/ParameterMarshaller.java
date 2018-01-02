// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.InvocationBuffer;

interface ParameterMarshaller
{
    void marshal(final Invocation p0, final InvocationBuffer p1, final IRubyObject p2);
    
    void marshal(final ThreadContext p0, final InvocationBuffer p1, final IRubyObject p2);
    
    boolean requiresPostInvoke();
    
    boolean requiresReference();
}
