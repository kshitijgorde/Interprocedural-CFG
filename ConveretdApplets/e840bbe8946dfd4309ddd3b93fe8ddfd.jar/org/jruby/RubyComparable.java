// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Comparable" })
public class RubyComparable
{
    public static RubyModule createComparable(final Ruby runtime) {
        final RubyModule comparableModule = runtime.defineModule("Comparable");
        runtime.setComparable(comparableModule);
        comparableModule.defineAnnotatedMethods(RubyComparable.class);
        return comparableModule;
    }
    
    public static int cmpint(final ThreadContext context, final IRubyObject val, final IRubyObject a, final IRubyObject b) {
        if (val.isNil()) {
            cmperr(a, b);
        }
        if (val instanceof RubyFixnum) {
            return RubyNumeric.fix2int((RubyFixnum)val);
        }
        if (val instanceof RubyBignum) {
            return (((RubyBignum)val).getValue().signum() == -1) ? -1 : 1;
        }
        final RubyFixnum zero = RubyFixnum.zero(context.getRuntime());
        if (val.callMethod(context, ">", zero).isTrue()) {
            return 1;
        }
        if (val.callMethod(context, "<", zero).isTrue()) {
            return -1;
        }
        return 0;
    }
    
    public static IRubyObject cmperr(final IRubyObject recv, final IRubyObject other) {
        IRubyObject target;
        if (other.isImmediate() || (!other.isNil() && !other.isTrue() && other != recv.getRuntime().getFalse())) {
            target = other.inspect();
        }
        else {
            target = other.getType();
        }
        throw recv.getRuntime().newArgumentError("comparison of " + recv.getType() + " with " + target + " failed");
    }
    
    @JRubyMethod(name = { "==" }, required = 1, compat = CompatVersion.RUBY1_8)
    public static IRubyObject op_equal(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        return callCmpMethod(context, recv, other, context.getRuntime().getNil());
    }
    
    @JRubyMethod(name = { "==" }, required = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject op_equal19(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        return callCmpMethod(context, recv, other, context.getRuntime().getFalse());
    }
    
    private static IRubyObject callCmpMethod(final ThreadContext context, final IRubyObject recv, final IRubyObject other, final IRubyObject returnValueOnError) {
        final Ruby runtime = context.getRuntime();
        if (recv == other) {
            return runtime.getTrue();
        }
        try {
            final IRubyObject result = RuntimeHelpers.invokedynamic(context, recv, 4, other);
            return RubyBoolean.newBoolean(runtime, cmpint(context, result, recv, other) == 0);
        }
        catch (RaiseException e) {
            if (e.getException().kind_of_p(context, runtime.getStandardError()).isTrue()) {
                context.setErrorInfo(runtime.getNil());
                return returnValueOnError;
            }
            throw e;
        }
    }
    
    @JRubyMethod(name = { ">" }, required = 1)
    public static RubyBoolean op_gt(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        final IRubyObject result = RuntimeHelpers.invokedynamic(context, recv, 4, other);
        if (result.isNil()) {
            cmperr(recv, other);
        }
        return RubyBoolean.newBoolean(context.getRuntime(), cmpint(context, result, recv, other) > 0);
    }
    
    @JRubyMethod(name = { ">=" }, required = 1)
    public static RubyBoolean op_ge(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        final IRubyObject result = RuntimeHelpers.invokedynamic(context, recv, 4, other);
        if (result.isNil()) {
            cmperr(recv, other);
        }
        return RubyBoolean.newBoolean(context.getRuntime(), cmpint(context, result, recv, other) >= 0);
    }
    
    @JRubyMethod(name = { "<" }, required = 1)
    public static RubyBoolean op_lt(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        final IRubyObject result = RuntimeHelpers.invokedynamic(context, recv, 4, other);
        if (result.isNil()) {
            cmperr(recv, other);
        }
        return RubyBoolean.newBoolean(context.getRuntime(), cmpint(context, result, recv, other) < 0);
    }
    
    @JRubyMethod(name = { "<=" }, required = 1)
    public static RubyBoolean op_le(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        final IRubyObject result = RuntimeHelpers.invokedynamic(context, recv, 4, other);
        if (result.isNil()) {
            cmperr(recv, other);
        }
        return RubyBoolean.newBoolean(context.getRuntime(), cmpint(context, result, recv, other) <= 0);
    }
    
    @JRubyMethod(name = { "between?" }, required = 2)
    public static RubyBoolean between_p(final ThreadContext context, final IRubyObject recv, final IRubyObject first, final IRubyObject second) {
        return context.getRuntime().newBoolean(op_lt(context, recv, first).isFalse() && op_gt(context, recv, second).isFalse());
    }
}
