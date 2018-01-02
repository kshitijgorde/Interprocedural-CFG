// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vap extends vm
{
    public String a;
    
    public vap() {
        this.a = "";
    }
    
    public vap(final Throwable t) {
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
