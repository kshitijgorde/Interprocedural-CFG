// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import flaxchat.c.a;
import java.awt.TextComponent;
import flaxchat.f.g;
import java.awt.Toolkit;
import flaxchat.f.d;
import java.awt.event.ActionEvent;
import flaxchat.n;
import java.awt.event.ActionListener;

public class k implements ActionListener
{
    protected n a;
    private static String[] z;
    
    public k(final n a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a();
        this.a.x();
    }
    
    public void a() {
        final int b = c.b;
        final TextComponent g = this.a.g();
        final String text = g.getText();
        final String trim = d.c(text).trim();
        if (trim.length() == 0) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (!this.a(trim)) {
            final String d = flaxchat.f.d.d(text);
            this.a.e().b(text);
            g.setText(d);
            g.setCaretPosition(d.length());
            return;
        }
        final String substring = trim.substring(1);
        if (substring.length() == 0) {
            Toolkit.getDefaultToolkit().beep();
            g.setText("");
            return;
        }
        if (this.b(substring)) {
            this.a.e().c(k.z[16]);
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[2])) {
            Label_0236: {
                if (substring.substring(2).startsWith("#")) {
                    this.a.h().e(k.z[15] + substring.substring(2));
                    if (b == 0) {
                        break Label_0236;
                    }
                }
                this.a.h().e(k.z[18] + substring.substring(2));
            }
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[11])) {
            Label_0342: {
                if (substring.substring(5).startsWith("#")) {
                    this.a.h().e(k.z[15] + substring.substring(5));
                    if (b == 0) {
                        break Label_0342;
                    }
                }
                this.a.h().e(k.z[18] + substring.substring(5));
            }
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[14])) {
            this.a.h().e(k.z[10] + substring.substring(4));
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[6])) {
            this.a.e().b(k.z[5] + substring.substring(3) + "\u0001");
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[4])) {
            final String[] p = this.a.h().p();
            int n = 0;
            while (true) {
                Label_0555: {
                    if (b == 0) {
                        break Label_0555;
                    }
                    final a c = this.a.c(p[n]);
                    if (c instanceof flaxchat.c.c) {
                        c.b(k.z[5] + substring.substring(3) + "\u0001");
                    }
                    ++n;
                }
                if (n >= p.length) {
                    g.setText("");
                    return;
                }
                continue;
            }
        }
        else if (substring.startsWith(k.z[9])) {
            final String[] p2 = this.a.h().p();
            int n2 = 0;
            while (true) {
                Label_0642: {
                    if (b == 0) {
                        break Label_0642;
                    }
                    final a c2 = this.a.c(p2[n2]);
                    if (c2 instanceof flaxchat.c.c) {
                        c2.b(substring.substring(4));
                    }
                    ++n2;
                }
                if (n2 >= p2.length) {
                    g.setText("");
                    return;
                }
                continue;
            }
        }
        else {
            if (substring.startsWith(k.z[7])) {
                this.a.a(new g("", substring.substring(6).trim()));
                g.setText("");
                return;
            }
            if (substring.startsWith(k.z[8])) {
                this.a.a(new g("", substring.substring(2).trim()));
                g.setText("");
                return;
            }
            if (substring.startsWith(k.z[17]) || substring.startsWith(k.z[13])) {
                final String s = k.z[1];
                final String string = k.z[0] + this.a.l().i();
                final String s2 = k.z[3];
                this.a.e().c(k.z[19]);
                this.a.e().c(s);
                this.a.e().c(string);
                this.a.e().c(s2);
                this.a.e().c(k.z[19]);
                g.setText("");
                return;
            }
            Label_0943: {
                if (substring.toLowerCase().startsWith(k.z[12])) {
                    try {
                        this.a.l().f(substring.substring(7));
                        break Label_0943;
                    }
                    catch (RuntimeException ex) {
                        if (b == 0) {
                            break Label_0943;
                        }
                    }
                }
                this.a.h().e(substring);
            }
            g.setText("");
        }
    }
    
    private boolean a(final String s) {
        return s.startsWith("/");
    }
    
    private boolean b(final String s) {
        final int b = c.b;
        final String[] d = flaxchat.d.a.d();
        if (d == null) {
            return false;
        }
        int n = 0;
        while (true) {
            Label_0038: {
                if (b == 0) {
                    break Label_0038;
                }
                if (s.indexOf(d[n]) != -1) {
                    return true;
                }
                ++n;
            }
            if (n >= d.length) {
                return false;
            }
            continue;
        }
    }
    
    static {
        k.z = new String[] { z(z("\u00167\u001b$\u001bth$mR5")), z(z("\u0017\u0005c\u000b\u0004t~T\u007f+}g#")), z(z("\u007f&")), z(z("Vi'4\u001a|a?9H'6gt\u00c6")), z(z("tk2m")), z(z("\u0014G\u0014\u0019!ZHw")), z(z("xcw")), z(z("ds2?\u00115")), z(z("d&")), z(z("tk$*H")), z(z("ET\u001e\u001b%FAw")), z(z("\u007fi>#H")), z(z("ph4\"\fp&")), z(z("yo$,\u0006f")), z(z("xu0m")), z(z("_I\u001e\u0003H")), z(z("\u00162w\u000f\u001d5m8 \u001das9m\u0003`j;,\u0006|j:,\u001b|&.,\u001btm;,\u0006xo$9\u0001g(")), z(z("sj65\u000b}g#")), z(z("_I\u001e\u0003H6")), z(z("8+z`E8+z`E8+z`E8+z`E8")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'h';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0015';
                    break;
                }
                case 1: {
                    c2 = '\u0006';
                    break;
                }
                case 2: {
                    c2 = 'W';
                    break;
                }
                case 3: {
                    c2 = 'M';
                    break;
                }
                default: {
                    c2 = 'h';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
