// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.RubyUnboundMethod;

public class MethodMethod extends DynamicMethod
{
    private RubyUnboundMethod method;
    
    public MethodMethod(final RubyModule implementationClass, final RubyUnboundMethod method, final Visibility visibility) {
        super(implementationClass, visibility, null);
        this.method = method;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args, final Block block) {
        return this.method.bind(context, self).call(context, args, block);
    }
    
    public DynamicMethod dup() {
        return new MethodMethod(this.getImplementationClass(), this.method, this.getVisibility());
    }
    
    public DynamicMethod getRealMethod() {
        return this.method.getMethod();
    }
}
