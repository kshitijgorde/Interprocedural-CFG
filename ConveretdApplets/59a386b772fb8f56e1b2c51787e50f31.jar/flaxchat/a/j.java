// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import flaxchat.e.i;
import java.awt.event.ActionEvent;
import flaxchat.m;
import flaxchat.h.g;
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
    private m g;
    
    public j(final m g, final Component a, final String b, final String c, final g d, final String e, final String s) {
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
        final boolean b = flaxchat.a.c.b;
        final int length = this.f.length();
        i i = new i();
        int n = 0;
        while (true) {
            Label_0494: {
                if (!b) {
                    break Label_0494;
                }
                char c = this.f.charAt(n);
                Label_0491: {
                    if (c == '#') {
                        if (n + 1 >= length) {
                            i.a(this.c);
                            if (!b) {
                                break Label_0491;
                            }
                        }
                        ++n;
                        c = this.f.charAt(n);
                        if (c == ' ') {
                            i.a(this.c);
                            i.a(' ');
                            if (!b) {
                                break Label_0491;
                            }
                        }
                        i.a(this.f.charAt(n - 1));
                        i.a(c);
                        if (!b) {
                            break Label_0491;
                        }
                    }
                    if (c == '$') {
                        if (n + 1 >= length) {
                            break Label_0491;
                        }
                        ++n;
                        final char char1 = this.f.charAt(n);
                        if (char1 != '$') {
                            i.a(this.f.charAt(n - 1));
                            i.a(char1);
                            if (!b) {
                                break Label_0491;
                            }
                        }
                        if (n + 1 >= length) {
                            break Label_0491;
                        }
                        ++n;
                        c = this.f.charAt(n);
                        if (c == '1') {
                            i.a(this.d.d());
                            if (!b) {
                                break Label_0491;
                            }
                        }
                        if (c == '?') {
                            if (n + 1 >= length) {
                                break Label_0491;
                            }
                            ++n;
                            c = this.f.charAt(n);
                            if (c != '=') {
                                i.a(this.f.charAt(n - 3));
                                i.a(this.f.charAt(n - 2));
                                i.a(this.f.charAt(n - 1));
                                i.a(c);
                                if (!b) {
                                    break Label_0491;
                                }
                            }
                            final String a = this.a(n + 1);
                            if (a == null) {
                                break Label_0491;
                            }
                            final String a2 = flaxchat.e.j.a(this.a, a);
                            if (a2 == null) {
                                return;
                            }
                            i.a(a2);
                            n += a.length() + 2;
                            if (!b) {
                                break Label_0491;
                            }
                        }
                        i.a(this.f.charAt(n - 2));
                        i.a(this.f.charAt(n - 1));
                        i.a(c);
                        if (!b) {
                            break Label_0491;
                        }
                    }
                    if (c == '|') {
                        if (i.a() != 0) {
                            this.a(actionEvent.getSource(), i.toString());
                        }
                        i = new i();
                        if (!b) {
                            break Label_0491;
                        }
                    }
                    i.a(c);
                }
                ++n;
            }
            if (n >= length) {
                if (i.a() != 0) {
                    this.a(actionEvent.getSource(), i.toString());
                }
                return;
            }
            continue;
        }
    }
    
    private void a(final Object o, String s) {
        s = s.trim();
        if (s.startsWith("/")) {
            s = s.substring(1);
        }
        this.g.a(o, this.c, this.a(), s);
    }
    
    private String a(final int n) {
        final boolean b = flaxchat.a.c.b;
        final i i = new i();
        final int length = this.f.length();
        int n2 = n + 1;
        while (true) {
            Label_0064: {
                if (!b) {
                    break Label_0064;
                }
                final char char1 = this.f.charAt(n2);
                if (char1 == '\"' && !b) {
                    return i.toString();
                }
                i.a(char1);
                ++n2;
            }
            if (n2 < length) {
                continue;
            }
            break;
        }
        return i.toString();
    }
    
    public g a() {
        return this.d;
    }
}
