// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Panel;

class f extends Panel
{
    TextArea a;
    i b;
    boolean c;
    String d;
    a e;
    static Image f;
    int g;
    private static String[] z;
    
    public f() {
        this.a = null;
        this.b = new i();
        this.c = false;
        this.d = new String();
        this.e = null;
        this.g = 3;
        this.setFont(new Font(cfg8.f.z[2], 0, 10));
        (this.a = new TextArea("", 1, 1, 3)).setEnabled(true);
        this.a.setEditable(false);
        this.a.setBackground(new Color(248, 248, 218));
        this.a.setForeground(new Color(80, 80, 80));
        this.add(this.a);
        this.enableEvents(16L);
    }
    
    protected int a(final int n) {
        final int a = RotationImageFilter.a;
        int n2 = this.b.e() + 2;
        f f = this;
        Label_0126: {
            if (a == 0) {
                if (!this.a()) {
                    break Label_0126;
                }
                f = this;
            }
            final FontMetrics fontMetrics = f.getFontMetrics(this.getFont());
            fontMetrics.stringWidth(this.a.getText());
            int n3 = n - this.b.d() - 24;
            final int b = this.b() ? 1 : 0;
            final int n4 = 1;
            if (a == 0 && b == n4) {
                n3 -= 16;
                goto Label_0080;
            }
            final int n5;
            int g = n5 = b + n4;
            final int g2 = this.g;
            if (a == 0) {
                if (n5 > g2) {
                    g = this.g;
                }
                final int n6 = 4 + fontMetrics.getHeight() * g;
            }
            n2 = n5 + g2;
            if (a == 0) {
                return n2;
            }
        }
        n2 += 14;
        return n2;
    }
    
    public void doLayout() {
        final int a = RotationImageFilter.a;
        super.doLayout();
        final Rectangle bounds = this.getBounds();
        this.b.b(new Rectangle(0, 0, bounds.width, bounds.height));
        final Rectangle a2 = this.b.a();
        int n2;
        final int n = n2 = (this.a() ? 1 : 0);
        int n4;
        final int n3 = n4 = 0;
        if (a == 0) {
            if (n == n3) {
                this.a.setVisible(false);
                if (a == 0) {
                    return;
                }
            }
            final int length;
            n2 = (length = this.d.length());
            final int n5;
            n4 = (n5 = 0);
        }
        Label_0190: {
            f f = null;
            Label_0165: {
                if (a == 0) {
                    if (n == n3) {
                        this.a.setBounds(a2.x, a2.y, a2.width, a2.height);
                        this.a.setVisible(true);
                        if (a == 0) {
                            return;
                        }
                    }
                    f = this;
                    if (a != 0) {
                        break Label_0165;
                    }
                    n2 = this.b.d();
                    n4 = 0;
                }
                if (n2 > n4) {
                    this.a.setBounds(a2.x, a2.y, a2.width - 14, a2.height);
                    if (a == 0) {
                        break Label_0190;
                    }
                }
                f = this;
            }
            f.a.setBounds(a2.x, a2.y, a2.width - 17, a2.height);
        }
        this.a.setVisible(true);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int a = RotationImageFilter.a;
        super.paint(graphics);
        final Rectangle bounds = this.getBounds();
        final Rectangle a2 = this.b.a();
        this.b.a(graphics, this, 0, 0, bounds.width, bounds.height);
        final int length = this.d.length();
        final int n = 0;
        if (a == 0) {
            if (length <= n) {
                return;
            }
            this.b.d();
            if (a != 0) {
                return;
            }
        }
        if (length > n) {
            graphics.drawImage(cfg8.f.f, a2.x + a2.width - 12, a2.y - 2, this);
            if (a == 0) {
                return;
            }
        }
        graphics.drawImage(cfg8.f.f, a2.x + a2.width - 16, a2.y, this);
    }
    
    protected boolean a() {
        return this.c;
    }
    
