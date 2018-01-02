// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class av extends bz implements bc
{
    private int ef;
    private String jf;
    
    public av() {
    }
    
    public av(final int ef, final String jf) {
        this.ef = ef;
        this.jf = jf;
    }
    
    public void yb() {
        this.p(2);
    }
    
    public void yb(final String jf) {
        this.p(2);
        this.jf = jf;
    }
    
    public int hc() {
        return this.ef;
    }
    
    public String qc() {
        return this.jf;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeInt(this.ef);
        dataOutputStream.writeUTF(this.jf);
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.ef = dataInputStream.readInt();
        this.jf = dataInputStream.readUTF();
    }
}
