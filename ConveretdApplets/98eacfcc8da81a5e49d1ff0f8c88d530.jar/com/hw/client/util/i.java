// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.awt.Component;

final class i implements Runnable
{
    private final Component a;
    
    i(final Component a) {
        this.a = a;
    }
    
    public final void run() {
        this.a.repaint();
    }
}
