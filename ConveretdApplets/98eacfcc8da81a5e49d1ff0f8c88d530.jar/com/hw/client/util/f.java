// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.util.EventObject;

public class f extends EventObject implements Runnable
{
    public f(final e e) {
        super(e);
    }
    
    public void run() {
        ((e)this.getSource()).a(this, false);
    }
}
