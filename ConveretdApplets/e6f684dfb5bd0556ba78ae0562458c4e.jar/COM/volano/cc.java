// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class cc extends bz
{
    private String ke;
    
    public cc() {
        this.ke = "";
    }
    
    public cc(final Throwable t) {
        this.ke = t.toString();
    }
    
    public String lc() {
        return this.ke;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeUTF(this.ke);
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        this.ke = dataInputStream.readUTF();
    }
}
