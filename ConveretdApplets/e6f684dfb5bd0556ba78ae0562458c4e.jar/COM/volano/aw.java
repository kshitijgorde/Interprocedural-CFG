// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class aw extends bz implements bf
{
    private String t;
    private String u;
    
    public aw() {
    }
    
    public aw(final String t, final String u) {
        this.t = t;
        this.u = u;
    }
    
    public void yb() {
        this.p(2);
    }
    
    public void yb(final String u) {
        this.p(2);
        this.u = u;
    }
    
    public void xb() {
        this.p(2);
        this.u = "";
    }
    
    public String ld() {
        return this.t;
    }
    
    public String n() {
        return this.u;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.t);
        dataOutputStream.writeUTF(this.u);
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.t = dataInputStream.readUTF();
        this.u = dataInputStream.readUTF();
    }
}
