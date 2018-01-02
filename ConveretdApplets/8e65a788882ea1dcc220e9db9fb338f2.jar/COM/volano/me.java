// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.applet.AudioClip;

public class me
{
    private AudioClip[] a;
    private AudioClip b;
    
    public me(final md md) {
        this.a = new AudioClip[7];
        if (md.c1 != null) {
            this.a[0] = md.a.getAudioClip(md.c1);
        }
        if (md.c2 != null) {
            this.a[1] = md.a.getAudioClip(md.c2);
        }
        if (md.c3 != null) {
            this.a[2] = md.a.getAudioClip(md.c3);
        }
        if (md.c4 != null) {
            this.a[3] = md.a.getAudioClip(md.c4);
        }
        if (md.c5 != null) {
            this.a[4] = md.a.getAudioClip(md.c5);
        }
        if (md.c6 != null) {
            this.a[5] = md.a.getAudioClip(md.c6);
        }
        if (md.c7 != null) {
            this.a[6] = md.a.getAudioClip(md.c7);
        }
        if (md.c8 != null) {
            this.b = md.a.getAudioClip(md.c8);
        }
    }
    
    public void a(final int n) {
        if (this.a[n] != null) {
            this.a[n].play();
        }
    }
    
    public void a() {
        if (this.b != null) {
            this.b.play();
        }
    }
}
