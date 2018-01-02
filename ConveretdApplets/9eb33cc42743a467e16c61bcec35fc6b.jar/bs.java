import java.io.IOException;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class bs extends ar
{
    public final void fz(final b5 b5) {
        b5.lh("ftp");
        if (b5.lh) {
            int lg;
            while ((lg = (new Random().nextInt() & 0xFFF0)) < 8192) {}
            b5.lg = lg;
            for (int i = 0; i < 10; ++i) {
                b5.gn(b5.lg + i, "#FTP" + i, b5.lg + i, "ftp");
            }
        }
    }
    
    public final bm fy(final String s, final int n, final String s2, final int n2, final b7 b7) throws IOException {
        return new bt(s, n, s2, n2, b7);
    }
}
