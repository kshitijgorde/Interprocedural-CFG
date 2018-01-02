// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import com.eventim.common.transfer.saalplan.SaalplanRabattstufeDetails;

public final class p
{
    private SaalplanRabattstufeDetails a;
    
    public p(final SaalplanRabattstufeDetails a) {
        this.a = a;
    }
    
    public final SaalplanRabattstufeDetails a() {
        return this.a;
    }
    
    public final String toString() {
        return this.a.getName();
    }
}
