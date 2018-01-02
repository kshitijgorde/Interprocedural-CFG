// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class mw extends mm implements mq
{
    private static long a;
    private static long b;
    private String c;
    public String d;
    
    public static void a(final long b) {
        mw.b = b;
    }
    
    public long a() {
        return mw.a;
    }
    
    public long b() {
        return mw.b;
    }
    
    public mw() {
    }
    
    public mw(final String c, final String d) {
        this.c = c;
        this.d = d;
    }
    
    public int c() {
        return 214;
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
