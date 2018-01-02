// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;

public class MessageInputStream extends MessageInput
{
    protected InputStream i;
    protected DataInputStream dataI;
    byte[] buffer;
    
    public MessageInputStream(final InputStream i) {
        super(null);
        this.i = i;
        this.dataI = new DataInputStream(i);
    }
    
    public final String myreadUTF() throws IOException {
        synchronized (this.i) {
            this.buffer = new byte[super.in.available()];
            final int count = super.in.read(this.buffer);
            final String s;
            final String ret = s = new String(this.buffer, 0);
            // monitorexit(this.i)
            return s;
        }
    }
    
    public void receive() throws IOException {
        synchronized (this.i) {
            final int n = this.dataI.readInt();
            if (n > 8000) {
                System.out.println("Receive, sensible check, n too large.");
                throw new IOException("Error reading message length");
            }
            this.buffer = new byte[n];
            this.dataI.readFully(this.buffer);
        }
        // monitorexit(this.i)
        super.in = new ByteArrayInputStream(this.buffer);
    }
}
