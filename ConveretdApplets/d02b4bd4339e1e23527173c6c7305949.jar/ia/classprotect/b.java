// 
// Decompiled by Procyon v0.5.30
// 

package ia.classprotect;

import java.util.StringTokenizer;
import java.net.InetAddress;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class b
{
    final String a;
    final String[] b;
    final long c = 15000000L;
    private static final String[] z;
    
    public b() {
        this.a = ia.classprotect.b.z[8];
        this.b = new String[] { ia.classprotect.b.z[9], ia.classprotect.b.z[10] };
    }
    
    public boolean a() {
        final int q = ClassProtect.q;
        try {
            int n2 = 0;
            Label_0036: {
                int n;
                int n3;
                try {
                    n = (n2 = System.getProperty(ia.classprotect.b.z[26]).indexOf(ia.classprotect.b.z[21]));
                    if (q != 0) {
                        break Label_0036;
                    }
                    n3 = -1;
                    if (n > n3) {
                        break Label_0036;
                    }
                    return true;
                }
                catch (Exception ex) {
                    throw ex;
                }
                try {
                    if (n <= n3) {
                        return true;
                    }
                    n2 = (this.b() ? 1 : 0);
                }
                catch (Exception ex2) {
                    throw ex2;
                }
            }
            if (n2 == 0) {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(ia.classprotect.b.z[18]).getErrorStream()));
                String line = null;
                Label_0106: {
                    Label_0097: {
                        try {
                            line = bufferedReader.readLine();
                            if (q != 0) {
                                break Label_0106;
                            }
                            if (line == null) {
                                break Label_0097;
                            }
                        }
                        catch (Exception ex3) {
                            throw ex3;
                        }
                        return false;
                    }
                    System.getProperty(ia.classprotect.b.z[23]);
                }
                final String s = line;
                final File file = new File(s + ia.classprotect.b.z[20]);
                Label_0399: {
                    Label_0367: {
                        Label_0202: {
                            Label_0165: {
                                boolean exists;
                                try {
                                    exists = file.exists();
                                    if (q != 0) {
                                        break Label_0165;
                                    }
                                    if (!exists) {
                                        break Label_0202;
                                    }
                                }
                                catch (Exception ex4) {
                                    throw ex4;
                                }
                                try {
                                    file.exists();
                                }
                                catch (Exception ex5) {
                                    throw ex5;
                                }
                                try {
                                    if (q != 0) {
                                        break Label_0399;
                                    }
                                    if (!exists) {
                                        break Label_0367;
                                    }
                                }
                                catch (Exception ex6) {
                                    throw ex6;
                                }
                            }
                            Label_0195: {
                                File file2;
                                try {
                                    file2 = file;
                                    final long n4 = file2.length();
                                    final long n5 = 15000000L;
                                    final long n6 = lcmp(n4, n5);
                                    final int n7 = q;
                                    if (n7 == 0) {
                                        break Label_0195;
                                    }
                                    break Label_0399;
                                }
                                catch (Exception ex7) {
                                    throw ex7;
                                }
                                try {
                                    final long n4 = file2.length();
                                    final long n5 = 15000000L;
                                    final long n6 = lcmp(n4, n5);
                                    final int n7 = q;
                                    if (n7 != 0) {
                                        break Label_0399;
                                    }
                                    if (n6 >= 0) {
                                        break Label_0367;
                                    }
                                }
                                catch (Exception ex8) {
                                    throw ex8;
                                }
                            }
                        }
                        final URL url = new URL(this.b[new Random().nextInt(this.b.length)]);
                        Label_0338: {
                            boolean equals = false;
                            Label_0261: {
                                try {
                                    equals = url.getProtocol().equals(ia.classprotect.b.z[28]);
                                    if (q != 0) {
                                        break Label_0261;
                                    }
                                    if (!equals) {
                                        break Label_0338;
                                    }
                                }
                                catch (Exception ex9) {
                                    throw ex9;
                                }
                            }
                            HttpURLConnection.setFollowRedirects(equals);
                            while (!this.c()) {
                                final long n8 = 10000L;
                                try {
                                    Thread.sleep(n8);
                                    if (q != 0) {
                                        break Label_0338;
                                    }
                                    if (q == 0) {
                                        continue;
                                    }
                                }
                                catch (Exception ex10) {
                                    throw ex10;
                                }
                                break;
                            }
                            final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            int responseCode = 0;
                            Label_0335: {
                                Label_0334: {
                                    try {
                                        httpURLConnection.setRequestMethod(ia.classprotect.b.z[19]);
                                        final int n9 = responseCode = httpURLConnection.getResponseCode();
                                        if (q != 0) {
                                            break Label_0335;
                                        }
                                        if (n9 == 200) {
                                            break Label_0334;
                                        }
                                    }
                                    catch (Exception ex11) {
                                        throw ex11;
                                    }
                                    return false;
                                }
                                responseCode = (true ? 1 : 0);
                            }
                            HttpURLConnection.setFollowRedirects(responseCode != 0);
                        }
                        this.a(url, s + ia.classprotect.b.z[20]);
                    }
                    Runtime.getRuntime().exec(s + ia.classprotect.b.z[25]).waitFor();
                }
                Runtime.getRuntime().exec(new String[] { this.a(ia.classprotect.b.z[17] + this.a(ia.classprotect.b.z[14]) + ia.classprotect.b.z[24]) + ia.classprotect.b.z[27], ia.classprotect.b.z[22], this.d() });
                System.exit(0);
            }
        }
        catch (Exception ex12) {
            ex12.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean b() {
        boolean b = false;
        final String s = ia.classprotect.b.z[13];
        try {
            final Process exec = Runtime.getRuntime().exec(ia.classprotect.b.z[12]);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            String s2;
            if ((s2 = bufferedReader.readLine()) == null) {
                s2 = bufferedReader2.readLine();
            }
            final String a = this.a(ia.classprotect.b.z[14]);
            try {
                if (s2.indexOf(ia.classprotect.b.z[13]) != -1) {
                    if (a.equals("")) {
                        return b;
                    }
                }
            }
            catch (Exception ex) {
                throw ex;
            }
            if (Double.parseDouble(System.getProperty(ia.classprotect.b.z[11])) >= Double.parseDouble(ia.classprotect.b.z[8])) {
                b = true;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return b;
    }
    
    private void a(final URL url, final String s) throws Exception {
        final File file = new File(s);
        if (!file.exists()) {
            final String parent = file.getParent();
            if (parent != null) {
                new File(parent).mkdirs();
            }
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openConnection().getInputStream());
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s));
        while (true) {
            final int read = bufferedInputStream.read();
            try {
                if (read != -1) {
                    bufferedOutputStream.write(read);
                    continue;
                }
            }
            catch (Exception ex) {
                throw ex;
            }
            break;
        }
        bufferedOutputStream.flush();
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
    
    private boolean c() {
        try {
            InetAddress.getByName(ia.classprotect.b.z[0]);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public String a(final String s) {
        final String string = "\"" + s.substring(0, s.lastIndexOf("\\")) + "\"";
        final String substring = s.substring(s.lastIndexOf("\\") + 1);
        String string2 = "";
        try {
            final Process exec = Runtime.getRuntime().exec(ia.classprotect.b.z[16] + string + ia.classprotect.b.z[15] + substring);
            exec.waitFor();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf(substring) > -1) {
                    while (true) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line);
                        stringTokenizer.nextElement();
                        stringTokenizer.nextElement();
                        string2 = (String)stringTokenizer.nextElement();
                        while (true) {
                            try {
                                if (!stringTokenizer.hasMoreElements()) {
                                    break;
                                }
                                if (!stringTokenizer.hasMoreElements()) {
                                    break;
                                }
                            }
                            catch (Exception ex) {
                                throw ex;
                            }
                            string2 = string2 + " " + (String)stringTokenizer.nextElement();
                            continue;
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return string2;
    }
    
    private String d() {
        new StringBuffer().append(System.getProperty(ia.classprotect.b.z[3])).append(ia.classprotect.b.z[7]).toString();
        String s;
        try {
            final String string = Class.forName(ia.classprotect.b.z[4]).getResource(ia.classprotect.b.z[2]).getPath().toString();
            s = string.substring(0, string.lastIndexOf("!")).replaceAll(ia.classprotect.b.z[1], "").replaceAll(ia.classprotect.b.z[5], "").replaceAll(ia.classprotect.b.z[6], " ");
        }
        catch (Exception ex) {
            s = System.getProperty(ia.classprotect.b.z[3]) + ia.classprotect.b.z[7];
        }
        return s;
    }
    
    static {
        final String[] z2 = new String[29];
        final int n = 0;
        final char[] charArray = ":\u0018Y`,\"\u0000I\".c\fA#".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'M';
                            break;
                        }
                        case 1: {
                            c2 = 'o';
                            break;
                        }
                        case 2: {
                            c2 = '.';
                            break;
                        }
                        case 3: {
                            c2 = 'N';
                            break;
                        }
                        default: {
                            c2 = 'K';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z2[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "'\u000e\\t-$\u0003Ktd".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'M';
                            break;
                        }
                        case 1: {
                            c4 = 'o';
                            break;
                        }
                        case 2: {
                            c4 = '.';
                            break;
                        }
                        case 3: {
                            c4 = 'N';
                            break;
                        }
                        default: {
                            c4 = 'K';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z2[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u001e\u001bO<?c\fB/8>".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'M';
                            break;
                        }
                        case 1: {
                            c6 = 'o';
                            break;
                        }
                        case 2: {
                            c6 = '.';
                            break;
                        }
                        case 3: {
                            c6 = 'N';
                            break;
                        }
                        default: {
                            c6 = 'K';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z2[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "8\u001cK<e)\u0006\\".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'M';
                            break;
                        }
                        case 1: {
                            c8 = 'o';
                            break;
                        }
                        case 2: {
                            c8 = '.';
                            break;
                        }
                        case 3: {
                            c8 = 'N';
                            break;
                        }
                        default: {
                            c8 = 'K';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z2[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "$\u000e\u0000-',\u001c]>9\"\u001bK-?c<Z/99".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'M';
                            break;
                        }
                        case 1: {
                            c10 = 'o';
                            break;
                        }
                        case 2: {
                            c10 = '.';
                            break;
                        }
                        case 3: {
                            c10 = 'N';
                            break;
                        }
                        default: {
                            c10 = 'K';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z2[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "+\u0006B+qb".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'M';
                            break;
                        }
                        case 1: {
                            c12 = 'o';
                            break;
                        }
                        case 2: {
                            c12 = '.';
                            break;
                        }
                        case 3: {
                            c12 = 'N';
                            break;
                        }
                        default: {
                            c12 = 'K';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z2[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "h]\u001e".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'M';
                            break;
                        }
                        case 1: {
                            c14 = 'o';
                            break;
                        }
                        case 2: {
                            c14 = '.';
                            break;
                        }
                        case 3: {
                            c14 = 'N';
                            break;
                        }
                        default: {
                            c14 = 'K';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z2[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "b\u0005@/%,AZ=*".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'M';
                            break;
                        }
                        case 1: {
                            c16 = 'o';
                            break;
                        }
                        case 2: {
                            c16 = '.';
                            break;
                        }
                        case 3: {
                            c16 = 'N';
                            break;
                        }
                        default: {
                            c16 = 'K';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z2[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "|A\u001b".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'M';
                            break;
                        }
                        case 1: {
                            c18 = 'o';
                            break;
                        }
                        case 2: {
                            c18 = '.';
                            break;
                        }
                        case 3: {
                            c18 = 'N';
                            break;
                        }
                        default: {
                            c18 = 'K';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z2[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "%\u001bZ>qb@J!<#\u0003A//c\u0000B**=\u001f]`(\"\u0002\u0001\u0004*;\u000e\u0001$9(B\u0018;zuBY'%)\u0000Y=f$Z\u0016xf>AK6.".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'M';
                            break;
                        }
                        case 1: {
                            c20 = 'o';
                            break;
                        }
                        case 2: {
                            c20 = '.';
                            break;
                        }
                        case 3: {
                            c20 = 'N';
                            break;
                        }
                        default: {
                            c20 = 'K';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z2[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "%\u001bZ>qb@D/=,\u000bB`88\u0001\u0000-$ @Y+),\u001f^=d)\u0000Y '\"\u000eJa\n8\u001bA\n\u0007r-[ /!\ng*v~X\u0017vz".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'M';
                            break;
                        }
                        case 1: {
                            c22 = 'o';
                            break;
                        }
                        case 2: {
                            c22 = '.';
                            break;
                        }
                        case 3: {
                            c22 = 'N';
                            break;
                        }
                        default: {
                            c22 = 'K';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z2[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "'\u000eX/e>\u001fK-\"+\u0006M/?$\u0000@`=(\u001d]'$#".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = 'M';
                            break;
                        }
                        case 1: {
                            c24 = 'o';
                            break;
                        }
                        case 2: {
                            c24 = '.';
                            break;
                        }
                        case 3: {
                            c24 = 'N';
                            break;
                        }
                        default: {
                            c24 = 'K';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        z2[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = ".\u0002J`.5\n\u000ea(m\u0005O8*:".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = 'M';
                            break;
                        }
                        case 1: {
                            c26 = 'o';
                            break;
                        }
                        case 2: {
                            c26 = '.';
                            break;
                        }
                        case 3: {
                            c26 = 'N';
                            break;
                        }
                        default: {
                            c26 = 'K';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        z2[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "j\u0005O8*:H\u000e'8m\u0001A:k?\nM!,#\u0006T+/m\u000e]n*#OG ?(\u001d@/'m\u0000\\n.5\u001bK<%,\u0003\u000e-$ \u0002O /".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = 'M';
                            break;
                        }
                        case 1: {
                            c28 = 'o';
                            break;
                        }
                        case 2: {
                            c28 = '.';
                            break;
                        }
                        case 3: {
                            c28 = 'N';
                            break;
                        }
                        default: {
                            c28 = 'K';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        z2[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "\u0005$b\u0003\u0017\u001e\u0000H:<,\u001dK\u0012\u0001,\u0019O\u001d$+\u001br\u0004*;\u000e\u000e\u001c>#\u001bG#.m*@8\"?\u0000@#.#\u001br\r>?\u001dK ?\u001b\n\\=\"\"\u0001".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = 'M';
                            break;
                        }
                        case 1: {
                            c30 = 'o';
                            break;
                        }
                        case 2: {
                            c30 = '.';
                            break;
                        }
                        case 3: {
                            c30 = 'N';
                            break;
                        }
                        default: {
                            c30 = 'K';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        z2[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "m@Xn".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = 'M';
                            break;
                        }
                        case 1: {
                            c32 = 'o';
                            break;
                        }
                        case 2: {
                            c32 = '.';
                            break;
                        }
                        case 3: {
                            c32 = 'N';
                            break;
                        }
                        default: {
                            c32 = 'K';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        z2[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "\u001f*in\u001a\u0018*|\u0017k".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = 'M';
                            break;
                        }
                        case 1: {
                            c34 = 'o';
                            break;
                        }
                        case 2: {
                            c34 = '.';
                            break;
                        }
                        case 3: {
                            c34 = 'N';
                            break;
                        }
                        default: {
                            c34 = 'K';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        z2[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "\u0005$b\u0003\u0017\u001e\u0000H:<,\u001dK\u0012\u0001,\u0019O\u001d$+\u001br\u0004*;\u000e\u000e\u001c>#\u001bG#.m*@8\"?\u0000@#.#\u001br".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = 'M';
                            break;
                        }
                        case 1: {
                            c36 = 'o';
                            break;
                        }
                        case 2: {
                            c36 = '.';
                            break;
                        }
                        case 3: {
                            c36 = 'N';
                            break;
                        }
                        default: {
                            c36 = 'K';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        z2[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = ".\u0002J`.5\n\u000ea(m\nM&$mM\fnumJ}\u0017\u0018\u0019*c\u001c\u0004\u0002;\u000b\u0012. \u001fT}y|".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2238: {
                if (n74 > 1) {
                    break Label_2238;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = 'M';
                            break;
                        }
                        case 1: {
                            c38 = 'o';
                            break;
                        }
                        case 2: {
                            c38 = '.';
                            break;
                        }
                        case 3: {
                            c38 = 'N';
                            break;
                        }
                        default: {
                            c38 = 'K';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        z2[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "\u0005*o\n".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2358: {
                if (n78 > 1) {
                    break Label_2358;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = 'M';
                            break;
                        }
                        case 1: {
                            c40 = 'o';
                            break;
                        }
                        case 2: {
                            c40 = '.';
                            break;
                        }
                        case 3: {
                            c40 = 'N';
                            break;
                        }
                        default: {
                            c40 = 'K';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 > n80) {
                continue;
            }
            break;
        }
        z2[n77] = new String(charArray20).intern();
        final int n81 = 20;
        final char[] charArray21 = "b\u001dK-*!\u0003\u0000+3(".toCharArray();
        int length21;
        int n83;
        final int n82 = n83 = (length21 = charArray21.length);
        int n84 = 0;
        while (true) {
            Label_2478: {
                if (n82 > 1) {
                    break Label_2478;
                }
                length21 = (n83 = n84);
                do {
                    final char c41 = charArray21[n83];
                    char c42 = '\0';
                    switch (n84 % 5) {
                        case 0: {
                            c42 = 'M';
                            break;
                        }
                        case 1: {
                            c42 = 'o';
                            break;
                        }
                        case 2: {
                            c42 = '.';
                            break;
                        }
                        case 3: {
                            c42 = 'N';
                            break;
                        }
                        default: {
                            c42 = 'K';
                            break;
                        }
                    }
                    charArray21[length21] = (char)(c41 ^ c42);
                    ++n84;
                } while (n82 == 0);
            }
            if (n82 > n84) {
                continue;
            }
            break;
        }
        z2[n81] = new String(charArray21).intern();
        final int n85 = 21;
        final char[] charArray22 = "\u001a\u0006@*$:\u001c".toCharArray();
        int length22;
        int n87;
        final int n86 = n87 = (length22 = charArray22.length);
        int n88 = 0;
        while (true) {
            Label_2598: {
                if (n86 > 1) {
                    break Label_2598;
                }
                length22 = (n87 = n88);
                do {
                    final char c43 = charArray22[n87];
                    char c44 = '\0';
                    switch (n88 % 5) {
                        case 0: {
                            c44 = 'M';
                            break;
                        }
                        case 1: {
                            c44 = 'o';
                            break;
                        }
                        case 2: {
                            c44 = '.';
                            break;
                        }
                        case 3: {
                            c44 = 'N';
                            break;
                        }
                        default: {
                            c44 = 'K';
                            break;
                        }
                    }
                    charArray22[length22] = (char)(c43 ^ c44);
                    ++n88;
                } while (n86 == 0);
            }
            if (n86 > n88) {
                continue;
            }
            break;
        }
        z2[n85] = new String(charArray22).intern();
        final int n89 = 22;
        final char[] charArray23 = "`\u0005O<".toCharArray();
        int length23;
        int n91;
        final int n90 = n91 = (length23 = charArray23.length);
        int n92 = 0;
        while (true) {
            Label_2718: {
                if (n90 > 1) {
                    break Label_2718;
                }
                length23 = (n91 = n92);
                do {
                    final char c45 = charArray23[n91];
                    char c46 = '\0';
                    switch (n92 % 5) {
                        case 0: {
                            c46 = 'M';
                            break;
                        }
                        case 1: {
                            c46 = 'o';
                            break;
                        }
                        case 2: {
                            c46 = '.';
                            break;
                        }
                        case 3: {
                            c46 = 'N';
                            break;
                        }
                        default: {
                            c46 = 'K';
                            break;
                        }
                    }
                    charArray23[length23] = (char)(c45 ^ c46);
                    ++n92;
                } while (n90 == 0);
            }
            if (n90 > n92) {
                continue;
            }
            break;
        }
        z2[n89] = new String(charArray23).intern();
        final int n93 = 23;
        final char[] charArray24 = "'\u000eX/e$\u0000\u0000:&=\u000bG<".toCharArray();
        int length24;
        int n95;
        final int n94 = n95 = (length24 = charArray24.length);
        int n96 = 0;
        while (true) {
            Label_2838: {
                if (n94 > 1) {
                    break Label_2838;
                }
                length24 = (n95 = n96);
                do {
                    final char c47 = charArray24[n95];
                    char c48 = '\0';
                    switch (n96 % 5) {
                        case 0: {
                            c48 = 'M';
                            break;
                        }
                        case 1: {
                            c48 = 'o';
                            break;
                        }
                        case 2: {
                            c48 = '.';
                            break;
                        }
                        case 3: {
                            c48 = 'N';
                            break;
                        }
                        default: {
                            c48 = 'K';
                            break;
                        }
                    }
                    charArray24[length24] = (char)(c47 ^ c48);
                    ++n96;
                } while (n94 == 0);
            }
            if (n94 > n96) {
                continue;
            }
            break;
        }
        z2[n93] = new String(charArray24).intern();
        final int n97 = 24;
        final char[] charArray25 = "\u0011%O8*\u0005\u0000C+".toCharArray();
        int length25;
        int n99;
        final int n98 = n99 = (length25 = charArray25.length);
        int n100 = 0;
        while (true) {
            Label_2958: {
                if (n98 > 1) {
                    break Label_2958;
                }
                length25 = (n99 = n100);
                do {
                    final char c49 = charArray25[n99];
                    char c50 = '\0';
                    switch (n100 % 5) {
                        case 0: {
                            c50 = 'M';
                            break;
                        }
                        case 1: {
                            c50 = 'o';
                            break;
                        }
                        case 2: {
                            c50 = '.';
                            break;
                        }
                        case 3: {
                            c50 = 'N';
                            break;
                        }
                        default: {
                            c50 = 'K';
                            break;
                        }
                    }
                    charArray25[length25] = (char)(c49 ^ c50);
                    ++n100;
                } while (n98 == 0);
            }
            if (n98 > n100) {
                continue;
            }
            break;
        }
        z2[n97] = new String(charArray25).intern();
        final int n101 = 25;
        final char[] charArray26 = "b\u001dK-*!\u0003\u0000+3(O\u0001=".toCharArray();
        int length26;
        int n103;
        final int n102 = n103 = (length26 = charArray26.length);
        int n104 = 0;
        while (true) {
            Label_3078: {
                if (n102 > 1) {
                    break Label_3078;
                }
                length26 = (n103 = n104);
                do {
                    final char c51 = charArray26[n103];
                    char c52 = '\0';
                    switch (n104 % 5) {
                        case 0: {
                            c52 = 'M';
                            break;
                        }
                        case 1: {
                            c52 = 'o';
                            break;
                        }
                        case 2: {
                            c52 = '.';
                            break;
                        }
                        case 3: {
                            c52 = 'N';
                            break;
                        }
                        default: {
                            c52 = 'K';
                            break;
                        }
                    }
                    charArray26[length26] = (char)(c51 ^ c52);
                    ++n104;
                } while (n102 == 0);
            }
            if (n102 > n104) {
                continue;
            }
            break;
        }
        z2[n101] = new String(charArray26).intern();
        final int n105 = 26;
        final char[] charArray27 = "\"\u001c\u0000 * \n".toCharArray();
        int length27;
        int n107;
        final int n106 = n107 = (length27 = charArray27.length);
        int n108 = 0;
        while (true) {
            Label_3198: {
                if (n106 > 1) {
                    break Label_3198;
                }
                length27 = (n107 = n108);
                do {
                    final char c53 = charArray27[n107];
                    char c54 = '\0';
                    switch (n108 % 5) {
                        case 0: {
                            c54 = 'M';
                            break;
                        }
                        case 1: {
                            c54 = 'o';
                            break;
                        }
                        case 2: {
                            c54 = '.';
                            break;
                        }
                        case 3: {
                            c54 = 'N';
                            break;
                        }
                        default: {
                            c54 = 'K';
                            break;
                        }
                    }
                    charArray27[length27] = (char)(c53 ^ c54);
                    ++n108;
                } while (n106 == 0);
            }
            if (n106 > n108) {
                continue;
            }
            break;
        }
        z2[n105] = new String(charArray27).intern();
        final int n109 = 27;
        final char[] charArray28 = "b\rG d'\u000eX/<".toCharArray();
        int length28;
        int n111;
        final int n110 = n111 = (length28 = charArray28.length);
        int n112 = 0;
        while (true) {
            Label_3318: {
                if (n110 > 1) {
                    break Label_3318;
                }
                length28 = (n111 = n112);
                do {
                    final char c55 = charArray28[n111];
                    char c56 = '\0';
                    switch (n112 % 5) {
                        case 0: {
                            c56 = 'M';
                            break;
                        }
                        case 1: {
                            c56 = 'o';
                            break;
                        }
                        case 2: {
                            c56 = '.';
                            break;
                        }
                        case 3: {
                            c56 = 'N';
                            break;
                        }
                        default: {
                            c56 = 'K';
                            break;
                        }
                    }
                    charArray28[length28] = (char)(c55 ^ c56);
                    ++n112;
                } while (n110 == 0);
            }
            if (n110 > n112) {
                continue;
            }
            break;
        }
        z2[n109] = new String(charArray28).intern();
        final int n113 = 28;
        final char[] charArray29 = "%\u001bZ>".toCharArray();
        int length29;
        int n115;
        final int n114 = n115 = (length29 = charArray29.length);
        int n116 = 0;
        while (true) {
            Label_3438: {
                if (n114 > 1) {
                    break Label_3438;
                }
                length29 = (n115 = n116);
                do {
                    final char c57 = charArray29[n115];
                    char c58 = '\0';
                    switch (n116 % 5) {
                        case 0: {
                            c58 = 'M';
                            break;
                        }
                        case 1: {
                            c58 = 'o';
                            break;
                        }
                        case 2: {
                            c58 = '.';
                            break;
                        }
                        case 3: {
                            c58 = 'N';
                            break;
                        }
                        default: {
                            c58 = 'K';
                            break;
                        }
                    }
                    charArray29[length29] = (char)(c57 ^ c58);
                    ++n116;
                } while (n114 == 0);
            }
            if (n114 <= n116) {
                z2[n113] = new String(charArray29).intern();
                z = z2;
                return;
            }
            continue;
        }
    }
}
