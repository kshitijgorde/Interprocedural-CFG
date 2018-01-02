import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;

// 
// Decompiled by Procyon v0.5.30
// 

class mb extends Thread
{
    private final esChat a;
    private static String[] z;
    
    mb(final esChat a) {
        this.a = a;
    }
    
    public void run() {
        final int m = fb.m;
        try {
            final esChat a = this.a;
            if (m == 0) {
                if (a.Gb != null) {
                    try {
                        this.a.Jb = true;
                        final esChat a2 = this.a;
                        Label_0116: {
                            if (m == 0) {
                                if (a2.Sb == null) {
                                    this.a.a(mb.z[6] + this.a.wb, 1);
                                    if (m == 0) {
                                        break Label_0116;
                                    }
                                }
                                final esChat a3 = this.a;
                            }
                            a2.a(mb.z[6] + this.a.Sb, 1);
                        }
                        Thread.sleep(3000L);
                    }
                    catch (Exception ex) {}
                    final esChat a4 = this.a;
                    if (m == 0) {
                        if (a4.r != null) {
                            this.a.a(String.valueOf(this.a.a(11, "", "", "")) + mb.z[9]);
                        }
                        final esChat a5 = this.a;
                    }
                    a4.a(this.a.Gb, null);
                }
                this.a.Tb.b.a();
                this.a.C = this.a.c(mb.z[3], mb.z[1]);
                final esChat a6 = this.a;
            }
            final String s = a.C[0];
            if (m == 0) {
                if (s == null) {
                    this.a.C = this.a.c(mb.z[3], mb.z[8]);
                    this.a.D = true;
                }
                this.a.getDocumentBase().toString();
            }
            final String s2 = s;
            String jc;
            final String s3 = jc = this.a.C[0];
            if (m == 0) {
                if (s3 != null) {
                    final boolean d = this.a.D;
                    if (m == 0 && d) {
                        int n = 0;
                        while (true) {
                            Label_0400: {
                                if (m == 0) {
                                    break Label_0400;
                                }
                                this.a.C[n] = this.a.C[n].substring(this.a.C[n].indexOf("=") + 1);
                                if (s2.toLowerCase().indexOf(this.a.C[n].toLowerCase()) > 0) {
                                    return;
                                }
                                ++n;
                            }
                            if (this.a.C[n] != null) {
                                continue;
                            }
                            break;
                        }
                    }
                    else {
                        int n2 = d ? 1 : 0;
                        int n3 = 0;
                        int n5 = 0;
                        while (true) {
                            while (true) {
                                Label_0493: {
                                    if (m == 0) {
                                        break Label_0493;
                                    }
                                    this.a.C[n3] = this.a.C[n3].substring(this.a.C[n3].indexOf("=") + 1);
                                    final int index;
                                    int n4 = index = s2.toLowerCase().indexOf(this.a.C[n3].toLowerCase());
                                    Label_0490: {
                                        if (m == 0) {
                                            if (n5 <= 0) {
                                                break Label_0490;
                                            }
                                            n4 = 1;
                                        }
                                        n2 = n4;
                                    }
                                    ++n3;
                                }
                                if (this.a.C[n3] != null) {
                                    continue;
                                }
                                break;
                            }
                            int n4;
                            n5 = (n4 = n2);
                            if (m != 0) {
                                continue;
                            }
                            break;
                        }
                        if (n5 == 0) {
                            return;
                        }
                    }
                }
                final String r;
                jc = (r = this.a.r);
            }
            if (m == 0) {
                if (s3 == null) {
                    this.a.a(mb.z[7]);
                    return;
                }
                this.a.a("\n" + this.a.a(7, "", "", ""));
                this.a.a(this.a.a(8, "", "", ""));
                this.a.Jb = false;
                jc = this.a.jc;
            }
            this.a.Gb = new Socket(this.a.r, Integer.parseInt(jc));
            try {
                this.a.Wb = new BufferedReader(new InputStreamReader(this.a.Gb.getInputStream(), this.a.W));
                this.a.Xb = new BufferedWriter(new OutputStreamWriter(this.a.Gb.getOutputStream(), this.a.W));
            }
            catch (Exception ex2) {
                this.a.Wb = new BufferedReader(new InputStreamReader(this.a.Gb.getInputStream()));
                this.a.Xb = new BufferedWriter(new OutputStreamWriter(this.a.Gb.getOutputStream()));
            }
            new pb(this.a).start();
            this.a.ic = 0;
            this.a.a(mb.z[4] + this.a.Z, 1);
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex3) {}
            this.a.a(String.valueOf(this.a.ib) + " " + this.a.q + mb.z[0] + this.a.p, 1);
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex4) {}
            this.a.a(mb.z[5] + this.a.n, 1);
        }
        catch (Exception ex5) {
            this.a.a(String.valueOf(this.a.a(10, "", "", "")) + mb.z[2] + this.a.kc + ")");
            final esChat a7 = this.a;
            ++a7.kc;
            try {
                Thread.sleep(2000L);
            }
            catch (Exception ex6) {}
            final esChat a8 = this.a;
            if (m == 0) {
                if (a8.kc >= 4) {
                    return;
                }
                final esChat a9 = this.a;
            }
            a8.c();
        }
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "P\u0010\u0000\u000b_\u001c\u0014\u0000\u001bJP\u000b\u0006\u0006\rB\\U".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = '|';
                    break;
                }
                case 2: {
                    c2 = 'o';
                    break;
                }
                case 3: {
                    c2 = 'h';
                    break;
                }
                default: {
                    c2 = '>';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "1\u0010\u0003\u0007I\u0015\u0018O,Q\u001d\u001d\u0006\u0006M".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'p';
                    break;
                }
                case 1: {
                    c4 = '|';
                    break;
                }
                case 2: {
                    c4 = 'o';
                    break;
                }
                case 3: {
                    c4 = 'h';
                    break;
                }
                default: {
                    c4 = '>';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "PT".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'p';
                    break;
                }
                case 1: {
                    c6 = '|';
                    break;
                }
                case 2: {
                    c6 = 'o';
                    break;
                }
                case 3: {
                    c6 = 'h';
                    break;
                }
                default: {
                    c6 = '>';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0003\u0019\u001b\u001cW\u001e\u001b\u001cFJ\b\b".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'p';
                    break;
                }
                case 1: {
                    c8 = '|';
                    break;
                }
                case 2: {
                    c8 = 'o';
                    break;
                }
                case 3: {
                    c8 = 'h';
                    break;
                }
                default: {
                    c8 = '>';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = " =<;\u001e".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'p';
                    break;
                }
                case 1: {
                    c10 = '|';
                    break;
                }
                case 2: {
                    c10 = 'o';
                    break;
                }
                case 3: {
                    c10 = 'h';
                    break;
                }
                default: {
                    c10 = '>';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = ">5,#\u001e".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'p';
                    break;
                }
                case 1: {
                    c12 = '|';
                    break;
                }
                case 2: {
                    c12 = 'o';
                    break;
                }
                case 3: {
                    c12 = 'h';
                    break;
                }
                default: {
                    c12 = '>';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "!)&<\u001eJ".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'p';
                    break;
                }
                case 1: {
                    c14 = '|';
                    break;
                }
                case 2: {
                    c14 = 'o';
                    break;
                }
                case 3: {
                    c14 = 'h';
                    break;
                }
                default: {
                    c14 = '>';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "z\\#\u0001M\u0011\u0012\u001cHv\u0011\b\u000e\u001bW".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'p';
                    break;
                }
                case 1: {
                    c16 = '|';
                    break;
                }
                case 2: {
                    c16 = 'o';
                    break;
                }
                case 3: {
                    c16 = 'h';
                    break;
                }
                default: {
                    c16 = '>';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "2\u0010\u0000\u000bU\u0015\u0018O,Q\u001d\u001d\u0006\u0006M".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'p';
                    break;
                }
                case 1: {
                    c18 = '|';
                    break;
                }
                case 2: {
                    c18 = 'o';
                    break;
                }
                case 3: {
                    c18 = 'h';
                    break;
                }
                default: {
                    c18 = '>';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "XNF".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'p';
                    break;
                }
                case 1: {
                    c20 = '|';
                    break;
                }
                case 2: {
                    c20 = 'o';
                    break;
                }
                case 3: {
                    c20 = 'h';
                    break;
                }
                default: {
                    c20 = '>';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        mb.z = z;
    }
}
