// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import com.eventim.common.transfer.saalplan.SaalplanPromotionDetails;

public final class q
{
    private SaalplanPromotionDetails a;
    
    public q(final SaalplanPromotionDetails a) {
        this.a = a;
    }
    
    public final SaalplanPromotionDetails a() {
        return this.a;
    }
    
    public final String toString() {
        return this.a.getTdlName();
    }
}
