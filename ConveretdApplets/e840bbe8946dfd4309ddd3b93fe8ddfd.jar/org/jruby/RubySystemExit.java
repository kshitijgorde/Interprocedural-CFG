// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "SystemExit" }, parent = "Exception")
public class RubySystemExit extends RubyException
{
    IRubyObject status;
    private static ObjectAllocator SYSTEMEXIT_ALLOCATOR;
    
    public static RubyClass createSystemExitClass(final Ruby runtime, final RubyClass exceptionClass) {
        final RubyClass systemExitClass = runtime.defineClass("SystemExit", exceptionClass, RubySystemExit.SYSTEMEXIT_ALLOCATOR);
        systemExitClass.defineAnnotatedMethods(RubySystemExit.class);
        return systemExitClass;
    }
    
    public static RubySystemExit newInstance(final Ruby runtime, final int status) {
        final RubyClass exc = runtime.getSystemExit();
        final IRubyObject[] exArgs = { runtime.newFixnum(status), runtime.newString("exit") };
        return (RubySystemExit)exc.newInstance(runtime.getCurrentContext(), exArgs, Block.NULL_BLOCK);
    }
    
    protected RubySystemExit(final Ruby runtime, final RubyClass exceptionClass) {
        super(runtime, exceptionClass);
        this.status = runtime.getNil();
    }
    
    @JRubyMethod(optional = 2, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(IRubyObject[] args, final Block block) {
        this.status = RubyFixnum.zero(this.getRuntime());
        if (args.length > 0 && args[0] instanceof RubyFixnum) {
            this.status = args[0];
            final IRubyObject[] tmpArgs = new IRubyObject[args.length - 1];
            System.arraycopy(args, 1, tmpArgs, 0, tmpArgs.length);
            args = tmpArgs;
        }
        super.initialize(args, block);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject status() {
        return this.status;
    }
    
    @JRubyMethod(name = { "success?" })
    public IRubyObject success_p() {
        if (this.status.isNil()) {
            return this.getRuntime().getTrue();
        }
        if (this.status.equals(RubyFixnum.zero(this.getRuntime()))) {
            return this.getRuntime().getTrue();
        }
        return this.getRuntime().getFalse();
    }
    
    static {
        RubySystemExit.SYSTEMEXIT_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubySystemExit(runtime, klass);
            }
        };
    }
}
