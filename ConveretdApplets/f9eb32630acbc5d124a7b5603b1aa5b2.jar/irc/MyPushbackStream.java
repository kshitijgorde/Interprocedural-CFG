// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.io.IOException;
import java.io.InputStream;

class MyPushbackStream extends InputStream
{
    private InputStream _is;
    private int _back;
    private boolean _closed;
    
    public MyPushbackStream(final InputStream is) {
        this._is = is;
        this._back = -1;
        this._closed = false;
    }
    
    public void close() throws IOException {
        this._is.close();
        this._back = -1;
        this._closed = true;
    }
    
    public int read() throws IOException {
        if (this._back != -1) {
            final int back = this._back;
            this._back = -1;
            return back;
        }
        return this._is.read();
    }
    
    public int read(final byte[] array) throws IOException {
        return this.read(array, 0, array.length);
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (n2 == 0) {
            return 0;
        }
        if (this._back != -1) {
            array[n] = (byte)this._back;
            this._back = -1;
            return 1;
        }
        return this._is.read(array, n, n2);
    }
    
    public int available() throws IOException {
        if (this._back != -1) {
            return 1 + this._is.available();
        }
        return this._is.available();
    }
    
    public void unread(final byte back) {
        if (this._closed) {
            return;
        }
        this._back = back;
    }
}
