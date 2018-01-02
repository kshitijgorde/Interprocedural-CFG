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

public abstract class FastInvocationCallback extends InvocationCallback
{
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
            return this.call(recv, oargs);
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
    
    public abstract IRubyObject call(final Object p0, final Object[] p1);
}
