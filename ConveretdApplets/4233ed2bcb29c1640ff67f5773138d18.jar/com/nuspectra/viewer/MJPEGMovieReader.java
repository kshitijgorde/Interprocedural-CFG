// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.util.Date;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;

public class MJPEGMovieReader
{
    DataInputStream ff;
    private int framePos;
    boolean hasHeader;
    String fatalErr;
    private int id;
    private int version;
    private int dataOffset;
    private int width;
    private int height;
    long date;
    int frames;
    int timestamp;
    int delta;
    int ifd;
    int seconds;
    long lastTime;
    int flags;
    
    protected MJPEGMovieReader(final InputStream i) {
        this.ff = null;
        this.framePos = 0;
        this.hasHeader = false;
        this.fatalErr = null;
        this.dataOffset = 0;
        this.lastTime = 0L;
        this.flags = 0;
        try {
            this.ff = new DataInputStream(i);
        }
        catch (Exception ex) {
            this.ff = null;
        }
    }
    
    public void finalize() {
        if (this.ff != null) {
            this.close();
        }
    }
    
    protected void close() {
        if (this.ff != null) {
            try {
                this.ff.close();
            }
            catch (Exception ex) {}
            this.ff = null;
        }
    }
    
    private void fatalErr(final String e) throws IOException {
        if (this.fatalErr == null && e != null) {
            this.fatalErr = e;
            throw new IOException(this.fatalErr);
        }
    }
    
    private int readShort() throws IOException {
        final int b1 = this.ff.readUnsignedByte();
        final int b2 = this.ff.readUnsignedByte();
        final int cc = b1 << 8 | b2;
        return cc;
    }
    
    protected void readHeader() throws IOException {
        if (!this.hasHeader && this.fatalErr == null) {
            if (this.ff == null) {
                this.fatalErr("File not found ff=null");
            }
            this.id = this.readShort();
            if (this.id != 21328) {
                this.fatalErr("invalid mjpeg");
            }
            this.version = this.readShort();
            if (this.version > 2) {
                this.fatalErr("invalid mjpeg file version:" + this.version);
            }
            this.dataOffset = this.readShort();
            this.date = this.ff.readLong();
            this.frames = this.readShort();
            this.ifd = this.readShort();
            this.seconds = this.readShort();
            this.width = this.readShort();
            this.height = this.readShort();
            this.flags = this.readShort();
            for (int skip = this.dataOffset - 26, x = 0; x < skip; ++x) {
                this.ff.readUnsignedByte();
            }
            this.hasHeader = true;
        }
    }
    
    protected int getFrameCount() {
        return this.frames;
    }
    
    protected int getInterframeDelay() {
        return this.ifd;
    }
    
    protected float getFramesPerSecond() {
        final float f = (float)(this.ifd / 10.0 / 60.0);
        return f;
    }
    
    protected int getSeconds() {
        return this.seconds;
    }
    
    protected Date getDate() {
        return new Date(this.date);
    }
    
    protected long getTime() {
        return this.date;
    }
    
    protected int getWidth() {
        return this.width;
    }
    
    protected int getHeight() {
        return this.height;
    }
    
    protected int getTimestamp() {
        return this.timestamp;
    }
    
    protected boolean getInvertVideo() {
        return (this.flags & 0x1) != 0x0;
    }
    
    protected int getFrameDuration() {
        if (this.lastTime == 0L) {
            return this.ifd;
        }
        return (int)(this.timestamp - this.lastTime);
    }
    
    protected byte[] getNextImage() throws IOException {
        if (this.ff == null) {
            return null;
        }
        if (!this.hasHeader) {
            this.readHeader();
        }
        if (this.fatalErr != null) {
            throw new IOException(this.fatalErr);
        }
        this.lastTime = this.timestamp;
        this.timestamp = this.ff.readInt();
        final int len = (this.version == 1) ? this.readShort() : this.ff.readInt();
        if (len == 0) {
            this.close();
            return null;
        }
        if (len < 0 || len > 1048576) {
            throw new IOException("bad length " + len + "at frame " + this.framePos + " " + this.toString());
        }
        ++this.framePos;
        final byte[] b = new byte[len];
        this.ff.readFully(b);
        if (b[0] != -1) {
            throw new IOException("bad data");
        }
        return b;
    }
    
    protected int getNextImage(final byte[] data) throws IOException {
        if (!this.hasHeader) {
            this.readHeader();
        }
        this.timestamp = this.ff.readInt();
        final int len = (this.version == 1) ? this.readShort() : this.ff.readInt();
        if (len == 0) {
            this.close();
            return 0;
        }
        if (data.length < len) {
            throw new IOException("Buffer overflow error reading mjpeg. (" + len + " > " + data.length + ")");
        }
        this.ff.read(data, 0, len);
        return len;
    }
    
    public String toString() {
        final String out = "mjpeg: " + this.id + " vers:" + this.version + " offset:" + this.dataOffset + " date:" + this.date + " size" + this.width + "x" + this.height + " frames:" + this.frames + " secs:" + this.seconds;
        return out;
    }
}
