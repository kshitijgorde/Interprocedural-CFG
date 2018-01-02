// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class ml extends mm
{
    private static long a;
    private static long b;
    private boolean c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    public int o;
    private String[] p;
    public byte[] q;
    
    public static void a(final long b) {
        ml.b = b;
    }
    
    public long a() {
        return ml.a;
    }
    
    public long b() {
        return ml.b;
    }
    
    public ml() {
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = "";
        this.p = new String[0];
        this.q = new byte[0];
        this.c = false;
    }
    
    public ml(final boolean c) {
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = "";
        this.p = new String[0];
        this.q = new byte[0];
        this.c = c;
    }
    
    public ml(final String d, final String e, final String f, final String g, final String h, final String i, final String j, final String k, final String l, final String m, final String n) {
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = "";
        this.p = new String[0];
        this.q = new byte[0];
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
    }
    
    public int c() {
        if (this.c) {
            return 17;
        }
        return 1;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        if (this.c) {
            dataOutputStream.writeBytes("COM.volano.Access");
        }
        super.a(dataOutputStream);
        switch (super.e) {
            case 1: {
                dataOutputStream.writeUTF(this.d);
                dataOutputStream.writeUTF(this.e);
                dataOutputStream.writeUTF(this.f);
                dataOutputStream.writeUTF(this.g);
                dataOutputStream.writeUTF(this.h);
                dataOutputStream.writeUTF(this.i);
                dataOutputStream.writeUTF(this.j);
                dataOutputStream.writeUTF(this.k);
                dataOutputStream.writeUTF(this.l);
                dataOutputStream.writeUTF(this.m);
                dataOutputStream.writeUTF(this.n);
            }
            case 4: {
                dataOutputStream.writeInt(this.o);
                dataOutputStream.writeInt(this.p.length);
                for (int i = 0; i < this.p.length; ++i) {
                    dataOutputStream.writeUTF(this.p[i]);
                }
                if (this.c) {
                    break;
                }
                dataOutputStream.writeByte(this.q.length);
                if (this.q.length > 0) {
                    dataOutputStream.write(this.q);
                    return;
                }
                break;
            }
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        if (this.c) {
            dataInputStream.readFully(new byte[17]);
        }
        super.a(dataInputStream);
        switch (super.e) {
            case 1: {
                this.d = dataInputStream.readUTF().trim();
                this.e = dataInputStream.readUTF();
                this.f = dataInputStream.readUTF();
                this.g = dataInputStream.readUTF();
                this.h = dataInputStream.readUTF();
                this.i = dataInputStream.readUTF();
                this.j = dataInputStream.readUTF();
                this.k = dataInputStream.readUTF();
                this.l = dataInputStream.readUTF();
                this.m = dataInputStream.readUTF();
                this.n = dataInputStream.readUTF();
            }
            case 4: {
                this.o = dataInputStream.readInt();
                final int int1 = dataInputStream.readInt();
                this.p = new String[int1];
                for (int i = 0; i < int1; ++i) {
                    this.p[i] = dataInputStream.readUTF();
                }
                if (this.c) {
                    break;
                }
                final int unsignedByte = dataInputStream.readUnsignedByte();
                this.q = new byte[unsignedByte];
                if (unsignedByte > 0) {
                    dataInputStream.read(this.q);
                    return;
                }
                break;
            }
        }
    }
}
