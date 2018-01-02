// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class QueueOutputStream extends MessageOutput
{
    protected ByteArrayOutputStream byteO;
    protected Queue q;
    
    public void close() {
        this.q.terminateNow();
    }
    
    public QueueOutputStream(final Queue q) {
        super(new ByteArrayOutputStream());
        this.byteO = (ByteArrayOutputStream)super.out;
        this.q = q;
    }
    
    public void send() {
        final byte[] buffer = this.byteO.toByteArray();
        this.byteO.reset();
        this.q.add(buffer);
    }
}
