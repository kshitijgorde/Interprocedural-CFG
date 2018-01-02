// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "JavaUtilities" })
public class JavaUtilities
{
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject set_java_object(final IRubyObject recv, final IRubyObject self, final IRubyObject java_object) {
        self.dataWrapStruct(java_object);
        return java_object;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject get_interface_module(final IRubyObject recv, final IRubyObject arg0) {
        return Java.get_interface_module(recv.getRuntime(), arg0);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject get_package_module(final IRubyObject recv, final IRubyObject arg0) {
        return Java.get_package_module(recv, arg0);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject get_package_module_dot_format(final IRubyObject recv, final IRubyObject arg0) {
        return Java.get_package_module_dot_format(recv, arg0);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject get_proxy_class(final IRubyObject recv, final IRubyObject arg0) {
        return Java.get_proxy_class(recv, arg0);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject create_proxy_class(final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return Java.create_proxy_class(recv, arg0, arg1, arg2);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject get_java_class(final IRubyObject recv, final IRubyObject arg0) {
        return Java.get_java_class(recv, arg0);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject get_top_level_proxy_or_package(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0) {
        return Java.get_top_level_proxy_or_package(context, recv, arg0);
    }
    
    @JRubyMethod(module = true, backtrace = true, visibility = Visibility.PRIVATE)
    public static IRubyObject get_proxy_or_package_under_package(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1) {
        return Java.get_proxy_or_package_under_package(context, recv, arg0, arg1);
    }
}
