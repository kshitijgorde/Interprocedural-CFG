// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.applet.AudioClip;

public class ap
{
    public static final int vy = 0;
    public static final int wy = 1;
    public static final int xy = 2;
    public static final int yy = 3;
    public static final int zy = 4;
    public static final int dz = 5;
    public static final int qg = 6;
    public static final int ez = 7;
    private AudioClip[] kk;
    private AudioClip fz;
    
    public ap(final aq aq) {
        this.kk = new AudioClip[7];
        if (aq.do != null) {
            this.kk[0] = aq.lc.getAudioClip(aq.do);
        }
        if (aq.eo != null) {
            this.kk[1] = aq.lc.getAudioClip(aq.eo);
        }
        if (aq.fo != null) {
            this.kk[2] = aq.lc.getAudioClip(aq.fo);
        }
        if (aq.go != null) {
            this.kk[3] = aq.lc.getAudioClip(aq.go);
        }
        if (aq.ho != null) {
            this.kk[4] = aq.lc.getAudioClip(aq.ho);
        }
        if (aq.io != null) {
            this.kk[5] = aq.lc.getAudioClip(aq.io);
        }
        if (aq.jo != null) {
            this.kk[6] = aq.lc.getAudioClip(aq.jo);
        }
        if (aq.ko != null) {
            this.fz = aq.lc.getAudioClip(aq.ko);
        }
    }
    
    public void ok(final int n) {
        if (this.kk[n] != null) {
            this.kk[n].play();
        }
    }
    
    public void pk() {
        if (this.fz != null) {
            this.fz.play();
        }
    }
}
