// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class Bytestring
{
    int hash;
    public byte[] buffer;
    int length;
    int remaining;
    boolean printed;
    public static final int HASH = -889271554;
    public static final int CHUNKSIZE = 64;
    
    public static int strlen(final byte[] buf) {
        return strlen(buf, 0);
    }
    
    public static int strlen(final byte[] buf, final int start) {
        final int stop = buf.length;
        for (int ix = start; ix < stop; ++ix) {
            if (buf[ix] == 0) {
                return ix - start;
            }
        }
        return stop - start;
    }
    
    public Bytestring() {
        this.hash = -889271554;
        this.length = 64;
        this.remaining = this.length;
        (this.buffer = new byte[this.length + 1])[0] = 0;
        this.printed = false;
    }
    
    public void append(final byte code, final byte[] inbuf, int start, int finish) {
        int length = 2;
        if (inbuf != null) {
            if (finish == -1) {
                finish = start + strlen(inbuf, start);
            }
            length += finish - start;
        }
        if (length > this.remaining) {
            final int grow = length - this.remaining + 64;
            this.remaining += grow;
            this.length += grow;
            this.buffer = YAML.realloc(this.buffer, this.length + 1);
        }
        int curr = this.length - this.remaining;
        this.buffer[curr] = code;
        ++curr;
        if (inbuf != null) {
            while (start < finish) {
                this.buffer[curr++] = inbuf[start++];
            }
        }
        this.buffer[curr] = 10;
        ++curr;
        this.buffer[curr] = 0;
        this.remaining -= length;
    }
    
    public void extend(final Bytestring ext) {
        if (ext.printed) {
            int curr;
            for (curr = 0; ext.buffer[curr] != 10; ++curr) {}
            this.append((byte)82, ext.buffer, 1, curr);
        }
        else {
            ext.printed = true;
            final int length = ext.length - ext.remaining;
            if (length > this.remaining) {
                final int grow = length - this.remaining + 64;
                this.remaining += grow;
                this.length += grow;
                this.buffer = YAML.realloc(this.buffer, this.length + 1);
            }
            int curr2 = this.length - this.remaining;
            for (int from = 0, stop = length; from < stop; this.buffer[curr2++] = ext.buffer[from++]) {}
            this.buffer[curr2] = 0;
            this.remaining -= length;
        }
    }
}
