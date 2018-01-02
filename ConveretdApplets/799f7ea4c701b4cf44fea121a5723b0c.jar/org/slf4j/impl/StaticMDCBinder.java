// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.impl;

import org.slf4j.helpers.NOPMakerAdapter;
import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder
{
    public static final StaticMDCBinder SINGLETON;
    static /* synthetic */ Class class$org$slf4j$helpers$NOPMakerAdapter;
    
    public MDCAdapter getMDCA() {
        return new NOPMakerAdapter();
    }
    
    public String getMDCAdapterClassStr() {
        return ((StaticMDCBinder.class$org$slf4j$helpers$NOPMakerAdapter == null) ? (StaticMDCBinder.class$org$slf4j$helpers$NOPMakerAdapter = class$("org.slf4j.helpers.NOPMakerAdapter")) : StaticMDCBinder.class$org$slf4j$helpers$NOPMakerAdapter).getName();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        SINGLETON = new StaticMDCBinder();
    }
}
