// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vx extends vm implements vq
{
    private static long a;
    private static long b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    
    public static void a(final long b) {
        vx.b = b;
    }
    
    public long a() {
        return vx.a;
    }
    
    public long b() {
        return vx.b;
    }
    
    public vx() {
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = 1;
    }
    
    public vx(final String c, final String d, final String e, final String f, final int g) {
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = 1;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public int c() {
        return 211;
    }
    
    public String e() {
        return this.c;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.c);
        dataOutputStream.writeUTF(this.d);
        dataOutputStream.writeUTF(this.e);
        dataOutputStream.writeUTF(this.f);
        dataOutputStream.writeInt(this.g);
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.c = dataInputStream.readUTF();
        this.d = dataInputStream.readUTF();
        this.e = dataInputStream.readUTF();
        this.f = dataInputStream.readUTF();
        this.g = dataInputStream.readInt();
    }
}
