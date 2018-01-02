// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

public enum CapturedMouse$ButtonAction
{
    a("noAction", 0), 
    b("lDown", 1), 
    c("mDown", 2), 
    d("rDown", 3);
    
    private CapturedMouse$ButtonAction(final String s, final int n) {
    }
    
    static {
        e = new CapturedMouse$ButtonAction[] { CapturedMouse$ButtonAction.a, CapturedMouse$ButtonAction.b, CapturedMouse$ButtonAction.c, CapturedMouse$ButtonAction.d };
    }
}
