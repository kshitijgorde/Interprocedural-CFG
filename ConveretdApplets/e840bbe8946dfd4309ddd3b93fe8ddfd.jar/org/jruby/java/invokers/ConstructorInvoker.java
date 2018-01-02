// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.RubyProc;
import org.jruby.runtime.Block;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.javasupport.JavaConstructor;
import org.jruby.javasupport.JavaCallable;
import org.jruby.Ruby;
import java.lang.reflect.Member;
import java.lang.reflect.Constructor;
import java.util.List;
import org.jruby.RubyModule;

public class ConstructorInvoker extends RubyToJavaInvoker
{
    public ConstructorInvoker(final RubyModule host, final List<Constructor> ctors) {
        super(host, ctors.toArray(new Constructor[ctors.size()]));
        RubyToJavaInvoker.trySetAccessible(this.getAccessibleObjects());
    }
    
    protected JavaCallable createCallable(final Ruby ruby, final Member member) {
        return JavaConstructor.create(ruby, (Constructor<?>)member);
    }
    
    protected JavaCallable[] createCallableArray(final JavaCallable callable) {
        return new JavaConstructor[] { (JavaConstructor)callable };
    }
    
    protected JavaCallable[] createCallableArray(final int size) {
        return new JavaConstructor[size];
    }
    
    protected JavaCallable[][] createCallableArrayArray(final int size) {
        return new JavaConstructor[size][];
    }
    
    protected Class[] getMemberParameterTypes(final Member member) {
        return ((Constructor)member).getParameterTypes();
    }
    
    protected boolean isMemberVarArgs(final Member member) {
        return ((Constructor)member).isVarArgs();
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        int len = args.length;
        Object[] convertedArgs = new Object[len];
        final JavaConstructor constructor = (JavaConstructor)this.findCallable(self, name, args, len);
        if (constructor.isVarArgs()) {
            len = constructor.getParameterTypes().length - 1;
            convertedArgs = new Object[len + 1];
            for (int i = 0; i < len; ++i) {
                convertedArgs[i] = RubyToJavaInvoker.convertArg(args[i], constructor, i);
            }
            convertedArgs[len] = RubyToJavaInvoker.convertVarargs(args, constructor);
        }
        else {
            convertedArgs = new Object[len];
            for (int i = 0; i < len; ++i) {
                convertedArgs[i] = RubyToJavaInvoker.convertArg(args[i], constructor, i);
            }
        }
        proxy.setObject(constructor.newInstanceDirect(convertedArgs));
        return self;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final JavaConstructor constructor = (JavaConstructor)this.findCallableArityZero(self, name);
        proxy.setObject(constructor.newInstanceDirect());
        return self;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
        if (this.javaVarargsCallables != null) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0 });
        }
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final JavaConstructor constructor = (JavaConstructor)this.findCallableArityOne(self, name, arg0);
        final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, constructor, 0);
        proxy.setObject(constructor.newInstanceDirect(cArg0));
        return self;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        if (this.javaVarargsCallables != null) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0, arg1 });
        }
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final JavaConstructor constructor = (JavaConstructor)this.findCallableArityTwo(self, name, arg0, arg1);
        final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, constructor, 0);
        final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, constructor, 1);
        proxy.setObject(constructor.newInstanceDirect(cArg0, cArg2));
        return self;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        if (this.javaVarargsCallables != null) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0, arg1, arg2 });
        }
        final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
        final JavaConstructor constructor = (JavaConstructor)this.findCallableArityThree(self, name, arg0, arg1, arg2);
        final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, constructor, 0);
        final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, constructor, 1);
        final Object cArg3 = RubyToJavaInvoker.convertArg(arg2, constructor, 2);
        proxy.setObject(constructor.newInstanceDirect(cArg0, cArg2, cArg3));
        return self;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        if (block.isGiven()) {
            final Ruby runtime = context.getRuntime();
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final int len = args.length;
            final Object[] convertedArgs = new Object[len + 1];
            final IRubyObject[] intermediate = new IRubyObject[len + 1];
            System.arraycopy(args, 0, intermediate, 0, len);
            intermediate[len] = RubyProc.newProc(runtime, block, Block.Type.LAMBDA);
            final JavaConstructor constructor = (JavaConstructor)this.findCallable(self, name, intermediate, len + 1);
            for (int i = 0; i < len + 1; ++i) {
                convertedArgs[i] = RubyToJavaInvoker.convertArg(intermediate[i], constructor, i);
            }
            proxy.setObject(constructor.newInstanceDirect(convertedArgs));
            return self;
        }
        return this.call(context, self, clazz, name, args);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final JavaConstructor constructor = (JavaConstructor)this.findCallableArityOne(self, name, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(proc, constructor, 0);
            proxy.setObject(constructor.newInstanceDirect(cArg0));
            return self;
        }
        return this.call(context, self, clazz, name);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final JavaConstructor constructor = (JavaConstructor)this.findCallableArityTwo(self, name, arg0, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, constructor, 0);
            final Object cArg2 = RubyToJavaInvoker.convertArg(proc, constructor, 1);
            proxy.setObject(constructor.newInstanceDirect(cArg0, cArg2));
            return self;
        }
        return this.call(context, self, clazz, name, arg0);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final JavaConstructor constructor = (JavaConstructor)this.findCallableArityThree(self, name, arg0, arg1, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, constructor, 0);
            final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, constructor, 1);
            final Object cArg3 = RubyToJavaInvoker.convertArg(proc, constructor, 2);
            proxy.setObject(constructor.newInstanceDirect(cArg0, cArg2, cArg3));
            return self;
        }
        return this.call(context, self, clazz, name, arg0, arg1);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        if (block.isGiven()) {
            final JavaProxy proxy = RubyToJavaInvoker.castJavaProxy(self);
            final RubyProc proc = RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
            final JavaConstructor constructor = (JavaConstructor)this.findCallableArityFour(self, name, arg0, arg1, arg2, proc);
            final Object cArg0 = RubyToJavaInvoker.convertArg(arg0, constructor, 0);
            final Object cArg2 = RubyToJavaInvoker.convertArg(arg1, constructor, 1);
            final Object cArg3 = RubyToJavaInvoker.convertArg(arg2, constructor, 2);
            final Object cArg4 = RubyToJavaInvoker.convertArg(proc, constructor, 3);
            proxy.setObject(constructor.newInstanceDirect(cArg0, cArg2, cArg3, cArg4));
            return self;
        }
        return this.call(context, self, clazz, name, arg0, arg1, arg2);
    }
}
