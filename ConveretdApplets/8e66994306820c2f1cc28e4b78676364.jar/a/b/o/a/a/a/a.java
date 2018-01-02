// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.a.a.a;

import java.awt.Color;
import a.b.o.a.b.d;
import a.b.g.b;
import a.b.o.a.a.c;

public class a implements c
{
    private int a;
    private int b;
    private int c;
    private String d;
    private String e;
    private static String[] z;
    
    public a() {
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = null;
        this.e = null;
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, null, null);
    }
    
    public void a(final String s) {
        this.a(-1, -1, -1, s, null);
    }
    
    public void b(final String s) {
        this.a(-1, -1, -1, null, s);
    }
    
    public String a() {
        return this.d;
    }
    
    public String b() {
        return this.e;
    }
    
    public Object clone() {
        try {
            final a a = (a)super.clone();
            if (this.a() != null) {
                a.a(new String(this.a()));
            }
            if (this.b() != null) {
                a.b(new String(this.b()));
            }
            return a;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).e(a.b.o.a.a.a.a.z[14], ex);
            return null;
        }
    }
    
    private void a(final int a, final int b, final int c, final String d, final String e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public int a() {
        return 0;
    }
    
    public a.b.o.a.b.d a() throws a.b.o.a.c {
        Color color = null;
        if (this.d != null) {
            color = a(this.d);
        }
        else if (this.e != null) {
            try {
                color = Color.decode(this.e);
            }
            catch (NumberFormatException ex) {}
        }
        else if (a(this.a) && a(this.b) && a(this.c)) {
            color = new Color(this.a, this.b, this.c);
        }
        if (color == null) {
            throw new a.b.o.a.c(a.b.o.a.a.a.a.z[13]);
        }
        return new a.b.o.a.b.a(color);
    }
    
    public String toString() {
        return new String(a.b.o.a.a.a.a.z[16] + this.a + a.b.o.a.a.a.a.z[18] + this.b + a.b.o.a.a.a.a.z[15] + this.c + a.b.o.a.a.a.a.z[17] + this.d + a.b.o.a.a.a.a.z[19] + this.e + "}");
    }
    
    private static boolean a(final int n) {
        return n < 256 && n > -1;
    }
    
    private static Color a(final String s) {
        Color color = null;
        if (s.equalsIgnoreCase(a.z[2])) {
            color = Color.red;
        }
        if (s.equalsIgnoreCase(a.z[6])) {
            color = Color.blue;
        }
        if (s.equalsIgnoreCase(a.z[1])) {
            color = Color.green;
        }
        if (s.equalsIgnoreCase(a.z[4])) {
            color = Color.black;
        }
        if (s.equalsIgnoreCase(a.z[7])) {
            color = Color.white;
        }
        if (s.equalsIgnoreCase(a.z[3])) {
            color = Color.yellow;
        }
        if (s.equalsIgnoreCase(a.z[5])) {
            color = Color.pink;
        }
        if (s.equalsIgnoreCase(a.z[11])) {
            color = Color.orange;
        }
        if (s.equalsIgnoreCase(a.z[0])) {
            color = Color.magenta;
        }
        if (s.equalsIgnoreCase(a.z[8])) {
            color = Color.lightGray;
        }
        if (s.equalsIgnoreCase(a.z[10])) {
            color = Color.gray;
        }
        if (s.equalsIgnoreCase(a.z[12])) {
            color = Color.darkGray;
        }
        if (s.equalsIgnoreCase(a.z[9])) {
            color = Color.cyan;
        }
        return color;
    }
    
    public int a(final byte[] array, final int n) throws a.b.c.a {
        return 0;
    }
    
    public int a(final byte[] array) throws a.b.c.a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a.b.c.a {
        return 0;
    }
    
    public byte[] a() throws a.b.c.a {
        final int a = this.a();
        if (a == 0) {
            return new byte[0];
        }
        byte[] array = new byte[a];
        final int b = this.b(array, 0);
        if (b < array.length) {
            final byte[] array2 = new byte[b];
            System.arraycopy(array, 0, array2, 0, b);
            array = array2;
        }
        return array;
    }
    
    static {
        final String[] z = new String[20];
        final int n = 0;
        final char[] charArray = "\u0006w/T\b\u001fw".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'k';
                    break;
                }
                case 1: {
                    c2 = '\u0016';
                    break;
                }
                case 2: {
                    c2 = 'H';
                    break;
                }
                case 3: {
                    c2 = '1';
                    break;
                }
                default: {
                    c2 = 'f';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\fd-T\b".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'k';
                    break;
                }
                case 1: {
                    c4 = '\u0016';
                    break;
                }
                case 2: {
                    c4 = 'H';
                    break;
                }
                case 3: {
                    c4 = '1';
                    break;
                }
                default: {
                    c4 = 'f';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0019s,".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'k';
                    break;
                }
                case 1: {
                    c6 = '\u0016';
                    break;
                }
                case 2: {
                    c6 = 'H';
                    break;
                }
                case 3: {
                    c6 = '1';
                    break;
                }
                default: {
                    c6 = 'f';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0012s$]\t\u001c".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'k';
                    break;
                }
                case 1: {
                    c8 = '\u0016';
                    break;
                }
                case 2: {
                    c8 = 'H';
                    break;
                }
                case 3: {
                    c8 = '1';
                    break;
                }
                default: {
                    c8 = 'f';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\tz)R\r".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'k';
                    break;
                }
                case 1: {
                    c10 = '\u0016';
                    break;
                }
                case 2: {
                    c10 = 'H';
                    break;
                }
                case 3: {
                    c10 = '1';
                    break;
                }
                default: {
                    c10 = 'f';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u001b\u007f&Z".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'k';
                    break;
                }
                case 1: {
                    c12 = '\u0016';
                    break;
                }
                case 2: {
                    c12 = 'H';
                    break;
                }
                case 3: {
                    c12 = '1';
                    break;
                }
                default: {
                    c12 = 'f';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\tz=T".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'k';
                    break;
                }
                case 1: {
                    c14 = '\u0016';
                    break;
                }
                case 2: {
                    c14 = 'H';
                    break;
                }
                case 3: {
                    c14 = '1';
                    break;
                }
                default: {
                    c14 = 'f';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u001c~!E\u0003".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'k';
                    break;
                }
                case 1: {
                    c16 = '\u0016';
                    break;
                }
                case 2: {
                    c16 = 'H';
                    break;
                }
                case 3: {
                    c16 = '1';
                    break;
                }
                default: {
                    c16 = 'f';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0007\u007f/Y\u0012\fd)H".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'k';
                    break;
                }
                case 1: {
                    c18 = '\u0016';
                    break;
                }
                case 2: {
                    c18 = 'H';
                    break;
                }
                case 3: {
                    c18 = '1';
                    break;
                }
                default: {
                    c18 = 'f';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "\bo)_".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'k';
                    break;
                }
                case 1: {
                    c20 = '\u0016';
                    break;
                }
                case 2: {
                    c20 = 'H';
                    break;
                }
                case 3: {
                    c20 = '1';
                    break;
                }
                default: {
                    c20 = 'f';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\fd)H".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'k';
                    break;
                }
                case 1: {
                    c22 = '\u0016';
                    break;
                }
                case 2: {
                    c22 = 'H';
                    break;
                }
                case 3: {
                    c22 = '1';
                    break;
                }
                default: {
                    c22 = 'f';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "\u0004d)_\u0001\u000e".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = 'k';
                    break;
                }
                case 1: {
                    c24 = '\u0016';
                    break;
                }
                case 2: {
                    c24 = 'H';
                    break;
                }
                case 3: {
                    c24 = '1';
                    break;
                }
                default: {
                    c24 = 'f';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "\u000fw:Z\u0001\u0019w1".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = 'k';
                    break;
                }
                case 1: {
                    c26 = '\u0016';
                    break;
                }
                case 2: {
                    c26 = 'H';
                    break;
                }
                case 3: {
                    c26 = '1';
                    break;
                }
                default: {
                    c26 = 'f';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "(y=]\u0002Kx'EF\fs&T\u0014\nb-\u0011\u0007\u00056\u000b^\n\u0004d\fP\u0012\n6.C\t\u00066<Y\u000f\u00186\u000b^\n\u0004d\fT\u0000\u0002x!E\u000f\u0004x\u0004X\u0012\u000e".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = 'k';
                    break;
                }
                case 1: {
                    c28 = '\u0016';
                    break;
                }
                case 2: {
                    c28 = 'H';
                    break;
                }
                case 3: {
                    c28 = '1';
                    break;
                }
                default: {
                    c28 = 'f';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "(w&_\t\u001f6+]\t\u0005shr\t\u0007y:u\u0003\r\u007f&E\u000f\u0004x\u0004X\u0012\u000e,h".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = 'k';
                    break;
                }
                case 1: {
                    c30 = '\u0016';
                    break;
                }
                case 2: {
                    c30 = 'H';
                    break;
                }
                case 3: {
                    c30 = '1';
                    break;
                }
                default: {
                    c30 = 'f';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "G6*\u000bF".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = 'k';
                    break;
                }
                case 1: {
                    c32 = '\u0016';
                    break;
                }
                case 2: {
                    c32 = 'H';
                    break;
                }
                case 3: {
                    c32 = '1';
                    break;
                }
                default: {
                    c32 = 'f';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        z[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "(y$^\u0014/s.X\b\u0002b!^\bKm:\u000bF".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = 'k';
                    break;
                }
                case 1: {
                    c34 = '\u0016';
                    break;
                }
                case 2: {
                    c34 = 'H';
                    break;
                }
                case 3: {
                    c34 = '1';
                    break;
                }
                default: {
                    c34 = 'f';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        z[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "P6,T\u0015\b,h".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = 'k';
                    break;
                }
                case 1: {
                    c36 = '\u0016';
                    break;
                }
                case 2: {
                    c36 = 'H';
                    break;
                }
                case 3: {
                    c36 = '1';
                    break;
                }
                default: {
                    c36 = 'f';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        z[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "G6/\u000bF".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = 'k';
                    break;
                }
                case 1: {
                    c38 = '\u0016';
                    break;
                }
                case 2: {
                    c38 = 'H';
                    break;
                }
                case 3: {
                    c38 = '1';
                    break;
                }
                default: {
                    c38 = 'f';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        z[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "P6 T\u001eQ6".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = 'k';
                    break;
                }
                case 1: {
                    c40 = '\u0016';
                    break;
                }
                case 2: {
                    c40 = 'H';
                    break;
                }
                case 3: {
                    c40 = '1';
                    break;
                }
                default: {
                    c40 = 'f';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        z[n58] = new String(charArray20).intern();
        a.z = z;
    }
}
