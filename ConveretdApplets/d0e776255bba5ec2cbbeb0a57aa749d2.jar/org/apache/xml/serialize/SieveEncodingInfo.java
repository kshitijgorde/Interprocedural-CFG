// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class SieveEncodingInfo extends EncodingInfo
{
    BAOutputStream checkerStream;
    Writer checkerWriter;
    String dangerChars;
    
    public SieveEncodingInfo(final String s, final String s2, final int n, final String dangerChars) {
        super(s, s2, n);
        this.checkerStream = null;
        this.checkerWriter = null;
        this.dangerChars = null;
        this.dangerChars = dangerChars;
    }
    
    public SieveEncodingInfo(final String s, final int n) {
        this(s, s, n, null);
    }
    
    public boolean isPrintable(final int n) {
        if (this.dangerChars != null && n <= 65535 && this.dangerChars.indexOf(n) >= 0) {
            return false;
        }
        if (n <= super.lastPrintable) {
            return true;
        }
        boolean b = true;
        synchronized (this) {
            try {
                if (this.checkerWriter == null) {
                    this.checkerStream = new BAOutputStream(10);
                    this.checkerWriter = new OutputStreamWriter(this.checkerStream, super.javaName);
                }
                if (n > 65535) {
                    this.checkerWriter.write((n - 65536 >> 10) + 55296);
                    this.checkerWriter.write((n - 65536 & 0x3FF) + 56320);
                    final byte[] buffer = this.checkerStream.getBuffer();
                    if (this.checkerStream.size() == 2 && buffer[0] == 63 && buffer[1] == 63) {
                        b = false;
                    }
                }
                else {
                    this.checkerWriter.write(n);
                    this.checkerWriter.flush();
                    final byte[] buffer2 = this.checkerStream.getBuffer();
                    if (this.checkerStream.size() == 1 && buffer2[0] == 63) {
                        b = false;
                    }
                }
                this.checkerStream.reset();
            }
            catch (IOException ex) {
                b = false;
            }
        }
        return b;
    }
    
    static class BAOutputStream extends ByteArrayOutputStream
    {
        BAOutputStream() {
        }
        
        BAOutputStream(final int n) {
            super(n);
        }
        
        byte[] getBuffer() {
            return super.buf;
        }
    }
}
