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
    AudioData aG;
    ContinuousAudioDataStream aH;
    AudioDataStream aF;
    
    public void if() {
        super.if();
        try {
            AudioPlayer.player.stop(this.aH);
            AudioPlayer.player.stop(this.aF);
            if (this.aH != null) {
                this.aH.close();
                this.aH = null;
            }
            if (this.aF != null) {
                this.aF.close();
                this.aF = null;
            }
        }
        catch (Exception ex) {}
        this.aG = null;
        super.au = false;
        if (super.long != null) {
            super.long.a();
        }
        super.long = null;
    }
    
    public void a(final String s, final ae ae, final ac b, final boolean goto1, final boolean try1) {
        super.b = b;
        super.long = super.b.B.a(s, ae, false, false, false);
        super.goto = goto1;
        super.try = try1;
    }
    
    public boolean a(final long n) {
        if (super.long.k != 0) {
            super.long = super.long.try[0];
        }
        if (super.long.b && !super.aw) {
            this.aG = new AudioData(super.long.l);
            this.aH = new ContinuousAudioDataStream(this.aG);
            (this.aF = new AudioDataStream(this.aG)).reset();
            this.aH.reset();
            super.aw = true;
        }
        else if (super.goto && super.c) {
            if (!super.ay) {
                return super.c = false;
            }
            if (this.aH != null || this.aF != null) {
                super.c = false;
                if (super.av) {
                    AudioPlayer.player.start(this.aH);
                }
                else {
                    AudioPlayer.player.start(this.aF);
                }
                super.au = true;
            }
        }
        return false;
    }
    
    public void a(final float n, final float n2) {
    }
    
    public void else() {
        super.ay = true;
        if (super.av) {
            AudioPlayer.player.start(this.aH);
        }
        else {
            AudioPlayer.player.start(this.aF);
        }
        super.au = true;
    }
    
    public void long() {
        super.ay = false;
        if (super.av) {
            AudioPlayer.player.stop(this.aH);
        }
        else {
            AudioPlayer.player.stop(this.aF);
        }
        super.au = false;
    }
    
    public void goto() {
        super.ay = false;
        if (super.av) {
            AudioPlayer.player.stop(this.aH);
            this.aH.reset();
        }
        else {
            AudioPlayer.player.stop(this.aF);
            this.aF.reset();
        }
        super.au = false;
    }
    
    public void for(final boolean av) {
        if (super.av == av) {
            return;
        }
        if (!super.ay) {
            return;
        }
        super.av = av;
        if (super.av) {
            AudioPlayer.player.start(this.aH);
            AudioPlayer.player.stop(this.aF);
        }
        else {
            AudioPlayer.player.stop(this.aH);
            AudioPlayer.player.start(this.aF);
        }
    }
    
    public void int(final boolean b) {
        if (!b) {
            if (super.au) {
                this.long();
                super.au = true;
            }
        }
        else if (super.au) {
            this.else();
        }
    }
}
