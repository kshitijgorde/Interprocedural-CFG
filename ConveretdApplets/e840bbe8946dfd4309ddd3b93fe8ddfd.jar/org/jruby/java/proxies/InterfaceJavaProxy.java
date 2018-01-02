// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.anno.JRubyMethod;
import org.jruby.RubyProc;
import org.jruby.javasupport.JavaClass;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public class InterfaceJavaProxy extends JavaProxy
{
    public InterfaceJavaProxy(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz);
    }
    
    public static RubyClass createInterfaceJavaProxy(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyClass ifcJavaProxy = runtime.defineClass("InterfaceJavaProxy", runtime.getJavaSupport().getJavaProxyClass(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new InterfaceJavaProxy(runtime, klazz);
            }
        });
        final RubyClass javaIfcExtender = runtime.defineClass("JavaInterfaceExtender", runtime.getObject(), runtime.getObject().getAllocator());
        javaIfcExtender.defineAnnotatedMethods(JavaInterfaceExtender.class);
        return ifcJavaProxy;
    }
    
    public static class JavaInterfaceExtender
    {
        @JRubyMethod(backtrace = true)
        public static IRubyObject initialize(final ThreadContext context, final IRubyObject self, final IRubyObject javaClassName, final Block block) {
            final Ruby runtime = context.getRuntime();
            self.getInstanceVariables().setInstanceVariable("@java_class", JavaClass.forNameVerbose(runtime, javaClassName.asJavaString()));
            self.getInstanceVariables().setInstanceVariable("@block", RubyProc.newProc(runtime, block, Block.Type.PROC));
            return runtime.getNil();
        }
        
        @JRubyMethod(backtrace = true)
        public static IRubyObject extend_proxy(final ThreadContext context, final IRubyObject self, final IRubyObject proxyClass) {
            return proxyClass.callMethod(context, "class_eval", IRubyObject.NULL_ARRAY, ((RubyProc)self.getInstanceVariables().getInstanceVariable("@block")).getBlock());
        }
    }
}
