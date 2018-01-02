// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vad extends vp implements vt
{
    private static long a;
    private static long b;
    private String c;
    public int d;
    public int e;
    public String f;
    public String[][] g;
    
    public static void a(final long b) {
        vad.b = b;
    }
    
    public long a() {
        return vad.a;
    }
    
    public long b() {
        return vad.b;
    }
    
    public vad() {
        this.c = "";
        this.f = "";
        this.g = new String[0][];
    }
    
    public vad(final String c) {
        this.c = "";
        this.f = "";
        this.g = new String[0][];
        this.c = c;
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
        if (super.e == 4) {
            dataOutputStream.writeInt(this.d);
            dataOutputStream.writeInt(this.e);
            dataOutputStream.writeUTF(this.f);
            dataOutputStream.writeInt(this.g.length);
            for (int i = 0; i < this.g.length; ++i) {
                dataOutputStream.writeUTF(this.g[i][0]);
                dataOutputStream.writeUTF(this.g[i][1]);
                dataOutputStream.writeUTF(this.g[i][2]);
                dataOutputStream.writeUTF(this.g[i][3]);
                dataOutputStream.writeUTF(this.g[i][4]);
            }
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.c = dataInputStream.readUTF();
        if (super.e == 4) {
            this.d = dataInputStream.readInt();
            this.e = dataInputStream.readInt();
            this.f = dataInputStream.readUTF();
            final int int1 = dataInputStream.readInt();
            this.g = new String[int1][5];
            for (int i = 0; i < int1; ++i) {
                this.g[i][0] = dataInputStream.readUTF();
                this.g[i][1] = dataInputStream.readUTF();
                this.g[i][2] = dataInputStream.readUTF();
                this.g[i][3] = dataInputStream.readUTF();
                this.g[i][4] = dataInputStream.readUTF();
            }
        }
    }
}
