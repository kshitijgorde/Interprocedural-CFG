// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "NoMethodError" }, parent = "NameError")
public class RubyNoMethodError extends RubyNameError
{
    private IRubyObject args;
    private static final ObjectAllocator NOMETHODERROR_ALLOCATOR;
    
    public static RubyClass createNoMethodErrorClass(final Ruby runtime, final RubyClass nameErrorClass) {
        final RubyClass noMethodErrorClass = runtime.defineClass("NoMethodError", nameErrorClass, RubyNoMethodError.NOMETHODERROR_ALLOCATOR);
        noMethodErrorClass.defineAnnotatedMethods(RubyNoMethodError.class);
        return noMethodErrorClass;
    }
    
    protected RubyNoMethodError(final Ruby runtime, final RubyClass exceptionClass) {
        super(runtime, exceptionClass, exceptionClass.getName());
        this.args = runtime.getNil();
    }
    
    public RubyNoMethodError(final Ruby runtime, final RubyClass exceptionClass, final String message, final String name, final IRubyObject args) {
        super(runtime, exceptionClass, message, name);
        this.args = args;
    }
    
    @JRubyMethod(optional = 3)
    public IRubyObject initialize(IRubyObject[] args, final Block block) {
        if (args.length > 2) {
            this.args = args[args.length - 1];
            final IRubyObject[] tmpArgs = new IRubyObject[args.length - 1];
            System.arraycopy(args, 0, tmpArgs, 0, tmpArgs.length);
            args = tmpArgs;
        }
        else {
            this.args = this.getRuntime().getNil();
        }
        super.initialize(args, block);
        return this;
    }
    
    @JRubyMethod(name = { "args" })
    public IRubyObject args() {
        return this.args;
    }
    
    public void copySpecialInstanceVariables(final IRubyObject clone) {
        super.copySpecialInstanceVariables(clone);
        final RubyNoMethodError exception = (RubyNoMethodError)clone;
        exception.args = this.args;
    }
    
    static {
        NOMETHODERROR_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyNoMethodError(runtime, klass);
            }
        };
    }
}
