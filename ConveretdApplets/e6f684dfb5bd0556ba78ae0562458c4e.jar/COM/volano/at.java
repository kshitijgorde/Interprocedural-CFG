// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class at extends bz implements bf, bc
{
    private int ef;
    private String t;
    private String jf;
    private String kf;
    private String lf;
    private String mf;
    private String nf;
    private String of;
    
    public at() {
        this.t = "";
        this.jf = "";
        this.kf = "";
        this.lf = "";
        this.mf = "";
        this.nf = "";
        this.of = "";
    }
    
    public at(final String t, final String jf, final String kf) {
        this.t = "";
        this.jf = "";
        this.kf = "";
        this.lf = "";
        this.mf = "";
        this.nf = "";
        this.of = "";
        this.t = t;
        this.jf = jf;
        this.kf = kf;
    }
    
    public void yb(final int ef, final String jf, final String lf, final String mf) {
        this.p(2);
        this.ef = ef;
        this.jf = jf;
        this.lf = lf;
        this.mf = mf;
    }
    
    public void m(final int ef, final String jf, final String nf, final String of) {
        this.p(4);
        this.ef = ef;
        this.jf = jf;
        this.nf = nf;
        this.of = of;
    }
    
    public int hc() {
        return this.ef;
    }
    
    public String ld() {
        return this.t;
    }
    
    public String qc() {
        return this.jf;
    }
    
    public String oc() {
        return this.lf;
    }
    
    public String nc() {
        return this.mf;
    }
    
    public String pc() {
        return this.kf;
    }
    
    public String rc() {
        return this.nf;
    }
    
    public String mc() {
        return this.of;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.t);
        dataOutputStream.writeUTF(this.jf);
        dataOutputStream.writeUTF(this.kf);
        switch (this.o()) {
            case 2: {
                dataOutputStream.writeInt(this.ef);
                dataOutputStream.writeUTF(this.lf);
                dataOutputStream.writeUTF(this.mf);
            }
            case 4: {
                dataOutputStream.writeInt(this.ef);
                dataOutputStream.writeUTF(this.nf);
                dataOutputStream.writeUTF(this.of);
            }
            default: {}
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.t = dataInputStream.readUTF();
        this.jf = dataInputStream.readUTF();
        this.kf = dataInputStream.readUTF();
        switch (this.o()) {
            case 2: {
                this.ef = dataInputStream.readInt();
                this.lf = dataInputStream.readUTF();
                this.mf = dataInputStream.readUTF();
            }
            case 4: {
                this.ef = dataInputStream.readInt();
                this.nf = dataInputStream.readUTF();
                this.of = dataInputStream.readUTF();
            }
            default: {}
        }
    }
}
