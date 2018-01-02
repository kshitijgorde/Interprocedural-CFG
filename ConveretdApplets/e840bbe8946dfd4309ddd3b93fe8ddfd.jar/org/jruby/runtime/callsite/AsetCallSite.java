// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class AsetCallSite extends NormalCachingCallSite
{
    public AsetCallSite() {
        super("[]=");
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1) {
        final Ruby runtime = context.getRuntime();
        if (self.getMetaClass() == runtime.getArray()) {
            final RubyArray array = (RubyArray)self;
            return array.aset(arg0, arg1);
        }
        return super.call(context, caller, self, arg0, arg1);
    }
}
