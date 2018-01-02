// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class bg extends Thread
{
    aw for;
    String do;
    int int;
    int if;
    webphone a;
    
    public bg(final webphone a, final int int1, final String do1, final int if1) {
        this.for = null;
        this.do = "";
        this.int = 1;
        this.if = 0;
        this.a = null;
        this.do = do1;
        this.int = int1;
        this.if = if1;
        this.a = a;
        this.for = this.a.common;
    }
    
    public void run() {
        try {
            if (this.int == -1) {
                this.a.cachedsoundfile = "";
                this.a.playsoundfile = this.a.getAudioClip(this.a.getDocumentBase(), this.do);
                if (this.a.playsoundfile == null) {
                    this.for.a(1, "ERROR,threadedplaysound cannot load " + this.do);
                    return;
                }
                this.a.cachedsoundfile = this.do;
            }
            else if (this.int > 0) {
                if (this.a.playsoundfile == null || !this.a.cachedsoundfile.equals(this.do)) {
                    this.a.cachedsoundfile = "";
                    this.a.playsoundfile = this.a.getAudioClip(this.a.getDocumentBase(), this.do);
                    if (this.a.playsoundfile == null) {
                        this.for.a(1, "ERROR,threadedplaysound cannot load " + this.do);
                        return;
                    }
                    this.a.cachedsoundfile = this.do;
                }
                if (this.if > 0) {
                    this.a.playsoundfile.loop();
                }
                else {
                    this.a.playsoundfile.play();
                }
                this.for.a(1, "EVENT,threadedplaysound play " + this.do);
            }
            else {
                if (this.a.playsoundfile == null) {
                    this.for.a(1, "WARNING,threadedplaysound no playback in progress");
                    return;
                }
                this.a.playsoundfile.stop();
                if (this.a.cachedsoundfile.length() < 1) {
                    this.a.playsoundfile = null;
                }
                this.for.a(1, "EVENT,threadedplaysound stop file playback");
            }
        }
        catch (Exception ex) {
            this.for.a(1, "threadedplaysound API_PlaySound", ex);
        }
    }
}
