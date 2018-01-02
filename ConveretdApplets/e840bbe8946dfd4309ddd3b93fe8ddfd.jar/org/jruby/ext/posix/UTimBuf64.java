// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public final class UTimBuf64 extends Struct
{
    public final Signed64 actime;
    public final Signed64 modtime;
    
    public UTimBuf64(final long actime, final long modtime) {
        this.actime = new Signed64(this);
        this.modtime = new Signed64(this);
        this.actime.set(actime);
        this.modtime.set(modtime);
    }
}
