// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum SocketOption implements Constant
{
    SO_DEBUG(1), 
    SO_ACCEPTCONN(2), 
    SO_REUSEADDR(4), 
    SO_KEEPALIVE(8), 
    SO_DONTROUTE(16), 
    SO_BROADCAST(32), 
    SO_USELOOPBACK(64), 
    SO_LINGER(128), 
    SO_OOBINLINE(256), 
    SO_REUSEPORT(512), 
    SO_TIMESTAMP(1024), 
    SO_DONTTRUNC(8192), 
    SO_WANTMORE(16384), 
    SO_WANTOOBFLAG(32768), 
    SO_SNDBUF(4097), 
    SO_RCVBUF(4098), 
    SO_SNDLOWAT(4099), 
    SO_RCVLOWAT(4100), 
    SO_SNDTIMEO(4101), 
    SO_RCVTIMEO(4102), 
    SO_ERROR(4103), 
    SO_TYPE(4104), 
    SO_NREAD(4128), 
    SO_NKE(4129), 
    SO_NOSIGPIPE(4130), 
    SO_NOADDRERR(4131), 
    SO_NWRITE(4132), 
    SO_REUSESHAREUID(4133), 
    SO_LABEL(4112), 
    SO_PEERLABEL(4113);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 32768L;
    
    private SocketOption(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
