// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.mail.pop3;

import java.io.InputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.OutputStream;

class AppendStream extends OutputStream
{
    private final WritableSharedFile tf;
    private RandomAccessFile raf;
    private final long start;
    private long end;
    
    public AppendStream(final WritableSharedFile tf) throws IOException {
        this.tf = tf;
        this.raf = tf.getWritableFile();
        this.start = this.raf.length();
        this.raf.seek(this.start);
    }
    
    public void write(final int b) throws IOException {
        this.raf.write(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.raf.write(b);
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.raf.write(b, off, len);
    }
    
    public synchronized void close() throws IOException {
        this.end = this.tf.updateLength();
        this.raf = null;
    }
    
    public synchronized InputStream getInputStream() throws IOException {
        return this.tf.newStream(this.start, this.end);
    }
}
