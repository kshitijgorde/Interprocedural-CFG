// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.applets;

import java.applet.AudioClip;

final class a implements Runnable
{
    private final b a;
    
    a(final b a) {
        this.a = a;
    }
    
    public final void run() {
        int n = 0;
        while (true) {
            b.a(this.a, (String)b.a(this.a).elementAt(n));
            b.a(this.a, this.a.getAudioClip(this.a.getDocumentBase(), b.b(this.a)));
            b.c(this.a).play();
            try {
                Thread.sleep(b.d(this.a) * 1000);
            }
            catch (InterruptedException ex) {
                return;
            }
            try {
                if (b.c(this.a) != null) {
                    b.c(this.a).stop();
                    b.a(this.a, (AudioClip)null);
                }
            }
            catch (Exception ex2) {}
            n = (n + 1) % b.e(this.a);
        }
    }
}
