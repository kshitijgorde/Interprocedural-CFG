// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class as extends bz implements bf, bc
{
    private int ef;
    private String t;
    private String u;
    private String ke;
    
    public as() {
        this.t = "";
        this.u = "";
        this.ke = "";
    }
    
    public as(final String t, final String u, final String ke) {
        this.t = "";
        this.u = "";
        this.ke = "";
        this.t = t;
        this.u = u;
        this.ke = ke;
    }
    
    public as(final int ef, final String u, final String ke) {
        this.t = "";
        this.u = "";
        this.ke = "";
        this.ef = ef;
        this.u = u;
        this.ke = ke;
    }
    
    public as(final String ke) {
        this.t = "";
        this.u = "";
        this.ke = "";
        this.ke = ke;
    }
    
    public void yb() {
        this.p(2);
    }
    
    public void yb(final int ef, final String u) {
        this.p(2);
        this.ef = ef;
        this.u = u;
    }
    
    public void yb(final String t) {
        this.p(2);
        this.t = t;
    }
    
    public void zh(final String u) {
        this.p(2);
        this.u = u;
    }
    
    public int hc() {
        return this.ef;
    }
    
    public String ld() {
        return this.t;
    }
    
    public void yh(final String t) {
        this.t = t;
    }
    
    public String n() {
        return this.u;
    }
    
    public String lc() {
        return this.ke;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeInt(this.ef);
        dataOutputStream.writeUTF(this.t);
        dataOutputStream.writeUTF(this.u);
        dataOutputStream.writeUTF(this.ke);
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.ef = dataInputStream.readInt();
        this.t = dataInputStream.readUTF();
        this.u = dataInputStream.readUTF().trim();
        this.ke = dataInputStream.readUTF().trim();
    }
}
