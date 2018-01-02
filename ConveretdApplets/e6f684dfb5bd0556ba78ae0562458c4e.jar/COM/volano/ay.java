// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class ay extends bz implements bf
{
    public static final int ot = 1;
    public static final int pt = 2;
    public static final int qt = 3;
    private String t;
    private String rt;
    private String u;
    private int st;
    
    public ay() {
        this.t = "";
        this.rt = "";
        this.u = "";
        this.st = 1;
    }
    
    public ay(final String t, final String rt, final String u, final int st) {
        this.t = "";
        this.rt = "";
        this.u = "";
        this.st = 1;
        this.t = t;
        this.rt = rt;
        this.u = u;
        this.st = st;
    }
    
    public String ld() {
        return this.t;
    }
    
    public String oh() {
        return this.rt;
    }
    
    public String n() {
        return this.u;
    }
    
    public int nh() {
        return this.st;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.t);
        dataOutputStream.writeUTF(this.rt);
        dataOutputStream.writeUTF(this.u);
        dataOutputStream.writeInt(this.st);
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.t = dataInputStream.readUTF();
        this.rt = dataInputStream.readUTF();
        this.u = dataInputStream.readUTF();
        this.st = dataInputStream.readInt();
    }
}
