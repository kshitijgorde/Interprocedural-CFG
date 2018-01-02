// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;

public class DomainType
{
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject domain, final IRubyObject type_id, final IRubyObject val) {
        ((RubyObject)self).fastSetInstanceVariable("@domain", domain);
        ((RubyObject)self).fastSetInstanceVariable("@type_id", type_id);
        ((RubyObject)self).fastSetInstanceVariable("@value", val);
        return self;
    }
}
