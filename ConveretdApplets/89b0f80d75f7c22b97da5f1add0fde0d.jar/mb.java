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
                if (a.Bb != null) {
                    try {
                        this.a.Eb = true;
                        final esChat a2 = this.a;
                        Label_0116: {
                            if (m == 0) {
                                if (a2.Nb == null) {
                                    this.a.a(mb.z[9] + this.a.sb, 1);
                                    if (m == 0) {
                                        break Label_0116;
                                    }
                                }
                                final esChat a3 = this.a;
                            }
                            a2.a(mb.z[9] + this.a.Nb, 1);
                        }
                        Thread.sleep(3000L);
                    }
                    catch (Exception ex) {}
                    final esChat a4 = this.a;
                    if (m == 0) {
                        if (a4.q != null) {
                            this.a.a(String.valueOf(this.a.a(11, "", "", "")) + mb.z[2]);
                        }
                        final esChat a5 = this.a;
                    }
                    a4.a(this.a.Bb, null);
                }
                this.a.Ob.b.a();
                this.a.B = this.a.c(mb.z[4], mb.z[5]);
                final esChat a6 = this.a;
            }
            final String s = a.B[0];
            if (m == 0) {
                if (s == null) {
                    this.a.B = this.a.c(mb.z[4], mb.z[7]);
                    this.a.C = true;
                }
                this.a.getDocumentBase().toString();
            }
            final String s2 = s;
            String ec;
            final String s3 = ec = this.a.B[0];
            if (m == 0) {
                if (s3 != null) {
                    final boolean c = this.a.C;
                    if (m == 0 && c) {
                        int n = 0;
                        while (true) {
                            Label_0399: {
                                if (m == 0) {
                                    break Label_0399;
                                }
                                this.a.B[n] = this.a.B[n].substring(this.a.B[n].indexOf("=") + 1);
                                if (s2.toLowerCase().indexOf(this.a.B[n].toLowerCase()) > 0) {
                                    return;
                                }
                                ++n;
                            }
                            if (this.a.B[n] != null) {
                                continue;
                            }
                            break;
                        }
                    }
                    else {
                        int n2 = c ? 1 : 0;
                        int n3 = 0;
                        int n5 = 0;
                        while (true) {
                            while (true) {
                                Label_0492: {
                                    if (m == 0) {
                                        break Label_0492;
                                    }
                                    this.a.B[n3] = this.a.B[n3].substring(this.a.B[n3].indexOf("=") + 1);
                                    final int index;
                                    int n4 = index = s2.toLowerCase().indexOf(this.a.B[n3].toLowerCase());
                                    Label_0489: {
                                        if (m == 0) {
                                            if (n5 <= 0) {
                                                break Label_0489;
                                            }
                                            n4 = 1;
                                        }
                                        n2 = n4;
                                    }
                                    ++n3;
                                }
                                if (this.a.B[n3] != null) {
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
                final String q;
                ec = (q = this.a.q);
            }
            if (m == 0) {
                if (s3 == null) {
                    this.a.a(mb.z[8]);
                    return;
                }
                this.a.a("\n" + this.a.a(7, "", "", ""));
                this.a.a(this.a.a(8, "", "", ""));
                this.a.Eb = false;
                ec = this.a.ec;
            }
            this.a.Bb = new Socket(this.a.q, Integer.parseInt(ec));
            try {
                this.a.Rb = new BufferedReader(new InputStreamReader(this.a.Bb.getInputStream(), this.a.T));
                this.a.Sb = new BufferedWriter(new OutputStreamWriter(this.a.Bb.getOutputStream(), this.a.T));
            }
            catch (Exception ex2) {
                this.a.Rb = new BufferedReader(new InputStreamReader(this.a.Bb.getInputStream()));
                this.a.Sb = new BufferedWriter(new OutputStreamWriter(this.a.Bb.getOutputStream()));
            }
            new pb(this.a).start();
            this.a.dc = 0;
            this.a.a(mb.z[0] + this.a.W, 1);
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex3) {}
            this.a.a(String.valueOf(this.a.fb) + " " + this.a.p + mb.z[1] + this.a.o, 1);
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex4) {}
            this.a.a(mb.z[3] + this.a.m, 1);
        }
        catch (Exception ex5) {
            this.a.a(String.valueOf(this.a.a(10, "", "", "")) + mb.z[6] + this.a.fc + ")");
            final esChat a7 = this.a;
            ++a7.fc;
            try {
                Thread.sleep(2000L);
            }
            catch (Exception ex6) {}
            final esChat a8 = this.a;
            if (m == 0) {
                if (a8.fc >= 4) {
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
        final char[] charArray = "OuyQR".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u001f';
                    break;
                }
                case 1: {
                    c2 = '4';
                    break;
                }
                case 2: {
                    c2 = '*';
                    break;
                }
                case 3: {
                    c2 = '\u0002';
                    break;
                }
                default: {
                    c2 = 'r';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "?XEa\u0013s\\Eq\u0006?CClA-\u0014\u0010".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u001f';
                    break;
                }
                case 1: {
                    c4 = '4';
                    break;
                }
                case 2: {
                    c4 = '*';
                    break;
                }
                case 3: {
                    c4 = '\u0002';
                    break;
                }
                default: {
                    c4 = 'r';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "7\u0006\u0003".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u001f';
                    break;
                }
                case 1: {
                    c6 = '4';
                    break;
                }
                case 2: {
                    c6 = '*';
                    break;
                }
                case 3: {
                    c6 = '\u0002';
                    break;
                }
                default: {
                    c6 = 'r';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "q]IiR".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u001f';
                    break;
                }
                case 1: {
                    c8 = '4';
                    break;
                }
                case 2: {
                    c8 = '*';
                    break;
                }
                case 3: {
                    c8 = '\u0002';
                    break;
                }
                default: {
                    c8 = 'r';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "lQ^v\u001bqSY,\u0006g@".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u001f';
                    break;
                }
                case 1: {
                    c10 = '4';
                    break;
                }
                case 2: {
                    c10 = '*';
                    break;
                }
                case 3: {
                    c10 = '\u0002';
                    break;
                }
                default: {
                    c10 = 'r';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "^XFm\u0005zP\nF\u001drUCl\u0001".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u001f';
                    break;
                }
                case 1: {
                    c12 = '4';
                    break;
                }
                case 2: {
                    c12 = '*';
                    break;
                }
                case 3: {
                    c12 = '\u0002';
                    break;
                }
                default: {
                    c12 = 'r';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "?\u001c".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u001f';
                    break;
                }
                case 1: {
                    c14 = '4';
                    break;
                }
                case 2: {
                    c14 = '*';
                    break;
                }
                case 3: {
                    c14 = '\u0002';
                    break;
                }
                default: {
                    c14 = 'r';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "]XEa\u0019zP\nF\u001drUCl\u0001".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u001f';
                    break;
                }
                case 1: {
                    c16 = '4';
                    break;
                }
                case 2: {
                    c16 = '*';
                    break;
                }
                case 3: {
                    c16 = '\u0002';
                    break;
                }
                default: {
                    c16 = 'r';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0015\u0014fk\u0001~ZY\":~@Kq\u001b".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u001f';
                    break;
                }
                case 1: {
                    c18 = '4';
                    break;
                }
                case 2: {
                    c18 = '*';
                    break;
                }
                case 3: {
                    c18 = '\u0002';
                    break;
                }
                default: {
                    c18 = 'r';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "NacVR%".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u001f';
                    break;
                }
                case 1: {
                    c20 = '4';
                    break;
                }
                case 2: {
                    c20 = '*';
                    break;
                }
                case 3: {
                    c20 = '\u0002';
                    break;
                }
                default: {
                    c20 = 'r';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        mb.z = z;
    }
}
