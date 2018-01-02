// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class va1 extends vp implements vt
{
    private String a;
    private String b;
    private String c;
    private String d;
    
    public va1() {
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
    }
    
    public int c() {
        return 203;
    }
    
    public String e() {
        return this.a;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.a);
        dataOutputStream.writeUTF(this.b);
        dataOutputStream.writeUTF(this.c);
        dataOutputStream.writeUTF(this.d);
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.a = dataInputStream.readUTF();
        this.b = dataInputStream.readUTF();
        this.c = dataInputStream.readUTF();
        this.d = dataInputStream.readUTF();
    }
}
