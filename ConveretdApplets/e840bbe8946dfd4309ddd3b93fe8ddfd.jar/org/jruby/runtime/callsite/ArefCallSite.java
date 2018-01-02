// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class ArefCallSite extends NormalCachingCallSite
{
    public ArefCallSite() {
        super("[]");
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final long fixnum) {
        if (self.getMetaClass() == context.getRuntime().getArray()) {
            return ((RubyArray)self).entry(fixnum);
        }
        return super.call(context, caller, self, fixnum);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg) {
        if (self.getMetaClass() == context.getRuntime().getArray()) {
            return ((RubyArray)self).aref(arg);
        }
        return super.call(context, caller, self, arg);
    }
}
