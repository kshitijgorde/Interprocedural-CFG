// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Converter" })
public class RubyConverter extends RubyObject
{
    private static ObjectAllocator CONVERTER_ALLOCATOR;
    
    public static RubyClass createConverterClass(final Ruby runtime) {
        final RubyClass converterc = runtime.defineClassUnder("Converter", runtime.getClass("Data"), RubyConverter.CONVERTER_ALLOCATOR, runtime.getEncoding());
        runtime.setConverter(converterc);
        converterc.index = 23;
        converterc.setReifiedClass(RubyConverter.class);
        converterc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyConverter;
            }
        };
        converterc.defineAnnotatedMethods(RubyConverter.class);
        return converterc;
    }
    
    public RubyConverter(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
    }
    
    public RubyConverter(final Ruby runtime) {
        super(runtime, runtime.getConverter());
    }
    
    @JRubyMethod(name = { "convpath" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject convpath(final ThreadContext context) {
        return context.getRuntime().getNil();
    }
    
    static {
        RubyConverter.CONVERTER_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyConverter(runtime, klass);
            }
        };
    }
}
