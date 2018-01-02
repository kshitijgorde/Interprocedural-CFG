// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.util.StringTokenizer;
import java.awt.Image;
import flaxchat.a.c;
import java.awt.image.ImageObserver;
import flaxchat.d.d;
import java.awt.Shape;
import java.awt.Font;
import flaxchat.a.j;
import java.awt.Color;
import java.awt.Graphics;
import flaxchat.a.h;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.event.ComponentEvent;
import flaxchat.h.e;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

public final class b extends a
{
    private int w;
    private boolean x;
    private Dimension y;
    protected int z;
    protected int A;
    private boolean B;
    private static String[] C;
    
    public b(final int w) {
        this.x = true;
        this.y = new Dimension();
        this.z = -1;
        this.A = -1;
        this.B = false;
        this.w = w;
        this.setFont(flaxchat.d.b.d(b.C[2]));
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public void a(final boolean x) {
        this.x = x;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (!this.x) {
            return;
        }
        if (keyEvent.getKeyCode() == 17) {
            super.p = true;
            return;
        }
        if (keyEvent.getKeyCode() == 38) {
            keyEvent.consume();
            this.a();
            return;
        }
        if (keyEvent.getKeyCode() == 40) {
            keyEvent.consume();
            this.b();
            return;
        }
        if (keyEvent.getKeyCode() == 33) {
            keyEvent.consume();
            return;
        }
        if (keyEvent.getKeyCode() == 34) {
            keyEvent.consume();
        }
    }
    
    private void a() {
        final e j = this.j();
        if (j == null) {
            return;
        }
        this.a(j);
        this.repaint();
    }
    
    private void b() {
        final e i = this.i();
        if (i == null) {
            return;
        }
        this.a(i);
        this.repaint();
    }
    
    private void a(final e e) {
        final int d = e.a().d;
        final int e2 = e.a().e;
        final int n = super.t.g() * super.j;
        final int height = this.getSize().height;
        if (d <= n) {
            super.t.c();
            return;
        }
        if (e2 >= n + height - 1) {
            super.t.d();
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        if (!this.x) {
            return;
        }
        if (keyEvent.getKeyCode() == 17) {
            super.p = false;
        }
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final boolean j = flaxchat.i.i.j;
        final int n = super.n;
        final FontMetrics fontMetrics = this.getFontMetrics(super.s);
        super.j = fontMetrics.getHeight();
        super.k = fontMetrics.getAscent();
        super.n = 0;
        super.e.setSize(0);
        super.g.setSize(0);
        final k g = this.g();
        e e = super.d.a;
        while (true) {
            Label_0095: {
                if (!j) {
                    break Label_0095;
                }
                this.a((k)e.a);
                e = e.b;
            }
            if (e == null) {
                this.b(g);
                this.a(n);
                super.u = null;
                return;
            }
            continue;
        }
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.y = this.getSize();
        super.setBounds(n, n2, n3, n4);
    }
    
    public void a(final int n) {
        if (super.t == null) {
            return;
        }
        final int height = this.getSize().height;
        int g = super.t.g();
        final int n2 = (super.n - height) / super.j + 1;
        final int n3 = this.y.height - height;
        if (g != 0) {
            g += n3 / super.j;
            if (g < 0) {
                g = 0;
            }
            if (g >= this.c()) {
                g = this.c() - 1;
            }
        }
        super.i = g * super.j;
        super.t.a(g, n2);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.x) {
            return;
        }
        if (flaxchat.a.h.a(mouseEvent)) {
            return;
        }
        if (mouseEvent.getClickCount() == 1) {
            this.a(mouseEvent);
        }
        this.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.x) {
            return;
        }
        if (flaxchat.a.h.a(mouseEvent)) {
            return;
        }
        this.B = false;
        this.repaint();
        if (super.c == null && super.p) {
            return;
        }
        if (super.c == null) {
            return;
        }
        super.c.requestFocus();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (flaxchat.a.h.a(mouseEvent)) {
            return;
        }
        this.B = true;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final boolean j = flaxchat.i.i.j;
        if (!this.x) {
            return;
        }
        if (flaxchat.a.h.a(mouseEvent)) {
            return;
        }
        final int y = mouseEvent.getY();
        if (super.p && super.q) {
            this.repaint();
            return;
        }
        e e = super.d.a;
        while (true) {
            Label_0132: {
                if (!j) {
                    break Label_0132;
                }
                final k k = (k)e.a;
                final int d = k.d;
                Label_0125: {
                    if (d - super.i <= y && d - super.i + super.j >= y) {
                        if (k.f) {
                            break Label_0125;
                        }
                        k.f = true;
                        if (!j) {
                            break Label_0125;
                        }
                    }
                    ((k)e.a).f = false;
                }
                e = e.b;
            }
            if (e == null) {
                return;
            }
            continue;
        }
    }
    
