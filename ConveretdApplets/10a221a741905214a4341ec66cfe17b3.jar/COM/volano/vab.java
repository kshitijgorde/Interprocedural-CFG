// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vab extends vl
{
    private boolean a;
    private boolean b;
    private boolean c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private String h;
    public String i;
    
    public vab() {
        this.d = "";
        this.e = "";
        this.f = "";
        this.h = "";
        this.i = "";
    }
    
    public vab(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final boolean a, final boolean b, final boolean c, final String d, final String e, final String f, final boolean g, final String h) {
        super(s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11);
        this.d = "";
        this.e = "";
        this.f = "";
        this.h = "";
        this.i = "";
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public int c() {
        return 202;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        switch (super.e) {
            case 1: {
                dataOutputStream.writeBoolean(this.a);
                dataOutputStream.writeBoolean(this.b);
                dataOutputStream.writeBoolean(this.c);
                dataOutputStream.writeUTF(this.d);
                dataOutputStream.writeUTF(this.e);
                dataOutputStream.writeUTF(this.f);
                dataOutputStream.writeBoolean(this.g);
                dataOutputStream.writeUTF(this.h);
            }
            case 4: {
                dataOutputStream.writeUTF(this.i);
            }
            default: {}
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        switch (super.e) {
            case 1: {
                this.a = dataInputStream.readBoolean();
                this.b = dataInputStream.readBoolean();
                this.c = dataInputStream.readBoolean();
                this.d = dataInputStream.readUTF();
                this.e = dataInputStream.readUTF();
                this.f = dataInputStream.readUTF();
                this.g = dataInputStream.readBoolean();
                this.h = dataInputStream.readUTF();
            }
            case 4: {
                this.i = dataInputStream.readUTF();
            }
            default: {}
        }
    }
}
