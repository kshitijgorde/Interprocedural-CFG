// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.Iterator;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.EventQueue;

class d implements Runnable
{
    final /* synthetic */ k a;
    
    d(final k a) {
        this.a = a;
    }
    
    public void run() {
        try {
            while (true) {
                if (this.a.f && System.currentTimeMillis() - this.a.g > 3000L) {
                    this.a.f = false;
                    EventQueue.invokeLater(new i(this));
                }
                Thread.sleep(1000L);
            }
        }
        catch (InterruptedException ex) {}
    }
}
