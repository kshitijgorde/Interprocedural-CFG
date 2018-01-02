// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import flaxchat.a.k;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import flaxchat.n;
import flaxchat.f.g;
import java.awt.Component;
import java.awt.event.ActionListener;

public final class j implements ActionListener
{
    private Component a;
    private String b;
    private String c;
    private g d;
    private String e;
    private String f;
    private n g;
    
    public j(final n g, final Component a, final String b, final String c, final g d, final String e, final String s) {
        this.g = g;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = s.trim();
        if (this.f.startsWith("/")) {
            this.f = this.f.substring(1);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int b = flaxchat.g.c.b;
        final Hashtable hashtable = new Hashtable();
        final int length = this.f.length();
        flaxchat.a.j j = new flaxchat.a.j();
        int n = 0;
        while (true) {
            Label_0552: {
                if (b == 0) {
                    break Label_0552;
                }
                char c = this.f.charAt(n);
                Label_0549: {
                    if (c == '#') {
                        if (n + 1 >= length) {
                            j.a(this.c);
                            if (b == 0) {
                                break Label_0549;
                            }
                        }
                        ++n;
                        c = this.f.charAt(n);
                        if (c == ' ') {
                            j.a(this.c);
                            j.a(' ');
                            if (b == 0) {
                                break Label_0549;
                            }
                        }
                        j.a(this.f.charAt(n - 1));
                        j.a(c);
                        if (b == 0) {
                            break Label_0549;
                        }
                    }
                    if (c == '$') {
                        if (n + 1 >= length) {
                            break Label_0549;
                        }
                        ++n;
                        final char char1 = this.f.charAt(n);
                        if (char1 != '$') {
                            j.a(this.f.charAt(n - 1));
                            j.a(char1);
                            if (b == 0) {
                                break Label_0549;
                            }
                        }
                        if (n + 1 >= length) {
                            break Label_0549;
                        }
                        ++n;
                        c = this.f.charAt(n);
                        if (c == '1') {
                            j.a(this.d.g());
                            if (b == 0) {
                                break Label_0549;
                            }
                        }
                        if (c == 'm') {
                            j.a(this.g.h().g());
                            if (b == 0) {
                                break Label_0549;
                            }
                        }
                        if (c == '?') {
                            if (n + 1 >= length) {
                                break Label_0549;
                            }
                            ++n;
                            c = this.f.charAt(n);
                            if (c != '=') {
                                j.a(this.f.charAt(n - 3));
                                j.a(this.f.charAt(n - 2));
                                j.a(this.f.charAt(n - 1));
                                j.a(c);
                                if (b == 0) {
                                    break Label_0549;
                                }
                            }
                            final String a = this.a(n + 1);
                            if (a == null) {
                                break Label_0549;
                            }
                            final String a2 = k.a(this.a, a);
                            if (a2 == null) {
                                return;
                            }
                            j.a(a2);
                            n += a.length() + 2;
                            if (b == 0) {
                                break Label_0549;
                            }
                        }
                        j.a(this.f.charAt(n - 2));
                        j.a(this.f.charAt(n - 1));
                        j.a(c);
                        if (b == 0) {
                            break Label_0549;
                        }
                    }
                    if (c == '|') {
                        if (j.a() != 0) {
                            this.a(actionEvent.getSource(), j.toString(), hashtable);
                        }
                        j = new flaxchat.a.j();
                        if (b == 0) {
                            break Label_0549;
                        }
                    }
                    j.a(c);
                }
                ++n;
            }
            if (n >= length) {
                if (j.a() != 0) {
                    this.a(actionEvent.getSource(), j.toString(), hashtable);
                }
                return;
            }
            continue;
        }
    }
    
    private void a(final Object o, String s, final Hashtable hashtable) {
        s = s.trim();
        if (s.startsWith("/")) {
            s = s.substring(1);
        }
        this.g.a(o, this.c, this.a(), s, hashtable);
    }
    
    private String a(final int n) {
        final int b = flaxchat.g.c.b;
        final flaxchat.a.j j = new flaxchat.a.j();
        final int length = this.f.length();
        int n2 = n + 1;
        while (true) {
            Label_0064: {
                if (b == 0) {
                    break Label_0064;
                }
                final char char1 = this.f.charAt(n2);
                if (char1 == '\"' && b == 0) {
                    return j.toString();
                }
                j.a(char1);
                ++n2;
            }
            if (n2 < length) {
                continue;
            }
            break;
        }
        return j.toString();
    }
    
    public g a() {
        return this.d;
    }
}
