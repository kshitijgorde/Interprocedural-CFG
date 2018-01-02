// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;

public abstract class MessageInput extends DataInputStream
{
    byte[] buffer;
    
    public MessageInput(final InputStream in) {
        super(in);
    }
    
    public String myreadUTF() throws IOException {
        this.buffer = new byte[super.in.available()];
        final int count = super.in.read(this.buffer);
        final String ret = new String(this.buffer, 0);
        return ret;
    }
    
    public abstract void receive() throws IOException;
}
