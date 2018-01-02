// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.Ruby;

public abstract class CallbackManager
{
    public abstract Pointer getCallback(final Ruby p0, final CallbackInfo p1, final Object p2);
}
