// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Font;

public class x
{
    protected w e;
    private qz a;
    private int b;
    private Font c;
    private Font d;
    private final String f = "m\u0011\u001a&I\r=U\u00fa\f\u0007@L";
    private float[] g;
    private int[] h;
    private int[] i;
    private int j;
    private int k;
    private int[] l;
    private int[] m;
    private int[] n;
    private int[] o;
    private int p;
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        final boolean a = qz.a;
        int n6 = n2;
        while (true) {
            Label_0064: {
                if (!a) {
                    break Label_0064;
                }
                final int n7 = n6 * this.e.q[2];
                int n8 = n;
                while (true) {
                    Label_0053: {
                        if (!a) {
                            break Label_0053;
                        }
                        this.e.e[n7 + n8] = n5;
                        ++n8;
                    }
                    if (n8 < n + n3) {
                        continue;
                    }
                    break;
                }
                ++n6;
            }
            if (n6 >= n2 + n4) {
                return;
            }
            continue;
        }
    }
    
    protected x() {
        final boolean a = qz.a;
        this.a = null;
        this.b = 0;
        this.c = new Font(a("z\u0010\u0014?RY"), 0, 12);
        this.d = new Font(a("z\u0010\u0014?RY"), 1, 12);
        this.j = 0;
        this.k = 98;
        this.m = new int[] { 23, 29, 34 };
        this.n = new int[] { 23, 29, 34 };
        this.p = 1;
        if (w.b) {
            qz.a = !a;
        }
    }
    
    protected final void k() {
        final boolean a = qz.a;
        x x = this;
        x x2 = this;
        x x3 = this;
        Label_0053: {
            if (!a) {
                if (this.e.g == null) {
                    x = this;
                    x2 = this;
                    x3 = this;
                    if (a) {
                        break Label_0053;
                    }
                    if (this.e.h != null) {
                        this.e.g = this.e.h.getGraphics();
                    }
                }
                x = this;
                x2 = this;
                x3 = this;
            }
        }
        if (!a) {
            Label_1409: {
                if (x3.k == 98) {
                    this.l = new int[this.e.q[2]];
                    int n = 0;
                    int index = 0;
                    Label_0180: {
                        while (true) {
                            while (true) {
                                Label_0113: {
                                    if (!a) {
                                        break Label_0113;
                                    }
                                    w.b = !w.b;
                                    this.l[n] = -16777216;
                                    ++n;
                                }
                                if (n < this.l.length) {
                                    continue;
                                }
                                break;
                            }
                            try {
                                if (a) {
                                    continue;
                                }
                                index = System.getProperty(a("T\u0018\u00032\u0013H\u001c\u0007 TQ\u0017")).indexOf(a("\u000fWE"));
                                if (a) {
                                    break Label_0180;
                                }
                                if (index == -1) {
                                    this.a = new qz(this.e);
                                }
                            }
                            catch (Exception ex) {}
                            break;
                        }
                        this.o = new int[98];
                    }
                    int n2 = index;
                    char[] charArray;
                    char[] charArray2;
                    char[] charArray3;
                    int length;
                    boolean equalsIgnoreCase;
                    boolean equals;
                    int n3;
                    int intValue;
                    int n4;
                    int intValue2;
                    int n5 = 0;
                    int n6;
                    Vector vector;
                    int length2;
                    int n7;
                    String s2 = null;
                    String s;
                    int n8;
                    int n9;
                    int startsWith;
                    int n10;
                    int n11;
                    String s3;
                    String string;
                    int n12;
                    int n13;
                    char[] charArray4;
                    char[] charArray5;
                    int n14;
                    int n15;
                    int n16;
                    char c = '\0';
                    char c2;
                    int n17 = 0;
                    int n18 = 0;
                    int n19;
                    int n20;
                    char c3 = '\0';
                    char c4;
                    int n21;
                    int n22;
                    char c5 = '\0';
                    char c6;
                    int n24;
                    int n23;
                    char c7;
                    int n26;
                    int n25;
                    char c8;
                    char c9;
                    char c10;
                    int n27;
                    int n28;
                    int n29;
                    int n30;
                    int index2;
                    int n32;
                    int n31;
                    int n33;
                    int length3;
                    int n34 = 0;
                    int n36;
                    int n35;
                    Label_0538_Outer:Label_0558_Outer:
                    while (true) {
                        while (true) {
                            Label_0211: {
                                if (!a) {
                                    break Label_0211;
                                }
                                this.o[n2] = -16777216 + (255 - (int)(n2 * 2.602f) << 16);
                                ++n2;
                            }
                            if (n2 < this.o.length) {
                                continue;
                            }
                            break;
                        }
                        try {
                            if (a) {
                                continue Label_0538_Outer;
                            }
                            charArray = a("m\u0011\u001a&I\r=U\u00fa\f\u0007@L").substring(0, 7).toLowerCase().toCharArray();
                            charArray2 = ("E" + a("m\u0011\u001a&I\r=U\u00fa\f\u0007@L").substring(0, 7).toLowerCase()).toCharArray();
                            charArray3 = this.e.t[1].toCharArray();
                            length = this.e.t[2].length();
                            Label_0507: {
                                if (!a) {
                                    if (length != 0) {
                                        equalsIgnoreCase = this.e.t[2].equalsIgnoreCase(a("R\u0016\u00162QV\u0016\u0006'"));
                                        if (!a) {
                                            if (!equalsIgnoreCase) {
                                                equals = this.e.t[2].equals(a("\u000fKB}\r\u0010I[b"));
                                                if (!a) {
                                                    if (!equals) {
                                                        break Label_0507;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                n3 = length;
                                intValue = new Integer(this.e.t[0].substring(16, 19));
                                n4 = (int)((charArray3[1] + charArray[2] + charArray3[3]) / 255.0f % 1.0f * 1000.0f);
                                Label_0491: {
                                    Label_0489: {
                                        if (!a) {
                                            if (intValue == n4) {
                                                n3 = 0;
                                                if (!a) {
                                                    break Label_0491;
                                                }
                                            }
                                            n5 = (intValue2 = new Integer(this.e.t[0].substring(16, 19)));
                                            if (a) {
                                                break Label_0489;
                                            }
                                            n6 = (int)((charArray3[1] + charArray[3] + charArray3[3]) / 255.0f % 1.0f * 1000.0f);
                                        }
                                        if (intValue != n4) {
                                            break Label_0491;
                                        }
                                        this.j = 1;
                                        n5 = 0;
                                    }
                                    n3 = n5;
                                }
                                this.k += n3;
                                if (!a) {
                                    break Label_1409;
                                }
                            }
                            vector = new Vector<String>();
                            length2 = this.e.t[0].length();
                            n7 = 5;
                            while (true) {
                                while (true) {
                                    Label_0573: {
                                        if (!a) {
                                            break Label_0573;
                                        }
                                        this.e.t[0].substring(n7).substring(0, 10);
                                        s = s2;
                                        n7 += 19;
                                        vector.addElement(s);
                                        ++n7;
                                    }
                                    if (n7 < length2) {
                                        continue Label_0558_Outer;
                                    }
                                    break;
                                }
                                n8 = 0;
                                n9 = 0;
                                s2 = this.e.t[2];
                                if (a) {
                                    continue;
                                }
                                break;
                            }
                            startsWith = (s2.startsWith(a("I\u000e\u0002}")) ? 1 : 0);
                            if (!a && startsWith != 0) {
                                this.e.t[2] = this.e.t[2].substring(4);
                                goto Label_0638;
                            }
                            n10 = startsWith;
                            while (true) {
                                n11 = 0;
                            Label_0649_Outer:
                                while (true) {
                                    if (!a) {
                                        break Label_1111;
                                    }
                                Label_1121:
                                    while (true) {
                                        string = (s3 = this.e.t[2]);
                                        if (!a && s3.length() < 10) {
                                            n12 = 10 - string.length();
                                            n13 = 0;
                                            while (true) {
                                                Label_0728: {
                                                    if (!a) {
                                                        break Label_0728;
                                                    }
                                                    string += charArray[n13 % charArray.length];
                                                    ++n13;
                                                }
                                                if (n13 >= n12) {
                                                    goto Label_0735;
                                                }
                                                continue;
                                            }
                                        }
                                        else {
                                            charArray4 = s3.toCharArray();
                                            charArray5 = vector.elementAt(n11).toCharArray();
                                            n14 = 0;
                                            n15 = 0;
                                            n16 = 0;
                                            while (true) {
                                                while (true) {
                                                    Label_0843: {
                                                        if (n16 < 4) {
                                                            c = (char)('\u0019' - (charArray4[n16] + charArray3[n16 % charArray3.length] + charArray[n16 % charArray.length]) % '\u0019' + 'A');
                                                            if (!a) {
                                                                break Label_0843;
                                                            }
                                                        }
                                                        c2 = (char)('\t' - (charArray4[n16] + charArray3[n16 % charArray3.length] + charArray[n16 % charArray.length]) % '\t');
                                                        c = (char)(n17 + n18);
                                                    }
                                                    n19 = n16;
                                                    n20 = 4;
                                                    Label_0925: {
                                                        if (!a) {
                                                            if (n19 < n20) {
                                                                c3 = (char)('\u0019' - (charArray4[n16] + charArray3[n16 % charArray3.length] - charArray2[n16 % charArray2.length]) % '\u0019' + 'A');
                                                                if (!a) {
                                                                    break Label_0925;
                                                                }
                                                            }
                                                            c4 = (char)('\t' - (charArray4[n16] + charArray3[n16 % charArray3.length] - charArray2[n16 % charArray2.length]) % '\t');
                                                        }
                                                        c3 = (char)(n19 + n20);
                                                    }
                                                    n21 = n16;
                                                    n22 = 4;
                                                    Label_1007: {
                                                        if (!a) {
                                                            if (n21 < n22) {
                                                                c5 = (char)('\u0019' - (charArray4[n16] + charArray3[n16 % charArray3.length] - charArray2[n16 % charArray.length]) % '\u0019' + 'A');
                                                                if (!a) {
                                                                    break Label_1007;
                                                                }
                                                            }
                                                            c6 = (char)('\t' - (charArray4[n16] + charArray3[n16 % charArray3.length] - charArray2[n16 % charArray.length]) % '\t');
                                                        }
                                                        c5 = (char)(n21 + n22);
                                                    }
                                                    c7 = (char)(n23 = (n24 = c));
                                                    c8 = (char)(n25 = (n26 = charArray5[n16]));
                                                    if (!a) {
                                                        if (c7 == c8) {
                                                            ++n14;
                                                        }
                                                        n23 = (c9 = (char)(n24 = c3));
                                                        n25 = (c10 = (char)(n26 = charArray5[n16]));
                                                    }
                                                    Label_1065: {
                                                        Label_1058: {
                                                            Label_1055: {
                                                                if (!a) {
                                                                    if (c7 == c8) {
                                                                        break Label_1055;
                                                                    }
                                                                    n24 = (n23 = c5);
                                                                    n26 = (n25 = charArray5[n16]);
                                                                }
                                                                if (a) {
                                                                    break Label_1065;
                                                                }
                                                                if (n23 != n25) {
                                                                    break Label_1058;
                                                                }
                                                            }
                                                            ++n15;
                                                        }
                                                        n24 = ++n16;
                                                        n26 = 10;
                                                    }
                                                    if (n24 >= n26) {
                                                        n17 = (n27 = n14);
                                                        n18 = (n28 = 10);
                                                        if (!a) {
                                                            break;
                                                        }
                                                        continue;
                                                    }
                                                    break;
                                                }
                                            }
                                            if (!a) {
                                                if (n17 == n18) {
                                                    ++n8;
                                                    if (!a) {
                                                        break Label_1121;
                                                    }
                                                }
                                                n27 = n15;
                                                n28 = 10;
                                            }
                                            if (n27 == n28) {
                                                ++n9;
                                                if (!a) {
                                                    break Label_1121;
                                                }
                                            }
                                            ++n11;
                                        }
                                        if (n29 < vector.size()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    n30 = n10;
                                    Label_1324: {
                                        if (!a) {
                                            if (n30 == 0) {
                                                n11 = (index2 = this.e.t[2].indexOf("/", this.e.t[2].indexOf(".") + 1));
                                                if (!a) {
                                                    if (index2 > 0) {
                                                        this.e.t[2] = this.e.t[2].substring(0, n11);
                                                    }
                                                    this.e.t[2].lastIndexOf(".");
                                                }
                                                n31 = (n32 = index2);
                                                if (!a) {
                                                    if (n32 > 0) {
                                                        this.e.t[2] = this.e.t[2].substring(0, n31);
                                                    }
                                                    this.e.t[2].indexOf(".");
                                                }
                                                n33 = n32;
                                                if (a) {
                                                    break Label_1324;
                                                }
                                                if (n33 > 0) {
                                                    length3 = this.e.t[2].length();
                                                    n34 = 1;
                                                    if (a) {
                                                        break Label_1324;
                                                    }
                                                    if (length3 > n34) {
                                                        this.e.t[2] = this.e.t[2].substring(n33 + 1);
                                                    }
                                                }
                                            }
                                            ++n10;
                                        }
                                    }
                                    if (n30 >= n34) {
                                        n29 = (n35 = (n36 = n8 + n9));
                                        if (a) {
                                            continue Label_0649_Outer;
                                        }
                                        if (!a) {
                                            if (n29 == 0) {
                                                ++this.k;
                                                if (!a) {
                                                    break;
                                                }
                                            }
                                            n36 = (n35 = n8);
                                        }
                                        if (!a) {
                                            if (n35 > 0) {
                                                break;
                                            }
                                            n36 = n9;
                                        }
                                        if (n36 > 0) {
                                            this.j = 1;
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                        catch (Exception ex2) {
                            try {
                                ++this.k;
                                ++this.k;
                            }
                            catch (Exception ex3) {}
                        }
                        break;
                    }
                }
            }
            x = this;
            x2 = this;
        }
        if (!a) {
            if (x2.g == null) {
                this.g = new float[0];
                ++this.k;
            }
            x = this;
        }
        final int n37 = (x.e.q[2] - 198) / 2;
        int n38 = (this.e.q[1] - 36) / 2;
        int k;
        final int n39 = k = this.e.q[3];
        final int n41;
        final int n40 = n41 = 1;
        final int n42;
        final boolean b;
        Label_1914: {
            if (!a) {
                Label_1912: {
                    Label_1908: {
                        if (n39 == n40) {
                            n42 = this.e.q[3];
                            b = true;
                            if (a) {
                                break Label_1914;
                            }
                            if (n42 == (b ? 1 : 0)) {
                                final int n43 = this.e.q[1];
                                final int n44 = 36;
                                if (a) {
                                    break Label_1914;
                                }
                                if (n43 > n44) {
                                    final int n45 = this.e.q[2];
                                    final int n46 = 198;
                                    if (a) {
                                        break Label_1914;
                                    }
                                    if (n45 > n46) {
                                        final int p = this.p;
                                        if (!a && p == 1) {
                                            int n47 = 0;
                                            while (true) {
                                                while (true) {
                                                    Label_1578: {
                                                        if (!a) {
                                                            break Label_1578;
                                                        }
                                                        final x x4 = this;
                                                        x4.e.e[n47] = -1315861;
                                                        ++n47;
                                                    }
                                                    if (n47 < this.e.e.length) {
                                                        continue;
                                                    }
                                                    break;
                                                }
                                                final x x4 = this;
                                                if (!a) {
                                                    this.p = 289;
                                                    goto Label_1602;
                                                }
                                                continue;
                                            }
                                        }
                                        else {
                                            final int n48 = p;
                                            final int n49 = n38;
                                            int n50 = this.e.q[2] * n49 + n48;
                                            int n51 = 0;
                                            int n54 = 0;
                                            Label_1706: {
                                                int n53 = 0;
                                                Label_1704: {
                                                Label_1701:
                                                    while (true) {
                                                        System.arraycopy(this.l, 0, this.e.e, n50, 198);
                                                        n50 += this.e.q[2];
                                                        ++n51;
                                                        int i = 0;
                                                        int n52 = 0;
                                                        while (i >= n52) {
                                                            n51 = 158 + n48;
                                                            n53 = n51 - 98;
                                                            i = (n54 = n53);
                                                            if (a) {
                                                                break Label_1701;
                                                            }
                                                            n52 = n48;
                                                            if (a) {
                                                                continue;
                                                            }
                                                            if (i < n52) {
                                                                n54 = n48;
                                                                break Label_1701;
                                                            }
                                                            break Label_1704;
                                                        }
                                                    }
                                                    break Label_1706;
                                                }
                                                n54 = n53;
                                            }
                                            final int n55 = n54;
                                            this.n[0] = this.e.q[1] - (n49 + 7);
                                            this.n[1] = this.e.q[1] - (n49 + 7 + 5);
                                            this.n[2] = this.e.q[1] - (n49 + 7 + 11);
                                            final int n56 = n51;
                                            if (a) {
                                                break Label_1912;
                                            }
                                            if (n56 > 0) {
                                                int n57 = 0;
                                                while (true) {
                                                    Label_1898: {
                                                        if (!a) {
                                                            break Label_1898;
                                                        }
                                                        final int n58 = this.e.q[1];
                                                        final int n59 = this.n[n57];
                                                        if (a) {
                                                            break Label_1914;
                                                        }
                                                        if (n58 <= n59) {
                                                            break Label_1908;
                                                        }
                                                        int n60 = n51 + this.e.q[0] - this.n[n57] * this.e.q[2];
                                                        int n61 = 0;
                                                        int n62 = n51;
                                                        while (true) {
                                                            Label_1888: {
                                                                if (!a) {
                                                                    break Label_1888;
                                                                }
                                                                this.e.e[n60--] = this.o[n61++];
                                                                --n62;
                                                            }
                                                            if (n62 > n55) {
                                                                continue;
                                                            }
                                                            break;
                                                        }
                                                        ++n57;
                                                    }
                                                    if (n57 < this.n.length) {
                                                        continue;
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    final int j = this.k;
                }
            }
        }
        x x5 = null;
        Label_3972: {
            final int n63;
            Label_2296: {
                Label_2282: {
                    if (!a) {
                        if (n39 < n40) {
                            this.e.e = new int[13];
                            if (!a) {
                                break Label_2282;
                            }
                        }
                        k = this.k;
                        if (a) {
                            break Label_2296;
                        }
                    }
                    if (n42 != (b ? 1 : 0)) {
                        n63 = this.e.q[3];
                        if (a) {
                            break Label_2296;
                        }
                        if (n63 != 1) {
                            final int n64 = this.e.q[1];
                            if (a) {
                                break Label_2296;
                            }
                            if (n64 > 45) {
                                final int n65 = this.e.q[2];
                                if (a) {
                                    break Label_2296;
                                }
                                if (n65 > 36) {
                                    int n66 = this.e.q[0] - this.e.q[2] - this.e.q[2] * 15;
                                    int n67 = 10;
                                    int l = 0;
                                    int n68 = 0;
                                    int n69 = 0;
                                Block_46:
                                    while (true) {
                                        if (n66 > 0) {
                                            System.arraycopy(this.l, 0, this.e.e, n66, this.e.q[2]);
                                            n66 -= this.e.q[2];
                                        }
                                        ++n67;
                                        while (l >= 40) {
                                            n67 = this.e.q[2] - 38;
                                            n68 = n67 - 98;
                                            l = (n69 = n68);
                                            if (!a) {
                                                break Block_46;
                                            }
                                        }
                                    }
                                    if (!a) {
                                        if (l < 0) {
                                            n69 = 0;
                                        }
                                        else {
                                            n69 = n68;
                                        }
                                    }
                                    final int n70 = n69;
                                    final int n71 = n67;
                                    int[] q = null;
                                    int n73 = 0;
                                    Label_2280: {
                                        Label_2272: {
                                            if (a || n71 > 0) {
                                                int n72 = n71;
                                                while (true) {
                                                    Label_2262: {
                                                        if (!a) {
                                                            break Label_2262;
                                                        }
                                                        q = this.e.q;
                                                        n73 = 1;
                                                        if (a) {
                                                            break Label_2280;
                                                        }
                                                        if (q[n73] <= this.m[n72]) {
                                                            break Label_2272;
                                                        }
                                                        int n74 = n67 + this.e.q[0] - this.m[n72] * this.e.q[2];
                                                        int n75 = 0;
                                                        int n76 = n67;
                                                        while (true) {
                                                            Label_2252: {
                                                                if (!a) {
                                                                    break Label_2252;
                                                                }
                                                                this.e.e[n74--] = this.o[n75++];
                                                                --n76;
                                                            }
                                                            if (n76 > n70) {
                                                                continue;
                                                            }
                                                            break;
                                                        }
                                                        ++n72;
                                                    }
                                                    if (n72 < this.m.length) {
                                                        continue;
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                        final int[] q2 = this.e.q;
                                    }
                                    q[n73] = 2;
                                }
                            }
                        }
                    }
                }
                x5 = this;
                if (a) {
                    break Label_3972;
                }
                final int n77 = this.e.q[3];
            }
            if (n63 > 0) {
                int n78 = 0;
                int n79 = 0;
                final int n80 = this.e.q[3];
                final int n81 = 1;
                if (!a) {
                    if (n80 == n81) {
                        n78 = 18 + this.e.q[2] / 2 - 117;
                        n79 = 34 + this.e.q[1] / 2 - 57;
                    }
                    final int n82 = this.e.q[2] - 18;
                }
                final int n83 = n80 - n81;
                final int n84 = this.e.q[1] - 34 - n79;
                this.a(n83, n84, 13, 12, -65536);
                this.a(n83, n84, 11, 3, -16777216);
                this.a(n83 + 5, n84 + 5, 5, 5, -16777216);
                final int n85 = -8978432;
                this.a(n83 + 10, n84 + 5, 1, 5, n85);
                this.a(n83 + 6, n84 + 10, 4, 1, n85);
                this.a(n83, n84 + 11, 1, 1, n85);
                this.a(n83 + 10, n84, 1, 3, n85);
                this.a(n83 + 11, n84 - 1, 2, 1, n85);
                this.a(n83, n84 + 3, 1, 1, n85);
                this.a(n83 + 12, n84 + 11, 1, 1, n85);
                final int n86 = -4390912;
                this.a(n83 + 1, n84 + 3, 9, 1, n86);
                this.a(n83 + 6, n84 + 4, 4, 1, n86);
                this.e.t[4] = a("V\r\u0001#\u0007\u0011V\u0002$J\u0010") + a("m\u0011\u001a&I\r=U\u00fa\f\u0007@L").substring(0, 7).toLowerCase() + a("\u0010\u001a\u001a>");
                final int n87 = this.e.q[2] - 33 - n78;
                final int n88 = this.e.q[1] - 34 - n79;
                this.a(n87, n88, 11, 12, -65536);
                this.a(n87, n88 + 1, 8, 4, -16777216);
                this.a(n87, n88 + 5, 2, 1, -16777216);
                this.a(n87, n88 + 6, 5, 4, -16777216);
                final int n89 = -8978432;
                this.a(n87, n88 - 1, 10, 1, n89);
                this.a(n87 - 1, n88, 1, 1, n89);
                this.a(n87 - 1, n88 + 11, 1, 1, n89);
                this.a(n87 + 10, n88 + 11, 1, 1, n89);
                this.a(n87 + 10, n88, 1, 1, n89);
                this.a(n87, n88 + 10, 5, 1, n89);
                this.a(n87 + 4, n88 + 6, 1, 5, n89);
                this.a(n87 + 2, n88 + 4, 6, 1, n89);
                this.a(n87 + 1, n88 + 5, 1, 1, n89);
                x5 = this;
                if (a) {
                    break Label_3972;
                }
                if (this.e.q[2] > 93) {
                    final int n90 = this.e.q[2] - 48 - n78;
                    final int n91 = this.e.q[1] - 34 - n79;
                    this.a(n90, n91, 1, 12, -1);
                    final int n92 = -4605511;
                    this.a(n90 - 2, n91 - 1, 6, 1, n92);
                    this.a(n90 + 1, n91, 1, 12, n92);
                    final int n93 = -345671;
                    this.a(n90 - 2, n91, 2, 1, n93);
                    this.a(n90 + 1, n91, 3, 1, n93);
                    final int n94 = this.e.q[2] - 60 - n78;
                    final int n95 = this.e.q[1] - 34 - n79;
                    final int n96 = -4605511;
                    this.a(n94 + 1, n95, 1, 10, n96);
                    this.a(n94 + 5, n95, 1, 11, n96);
                    this.a(n94, n95 - 1, 2, 1, n96);
                    this.a(n94 + 4, n95 - 1, 2, 1, n96);
                    this.a(n94, n95 + 10, 5, 1, n96);
                    final int n97 = -1;
                    this.a(n94, n95, 1, 10, n97);
                    this.a(n94 + 4, n95, 1, 11, n97);
                    this.a(n94 + 1, n95 + 10, 1, 1, n97);
                    this.a(n94 + 2, n95 + 11, 2, 1, n97);
                    final int n98 = -345671;
                    this.a(n94 + 1, n95 + 11, 1, 1, n98);
                    this.a(n94 + 4, n95 + 11, 1, 1, n98);
                    final int n99 = this.e.q[2] - 71 - n78;
                    final int n100 = this.e.q[1] - 34 - n79;
                    final int n101 = -4605511;
                    this.a(n99, n100 + 1, 2, 10, n101);
                    this.a(n99 + 4, n100 + 1, 2, 10, n101);
                    this.a(n99 + 2, n100 - 1, 2, 1, n101);
                    this.a(n99, n100 + 10, 5, 1, n101);
                    final int n102 = -345671;
                    this.a(n99, n100, 5, 1, n102);
                    this.a(n99 + 1, n100 + 11, 4, 1, n102);
                    final int n103 = -1;
                    this.a(n99 + 1, n100, 1, 1, n103);
                    this.a(n99 + 4, n100, 1, 1, n103);
                    this.a(n99, n100 + 1, 1, 9, n103);
                    this.a(n99 + 1, n100 + 10, 1, 1, n103);
                    this.a(n99 + 2, n100 + 11, 2, 1, n103);
                    this.a(n99 + 4, n100 + 10, 1, 1, n103);
                    final int n104 = this.e.q[2] - 82 - n78;
                    final int n105 = this.e.q[1] - 34 - n79;
                    final int n106 = -4605511;
                    this.a(n104, n105 - 1, 2, 13, n106);
                    this.a(n104 + 4, n105 - 1, 2, 13, n106);
                    this.a(n104 + 2, n105 + 4, 2, 1, n106);
                    final int n107 = -1;
                    this.a(n104, n105, 1, 12, n107);
                    this.a(n104 + 4, n105, 1, 12, n107);
                    this.a(n104 + 1, n105 + 5, 3, 1, n107);
                    final int n108 = this.e.q[2] - 92 - n78;
                    final int n109 = this.e.q[1] - 34 - n79;
                    final int n110 = -4605511;
                    this.a(n108, n109 + 1, 2, 3, n110);
                    this.a(n108 + 4, n109 + 1, 2, 2, n110);
                    this.a(n108 + 2, n109 - 1, 2, 1, n110);
                    this.a(n108, n109 + 8, 2, 3, n110);
                    this.a(n108 + 2, n109 + 10, 2, 1, n110);
                    this.a(n108 + 2, n109 + 10, 2, 1, n110);
                    this.a(n108 + 4, n109 + 7, 2, 3, n110);
                    final int n111 = -1;
                    this.a(n108 + 1, n109, 4, 1, n111);
                    this.a(n108 + 4, n109, 1, 3, n111);
                    this.a(n108, n109 + 1, 1, 2, n111);
                    this.a(n108, n109 + 8, 1, 2, n111);
                    this.a(n108 + 1, n109 + 10, 1, 1, n111);
                    this.a(n108 + 2, n109 + 11, 2, 1, n111);
                    this.a(n108 + 4, n109 + 6, 1, 5, n111);
                    this.a(n108 + 1, n109 + 3, 1, 2, n111);
                    this.a(n108 + 2, n109 + 4, 1, 2, n111);
                    this.a(n108 + 3, n109 + 5, 1, 2, n111);
                    final int n112 = -345671;
                    this.a(n108 + 2, n109, 1, 1, n112);
                    this.a(n108 + 1, n109 + 11, 1, 1, n112);
                    this.a(n108 + 4, n109 + 11, 1, 1, n112);
                    this.a(n108 + 3, n109 + 5, 1, 1, n112);
                }
            }
            x5 = this;
        }
        final qz a2 = x5.a;
        Label_4005: {
            if (!a) {
                if (a2 == null) {
                    this.e.j.flush();
                    if (!a) {
                        break Label_4005;
                    }
                }
                final qz a3 = this.a;
            }
            a2.flush();
        }
        this.e.a.drawImage(this.e.j, 0, 0, this.e.h);
        final int n113 = this.e.q[3];
        final int n114 = 1;
        if (!a) {
            if (n113 == n114) {
                this.e.a.setColor(Color.lightGray);
                this.e.a.drawRect(n37, n38, 197, 35);
                this.e.a.setColor(Color.gray);
                this.e.a.drawRect(n37 + 1, n38 + 1, 195, 33);
                this.e.a.drawLine(n37 + 1, n38 + 23, n37 + 195, n38 + 23);
                this.e.a.setColor(Color.red);
                this.e.a.fillRect(n37 + 3, n38 + 25, (int)(192.0f * this.e.z), 8);
                this.e.a.setFont(this.d);
                this.e.a.setColor(Color.white);
                this.e.a.drawString(this.e.m, n37 + 4, n38 + 18);
            }
            final int m = this.k;
            if (a) {
                return;
            }
        }
        if (n113 < n114) {
            this.e.t[4] = null;
            final int j2 = this.j;
            if (a) {
                return;
            }
            if (j2 == 1) {
                final int n115 = this.e.q[3];
                if (a) {
                    return;
                }
                if (n115 == 1) {
                    n38 += 40;
                    this.e.a.setColor(Color.black);
                    this.e.a.fillRect(n37, n38, 197, 17);
                    this.e.a.setColor(Color.lightGray);
                    this.e.a.drawRect(n37, n38, 197, 17);
                    this.e.a.setColor(Color.gray);
                    this.e.a.drawRect(n37 + 1, n38 + 1, 195, 15);
                    this.e.a.setColor(Color.white);
                    this.e.a.setFont(this.d);
                    this.e.a.drawString(a("{\u001d\u00000\\J\u0010\u001a=\\RY#6OM\u0010\u001a="), n37 + 43, n38 + 13);
                }
            }
        }
        this.e.g.drawImage(this.e.d, 0, 0, this.e.h);
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '>';
                    break;
                }
                case 1: {
                    c2 = 'y';
                    break;
                }
                case 2: {
                    c2 = 'u';
                    break;
                }
                case 3: {
                    c2 = 'S';
                    break;
                }
                default: {
                    c2 = '=';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
