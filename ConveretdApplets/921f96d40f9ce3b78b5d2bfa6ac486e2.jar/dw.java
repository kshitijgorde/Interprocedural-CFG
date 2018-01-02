import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class dw
{
    public static final Font p;
    public static final Font d;
    public static final Font a;
    public static final Font n;
    public static final Font v;
    public static final Font i;
    public static final Font l;
    public static final Font b;
    public static final Font c;
    public static final Font e;
    public static final Color[][] p;
    public static final Color[] p;
    
    public static final void p(final int n, final int n2) {
        final Color[] array = dw.p[n];
        dw.p[n] = dw.p[n2];
        dw.p[n2] = array;
    }
    
    static {
        p = new Font("Serif", 0, 12);
        d = new Font("Serif", 1, 12);
        a = new Font("Serif", 0, 14);
        n = new Font("Serif", 0, 11);
        v = new Font("SanSerif", 0, 12);
        i = new Font("SanSerif", 1, 12);
        l = new Font("SanSerif", 0, 14);
        b = new Font("SanSerif", 1, 14);
        c = new Font("SanSerif", 0, 11);
        e = new Font("SanSerif", 1, 11);
        p = new Color[][] { { new Color(156, 154, 206), new Color(0, 0, 128), new Color(196, 196, 196), new Color(0, 0, 128), new Color(99, 101, 156), Color.white, new Color(99, 101, 156), Color.white, Color.white, new Color(99, 101, 156), Color.white, new Color(0, 0, 128), Color.blue, Color.cyan, Color.yellow, Color.yellow }, { new Color(156, 154, 206), new Color(0, 0, 128), new Color(196, 196, 196), new Color(0, 0, 128), new Color(99, 101, 156), Color.white, new Color(99, 101, 156), Color.white, Color.white, new Color(99, 101, 156), Color.white, new Color(0, 0, 128), Color.blue, Color.cyan, Color.yellow, Color.yellow }, { new Color(0, 117, 51), Color.white, new Color(102, 167, 98), Color.black, new Color(72, 125, 32), new Color(215, 242, 170), new Color(0, 83, 23), new Color(215, 242, 170), new Color(215, 242, 170), new Color(102, 167, 98), new Color(215, 242, 170), Color.white, new Color(204, 204, 51), new Color(51, 204, 153), new Color(102, 153, 255), Color.yellow }, { new Color(125, 101, 32), Color.black, new Color(208, 169, 96), Color.black, new Color(102, 102, 102), Color.white, new Color(102, 102, 102), Color.white, Color.white, new Color(204, 204, 204), Color.white, Color.white, Color.green, Color.cyan, Color.lightGray, Color.yellow }, { new Color(54, 85, 107), Color.white, Color.white, Color.black, new Color(66, 97, 111), Color.white, new Color(66, 97, 111), Color.white, Color.white, new Color(183, 219, 241), Color.white, Color.white, Color.green, Color.cyan, Color.lightGray, Color.yellow } };
        p = new Color[] { new Color(0, 114, 62), new Color(51, 51, 51), new Color(102, 0, 0), new Color(153, 0, 0), new Color(255, 51, 0), new Color(143, 15, 118), new Color(204, 0, 153), new Color(204, 0, 153), new Color(0, 102, 102), new Color(215, 21, 52), new Color(118, 32, 34), new Color(1, 77, 162), new Color(2, 56, 220), new Color(134, 0, 94) };
    }
}
