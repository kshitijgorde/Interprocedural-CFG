// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.RubyFixnum;
import org.jruby.Ruby;
import java.lang.reflect.Array;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyArray;
import org.jruby.javasupport.JavaArray;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class ArrayJavaAddons
{
    @JRubyMethod
    public static IRubyObject copy_data(final ThreadContext context, final IRubyObject rubyArray, final IRubyObject javaArray, final IRubyObject fillValue) {
        final JavaArray javaArrayJavaObj = (JavaArray)javaArray.dataGetStruct();
        Object fillJavaObject = null;
        final int javaLength = (int)javaArrayJavaObj.length().getLongValue();
        final Class targetType = javaArrayJavaObj.getComponentType();
        if (!fillValue.isNil()) {
            fillJavaObject = fillValue.toJava(targetType);
        }
        RubyArray array = null;
        int rubyLength;
        if (rubyArray instanceof RubyArray) {
            array = (RubyArray)rubyArray;
            rubyLength = ((RubyArray)rubyArray).getLength();
        }
        else {
            rubyLength = 0;
            fillJavaObject = rubyArray.toJava(targetType);
        }
        int i;
        for (i = 0; i < rubyLength && i < javaLength; ++i) {
            javaArrayJavaObj.setWithExceptionHandling(i, array.entry(i).toJava(targetType));
        }
        if (i < javaLength && fillJavaObject != null) {
            javaArrayJavaObj.fillWithExceptionHandling(i, javaLength, fillJavaObject);
        }
        return javaArray;
    }
    
    @JRubyMethod
    public static IRubyObject copy_data_simple(final ThreadContext context, final IRubyObject from, final IRubyObject to) {
        final JavaArray javaArray = (JavaArray)to.dataGetStruct();
        final RubyArray rubyArray = (RubyArray)from;
        copyDataToJavaArray(context, rubyArray, javaArray);
        return to;
    }
    
    public static void copyDataToJavaArray(final ThreadContext context, final RubyArray rubyArray, final JavaArray javaArray) {
        final int javaLength = (int)javaArray.length().getLongValue();
        final Class targetType = javaArray.getComponentType();
        for (int rubyLength = rubyArray.getLength(), i = 0; i < rubyLength && i < javaLength; ++i) {
            javaArray.setWithExceptionHandling(i, rubyArray.entry(i).toJava(targetType));
        }
    }
    
    public static void copyDataToJavaArrayDirect(final ThreadContext context, final RubyArray rubyArray, final Object javaArray) {
        final int javaLength = Array.getLength(javaArray);
        final Class targetType = javaArray.getClass().getComponentType();
        for (int rubyLength = rubyArray.getLength(), i = 0; i < rubyLength && i < javaLength; ++i) {
            Array.set(javaArray, i, rubyArray.entry(i).toJava(targetType));
        }
    }
    
    public static void copyDataToJavaArray(final ThreadContext context, final RubyArray rubyArray, final int src, final JavaArray javaArray, final int dest, final int length) {
        final Class targetType = javaArray.getComponentType();
        for (int destLength = (int)javaArray.length().getLongValue(), srcLength = rubyArray.getLength(), i = 0; src + i < srcLength && dest + i < destLength && i < length; ++i) {
            javaArray.setWithExceptionHandling(dest + i, rubyArray.entry(src + i).toJava(targetType));
        }
    }
    
    @JRubyMethod
    public static IRubyObject dimensions(final ThreadContext context, final IRubyObject maybeArray) {
        final Ruby runtime = context.getRuntime();
        if (!(maybeArray instanceof RubyArray)) {
            return runtime.newEmptyArray();
        }
        final RubyArray rubyArray = (RubyArray)maybeArray;
        final RubyArray dims = runtime.newEmptyArray();
        return dimsRecurse(context, rubyArray, dims, 0);
    }
    
    @JRubyMethod
    public static IRubyObject dimensions(final ThreadContext context, final IRubyObject maybeArray, final IRubyObject dims) {
        final Ruby runtime = context.getRuntime();
        if (!(maybeArray instanceof RubyArray)) {
            return runtime.newEmptyArray();
        }
        assert dims instanceof RubyArray;
        final RubyArray rubyArray = (RubyArray)maybeArray;
        return dimsRecurse(context, rubyArray, (RubyArray)dims, 0);
    }
    
    @JRubyMethod
    public static IRubyObject dimensions(final ThreadContext context, final IRubyObject maybeArray, final IRubyObject dims, final IRubyObject index) {
        final Ruby runtime = context.getRuntime();
        if (!(maybeArray instanceof RubyArray)) {
            return runtime.newEmptyArray();
        }
        assert dims instanceof RubyArray;
        assert index instanceof RubyFixnum;
        final RubyArray rubyArray = (RubyArray)maybeArray;
        return dimsRecurse(context, rubyArray, (RubyArray)dims, (int)((RubyFixnum)index).getLongValue());
    }
    
    private static RubyArray dimsRecurse(final ThreadContext context, final RubyArray rubyArray, final RubyArray dims, final int index) {
        final Ruby runtime = context.getRuntime();
        while (dims.size() <= index) {
            dims.append(RubyFixnum.zero(runtime));
        }
        if (rubyArray.size() > ((RubyFixnum)dims.eltInternal(index)).getLongValue()) {
            dims.eltInternalSet(index, RubyFixnum.newFixnum(runtime, rubyArray.size()));
        }
        for (int i = 0; i < rubyArray.size(); ++i) {
            if (rubyArray.eltInternal(i) instanceof RubyArray) {
                dimsRecurse(context, (RubyArray)rubyArray.eltInternal(i), dims, 1);
            }
        }
        return dims;
    }
}
