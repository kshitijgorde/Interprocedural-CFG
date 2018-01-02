// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.io.OutputStream;
import java.awt.Dimension;
import java.io.IOException;
import de.mud.jta.Wrapper;

public class SshWrapper extends Wrapper
{
    protected SshIO handler;
    private static final int debug = 0;
    private byte[] buffer;
    private int pos;
    
    public SshWrapper() {
        this.handler = new SshIO() {
            public String getTerminalType() {
                return "vt320";
            }
            
            public Dimension getWindowSize() {
                return new Dimension(80, 25);
            }
            
            public void setLocalEcho(final boolean echo) {
            }
            
            public void write(final byte[] b) throws IOException {
                SshWrapper.this.out.write(b);
            }
        };
    }
    
    public String send(final String cmd) throws IOException {
        final byte[] arr = (cmd + "\n").getBytes();
        for (int i = 0; i < arr.length; ++i) {
            switch (arr[i]) {
                case 10: {
                    arr[i] = 13;
                    break;
                }
            }
        }
        this.handler.sendData(new String(arr));
        if (this.getPrompt() != null) {
            return this.waitfor(this.getPrompt());
        }
        return null;
    }
    
    public int read(final byte[] b) throws IOException {
        if (this.buffer != null) {
            final int amount = (this.buffer.length - this.pos <= b.length) ? (this.buffer.length - this.pos) : b.length;
            System.arraycopy(this.buffer, this.pos, b, 0, amount);
            if (this.pos + amount < this.buffer.length) {
                this.pos += amount;
            }
            else {
                this.buffer = null;
            }
            return amount;
        }
        int n = this.in.read(b);
        if (n > 0) {
            final byte[] tmp = new byte[n];
            System.arraycopy(b, 0, tmp, 0, n);
            this.pos = 0;
            this.buffer = this.handler.handleSSH(tmp);
            if (this.buffer == null || this.buffer.length <= 0) {
                return 0;
            }
            final int amount2 = (this.buffer.length <= b.length) ? this.buffer.length : b.length;
            System.arraycopy(this.buffer, 0, b, 0, amount2);
            n = (this.pos = amount2);
            if (amount2 == this.buffer.length) {
                this.buffer = null;
                this.pos = 0;
            }
        }
        return n;
    }
}
