// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class bg extends bz implements bf
{
    public static final int ne = 1;
    public static final int qe = 2;
    private String t;
    private int ue;
    private int ve;
    private String vg;
    private String[][] we;
    
    public bg() {
        this.t = "";
        this.vg = "";
        this.we = new String[0][];
    }
    
    public bg(final String t) {
        this.t = "";
        this.vg = "";
        this.we = new String[0][];
        this.t = t;
    }
    
    public void m(final int ue) {
        this.p(4);
        this.ue = ue;
    }
    
    public void m(final int n, final int ve, final String vg, final String[][] we) {
        this.m(n);
        this.ve = ve;
        this.vg = vg;
        this.we = we;
    }
    
    public int tb() {
        return this.ue;
    }
    
    public int wb() {
        return this.ve;
    }
    
    public String ld() {
        return this.t;
    }
    
    public String nd() {
        return this.vg;
    }
    
    public String[][] qb() {
        return this.we;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.t);
        if (this.o() == 4) {
            dataOutputStream.writeInt(this.ue);
            dataOutputStream.writeInt(this.ve);
            dataOutputStream.writeUTF(this.vg);
            dataOutputStream.writeInt(this.we.length);
            for (int i = 0; i < this.we.length; ++i) {
                dataOutputStream.writeUTF(this.we[i][0]);
                dataOutputStream.writeUTF(this.we[i][1]);
                dataOutputStream.writeUTF(this.we[i][2]);
                dataOutputStream.writeUTF(this.we[i][3]);
                dataOutputStream.writeUTF(this.we[i][4]);
            }
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.t = dataInputStream.readUTF();
        if (this.o() == 4) {
            this.ue = dataInputStream.readInt();
            this.ve = dataInputStream.readInt();
            this.vg = dataInputStream.readUTF();
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
    }
}
