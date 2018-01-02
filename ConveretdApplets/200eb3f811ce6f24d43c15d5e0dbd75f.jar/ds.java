import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ds implements ak
{
    public final int a(final aj aj, final int n, final Vector vector, final bf bf, final bb bb, final dr dr, final j j) throws al, dp {
        if (n >= 0) {
            for (int i = 0; i < n; ++i) {
                try {
                    if (dr.a(new do(aj.a()), aj, vector, bf, bb, j) == 1) {
                        if (ak.a.k()) {
                            ak.a.i("leaving response read at " + i);
                        }
                        return i;
                    }
                }
                catch (Exception ex) {
                    if (ak.a.g()) {
                        ak.a.d("read error: " + ex + " leaving at " + i);
                    }
                    return i;
                }
            }
            return n;
        }
        final ch ch = (ch)bf;
        if (ak.a.k()) {
            ak.a.i("InputStreamMsgReader: try to read header");
        }
        final do do1 = new do(aj.a());
        if (ak.a.k()) {
            ak.a.i("InputStreamMsgReader: try to read body");
        }
        final int a = dr.a(do1, aj, vector, ch, bb, j);
        if (ak.a.k()) {
            ak.a.i("InputStreamMsgReader: nextTimeout=" + ch.b());
        }
        if (a == 1) {
            return 0;
        }
        final int b = dr.b();
        final int a2 = bb.b.a(new dt(this, dr, ch, bb, aj, vector, j), "OuterPushProducer");
        if (a2 != 0) {
            dj.a(ch, bb.b, new cu(a2), b);
        }
        return -1;
    }
}
