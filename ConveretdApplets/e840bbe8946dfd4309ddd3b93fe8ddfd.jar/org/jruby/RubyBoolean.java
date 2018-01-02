// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.anno.JRubyMethod;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "TrueClass", "FalseClass" })
public class RubyBoolean extends RubyObject
{
    public RubyBoolean(final Ruby runtime, final boolean value) {
        super(runtime, value ? runtime.getTrueClass() : runtime.getFalseClass(), false, false);
        if (!value) {
            this.flags = 1;
        }
    }
    
    public int getNativeTypeIndex() {
        return ((this.flags & 0x1) == 0x0) ? 6 : 7;
    }
    
    public boolean isImmediate() {
        return true;
    }
    
    public RubyClass getSingletonClass() {
        return this.metaClass;
    }
    
    public Class<?> getJavaClass() {
        return Boolean.TYPE;
    }
    
    public static RubyClass createFalseClass(final Ruby runtime) {
        final RubyClass falseClass = runtime.defineClass("FalseClass", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setFalseClass(falseClass);
        falseClass.index = 7;
        falseClass.setReifiedClass(RubyBoolean.class);
        falseClass.defineAnnotatedMethods(False.class);
        falseClass.getMetaClass().undefineMethod("new");
        return falseClass;
    }
    
    public static RubyClass createTrueClass(final Ruby runtime) {
        final RubyClass trueClass = runtime.defineClass("TrueClass", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setTrueClass(trueClass);
        trueClass.index = 6;
        trueClass.setReifiedClass(RubyBoolean.class);
        trueClass.defineAnnotatedMethods(True.class);
        trueClass.getMetaClass().undefineMethod("new");
        return trueClass;
    }
    
    public static RubyBoolean newBoolean(final Ruby runtime, final boolean value) {
        return value ? runtime.getTrue() : runtime.getFalse();
    }
    
    public RubyFixnum id() {
        if ((this.flags & 0x1) == 0x0) {
            return RubyFixnum.newFixnum(this.getRuntime(), 2L);
        }
        return RubyFixnum.zero(this.getRuntime());
    }
    
    public IRubyObject taint(final ThreadContext context) {
        return this;
    }
    
    public void marshalTo(final MarshalStream output) throws IOException {
        output.write(this.isTrue() ? 84 : 70);
    }
    
    public Object toJava(final Class target) {
        if (!target.isAssignableFrom(Boolean.class) && !target.equals(Boolean.TYPE)) {
            return super.toJava(target);
        }
        if (this.isFalse()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    public static class False
    {
        @JRubyMethod(name = { "&" })
        public static IRubyObject false_and(final IRubyObject f, final IRubyObject oth) {
            return f;
        }
        
        @JRubyMethod(name = { "|" })
        public static IRubyObject false_or(final IRubyObject f, final IRubyObject oth) {
            return oth.isTrue() ? f.getRuntime().getTrue() : f;
        }
        
        @JRubyMethod(name = { "^" })
        public static IRubyObject false_xor(final IRubyObject f, final IRubyObject oth) {
            return oth.isTrue() ? f.getRuntime().getTrue() : f;
        }
        
        @JRubyMethod(name = { "to_s" })
        public static IRubyObject false_to_s(final IRubyObject f) {
            return f.getRuntime().newString("false");
        }
    }
    
    public static class True
    {
        @JRubyMethod(name = { "&" })
        public static IRubyObject true_and(final IRubyObject t, final IRubyObject oth) {
            return oth.isTrue() ? t : t.getRuntime().getFalse();
        }
        
        @JRubyMethod(name = { "|" })
        public static IRubyObject true_or(final IRubyObject t, final IRubyObject oth) {
            return t;
        }
        
        @JRubyMethod(name = { "^" })
        public static IRubyObject true_xor(final IRubyObject t, final IRubyObject oth) {
            return oth.isTrue() ? t.getRuntime().getFalse() : t;
        }
        
        @JRubyMethod(name = { "to_s" })
        public static IRubyObject true_to_s(final IRubyObject t) {
            return t.getRuntime().newString("true");
        }
    }
}
