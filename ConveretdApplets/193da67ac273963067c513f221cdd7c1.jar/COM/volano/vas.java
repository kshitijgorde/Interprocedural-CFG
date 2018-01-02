// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vas extends vp
{
    public String a;
    
    public vas() {
        this.a = "";
    }
    
    public vas(final Throwable t) {
        this.a = t.toString();
    }
    
    public int c() {
        return 102;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        super.a(dataOutputStream);
        dataOutputStream.writeUTF(this.a);
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        super.a(dataInputStream);
        this.a = dataInputStream.readUTF();
    }
}
