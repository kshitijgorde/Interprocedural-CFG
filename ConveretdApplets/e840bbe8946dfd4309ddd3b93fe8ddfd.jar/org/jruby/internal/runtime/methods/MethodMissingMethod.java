// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.RubyString;

public class MethodMissingMethod extends DynamicMethod
{
    private RubyString name;
    
    public MethodMissingMethod(final RubyModule implementationClass, final RubyString name) {
        super(implementationClass, null, null);
        this.name = name;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, IRubyObject[] args, final Block block) {
        args = this.createArgs(args);
        return self.callMethod(context, "method_missing", args, block);
    }
    
    private IRubyObject[] createArgs(final IRubyObject[] args) {
        final IRubyObject[] newArgs = new IRubyObject[args.length + 1];
        System.arraycopy(args, 0, newArgs, 1, args.length);
        newArgs[0] = this.name;
        return newArgs;
    }
    
    public DynamicMethod dup() {
        return new MethodMissingMethod(this.getImplementationClass(), this.name);
    }
}
