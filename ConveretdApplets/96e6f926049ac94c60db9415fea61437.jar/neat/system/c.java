// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.io.OutputStream;

class c extends OutputStream
{
    StringBuffer a;
    private final f b;
    
    public void write(int n) {
        if (n == 9) {
            n = 32;
        }
        if (n == 13) {
            n = 32;
        }
        this.a.append((char)n);
    }
    
    c(final f b) {
        this.b = b;
        this.a = new StringBuffer();
    }
}
