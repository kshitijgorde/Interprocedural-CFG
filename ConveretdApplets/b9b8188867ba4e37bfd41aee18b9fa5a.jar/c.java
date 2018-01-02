import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Thread
{
    public Chat o;
    public String n;
    public boolean m;
    
    public c(final Chat o, final String n, final boolean m) {
        this.n = "";
        this.o = o;
        this.n = n;
        this.m = m;
    }
    
    public final void run() {
        if (this.m) {
            final AudioClip audioClip = this.o.getAudioClip(this.o.getCodeBase(), "sound_" + this.n + ".au");
            if (audioClip != null) {
                audioClip.play();
            }
        }
        else {
            this.o.play(this.o.getCodeBase(), "sound_" + this.n + ".au");
        }
    }
}
