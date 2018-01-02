// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.LayoutManager;
import java.awt.BorderLayout;

class G implements Runnable
{
    final /* synthetic */ au a;
    
    G(final au a) {
        this.a = a;
    }
    
    public void run() {
        this.a.setLayout(new BorderLayout());
        this.a.validate();
    }
}
