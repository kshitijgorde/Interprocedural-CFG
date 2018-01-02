// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class Access extends bz
{
    public static final String yg = "2.1.0";
    public static final String zg = "2.1.0p";
    public static final int ne = 1;
    public static final int eh = 2;
    public static final int fh = 3;
    public static final int gh = 4;
    public static final int hh = 5;
    private String ih;
    private String jh;
    private String vg;
    private String kh;
    private String lh;
    private String mh;
    private String nh;
    private String oh;
    private String ph;
    private String qh;
    private String rh;
    private int ue;
    private String[] lg;
    
    public Access() {
        this.ih = "";
        this.jh = "";
        this.vg = "";
        this.kh = "";
        this.lh = "";
        this.mh = "";
        this.nh = "";
        this.oh = "";
        this.ph = "";
        this.qh = "";
        this.rh = "";
        this.lg = new String[0];
    }
    
    public Access(final String ih, final String jh, final String vg, final String kh, final String lh, final String mh, final String nh, final String oh, final String ph, final String qh, final String rh) {
        this.ih = "";
        this.jh = "";
        this.vg = "";
        this.kh = "";
        this.lh = "";
        this.mh = "";
        this.nh = "";
        this.oh = "";
        this.ph = "";
        this.qh = "";
        this.rh = "";
        this.lg = new String[0];
        this.ih = ih;
        this.jh = jh;
        this.vg = vg;
        this.kh = kh;
        this.lh = lh;
        this.mh = mh;
        this.nh = nh;
        this.oh = oh;
        this.ph = ph;
        this.qh = qh;
        this.rh = rh;
    }
    
    public void m(final int ue) {
        this.p(4);
        this.ue = ue;
    }
    
    public void m(final int n, final String[] lg) {
        this.m(n);
        this.lg = lg;
    }
    
    public String td() {
        return this.ih;
    }
    
    public String od() {
        return this.jh;
    }
    
    public String nd() {
        return this.vg;
    }
    
    public String qd() {
        return this.kh;
    }
    
    public String wd() {
        return this.lh;
    }
    
    public String ud() {
        return this.mh;
    }
    
    public String rd() {
        return this.nh;
    }
    
    public String pd() {
        return this.oh;
    }
    
    public String vd() {
        return this.ph;
    }
    
    public String sd() {
        return this.qh;
    }
    
    public String xd() {
        return this.rh;
    }
    
    public int tb() {
        return this.ue;
    }
    
    public String[] kd() {
        return this.lg;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        switch (this.o()) {
            case 1: {
                dataOutputStream.writeUTF(this.ih);
                dataOutputStream.writeUTF(this.jh);
                dataOutputStream.writeUTF(this.vg);
                dataOutputStream.writeUTF(this.kh);
                dataOutputStream.writeUTF(this.lh);
                dataOutputStream.writeUTF(this.mh);
                dataOutputStream.writeUTF(this.nh);
                dataOutputStream.writeUTF(this.oh);
                dataOutputStream.writeUTF(this.ph);
                dataOutputStream.writeUTF(this.qh);
                dataOutputStream.writeUTF(this.rh);
            }
            case 4: {
                dataOutputStream.writeInt(this.ue);
                dataOutputStream.writeInt(this.lg.length);
                for (int i = 0; i < this.lg.length; ++i) {
                    dataOutputStream.writeUTF(this.lg[i]);
                }
            }
            default: {}
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        switch (this.o()) {
            case 1: {
                this.ih = dataInputStream.readUTF().trim();
                this.jh = dataInputStream.readUTF();
                this.vg = dataInputStream.readUTF();
                this.kh = dataInputStream.readUTF();
                this.lh = dataInputStream.readUTF();
                this.mh = dataInputStream.readUTF();
                this.nh = dataInputStream.readUTF();
                this.oh = dataInputStream.readUTF();
                this.ph = dataInputStream.readUTF();
                this.qh = dataInputStream.readUTF();
                this.rh = dataInputStream.readUTF();
            }
            case 4: {
                this.ue = dataInputStream.readInt();
                final int int1 = dataInputStream.readInt();
                this.lg = new String[int1];
                for (int i = 0; i < int1; ++i) {
                    this.lg[i] = dataInputStream.readUTF();
                }
            }
            default: {}
        }
    }
}
