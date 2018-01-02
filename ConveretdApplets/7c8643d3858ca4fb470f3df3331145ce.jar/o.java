import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class o extends MouseAdapter
{
    private final x a;
    
    o(final x a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        x.a(this.a).xb = x.a(this.a).p(this.a.b.b.e());
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        final int contains;
        Label_0255: {
            if (!r) {
                if (clickCount == n) {
                    contains = (this.a.b.b.contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0);
                    if (r) {
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
                                            if (!r) {
                                                if (!e.startsWith("@")) {
                                                    final String s = substring;
                                                    if (r) {
                                                        break Label_0145;
                                                    }
                                                    if (!s.startsWith("+")) {
                                                        final boolean b = (c = (char)(substring.startsWith("%") ? 1 : 0)) != '\0';
                                                        if (r) {
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
                                    if (r) {
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
                        final y i;
                        y y = i = x.a(this.a).i(substring);
                        Label_0231: {
                            if (!r) {
                                if (i != null) {
                                    break Label_0231;
                                }
                                final esChat a;
                                final y y2 = new y(a, substring);
                                a = x.a(this.a);
                                a.getClass();
                            }
                            y = i;
                            x.a(this.a).Zb.b.a(substring, y, false);
                        }
                        x.a(this.a).Zb.b.a(y);
                    }
                }
                mouseEvent.getModifiers();
            }
        }
        if (contains > 0) {
            x.a(this.a).Ib.show(x.a(this.a).Zb.e, x.a(this.a).Zb.e.getSize().width - 80, mouseEvent.getY() - 5);
        }
    }
}
