// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import flaxchat.h.b;
import java.awt.Component;
import flaxchat.e.j;
import flaxchat.h.a;
import java.io.IOException;
import flaxchat.e.r;

public final class s extends r
{
    private final FlaxChat b;
    private String c;
    private String d;
    int e;
    private static String[] z;
    
    public s(final FlaxChat flaxChat, final String c, final String d) {
        this.b = flaxChat;
        this.b = flaxChat;
        this.c = c;
        this.d = d;
        this.e = FlaxChat.a(flaxChat);
        FlaxChat.a(flaxChat, FlaxChat.a(flaxChat) + 1);
    }
    
    public void run() {
        while (true) {
            Label_0072: {
                if (!m.s) {
                    break Label_0072;
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
                    Thread.sleep(this.b.a(s.z[5], 50000));
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
            Label_0208: {
                if (!m.s) {
                    break Label_0208;
                }
                try {
                    final String[] a2 = this.a();
                    final String s3 = a2[0];
                    final String s4 = a2[1];
                    FlaxChat.b(this.b).n(a);
                    FlaxChat.b(this.b).m(a);
                    if (this.b.r()) {
                        return;
                    }
                    try {
                        FlaxChat.b(this.b).a(s3, Integer.parseInt(s4), s2);
                    }
                    catch (RuntimeException ex) {
                        FlaxChat.a(this.b, s.z[6], s.z[9]);
                        return;
                    }
                    if (!FlaxChat.b(this.b).i()) {
                        FlaxChat.a(this.b, s.z[11], new String[] { s3, s4, a }, false);
                        throw new IOException(s.z[8]);
                    }
                    return;
                }
                catch (b b) {
                    FlaxChat.a(this.b, b.getMessage(), true);
                    a = j.a(this.b, s.z[10]);
                    if (a == null) {
                        FlaxChat.a(this.b, s.z[7], true);
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
        String s = null;
        Label_0061: {
            if (!this.b.b(this.b.getParameter(flaxchat.s.z[2]))) {
                flaxchat.i.a.a(flaxchat.s.z[2]);
                s = flaxchat.i.a.a(flaxchat.s.z[0]);
                if (!m.s) {
                    break Label_0061;
                }
            }
            s = this.b.getParameter(flaxchat.s.z[0]);
        }
        if (this.b.getParameter(flaxchat.s.z[4]) != null) {
            return new String[] { flaxchat.s.z[1], s };
        }
        return new String[] { flaxchat.s.z[3], FlaxChat.a(this.b, s) };
    }
    
    static {
        s.z = new String[] { z(z("5SR\u0015\u001b.U")), z(z("0NR$\u00184NB1")), z(z("5SR\r\u001b/U")), z(z("5SRk\u0007=CC \u0000rOT1")), z(z("\u0018dg")), z(z(".DR*\u001a2DR109MP<")), z(z("_\u0011\u0005")), z(z("\u000eT\\0\u000e|CT)\u001d.U\\ \u00109O\u00116\u00012TR0\r=\u0001S$\u00130@_$\u0019=[B,\u001a5[\u001f")), z(z(">@V)\u001d|ET\"\u001d0")), z(z(">@V)\u00152UXe\u001f)SD)\u00151HH*\u0006r")), z(z("\u0005D_,T\u000eT\\0\u000e")), z(z("1RVv")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 't';
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
                    c2 = '\\';
                    break;
                }
                case 1: {
                    c2 = '!';
                    break;
                }
                case 2: {
                    c2 = '1';
                    break;
                }
                case 3: {
                    c2 = 'E';
                    break;
                }
                default: {
                    c2 = 't';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
