// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;
import org.jruby.RubyString;
import org.yecht.Emitter;
import org.jruby.Ruby;
import org.yecht.OutputHandler;

public class RubyOutputHandler implements OutputHandler
{
    private Ruby runtime;
    
    public RubyOutputHandler(final Ruby runtime) {
        this.runtime = runtime;
    }
    
    public void handle(final Emitter emitter, final byte[] str, final int len) {
        final YEmitter.Extra bonus = (YEmitter.Extra)emitter.bonus;
        final IRubyObject dest = bonus.port;
        if (dest instanceof RubyString) {
            ((RubyString)dest).cat(new ByteList(str, 0, len, false));
        }
        else {
            dest.callMethod(this.runtime.getCurrentContext(), "write", RubyString.newStringShared(this.runtime, str, 0, len));
        }
    }
}
