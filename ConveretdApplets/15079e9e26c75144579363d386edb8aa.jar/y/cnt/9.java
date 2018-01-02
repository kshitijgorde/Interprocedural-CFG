// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;

public final class 9
{
    public DataOutputStream a;
    
    public 9(final OutputStream outputStream) {
        this.a = new DataOutputStream(outputStream);
    }
    
    public void x(final int n) {
        try {
            this.a.write(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public void y(final String s) {
        try {
            this.a.writeUTF(s);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
}
