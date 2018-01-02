// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

public enum g
{
    a("SYSTEM_ERROR", 0), 
    b("PERMISSION_ERROR", 1), 
    c("CONNECTION_ERROR", 2), 
    d("AUTHENTICATION_ERROR", 3), 
    e("COMMUNICATION_ERROR", 4), 
    f("LONG_POLLING_FAILED", 5), 
    g("LONG_POLLING_ENABLED", 6), 
    h("NEW_BLOCK_DETECTED", 7), 
    i("NEW_WORK", 8), 
    j("POW_TRUE", 9), 
    k("POW_FALSE", 10), 
    l("TERMINATED", 11);
    
    static {
        final g[] array = { g.a, g.b, g.c, g.d, g.e, g.f, g.g, g.h, g.i, g.j, g.k, g.l };
    }
    
    private g(final String s, final int n) {
    }
}
