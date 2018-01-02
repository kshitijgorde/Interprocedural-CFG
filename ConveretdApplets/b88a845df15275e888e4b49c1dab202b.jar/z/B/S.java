// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.RenderedImage;
import java.io.IOException;

public class S extends b
{
    public static final int V = 256;
    public static final int a = 257;
    public static final int L = 258;
    public static final int S = 259;
    public static final int G = 262;
    public static final int X = 266;
    public static final int _ = 273;
    public static final int N = 277;
    public static final int T = 278;
    public static final int W = 279;
    public static final int J = 282;
    public static final int O = 283;
    public static final int P = 284;
    public static final int E = 292;
    public static final int D = 293;
    public static final int Z = 296;
    public static final int K = 317;
    public static final int H = 320;
    public static final int Y = 322;
    public static final int C = 323;
    public static final int M = 324;
    public static final int Q = 325;
    public static final int F = 338;
    public static final int R = 339;
    public static final int U = 340;
    public static final int I = 341;
    
    public S(final X x, final A a) {
        super(x, a);
    }
    
    public int B() throws IOException {
        return z.B.F.D(this.A);
    }
    
    public RenderedImage B(final int n) throws IOException {
        if (n < 0 || n >= this.B()) {
            throw new IOException(m.A("TIFFImageDecoder0"));
        }
        return new h(this.A, (I)this.B, n);
    }
}