    public void b(final boolean q) {
        super.q = q;
    }
    
    public Dimension getPreferredSize() {
        if (this.w == 0) {
            return super.getPreferredSize();
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(fontMetrics.charWidth('W') * this.w, fontMetrics.getHeight());
    }
    
    public Dimension getMinimumSize() {
        if (this.w == 0) {
            return super.getMinimumSize();
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(fontMetrics.charWidth('W') * 5, fontMetrics.getHeight());
    }
    
    public void e() {
        if (super.t == null) {
            return;
        }
        final int g = super.t.g();
        final int n = super.n / super.j - this.getSize().height / super.j + 1;
        if (g != super.t.h() - 1 || this.B) {
            super.t.d(n);
            return;
        }
        super.i = g * super.j;
        super.t.a(g, n);
    }
    
    public void paint(final Graphics graphics) {
        final boolean j = flaxchat.i.i.j;
        final Dimension size = this.getSize();
        int n = 0;
        int n2 = 0;
        Color black = Color.black;
        final Color blue = Color.blue;
        int n3 = 0;
        final j i = new j();
        Font s = super.s;
        final String name = s.getName();
        final int size2 = s.getSize();
        final FontMetrics fontMetrics = this.getFontMetrics(s);
        final Font font = s;
        final Font font2 = new Font(name, 1, size2);
        this.e();
        final Graphics graphics2 = this.a().getGraphics();
        graphics2.setClip(this.getBounds());
        this.b(graphics2);
        this.c(graphics2);
        final int k = super.j;
        int l = super.k;
        e e = super.d.a;
    Label_0230:
        while (true) {
            Label_0225: {
                if (!j) {
                    break Label_0225;
                }
                final k m = (k)e.a;
                l = m.d + k - super.j + super.k;
                if (m.e + k - super.j + super.k - super.i - fontMetrics.getAscent() >= 0) {
                    break Label_0230;
                }
                n2 += m.toString().length();
                e = e.b;
            }
            if (e != null) {
                continue;
            }
            break;
        }
        final Color black2 = Color.black;
        final Color white = Color.white;
        e b = e;
        while (true) {
            Label_1699: {
                if (!j) {
                    break Label_1699;
                }
                final String string = ((k)b.a).toString();
                int n5;
                int n4 = n5 = super.l + 2;
                int n6 = l - super.i;
                final boolean f = ((k)b.a).f;
                Font font3 = font;
                i.b(0);
                int n7 = 0;
                Color color = Color.black;
                Color color2 = Color.white;
                int n8 = 1;
                int n9 = 0;
                int n10 = 0;
                int n11 = 0;
                if (s != font) {
                    s = font;
                    graphics2.setFont(s);
                }
                Label_0501: {
                    if (f) {
                        graphics2.setColor(flaxchat.i.a.c(2));
                        n = n6 - fontMetrics.getAscent();
                        final Image f2 = flaxchat.d.d.f(flaxchat.i.b.C[1]);
                        if (f2 == null) {
                            graphics2.fillRect(0, n, size.width, super.j);
                            color = Color.white;
                            color2 = flaxchat.i.a.c(2);
                            if (!j) {
                                break Label_0501;
                            }
                        }
                        color = flaxchat.d.b.a(flaxchat.i.b.C[0], Color.white);
                        graphics2.drawImage(f2, 2, n, size.width, super.j, this);
                        if (this.hasFocus()) {
                            flaxchat.a.c.a(Color.white, graphics2, 2, n + super.j - 2, this.getSize().width, n + super.j - 2);
                        }
                    }
                }
                int n12 = 0;
            Label_1537:
                while (true) {
                    Label_1519: {
                        if (!j) {
                            break Label_1519;
                        }
                        if (string.charAt(n12) == '\u0001') {
                            if (n12 >= string.length() - 1) {
                                break Label_1537;
                            }
                            ++n12;
                        }
                        if (string.charAt(n12) == '\u0002') {
                            if (n12 >= string.length() - 1) {
                                break Label_1537;
                            }
                            Label_0578: {
                                if (font3 == font2) {
                                    font3 = font;
                                    if (!j) {
                                        break Label_0578;
                                    }
                                }
                                font3 = font2;
                            }
                            ++n12;
                        }
                        if (string.charAt(n12) == '\u001f') {
                            if (n12 >= string.length() - 1) {
                                break Label_1537;
                            }
                            Label_0621: {
                                if (n10 != 0) {
                                    n10 = 0;
                                    if (!j) {
                                        break Label_0621;
                                    }
                                }
                                n10 = 1;
                            }
                            ++n12;
                        }
                        if (string.charAt(n12) == '\u0016') {
                            if (n12 >= string.length() - 1) {
                                break Label_1537;
                            }
                            color = Color.white;
                            color2 = Color.black;
                            ++n12;
                        }
                        if (string.charAt(n12) == '\u000f') {
                            if (n12 >= string.length() - 1) {
                                break Label_1537;
                            }
                            Label_0721: {
                                if (n11 != 0) {
                                    color = Color.white;
                                    color2 = Color.black;
                                    n11 = 0;
                                    if (!j) {
                                        break Label_0721;
                                    }
                                }
                                n11 = 1;
                                color = Color.black;
                                color2 = Color.white;
                            }
                            ++n12;
                        }
                        if (string.charAt(n12) == '\u0003') {
                            if (n12 >= string.length() - 1) {
                                break Label_1537;
                            }
                            ++n12;
                            final char c = (char)(string.charAt(n12) - '0');
                            Label_0851: {
                                if (c > -1 && c < '\n') {
                                    if (string.length() - n12 < 2) {
                                        break Label_1537;
                                    }
                                    final char c2 = (char)(string.charAt(n12 + 1) - '0');
                                    if (c2 > -1 && c2 < '\n') {
                                        if (string.length() - n12 <= 2) {
                                            break Label_1537;
                                        }
                                        n8 = c2 + '\n' * c;
                                        n12 += 2;
                                        if (!j) {
                                            break Label_0851;
                                        }
                                    }
                                    n8 = c;
                                    ++n12;
                                }
                            }
                            Label_0987: {
                                if (string.charAt(n12) == ',' && n12 < string.length() - 1) {
                                    ++n12;
                                    final char c3 = (char)(string.charAt(n12) - '0');
                                    if (c3 > -1 && c3 < '\n') {
                                        if (string.length() - n12 < 2) {
                                            break Label_1537;
                                        }
                                        final char c4 = (char)(string.charAt(n12 + 1) - '0');
                                        if (c4 > -1 && c4 < '\n') {
                                            if (string.length() - n12 <= 2) {
                                                break Label_1537;
                                            }
                                            n9 = c4 + '\n' * c3;
                                            n12 += 2;
                                            if (!j) {
                                                break Label_0987;
                                            }
                                        }
                                        n9 = c3;
                                        ++n12;
                                        if (!j) {
                                            break Label_0987;
                                        }
                                    }
                                    --n12;
                                }
                            }
                            if (!f) {
                                if (n8 >= 0 && n8 < 16) {
                                    color = flaxchat.i.a.c(n8);
                                }
                                if (n9 >= 0 && n9 < 16) {
                                    color2 = flaxchat.i.a.c(n9);
                                }
                            }
                        }
                        final char char1 = string.charAt(n12);
                        final int a = this.a(char1);
                        if (char1 != '\u0001' && char1 != '\u0003' && char1 != '\u000f' && char1 != '\u0002' && char1 != '\u001f' && char1 != '\u0016') {
                            final int n13 = l - super.i;
                            n = n13 - fontMetrics.getAscent();
                            if (n + super.j >= 0 && n <= size.height) {
                                Label_1498: {
                                    if (this.B && n2 + n12 >= this.A && n2 + n12 <= this.z) {
                                        if (i.a() > 0) {
                                            Label_1237: {
                                                if (n3 != 0 || n7 == 1) {
                                                    graphics2.setColor(blue);
                                                    graphics2.fillRect(n5, n, this.a(i), super.j);
                                                    graphics2.setColor(Color.white);
                                                    graphics2.drawString(i.toString(), n5, n6);
                                                    if (!j) {
                                                        break Label_1237;
                                                    }
                                                }
                                                graphics2.drawString(i.toString(), n5, n6);
                                            }
                                            n3 = 1;
                                        }
                                        i.b(0);
                                        n5 = n4;
                                        n6 = n13;
                                        graphics2.setColor(black);
                                        if (!j) {
                                            break Label_1498;
                                        }
                                    }
                                    Label_1328: {
                                        if (color2 != Color.white) {
                                            if (color2 != black) {
                                                graphics2.setColor(color2);
                                                graphics2.fillRect(n4, n, a, super.j);
                                                graphics2.setColor(black);
                                                if (!j) {
                                                    break Label_1328;
                                                }
                                            }
                                            graphics2.fillRect(n4, n, a, super.j);
                                        }
                                    }
                                    Label_1459: {
                                        if (s != font3) {
                                            if (i.a() > 0) {
                                                graphics2.drawString(i.toString(), n5, n6);
                                            }
                                            if (color != black) {
                                                graphics2.setColor(color);
                                                black = color;
                                            }
                                            i.b(0);
                                            n5 = n4;
                                            n6 = n13;
                                            s = font3;
                                            graphics2.setFont(s);
                                            if (!j) {
                                                break Label_1459;
                                            }
                                        }
                                        if (color != black) {
                                            if (i.a() > 0) {
                                                graphics2.drawString(i.toString(), n5, n6);
                                            }
                                            i.b(0);
                                            n5 = n4;
                                            n6 = n13;
                                            graphics2.setColor(color);
                                            black = color;
                                        }
                                    }
                                    if (n10 != 0) {
                                        graphics2.drawLine(n4, n13 + fontMetrics.getDescent() - 3, n4 + a - 1, n13 + fontMetrics.getDescent() - 3);
                                    }
                                }
                                i.a(char1);
                            }
                            n4 += a;
                            ++n7;
                            ++n12;
                        }
                    }
                    if (n12 < string.length() && n <= size.height) {
                        continue;
                    }
                    break;
                }
                n2 += n12;
                Label_1683: {
                    if (i.a() > 0) {
                        if (this.B && n2 >= this.A && n2 <= this.z) {
                            Label_1661: {
                                if (n3 != 0 || n7 == 1) {
                                    graphics2.setColor(blue);
                                    graphics2.fillRect(n5, n, this.a(i), super.j);
                                    graphics2.setColor(Color.white);
                                    graphics2.drawString(i.toString(), n5, n6);
                                    graphics2.setColor(black);
                                    if (!j) {
                                        break Label_1661;
                                    }
                                }
                                graphics2.drawString(i.toString(), n5, n6);
                            }
                            n3 = 1;
                            if (!j) {
                                break Label_1683;
                            }
                        }
                        graphics2.drawString(i.toString(), n5, n6);
                    }
                }
                b = b.b;
                l += super.j;
            }
            if (b == null || n > size.height) {
                this.a(graphics2);
                graphics.setClip(this.getBounds());
                graphics.drawImage(this.a(), 0, 0, this);
                graphics2.dispose();
                return;
            }
            continue;
        }
    }
    
    public void a(final k k, final flaxchat.a.i i) {
        final boolean j = i.j;
        int n = 0;
        e e = super.d.a;
        while (true) {
            Label_0031: {
                if (!j) {
                    break Label_0031;
                }
                e = e.b;
                ++n;
            }
            if (e != null && i.a(k, e.a) > 0) {
                continue;
            }
            break;
        }
        if (n != super.d.b() - 1) {
            super.d.a(k, n);
            ++super.o;
            this.f();
            this.repaint();
            return;
        }
        if (e != null && i.a(k, e.a) > 0) {
            this.c(k);
            return;
        }
        super.d.a(k, n);
        ++super.o;
        this.f();
        this.repaint();
    }
    
    public void c(final k k) {
        final int o = super.o + 1;
        this.a(k);
        super.d.a(k);
        super.o = o;
        this.e();
        this.repaint();
    }
    
    public void a(final String s) {
        final boolean j = flaxchat.i.i.j;
        final int o = super.o;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        final int o2 = super.o + stringTokenizer.countTokens();
        int n = o;
        while (true) {
            Label_0071: {
                if (!j) {
                    break Label_0071;
                }
                final k k = new k(stringTokenizer.nextToken());
                this.a(k);
                super.d.a(k);
                ++n;
            }
            if (n >= o2) {
                super.o = o2;
                this.repaint();
                return;
            }
            continue;
        }
    }
    
    public k b(final String s) {
        final boolean j = flaxchat.i.i.j;
        e e = super.d.a;
        while (true) {
            Label_0068: {
                if (!j) {
                    break Label_0068;
                }
                final k k = (k)e.a;
                if (k.equals(s)) {
                    super.d.a(e);
                    --super.o;
                    this.f();
                    this.repaint();
                    return k;
                }
                e = e.b;
            }
            if (e == null) {
                return null;
            }
            continue;
        }
    }
    
    private void a(final MouseEvent mouseEvent) {
        final boolean j = flaxchat.i.i.j;
        if (super.n == 0) {
            return;
        }
        final int y = mouseEvent.getY();
        int n = 1;
        e e = super.d.a;
        while (true) {
            Label_0133: {
                if (!j) {
                    break Label_0133;
                }
                final int n2 = ((k)e.a).d - super.i;
                final int n3 = n2 + super.j;
                Label_0126: {
                    if (n != 0 && n2 <= y && n3 >= y) {
                        ((k)e.a).f = true;
                        n = 0;
                        if (!j) {
                            break Label_0126;
                        }
                    }
                    if (!super.p || !super.q) {
                        ((k)e.a).f = false;
                    }
                }
                e = e.b;
            }
            if (e == null) {
                return;
            }
            continue;
        }
    }
    
    public synchronized void a(final k k) {
        final boolean j = flaxchat.i.i.j;
        final FontMetrics fontMetrics = this.getFontMetrics(super.s);
        super.j = fontMetrics.getHeight();
        super.k = fontMetrics.getAscent();
        final String string = k.toString();
        k.d = super.n;
        final int n = super.n + super.j;
        int n2 = super.l + 2;
        final int length = string.length();
        int n3 = 0;
    Label_0422:
        while (true) {
            Label_0415: {
                if (!j) {
                    break Label_0415;
                }
                final char char1 = string.charAt(n3);
                if (char1 == '\u0001' || char1 == '\u0002' || char1 == '\u001f' || char1 == '\u0016' || char1 == '\u000f') {
                    if (n3 >= string.length() - 1) {
                        break Label_0422;
                    }
                    ++n3;
                }
                Label_0354: {
                    if (string.charAt(n3) == '\u0003') {
                        if (n3 >= string.length() - 1) {
                            break Label_0422;
                        }
                        ++n3;
                        final char c = (char)(string.charAt(n3) - '0');
                        Label_0238: {
                            if (c > -1 && c < '\n') {
                                if (string.length() - n3 < 2) {
                                    break Label_0422;
                                }
                                final char c2 = (char)(string.charAt(n3 + 1) - '0');
                                if (c2 > -1 && c2 < '\n') {
                                    if (string.length() - n3 <= 2) {
                                        break Label_0422;
                                    }
                                    n3 += 2;
                                    if (!j) {
                                        break Label_0238;
                                    }
                                }
                                ++n3;
                            }
                        }
                        if (string.charAt(n3) == ',' && n3 < string.length() - 1) {
                            ++n3;
                            final char c3 = (char)(string.charAt(n3) - '0');
                            if (c3 > -1 && c3 < '\n') {
                                if (string.length() - n3 < 2) {
                                    break Label_0422;
                                }
                                final char c4 = (char)(string.charAt(n3 + 1) - '0');
                                if (c4 > -1 && c4 < '\n') {
                                    if (string.length() - n3 <= 2) {
                                        break Label_0422;
                                    }
                                    n3 += 2;
                                    if (!j) {
                                        break Label_0354;
                                    }
                                }
                                ++n3;
                                if (!j) {
                                    break Label_0354;
                                }
                            }
                            --n3;
                        }
                    }
                }
                final char char2 = string.charAt(n3);
                if (char2 != '\u0001' && char2 != '\u0003' && char2 != '\u000f' && char2 != '\u0002' && char2 != '\u001f' && char2 != '\u0016') {
                    n2 += this.a(char2);
                    ++n3;
                }
            }
            if (n3 < length) {
                continue;
            }
            break;
        }
        super.n = n;
        k.e = n;
    }
    
    static {
        b.C = new String[] { z(z("\tUO\u0010D\u0000PY\u0007c\fSR\"p")), z(z("\u0003N]\tr1UH\brT")), z(z("\tOH\"x\u000bH")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0017';
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
                    c2 = 'e';
                    break;
                }
                case 1: {
                    c2 = '<';
                    break;
                }
                case 2: {
                    c2 = '<';
                    break;
                }
                case 3: {
                    c2 = 'd';
                    break;
                }
                default: {
                    c2 = '\u0017';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
