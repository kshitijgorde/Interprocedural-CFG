// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;
import java.io.InputStream;

public class LazyReadInputStream extends DemultiplexorInputStream
{
    private byte[] m_buf;
    private byte[] m_oldBuf;
    private byte[] m_eodStr;
    private InputStream m_underlyingStream;
    private boolean m_endOfUnderlyingStreamReached;
    
    public LazyReadInputStream(final InputStream underlyingStream) {
        super(underlyingStream);
        this.m_buf = null;
        this.m_eodStr = null;
        this.m_oldBuf = new byte[0];
        this.m_underlyingStream = underlyingStream;
        this.m_endOfUnderlyingStreamReached = false;
    }
    
    public synchronized int read() throws IOException {
        int ahead = -1;
        if (this.m_eodStr != null) {
            if (this.m_buf == null) {
                this.m_buf = new byte[this.m_eodStr.length];
                int i = 0;
                while (i < this.m_buf.length) {
                    final int ahead2 = this.readAhead();
                    if (ahead2 != -1) {
                        this.m_buf[i] = (byte)ahead2;
                        ++i;
                    }
                    else {
                        final byte[] buf = new byte[i];
                        System.arraycopy(this.m_buf, 0, buf, 0, i);
                        this.m_buf = buf;
                    }
                }
            }
            int n = 1;
            if (this.m_buf.length == this.m_eodStr.length) {
                for (int j = 0; j < this.m_eodStr.length; ++j) {
                    if (this.m_buf[j] != this.m_eodStr[j]) {
                        n = 0;
                        break;
                    }
                }
            }
            if (this.m_buf.length > 0) {
                final byte[] array = new byte[this.m_buf.length - 1];
                System.arraycopy(this.m_buf, 1, array, 0, array.length);
                ahead = (this.m_buf[0] & 0xFF);
                if (n == 1) {
                    this.m_buf = array;
                }
                else {
                    final int ahead3 = this.readAhead();
                    if (ahead3 != -1) {
                        System.arraycopy(array, 0, this.m_buf, 0, array.length);
                        this.m_buf[this.m_buf.length - 1] = (byte)ahead3;
                    }
                    else {
                        this.m_buf = array;
                    }
                }
            }
        }
        else {
            ahead = this.readAhead();
        }
        return ahead;
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
        int n3 = 0;
        boolean b = false;
        while (!b && n3 < n2) {
            final int read = this.read();
            if (read == -1) {
                b = true;
            }
            else {
                array[n + n3] = (byte)read;
                ++n3;
            }
        }
        if (b && n3 == 0) {
            n3 = -1;
        }
        return n3;
    }
    
    public synchronized int available() throws IOException {
        int n;
        if (this.m_eodStr == null) {
            n = this.m_oldBuf.length + this.m_underlyingStream.available();
        }
        else if (this.m_buf == null) {
            n = Math.max(0, Math.min(this.m_eodStr.length, this.m_oldBuf.length + this.m_underlyingStream.available() - this.m_eodStr.length));
        }
        else {
            n = Math.min(this.m_buf.length, this.m_oldBuf.length + this.m_underlyingStream.available());
        }
        return n;
    }
    
    public synchronized void setTerminator(final byte[] eodStr, final int[] array) {
        this.m_eodStr = eodStr;
        if (this.m_buf != null) {
            final byte[] oldBuf = new byte[this.m_oldBuf.length + this.m_buf.length];
            System.arraycopy(this.m_oldBuf, 0, oldBuf, 0, this.m_oldBuf.length);
            System.arraycopy(this.m_buf, 0, oldBuf, this.m_oldBuf.length, this.m_buf.length);
            this.m_oldBuf = oldBuf;
        }
        this.m_buf = null;
    }
    
    public synchronized boolean atEnd() {
        boolean b = false;
        if (this.m_eodStr != null && this.m_buf != null && !this.m_endOfUnderlyingStreamReached && this.m_buf.length == 0) {
            b = true;
        }
        return b;
    }
    
    public synchronized boolean startsWithCRLF() throws IOException {
        boolean b = false;
        if (this.m_buf != null && this.m_buf.length > 0) {
            if (this.m_buf[0] == 13) {
                if (this.m_buf.length > 1) {
                    if (this.m_buf[1] == 10) {
                        b = true;
                        this.read();
                        this.read();
                    }
                }
                else if (this.m_oldBuf.length > 0) {
                    if (this.m_oldBuf[0] == 10) {
                        b = true;
                        this.readAhead();
                        this.read();
                    }
                }
                else {
                    final int read = this.m_underlyingStream.read();
                    if (read != -1) {
                        if ((byte)read == 10) {
                            b = true;
                            this.read();
                        }
                        else {
                            (this.m_oldBuf = new byte[1])[0] = (byte)read;
                        }
                    }
                }
            }
        }
        else if (this.m_oldBuf.length > 0) {
            if (this.m_oldBuf[0] == 13) {
                if (this.m_oldBuf.length > 1) {
                    if (this.m_oldBuf[1] == 10) {
                        b = true;
                        this.readAhead();
                        this.readAhead();
                    }
                }
                else {
                    final int read2 = this.m_underlyingStream.read();
                    if (read2 != -1) {
                        if ((byte)read2 == 10) {
                            b = true;
                            this.readAhead();
                        }
                        else {
                            this.m_oldBuf = new byte[] { this.m_oldBuf[0], (byte)read2 };
                        }
                    }
                }
            }
        }
        else {
            final int read3 = this.m_underlyingStream.read();
            if (read3 != -1) {
                if ((byte)read3 == 13) {
                    final int read4 = this.m_underlyingStream.read();
                    if (read4 != -1) {
                        if ((byte)read4 == 10) {
                            b = true;
                        }
                        else {
                            (this.m_oldBuf = new byte[2])[0] = (byte)read3;
                            this.m_oldBuf[1] = (byte)read4;
                        }
                    }
                    else {
                        (this.m_oldBuf = new byte[1])[0] = (byte)read3;
                    }
                }
                else {
                    (this.m_oldBuf = new byte[1])[0] = (byte)read3;
                }
            }
        }
        return b;
    }
    
    private synchronized int readAhead() throws IOException {
        int read;
        if (this.m_oldBuf.length > 0) {
            final byte[] oldBuf = new byte[this.m_oldBuf.length - 1];
            System.arraycopy(this.m_oldBuf, 1, oldBuf, 0, oldBuf.length);
            read = (this.m_oldBuf[0] & 0xFF);
            this.m_oldBuf = oldBuf;
        }
        else {
            read = this.m_underlyingStream.read();
            if (read == -1) {
                this.m_endOfUnderlyingStreamReached = true;
            }
        }
        return read;
    }
}
