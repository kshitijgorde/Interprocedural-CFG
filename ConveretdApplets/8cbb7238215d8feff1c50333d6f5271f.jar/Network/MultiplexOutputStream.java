// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class MultiplexOutputStream extends MessageOutput
{
    protected MessageOutput o;
    protected ByteArrayOutputStream byteO;
    protected String label;
    
    public MultiplexOutputStream(final MessageOutput o, final String label) {
        super(new ByteArrayOutputStream());
        this.byteO = (ByteArrayOutputStream)super.out;
        this.o = o;
        this.label = label;
    }
    
    public void send() throws IOException {
        synchronized (this.o) {
            this.o.mywriteUTF(this.label);
            this.o.send();
            this.byteO.writeTo(this.o);
            this.o.send();
        }
        // monitorexit(this.o)
        this.byteO.reset();
    }
    
    public void send(final String[] dst) throws IOException {
        synchronized (this.o) {
            this.o.mywriteUTF(this.label);
            this.byteO.writeTo(this.o);
            this.o.send(dst);
        }
        // monitorexit(this.o)
        this.byteO.reset();
    }
}
