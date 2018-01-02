// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer;

import java.util.Hashtable;
import java.io.Serializable;

public class Reply implements Serializable
{
    public Hashtable infos;
    public Object[] objects;
    public int ret;
    public String text;
    
    public Reply(final int anzObj) {
        this.ret = 0;
        this.text = "";
        this.objects = new Object[anzObj];
    }
}
