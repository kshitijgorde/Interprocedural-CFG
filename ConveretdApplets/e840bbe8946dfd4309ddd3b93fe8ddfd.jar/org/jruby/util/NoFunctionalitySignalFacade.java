// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.runtime.BlockCallback;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

public class NoFunctionalitySignalFacade implements SignalFacade
{
    public IRubyObject trap(final IRubyObject recv, final IRubyObject block, final IRubyObject sig) {
        return recv.getRuntime().getNil();
    }
    
    public IRubyObject trap(final Ruby runtime, final BlockCallback block, final String sig) {
        return runtime.getNil();
    }
}
