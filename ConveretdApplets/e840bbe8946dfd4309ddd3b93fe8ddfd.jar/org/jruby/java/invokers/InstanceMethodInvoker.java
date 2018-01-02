// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.RubyProc;
import org.jruby.runtime.Block;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.javasupport.JavaCallable;
import org.jruby.javasupport.JavaMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.lang.reflect.Method;
import java.util.List;
import org.jruby.RubyModule;

public class InstanceMethodInvoker extends MethodInvoker
{
    public InstanceMethodInvoker(final RubyModule host, final List<Method> methods) {
        super(host, methods);
    }
    
    public InstanceMethodInvoker(final RubyModule host, final Method method) {
        super(host, method);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        int len = args.length;
        Object[] convertedArgs = new Object[len];
        final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallable(self, name, args, len);
        if (method.isVarArgs()) {
            len = method.getParameterTypes().length - 1;
            convertedArgs = new Object[len + 1];
            for (int i = 0; i < len; ++i) {
                convertedArgs[i] = RubyToJavaInvoker.convertArg(args[i], method, i);
            }
            convertedArgs[len] = RubyToJavaInvoker.convertVarargs(args, method);
        }
        else {
            convertedArgs = new Object[len];
            for (int i = 0; i < len; ++i) {
                convertedArgs[i] = RubyToJavaInvoker.convertArg(args[i], method, i);
            }
        }
        return method.invokeDirect(proxy.getObject(), convertedArgs);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityZero(self, name);
        return method.invokeDirect(proxy.getObject());
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
        if (this.javaVarargsCallables != null) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0 });
        }
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityOne(self, name, arg0);
        final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, method, 0);
        return method.invokeDirect(proxy.getObject(), cArg0);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        if (this.javaVarargsCallables != null) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0, arg1 });
        }
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityTwo(self, name, arg0, arg1);
        final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, method, 0);
        final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, method, 1);
        return method.invokeDirect(proxy.getObject(), cArg0, cArg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        if (this.javaVarargsCallables != null) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0, arg1, arg2 });
        }
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityThree(self, name, arg0, arg1, arg2);
        final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, method, 0);
        final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, method, 1);
        final Object cArg3 = RubyToJavaInvoker.convertArg(arg2, method, 2);
        return method.invokeDirect(proxy.getObject(), cArg0, cArg2, cArg3);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final int len = args.length;
            final Object[] convertedArgs = new Object[len + 1];
            final IRubyObject[] intermediate = new IRubyObject[len + 1];
            System.arraycopy(args, 0, intermediate, 0, len);
            intermediate[len] = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallable(self, name, intermediate, len + 1);
            for (int i = 0; i < len + 1; ++i) {
                convertedArgs[i] = RubyToJavaInvoker.convertArg(intermediate[i], method, i);
            }
            return method.invokeDirect(proxy.getObject(), convertedArgs);
        }
        return this.call(context, self, clazz, name, args);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityOne(self, name, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(proc, method, 0);
            return method.invokeDirect(proxy.getObject(), cArg0);
        }
        return this.call(context, self, clazz, name);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityTwo(self, name, arg0, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, method, 0);
            final Object cArg2 = RubyToJavaInvoker.convertArg(proc, method, 1);
            return method.invokeDirect(proxy.getObject(), cArg0, cArg2);
        }
        return this.call(context, self, clazz, name, arg0);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityThree(self, name, arg0, arg1, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, method, 0);
            final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, method, 1);
            final Object cArg3 = RubyToJavaInvoker.convertArg(proc, method, 2);
            return method.invokeDirect(proxy.getObject(), cArg0, cArg2, cArg3);
        }
        return this.call(context, self, clazz, name, arg0, arg1);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final org.jruby.javasupport.JavaMethod method = (org.jruby.javasupport.JavaMethod)this.findCallableArityFour(self, name, arg0, arg1, arg2, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, method, 0);
            final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, method, 1);
            final Object cArg3 = RubyToJavaInvoker.convertArg(arg2, method, 2);
            final Object cArg4 = RubyToJavaInvoker.convertArg(proc, method, 3);
            return method.invokeDirect(proxy.getObject(), cArg0, cArg2, cArg3, cArg4);
        }
        return this.call(context, self, clazz, name, arg0, arg1, arg2);
    }
}
