// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

public class GlobalChoice
{
    public boolean timestamp;
    public boolean audio;
    public boolean smiley;
    public boolean block;
    public boolean haveVideo;
    public boolean popPublic;
    public boolean popPrivate;
    public boolean reconnect;
    public boolean approvePrivate;
    
    public GlobalChoice() {
        this.timestamp = false;
        this.audio = true;
        this.smiley = true;
        this.block = false;
        this.haveVideo = false;
        this.popPublic = false;
        this.popPrivate = false;
        this.reconnect = true;
        this.approvePrivate = true;
    }
}
