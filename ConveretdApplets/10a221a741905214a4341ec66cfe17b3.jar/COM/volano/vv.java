// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vv extends vm implements vs
{
    private static long a;
    private static long b;
    private int c;
    private String d;
    
    public static void a(final long b) {
        vv.b = b;
    }
    
    public long a() {
        return vv.a;
    }
    
    public long b() {
        return vv.b;
    }
    
    public vv() {
    }
    
    public vv(final int c, final String d) {
        this.c = c;
        this.d = d;
    }
    
    public int c() {
        return 203;
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
