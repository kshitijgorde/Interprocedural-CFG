// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vo extends vm
{
    private static long a;
    private static long b;
    private byte[] c;
    public int d;
    public String[] e;
    
    public static void a(final long b) {
        vo.b = b;
    }
    
    public long a() {
        return vo.a;
    }
    
    public long b() {
        return vo.b;
    }
    
    public vo() {
        this.c = new byte[0];
        this.e = new String[0];
    }
    
    public vo(final byte[] c) {
        this.c = new byte[0];
        this.e = new String[0];
        this.c = c;
    }
    
    public int c() {
        return 209;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        switch (super.e) {
            case 1: {
                dataOutputStream.writeByte(this.c.length);
                if (this.c.length > 0) {
                    dataOutputStream.write(this.c);
                    return;
                }
                break;
            }
            case 4: {
                dataOutputStream.writeInt(this.d);
                dataOutputStream.writeInt(this.e.length);
                for (int i = 0; i < this.e.length; ++i) {
                    dataOutputStream.writeUTF(this.e[i]);
                }
            }
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        switch (super.e) {
            case 1: {
                final int unsignedByte = dataInputStream.readUnsignedByte();
                this.c = new byte[unsignedByte];
                if (unsignedByte > 0) {
                    dataInputStream.read(this.c);
                    return;
                }
                break;
            }
            case 4: {
                this.d = dataInputStream.readInt();
                final int int1 = dataInputStream.readInt();
                this.e = new String[int1];
                for (int i = 0; i < int1; ++i) {
                    this.e[i] = dataInputStream.readUTF();
                }
            }
        }
    }
}
