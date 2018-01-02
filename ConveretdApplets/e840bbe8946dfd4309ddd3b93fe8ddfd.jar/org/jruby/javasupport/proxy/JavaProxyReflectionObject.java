// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import org.jruby.RubyArray;
import org.jruby.javasupport.JavaClass;
import org.jruby.RubyString;
import org.jruby.javasupport.JavaObject;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyFixnum;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "Java::JavaProxyClass" })
public class JavaProxyReflectionObject extends RubyObject
{
    public JavaProxyReflectionObject(final Ruby runtime, final RubyClass metaClass) {
        super(runtime, metaClass, false);
    }
    
    protected static void registerRubyMethods(final Ruby runtime, final RubyClass result) {
        result.defineAnnotatedMethods(JavaProxyReflectionObject.class);
        result.getMetaClass().defineAlias("__j_allocate", "allocate");
    }
    
    @JRubyMethod
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.hashCode());
    }
    
    @JRubyMethod
    public IRubyObject to_s() {
        return this.getRuntime().newString(this.toString());
    }
    
    @JRubyMethod(name = { "==", "eql?" })
    public IRubyObject op_equal(IRubyObject other) {
        if (!(other instanceof JavaProxyReflectionObject)) {
            final Object otherObj = other.dataGetStruct();
            if (!(otherObj instanceof JavaObject)) {
                return this.getRuntime().getFalse();
            }
            other = (IRubyObject)otherObj;
        }
        final boolean isEqual = this.equals(other);
        return isEqual ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    public int hashCode() {
        return this.getClass().hashCode();
    }
    
    public String toString() {
        return this.getClass().getName();
    }
    
    public boolean equals(final Object other) {
        return this == other;
    }
    
    @JRubyMethod(name = { "equal?" })
    public IRubyObject same(IRubyObject other) {
        if (!(other instanceof JavaObject)) {
            final Object otherObj = other.dataGetStruct();
            if (!(otherObj instanceof JavaObject)) {
                return this.getRuntime().getFalse();
            }
            other = (IRubyObject)otherObj;
        }
        final boolean isSame = this == other;
        return isSame ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod
    public RubyString java_type() {
        return this.getRuntime().newString(this.getJavaClass().getName());
    }
    
    @JRubyMethod
    public IRubyObject java_class() {
        return JavaClass.get(this.getRuntime(), this.getJavaClass());
    }
    
    @JRubyMethod
    public RubyFixnum length() {
        throw this.getRuntime().newTypeError("not a java array");
    }
    
    @JRubyMethod(name = { "[]" })
    public IRubyObject aref(final IRubyObject index) {
        throw this.getRuntime().newTypeError("not a java array");
    }
    
    @JRubyMethod(name = { "[]=" })
    public IRubyObject aset(final IRubyObject index, final IRubyObject someValue) {
        throw this.getRuntime().newTypeError("not a java array");
    }
    
    @JRubyMethod(name = { "java_proxy?" })
    public IRubyObject is_java_proxy() {
        return this.getRuntime().getFalse();
    }
    
    protected RubyArray buildRubyArray(final IRubyObject[] constructors) {
        final RubyArray result = this.getRuntime().newArray(constructors.length);
        for (int i = 0; i < constructors.length; ++i) {
            result.append(constructors[i]);
        }
        return result;
    }
    
    protected RubyArray buildRubyArray(final Class[] classes) {
        final RubyArray result = this.getRuntime().newArray(classes.length);
        for (int i = 0; i < classes.length; ++i) {
            result.append(JavaClass.get(this.getRuntime(), classes[i]));
        }
        return result;
    }
}
