// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.players;

final class a implements Runnable
{
    private final h a;
    
    a(final h a) {
        this.a = a;
    }
    
    public final void run() {
        try {
            while (!this.a.h()) {
                this.a.A = (this.a.A + 12) % 360;
                Thread.sleep(200L);
                this.a.repaint();
                this.a.requestFocus();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
