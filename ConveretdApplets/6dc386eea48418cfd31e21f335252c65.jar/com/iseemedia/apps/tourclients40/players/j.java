// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.players;

import java.awt.Graphics;

final class j implements Runnable
{
    private final f a;
    
    j(final f a) {
        this.a = a;
    }
    
    public final void run() {
        try {
            final Graphics graphics = this.a.getGraphics();
            while (this.a.m) {
                this.a.b(graphics);
                this.a.A += 24;
                Thread.sleep(150L);
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
