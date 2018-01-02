// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.IOException;
import java.io.InputStream;

public class MultiplexInputStream extends MessageInput
{
    public String label;
    protected MessageInput i;
    
    public MultiplexInputStream(final MessageInput i) {
        super(i);
        this.i = i;
    }
    
    public void receive() throws IOException {
        this.i.receive();
        this.label = this.i.myreadUTF();
        this.i.receive();
    }
}
