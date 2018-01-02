import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    private InputStream p;
    private byte[] p;
    private char[] p;
    private boolean p;
    private int p;
    private int d;
    
    public c(final InputStream p) {
        this.p = new byte[4096];
        this.p = new char[8192];
        this.p = false;
        this.p = p;
    }
    
    public final String p() throws IOException {
        while (true) {
            for (int i = this.d; i < this.p; ++i) {
                if (this.p[i] == '\r' || this.p[i] == '\n') {
                    final String s = new String(this.p, 0, i);
                    if (i + 1 < this.p && (this.p[i + 1] == '\r' || this.p[i + 1] == '\n')) {
                        ++i;
                    }
                    for (int j = 0; j < this.p - i - 1; ++j) {
                        this.p[j] = this.p[j + i + 1];
                    }
                    this.d = 0;
                    this.p = this.p - i - 1;
                    return s;
                }
            }
            final int read = this.p.read(this.p, 0, this.p.length);
            if (read == 0) {
                return null;
            }
            if (read == -1) {
                this.p = true;
                return null;
            }
            final String s2 = new String(this.p, 0, read);
            final int min = Math.min(this.p.length - this.p, s2.length());
            s2.getChars(0, min, this.p, this.p);
            this.p += min;
        }
    }
}
