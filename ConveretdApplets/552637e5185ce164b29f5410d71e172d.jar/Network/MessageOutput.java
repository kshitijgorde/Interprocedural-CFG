// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;

public abstract class MessageOutput extends DataOutputStream
{
    public MessageOutput(final OutputStream out) {
        super(out);
    }
    
    public void mywriteUTF(final String s) throws IOException {
        final byte[] outstr = new byte[s.length()];
        final int length = s.length();
        if (length < 0 || length > 3000) {
            System.out.println("Length is: " + length);
            System.out.println("String was: " + s);
        }
        s.getBytes(0, s.length(), outstr, 0);
        super.out.write(outstr);
    }
    
    public abstract void send() throws IOException;
    
    public void send(final String[] dst) throws IOException {
        throw new IOException("send[] not supported");
    }
    
    public void send(final String dst) throws IOException {
        final String[] dsts = { dst };
        this.send(dsts);
    }
}
