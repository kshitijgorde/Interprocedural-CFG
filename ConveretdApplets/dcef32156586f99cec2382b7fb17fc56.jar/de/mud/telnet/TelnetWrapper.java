// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.telnet;

import java.io.OutputStream;
import java.awt.Dimension;
import java.io.IOException;
import de.mud.jta.Wrapper;

public class TelnetWrapper extends Wrapper
{
    protected TelnetProtocolHandler handler;
    private static final int debug = 0;
    
    public TelnetWrapper() {
        this.handler = new TelnetProtocolHandler() {
            public String getTerminalType() {
                return "vt320";
            }
            
            public Dimension getWindowSize() {
                return new Dimension(80, 25);
            }
            
            public void setLocalEcho(final boolean echo) {
            }
            
            public void write(final byte[] b) throws IOException {
                TelnetWrapper.this.out.write(b);
            }
            
            public void notifyEndOfRecord() {
            }
        };
    }
    
    public TelnetProtocolHandler getHandler() {
        return this.handler;
    }
    
    public void connect(final String host, final int port) throws IOException {
        super.connect(host, port);
        this.handler.reset();
    }
    
    public String send(final String cmd) throws IOException {
        final byte[] arr = (cmd + "\n").getBytes();
        this.handler.transpose(arr);
        if (this.getPrompt() != null) {
            return this.waitfor(this.getPrompt());
        }
        return null;
    }
    
    public int read(final byte[] b) throws IOException {
        int n;
        do {
            n = this.handler.negotiate(b);
            if (n > 0) {
                return n;
            }
        } while (n == 0);
        while (n <= 0) {
            do {
                n = this.handler.negotiate(b);
                if (n > 0) {
                    return n;
                }
            } while (n == 0);
            n = this.in.read(b);
            if (n < 0) {
                return n;
            }
            this.handler.inputfeed(b, n);
            n = this.handler.negotiate(b);
        }
        return n;
    }
}
