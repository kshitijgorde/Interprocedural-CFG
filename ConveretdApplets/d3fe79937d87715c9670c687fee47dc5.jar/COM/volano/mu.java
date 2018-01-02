// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class mu extends mm implements mq
{
    private static long a;
    private static long b;
    private String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public int i;
    public int j;
    public int k;
    public String[][] l;
    
    public static void a(final long b) {
        mu.b = b;
    }
    
    public long a() {
        return mu.a;
    }
    
    public long b() {
        return mu.b;
    }
    
    public mu() {
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.l = new String[0][];
    }
    
    public mu(final String c, final String d, final String e) {
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.l = new String[0][];
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public int c() {
        return 208;
    }
    
    public String e() {
        return this.c;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.e);
        dataOutputStream.writeUTF(this.c);
        dataOutputStream.writeUTF(this.d);
        switch (super.e) {
            case 2: {
                dataOutputStream.writeUTF(this.f);
                dataOutputStream.writeUTF(this.g);
                dataOutputStream.writeUTF(this.h);
            }
            case 4: {
                dataOutputStream.writeInt(this.i);
                dataOutputStream.writeInt(this.j);
                dataOutputStream.writeInt(this.k);
                dataOutputStream.writeInt(this.l.length);
                for (int i = 0; i < this.l.length; ++i) {
                    dataOutputStream.writeUTF(this.l[i][0]);
                    dataOutputStream.writeUTF(this.l[i][1]);
                    dataOutputStream.writeUTF(this.l[i][2]);
                    dataOutputStream.writeUTF(this.l[i][3]);
                    dataOutputStream.writeUTF(this.l[i][4]);
                }
            }
            default: {}
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.e = dataInputStream.readUTF().trim();
        this.c = dataInputStream.readUTF();
        this.d = dataInputStream.readUTF().trim();
        switch (super.e) {
            case 2: {
                this.f = dataInputStream.readUTF();
                this.g = dataInputStream.readUTF();
                this.h = dataInputStream.readUTF();
            }
            case 4: {
                this.i = dataInputStream.readInt();
                this.j = dataInputStream.readInt();
                this.k = dataInputStream.readInt();
                final int int1 = dataInputStream.readInt();
                this.l = new String[int1][5];
                for (int i = 0; i < int1; ++i) {
                    this.l[i][0] = dataInputStream.readUTF();
                    this.l[i][1] = dataInputStream.readUTF();
                    this.l[i][2] = dataInputStream.readUTF();
                    this.l[i][3] = dataInputStream.readUTF();
                    this.l[i][4] = dataInputStream.readUTF();
                }
            }
            default: {}
        }
    }
}
