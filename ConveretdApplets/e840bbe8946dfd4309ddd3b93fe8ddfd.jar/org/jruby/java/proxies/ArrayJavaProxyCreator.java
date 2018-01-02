// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.javasupport.Java;
import java.lang.reflect.Array;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyObject;

public class ArrayJavaProxyCreator extends RubyObject
{
    private static final int[] EMPTY;
    Class elementClass;
    int[] dimensions;
    
    public static RubyClass createArrayJavaProxyCreator(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyClass arrayJavaProxyCreator = runtime.defineClass("ArrayJavaProxyCreator", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        arrayJavaProxyCreator.defineAnnotatedMethods(ArrayJavaProxyCreator.class);
        return arrayJavaProxyCreator;
    }
    
    public ArrayJavaProxyCreator(final Ruby runtime) {
        super(runtime, runtime.getJavaSupport().getArrayJavaProxyCreatorClass());
        this.dimensions = ArrayJavaProxyCreator.EMPTY;
    }
    
    public void setup(final ThreadContext context, final IRubyObject javaClass, final IRubyObject[] sizes) {
        this.elementClass = (Class)javaClass.toJava(Class.class);
        this.aggregateDimensions(sizes);
    }
    
    @JRubyMethod(required = 1, rest = true)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject[] sizes) {
        Arity.checkArgumentCount(context.getRuntime(), sizes, 1, -1);
        this.aggregateDimensions(sizes);
        return this;
    }
    
    @JRubyMethod(name = { "new" })
    public IRubyObject _new(final ThreadContext context) {
        final Object array = Array.newInstance(this.elementClass, this.dimensions);
        return Java.wrapJavaObject(context.runtime, array);
    }
    
    private void aggregateDimensions(final IRubyObject[] sizes) {
        final int[] newDimensions = new int[this.dimensions.length + sizes.length];
        System.arraycopy(this.dimensions, 0, newDimensions, 0, this.dimensions.length);
        for (int i = 0; i < sizes.length; ++i) {
            final IRubyObject size = sizes[i];
            final int intSize = (int)size.convertToInteger().getLongValue();
            newDimensions[i + this.dimensions.length] = intSize;
        }
        this.dimensions = newDimensions;
    }
    
    static {
        EMPTY = new int[0];
    }
}
