// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vr extends vm implements vq, vs
{
    private static long a;
    private static long b;
    public boolean c;
    private int d;
    private String e;
    public String f;
    public String g;
    
    public static void a(final long b) {
        vr.b = b;
    }
    
    public long a() {
        return vr.a;
    }
    
    public long b() {
        return vr.b;
    }
    
    public vr() {
        this.e = "";
        this.f = "";
        this.g = "";
    }
    
    public vr(final String e, final String f, final String g) {
        this.e = "";
        this.f = "";
        this.g = "";
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public vr(final int d, final String f, final String g) {
        this.e = "";
        this.f = "";
        this.g = "";
        this.d = d;
        this.f = f;
        this.g = g;
    }
    
    public vr(final String g) {
        this.e = "";
        this.f = "";
        this.g = "";
        this.g = g;
    }
    
    public int c() {
        return 208;
    }
    
    public int f() {
        return this.d;
    }
    
    public String e() {
        return this.e;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeBoolean(this.c);
        dataOutputStream.writeInt(this.d);
        dataOutputStream.writeUTF(this.e);
        dataOutputStream.writeUTF(this.f);
        dataOutputStream.writeUTF(this.g);
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.c = dataInputStream.readBoolean();
        this.d = dataInputStream.readInt();
        this.e = dataInputStream.readUTF();
        this.f = dataInputStream.readUTF().trim();
        this.g = dataInputStream.readUTF().trim();
    }
}
