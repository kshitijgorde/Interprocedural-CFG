// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.exceptions.MainExitException;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyModule;
import org.jruby.RubyProc;
import org.jruby.runtime.CallBlock;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import sun.misc.SignalHandler;
import sun.misc.Signal;
import org.jruby.runtime.BlockCallback;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

public class SunSignalFacade implements SignalFacade
{
    public IRubyObject trap(final IRubyObject recv, final IRubyObject blk, final IRubyObject sig) {
        return this.trap(recv.getRuntime(), new JRubySignalHandler(recv.getRuntime(), blk, sig.toString()));
    }
    
    public IRubyObject trap(final Ruby runtime, final BlockCallback blk, final String sig) {
        return this.trap(runtime, new JRubySignalHandler(runtime, blk, sig));
    }
    
    private IRubyObject trap(final Ruby runtime, final JRubySignalHandler handler) {
        Signal signal;
        try {
            signal = new Signal(handler.signal);
        }
        catch (Throwable e2) {
            return runtime.getNil();
        }
        SignalHandler oldHandler;
        try {
            oldHandler = Signal.handle(signal, handler);
        }
        catch (Exception e) {
            throw runtime.newArgumentError(e.getMessage());
        }
        BlockCallback callback = null;
        if (oldHandler instanceof JRubySignalHandler) {
            final JRubySignalHandler jsHandler = (JRubySignalHandler)oldHandler;
            if (jsHandler.blockCallback == null) {
                return jsHandler.block;
            }
            callback = jsHandler.blockCallback;
        }
        if (callback == null) {
            callback = new BlockCallback() {
                public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block block) {
                    oldHandler.handle(new Signal(handler.signal));
                    return runtime.getNil();
                }
            };
        }
        final RubyModule signalModule = runtime.getModule("Signal");
        final Block block = CallBlock.newCallClosure(signalModule, signalModule, Arity.noArguments(), callback, runtime.getCurrentContext());
        return RubyProc.newProc(runtime, block, Block.Type.NORMAL);
    }
    
    private static final class JRubySignalHandler implements SignalHandler
    {
        private final Ruby runtime;
        private final IRubyObject block;
        private final String signal;
        private final BlockCallback blockCallback;
        
        public JRubySignalHandler(final Ruby runtime, final IRubyObject block, final String signal) {
            this(runtime, block, null, signal);
        }
        
        public JRubySignalHandler(final Ruby runtime, final BlockCallback callback, final String signal) {
            this(runtime, null, callback, signal);
        }
        
        private JRubySignalHandler(final Ruby runtime, final IRubyObject block, final BlockCallback callback, final String signal) {
            this.runtime = runtime;
            this.block = block;
            this.blockCallback = callback;
            this.signal = signal;
        }
        
        public void handle(final Signal signal) {
            final ThreadContext context = this.runtime.getCurrentContext();
            try {
                if (this.block != null) {
                    this.block.callMethod(context, "call");
                }
                else {
                    this.blockCallback.call(context, new IRubyObject[0], Block.NULL_BLOCK);
                }
            }
            catch (RaiseException e) {
                try {
                    this.runtime.getThread().callMethod(context, "main").callMethod(context, "raise", e.getException());
                }
                catch (Exception ex) {}
            }
            catch (MainExitException mee) {
                this.runtime.getThreadService().getMainThread().kill();
            }
            finally {
                Signal.handle(new Signal(this.signal), this);
            }
        }
    }
}
