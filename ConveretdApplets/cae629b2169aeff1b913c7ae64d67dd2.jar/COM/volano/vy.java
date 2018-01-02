// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vy extends vp implements vv
{
    private static long a;
    private static long b;
    private int c;
    private String d;
    
    public static void a(final long b) {
        vy.b = b;
    }
    
    public long a() {
        return vy.a;
    }
    
    public long b() {
        return vy.b;
    }
    
    public vy() {
    }
    
    public vy(final int c, final String d) {
        this.c = c;
        this.d = d;
    }
    
    public int c() {
        return 216;
    }
    
    public int f() {
        return this.c;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeInt(this.c);
        dataOutputStream.writeUTF(this.d);
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.c = dataInputStream.readInt();
        this.d = dataInputStream.readUTF();
    }
}
