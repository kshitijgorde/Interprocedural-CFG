// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vp extends vm implements vq
{
    private static long a;
    private static long b;
    private String c;
    public String d;
    private String e;
    
    public static void a(final long b) {
        vp.b = b;
    }
    
    public long a() {
        return vp.a;
    }
    
    public long b() {
        return vp.b;
    }
    
    public vp() {
        this.c = "";
        this.d = "";
        this.e = "";
    }
    
    public vp(final String c, final String d, final String e) {
        this.c = "";
        this.d = "";
        this.e = "";
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public int c() {
        return 216;
    }
    
    public String e() {
        return this.c;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.c);
        dataOutputStream.writeUTF(this.d);
        dataOutputStream.writeUTF(this.e);
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.c = dataInputStream.readUTF();
        this.d = dataInputStream.readUTF();
        this.e = dataInputStream.readUTF();
    }
}
