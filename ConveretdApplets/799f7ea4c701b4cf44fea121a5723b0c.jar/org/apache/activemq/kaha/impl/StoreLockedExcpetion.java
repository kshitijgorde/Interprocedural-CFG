// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl;

import java.io.IOException;

public class StoreLockedExcpetion extends IOException
{
    private static final long serialVersionUID = 3857646689671366926L;
    
    public StoreLockedExcpetion() {
    }
    
    public StoreLockedExcpetion(final String s) {
        super(s);
    }
}
