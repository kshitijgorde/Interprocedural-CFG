// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioDataStream;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.AudioData;

public class bd extends ai
{
    ae goto;
    AudioData aw;
    ContinuousAudioDataStream ax;
    AudioDataStream av;
    
    public bd() {
        this.goto = null;
    }
    
    public void a(final String s, final ae ae, final ac void1, final boolean else1, final boolean new1) {
        super.void = void1;
        this.goto = super.void.w.a(s, ae, false, false, false);
        super.else = else1;
        super.new = new1;
    }
    
    public boolean a(final long n) {
        if (this.goto.j != 0) {
            this.goto = this.goto.new[0];
        }
        if (this.goto.b && !super.am) {
            this.aw = new AudioData(this.goto.k);
            this.ax = new ContinuousAudioDataStream(this.aw);
            (this.av = new AudioDataStream(this.aw)).reset();
            this.ax.reset();
            super.am = true;
        }
        else if (super.else && super.b) {
            if (!super.ao) {
                return super.b = false;
            }
            if (this.ax != null || this.av != null) {
                super.b = false;
                if (super.al) {
                    AudioPlayer.player.start(this.ax);
                }
                else {
                    AudioPlayer.player.start(this.av);
                }
                super.ak = true;
            }
        }
        return false;
    }
    
    public void if() {
        try {
            AudioPlayer.player.stop(this.ax);
            AudioPlayer.player.stop(this.av);
            if (this.ax != null) {
                this.ax.close();
                this.ax = null;
            }
            if (this.av != null) {
                this.av.close();
                this.av = null;
            }
            this.aw = null;
            super.ak = false;
        }
        catch (Exception ex) {}
    }
    
    public void a(final float n, final float n2) {
    }
    
    public void void() {
        super.ao = true;
        if (super.al) {
            AudioPlayer.player.start(this.ax);
        }
        else {
            AudioPlayer.player.start(this.av);
        }
        super.ak = true;
    }
    
    public void c() {
        super.ao = false;
        if (super.al) {
            AudioPlayer.player.stop(this.ax);
        }
        else {
            AudioPlayer.player.stop(this.av);
        }
        super.ak = false;
    }
    
    public void b() {
        super.ao = false;
        if (super.al) {
            AudioPlayer.player.stop(this.ax);
            this.ax.reset();
        }
        else {
            AudioPlayer.player.stop(this.av);
            this.av.reset();
        }
        super.ak = false;
    }
    
    public void for(final boolean al) {
        if (super.al == al) {
            return;
        }
        if (!super.ao) {
            return;
        }
        super.al = al;
        if (super.al) {
            AudioPlayer.player.start(this.ax);
            AudioPlayer.player.stop(this.av);
        }
        else {
            AudioPlayer.player.stop(this.ax);
            AudioPlayer.player.start(this.av);
        }
    }
    
    public void int(final boolean b) {
        if (!b) {
            if (super.ak) {
                this.c();
                super.ak = true;
            }
        }
        else if (super.ak) {
            this.void();
        }
    }
}
