// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class be extends bz
{
    private String[] lg;
    
    public be() {
        this.lg = new String[0];
    }
    
    public void m(final String[] lg) {
        this.p(4);
        this.lg = lg;
    }
    
    public String[] kd() {
        return this.lg;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        super.og(dataOutputStream);
        dataOutputStream.writeInt(this.lg.length);
        for (int i = 0; i < this.lg.length; ++i) {
            dataOutputStream.writeUTF(this.lg[i]);
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        super.pg(dataInputStream);
        final int int1 = dataInputStream.readInt();
        this.lg = new String[int1];
        for (int i = 0; i < int1; ++i) {
            this.lg[i] = dataInputStream.readUTF();
        }
    }
}