    protected boolean b() {
        int length;
        final int n = length = this.d.length();
        if (RotationImageFilter.a == 0) {
            if (n > 0) {
                length = (true ? 1 : 0);
            }
            else {
                length = (false ? 1 : 0);
            }
        }
        return length != 0;
    }
    
    protected boolean c() {
        final int a = RotationImageFilter.a;
        final boolean a2 = this.a();
        if (a == 0) {
            if (!a2) {
                final boolean b = this.b();
                if (a == 0) {
                    if (b) {}
                }
            }
        }
        return a2;
    }
    
    protected void a(final String text) {
        int length;
        final int n = length = text.length();
        if (RotationImageFilter.a == 0) {
            if (n > 0) {
                length = 1;
            }
            else {
                length = 0;
            }
        }
        this.c = (length != 0);
        this.a.setText(text);
    }
    
    protected void a(final String d, final a e) {
        this.d = d;
        this.e = e;
    }
    
    protected void a(final String s, final String s2, final a a) {
        final int length = s2.length();
        Label_0033: {
            if (RotationImageFilter.a == 0) {
                if (length <= 0) {
                    break Label_0033;
                }
                this.b.a(s, s2, a);
            }
            this.b.a(this);
        }
        cfg8.f.f = a.a(cfg8.f.z[0]);
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        final int a = RotationImageFilter.a;
        final int id = mouseEvent.getID();
        f f = null;
        Label_0145: {
            Label_0144: {
                if (a == 0) {
                    switch (id) {
                        case 500: {
                            mouseEvent.getClickCount();
                            break;
                        }
                        default: {
                            break Label_0144;
                        }
                    }
                }
                if (id > 0) {
                    final Rectangle a2 = this.b.a();
                    final int length = this.d.length();
                    final int n = 0;
                    Label_0136: {
                        if (a == 0) {
                            if (length > n) {
                                final int x = mouseEvent.getX();
                                final int n2 = a2.x + a2.width - 16;
                                if (a != 0) {
                                    break Label_0136;
                                }
                                if (x > n2) {
                                    final int y = mouseEvent.getY();
                                    final int n3 = a2.y + 16;
                                    if (a != 0) {
                                        break Label_0136;
                                    }
                                    if (y < n3) {
                                        this.e.ShowWebPage(this.d, cfg8.f.z[1]);
                                        if (a == 0) {
                                            break Label_0144;
                                        }
                                    }
                                }
                            }
                            f = this;
                            if (a != 0) {
                                break Label_0145;
                            }
                            this.b.d();
                        }
                    }
                    if (length > n) {
                        this.setVisible(false);
                    }
                }
            }
            f = this;
        }
        f.processMouseEvent(mouseEvent);
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u0005JRwj\u000b[@(k\f^\\ew\u0016L\\i,\u0005QU".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'b';
                            break;
                        }
                        case 1: {
                            c2 = '8';
                            break;
                        }
                        case 2: {
                            c2 = '3';
                            break;
                        }
                        case 3: {
                            c2 = '\u0007';
                            break;
                        }
                        default: {
                            c2 = '\u0002';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "=Z_fl\t".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'b';
                            break;
                        }
                        case 1: {
                            c4 = '8';
                            break;
                        }
                        case 2: {
                            c4 = '3';
                            break;
                        }
                        case 3: {
                            c4 = '\u0007';
                            break;
                        }
                        default: {
                            c4 = '\u0002';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "1Y]tQ\u0007JZa".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'b';
                            break;
                        }
                        case 1: {
                            c6 = '8';
                            break;
                        }
                        case 2: {
                            c6 = '3';
                            break;
                        }
                        case 3: {
                            c6 = '\u0007';
                            break;
                        }
                        default: {
                            c6 = '\u0002';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                cfg8.f.z = z;
                cfg8.f.f = null;
                return;
            }
            continue;
        }
    }
}
