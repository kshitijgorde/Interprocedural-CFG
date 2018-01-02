import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import java.awt.Frame;
import java.applet.AppletStub;

// 
// Decompiled by Procyon v0.5.30
// 

public class tb implements AppletStub
{
    Frame a;
    Applet b;
    String c;
    String d;
    private static final String[] z;
    
    public tb() {
    }
    
    public tb(final Frame a, final Applet b, final String c, final String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        sb.a().a(b, c);
    }
    
    public boolean isActive() {
        return true;
    }
    
    public URL getDocumentBase() {
        final String property = System.getProperty(tb.z[1]);
        System.out.println(tb.z[0] + property);
        try {
            if (property == null) {
                System.out.println(tb.z[2] + this.d);
                return new URL(this.d);
            }
            return new URL(property);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public URL getCodeBase() {
        final String property = System.getProperty(tb.z[3]);
        System.out.println(tb.z[4] + property);
        try {
            if (property == null) {
                System.out.println(tb.z[5] + this.d);
                return new URL(this.d);
            }
            return new URL(property);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public String getParameter(final String s) {
        final String property = System.getProperty(this.c + s);
        if (property != null) {
            return property;
        }
        return System.getProperty(s);
    }
    
    public AppletContext getAppletContext() {
        return sb.a();
    }
    
    public void appletResize(final int n, final int n2) {
        this.a.setSize(n + 10, n2 + 20);
        this.b.setSize(n, n2);
    }
    
    static {
        final String[] z2 = new String[6];
        final int n = 0;
        final char[] charArray = "\u001dPM\u0000(.\u0002@\u0006f(AW\f5:\u0002P\u0006%i@U\u001a#s\u0002".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'I';
                    break;
                }
                case 1: {
                    c2 = '\"';
                    break;
                }
                case 2: {
                    c2 = '4';
                    break;
                }
                case 3: {
                    c2 = 'i';
                    break;
                }
                default: {
                    c2 = 'F';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "-MW\u000b':G".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'I';
                    break;
                }
                case 1: {
                    c4 = '\"';
                    break;
                }
                case 2: {
                    c4 = '4';
                    break;
                }
                case 3: {
                    c4 = 'i';
                    break;
                }
                default: {
                    c4 = 'F';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u001ecf'\u000f\u0007e\u000eIf\u0007m`I\u0002\u0010lu$\u000f\n\u0002S\f2\rMW\u001c+,L@+':G\u001c@hi\u0002^\b4\u001cpx-/;\u0002R\u001b)$\u0002W\u0006(:VF\u001c%=MFI{i".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'I';
                    break;
                }
                case 1: {
                    c6 = '\"';
                    break;
                }
                case 2: {
                    c6 = '4';
                    break;
                }
                case 3: {
                    c6 = 'i';
                    break;
                }
                default: {
                    c6 = 'F';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "*MP\f$(QQ".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'I';
                    break;
                }
                case 1: {
                    c8 = '\"';
                    break;
                }
                case 2: {
                    c8 = '4';
                    break;
                }
                case 3: {
                    c8 = 'i';
                    break;
                }
                default: {
                    c8 = 'F';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\u001dPM\u0000(.\u0002@\u0006f(AW\f5:\u0002W\u0006\",\u0002V\b5,\u0018\u0014".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'I';
                    break;
                }
                case 1: {
                    c10 = '\"';
                    break;
                }
                case 2: {
                    c10 = '4';
                    break;
                }
                case 3: {
                    c10 = 'i';
                    break;
                }
                default: {
                    c10 = 'F';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u001ecf'\u000f\u0007e\u000eIf\u0007m`I\u0002\u0010lu$\u000f\n\u0002S\f2\nMP\f\u0004(QQAog\u0002\u0014\u0003';wf%\u0002 P\u0014\u000f4&O\u0014\n)'Q@\u001b3*V[\u001bft\u0002".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'I';
                    break;
                }
                case 1: {
                    c12 = '\"';
                    break;
                }
                case 2: {
                    c12 = '4';
                    break;
                }
                case 3: {
                    c12 = 'i';
                    break;
                }
                default: {
                    c12 = 'F';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        z = z2;
    }
}
