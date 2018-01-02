// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.ext.ffi.Pointer;
import org.jruby.runtime.Block;
import org.jruby.RubyProc;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.CallingConvention;
import org.jruby.ext.ffi.CallbackInfo;

final class CallbackMarshaller implements ParameterMarshaller
{
    private final CallbackInfo cbInfo;
    private final CallingConvention convention;
    
    public CallbackMarshaller(final CallbackInfo cbInfo, final CallingConvention convention) {
        this.cbInfo = cbInfo;
        this.convention = convention;
    }
    
    public void marshal(final Invocation invocation, final InvocationBuffer buffer, final IRubyObject value) {
        this.marshal(invocation.getThreadContext(), buffer, value);
    }
    
    public void marshal(final ThreadContext context, final InvocationBuffer buffer, final IRubyObject value) {
        if (value.isNil()) {
            buffer.putAddress(0L);
        }
        else {
            if (!(value instanceof RubyProc) && !value.respondsTo("call")) {
                throw context.getRuntime().newTypeError("wrong argument type.  Expected callable object");
            }
            this.marshalParam(context, buffer, value);
        }
    }
    
    void marshal(final Invocation session, final InvocationBuffer buffer, final Block value) {
        final CallbackManager.Callback cb = CallbackManager.getInstance().getCallback(session.getThreadContext().getRuntime(), this.cbInfo, value);
        buffer.putAddress(cb.getAddress());
        session.addPostInvoke(new CallbackReaper(cb));
    }
    
    private void marshalParam(final ThreadContext context, final InvocationBuffer buffer, final Object value) {
        final Pointer cb = CallbackManager.getInstance().getCallback(context.getRuntime(), this.cbInfo, value);
        buffer.putAddress(cb.getAddress());
    }
    
    public boolean requiresPostInvoke() {
        return true;
    }
    
    public boolean requiresReference() {
        return false;
    }
    
    private static final class CallbackReaper implements Runnable
    {
        private final CallbackManager.Callback cb;
        
        public CallbackReaper(final CallbackManager.Callback cb) {
            this.cb = cb;
        }
        
        public void run() {
            this.cb.dispose();
        }
    }
}
