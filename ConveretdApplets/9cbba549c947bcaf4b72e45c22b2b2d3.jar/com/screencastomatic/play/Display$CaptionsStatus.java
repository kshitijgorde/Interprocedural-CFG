// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

enum Display$CaptionsStatus
{
    a("loading", 0), 
    b("loaded", 1), 
    c("ready", 2), 
    d("error", 3);
    
    private Display$CaptionsStatus(final String s, final int n) {
    }
    
    static {
        e = new Display$CaptionsStatus[] { Display$CaptionsStatus.a, Display$CaptionsStatus.b, Display$CaptionsStatus.c, Display$CaptionsStatus.d };
    }
}
