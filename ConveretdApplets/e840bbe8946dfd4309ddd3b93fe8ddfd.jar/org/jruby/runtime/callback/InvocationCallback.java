// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callback;

import org.jruby.Ruby;
import org.jruby.exceptions.MainExitException;
import org.jruby.exceptions.ThreadKill;
import org.jruby.exceptions.JumpException;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import java.lang.reflect.Member;
import org.jruby.runtime.Arity;

public abstract class InvocationCallback implements Callback
{
    public static final Class[] EMPTY_ARGS;
    public static final Class[] OPTIONAL_ARGS;
    protected int arityValue;
    protected Arity arity;
    private Class[] argumentTypes;
    private String javaName;
    private boolean isSingleton;
    private Member target;
    
    public InvocationCallback() {
        this.argumentTypes = InvocationCallback.EMPTY_ARGS;
    }
    
    public IRubyObject execute(final IRubyObject recv, final IRubyObject[] oargs, final Block block) {
        if (this.arityValue >= 0) {
            if (oargs.length != this.arityValue) {
                throw recv.getRuntime().newArgumentError("wrong number of arguments (" + oargs.length + " for " + this.arityValue + ")");
            }
        }
        else if (oargs.length < -(1 + this.arityValue)) {
            throw recv.getRuntime().newArgumentError("wrong number of arguments (" + oargs.length + " for " + -(1 + this.arityValue) + ")");
        }
        try {
            return this.call(recv, oargs, block);
        }
        catch (RaiseException e) {
            throw e;
        }
        catch (JumpException e2) {
            throw e2;
        }
        catch (ThreadKill e3) {
            throw e3;
        }
        catch (MainExitException e4) {
            throw e4;
        }
        catch (Exception e5) {
            final Ruby runtime = recv.getRuntime();
            runtime.getJavaSupport().handleNativeException(e5, this.getTarget());
            return runtime.getNil();
        }
    }
    
    public abstract IRubyObject call(final Object p0, final Object[] p1, final Block p2);
    
    public void setArity(final Arity arity) {
        this.arity = arity;
        this.arityValue = arity.getValue();
    }
    
    public Arity getArity() {
        return this.arity;
    }
    
    public void setArgumentTypes(final Class[] argumentTypes) {
        this.argumentTypes = argumentTypes;
    }
    
    public Class[] getArgumentTypes() {
        return this.argumentTypes;
    }
    
    public void setJavaName(final String javaName) {
        this.javaName = javaName;
    }
    
    public String getJavaName() {
        return this.javaName;
    }
    
    public void setSingleton(final boolean isSingleton) {
        this.isSingleton = isSingleton;
    }
    
    public boolean isSingleton() {
        return this.isSingleton;
    }
    
    public void setTarget(final Member target) {
        this.target = target;
    }
    
    public Member getTarget() {
        return this.target;
    }
    
    static {
        EMPTY_ARGS = new Class[0];
        OPTIONAL_ARGS = new Class[] { IRubyObject[].class };
    }
}
