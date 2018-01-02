// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vz extends vm
{
    private static long a;
    private static long b;
    private String c;
    public String[] d;
    
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
        this.c = "";
        this.d = new String[0];
    }
    
    public vz(final String c) {
        this.c = "";
        this.d = new String[0];
        this.c = c;
    }
    
    public int c() {
        return 205;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.c);
        dataOutputStream.writeInt(this.d.length);
        for (int i = 0; i < this.d.length; ++i) {
            dataOutputStream.writeUTF(this.d[i]);
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.c = dataInputStream.readUTF();
        final int int1 = dataInputStream.readInt();
        this.d = new String[int1];
        for (int i = 0; i < int1; ++i) {
            this.d[i] = dataInputStream.readUTF();
        }
    }
}
