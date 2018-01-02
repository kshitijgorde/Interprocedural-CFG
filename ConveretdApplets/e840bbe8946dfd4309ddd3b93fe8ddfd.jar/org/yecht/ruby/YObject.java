// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;

public class YObject
{
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject klass, final IRubyObject ivars) {
        ((RubyObject)self).fastSetInstanceVariable("@class", klass);
        ((RubyObject)self).fastSetInstanceVariable("@ivars", ivars);
        return self;
    }
    
    @JRubyMethod
    public static IRubyObject yaml_initialize(final IRubyObject self, final IRubyObject klass, final IRubyObject ivars) {
        ((RubyObject)self).fastSetInstanceVariable("@class", klass);
        ((RubyObject)self).fastSetInstanceVariable("@ivars", ivars);
        return self;
    }
}
