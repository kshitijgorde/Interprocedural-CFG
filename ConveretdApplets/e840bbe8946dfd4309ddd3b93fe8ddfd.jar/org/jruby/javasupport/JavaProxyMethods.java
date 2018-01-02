// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.Block;
import org.jruby.RubyFixnum;
import org.jruby.RubyBasicObject;
import org.jruby.RubyObject;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;

public class JavaProxyMethods
{
    public static RubyModule createJavaProxyMethods(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyModule javaProxyMethods = runtime.defineModule("JavaProxyMethods");
        javaProxyMethods.defineAnnotatedMethods(JavaProxyMethods.class);
        return javaProxyMethods;
    }
    
    @JRubyMethod
    public static IRubyObject java_class(final ThreadContext context, final IRubyObject recv) {
        return recv.getMetaClass().getRealClass().fastGetInstanceVariable("@java_class");
    }
    
    @JRubyMethod
    public static IRubyObject java_object(final ThreadContext context, final IRubyObject recv) {
        return (IRubyObject)recv.dataGetStruct();
    }
    
    @JRubyMethod(name = { "java_object=" })
    public static IRubyObject java_object_set(final ThreadContext context, final IRubyObject recv, final IRubyObject obj) {
        recv.dataWrapStruct(obj);
        return obj;
    }
    
    @JRubyMethod(name = { "==" })
    public static IRubyObject op_equal(final IRubyObject recv, final IRubyObject rhs) {
        if (recv instanceof JavaProxy) {
            return JavaObject.op_equal((JavaProxy)recv, rhs);
        }
        return ((JavaObject)recv.dataGetStruct()).op_equal(rhs);
    }
    
    @JRubyMethod
    public static IRubyObject to_s(final IRubyObject recv) {
        if (recv instanceof JavaProxy) {
            return JavaObject.to_s(recv.getRuntime(), ((JavaProxy)recv).getObject());
        }
        if (recv.dataGetStruct() != null) {
            return ((JavaObject)recv.dataGetStruct()).to_s();
        }
        return ((RubyObject)recv).to_s();
    }
    
    @JRubyMethod
    public static IRubyObject inspect(final IRubyObject recv) {
        if (recv instanceof RubyBasicObject) {
            return ((RubyBasicObject)recv).hashyInspect();
        }
        return recv.inspect();
    }
    
    @JRubyMethod(name = { "eql?" })
    public static IRubyObject op_eql(final IRubyObject recv, final IRubyObject rhs) {
        return op_equal(recv, rhs);
    }
    
    @JRubyMethod
    public static IRubyObject hash(final IRubyObject recv) {
        if (recv instanceof JavaProxy) {
            return RubyFixnum.newFixnum(recv.getRuntime(), ((JavaProxy)recv).getObject().hashCode());
        }
        return ((JavaObject)recv.dataGetStruct()).hash();
    }
    
    @JRubyMethod
    public static IRubyObject to_java_object(final IRubyObject recv) {
        return (JavaObject)recv.dataGetStruct();
    }
    
    @JRubyMethod(name = { "synchronized" })
    public static IRubyObject rbSynchronized(final ThreadContext context, final IRubyObject recv, final Block block) {
        if (recv instanceof JavaProxy) {
            return JavaObject.ruby_synchronized(context, ((JavaProxy)recv).getObject(), block);
        }
        return ((JavaObject)recv.dataGetStruct()).ruby_synchronized(context, block);
    }
}
