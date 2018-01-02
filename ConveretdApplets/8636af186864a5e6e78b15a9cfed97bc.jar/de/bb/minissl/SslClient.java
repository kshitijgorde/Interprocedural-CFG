// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.util.Vector;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public final class SslClient
{
    c x;
    private byte[] y;
    
    public SslClient() {
        this.y = new byte[16];
    }
    
    public SslClient(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.y = new byte[16];
        this.connect(inputStream, outputStream);
    }
    
    public void connect(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        try {
            final byte[] n = this.n();
            outputStream.write(n);
            final byte b = (byte)inputStream.read();
            if (this.x == null) {
                if (b < 0) {
                    this.x = new g();
                }
                else {
                    this.x = new e();
                }
            }
            this.x.H(inputStream, outputStream, this.y, b, n);
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new IOException("SSL connect:" + ex2.toString());
        }
    }
    
    public final Vector getCertificates() throws IOException {
        if (this.x == null) {
            throw new IOException("SSL: not connected");
        }
        return this.x.G;
    }
    
    public int getCipherType() throws IOException {
        if (this.x == null) {
            throw new IOException("SSL: not connected");
        }
        return this.x.J;
    }
    
    public InputStream getInputStream() throws IOException {
        if (this.x == null) {
            throw new IOException("SSL: not connected");
        }
        return this.x.I();
    }
    
    public OutputStream getOutputStream() throws IOException {
        if (this.x == null) {
            throw new IOException("SSL: not connected");
        }
        return this.x.D();
    }
    
    private final byte[] n() {
        if (this.x != null) {
            this.x.T = null;
        }
        int length = 0;
        if (this.x != null && this.x.T != null) {
            length = this.x.T.length;
        }
        final byte[] array = new byte[42 + length];
        int n = 0;
        array[n++] = -128;
        array[n++] = (byte)(40 + length);
        array[n++] = 1;
        array[n++] = 3;
        array[n++] = 0;
        array[n++] = 0;
        array[n++] = 15;
        array[n++] = 0;
        array[n++] = (byte)length;
        array[n++] = 0;
        array[n++] = 16;
        if (length != 0) {
            System.arraycopy(this.x.T, 0, array, n, length);
            n += length;
        }
        array[n++] = 0;
        array[n++] = 0;
        array[n++] = 5;
        array[n++] = 0;
        array[n++] = 0;
        array[n++] = 4;
        array[n++] = 1;
        array[n++] = 0;
        array[n++] = -128;
        array[n++] = 0;
        array[n++] = 0;
        array[n++] = 3;
        array[n++] = 2;
        array[n++] = 0;
        array[n++] = -128;
        a.A.nextBytes(this.y);
        System.arraycopy(this.y, 0, array, n, 16);
        return array;
    }
}
