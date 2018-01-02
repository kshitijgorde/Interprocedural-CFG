// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class QueueInputStream extends MessageInput
{
    protected Queue q;
    byte[] buffer;
    
    public QueueInputStream(final Queue q) {
        super(null);
        this.q = q;
    }
    
    public void receive() throws IOException {
        this.buffer = (byte[])this.q.remove();
        super.in = new ByteArrayInputStream(this.buffer);
    }
}
