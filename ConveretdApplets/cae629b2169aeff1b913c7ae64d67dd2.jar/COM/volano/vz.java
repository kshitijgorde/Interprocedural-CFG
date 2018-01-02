// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vz extends vp implements vt
{
    private static long a;
    private static long b;
    private String c;
    public String d;
    
    public static void a(final long b) {
        vz.b = b;
    }
    
    public long a() {
        return vz.a;
    }
    
    public long b() {
        return vz.b;
    }
    
    public vz() {
    }
    
    public vz(final String c, final String d) {
        this.c = c;
        this.d = d;
    }
    
    public int c() {
        return 205;
    }
    
    public String e() {
        return this.c;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.c);
        dataOutputStream.writeUTF(this.d);
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.c = dataInputStream.readUTF();
        this.d = dataInputStream.readUTF();
    }
}
