// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public class DataConverter
{
    public static RubyModule createDataConverterModule(final Ruby runtime, final RubyModule module) {
        final RubyModule result = module.defineModuleUnder("DataConverter");
        result.defineAnnotatedMethods(DataConverter.class);
        result.defineAnnotatedConstants(DataConverter.class);
        return result;
    }
    
    @JRubyMethod(name = { "native_type" }, module = true, optional = 1)
    public static IRubyObject native_type(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        if (!(self instanceof RubyModule)) {
            throw context.getRuntime().newRuntimeError("not a module");
        }
        final RubyModule m = (RubyModule)self;
        if (args.length == 0) {
            if (!m.hasInternalVariable("native_type")) {
                throw context.getRuntime().newNotImplementedError("native_type method not overridden and no native_type set");
            }
            return (Type)m.fastGetInternalVariable("native_type");
        }
        else {
            if (args.length == 1) {
                final Type type = Util.findType(context, args[0]);
                m.fastSetInternalVariable("native_type", type);
                return type;
            }
            throw context.getRuntime().newArgumentError("incorrect arguments");
        }
    }
    
    @JRubyMethod(name = { "to_native" }, module = true)
    public static IRubyObject to_native(final ThreadContext context, final IRubyObject self, final IRubyObject value, final IRubyObject ctx) {
        return value;
    }
    
    @JRubyMethod(name = { "from_native" }, module = true)
    public static IRubyObject from_native(final ThreadContext context, final IRubyObject self, final IRubyObject value, final IRubyObject ctx) {
        return value;
    }
}
