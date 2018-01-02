// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;

public abstract class EventHook
{
    public void event(final ThreadContext context, final RubyEvent event, final String file, final int line, final String name, final IRubyObject type) {
        this.eventHandler(context, event.getName(), file, line + event.getLineNumberOffset(), name, type);
    }
    
    public abstract void eventHandler(final ThreadContext p0, final String p1, final String p2, final int p3, final String p4, final IRubyObject p5);
    
    public abstract boolean isInterestedInEvent(final RubyEvent p0);
}
