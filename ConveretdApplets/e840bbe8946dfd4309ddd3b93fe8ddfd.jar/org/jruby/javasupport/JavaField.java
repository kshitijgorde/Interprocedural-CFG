// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import java.lang.reflect.AccessibleObject;
import org.jruby.runtime.ThreadContext;
import java.lang.reflect.Modifier;
import org.jruby.RubyBoolean;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyString;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import java.lang.reflect.Field;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Java::JavaField" })
public class JavaField extends JavaAccessibleObject
{
    private Field field;
    
    public Object getValue() {
        return this.field;
    }
    
    public static RubyClass createJavaFieldClass(final Ruby runtime, final RubyModule javaModule) {
        final RubyClass result = javaModule.defineClassUnder("JavaField", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        JavaAccessibleObject.registerRubyMethods(runtime, result);
        result.defineAnnotatedMethods(JavaField.class);
        return result;
    }
    
    public JavaField(final Ruby runtime, final Field field) {
        super(runtime, runtime.getJavaSupport().getJavaFieldClass());
        this.field = field;
    }
    
    public boolean equals(final Object other) {
        return other instanceof JavaField && this.field == ((JavaField)other).field;
    }
    
    public int hashCode() {
        return this.field.hashCode();
    }
    
    @JRubyMethod
    public RubyString value_type() {
        return this.getRuntime().newString(this.field.getType().getName());
    }
    
    @JRubyMethod(name = { "==", "===" })
    public IRubyObject op_equal(final IRubyObject other) {
        if (!(other instanceof JavaField)) {
            return this.getRuntime().getFalse();
        }
        return this.getRuntime().newBoolean(this.field.equals(((JavaField)other).field));
    }
    
    @JRubyMethod(name = { "public?" })
    public RubyBoolean public_p() {
        return this.getRuntime().newBoolean(Modifier.isPublic(this.field.getModifiers()));
    }
    
    @JRubyMethod(name = { "static?" })
    public RubyBoolean static_p() {
        return this.getRuntime().newBoolean(Modifier.isStatic(this.field.getModifiers()));
    }
    
    @JRubyMethod(name = { "enum_constant?" })
    public RubyBoolean enum_constant_p() {
        return this.getRuntime().newBoolean(this.field.isEnumConstant());
    }
    
    @JRubyMethod
    public RubyString to_generic_string() {
        return this.getRuntime().newString(this.field.toGenericString());
    }
    
    @JRubyMethod(name = { "type" })
    public IRubyObject field_type() {
        return JavaClass.get(this.getRuntime(), this.field.getType());
    }
    
    @JRubyMethod
    public IRubyObject value(final ThreadContext context, final IRubyObject object) {
        final Ruby runtime = context.getRuntime();
        Object javaObject = null;
        if (!Modifier.isStatic(this.field.getModifiers())) {
            javaObject = JavaUtil.unwrapJavaValue(runtime, object, "not a java object");
        }
        try {
            return JavaUtil.convertJavaToUsableRubyObject(runtime, this.field.get(javaObject));
        }
        catch (IllegalAccessException iae) {
            throw runtime.newTypeError("illegal access");
        }
    }
    
    @JRubyMethod
    public IRubyObject set_value(final IRubyObject object, final IRubyObject value) {
        Object javaObject = null;
        if (!Modifier.isStatic(this.field.getModifiers())) {
            javaObject = JavaUtil.unwrapJavaValue(this.getRuntime(), object, "not a java object: " + object);
        }
        IRubyObject val = value;
        if (val.dataGetStruct() instanceof JavaObject) {
            val = (IRubyObject)val.dataGetStruct();
        }
        try {
            final Object convertedValue = val.toJava(this.field.getType());
            this.field.set(javaObject, convertedValue);
        }
        catch (IllegalAccessException iae) {
            throw this.getRuntime().newTypeError("illegal access on setting variable: " + iae.getMessage());
        }
        catch (IllegalArgumentException iae2) {
            throw this.getRuntime().newTypeError("wrong type for " + this.field.getType().getName() + ": " + val.getClass().getName());
        }
        return val;
    }
    
    @JRubyMethod(name = { "final?" })
    public RubyBoolean final_p() {
        return this.getRuntime().newBoolean(Modifier.isFinal(this.field.getModifiers()));
    }
    
    @JRubyMethod
    public JavaObject static_value() {
        try {
            if (!Ruby.isSecurityRestricted()) {
                this.field.setAccessible(true);
            }
            return JavaObject.wrap(this.getRuntime(), this.field.get(null));
        }
        catch (IllegalAccessException iae) {
            throw this.getRuntime().newTypeError("illegal static value access: " + iae.getMessage());
        }
    }
    
    @JRubyMethod
    public JavaObject set_static_value(final IRubyObject value) {
        if (!(value instanceof JavaObject)) {
            throw this.getRuntime().newTypeError("not a java object:" + value);
        }
        try {
            final Object convertedValue = value.toJava(this.field.getType());
            this.field.set(null, convertedValue);
        }
        catch (IllegalAccessException iae) {
            throw this.getRuntime().newTypeError("illegal access on setting static variable: " + iae.getMessage());
        }
        catch (IllegalArgumentException iae2) {
            throw this.getRuntime().newTypeError("wrong type for " + this.field.getType().getName() + ": " + ((JavaObject)value).getValue().getClass().getName());
        }
        return (JavaObject)value;
    }
    
    @JRubyMethod
    public RubyString name() {
        return this.getRuntime().newString(this.field.getName());
    }
    
    protected AccessibleObject accessibleObject() {
        return this.field;
    }
}
