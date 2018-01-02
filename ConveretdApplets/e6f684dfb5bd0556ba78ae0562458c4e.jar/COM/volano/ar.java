// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class ar extends bz implements bf
{
    private String t;
    private String jf;
    private String kf;
    
    public ar() {
        this.t = "";
        this.jf = "";
        this.kf = "";
    }
    
    public ar(final String t, final String jf, final String kf) {
        this.t = "";
        this.jf = "";
        this.kf = "";
        this.t = t;
        this.jf = jf;
        this.kf = kf;
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
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.t);
        dataOutputStream.writeUTF(this.jf);
        dataOutputStream.writeUTF(this.kf);
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.t = dataInputStream.readUTF();
        this.jf = dataInputStream.readUTF();
        this.kf = dataInputStream.readUTF();
    }
}
