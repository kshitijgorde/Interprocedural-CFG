// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

public class MessageOutputStream extends MessageOutput
{
    protected OutputStream o;
    protected DataOutputStream dataO;
    protected ByteArrayOutputStream byteO;
    
    public MessageOutputStream(final OutputStream o) {
        super(new ByteArrayOutputStream());
        this.byteO = (ByteArrayOutputStream)super.out;
        this.o = o;
        this.dataO = new DataOutputStream(o);
    }
    
    public final void mywriteUTF(final String str) throws IOException {
        final byte[] outbyte = new byte[str.length()];
        str.getBytes(0, str.length(), outbyte, 0);
        super.out.write(outbyte);
    }
    
    public void send() throws IOException {
        synchronized (this.o) {
            this.dataO.writeInt(this.byteO.size());
            this.byteO.writeTo(this.o);
        }
        // monitorexit(this.o)
        this.byteO.reset();
        this.o.flush();
    }
}
