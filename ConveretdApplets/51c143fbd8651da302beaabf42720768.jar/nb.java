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

class nb extends Thread
{
    private final esChat a;
    private static String[] z;
    
    nb(final esChat a) {
        this.a = a;
    }
    
    public void run() {
        final boolean r = d.r;
        try {
            final esChat a = this.a;
            if (!r) {
                if (a.Mb != null) {
                    try {
                        this.a.Pb = true;
                        final esChat a2 = this.a;
                        Label_0116: {
                            if (!r) {
                                if (a2.Yb == null) {
                                    this.a.a(nb.z[10] + this.a.Bb, 1);
                                    if (!r) {
                                        break Label_0116;
                                    }
                                }
                                final esChat a3 = this.a;
                            }
                            a2.a(nb.z[10] + this.a.Yb, 1);
                        }
                        Thread.sleep(3000L);
                    }
                    catch (Exception ex) {}
                    final esChat a4 = this.a;
                    if (!r) {
                        if (a4.r != null) {
                            this.a.a(String.valueOf(this.a.a(11, "", "", "")) + nb.z[0]);
                        }
                        final esChat a5 = this.a;
                    }
                    a4.a(this.a.Mb, null);
                }
                this.a.Zb.b.a();
                this.a.G = this.a.c(nb.z[9], nb.z[3]);
                final esChat a6 = this.a;
            }
            final String s = a.G[0];
            if (!r) {
                if (s == null) {
                    this.a.G = this.a.c(nb.z[9], nb.z[12]);
                    this.a.H = true;
                }
                this.a.getDocumentBase().toString();
            }
            final String s2 = s;
            String pc;
            final String s3 = pc = this.a.G[0];
            if (!r) {
                if (s3 != null) {
                    final boolean h = this.a.H;
                    if (!r && h) {
                        int n = 0;
                        while (true) {
                            Label_0401: {
                                if (!r) {
                                    break Label_0401;
                                }
                                this.a.G[n] = this.a.G[n].substring(this.a.G[n].indexOf("=") + 1);
                                if (s2.toLowerCase().indexOf(this.a.G[n].toLowerCase()) > 0) {
                                    return;
                                }
                                ++n;
                            }
                            if (this.a.G[n] != null) {
                                continue;
                            }
                            break;
                        }
                    }
                    else {
                        int n2 = h ? 1 : 0;
                        int n3 = 0;
                        int n5 = 0;
                        while (true) {
                            while (true) {
                                Label_0494: {
                                    if (!r) {
                                        break Label_0494;
                                    }
                                    this.a.G[n3] = this.a.G[n3].substring(this.a.G[n3].indexOf("=") + 1);
                                    final int index;
                                    int n4 = index = s2.toLowerCase().indexOf(this.a.G[n3].toLowerCase());
                                    Label_0491: {
                                        if (!r) {
                                            if (n5 <= 0) {
                                                break Label_0491;
                                            }
                                            n4 = 1;
                                        }
                                        n2 = n4;
                                    }
                                    ++n3;
                                }
                                if (this.a.G[n3] != null) {
                                    continue;
                                }
                                break;
                            }
                            int n4;
                            n5 = (n4 = n2);
                            if (r) {
                                continue;
                            }
                            break;
                        }
                        if (n5 == 0) {
                            return;
                        }
                    }
                }
                final String r2;
                pc = (r2 = this.a.r);
            }
            if (!r) {
                if (s3 == null) {
                    this.a.a(nb.z[2]);
                    return;
                }
                this.a.a("\n" + this.a.a(7, "", "", ""));
                this.a.a(this.a.a(8, "", "", ""));
                this.a.Pb = false;
                pc = this.a.pc;
            }
            this.a.Mb = new Socket(this.a.r, Integer.parseInt(pc));
            try {
                this.a.cc = new BufferedReader(new InputStreamReader(this.a.Mb.getInputStream(), this.a.ab));
                this.a.dc = new BufferedWriter(new OutputStreamWriter(this.a.Mb.getOutputStream(), this.a.ab));
            }
            catch (Exception ex2) {
                this.a.cc = new BufferedReader(new InputStreamReader(this.a.Mb.getInputStream()));
                this.a.dc = new BufferedWriter(new OutputStreamWriter(this.a.Mb.getOutputStream()));
            }
            new rb(this.a).start();
            this.a.oc = 0;
            this.a.a(nb.z[11] + this.a.db, 1);
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex3) {}
            this.a.a(String.valueOf(this.a.nb) + " " + this.a.q + nb.z[1] + this.a.p, 1);
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex4) {}
            final esChat a7 = this.a;
            if (!r) {
                if (a7.mb.equals(nb.z[5])) {
                    this.a.a(nb.z[7] + this.a.n, 1);
                    if (!r) {
                        return;
                    }
                }
                final esChat a8 = this.a;
            }
            a7.a(nb.z[4] + this.a.n + " " + this.a.o, 1);
        }
        catch (Exception ex5) {
            this.a.a(String.valueOf(this.a.a(11, "", "", "")) + nb.z[8]);
            this.a.a(String.valueOf(this.a.a(10, "", "", "")) + nb.z[6] + this.a.qc + ")");
            final esChat a9 = this.a;
            ++a9.qc;
            try {
                Thread.sleep(2000L);
            }
            catch (Exception ex6) {}
            final esChat a10 = this.a;
            if (!r) {
                if (a10.qc >= 4) {
                    return;
                }
                final esChat a11 = this.a;
            }
            a10.c();
        }
    }
    
    static {
        final String[] z = new String[13];
        final int n = 0;
        final char[] charArray = "\u0005\u0006*".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '-';
                    break;
                }
                case 1: {
                    c2 = '4';
                    break;
                }
                case 2: {
                    c2 = '\u0003';
                    break;
                }
                case 3: {
                    c2 = '4';
                    break;
                }
                default: {
                    c2 = '\u0004';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\rXlWeA\\lGp\rCjZ7\u001f\u00149".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '-';
                    break;
                }
                case 1: {
                    c4 = '4';
                    break;
                }
                case 2: {
                    c4 = '\u0003';
                    break;
                }
                case 3: {
                    c4 = '4';
                    break;
                }
                default: {
                    c4 = '\u0004';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "'\u0014O]wLZp\u0014LL@bGm".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '-';
                    break;
                }
                case 1: {
                    c6 = '4';
                    break;
                }
                case 2: {
                    c6 = '\u0003';
                    break;
                }
                case 3: {
                    c6 = '4';
                    break;
                }
                default: {
                    c6 = '\u0004';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "lXo[sHP#pk@UjZw".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '-';
                    break;
                }
                case 1: {
                    c8 = '4';
                    break;
                }
                case 2: {
                    c8 = '\u0003';
                    break;
                }
                case 3: {
                    c8 = '4';
                    break;
                }
                default: {
                    c8 = '\u0004';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "C]`_$".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '-';
                    break;
                }
                case 1: {
                    c10 = '4';
                    break;
                }
                case 2: {
                    c10 = '\u0003';
                    break;
                }
                case 3: {
                    c10 = '4';
                    break;
                }
                default: {
                    c10 = '\u0004';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "bRe".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '-';
                    break;
                }
                case 1: {
                    c12 = '4';
                    break;
                }
                case 2: {
                    c12 = '\u0003';
                    break;
                }
                case 3: {
                    c12 = '4';
                    break;
                }
                default: {
                    c12 = '\u0004';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\r\u001c".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '-';
                    break;
                }
                case 1: {
                    c14 = '4';
                    break;
                }
                case 2: {
                    c14 = '\u0003';
                    break;
                }
                case 3: {
                    c14 = '4';
                    break;
                }
                default: {
                    c14 = '\u0004';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "c}@\u007f$".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '-';
                    break;
                }
                case 1: {
                    c16 = '4';
                    break;
                }
                case 2: {
                    c16 = '\u0003';
                    break;
                }
                case 3: {
                    c16 = '4';
                    break;
                }
                default: {
                    c16 = '\u0004';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0005\u0007*".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '-';
                    break;
                }
                case 1: {
                    c18 = '4';
                    break;
                }
                case 2: {
                    c18 = '\u0003';
                    break;
                }
                case 3: {
                    c18 = '4';
                    break;
                }
                default: {
                    c18 = '\u0004';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "^Qw@mCSp\u001apU@".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '-';
                    break;
                }
                case 1: {
                    c20 = '4';
                    break;
                }
                case 2: {
                    c20 = '\u0003';
                    break;
                }
                case 3: {
                    c20 = '4';
                    break;
                }
                default: {
                    c20 = '\u0004';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "|aJ`$\u0017".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '-';
                    break;
                }
                case 1: {
                    c22 = '4';
                    break;
                }
                case 2: {
                    c22 = '\u0003';
                    break;
                }
                case 3: {
                    c22 = '4';
                    break;
                }
                default: {
                    c22 = '\u0004';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "}uPg$".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '-';
                    break;
                }
                case 1: {
                    c24 = '4';
                    break;
                }
                case 2: {
                    c24 = '\u0003';
                    break;
                }
                case 3: {
                    c24 = '4';
                    break;
                }
                default: {
                    c24 = '\u0004';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "oXlWoHP#pk@UjZw".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '-';
                    break;
                }
                case 1: {
                    c26 = '4';
                    break;
                }
                case 2: {
                    c26 = '\u0003';
                    break;
                }
                case 3: {
                    c26 = '4';
                    break;
                }
                default: {
                    c26 = '\u0004';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        nb.z = z;
    }
}
