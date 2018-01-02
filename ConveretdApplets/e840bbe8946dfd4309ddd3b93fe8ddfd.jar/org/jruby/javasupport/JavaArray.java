// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.RubyInteger;
import org.jruby.runtime.builtin.IRubyObject;
import java.lang.reflect.Array;
import org.jruby.RubyFixnum;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Java::JavaArray" }, parent = "Java::JavaObject")
public class JavaArray extends JavaObject
{
    private JavaUtil.JavaConverter javaConverter;
    
    public JavaArray(final Ruby runtime, final Object array) {
        super(runtime, runtime.getJavaSupport().getJavaArrayClass(), array);
        assert array.getClass().isArray();
        this.javaConverter = JavaUtil.getJavaConverter(array.getClass().getComponentType());
    }
    
    public static RubyClass createJavaArrayClass(final Ruby runtime, final RubyModule javaModule) {
        return javaModule.defineClassUnder("JavaArray", javaModule.fastGetClass("JavaObject"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
    }
    
    public Class getComponentType() {
        return this.getValue().getClass().getComponentType();
    }
    
    public RubyFixnum length() {
        return this.getRuntime().newFixnum(this.getLength());
    }
    
    private int getLength() {
        return Array.getLength(this.getValue());
    }
    
    public boolean equals(final Object other) {
        return other instanceof JavaArray && this.getValue() == ((JavaArray)other).getValue();
    }
    
    @Deprecated
    public IRubyObject aref(final IRubyObject index) {
        if (!(index instanceof RubyInteger)) {
            throw this.getRuntime().newTypeError(index, this.getRuntime().getInteger());
        }
        final int intIndex = (int)((RubyInteger)index).getLongValue();
        if (intIndex < 0 || intIndex >= this.getLength()) {
            throw this.getRuntime().newArgumentError("index out of bounds for java array (" + intIndex + " for length " + this.getLength() + ")");
        }
        final Object result = Array.get(this.getValue(), intIndex);
        if (result == null) {
            return this.getRuntime().getNil();
        }
        return JavaObject.wrap(this.getRuntime(), result);
    }
    
    public IRubyObject arefDirect(final int intIndex) {
        return arefDirect(this.getRuntime(), this.javaConverter, this.getValue(), intIndex);
    }
    
    public static IRubyObject arefDirect(final Ruby runtime, final JavaUtil.JavaConverter converter, final Object array, final int intIndex) {
        final int length = Array.getLength(array);
        if (intIndex < 0 || intIndex >= length) {
            throw runtime.newArgumentError("index out of bounds for java array (" + intIndex + " for length " + length + ")");
        }
        return JavaUtil.convertJavaArrayElementToRuby(runtime, converter, array, intIndex);
    }
    
    @Deprecated
    public IRubyObject at(final int index) {
        final Object result = Array.get(this.getValue(), index);
        if (result == null) {
            return this.getRuntime().getNil();
        }
        return JavaObject.wrap(this.getRuntime(), result);
    }
    
    public IRubyObject aset(final IRubyObject index, final IRubyObject value) {
        if (!(index instanceof RubyInteger)) {
            throw this.getRuntime().newTypeError(index, this.getRuntime().getInteger());
        }
        final int intIndex = (int)((RubyInteger)index).getLongValue();
        if (!(value instanceof JavaObject)) {
            throw this.getRuntime().newTypeError("not a java object:" + value);
        }
        final Object javaObject = ((JavaObject)value).getValue();
        this.setWithExceptionHandling(intIndex, javaObject);
        return value;
    }
    
    public void setWithExceptionHandling(final int intIndex, final Object javaObject) {
        try {
            Array.set(this.getValue(), intIndex, javaObject);
        }
        catch (IndexOutOfBoundsException e) {
            throw this.getRuntime().newArgumentError("index out of bounds for java array (" + intIndex + " for length " + this.getLength() + ")");
        }
        catch (ArrayStoreException e2) {
            throw this.getRuntime().newTypeError("wrong element type " + javaObject.getClass() + "(array contains " + this.getValue().getClass().getComponentType().getName() + ")");
        }
        catch (IllegalArgumentException iae) {
            throw this.getRuntime().newArgumentError("wrong element type " + javaObject.getClass() + "(array contains " + this.getValue().getClass().getComponentType().getName() + ")");
        }
    }
    
    public IRubyObject afill(final IRubyObject beginIndex, final IRubyObject endIndex, final IRubyObject value) {
        if (!(beginIndex instanceof RubyInteger)) {
            throw this.getRuntime().newTypeError(beginIndex, this.getRuntime().getInteger());
        }
        final int intIndex = (int)((RubyInteger)beginIndex).getLongValue();
        if (!(endIndex instanceof RubyInteger)) {
            throw this.getRuntime().newTypeError(endIndex, this.getRuntime().getInteger());
        }
        final int intEndIndex = (int)((RubyInteger)endIndex).getLongValue();
        if (!(value instanceof JavaObject)) {
            throw this.getRuntime().newTypeError("not a java object:" + value);
        }
        final Object javaObject = ((JavaObject)value).getValue();
        this.fillWithExceptionHandling(intIndex, intEndIndex, javaObject);
        return value;
    }
    
    public void fillWithExceptionHandling(int intIndex, final int intEndIndex, final Object javaObject) {
        try {
            while (intIndex < intEndIndex) {
                Array.set(this.getValue(), intIndex, javaObject);
                ++intIndex;
            }
        }
        catch (IndexOutOfBoundsException e) {
            throw this.getRuntime().newArgumentError("index out of bounds for java array (" + intIndex + " for length " + this.getLength() + ")");
        }
        catch (ArrayStoreException e2) {
            throw this.getRuntime().newArgumentError("wrong element type " + javaObject.getClass() + "(array is " + this.getValue().getClass() + ")");
        }
    }
}
