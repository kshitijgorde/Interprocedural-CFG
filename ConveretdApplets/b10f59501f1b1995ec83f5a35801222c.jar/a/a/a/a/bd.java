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
    AudioData aF;
    ContinuousAudioDataStream aG;
    AudioDataStream aE;
    
    public void if() {
        super.if();
        try {
            AudioPlayer.player.stop(this.aG);
            AudioPlayer.player.stop(this.aE);
            if (this.aG != null) {
                this.aG.close();
                this.aG = null;
            }
            if (this.aE != null) {
                this.aE.close();
                this.aE = null;
            }
        }
        catch (Exception ex) {}
        this.aF = null;
        super.at = false;
        if (super.long != null) {
            super.long.a();
        }
        super.long = null;
    }
    
    public void a(final String s, final ae ae, final ac b, final boolean goto1, final boolean try1) {
        super.b = b;
        super.long = super.b.A.a(s, ae, false, false, false);
        super.goto = goto1;
        super.try = try1;
    }
    
    public boolean a(final long n) {
        if (super.long.k != 0) {
            super.long = super.long.try[0];
        }
        if (super.long.b && !super.av) {
            this.aF = new AudioData(super.long.l);
            this.aG = new ContinuousAudioDataStream(this.aF);
            (this.aE = new AudioDataStream(this.aF)).reset();
            this.aG.reset();
            super.av = true;
        }
        else if (super.goto && super.c) {
            if (!super.ax) {
                return super.c = false;
            }
            if (this.aG != null || this.aE != null) {
                super.c = false;
                if (super.au) {
                    AudioPlayer.player.start(this.aG);
                }
                else {
                    AudioPlayer.player.start(this.aE);
                }
                super.at = true;
            }
        }
        return false;
    }
    
    public void a(final float n, final float n2) {
    }
    
    public void void() {
        super.ax = true;
        if (super.au) {
            AudioPlayer.player.start(this.aG);
        }
        else {
            AudioPlayer.player.start(this.aE);
        }
        super.at = true;
    }
    
    public void c() {
        super.ax = false;
        if (super.au) {
            AudioPlayer.player.stop(this.aG);
        }
        else {
            AudioPlayer.player.stop(this.aE);
        }
        super.at = false;
    }
    
    public void b() {
        super.ax = false;
        if (super.au) {
            AudioPlayer.player.stop(this.aG);
            this.aG.reset();
        }
        else {
            AudioPlayer.player.stop(this.aE);
            this.aE.reset();
        }
        super.at = false;
    }
    
    public void for(final boolean au) {
        if (super.au == au) {
            return;
        }
        if (!super.ax) {
            return;
        }
        super.au = au;
        if (super.au) {
            AudioPlayer.player.start(this.aG);
            AudioPlayer.player.stop(this.aE);
        }
        else {
            AudioPlayer.player.stop(this.aG);
            AudioPlayer.player.start(this.aE);
        }
    }
    
    public void int(final boolean b) {
        if (!b) {
            if (super.at) {
                this.c();
                super.at = true;
            }
        }
        else if (super.at) {
            this.void();
        }
    }
}
