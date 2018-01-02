// 
// Decompiled by Procyon v0.5.30
// 

public class IAXMiniFrame extends IAXFrame
{
    String host;
    
    public IAXMiniFrame(final byte[] frame, final String h) {
        this.host = h;
        this.full = false;
        this.scallno = (short)((frame[0] << 8 & 0xFF00) | frame[1]);
        this.timestamp = ((frame[2] << 8 & 0xFF00) | frame[3]);
        System.arraycopy(frame, 4, this.streamData = new byte[frame.length - 4], 0, this.streamData.length);
    }
    
    public IAXMiniFrame(final short callno, final short ts, final byte[] media) {
        this.full = false;
        this.scallno = callno;
        this.timestamp = ts;
        System.arraycopy(media, 0, this.streamData = new byte[media.length], 0, this.streamData.length);
    }
    
    public IAXMiniFrame(final short callno, final int ts, final byte[] media) {
        this(callno, (short)ts, media);
    }
    
    public IAXMiniFrame(final short callno, final byte[] media) {
        this.full = false;
        this.scallno = callno;
        this.timestamp = -1;
        System.arraycopy(media, 0, this.streamData = new byte[media.length], 0, this.streamData.length);
    }
    
    public void print(final int foo) {
        System.out.println("Miniframe received:  Callno " + this.scallno + "\t\t Timestamp " + this.timestamp);
    }
    
    public byte[] asByteArray() {
        final byte[] ret = new byte[4 + this.streamData.length];
        ret[0] = (byte)(this.scallno >>> 8);
        ret[1] = (byte)this.scallno;
        ret[2] = (byte)(this.timestamp >>> 8);
        ret[3] = (byte)this.timestamp;
        System.arraycopy(this.streamData, 0, ret, 4, this.streamData.length);
        return ret;
    }
}
