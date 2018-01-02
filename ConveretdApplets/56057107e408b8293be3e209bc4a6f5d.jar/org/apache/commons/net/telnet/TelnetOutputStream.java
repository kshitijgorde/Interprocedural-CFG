// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.telnet;

import java.io.IOException;
import java.io.OutputStream;

final class TelnetOutputStream extends OutputStream
{
    private TelnetClient __client;
    private boolean __convertCRtoCRLF;
    private boolean __lastWasCR;
    
    TelnetOutputStream(final TelnetClient client) {
        this.__convertCRtoCRLF = true;
        this.__lastWasCR = false;
        this.__client = client;
    }
    
    public void write(int ch) throws IOException {
        synchronized (this.__client) {
            ch &= 0xFF;
            if (this.__client._requestedWont(TelnetOption.BINARY)) {
                if (this.__lastWasCR) {
                    if (this.__convertCRtoCRLF) {
                        this.__client._sendByte(10);
                        if (ch == 10) {
                            this.__lastWasCR = false;
                            return;
                        }
                    }
                    else if (ch != 10) {
                        this.__client._sendByte(0);
                    }
                }
                this.__lastWasCR = false;
                switch (ch) {
                    case 13: {
                        this.__client._sendByte(13);
                        this.__lastWasCR = true;
                        break;
                    }
                    case 255: {
                        this.__client._sendByte(255);
                        this.__client._sendByte(255);
                        break;
                    }
                    default: {
                        this.__client._sendByte(ch);
                        break;
                    }
                }
            }
            else if (ch == 255) {
                this.__client._sendByte(ch);
                this.__client._sendByte(255);
            }
            else {
                this.__client._sendByte(ch);
            }
        }
    }
    
    public void write(final byte[] buffer) throws IOException {
        this.write(buffer, 0, buffer.length);
    }
    
    public void write(final byte[] buffer, int offset, int length) throws IOException {
        synchronized (this.__client) {
            while (length-- > 0) {
                this.write(buffer[offset++]);
            }
        }
    }
    
    public void flush() throws IOException {
        this.__client._flushOutputStream();
    }
    
    public void close() throws IOException {
        this.__client._closeOutputStream();
    }
}
