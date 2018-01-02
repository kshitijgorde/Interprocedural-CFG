// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import org.jruby.exceptions.MainExitException;
import org.jruby.exceptions.ThreadKill;
import org.jruby.exceptions.JumpException;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Arity;
import java.lang.reflect.Method;

public class ReflectionCallback implements Callback
{
    private Method method;
    private Class type;
    private String methodName;
    private Class[] argumentTypes;
    private boolean isRestArgs;
    private Arity arity;
    private boolean isStaticMethod;
    private boolean fast;
    
    public ReflectionCallback(final Class type, final String methodName, final Class[] argumentTypes, final boolean isRestArgs, final boolean isStaticMethod, final Arity arity, final boolean fast) {
        this.type = type;
        this.methodName = methodName;
        this.argumentTypes = argumentTypes;
        this.isRestArgs = isRestArgs;
        this.isStaticMethod = isStaticMethod;
        this.arity = arity;
        this.fast = fast;
        assert type != null;
        assert methodName != null;
        assert arity != null;
        this.loadMethod(fast);
    }
    
    private void loadMethod(final boolean fast) {
        Class[] args;
        if (this.isStaticMethod) {
            final Class[] types = new Class[this.argumentTypes.length + 1];
            System.arraycopy(this.argumentTypes, 0, types, 1, this.argumentTypes.length);
            types[0] = IRubyObject.class;
            args = types;
        }
        else {
            args = this.argumentTypes;
        }
        if (!fast) {
            final Class[] types = new Class[args.length + 1];
            System.arraycopy(args, 0, types, 0, args.length);
            types[args.length] = Block.class;
            args = types;
        }
        try {
            this.method = this.type.getMethod(this.methodName, (Class[])args);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException("NoSuchMethodException: Cannot get method \"" + this.methodName + "\" in class \"" + this.type.getName() + "\" by Reflection.");
        }
        catch (SecurityException e2) {
            throw new RuntimeException("SecurityException: Cannot get method \"" + this.methodName + "\" in class \"" + this.type.getName() + "\" by Reflection.");
        }
    }
    
    protected final Object[] packageRestArgumentsForReflection(final Object[] originalArgs) {
        final IRubyObject[] restArray = new IRubyObject[originalArgs.length - (this.argumentTypes.length - 1)];
        final Object[] result = new Object[this.argumentTypes.length];
        try {
            System.arraycopy(originalArgs, this.argumentTypes.length - 1, restArray, 0, originalArgs.length - (this.argumentTypes.length - 1));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            assert false : e;
            return null;
        }
        System.arraycopy(originalArgs, 0, result, 0, this.argumentTypes.length - 1);
        result[this.argumentTypes.length - 1] = restArray;
        return result;
    }
    
    public IRubyObject execute(final IRubyObject recv, final IRubyObject[] oargs, final Block block) {
        this.arity.checkArity(recv.getRuntime(), oargs);
        Object[] methodArgs = oargs;
        if (this.isRestArgs) {
            methodArgs = this.packageRestArgumentsForReflection(methodArgs);
        }
        try {
            IRubyObject receiver = recv;
            if (this.isStaticMethod) {
                final Object[] args = new Object[methodArgs.length + (this.fast ? 1 : 2)];
                System.arraycopy(methodArgs, 0, args, 1, methodArgs.length);
                args[0] = recv;
                if (!this.fast) {
                    args[methodArgs.length + 1] = block;
                }
                receiver = null;
                methodArgs = args;
            }
            else {
                final Object[] args = new Object[methodArgs.length + (this.fast ? 0 : 1)];
                System.arraycopy(methodArgs, 0, args, 0, methodArgs.length);
                if (!this.fast) {
                    args[methodArgs.length] = block;
                }
                methodArgs = args;
            }
            return (IRubyObject)this.method.invoke(receiver, methodArgs);
        }
        catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof RaiseException) {
                throw (RaiseException)e.getTargetException();
            }
            if (e.getTargetException() instanceof JumpException) {
                throw (JumpException)e.getTargetException();
            }
            if (e.getTargetException() instanceof ThreadKill) {
                throw (ThreadKill)e.getTargetException();
            }
            if (!(e.getTargetException() instanceof Exception)) {
                throw (Error)e.getTargetException();
            }
            if (e.getTargetException() instanceof MainExitException) {
                throw (RuntimeException)e.getTargetException();
            }
            recv.getRuntime().getJavaSupport().handleNativeException(e.getTargetException(), this.method);
            return recv.getRuntime().getNil();
        }
        catch (IllegalAccessException e2) {
            final StringBuilder message = new StringBuilder();
            message.append(e2.getMessage());
            message.append(':');
            message.append(" methodName=").append(this.methodName);
            message.append(" recv=").append(recv.toString());
            message.append(" type=").append(this.type.getName());
            message.append(" methodArgs=[");
            for (int i = 0; i < methodArgs.length; ++i) {
                message.append(methodArgs[i]);
                message.append(' ');
            }
            message.append(']');
            assert false : message.toString();
            return null;
        }
        catch (IllegalArgumentException e3) {
            assert false : e3;
            return null;
        }
    }
    
    public Arity getArity() {
        return this.arity;
    }
}
