// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.util;

import org.jruby.util.TypeConverter;
import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;

public final class ArgsUtil
{
    public static IRubyObject[] convertToJavaArray(final IRubyObject value) {
        if (value == null) {
            return IRubyObject.NULL_ARRAY;
        }
        if (value instanceof RubyArray) {
            return ((RubyArray)value).toJavaArrayMaybeUnsafe();
        }
        return new IRubyObject[] { value };
    }
    
    public static RubyArray convertToRubyArray(final Ruby runtime, final IRubyObject value, final boolean coerce) {
        if (value == null) {
            return RubyArray.newEmptyArray(runtime);
        }
        if (coerce) {
            return convertToRubyArrayWithCoerce(runtime, value);
        }
        return RubyArray.newArrayLight(runtime, value);
    }
    
    public static RubyArray convertToRubyArrayWithCoerce(final Ruby runtime, final IRubyObject value) {
        if (value instanceof RubyArray) {
            return (RubyArray)value;
        }
        final IRubyObject newValue = TypeConverter.convertToType(value, runtime.getArray(), "to_ary", false);
        if (newValue.isNil()) {
            return RubyArray.newArrayLight(runtime, value);
        }
        return (RubyArray)newValue;
    }
    
    public static IRubyObject[] popArray(final IRubyObject[] array) {
        if (array == null || array.length == 0) {
            return IRubyObject.NULL_ARRAY;
        }
        final IRubyObject[] newArray = new IRubyObject[array.length - 1];
        System.arraycopy(array, 1, newArray, 0, array.length - 1);
        return newArray;
    }
    
    public static int arrayLength(final IRubyObject node) {
        return (node instanceof RubyArray) ? ((RubyArray)node).getLength() : 0;
    }
}
