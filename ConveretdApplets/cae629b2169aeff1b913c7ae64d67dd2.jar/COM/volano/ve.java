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
        if (vd.c5 != null) {
            this.a[0] = vd.a.getAudioClip(vd.c5);
        }
        if (vd.c6 != null) {
            this.a[1] = vd.a.getAudioClip(vd.c6);
        }
        if (vd.c7 != null) {
            this.a[2] = vd.a.getAudioClip(vd.c7);
        }
        if (vd.c8 != null) {
            this.a[3] = vd.a.getAudioClip(vd.c8);
        }
        if (vd.c9 != null) {
            this.a[4] = vd.a.getAudioClip(vd.c9);
        }
        if (vd.da != null) {
            this.a[5] = vd.a.getAudioClip(vd.da);
        }
        if (vd.db != null) {
            this.a[6] = vd.a.getAudioClip(vd.db);
        }
        if (vd.dc != null) {
            this.b = vd.a.getAudioClip(vd.dc);
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
