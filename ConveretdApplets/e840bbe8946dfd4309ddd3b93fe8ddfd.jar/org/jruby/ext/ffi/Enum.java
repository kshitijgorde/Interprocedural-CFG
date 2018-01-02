// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyMethod;
import java.util.Iterator;
import org.jruby.RubyNumeric;
import org.jruby.RubyHash;
import org.jruby.runtime.ThreadContext;
import java.util.IdentityHashMap;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jcodings.util.IntHash;
import org.jruby.RubyInteger;
import org.jruby.RubySymbol;
import java.util.Map;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "FFI::Enum" }, parent = "Object")
public final class Enum extends RubyObject
{
    private final IRubyObject nativeType;
    private volatile Map<RubySymbol, RubyInteger> symbolToValue;
    private volatile IntHash<RubySymbol> valueToSymbol;
    
    public static RubyClass createEnumClass(final Ruby runtime, final RubyModule ffiModule) {
        final RubyClass enumClass = ffiModule.defineClassUnder("Enum", runtime.getObject(), Allocator.INSTANCE);
        enumClass.defineAnnotatedMethods(Enum.class);
        enumClass.defineAnnotatedConstants(Enum.class);
        enumClass.includeModule(ffiModule.fastGetConstant("DataConverter"));
        return enumClass;
    }
    
    private Enum(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        this.symbolToValue = new IdentityHashMap<RubySymbol, RubyInteger>();
        this.valueToSymbol = new IntHash<RubySymbol>();
        this.nativeType = runtime.fastGetModule("FFI").fastGetClass("Type").fastGetConstant("INT");
    }
    
    @JRubyMethod(name = { "init_values" })
    public final IRubyObject init_values(final ThreadContext context, final IRubyObject values) {
        if (!(values instanceof RubyHash)) {
            throw context.getRuntime().newTypeError(values, context.getRuntime().getHash());
        }
        final Map<RubySymbol, RubyInteger> s2v = new IdentityHashMap<RubySymbol, RubyInteger>();
        final IntHash<RubySymbol> v2s = new IntHash<RubySymbol>();
        for (final Object obj : ((RubyHash)values).directEntrySet()) {
            final Map.Entry entry = (Map.Entry)obj;
            if (!(entry.getKey() instanceof RubySymbol)) {
                throw context.getRuntime().newTypeError(values, context.getRuntime().getSymbol());
            }
            if (!(entry.getValue() instanceof RubyInteger)) {
                throw context.getRuntime().newTypeError(values, context.getRuntime().getInteger());
            }
            final RubySymbol sym = entry.getKey();
            s2v.put(sym, entry.getValue());
            v2s.put(RubyNumeric.num2int(entry.getValue()), sym);
        }
        this.symbolToValue = s2v;
        this.valueToSymbol = v2s;
        return this;
    }
    
    @JRubyMethod(name = { "native_type" })
    public final IRubyObject native_type(final ThreadContext context) {
        return this.nativeType;
    }
    
    @JRubyMethod(name = { "to_native" })
    public final IRubyObject to_native(final ThreadContext context, IRubyObject name, final IRubyObject ctx) {
        final RubyInteger value;
        if (name instanceof RubySymbol && (value = this.symbolToValue.get(name)) != null) {
            return value;
        }
        if (name instanceof RubyInteger) {
            return name;
        }
        if (name.respondsTo("to_int")) {
            return name.convertToInteger();
        }
        throw name.getRuntime().newArgumentError("invalid enum value, " + name.inspect());
    }
    
    @JRubyMethod(name = { "from_native" })
    public final IRubyObject from_native(final ThreadContext context, final IRubyObject value, final IRubyObject ctx) {
        final RubySymbol sym;
        if (value instanceof RubyInteger && (sym = this.valueToSymbol.get((int)((RubyInteger)value).getLongValue())) != null) {
            return sym;
        }
        return value;
    }
    
    private static final class Allocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new Enum(runtime, klass, null);
        }
        
        static {
            INSTANCE = new Allocator();
        }
    }
}
