// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;

class IndefiniteLengthInputStream extends LimitedInputStream
{
    private int _b1;
    private int _b2;
    private boolean _eofReached;
    private boolean _eofOn00;
    
    IndefiniteLengthInputStream(final InputStream inputStream) throws IOException {
        super(inputStream);
        this._eofReached = false;
        this._eofOn00 = true;
        this._b1 = inputStream.read();
        this._b2 = inputStream.read();
        if (this._b2 < 0) {
            throw new EOFException();
        }
        this.checkForEof();
    }
    
    void setEofOn00(final boolean eofOn00) {
        this._eofOn00 = eofOn00;
        this.checkForEof();
    }
    
    private boolean checkForEof() {
        if (!this._eofReached && this._eofOn00 && this._b1 == 0 && this._b2 == 0) {
            this.setParentEofDetect(this._eofReached = true);
        }
        return this._eofReached;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (this._eofOn00 || n2 < 3) {
            return super.read(array, n, n2);
        }
        if (this._eofReached) {
            return -1;
        }
        final int read = super._in.read(array, n + 2, n2 - 2);
        if (read < 0) {
            throw new EOFException();
        }
        array[n] = (byte)this._b1;
        array[n + 1] = (byte)this._b2;
        this._b1 = super._in.read();
        this._b2 = super._in.read();
        if (this._b2 < 0) {
            throw new EOFException();
        }
        return read + 2;
    }
    
    public int read() throws IOException {
        if (this.checkForEof()) {
            return -1;
        }
        final int read = super._in.read();
        if (read < 0) {
            throw new EOFException();
        }
        final int b1 = this._b1;
        this._b1 = this._b2;
        this._b2 = read;
        return b1;
    }
}
