// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vt extends vm implements vq, vs
{
    private static long a;
    private static long b;
    private int c;
    private String d;
    public String e;
    public String f;
    private String g;
    private String h;
    private String i;
    private String j;
    
    public static void a(final long b) {
        vt.b = b;
    }
    
    public long a() {
        return vt.a;
    }
    
    public long b() {
        return vt.b;
    }
    
    public vt() {
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
    }
    
    public vt(final String d, final String e, final String f) {
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public int c() {
        return 201;
    }
    
    public int f() {
        return this.c;
    }
    
    public String e() {
        return this.d;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.d);
        dataOutputStream.writeUTF(this.e);
        dataOutputStream.writeUTF(this.f);
        switch (super.e) {
            case 2: {
                dataOutputStream.writeInt(this.c);
                dataOutputStream.writeUTF(this.g);
                dataOutputStream.writeUTF(this.h);
            }
            case 4: {
                dataOutputStream.writeInt(this.c);
                dataOutputStream.writeUTF(this.i);
                dataOutputStream.writeUTF(this.j);
            }
            default: {}
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.d = dataInputStream.readUTF();
        this.e = dataInputStream.readUTF();
        this.f = dataInputStream.readUTF();
        switch (super.e) {
            case 2: {
                this.c = dataInputStream.readInt();
                this.g = dataInputStream.readUTF();
                this.h = dataInputStream.readUTF();
            }
            case 4: {
                this.c = dataInputStream.readInt();
                this.i = dataInputStream.readUTF();
                this.j = dataInputStream.readUTF();
            }
            default: {}
        }
    }
}
