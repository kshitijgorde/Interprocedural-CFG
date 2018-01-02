// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public abstract class SaalplanBaseDetails implements Serializable
{
    public int ret;
    public String retText;
    
    public SaalplanBaseDetails(final int ret, final String retText) {
        this.ret = ret;
        this.retText = retText;
    }
    
    public SaalplanBaseDetails() {
        this.ret = 1;
        this.retText = "OK";
    }
}
