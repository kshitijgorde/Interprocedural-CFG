// 
// Decompiled by Procyon v0.5.30
// 

package loader;

import java.awt.Dimension;
import java.awt.Image;
import java.applet.AudioClip;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Properties;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Font;
import java.applet.Applet;

public abstract class a extends Applet
{
    private static long a;
    private Applet b;
    private boolean c;
    private String[] d;
    private int e;
    private Font f;
    private boolean g;
    private long h;
    private String i;
    private String j;
    private b k;
    public static int l;
    private static String[] z;
    
    void a(final Applet b) {
        this.b = b;
    }
    
    protected Component a() {
        if (this.b != null) {
            return this.b;
        }
        return this;
    }
    
    public final synchronized void d() {
        this.a(loader.a.z[4], loader.a.z[5], loader.a.z[3]);
    }
    
    private final synchronized void a(final String s, final String s2, final String s3) {
        if (this.c) {
            return;
        }
        this.c = true;
        final String parameter = this.getParameter(s);
        this.e = 0;
        Label_0166: {
            if (parameter != null) {
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < parameter.length(); ++i) {
                    final char char1 = parameter.charAt(i);
                    if (char1 == '%') {
                        if (this.e >= this.d.length) {
                            break Label_0166;
                        }
                        this.d[this.e++] = sb.toString();
                        sb.setLength(0);
                    }
                    else {
                        sb.append(char1);
                    }
                }
                if (this.e < this.d.length) {
                    this.d[this.e++] = sb.toString();
                }
            }
        }
        this.i = this.getParameter(s2);
        this.j = this.getParameter(s3);
        if (this.j == null) {
            this.j = loader.a.z[13];
        }
        if (this.i != null) {
            this.h = System.currentTimeMillis();
            if (this == null) {
                throw null;
            }
            final c c = new c(this);
            if (this.b != null) {
                this.b.addMouseListener(c);
            }
            else {
                this.addMouseListener(c);
            }
        }
        this.g = false;
        this.b(this.getGraphics());
        this.repaint();
    }
    
    public final synchronized boolean b() {
        return this.c;
    }
    
    public static final boolean c() {
        try {
            if (System.getProperty(loader.a.z[8]).startsWith(loader.a.z[6])) {
                final String property = ((Properties)Class.forName(loader.a.z[9]).getMethod(loader.a.z[11], (Class<?>[])null).invoke(null, (Object[])null)).getProperty(loader.a.z[10]);
                if (property.length() == loader.a.z[7].length() && property.compareTo(loader.a.z[7]) <= 0) {
                    return false;
                }
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public final synchronized void e() {
        this.a(loader.a.z[2], loader.a.z[1], loader.a.z[0]);
    }
    
    protected final synchronized void a(final Graphics graphics) {
        if (!this.g) {
            this.g = true;
            this.b(graphics);
        }
    }
    
    protected final synchronized void b(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);
        if (this.e > 0) {
            graphics.setFont(this.f);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n = 0;
            int n2 = height / 2;
            final int n3 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
            for (int i = 0; i < this.e; ++i) {
                final int n4 = (width - fontMetrics.stringWidth(this.d[i])) / 2;
                if (n4 < n || i == 0) {
                    n = n4;
                }
                n2 -= n3 / 2;
            }
            graphics.setColor(Color.white);
            for (int j = 0; j < this.e; ++j) {
                graphics.drawString(this.d[j], (width - fontMetrics.stringWidth(this.d[j])) / 2, n2);
                n2 += n3;
            }
        }
    }
    
    private void f() {
        if (this.i != null) {
            if (System.currentTimeMillis() - this.h < loader.a.a) {
                return;
            }
            try {
                final URL url = new URL(this.getDocumentBase(), this.i);
                final AppletContext appletContext = this.getAppletContext();
                if (appletContext != null) {
                    appletContext.showDocument(url, this.j);
                    this.i = null;
                }
            }
            catch (Throwable t) {}
        }
    }
    
    public final AudioClip getAudioClip(final URL url) {
        if (this.b != null) {
            return this.b.getAudioClip(url);
        }
        return super.getAudioClip(url);
    }
    
    public final AppletContext getAppletContext() {
        if (this.b != null) {
            return this.b.getAppletContext();
        }
        return super.getAppletContext();
    }
    
    public final URL getCodeBase() {
        if (this.b != null) {
            return this.b.getCodeBase();
        }
        return super.getCodeBase();
    }
    
    public final URL getDocumentBase() {
        if (this.b != null) {
            return this.b.getDocumentBase();
        }
        return super.getDocumentBase();
    }
    
    public final Image getImage(final URL url) {
        if (this.b != null) {
            return this.b.getImage(url);
        }
        return super.getImage(url);
    }
    
    public final Image getImage(final URL url, final String s) {
        if (this.b != null) {
            return this.b.getImage(url, s);
        }
        return super.getImage(url, s);
    }
    
    public String getParameter(final String s) {
        if (this.b != null) {
            return this.b.getParameter(s);
        }
        return super.getParameter(s);
    }
    
    public final void requestFocus() {
        if (this.b != null) {
            this.b.requestFocus();
        }
        else {
            super.requestFocus();
        }
    }
    
    public final void repaint() {
        if (this.b != null) {
            this.b.repaint();
        }
        else {
            super.repaint();
        }
    }
    
    public final Dimension getSize() {
        if (this.b != null) {
            return this.b.getSize();
        }
        return super.getSize();
    }
    
    public final Graphics getGraphics() {
        if (this.b != null) {
            return this.b.getGraphics();
        }
        return super.getGraphics();
    }
    
    public synchronized void a(final String s, final Throwable t) {
    }
    
    static void a(final a a) {
        a.f();
    }
    
    public a() {
        this.b = null;
        this.c = false;
        this.d = new String[10];
        this.e = 0;
        this.f = new Font(loader.a.z[12], 0, 12);
        this.g = false;
        this.h = 0L;
        this.i = null;
        this.j = null;
        this.k = null;
    }
    
    static {
        final String[] z = new String[14];
        final int n = 0;
        final char[] charArray = "`\u0010\u001b1c+\u0014\u0004ped\u0010\u000e;e".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0005';
                            break;
                        }
                        case 1: {
                            c2 = 'b';
                            break;
                        }
                        case 2: {
                            c2 = 'i';
                            break;
                        }
                        case 3: {
                            c2 = '^';
                            break;
                        }
                        default: {
                            c2 = '\u0011';
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
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "`\u0010\u001b1c+\u0014\u0004pad\u0005\f".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0005';
                            break;
                        }
                        case 1: {
                            c4 = 'b';
                            break;
                        }
                        case 2: {
                            c4 = 'i';
                            break;
                        }
                        case 3: {
                            c4 = '^';
                            break;
                        }
                        default: {
                            c4 = '\u0011';
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
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "`\u0010\u001b1c+\u0014\u0004pe`\u001a\u001d".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0005';
                            break;
                        }
                        case 1: {
                            c6 = 'b';
                            break;
                        }
                        case 2: {
                            c6 = 'i';
                            break;
                        }
                        case 3: {
                            c6 = '^';
                            break;
                        }
                        default: {
                            c6 = '\u0011';
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
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "`\u0010\u001b1c+\u0016\b,v`\u0016".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0005';
                            break;
                        }
                        case 1: {
                            c8 = 'b';
                            break;
                        }
                        case 2: {
                            c8 = 'i';
                            break;
                        }
                        case 3: {
                            c8 = '^';
                            break;
                        }
                        default: {
                            c8 = '\u0011';
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
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "`\u0010\u001b1c+\u0016\f&e".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0005';
                            break;
                        }
                        case 1: {
                            c10 = 'b';
                            break;
                        }
                        case 2: {
                            c10 = 'i';
                            break;
                        }
                        case 3: {
                            c10 = '^';
                            break;
                        }
                        default: {
                            c10 = '\u0011';
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
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "`\u0010\u001b1cU\u0003\u000e;".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0677: {
                if (n22 > 1) {
                    break Label_0677;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0005';
                            break;
                        }
                        case 1: {
                            c12 = 'b';
                            break;
                        }
                        case 2: {
                            c12 = 'i';
                            break;
                        }
                        case 3: {
                            c12 = '^';
                            break;
                        }
                        default: {
                            c12 = '\u0011';
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
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "H\u000b\n,~v\r\u000f*".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0793: {
                if (n26 > 1) {
                    break Label_0793;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u0005';
                            break;
                        }
                        case 1: {
                            c14 = 'b';
                            break;
                        }
                        case 2: {
                            c14 = 'i';
                            break;
                        }
                        case 3: {
                            c14 = '^';
                            break;
                        }
                        default: {
                            c14 = '\u0011';
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
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "7VZi".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0909: {
                if (n30 > 1) {
                    break Label_0909;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u0005';
                            break;
                        }
                        case 1: {
                            c16 = 'b';
                            break;
                        }
                        case 2: {
                            c16 = 'i';
                            break;
                        }
                        case 3: {
                            c16 = '^';
                            break;
                        }
                        default: {
                            c16 = '\u0011';
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
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "o\u0003\u001f??s\u0007\u0007:~w".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1025: {
                if (n34 > 1) {
                    break Label_1025;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\u0005';
                            break;
                        }
                        case 1: {
                            c18 = 'b';
                            break;
                        }
                        case 2: {
                            c18 = 'i';
                            break;
                        }
                        case 3: {
                            c18 = '^';
                            break;
                        }
                        default: {
                            c18 = '\u0011';
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
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "f\r\u0004p|vL\u001c*xiL:'bq\u0007\u0004\btw\u0011\u00001\u007fH\u0003\u0007?v`\u0010".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1141: {
                if (n38 > 1) {
                    break Label_1141;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '\u0005';
                            break;
                        }
                        case 1: {
                            c20 = 'b';
                            break;
                        }
                        case 2: {
                            c20 = 'i';
                            break;
                        }
                        case 3: {
                            c20 = '^';
                            break;
                        }
                        default: {
                            c20 = '\u0011';
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
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "G\u0017\u00002uL\f\n,th\u0007\u0007*".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1257: {
                if (n42 > 1) {
                    break Label_1257;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = '\u0005';
                            break;
                        }
                        case 1: {
                            c22 = 'b';
                            break;
                        }
                        case 2: {
                            c22 = 'i';
                            break;
                        }
                        case 3: {
                            c22 = '^';
                            break;
                        }
                        default: {
                            c22 = '\u0011';
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
        z[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "b\u0007\u001d\b\\S\u0007\u001b-xj\f".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1373: {
                if (n46 > 1) {
                    break Label_1373;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '\u0005';
                            break;
                        }
                        case 1: {
                            c24 = 'b';
                            break;
                        }
                        case 2: {
                            c24 = 'i';
                            break;
                        }
                        case 3: {
                            c24 = '^';
                            break;
                        }
                        default: {
                            c24 = '\u0011';
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
        z[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "D\u0010\u0000?}".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1493: {
                if (n50 > 1) {
                    break Label_1493;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = '\u0005';
                            break;
                        }
                        case 1: {
                            c26 = 'b';
                            break;
                        }
                        case 2: {
                            c26 = 'i';
                            break;
                        }
                        case 3: {
                            c26 = '^';
                            break;
                        }
                        default: {
                            c26 = '\u0011';
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
        z[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "Z\u0000\u0005?\u007fn".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1609: {
                if (n54 > 1) {
                    break Label_1609;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = '\u0005';
                            break;
                        }
                        case 1: {
                            c28 = 'b';
                            break;
                        }
                        case 2: {
                            c28 = 'i';
                            break;
                        }
                        case 3: {
                            c28 = '^';
                            break;
                        }
                        default: {
                            c28 = '\u0011';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 <= n56) {
                z[n53] = new String(charArray14).intern();
                loader.a.z = z;
                loader.a.a = 1000L;
                return;
            }
            continue;
        }
    }
}
