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
    
    public SieveEncodingInfo(final String mimeName, final String javaName, final int lastPrintable, final String dangers) {
        super(mimeName, javaName, lastPrintable);
        this.checkerStream = null;
        this.checkerWriter = null;
        this.dangerChars = null;
        this.dangerChars = dangers;
    }
    
    public SieveEncodingInfo(final String mimeName, final int lastPrintable) {
        this(mimeName, mimeName, lastPrintable, null);
    }
    
    public boolean isPrintable(final int ch) {
        if (this.dangerChars != null && ch <= 65535 && this.dangerChars.indexOf(ch) >= 0) {
            return false;
        }
        if (ch <= super.lastPrintable) {
            return true;
        }
        boolean printable = true;
        synchronized (this) {
            try {
                if (this.checkerWriter == null) {
                    this.checkerStream = new BAOutputStream(10);
                    this.checkerWriter = new OutputStreamWriter(this.checkerStream, super.javaName);
                }
                if (ch > 65535) {
                    this.checkerWriter.write((ch - 65536 >> 10) + 55296);
                    this.checkerWriter.write((ch - 65536 & 0x3FF) + 56320);
                    final byte[] result = this.checkerStream.getBuffer();
                    if (this.checkerStream.size() == 2 && result[0] == 63 && result[1] == 63) {
                        printable = false;
                    }
                }
                else {
                    this.checkerWriter.write(ch);
                    this.checkerWriter.flush();
                    final byte[] result = this.checkerStream.getBuffer();
                    if (this.checkerStream.size() == 1 && result[0] == 63) {
                        printable = false;
                    }
                }
                this.checkerStream.reset();
            }
            catch (IOException ioe) {
                printable = false;
            }
        }
        return printable;
    }
    
    static class BAOutputStream extends ByteArrayOutputStream
    {
        BAOutputStream() {
        }
        
        BAOutputStream(final int size) {
            super(size);
        }
        
        byte[] getBuffer() {
            return super.buf;
        }
    }
}
