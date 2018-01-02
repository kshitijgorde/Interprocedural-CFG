// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import flaxchat.f.b;
import java.awt.Component;
import flaxchat.a.k;
import flaxchat.f.a;
import java.io.IOException;
import flaxchat.a.s;

public final class t extends s
{
    private final FlaxChat b;
    private String c;
    private String d;
    int e;
    private static String[] z;
    
    public t(final FlaxChat flaxChat, final String c, final String d) {
        this.b = flaxChat;
        this.b = flaxChat;
        this.c = c;
        this.d = d;
        this.e = FlaxChat.a(flaxChat);
        FlaxChat.a(flaxChat, FlaxChat.a(flaxChat) + 1);
    }
    
    public void run() {
        while (true) {
            Label_0073: {
                if (n.w == 0) {
                    break Label_0073;
                }
                try {
                    if (!FlaxChat.b(this.b).i()) {
                        this.a(this.c, this.d);
                    }
                    return;
                }
                catch (IOException ex) {}
                catch (a a) {
                    FlaxChat.a(this.b, a.getMessage(), true);
                }
                try {
                    Thread.sleep(this.b.a(t.z[14], 50000));
                }
                catch (InterruptedException ex2) {
                    return;
                }
            }
            if (super.a && !Thread.currentThread().isInterrupted()) {
                continue;
            }
            break;
        }
    }
    
    private void a(final String s, final String s2) throws IOException, a {
        String a = s;
        while (true) {
            Label_0232: {
                if (n.w == 0) {
                    break Label_0232;
                }
                try {
                    final String[] a2 = this.a();
                    final String s3 = a2[0];
                    final String s4 = a2[1];
                    FlaxChat.b(this.b).n(a);
                    FlaxChat.b(this.b).m(a);
                    if (FlaxChat.c(this.b)) {
                        FlaxChat.a(this.b, t.z[3], t.z[4]);
                        return;
                    }
                    if (this.b.s()) {
                        return;
                    }
                    try {
                        FlaxChat.b(this.b).a(s3, Integer.parseInt(s4), s2);
                    }
                    catch (RuntimeException ex) {
                        FlaxChat.a(this.b, t.z[7], t.z[2]);
                        return;
                    }
                    if (!FlaxChat.b(this.b).i()) {
                        FlaxChat.a(this.b, t.z[5], new String[] { s3, s4, a }, false);
                        throw new IOException(t.z[1]);
                    }
                    return;
                }
                catch (b b) {
                    FlaxChat.a(this.b, b.getMessage(), true);
                    a = k.a(this.b, t.z[0]);
                    if (a == null) {
                        FlaxChat.a(this.b, t.z[6], true);
                        return;
                    }
                }
            }
            if (super.a && !Thread.currentThread().isInterrupted()) {
                continue;
            }
            break;
        }
    }
    
    private String[] a() {
        String a;
        if (!this.b.b(null)) {
            flaxchat.d.a.a(t.z[12]);
            a = flaxchat.d.a.a(t.z[9]);
        }
        else {
            a = t.z[11];
        }
        if (this.b.getParameter(t.z[10]) != null) {
            return new String[] { t.z[8], FlaxChat.a(this.b, a) };
        }
        return new String[] { t.z[13], FlaxChat.a(this.b, a) };
    }
    
    static {
        t.z = new String[] { z(z("x\u0013dH.s\u0003gTt")), z(z("C\u0017mMg\u0001\u0012oFgM")), z(z("C\u0017mMoO\u0002c\u0001eT\u0004\u007fMoL\u001fsN|\u000f")), z(z("\"F<")), z(z("c\u0003*RgU\u0013dH`\u0001\u0005\u007fO{B\u0003s@.F\u001fxH\u0151HVs@}@\u001df@`L\u0147\u0155U\u013fSX")), z(z("L\u0005m\u0012")), z(z("s\u0003gTt\u0001\u0014oMgS\u0002gDjD\u0018*R{O\u0003iTw@Vh@iM\u0017d@c@\fyH`H\f$")), z(z("\"F>")), z(z("M\u0019i@bI\u0019yU")), z(z("H\u0004iqaS\u0002")), z(z("e3\\")), z(z("\u0017@<\u0016")), z(z("H\u0004iiaR\u0002")), z(z("H\u0004i\u000fl@\u001azDzD\u0011cL B\u0019g")), z(z("S\u0013iN`O\u0013iUJD\u001akX")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000e';
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
                    c2 = '!';
                    break;
                }
                case 1: {
                    c2 = 'v';
                    break;
                }
                case 2: {
                    c2 = '\n';
                    break;
                }
                case 3: {
                    c2 = '!';
                    break;
                }
                default: {
                    c2 = '\u000e';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
