// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class MinusCallSite extends NormalCachingCallSite
{
    public MinusCallSite() {
        super("-");
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final long fixnum) {
        if (self instanceof RubyFixnum && !context.runtime.isFixnumReopened()) {
            return ((RubyFixnum)self).op_minus(context, fixnum);
        }
        if (self instanceof RubyFloat && !context.runtime.isFloatReopened()) {
            return ((RubyFloat)self).op_minus(context, fixnum);
        }
        return super.call(context, caller, self, fixnum);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final double flote) {
        if (self instanceof RubyFloat && !context.runtime.isFloatReopened()) {
            return ((RubyFloat)self).op_minus(context, flote);
        }
        return super.call(context, caller, self, flote);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg) {
        if (self instanceof RubyFixnum && !context.runtime.isFixnumReopened()) {
            return ((RubyFixnum)self).op_minus(context, arg);
        }
        if (self instanceof RubyFloat && !context.runtime.isFloatReopened()) {
            return ((RubyFloat)self).op_minus(context, arg);
        }
        return super.call(context, caller, self, arg);
    }
}
