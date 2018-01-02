// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public final class DefaultNativeTimeval extends Timeval
{
    public final SignedLong tv_sec;
    public final SignedLong tv_usec;
    
    public DefaultNativeTimeval() {
        this.tv_sec = new SignedLong(this);
        this.tv_usec = new SignedLong(this);
    }
    
    public void setTime(final long[] timeval) {
        assert timeval.length == 2;
        this.tv_sec.set(timeval[0]);
        this.tv_usec.set(timeval[1]);
    }
}
