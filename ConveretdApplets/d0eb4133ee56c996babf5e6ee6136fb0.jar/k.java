import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class k extends MouseAdapter
{
    private final v a;
    private static String z;
    
    k(final v a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        if (m == 0) {
            if (clickCount == n) {
                final boolean contains = this.a.b.b.b.contains(mouseEvent.getX(), mouseEvent.getY());
                final v a2;
                Label_0312: {
                    if (m == 0) {
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
                                                if (m == 0) {
                                                    if (!e.startsWith("@")) {
                                                        final String s = substring;
                                                        if (m != 0) {
                                                            break Label_0114;
                                                        }
                                                        if (!s.startsWith("+")) {
                                                            final boolean b = (c = (char)(substring.startsWith("%") ? 1 : 0)) != '\0';
                                                            if (m != 0) {
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
                                        if (m != 0) {
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
                            x i = v.a(this.a).i(substring);
                            if (m == 0) {
                                if (i == null) {
                                    final esChat a = v.a(this.a);
                                    a.getClass();
                                    i = new x(a, substring);
                                    v.a(this.a).Tb.b.a(substring, i, false);
                                }
                                v.a(this.a).Tb.b.a(i);
                            }
                            if (m == 0) {
                                return;
                            }
                        }
                        a2 = this.a;
                        if (m != 0) {
                            break Label_0312;
                        }
                        a2.h.b.contains(mouseEvent.getX() - 100, mouseEvent.getY());
                    }
                    if (!contains) {
                        return;
                    }
                    (this.a.j = new s(this.a, k.z)).setSize(400, 250);
                    this.a.j.pack();
                    this.a.j.setVisible(true);
                    final v a3 = this.a;
                }
                a2.j.toFront();
                if (m == 0) {
                    return;
                }
            }
            mouseEvent.getModifiers();
        }
        final int n3 = clickCount & n;
        final v a4;
        Label_0367: {
            if (m == 0) {
                if (n3 <= 0) {
                    return;
                }
                a4 = this.a;
                if (m != 0) {
                    break Label_0367;
                }
                a4.w.contains(mouseEvent.getX(), mouseEvent.getY());
            }
            if (n3 == 0) {
                return;
            }
            final v a5 = this.a;
        }
        v.a(a4).Db.show(v.a(this.a).Tb.d, mouseEvent.getX() - 50, mouseEvent.getY() - 5);
    }
    
    static {
        final char[] charArray = ")\u0019\nE\u0011\u0002\u000b\u0010".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'k';
                    break;
                }
                case 1: {
                    c2 = 'x';
                    break;
                }
                case 2: {
                    c2 = 'd';
                    break;
                }
                case 3: {
                    c2 = 'e';
                    break;
                }
                default: {
                    c2 = ']';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        k.z = new String(charArray).intern();
    }
}
