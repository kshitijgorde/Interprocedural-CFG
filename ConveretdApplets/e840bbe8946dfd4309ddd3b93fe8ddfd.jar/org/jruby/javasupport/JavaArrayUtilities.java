// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.java.proxies.ArrayJavaProxy;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyString;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.ByteList;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "JavaArrayUtilities" })
public class JavaArrayUtilities
{
    public static RubyModule createJavaArrayUtilitiesModule(final Ruby runtime) {
        final RubyModule javaArrayUtils = runtime.defineModule("JavaArrayUtilities");
        javaArrayUtils.defineAnnotatedMethods(JavaArrayUtilities.class);
        return javaArrayUtils;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject bytes_to_ruby_string(final IRubyObject recv, final IRubyObject wrappedObject) {
        final Ruby runtime = recv.getRuntime();
        byte[] bytes = null;
        if (wrappedObject instanceof JavaProxy) {
            final Object wrapped = ((JavaProxy)wrappedObject).getObject();
            if (wrapped instanceof byte[]) {
                bytes = (byte[])wrapped;
            }
        }
        else {
            final IRubyObject byteArray = (JavaObject)wrappedObject.dataGetStruct();
            if (byteArray instanceof JavaArray && ((JavaArray)byteArray).getValue() instanceof byte[]) {
                bytes = (byte[])((JavaArray)byteArray).getValue();
            }
        }
        if (bytes == null) {
            throw runtime.newTypeError("wrong argument type " + wrappedObject.getMetaClass() + " (expected byte[])");
        }
        return runtime.newString(new ByteList(bytes, true));
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject ruby_string_to_bytes(final IRubyObject recv, final IRubyObject string) {
        final Ruby runtime = recv.getRuntime();
        if (!(string instanceof RubyString)) {
            throw runtime.newTypeError(string, runtime.getString());
        }
        return JavaUtil.convertJavaToUsableRubyObject(runtime, ((RubyString)string).getBytes());
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject java_to_ruby(final ThreadContext context, final IRubyObject recv, final IRubyObject ary) {
        if (!(ary instanceof ArrayJavaProxy)) {
            throw context.runtime.newTypeError(ary, context.runtime.getJavaSupport().getArrayProxyClass());
        }
        return ((ArrayJavaProxy)ary).to_a(context);
    }
}
