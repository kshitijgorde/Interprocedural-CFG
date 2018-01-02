// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.applet.AudioClip;

public class ve
{
    private AudioClip[] a;
    private AudioClip b;
    
    public ve(final vd vd) {
        this.a = new AudioClip[7];
        if (vd.de != null) {
            this.a[0] = vd.a.getAudioClip(vd.de);
        }
        if (vd.df != null) {
            this.a[1] = vd.a.getAudioClip(vd.df);
        }
        if (vd.dg != null) {
            this.a[2] = vd.a.getAudioClip(vd.dg);
        }
        if (vd.dh != null) {
            this.a[3] = vd.a.getAudioClip(vd.dh);
        }
        if (vd.di != null) {
            this.a[4] = vd.a.getAudioClip(vd.di);
        }
        if (vd.dj != null) {
            this.a[5] = vd.a.getAudioClip(vd.dj);
        }
        if (vd.dk != null) {
            this.a[6] = vd.a.getAudioClip(vd.dk);
        }
        if (vd.dl != null) {
            this.b = vd.a.getAudioClip(vd.dl);
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
