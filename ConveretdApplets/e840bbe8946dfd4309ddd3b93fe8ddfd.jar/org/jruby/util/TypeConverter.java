// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.exceptions.RaiseException;
import org.jruby.Ruby;
import org.jruby.RubyBoolean;
import org.jruby.runtime.marshal.DataType;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;

public class TypeConverter
{
    @Deprecated
    public static final IRubyObject convertToType(final IRubyObject obj, final RubyClass target, final int convertMethodIndex, final String convertMethod, final boolean raise) {
        if (!obj.respondsTo(convertMethod)) {
            return handleUncoercibleObject(raise, obj, target);
        }
        return obj.callMethod(obj.getRuntime().getCurrentContext(), convertMethod);
    }
    
    public static final IRubyObject convertToType(final IRubyObject obj, final RubyClass target, final String convertMethod, final boolean raise) {
        if (!obj.respondsTo(convertMethod)) {
            return handleUncoercibleObject(raise, obj, target);
        }
        return obj.callMethod(obj.getRuntime().getCurrentContext(), convertMethod);
    }
    
    public static final IRubyObject convertToType19(final IRubyObject obj, final RubyClass target, final String convertMethod, final boolean raise) {
        final IRubyObject r = obj.checkCallMethod(obj.getRuntime().getCurrentContext(), convertMethod);
        if (r == null) {
            return handleUncoercibleObject(raise, obj, target);
        }
        return r;
    }
    
    @Deprecated
    public static final IRubyObject convertToType(final IRubyObject obj, final RubyClass target, final int convertMethodIndex, final String convertMethod) {
        if (target.isInstance(obj)) {
            return obj;
        }
        final IRubyObject val = convertToType(obj, target, convertMethod, true);
        if (!target.isInstance(val)) {
            throw obj.getRuntime().newTypeError(obj.getMetaClass() + "#" + convertMethod + " should return " + target.getName());
        }
        return val;
    }
    
    public static final IRubyObject convertToType(final IRubyObject obj, final RubyClass target, final String convertMethod) {
        if (target.isInstance(obj)) {
            return obj;
        }
        final IRubyObject val = convertToType(obj, target, convertMethod, true);
        if (!target.isInstance(val)) {
            throw obj.getRuntime().newTypeError(obj.getMetaClass() + "#" + convertMethod + " should return " + target.getName());
        }
        return val;
    }
    
    public static final IRubyObject convertToType19(final IRubyObject obj, final RubyClass target, final String convertMethod) {
        if (target.isInstance(obj)) {
            return obj;
        }
        final IRubyObject val = convertToType19(obj, target, convertMethod, true);
        if (!target.isInstance(val)) {
            final String cname = obj.getMetaClass().toString();
            throw obj.getRuntime().newTypeError("can't convert " + cname + " to " + target.getName() + " (" + cname + "#" + convertMethod + " gives " + val.getMetaClass() + ")");
        }
        return val;
    }
    
    public static final IRubyObject checkData(final IRubyObject obj) {
        if (obj instanceof DataType) {
            return obj;
        }
        String type;
        if (obj.isNil()) {
            type = "nil";
        }
        else if (obj instanceof RubyBoolean) {
            type = (obj.isTrue() ? "true" : "false");
        }
        else {
            type = obj.getMetaClass().getRealClass().getName();
        }
        throw obj.getRuntime().newTypeError("wrong argument type " + type + " (expected Data)");
    }
    
    @Deprecated
    public static final IRubyObject convertToTypeWithCheck(final IRubyObject obj, final RubyClass target, final int convertMethodIndex, final String convertMethod) {
        if (target.isInstance(obj)) {
            return obj;
        }
        final IRubyObject val = convertToType(obj, target, convertMethod, false);
        if (val.isNil()) {
            return val;
        }
        if (!target.isInstance(val)) {
            throw obj.getRuntime().newTypeError(obj.getMetaClass() + "#" + convertMethod + " should return " + target.getName());
        }
        return val;
    }
    
    public static final IRubyObject convertToTypeWithCheck(final IRubyObject obj, final RubyClass target, final String convertMethod) {
        if (target.isInstance(obj)) {
            return obj;
        }
        final IRubyObject val = convertToType(obj, target, convertMethod, false);
        if (val.isNil()) {
            return val;
        }
        if (!target.isInstance(val)) {
            throw obj.getRuntime().newTypeError(obj.getMetaClass() + "#" + convertMethod + " should return " + target.getName());
        }
        return val;
    }
    
    public static final IRubyObject convertToTypeWithCheck19(final IRubyObject obj, final RubyClass target, final String convertMethod) {
        if (target.isInstance(obj)) {
            return obj;
        }
        final IRubyObject val = convertToType19(obj, target, convertMethod, false);
        if (val.isNil()) {
            return val;
        }
        if (!target.isInstance(val)) {
            throw obj.getRuntime().newTypeError(obj.getMetaClass() + "#" + convertMethod + " should return " + target.getName());
        }
        return val;
    }
    
    public static final IRubyObject convertToTypeOrRaise(final IRubyObject obj, final RubyClass target, final String convertMethod) {
        if (target.isInstance(obj)) {
            return obj;
        }
        final IRubyObject val = convertToType(obj, target, convertMethod, true);
        if (val.isNil()) {
            return val;
        }
        if (!target.isInstance(val)) {
            throw obj.getRuntime().newTypeError(obj.getMetaClass() + "#" + convertMethod + " should return " + target.getName());
        }
        return val;
    }
    
    public static IRubyObject checkHashType(final Ruby runtime, final IRubyObject obj) {
        return convertToTypeWithCheck(obj, runtime.getHash(), "to_hash");
    }
    
    public static IRubyObject handleUncoercibleObject(final boolean raise, final IRubyObject obj, final RubyClass target) throws RaiseException {
        if (raise) {
            String type;
            if (obj.isNil()) {
                type = "nil";
            }
            else if (obj instanceof RubyBoolean) {
                type = (obj.isTrue() ? "true" : "false");
            }
            else {
                type = obj.getMetaClass().getRealClass().getName();
            }
            throw obj.getRuntime().newTypeError("can't convert " + type + " into " + target);
        }
        return obj.getRuntime().getNil();
    }
}
