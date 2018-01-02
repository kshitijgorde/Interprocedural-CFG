// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.RubyRange;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.Block;
import org.jruby.javasupport.JavaClass;
import org.jruby.RubyInteger;
import org.jruby.RubyFixnum;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.JavaArray;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyModule;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public class ArrayJavaProxy extends JavaProxy
{
    public ArrayJavaProxy(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz);
    }
    
    public static RubyClass createArrayJavaProxy(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyClass arrayJavaProxy = runtime.defineClass("ArrayJavaProxy", runtime.getJavaSupport().getJavaProxyClass(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new ArrayJavaProxy(runtime, klazz);
            }
        });
        final RubyClass singleton = arrayJavaProxy.getSingletonClass();
        final DynamicMethod oldNew = singleton.searchMethod("new");
        singleton.addMethod("new", new ArrayNewMethod(singleton, Visibility.PUBLIC, oldNew));
        arrayJavaProxy.defineAnnotatedMethods(ArrayJavaProxy.class);
        arrayJavaProxy.includeModule(runtime.getEnumerable());
        return arrayJavaProxy;
    }
    
    public JavaArray getJavaArray() {
        return (JavaArray)this.dataGetStruct();
    }
    
    @JRubyMethod(name = { "length", "size" }, backtrace = true)
    public IRubyObject length() {
        return this.getJavaArray().length();
    }
    
    @JRubyMethod(name = { "empty?" }, backtrace = true)
    public IRubyObject empty(final ThreadContext context) {
        return RubyFixnum.zero(context.getRuntime()).eql_p(this.getJavaArray().length());
    }
    
    @JRubyMethod(name = { "[]" }, backtrace = true)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject arg) {
        if (arg instanceof RubyInteger) {
            final int index = (int)((RubyInteger)arg).getLongValue();
            return this.getJavaArray().arefDirect(index);
        }
        return this.getRange(context, arg);
    }
    
    @JRubyMethod(name = { "[]" }, required = 1, rest = true, backtrace = true)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject[] args) {
        if (args.length == 1 && args[0] instanceof RubyInteger) {
            final int index = (int)((RubyInteger)args[0]).getLongValue();
            return this.getJavaArray().arefDirect(index);
        }
        return this.getRange(context, args);
    }
    
    @JRubyMethod(name = { "[]=" }, backtrace = true)
    public IRubyObject op_aset(final ThreadContext context, final IRubyObject index, final IRubyObject value) {
        final Object converted = value.toJava(this.getJavaArray().getComponentType());
        this.getJavaArray().setWithExceptionHandling((int)index.convertToInteger().getLongValue(), converted);
        return value;
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject at(final ThreadContext context, final IRubyObject indexObj) {
        final RubyFixnum lengthF = this.getJavaArray().length();
        RubyInteger indexI = indexObj.convertToInteger();
        if (indexI.getLongValue() < 0L) {
            indexI = RubyFixnum.newFixnum(context.getRuntime(), indexI.getLongValue() + lengthF.getLongValue());
        }
        final long index = indexI.getLongValue();
        if (index >= 0L && index < lengthF.getLongValue()) {
            return this.getJavaArray().arefDirect((int)indexI.getLongValue());
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "+" }, backtrace = true)
    public IRubyObject op_plus(final ThreadContext context, final IRubyObject other) {
        final JavaClass arrayClass = JavaClass.get(context.getRuntime(), this.getJavaArray().getComponentType());
        if (other instanceof ArrayJavaProxy) {
            final JavaArray otherArray = ((ArrayJavaProxy)other).getJavaArray();
            if (this.getJavaArray().getComponentType().isAssignableFrom(otherArray.getComponentType())) {
                return arrayClass.concatArrays(context, this.getJavaArray(), otherArray);
            }
        }
        return arrayClass.concatArrays(context, this.getJavaArray(), other);
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject each(final ThreadContext context, final Block block) {
        for (int length = (int)this.getJavaArray().length().getLongValue(), i = 0; i < length; ++i) {
            final IRubyObject rubyObj = this.getJavaArray().arefDirect(i);
            block.yield(context, rubyObj);
        }
        return this;
    }
    
    @JRubyMethod(name = { "to_a", "to_ary" }, backtrace = true)
    public IRubyObject to_a(final ThreadContext context) {
        return JavaUtil.convertJavaArrayToRubyWithNesting(context, this.getObject());
    }
    
    public IRubyObject getRange(final ThreadContext context, final IRubyObject[] args) {
        if (args.length == 1) {
            return this.getRange(context, args[0]);
        }
        if (args.length == 2) {
            return this.getRange(context, args[0], args[1]);
        }
        throw context.getRuntime().newArgumentError(args.length, 1);
    }
    
    public IRubyObject getRange(final ThreadContext context, final IRubyObject arg0) {
        final int length = (int)this.getJavaArray().length().getLongValue();
        final JavaClass arrayClass = JavaClass.get(context.getRuntime(), this.getJavaArray().getComponentType());
        if (!(arg0 instanceof RubyRange)) {
            throw context.getRuntime().newTypeError(arg0, context.getRuntime().getRange());
        }
        final RubyRange range = (RubyRange)arg0;
        if (!(range.first() instanceof RubyFixnum) || !(range.last() instanceof RubyFixnum)) {
            throw context.getRuntime().newTypeError("only Fixnum ranges supported");
        }
        int first = (int)((RubyFixnum)range.first()).getLongValue();
        int last = (int)((RubyFixnum)range.last()).getLongValue();
        first = ((first >= 0) ? first : (length + first));
        last = ((last >= 0) ? last : (length + last));
        int newLength = last - first;
        if (range.exclude_end_p().isFalse()) {
            ++newLength;
        }
        if (newLength <= 0) {
            return arrayClass.emptyJavaArray(context);
        }
        return arrayClass.javaArraySubarray(context, this.getJavaArray(), first, newLength);
    }
    
    public IRubyObject getRange(final ThreadContext context, final IRubyObject firstObj, final IRubyObject lengthObj) {
        final JavaClass arrayClass = JavaClass.get(context.getRuntime(), this.getJavaArray().getComponentType());
        if (!(firstObj instanceof RubyFixnum) || !(lengthObj instanceof RubyFixnum)) {
            throw context.getRuntime().newTypeError("only Fixnum ranges supported");
        }
        int first = (int)((RubyFixnum)firstObj).getLongValue();
        final int length = (int)((RubyFixnum)lengthObj).getLongValue();
        if (length > this.getJavaArray().length().getLongValue()) {
            throw context.getRuntime().newIndexError("length specifed is longer than array");
        }
        first = ((first >= 0) ? first : ((int)this.getJavaArray().length().getLongValue() + first));
        if (length <= 0) {
            return arrayClass.emptyJavaArray(context);
        }
        return arrayClass.javaArraySubarray(context, this.getJavaArray(), first, length);
    }
    
    @Deprecated
    public IRubyObject at(final int index) {
        return this.getJavaArray().at(index);
    }
    
    public static class ArrayNewMethod extends JavaMethodOne
    {
        private DynamicMethod oldNew;
        
        public ArrayNewMethod(final RubyModule implClass, final Visibility visibility, final DynamicMethod oldNew) {
            super(implClass, visibility);
            this.oldNew = oldNew;
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
            final Ruby runtime = context.getRuntime();
            final IRubyObject proxy = this.oldNew.call(context, self, clazz, "new_proxy");
            if (arg0 instanceof JavaArray) {
                proxy.dataWrapStruct(arg0);
                return proxy;
            }
            throw runtime.newTypeError(arg0, runtime.getJavaSupport().getJavaArrayClass());
        }
    }
}
