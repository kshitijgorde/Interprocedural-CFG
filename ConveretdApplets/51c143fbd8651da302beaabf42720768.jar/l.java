import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class l extends MouseAdapter
{
    private final w a;
    private static String z;
    
    l(final w a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        if (!r) {
            if (clickCount == n) {
                final boolean contains = this.a.b.b.b.contains(mouseEvent.getX(), mouseEvent.getY());
                final w a2;
                Label_0312: {
                    if (!r) {
                        if (contains) {
                            final String e;
                            String substring = e = this.a.b.b.b.e();
                            Label_0135: {
                                final String s2;
                                final int n2;
                                Label_0131: {
                                    char c = '\0';
                                    Label_0125: {
                                        Label_0115: {
                                            Label_0114: {
                                                if (!r) {
                                                    if (!e.startsWith("@")) {
                                                        final String s = substring;
                                                        if (r) {
                                                            break Label_0114;
                                                        }
                                                        if (!s.startsWith("+")) {
                                                            final boolean b = (c = (char)(substring.startsWith("%") ? 1 : 0)) != '\0';
                                                            if (r) {
                                                                break Label_0125;
                                                            }
                                                            if (!b) {
                                                                break Label_0115;
                                                            }
                                                        }
                                                    }
                                                    substring.substring(1);
                                                }
                                            }
                                            substring = e;
                                        }
                                        s2 = substring;
                                        n2 = 0;
                                        if (r) {
                                            break Label_0131;
                                        }
                                        c = s2.charAt(n2);
                                    }
                                    if (c != '\u0003') {
                                        break Label_0135;
                                    }
                                }
                                substring = s2.substring(n2);
                            }
                            y i = w.a(this.a).i(substring);
                            if (!r) {
                                if (i == null) {
                                    final esChat a = w.a(this.a);
                                    a.getClass();
                                    i = new y(a, substring);
                                    w.a(this.a).Zb.b.a(substring, i, false);
                                }
                                w.a(this.a).Zb.b.a(i);
                            }
                            if (!r) {
                                return;
                            }
                        }
                        a2 = this.a;
                        if (r) {
                            break Label_0312;
                        }
                        a2.h.b.contains(mouseEvent.getX() - 100, mouseEvent.getY());
                    }
                    if (!contains) {
                        return;
                    }
                    (this.a.j = new t(this.a, l.z)).setSize(400, 250);
                    this.a.j.pack();
                    this.a.j.setVisible(true);
                    final w a3 = this.a;
                }
                a2.j.toFront();
                if (!r) {
                    return;
                }
            }
            mouseEvent.getModifiers();
        }
        final int n3 = clickCount & n;
        final w a4;
        Label_0367: {
            if (!r) {
                if (n3 <= 0) {
                    return;
                }
                a4 = this.a;
                if (r) {
                    break Label_0367;
                }
                a4.w.contains(mouseEvent.getX(), mouseEvent.getY());
            }
            if (n3 == 0) {
                return;
            }
            final w a5 = this.a;
        }
        w.a(a4).Jb.show(w.a(this.a).Zb.e, mouseEvent.getX() - 50, mouseEvent.getY() - 5);
    }
    
    static {
        final char[] charArray = "D\ba\u0018xo\u001a{".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0006';
                    break;
                }
                case 1: {
                    c2 = 'i';
                    break;
                }
                case 2: {
                    c2 = '\u000f';
                    break;
                }
                case 3: {
                    c2 = '8';
                    break;
                }
                default: {
                    c2 = '4';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        l.z = new String(charArray).intern();
    }
}
