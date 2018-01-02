import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class n extends MouseAdapter
{
    private final w a;
    
    n(final w a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int m = fb.m;
        w.a(this.a).ob = w.a(this.a).p(this.a.b.b.e());
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        final int contains;
        Label_0255: {
            if (m == 0) {
                if (clickCount == n) {
                    contains = (this.a.b.b.contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0);
                    if (m != 0) {
                        break Label_0255;
                    }
                    if (contains != 0) {
                        final String e;
                        String substring = e = this.a.b.b.e();
                        Label_0166: {
                            final String s2;
                            final int n2;
                            Label_0162: {
                                char c = '\0';
                                Label_0156: {
                                    Label_0146: {
                                        Label_0145: {
                                            if (m == 0) {
                                                if (!e.startsWith("@")) {
                                                    final String s = substring;
                                                    if (m != 0) {
                                                        break Label_0145;
                                                    }
                                                    if (!s.startsWith("+")) {
                                                        final boolean b = (c = (char)(substring.startsWith("%") ? 1 : 0)) != '\0';
                                                        if (m != 0) {
                                                            break Label_0156;
                                                        }
                                                        if (!b) {
                                                            break Label_0146;
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
                                        break Label_0162;
                                    }
                                    c = s2.charAt(n2);
                                }
                                if (c != '\u0003') {
                                    break Label_0166;
                                }
                            }
                            substring = s2.substring(n2);
                        }
                        final x i;
                        x x = i = w.a(this.a).i(substring);
                        Label_0231: {
                            if (m == 0) {
                                if (i != null) {
                                    break Label_0231;
                                }
                                final esChat a;
                                final x x2 = new x(a, substring);
                                a = w.a(this.a);
                                a.getClass();
                            }
                            x = i;
                            w.a(this.a).Ob.b.a(substring, x, false);
                        }
                        w.a(this.a).Ob.b.a(x);
                    }
                }
                mouseEvent.getModifiers();
            }
        }
        if (contains > 0) {
            w.a(this.a).yb.show(w.a(this.a).Ob.d, w.a(this.a).Ob.d.getSize().width - 80, mouseEvent.getY() - 5);
        }
    }
}
