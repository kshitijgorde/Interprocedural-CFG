// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class dj extends bz
{
    private static final int ec = 256;
    private static final int fc = 128;
    private static final String gc;
    private static final String hc;
    private String[] ic;
    
    public dj() {
        this.ic = new String[0];
    }
    
    public dj(final String[] ic) throws IOException {
        this.ic = new String[0];
        this.ic = ic;
        if (ic.length > 256) {
            throw new IOException(dj.gc);
        }
        for (int i = 0; i < ic.length; ++i) {
            if (ic[i].length() > 128) {
                throw new IOException(dj.hc);
            }
        }
    }
    
    public String[] r() {
        return this.ic;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeInt(this.ic.length);
        for (int i = 0; i < this.ic.length; ++i) {
            dataOutputStream.writeUTF(this.ic[i]);
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        final int int1 = dataInputStream.readInt();
        if (int1 > 256) {
            throw new IOException(dj.gc);
        }
        this.ic = new String[int1];
        for (int i = 0; i < int1; ++i) {
            this.ic[i] = dataInputStream.readUTF();
            if (this.ic[i].length() > 128) {
                throw new IOException(dj.hc);
            }
        }
    }
    
    static {
        gc = "Room list size exceeds " + 256;
        hc = "Room name length exceeds " + 128;
    }
}
