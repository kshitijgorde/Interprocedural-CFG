// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class ba extends Access
{
    private boolean se;
    private boolean yk;
    private boolean zk;
    private String ri;
    private String dl;
    private String el;
    private boolean fl;
    private String gl;
    private String re;
    
    public ba() {
        this.ri = "";
        this.dl = "";
        this.el = "";
        this.gl = "";
        this.re = "";
    }
    
    public ba(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final boolean se, final boolean yk, final boolean zk, final String ri, final String dl, final String el, final boolean fl, final String gl) {
        super(s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11);
        this.ri = "";
        this.dl = "";
        this.el = "";
        this.gl = "";
        this.re = "";
        this.se = se;
        this.yk = yk;
        this.zk = zk;
        this.ri = ri;
        this.dl = dl;
        this.el = el;
        this.fl = fl;
        this.gl = gl;
    }
    
    public void m(final int n, final String[] array, final String re) {
        super.m(n, array);
        this.re = re;
    }
    
    public boolean vb() {
        return this.se;
    }
    
    public boolean wg() {
        return this.yk;
    }
    
    public boolean rg() {
        return this.zk;
    }
    
    public String sg() {
        return this.ri;
    }
    
    public String vg() {
        return this.dl;
    }
    
    public String xg() {
        return this.el;
    }
    
    public String rb() {
        return this.re;
    }
    
    public boolean ug() {
        return this.fl;
    }
    
    public String tg() {
        return this.gl;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        switch (this.o()) {
            case 1: {
                dataOutputStream.writeBoolean(this.se);
                dataOutputStream.writeBoolean(this.yk);
                dataOutputStream.writeBoolean(this.zk);
                dataOutputStream.writeUTF(this.ri);
                dataOutputStream.writeUTF(this.dl);
                dataOutputStream.writeUTF(this.el);
                dataOutputStream.writeBoolean(this.fl);
                dataOutputStream.writeUTF(this.gl);
            }
            case 4: {
                dataOutputStream.writeUTF(this.re);
            }
            default: {}
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        switch (this.o()) {
            case 1: {
                this.se = dataInputStream.readBoolean();
                this.yk = dataInputStream.readBoolean();
                this.zk = dataInputStream.readBoolean();
                this.ri = dataInputStream.readUTF();
                this.dl = dataInputStream.readUTF();
                this.el = dataInputStream.readUTF();
                this.fl = dataInputStream.readBoolean();
                this.gl = dataInputStream.readUTF();
            }
            case 4: {
                this.re = dataInputStream.readUTF();
            }
            default: {}
        }
    }
}
