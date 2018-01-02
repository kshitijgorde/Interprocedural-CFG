// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.runtime.callback.Callback;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Precision" })
public class RubyPrecision
{
    public static RubyModule createPrecisionModule(final Ruby runtime) {
        final RubyModule precisionModule = runtime.defineModule("Precision");
        runtime.setPrecision(precisionModule);
        precisionModule.defineAnnotatedMethods(RubyPrecision.class);
        return precisionModule;
    }
    
    public static IRubyObject induced_from(final IRubyObject receiver, final IRubyObject source, final Block block) {
        throw receiver.getRuntime().newTypeError("Undefined conversion from " + source.getMetaClass().getName() + " into " + ((RubyClass)receiver).getName());
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject append_features(final IRubyObject receiver, final IRubyObject include, final Block block) {
        if (include instanceof RubyModule) {
            ((RubyModule)include).includeModule(receiver);
            include.getSingletonClass().defineMethod("induced_from", new Callback() {
                public IRubyObject execute(final IRubyObject recv, final IRubyObject[] args, final Block block) {
                    Arity.checkArgumentCount(recv.getRuntime(), args, 1, 1);
                    return RubyPrecision.induced_from(recv, args[0], block);
                }
                
                public Arity getArity() {
                    return Arity.ONE_ARGUMENT;
                }
            });
        }
        return receiver;
    }
    
    @JRubyMethod
    public static IRubyObject prec(final ThreadContext context, final IRubyObject receiver, final IRubyObject type, final Block block) {
        return type.callMethod(context, "induced_from", receiver);
    }
    
    @JRubyMethod
    public static IRubyObject prec_i(final ThreadContext context, final IRubyObject receiver, final Block block) {
        return receiver.getRuntime().getInteger().callMethod(context, "induced_from", receiver);
    }
    
    @JRubyMethod
    public static IRubyObject prec_f(final ThreadContext context, final IRubyObject receiver, final Block block) {
        return receiver.getRuntime().getFloat().callMethod(context, "induced_from", receiver);
    }
}
