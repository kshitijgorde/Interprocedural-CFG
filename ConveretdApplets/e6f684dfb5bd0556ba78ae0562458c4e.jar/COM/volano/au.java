// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class au extends bz implements bf
{
    public static final int ne = 1;
    public static final int m = 2;
    public static final int oe = 3;
    public static final int pe = 4;
    public static final int qe = 5;
    private String t;
    private String u;
    private String re;
    private String v;
    private String se;
    private String te;
    private int ue;
    private int ve;
    private int nd;
    private String[][] we;
    
    public au() {
        this.t = "";
        this.u = "";
        this.re = "";
        this.v = "";
        this.se = "";
        this.te = "";
        this.we = new String[0][];
    }
    
    public au(final String t, final String u, final String re) {
        this.t = "";
        this.u = "";
        this.re = "";
        this.v = "";
        this.se = "";
        this.te = "";
        this.we = new String[0][];
        this.t = t;
        this.u = u;
        this.re = re;
    }
    
    public void yb(final String v, final String se, final String te) {
        this.p(2);
        this.v = v;
        this.se = se;
        this.te = te;
    }
    
    public void xb() {
        this.p(2);
        this.u = "";
        this.re = "";
    }
    
    public void m(final int ue) {
        this.p(4);
        this.ue = ue;
    }
    
    public void m(final int n, final int ve, final int nd, final String[][] we) {
        this.m(n);
        this.ve = ve;
        this.nd = nd;
        this.we = we;
    }
    
    public String ld() {
        return this.t;
    }
    
    public String n() {
        return this.u;
    }
    
    public String rb() {
        return this.re;
    }
    
    public int tb() {
        return this.ue;
    }
    
    public int wb() {
        return this.ve;
    }
    
    public int sb() {
        return this.nd;
    }
    
    public String l() {
        return this.v;
    }
    
    public String vb() {
        return this.se;
    }
    
    public String ub() {
        return this.te;
    }
    
    public String[][] qb() {
        return this.we;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.t);
        dataOutputStream.writeUTF(this.u);
        dataOutputStream.writeUTF(this.re);
        switch (this.o()) {
            case 2: {
                dataOutputStream.writeUTF(this.v);
                dataOutputStream.writeUTF(this.se);
                dataOutputStream.writeUTF(this.te);
            }
            case 4: {
                dataOutputStream.writeInt(this.ue);
                dataOutputStream.writeInt(this.ve);
                dataOutputStream.writeInt(this.nd);
                dataOutputStream.writeInt(this.we.length);
                for (int i = 0; i < this.we.length; ++i) {
                    dataOutputStream.writeUTF(this.we[i][0]);
                    dataOutputStream.writeUTF(this.we[i][1]);
                    dataOutputStream.writeUTF(this.we[i][2]);
                    dataOutputStream.writeUTF(this.we[i][3]);
                    dataOutputStream.writeUTF(this.we[i][4]);
                }
            }
            default: {}
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.t = dataInputStream.readUTF();
        this.u = dataInputStream.readUTF().trim();
        this.re = dataInputStream.readUTF().trim();
        switch (this.o()) {
            case 2: {
                this.v = dataInputStream.readUTF();
                this.se = dataInputStream.readUTF();
                this.te = dataInputStream.readUTF();
            }
            case 4: {
                this.ue = dataInputStream.readInt();
                this.ve = dataInputStream.readInt();
                this.nd = dataInputStream.readInt();
                final int int1 = dataInputStream.readInt();
                this.we = new String[int1][5];
                for (int i = 0; i < int1; ++i) {
                    this.we[i][0] = dataInputStream.readUTF();
                    this.we[i][1] = dataInputStream.readUTF();
                    this.we[i][2] = dataInputStream.readUTF();
                    this.we[i][3] = dataInputStream.readUTF();
                    this.we[i][4] = dataInputStream.readUTF();
                }
            }
            default: {}
        }
    }
}
