import java.util.Enumeration;
import java.util.Vector;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class f
{
    public static final boolean a;
    public static final boolean b;
    public static final boolean c;
    public static final boolean d;
    public static final boolean e;
    public static final boolean f;
    public static final boolean g;
    public static Frame h;
    public static final int i = 1;
    public static final int j;
    public static final int k;
    public static final int l = 3;
    public static final int m = 3;
    public static final int n = 200;
    public static final int o = 5;
    public static final int p = 5000;
    public static final int q = 512;
    public static final int r = 128;
    public static final int s = 10;
    public static final int t = 5;
    public static final int u = 1;
    public static final int v;
    public static final int w = 1;
    public static final int x;
    public static final int y = 1;
    public static final int z = 2;
    public static final int A = 3;
    public static final int B = 4;
    public static final int C = 5;
    public static final int D = 6;
    public static final int E = 7;
    public static final int F = 8;
    public static final int G = 9;
    public static final int H = 10;
    public static final int I = 11;
    public static final int J = 12;
    public static final int K = 13;
    public static final int L = 14;
    public static final int M = 15;
    public static final int N = 16;
    public static final int O = 17;
    public static final int P = 18;
    public static final int Q = 19;
    public static final int R = 20;
    public static final int S = 21;
    public static final int T = 22;
    public static final int U = 23;
    public static final int V = 24;
    public static final int W = 25;
    public static final int X = 25;
    public static final int Y = -1;
    public static final int Z = -2;
    public static final int ba = -3;
    public static final int bb;
    public static final int bc = 1;
    public static final int bd = 2;
    public static final int be = 3;
    public static final int bf = 1;
    public static final int bg = 2;
    public static final int bh = 4;
    public static final int bi = 8;
    public static final int bj = -1;
    public static final int bk;
    public static final int bl = 25000;
    public static final int bm = 50000;
    public static final int bn = 75000;
    public static final int bo = 100000;
    public static final int bp = 1;
    public static final int bq = 2;
    public static final int br = 4;
    public static final int bs = 3;
    public static final int bt;
    public static final int bu = 1;
    public static final int bv = 2;
    public static final int bw = 3;
    public static final int bx = 4;
    public static final int by = 5;
    public static final int bz = 6;
    public static final int bA = 7;
    public static final int bB = 256;
    public static final int bC = 128;
    public static final int bD = 1;
    public static final int bE = 2;
    public static final int bF = 4;
    public static final int bG = 8;
    public static final int bH = 16;
    public static final int bI = 32;
    public static final int bJ = 64;
    public static final int bK = 128;
    public static final int bL = 256;
    public static final int bM = 512;
    public static final int bN = 1024;
    public static final int bO;
    public static final int bP = 1;
    public static final int bQ = 2;
    public static final int bR = 1;
    public static final int bS;
    public static final int bT = 1;
    public static final int bU = 1;
    public static final int bV = 96;
    public static final int bW = 50;
    public static final int bX = 1006;
    public static final int bY = 1007;
    public static final int bZ = 1004;
    public static final int ca = 1005;
    public static final int cb;
    public static final int cc = 128;
    public static final int cd = 1;
    public static final int ce = 2;
    public static final int cf = 4;
    public static final int cg = 8;
    public static final int ch;
    public static final int ci = 1;
    public static final int cj = 2;
    public static final int ck = -1;
    public static final int cl;
    public static final int cm = 1;
    public static final int cn = 2;
    public static final int co;
    public static final int cp = 1;
    public static final int cq = 2;
    public static final int cr = 3;
    public static final int cs = 4;
    public static final int ct;
    public static final int cu = 1;
    public static final int cv = 1024;
    public static final int cw = 1048576;
    public static final String cx = "smiliegames";
    public static final String cy = "file:/";
    public static final String cz = "__radio__";
    public static final String cA = "__cursor__";
    public static final String[] cB;
    private static final Vector cC;
    
    public static void a(final Object o, final String s) {
        f.cC.addElement(o);
        System.out.println(b("x6") + o.hashCode() + b("s,8") + o.getClass().getName() + ((s != null) ? (b("s,8") + s) : ""));
    }
    
    public static void a(final Object o) {
        a(o, null);
    }
    
    public static void a(final Object o, final boolean b) {
        f.cC.removeElement(o);
        if (b) {
            System.out.println(b("~;5I\u0011") + f.cC.size() + b("sst\u0001\\6xl\u0017\u0011!su\u0005X=\u007fv\u0003\u0011~;5I\u001c~;5I\u001c~;5I\u001c~;5I"));
            final Enumeration<Object> elements = (Enumeration<Object>)f.cC.elements();
            while (true) {
                Label_0114: {
                    if (!p.dJ) {
                        break Label_0114;
                    }
                    final Object nextElement = elements.nextElement();
                    System.out.println(nextElement.hashCode() + b("s,8") + nextElement.getClass().getName());
                }
                if (elements.hasMoreElements()) {
                    continue;
                }
                break;
            }
        }
    }
    
    public static void b(final Object o) {
        a(o, false);
    }
    
    public static void a() {
        f.cC.removeAllElements();
    }
    
    public static String a(final String s) {
        return System.getProperty(s);
    }
    
    static {
        f.h = null;
        cB = new String[] { b("\u001fYY x\u001dQ"), b("\u0003D]4p\u0001_V#"), b("\u0000YM*usS^\"t\u0010BK"), b("\u001eCK-r"), b("\u001cX"), b("\u001cP^"), b("\u0010YV#c\u0012BM(p\u0007_W*br"), b("\nymDY2`}DPsx}\u0013\u0011;\u007f\u007f\f\u0011 uw\u0016T"), b("\u0003z}\u0005B66}\nE6d8\u001d^&d8\nP>s"), b("\u0001WV/"), b("\u0000UW6t"), b("\u001dWU!"), b("\u0003ZY=t\u0001"), b("\u0010zq\u0007ZsbwDG:eq\u0010\u0011i?K\tX?\u007f}D\u0010"), b("\u0000WN-\u007f\u00146K'~\u0001S"), b("\u0010^]'z\u001aX_Db\u0010YJ!"), b("\u0003D]7bsEH%r\u0016"), b("\u0007Y84}\u0012O"), b("\u001b__,\u0011\u0000UW6t\u0000"), b("\u0007Y\\%htE8&t\u0000B"), b("\u001b_K'~\u0001S"), b("\u001f_N!b"), b("\u0003WM7t\u0017"), b("\u0014WU!\u0011\u001c@]6"), b("\u001axq\u0010X2zq\u0017X=q8#P>s"), b("\u001fyy\u0000X=q8,X4~87R<d}\u0017") };
        cC = new Vector();
    }
    
    private static String b(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'S';
                    break;
                }
                case 1: {
                    c2 = '\u0016';
                    break;
                }
                case 2: {
                    c2 = '\u0018';
                    break;
                }
                case 3: {
                    c2 = 'd';
                    break;
                }
                default: {
                    c2 = '1';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
