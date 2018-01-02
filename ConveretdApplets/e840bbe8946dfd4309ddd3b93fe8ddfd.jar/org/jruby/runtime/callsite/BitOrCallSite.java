// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.RubyFixnum;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class BitOrCallSite extends NormalCachingCallSite
{
    public BitOrCallSite() {
        super("|");
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final long fixnum) {
        if (self instanceof RubyFixnum && !context.runtime.isFixnumReopened()) {
            return ((RubyFixnum)self).op_or(context, fixnum);
        }
        return super.call(context, caller, self, fixnum);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg) {
        if (self instanceof RubyFixnum && !context.runtime.isFixnumReopened()) {
            return ((RubyFixnum)self).op_or(context, arg);
        }
        return super.call(context, caller, self, arg);
    }
}
