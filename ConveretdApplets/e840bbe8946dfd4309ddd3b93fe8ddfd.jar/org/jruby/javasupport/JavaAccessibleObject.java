// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.RubyString;
import java.lang.reflect.Member;
import java.lang.annotation.Annotation;
import org.jruby.RubyBoolean;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyFixnum;
import java.lang.reflect.AccessibleObject;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.RubyObject;

public abstract class JavaAccessibleObject extends RubyObject
{
    protected JavaAccessibleObject(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
    }
    
    public static void registerRubyMethods(final Ruby runtime, final RubyClass result) {
        result.defineAnnotatedMethods(JavaAccessibleObject.class);
    }
    
    protected abstract AccessibleObject accessibleObject();
    
    public boolean equals(final Object other) {
        return other instanceof JavaAccessibleObject && this.accessibleObject() == ((JavaAccessibleObject)other).accessibleObject();
    }
    
    public int hashCode() {
        return this.accessibleObject().hashCode();
    }
    
    @JRubyMethod
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.hashCode());
    }
    
    @JRubyMethod(name = { "==", "eql?" })
    public IRubyObject op_equal(final IRubyObject other) {
        return (other instanceof JavaAccessibleObject && this.accessibleObject().equals(((JavaAccessibleObject)other).accessibleObject())) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "equal?" })
    public IRubyObject same(final IRubyObject other) {
        return this.getRuntime().newBoolean(this.equals(other));
    }
    
    @JRubyMethod(name = { "accessible?" })
    public RubyBoolean isAccessible() {
        return new RubyBoolean(this.getRuntime(), this.accessibleObject().isAccessible());
    }
    
    @JRubyMethod(name = { "accessible=" })
    public IRubyObject setAccessible(final IRubyObject object) {
        this.accessibleObject().setAccessible(object.isTrue());
        return object;
    }
    
    @JRubyMethod
    public IRubyObject annotation(final IRubyObject annoClass) {
        if (!(annoClass instanceof JavaClass)) {
            throw this.getRuntime().newTypeError(annoClass, this.getRuntime().getJavaSupport().getJavaClassClass());
        }
        return Java.getInstance(this.getRuntime(), this.accessibleObject().getAnnotation((Class<Object>)((JavaClass)annoClass).javaClass()));
    }
    
    @JRubyMethod
    public IRubyObject annotations() {
        return Java.getInstance(this.getRuntime(), this.accessibleObject().getAnnotations());
    }
    
    @JRubyMethod(name = { "annotations?" })
    public RubyBoolean annotations_p() {
        return this.getRuntime().newBoolean(this.accessibleObject().getAnnotations().length > 0);
    }
    
    @JRubyMethod
    public IRubyObject declared_annotations() {
        return Java.getInstance(this.getRuntime(), this.accessibleObject().getDeclaredAnnotations());
    }
    
    @JRubyMethod(name = { "declared_annotations?" })
    public RubyBoolean declared_annotations_p() {
        return this.getRuntime().newBoolean(this.accessibleObject().getDeclaredAnnotations().length > 0);
    }
    
    @JRubyMethod(name = { "annotation_present?" })
    public IRubyObject annotation_present_p(final IRubyObject annoClass) {
        if (!(annoClass instanceof JavaClass)) {
            throw this.getRuntime().newTypeError(annoClass, this.getRuntime().getJavaSupport().getJavaClassClass());
        }
        return this.getRuntime().newBoolean(this.accessibleObject().isAnnotationPresent(((JavaClass)annoClass).javaClass()));
    }
    
    @JRubyMethod
    public IRubyObject declaring_class() {
        final Class<?> clazz = ((Member)this.accessibleObject()).getDeclaringClass();
        if (clazz != null) {
            return JavaClass.get(this.getRuntime(), clazz);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public IRubyObject modifiers() {
        return this.getRuntime().newFixnum(((Member)this.accessibleObject()).getModifiers());
    }
    
    @JRubyMethod
    public IRubyObject name() {
        return this.getRuntime().newString(((Member)this.accessibleObject()).getName());
    }
    
    @JRubyMethod(name = { "synthetic?" })
    public IRubyObject synthetic_p() {
        return this.getRuntime().newBoolean(((Member)this.accessibleObject()).isSynthetic());
    }
    
    @JRubyMethod(name = { "to_s", "to_string" })
    public RubyString to_string() {
        return this.getRuntime().newString(this.accessibleObject().toString());
    }
}
