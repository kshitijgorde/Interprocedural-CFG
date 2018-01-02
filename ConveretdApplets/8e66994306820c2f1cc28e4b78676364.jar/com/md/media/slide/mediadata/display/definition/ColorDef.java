// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display.definition;

import java.awt.Color;
import a.b.o.a.b.d;
import a.b.p.b;
import a.b.c.a;
import a.b.o.a.a.c;

public class ColorDef implements c
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private static String[] z;
    
    public int b() {
        return this.a;
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public int c() {
        return this.b;
    }
    
    public void b(final int b) {
        this.b = b;
    }
    
    public int a() {
        return 8;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(ColorDef.z[0]);
        }
        if (n >= array.length || n < 0) {
            throw new a(ColorDef.z[2] + n + ColorDef.z[1] + array.length);
        }
        int n2 = 0;
        try {
            this.a(a.b.p.b.a(array, n + n2));
            n2 += 4;
            this.b(a.b.p.b.a(array, n + n2));
            n2 += 4;
        }
        catch (a.b.p.a a) {
            throw new a(ColorDef.z[5] + a.toString());
        }
        return n2;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(ColorDef.z[0]);
        }
        if (n >= array.length || n < 0) {
            throw new a(ColorDef.z[2] + n + ColorDef.z[1] + array.length);
        }
        final int n2 = 0;
        int n4;
        try {
            final int n3 = n2 + a.b.p.b.a(this.b(), array, n + n2);
            n4 = n3 + a.b.p.b.a(this.c(), array, n + n3);
        }
        catch (a.b.p.a a) {
            throw new a(ColorDef.z[3] + a.toString());
        }
        return n4;
    }
    
    public byte[] a() throws a {
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
    
    public ColorDef() {
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = null;
        this.g = null;
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
    
    private void a(final int c, final int d, final int e, final String f, final String g) {
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public a.b.o.a.b.d a() throws a.b.o.a.c {
        Color color = null;
        if (this.f != null) {
            color = a(this.f);
        }
        else if (this.g != null) {
            try {
                color = Color.decode(this.g);
            }
            catch (NumberFormatException ex) {}
        }
        else if (c(this.c) && c(this.d) && c(this.e)) {
            color = new Color(this.c, this.d, this.e);
        }
        if (color == null) {
            throw new a.b.o.a.c(ColorDef.z[4]);
        }
        return new a.b.o.a.b.a(color);
    }
    
    public String toString() {
        return new String(ColorDef.z[11] + this.c + ColorDef.z[10] + this.d + ColorDef.z[8] + this.e + ColorDef.z[7] + this.f + ColorDef.z[9] + this.g + "}");
    }
    
    private static boolean c(final int n) {
        return n < 256 && n > -1;
    }
    
    private static Color a(final String s) {
        Color color = null;
        if (s.equalsIgnoreCase(ColorDef.z[14])) {
            color = Color.red;
        }
        if (s.equalsIgnoreCase(ColorDef.z[17])) {
            color = Color.blue;
        }
        if (s.equalsIgnoreCase(ColorDef.z[24])) {
            color = Color.green;
        }
        if (s.equalsIgnoreCase(ColorDef.z[21])) {
            color = Color.black;
        }
        if (s.equalsIgnoreCase(ColorDef.z[19])) {
            color = Color.white;
        }
        if (s.equalsIgnoreCase(ColorDef.z[22])) {
            color = Color.yellow;
        }
        if (s.equalsIgnoreCase(ColorDef.z[23])) {
            color = Color.pink;
        }
        if (s.equalsIgnoreCase(ColorDef.z[16])) {
            color = Color.orange;
        }
        if (s.equalsIgnoreCase(ColorDef.z[15])) {
            color = Color.magenta;
        }
        if (s.equalsIgnoreCase(ColorDef.z[13])) {
            color = Color.lightGray;
        }
        if (s.equalsIgnoreCase(ColorDef.z[18])) {
            color = Color.gray;
        }
        if (s.equalsIgnoreCase(ColorDef.z[12])) {
            color = Color.darkGray;
        }
        if (s.equalsIgnoreCase(ColorDef.z[20])) {
            color = Color.cyan;
        }
        return color;
    }
    
    public String d() {
        return this.f;
    }
    
    public String e() {
        return this.g;
    }
    
    public Object clone() {
        try {
            final ColorDef colorDef = (ColorDef)super.clone();
            if (this.e() != null) {
                colorDef.b(new String(this.e()));
            }
            if (this.d() != null) {
                colorDef.a(new String(this.d()));
            }
            return colorDef;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).e(ColorDef.z[6], ex);
            return null;
        }
    }
    
    static {
        final String[] z = new String[25];
        final int n = 0;
        final char[] charArray = "}6(YiW\"0TiR16T0\u001d".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '3';
                    break;
                }
                case 1: {
                    c2 = 'C';
                    break;
                }
                case 2: {
                    c2 = 'D';
                    break;
                }
                case 3: {
                    c2 = '5';
                    break;
                }
                default: {
                    c2 = 'I';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0013*7\u0015'\\7d\\'\u00137,PiA\"*R,\u0013sd\u0018i".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '3';
                    break;
                }
                case 1: {
                    c4 = 'C';
                    break;
                }
                case 2: {
                    c4 = 'D';
                    break;
                }
                case 3: {
                    c4 = '5';
                    break;
                }
                default: {
                    c4 = 'I';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "r16T0\u0013**Q,Kc+@=\u0013,\"\u0015+\\6*Q:\tc".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '3';
                    break;
                }
                case 1: {
                    c6 = 'C';
                    break;
                }
                case 2: {
                    c6 = 'D';
                    break;
                }
                case 3: {
                    c6 = '5';
                    break;
                }
                default: {
                    c6 = 'I';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "g+!\u0015.Z5![iR16T0\u0013*7\u0015'\\7dY(A$!\u0015,],1R!\u00137+\u0015!\\/ \u0015=[&dV&]7![=@c+SiG+-Fi\\!.P*Gyd".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '3';
                    break;
                }
                case 1: {
                    c8 = 'C';
                    break;
                }
                case 2: {
                    c8 = 'D';
                    break;
                }
                case 3: {
                    c8 = '5';
                    break;
                }
                default: {
                    c8 = 'I';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "p,1Y-\u0013-+AiT&*P;R7!\u0015(]c\u0007Z%\\1\u0000T=Rc\"G&^c0] @c\u0007Z%\\1\u0000P/\u001d".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '3';
                    break;
                }
                case 1: {
                    c10 = 'C';
                    break;
                }
                case 2: {
                    c10 = 'D';
                    break;
                }
                case 3: {
                    c10 = '5';
                    break;
                }
                default: {
                    c10 = 'I';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "g+!\u0015.Z5![iR16T0\u0013*7\u0015'\\7dY(A$!\u0015,],1R!\u00137+\u0015/Z/(\u0015=[*7\u0015&Q)!V=\tc".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '3';
                    break;
                }
                case 1: {
                    c12 = 'C';
                    break;
                }
                case 2: {
                    c12 = 'D';
                    break;
                }
                case 3: {
                    c12 = '5';
                    break;
                }
                default: {
                    c12 = 'I';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "p\"*[&Gc'Y&]&dv&_,6q,Uyd".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '3';
                    break;
                }
                case 1: {
                    c14 = 'C';
                    break;
                }
                case 2: {
                    c14 = 'D';
                    break;
                }
                case 3: {
                    c14 = '5';
                    break;
                }
                default: {
                    c14 = 'I';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\bc P:Pyd".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '3';
                    break;
                }
                case 1: {
                    c16 = 'C';
                    break;
                }
                case 2: {
                    c16 = 'D';
                    break;
                }
                case 3: {
                    c16 = '5';
                    break;
                }
                default: {
                    c16 = 'I';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u001fc&\u000fi".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '3';
                    break;
                }
                case 1: {
                    c18 = 'C';
                    break;
                }
                case 2: {
                    c18 = 'D';
                    break;
                }
                case 3: {
                    c18 = '5';
                    break;
                }
                default: {
                    c18 = 'I';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "\bc,P1\tc".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '3';
                    break;
                }
                case 1: {
                    c20 = 'C';
                    break;
                }
                case 2: {
                    c20 = 'D';
                    break;
                }
                case 3: {
                    c20 = '5';
                    break;
                }
                default: {
                    c20 = 'I';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u001fc#\u000fi".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '3';
                    break;
                }
                case 1: {
                    c22 = 'C';
                    break;
                }
                case 2: {
                    c22 = 'D';
                    break;
                }
                case 3: {
                    c22 = '5';
                    break;
                }
                default: {
                    c22 = 'I';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "p,(Z;w&\"\u00152Ayd".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '3';
                    break;
                }
                case 1: {
                    c24 = 'C';
                    break;
                }
                case 2: {
                    c24 = 'D';
                    break;
                }
                case 3: {
                    c24 = '5';
                    break;
                }
                default: {
                    c24 = 'I';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "W\"6^.A\"=".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '3';
                    break;
                }
                case 1: {
                    c26 = 'C';
                    break;
                }
                case 2: {
                    c26 = 'D';
                    break;
                }
                case 3: {
                    c26 = '5';
                    break;
                }
                default: {
                    c26 = 'I';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "_*#]=T1%L".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = '3';
                    break;
                }
                case 1: {
                    c28 = 'C';
                    break;
                }
                case 2: {
                    c28 = 'D';
                    break;
                }
                case 3: {
                    c28 = '5';
                    break;
                }
                default: {
                    c28 = 'I';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "A& ".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = '3';
                    break;
                }
                case 1: {
                    c30 = 'C';
                    break;
                }
                case 2: {
                    c30 = 'D';
                    break;
                }
                case 3: {
                    c30 = '5';
                    break;
                }
                default: {
                    c30 = 'I';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "^\"#P'G\"".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = '3';
                    break;
                }
                case 1: {
                    c32 = 'C';
                    break;
                }
                case 2: {
                    c32 = 'D';
                    break;
                }
                case 3: {
                    c32 = '5';
                    break;
                }
                default: {
                    c32 = 'I';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        z[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "\\1%[.V".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = '3';
                    break;
                }
                case 1: {
                    c34 = 'C';
                    break;
                }
                case 2: {
                    c34 = 'D';
                    break;
                }
                case 3: {
                    c34 = '5';
                    break;
                }
                default: {
                    c34 = 'I';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        z[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "Q/1P".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = '3';
                    break;
                }
                case 1: {
                    c36 = 'C';
                    break;
                }
                case 2: {
                    c36 = 'D';
                    break;
                }
                case 3: {
                    c36 = '5';
                    break;
                }
                default: {
                    c36 = 'I';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        z[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "T1%L".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = '3';
                    break;
                }
                case 1: {
                    c38 = 'C';
                    break;
                }
                case 2: {
                    c38 = 'D';
                    break;
                }
                case 3: {
                    c38 = '5';
                    break;
                }
                default: {
                    c38 = 'I';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        z[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "D+-A,".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = '3';
                    break;
                }
                case 1: {
                    c40 = 'C';
                    break;
                }
                case 2: {
                    c40 = 'D';
                    break;
                }
                case 3: {
                    c40 = '5';
                    break;
                }
                default: {
                    c40 = 'I';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        z[n58] = new String(charArray20).intern();
        final int n61 = 20;
        final char[] charArray21 = "P:%[".toCharArray();
        final int length17 = charArray21.length;
        for (int n62 = 0; length17 > n62; ++n62) {
            final int n63 = n62;
            final char c41 = charArray21[n63];
            char c42 = '\0';
            switch (n62 % 5) {
                case 0: {
                    c42 = '3';
                    break;
                }
                case 1: {
                    c42 = 'C';
                    break;
                }
                case 2: {
                    c42 = 'D';
                    break;
                }
                case 3: {
                    c42 = '5';
                    break;
                }
                default: {
                    c42 = 'I';
                    break;
                }
            }
            charArray21[n63] = (char)(c41 ^ c42);
        }
        z[n61] = new String(charArray21).intern();
        final int n64 = 21;
        final char[] charArray22 = "Q/%V\"".toCharArray();
        final int length18 = charArray22.length;
        for (int n65 = 0; length18 > n65; ++n65) {
            final int n66 = n65;
            final char c43 = charArray22[n66];
            char c44 = '\0';
            switch (n65 % 5) {
                case 0: {
                    c44 = '3';
                    break;
                }
                case 1: {
                    c44 = 'C';
                    break;
                }
                case 2: {
                    c44 = 'D';
                    break;
                }
                case 3: {
                    c44 = '5';
                    break;
                }
                default: {
                    c44 = 'I';
                    break;
                }
            }
            charArray22[n66] = (char)(c43 ^ c44);
        }
        z[n64] = new String(charArray22).intern();
        final int n67 = 22;
        final char[] charArray23 = "J&(Y&D".toCharArray();
        final int length19 = charArray23.length;
        for (int n68 = 0; length19 > n68; ++n68) {
            final int n69 = n68;
            final char c45 = charArray23[n69];
            char c46 = '\0';
            switch (n68 % 5) {
                case 0: {
                    c46 = '3';
                    break;
                }
                case 1: {
                    c46 = 'C';
                    break;
                }
                case 2: {
                    c46 = 'D';
                    break;
                }
                case 3: {
                    c46 = '5';
                    break;
                }
                default: {
                    c46 = 'I';
                    break;
                }
            }
            charArray23[n69] = (char)(c45 ^ c46);
        }
        z[n67] = new String(charArray23).intern();
        final int n70 = 23;
        final char[] charArray24 = "C**^".toCharArray();
        final int length20 = charArray24.length;
        for (int n71 = 0; length20 > n71; ++n71) {
            final int n72 = n71;
            final char c47 = charArray24[n72];
            char c48 = '\0';
            switch (n71 % 5) {
                case 0: {
                    c48 = '3';
                    break;
                }
                case 1: {
                    c48 = 'C';
                    break;
                }
                case 2: {
                    c48 = 'D';
                    break;
                }
                case 3: {
                    c48 = '5';
                    break;
                }
                default: {
                    c48 = 'I';
                    break;
                }
            }
            charArray24[n72] = (char)(c47 ^ c48);
        }
        z[n70] = new String(charArray24).intern();
        final int n73 = 24;
        final char[] charArray25 = "T1!P'".toCharArray();
        final int length21 = charArray25.length;
        for (int n74 = 0; length21 > n74; ++n74) {
            final int n75 = n74;
            final char c49 = charArray25[n75];
            char c50 = '\0';
            switch (n74 % 5) {
                case 0: {
                    c50 = '3';
                    break;
                }
                case 1: {
                    c50 = 'C';
                    break;
                }
                case 2: {
                    c50 = 'D';
                    break;
                }
                case 3: {
                    c50 = '5';
                    break;
                }
                default: {
                    c50 = 'I';
                    break;
                }
            }
            charArray25[n75] = (char)(c49 ^ c50);
        }
        z[n73] = new String(charArray25).intern();
        ColorDef.z = z;
    }
}
