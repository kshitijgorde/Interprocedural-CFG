// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class dg extends bz implements bf
{
    private String t;
    private String jf;
    private String kf;
    private String ke;
    
    public dg() {
        this.t = "";
        this.jf = "";
        this.kf = "";
        this.ke = "";
    }
    
    public dg(final String t, final String jf, final String kf, final String ke) {
        this.t = "";
        this.jf = "";
        this.kf = "";
        this.ke = "";
        this.t = t;
        this.jf = jf;
        this.kf = kf;
        this.ke = ke;
    }
    
    public void yb(final String jf) {
        this.p(2);
        this.jf = jf;
    }
    
    public String ld() {
        return this.t;
    }
    
    public String qc() {
        return this.jf;
    }
    
    public String pc() {
        return this.kf;
    }
    
    public String lc() {
        return this.ke;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.t);
        dataOutputStream.writeUTF(this.jf);
        dataOutputStream.writeUTF(this.kf);
        dataOutputStream.writeUTF(this.ke);
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.t = dataInputStream.readUTF();
        this.jf = dataInputStream.readUTF();
        this.kf = dataInputStream.readUTF();
        this.ke = dataInputStream.readUTF();
    }
}
