// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import flaxchat.c.a;
import java.awt.TextComponent;
import flaxchat.h.g;
import java.awt.Toolkit;
import flaxchat.h.d;
import java.awt.event.ActionEvent;
import flaxchat.m;
import java.awt.event.ActionListener;

public class k implements ActionListener
{
    protected m a;
    private static String[] z;
    
    public k(final m a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a();
        this.a.x();
    }
    
    public void a() {
        final boolean b = c.b;
        final TextComponent g = this.a.g();
        final String text = g.getText();
        final String trim = d.c(text).trim();
        if (trim.length() == 0) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (!this.a(trim)) {
            this.a.e().b(text);
            g.setText("");
            return;
        }
        final String substring = trim.substring(1);
        if (substring.length() == 0) {
            Toolkit.getDefaultToolkit().beep();
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[12])) {
            Label_0189: {
                if (substring.substring(2).startsWith("#")) {
                    this.a.h().e(k.z[2] + substring.substring(2));
                    if (!b) {
                        break Label_0189;
                    }
                }
                this.a.h().e(k.z[0] + substring.substring(2));
            }
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[7])) {
            Label_0293: {
                if (substring.substring(5).startsWith("#")) {
                    this.a.h().e(k.z[2] + substring.substring(5));
                    if (!b) {
                        break Label_0293;
                    }
                }
                this.a.h().e(k.z[0] + substring.substring(5));
            }
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[1])) {
            this.a.h().e(k.z[15] + substring.substring(4));
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[19])) {
            this.a.e().b(k.z[8] + substring.substring(3) + "\u0001");
            g.setText("");
            return;
        }
        if (substring.startsWith(k.z[9])) {
            final String[] p = this.a.h().p();
            int n = 0;
            while (true) {
                Label_0508: {
                    if (!b) {
                        break Label_0508;
                    }
                    final a c = this.a.c(p[n]);
                    if (c instanceof flaxchat.c.c) {
                        c.b(k.z[8] + substring.substring(3) + "\u0001");
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
        else if (substring.startsWith(k.z[5])) {
            final String[] p2 = this.a.h().p();
            int n2 = 0;
            while (true) {
                Label_0594: {
                    if (!b) {
                        break Label_0594;
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
            if (substring.startsWith(k.z[4])) {
                this.a.a(new g("", substring.substring(6).trim()));
                g.setText("");
                return;
            }
            if (substring.startsWith(k.z[13])) {
                this.a.a(new g("", substring.substring(2).trim()));
                g.setText("");
                return;
            }
            if (substring.startsWith(k.z[3])) {
                final String s = k.z[20];
                final String string = k.z[17] + this.a.l().h();
                final String string2 = k.z[18] + this.a.l().i();
                final String s2 = k.z[10];
                final String s3 = k.z[6];
                final String s4 = k.z[14];
                this.a.e().c(k.z[11]);
                this.a.e().c(s);
                this.a.e().c(string);
                this.a.e().c(string2);
                this.a.e().c(s2);
                this.a.e().c(s3);
                this.a.e().c(s4);
                this.a.e().c(k.z[11]);
                g.setText("");
                return;
            }
            Label_0966: {
                if (substring.toLowerCase().startsWith(k.z[16])) {
                    try {
                        this.a.l().f(substring.substring(7));
                        break Label_0966;
                    }
                    catch (RuntimeException ex) {
                        if (!b) {
                            break Label_0966;
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
    
    static {
        k.z = new String[] { z(z("0q]kFY")), z(z("\u0017Ms\u0005")), z(z("0q]kF")), z(z("\u001cRu]\u0005\u0012_`")), z(z("\u000bKqW\u001fZ")), z(z("\u001bSgBF")), z(z("7mZ\u0005\\ZWzC\t:XxD\u001e\u0019VuQH\u0019Qy")), z(z("\u0010Q}KF")), z(z("{\u007fWq/5p4")), z(z("\u001bSq\u0005")), z(z("eIcRH\u001cRu]\u0005\u0012_`\u000b\u0005\u0015S")), z(z("W\u00139\bKW\u00139\bKW\u00139\bKW\u00139\bKW")), z(z("\u0010\u001e")), z(z("\u000b\u001e")), z(z("9Qd\\\u0014\u0013Y|QFH\u000e$\u001c\u00c8")), z(z("*l]s+)y4")), z(z("\u001fPwJ\u0002\u001f\u001e")), z(z("y\u000fXL\u0015\u001bPg\u0005\\Z")), z(z("y\nM@\u0012\u0011WxLF8_mLF@\u001e\u0016")), z(z("\u0017[4")), z(z("x= c\n\u001bF\u0017\u0017%\u0012_`\tN5GaKF,[fV\u000f\u0015P=")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'f';
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
                    c2 = 'z';
                    break;
                }
                case 1: {
                    c2 = '>';
                    break;
                }
                case 2: {
                    c2 = '\u0014';
                    break;
                }
                case 3: {
                    c2 = '%';
                    break;
                }
                default: {
                    c2 = 'f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
