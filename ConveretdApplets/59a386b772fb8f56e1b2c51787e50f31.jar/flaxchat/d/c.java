// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import java.util.Vector;
import java.awt.event.ActionEvent;
import java.net.URL;
import flaxchat.f.e;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import flaxchat.e.g;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import flaxchat.i.b;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public final class c extends a implements MouseListener, MouseMotionListener, ComponentListener, KeyListener
{
    private StringBuffer v;
    private ActionListener w;
    private int x;
    private int y;
    private int z;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private boolean F;
    protected int G;
    protected int H;
    private flaxchat.c.a I;
    private static String[] J;
    
    public c(final int n, final flaxchat.c.a i) {
        this.v = new StringBuffer();
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = false;
        this.G = -1;
        this.H = -1;
        this.setFont(flaxchat.i.b.d(c.J[30]));
        this.I = i;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            super.p = true;
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            super.p = false;
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.g();
        super.u = null;
    }
    
    private int a() {
        return flaxchat.i.b.a(c.J[31], 2);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.a(mouseEvent)) {
            return;
        }
        if (mouseEvent.getClickCount() == this.a()) {
            if (this.a(mouseEvent)) {
                this.setCursor(Cursor.getPredefinedCursor(0));
                return;
            }
            if (this.b(mouseEvent)) {
                this.setCursor(Cursor.getPredefinedCursor(0));
                return;
            }
            if (this.c(mouseEvent)) {
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.a(mouseEvent)) {
            return;
        }
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = false;
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
        if (flaxchat.e.g.a(mouseEvent)) {
            return;
        }
        this.F = true;
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.z == -1) {
            this.z = this.x;
            this.A = this.y;
            this.C = this.A;
            this.B = this.z;
            this.D = this.C;
            this.E = this.B;
        }
        Label_0166: {
            if (this.y > this.D) {
                this.A = this.y;
                this.C = this.D;
                this.z = this.x;
                this.B = this.E;
                if (!flaxchat.d.i.g) {
                    break Label_0166;
                }
            }
            this.A = this.D;
            this.C = this.y;
            this.z = this.E;
            this.B = this.x;
        }
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.a(mouseEvent)) {
            return;
        }
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.z == -1) {
            this.z = this.x;
            this.A = this.y;
            this.C = this.A;
            this.B = this.z;
            this.D = this.C;
            this.E = this.B;
        }
        Label_0161: {
            if (this.y > this.D) {
                this.A = this.y;
                this.C = this.D;
                this.z = this.x;
                this.B = this.E;
                if (!flaxchat.d.i.g) {
                    break Label_0161;
                }
            }
            this.A = this.D;
            this.C = this.y;
            this.z = this.E;
            this.B = this.x;
        }
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.z != -1) {
            return;
        }
        if (this.a(mouseEvent, super.g) != null) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            return;
        }
        if (this.a(mouseEvent, super.e) != null) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            return;
        }
        if (this.a(mouseEvent, super.f) != null) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void a(final ActionListener w) {
        this.w = w;
    }
    
    public void f() {
        if (super.t == null) {
            return;
        }
        final int g = super.t.g();
        final int n = super.n / super.j;
        if (g != super.t.h() - 1 || this.F) {
            super.t.d(n);
            return;
        }
        super.i = super.i + (super.n - g * super.j) - super.j;
        super.t.a(n - 1, n);
    }
    
    public void paint(final Graphics graphics) {
        final boolean g = flaxchat.d.i.g;
        final Dimension size = this.getSize();
        int n = 0;
        int n2 = 0;
        Color black = Color.black;
        int n3 = 0;
        final flaxchat.e.i i = new flaxchat.e.i();
        Font s = super.s;
        final String name = s.getName();
        final int size2 = s.getSize();
        final FontMetrics fontMetrics = this.getFontMetrics(s);
        final Font font = s;
        final Font font2 = new Font(name, 1, size2);
        this.f();
        if (this.F) {
            this.a(this.B, this.C, this.z, this.A);
        }
        final Graphics graphics2 = this.b().getGraphics();
        graphics2.setClip(this.getBounds());
        this.b(graphics2);
        final int height = size.height;
        int n4 = height - super.j + super.k;
        e e = super.d.a;
    Label_0254:
        while (true) {
            Label_0249: {
                if (!g) {
                    break Label_0249;
                }
                final k k = (k)e.a;
                n4 = k.d + height - super.j + super.k;
                if (k.e + height - super.j + super.k - super.i - fontMetrics.getAscent() >= 0) {
                    break Label_0254;
                }
                n2 += k.toString().length();
                e = e.b;
            }
            if (e != null) {
                continue;
            }
            break;
        }
        e b = e;
        while (true) {
            Label_1789: {
                if (!g) {
                    break Label_1789;
                }
                final String string = ((k)b.a).toString();
                int n6;
                int n5 = n6 = super.l + 2;
                int n7 = n4 - super.i;
                Font font3 = font;
                i.b(0);
                int n8 = 0;
                Color color = Color.black;
                Color color2 = Color.white;
                int n9 = 1;
                int n10 = 0;
                int n11 = 0;
                int n12 = 0;
                if (s != font) {
                    s = font;
                    graphics2.setFont(s);
                }
                int n13 = 0;
            Label_1613:
                while (true) {
                    Label_1595: {
                        if (!g) {
                            break Label_1595;
                        }
                        if (string.charAt(n13) == '\u0001') {
                            if (n13 >= string.length() - 1) {
                                break Label_1613;
                            }
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u0002') {
                            if (n13 >= string.length() - 1) {
                                break Label_1613;
                            }
                            Label_0427: {
                                if (font3 == font2) {
                                    font3 = font;
                                    if (!g) {
                                        break Label_0427;
                                    }
                                }
                                font3 = font2;
                            }
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u001f') {
                            if (n13 >= string.length() - 1) {
                                break Label_1613;
                            }
                            Label_0470: {
                                if (n11 != 0) {
                                    n11 = 0;
                                    if (!g) {
                                        break Label_0470;
                                    }
                                }
                                n11 = 1;
                            }
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u0016') {
                            if (n13 >= string.length() - 1) {
                                break Label_1613;
                            }
                            color = Color.white;
                            color2 = Color.black;
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u000f') {
                            if (n13 >= string.length() - 1) {
                                break Label_1613;
                            }
                            Label_0570: {
                                if (n12 != 0) {
                                    color = Color.white;
                                    color2 = Color.black;
                                    n12 = 0;
                                    if (!g) {
                                        break Label_0570;
                                    }
                                }
                                n12 = 1;
                                color = Color.black;
                                color2 = Color.white;
                            }
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u0003') {
                            if (n13 >= string.length() - 1) {
                                break Label_1613;
                            }
                            ++n13;
                            final char c = (char)(string.charAt(n13) - '0');
                            Label_0692: {
                                if (this.a((int)c)) {
                                    if (string.length() - n13 < 2) {
                                        break Label_1613;
                                    }
                                    final char c2 = (char)(string.charAt(n13 + 1) - '0');
                                    if (this.a((int)c2)) {
                                        if (string.length() - n13 <= 2) {
                                            break Label_1613;
                                        }
                                        n9 = c2 + '\n' * c;
                                        n13 += 2;
                                        if (!g) {
                                            break Label_0692;
                                        }
                                    }
                                    n9 = c;
                                    ++n13;
                                }
                            }
                            Label_0820: {
                                if (string.charAt(n13) == ',' && n13 < string.length() - 1) {
                                    ++n13;
                                    final char c3 = (char)(string.charAt(n13) - '0');
                                    if (this.a((int)c3)) {
                                        if (string.length() - n13 < 2) {
                                            break Label_1613;
                                        }
                                        final char c4 = (char)(string.charAt(n13 + 1) - '0');
                                        if (this.a((int)c4)) {
                                            if (string.length() - n13 <= 2) {
                                                break Label_1613;
                                            }
                                            n10 = c4 + '\n' * c3;
                                            n13 += 2;
                                            if (!g) {
                                                break Label_0820;
                                            }
                                        }
                                        n10 = c3;
                                        ++n13;
                                        if (!g) {
                                            break Label_0820;
                                        }
                                    }
                                    --n13;
                                }
                            }
                            if (n9 >= 0 && n9 < 16) {
                                color = flaxchat.d.a.c(n9);
                            }
                            if (n10 >= 0 && n10 < 16) {
                                color2 = flaxchat.d.a.c(n10);
                            }
                        }
                        final char char1 = string.charAt(n13);
                        final int a = this.a(char1);
                        if (char1 != '\u0001' && char1 != '\u0003' && char1 != '\u000f' && char1 != '\u0002' && char1 != '\u001f' && char1 != '\u0016') {
                            if (n5 + a + 1 >= size.width) {
                                if (n7 - fontMetrics.getAscent() + super.j >= 0 && n7 - fontMetrics.getAscent() <= size.height && i.a() > 0) {
                                    Label_1112: {
                                        if (this.F && n2 + n13 >= this.H && n2 + n13 <= this.G) {
                                            if (n3 == 0) {
                                                graphics2.drawString(i.toString(), n6, n7);
                                                n3 = 1;
                                                if (!g) {
                                                    break Label_1112;
                                                }
                                            }
                                            graphics2.setColor(Color.gray);
                                            graphics2.fillRect(n6, n, this.a(i), super.j);
                                            graphics2.setColor(Color.white);
                                            graphics2.drawString(i.toString(), n6, n7);
                                            this.v.append(i.toString());
                                            graphics2.setColor(black);
                                            if (!g) {
                                                break Label_1112;
                                            }
                                        }
                                        graphics2.drawString(i.toString(), n6, n7);
                                    }
                                    i.b(0);
                                }
                                n5 = (n6 = super.l + 2);
                                n4 += super.j;
                                n7 = n4 - super.i;
                            }
                            final int n14 = n4 - super.i;
                            n = n14 - fontMetrics.getAscent();
                            if (n + super.j >= 0 && n <= size.height) {
                                Label_1574: {
                                    if (this.F && n2 + n13 >= this.H && n2 + n13 <= this.G) {
                                        if (i.a() > 0) {
                                            Label_1313: {
                                                if (n3 != 0 || n8 == 1) {
                                                    graphics2.setColor(Color.gray);
                                                    graphics2.fillRect(n6, n, this.a(i), super.j);
                                                    graphics2.setColor(Color.white);
                                                    graphics2.drawString(i.toString(), n6, n7);
                                                    this.v.append(i.toString());
                                                    if (!g) {
                                                        break Label_1313;
                                                    }
                                                }
                                                graphics2.drawString(i.toString(), n6, n7);
                                            }
                                            n3 = 1;
                                        }
                                        i.b(0);
                                        n6 = n5;
                                        n7 = n14;
                                        graphics2.setColor(black);
                                        if (!g) {
                                            break Label_1574;
                                        }
                                    }
                                    Label_1404: {
                                        if (color2 != Color.white) {
                                            if (color2 != black) {
                                                graphics2.setColor(color2);
                                                graphics2.fillRect(n5, n, a, super.j);
                                                graphics2.setColor(black);
                                                if (!g) {
                                                    break Label_1404;
                                                }
                                            }
                                            graphics2.fillRect(n5, n, a, super.j);
                                        }
                                    }
                                    Label_1535: {
                                        if (s != font3) {
                                            if (i.a() > 0) {
                                                graphics2.drawString(i.toString(), n6, n7);
                                            }
                                            if (color != black) {
                                                graphics2.setColor(color);
                                                black = color;
                                            }
                                            i.b(0);
                                            n6 = n5;
                                            n7 = n14;
                                            s = font3;
                                            graphics2.setFont(s);
                                            if (!g) {
                                                break Label_1535;
                                            }
                                        }
                                        if (color != black) {
                                            if (i.a() > 0) {
                                                graphics2.drawString(i.toString(), n6, n7);
                                            }
                                            i.b(0);
                                            n6 = n5;
                                            n7 = n14;
                                            graphics2.setColor(color);
                                            black = color;
                                        }
                                    }
                                    if (n11 != 0) {
                                        graphics2.drawLine(n5, n14 + fontMetrics.getDescent() - 3, n5 + a - 1, n14 + fontMetrics.getDescent() - 3);
                                    }
                                }
                                i.a(char1);
                            }
                            n5 += a;
                            ++n8;
                            ++n13;
                        }
                    }
                    if (n13 < string.length() && n <= size.height) {
                        continue;
                    }
                    break;
                }
                n2 += n13;
                Label_1773: {
                    if (i.a() > 0) {
                        if (this.F && n2 >= this.H && n2 <= this.G) {
                            Label_1751: {
                                if (n3 != 0 || n8 == 1) {
                                    graphics2.setColor(Color.gray);
                                    graphics2.fillRect(n6, n, this.a(i), super.j);
                                    graphics2.setColor(Color.white);
                                    graphics2.drawString(i.toString(), n6, n7);
                                    this.v.append(i.toString());
                                    graphics2.setColor(black);
                                    if (!g) {
                                        break Label_1751;
                                    }
                                }
                                graphics2.drawString(i.toString(), n6, n7);
                            }
                            n3 = 1;
                            if (!g) {
                                break Label_1773;
                            }
                        }
                        graphics2.drawString(i.toString(), n6, n7);
                    }
                }
                b = b.b;
                n4 += super.j;
            }
            if (b == null || n > size.height) {
                this.a(graphics2);
                this.c(graphics2);
                graphics.setClip(this.getBounds());
                graphics.drawImage(this.b(), 0, 0, this);
                graphics2.dispose();
                return;
            }
            continue;
        }
    }
    
    public String l() {
        return this.v.toString();
    }
    
    private boolean a(final int n) {
        return n > -1 && n < 10;
    }
    
    private boolean a(final MouseEvent mouseEvent) {
        String s = this.a(mouseEvent, super.g);
        if (s == null) {
            return false;
        }
        try {
            if (s.charAt(0) == 'w' || s.charAt(0) == 'W') {
                s = c.J[28] + s;
            }
            return this.a(new URL(s), c.J[33], mouseEvent);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean b(final MouseEvent mouseEvent) {
        final String a = this.a(mouseEvent, super.e);
        return a != null && this.a(new flaxchat.h.c(a, "", 0), c.J[32], mouseEvent);
    }
    
    private boolean c(final MouseEvent mouseEvent) {
        final String a = this.a(mouseEvent, super.f);
        if (a == null) {
            return false;
        }
        String s = a.trim();
        if (s.startsWith("<")) {
            s = s.substring(1);
        }
        if (s.startsWith(">")) {
            s = s.substring(0, s.length() - 1);
        }
        return this.a(new flaxchat.h.g("", s), c.J[27], mouseEvent);
    }
    
    private boolean a(final Object o, final String s, final MouseEvent mouseEvent) {
        if (this.w == null) {
            return true;
        }
        mouseEvent.consume();
        this.w.actionPerformed(new ActionEvent(o, 1001, s));
        return true;
    }
    
    private String a(final MouseEvent mouseEvent, final Vector vector) {
        final boolean g = flaxchat.d.i.g;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Dimension size = this.getSize();
        int n = 0;
        while (true) {
            Label_0140: {
                if (!g) {
                    break Label_0140;
                }
                final int n2 = vector.elementAt(n).e + size.height - super.j;
                final int d = vector.elementAt(n).d;
                final String string = vector.elementAt(n).toString();
                if (n2 - super.i <= y && n2 - super.i + super.j >= y && x >= d && x <= d + this.d(string.toString())) {
                    return string;
                }
                ++n;
            }
            if (n >= vector.size()) {
                return null;
            }
            continue;
        }
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4) {
    }
    
    public synchronized void a(final k k) {
        final boolean g = flaxchat.d.i.g;
        final FontMetrics fontMetrics = this.getFontMetrics(super.s);
        final flaxchat.e.i i = new flaxchat.e.i();
        final flaxchat.e.i j = new flaxchat.e.i();
        final flaxchat.e.i l = new flaxchat.e.i();
        final flaxchat.e.i m = new flaxchat.e.i();
        super.j = fontMetrics.getHeight();
        super.k = fontMetrics.getAscent();
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        final Dimension size = this.getSize();
        int n9 = 32;
        k.d = super.n;
        int n10 = super.n + super.j;
        int n11 = super.l + 2;
        final String string = k.toString();
        final int length = string.length();
        int n12 = 0;
    Label_1034:
        while (true) {
            Label_1027: {
                if (!g) {
                    break Label_1027;
                }
                final char char1 = string.charAt(n12);
                if (char1 == ' ' && i.a() > 0) {
                    super.e.addElement(new k(i.toString(), n, n2 - super.j, 0));
                    i.b(0);
                }
                if (char1 == '>' && m.a() > 0) {
                    super.f.addElement(new k(m.toString(), n7, n8 - super.j, 0));
                    m.b(0);
                }
                if (char1 == ' ') {
                    this.a(l, n5, n6);
                }
                if (char1 == ' ' && j.a() > 0) {
                    Label_0376: {
                        if (j.b(c.J[29])) {
                            super.g.addElement(new k(j.toString(), n3, n4 - super.j, 0));
                            if (!g) {
                                break Label_0376;
                            }
                        }
                        if (j.b(c.J[28])) {
                            super.g.addElement(new k(j.toString(), n3, n4 - super.j, 0));
                        }
                    }
                    j.b(0);
                }
                if (char1 == '\u0001' || char1 == '\u0002' || char1 == '\u001f' || char1 == '\u0016' || char1 == '\u000f') {
                    if (n12 >= string.length() - 1) {
                        break Label_1034;
                    }
                    ++n12;
                }
                Label_0665: {
                    if (string.charAt(n12) == '\u0003') {
                        if (n12 >= string.length() - 1) {
                            break Label_1034;
                        }
                        ++n12;
                        final char c = (char)(string.charAt(n12) - '0');
                        Label_0543: {
                            if (c > -1 && c < '\n') {
                                if (string.length() - n12 < 2) {
                                    break Label_1034;
                                }
                                final char c2 = (char)(string.charAt(n12 + 1) - '0');
                                if (c2 > -1 && c2 < '\n') {
                                    if (string.length() - n12 <= 2) {
                                        break Label_1034;
                                    }
                                    n12 += 2;
                                    if (!g) {
                                        break Label_0543;
                                    }
                                }
                                ++n12;
                            }
                        }
                        if (string.charAt(n12) == ',' && n12 < string.length() - 1) {
                            ++n12;
                            final char c3 = (char)(string.charAt(n12) - '0');
                            if (c3 > -1 && c3 < '\n') {
                                if (string.length() - n12 < 2) {
                                    break Label_1034;
                                }
                                final char c4 = (char)(string.charAt(n12 + 1) - '0');
                                if (c4 > -1 && c4 < '\n') {
                                    if (string.length() - n12 <= 2) {
                                        break Label_1034;
                                    }
                                    n12 += 2;
                                    if (!g) {
                                        break Label_0665;
                                    }
                                }
                                ++n12;
                                if (!g) {
                                    break Label_0665;
                                }
                            }
                            --n12;
                        }
                    }
                }
                final char char2 = string.charAt(n12);
                final int a = this.a(char2);
                if (char2 != '\u0001' && char2 != '\u0003' && char2 != '\u000f' && char2 != '\u0002' && char2 != '\u001f' && char2 != '\u0016') {
                    if (n11 + a + 1 >= size.width) {
                        n11 = super.l + 2;
                        n10 += super.j;
                    }
                    Label_1013: {
                        if (n9 == 32 || l.a() > 0 || i.a() > 0 || j.a() > 0 || m.a() > 0) {
                            Label_0845: {
                                if (i.a() > 0) {
                                    i.a(char2);
                                    if (!g) {
                                        break Label_0845;
                                    }
                                }
                                if (a(char2) && a(j, l, m)) {
                                    i.a(char2);
                                    n = n11;
                                    n2 = n10;
                                }
                            }
                            Label_0901: {
                                if (m.a() > 0) {
                                    m.a(char2);
                                    if (!g) {
                                        break Label_0901;
                                    }
                                }
                                if (b(char2) && a(j, l, i)) {
                                    m.a(' ');
                                    n7 = n11;
                                    n8 = n10;
                                }
                            }
                            Label_0957: {
                                if (l.a() > 0) {
                                    l.a(char2);
                                    if (!g) {
                                        break Label_0957;
                                    }
                                }
                                if (c(char2) && a(j, i, m)) {
                                    l.a(char2);
                                    n5 = n11;
                                    n6 = n10;
                                }
                            }
                            if (j.a() > 0) {
                                j.a(char2);
                                if (!g) {
                                    break Label_1013;
                                }
                            }
                            if (d(char2) && a(i, l, m)) {
                                j.a(char2);
                                n3 = n11;
                                n4 = n10;
                            }
                        }
                    }
                    n11 += a;
                    ++n12;
                    n9 = char2;
                }
            }
            if (n12 < length) {
                continue;
            }
            break;
        }
        if (i.a() > 0) {
            super.e.addElement(new k(i.toString(), n, n2 - super.j, 0));
        }
        if (m.a() > 0) {
            super.f.addElement(new k(m.toString(), n7, n8 - super.j, 0));
        }
        this.a(l, n5, n6);
        Label_1207: {
            if (j.b(c.J[28])) {
                super.g.addElement(new k(j.toString(), n3, n4 - super.j, 0));
                if (!g) {
                    break Label_1207;
                }
            }
            if (j.b(c.J[29])) {
                super.g.addElement(new k(j.toString(), n3, n4 - super.j, 0));
            }
        }
        super.n = n10;
        k.e = n10;
    }
    
    private static boolean a(final char c) {
        return c == '#' || c == '+' || c == '&';
    }
    
    private static boolean b(final char c) {
        return c == '<';
    }
    
    private static boolean c(final char c) {
        return c == ':' || c == '8' || c == 'x' || c == 'X' || c == ';';
    }
    
    private static boolean d(final char c) {
        return c == 'w' || c == 'W' || c == 'h' || c == 'H';
    }
    
    private static boolean a(final flaxchat.e.i i, final flaxchat.e.i j, final flaxchat.e.i k) {
        return i.a() + j.a() + k.a() == 0;
    }
    
    private void a(final flaxchat.e.i i, final int n, final int n2) {
        final boolean g = i.g;
        final String string = i.toString();
        if (i.a() <= 1) {
            return;
        }
        int n3 = -1;
        Label_0509: {
            if (string.equals(c.J[7]) || string.equals(c.J[18])) {
                n3 = 0;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[26]) || string.equals(c.J[11])) {
                n3 = 5;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[1]) || string.equals(c.J[2])) {
                n3 = 11;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[2]) || string.equals(c.J[22])) {
                n3 = 11;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[13]) || string.equals(c.J[20]) || string.equals(c.J[24])) {
                n3 = 7;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[6]) || string.equals(c.J[9])) {
                n3 = 8;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[10]) || string.equals(c.J[4])) {
                n3 = 9;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[3]) || string.equals(c.J[14])) {
                n3 = 10;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[8]) || string.equals(c.J[16])) {
                n3 = 1;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.startsWith(c.J[5]) || string.equals(c.J[17]) || string.equals(c.J[25])) {
                n3 = 2;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equalsIgnoreCase(c.J[23]) || string.equalsIgnoreCase(c.J[0]) || string.equals(c.J[12]) || string.equalsIgnoreCase(c.J[19])) {
                n3 = 3;
                if (!g) {
                    break Label_0509;
                }
            }
            if (string.equals(c.J[21]) || string.equals(c.J[15])) {
                n3 = 4;
            }
        }
        if (n3 != -1) {
            super.h.addElement(new k(null, n, n2 - super.j, n3));
        }
        i.b(0);
    }
    
    static {
        c.J = new String[] { z(z("w*")), z(z("w}")), z(z("w}/")), z(z("ws ")), z(z("ww-")), z(z("\u0015\u001e")), z(z("we")), z(z("ws")), z(z("wr")), z(z("ww8")), z(z("wp")), z(z("uw.")), z(z("w\u0084")), z(z("wu")), z(z("wsy")), z(z("vw.")), z(z("ww/")), z(z("w\u001e")), z(z("ww.")), z(z("wwW")), z(z("w\u0013")), z(z("vs")), z(z("w}*<")), z(z("v*")), z(z("wwN")), z(z("wwC")), z(z("us")), z(z("8)bf")), z(z("%.sdxbu")), z(z(":-p:")), z(z(" )`R-#.")), z(z("%#wq0\u000e6nw)\u000e5rz6")), z(z(".2fz,(6")), z(z("8(k")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'B';
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
                    c2 = 'M';
                    break;
                }
                case 1: {
                    c2 = 'Z';
                    break;
                }
                case 2: {
                    c2 = '\u0007';
                    break;
                }
                case 3: {
                    c2 = '\u0014';
                    break;
                }
                default: {
                    c2 = 'B';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
