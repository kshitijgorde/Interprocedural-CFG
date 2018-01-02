// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.util.Vector;
import java.awt.event.ActionEvent;
import flaxchat.f.g;
import java.net.URL;
import flaxchat.h.e;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import flaxchat.a.j;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Cursor;
import flaxchat.a.h;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import flaxchat.d.b;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public final class c extends a implements MouseListener, MouseMotionListener, ComponentListener, KeyListener
{
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
        this.setFont(flaxchat.d.b.d(c.J[30]));
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
        this.f();
        super.u = null;
    }
    
    private int a() {
        return flaxchat.d.b.a(c.J[0], 2);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.a.h.a(mouseEvent)) {
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
        if (flaxchat.a.h.a(mouseEvent)) {
            return;
        }
        final String trim = this.c().trim();
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
        if (trim.length() > 20) {
            this.I.i(trim.toString());
        }
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
                if (!flaxchat.i.i.j) {
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
        if (flaxchat.a.h.a(mouseEvent)) {
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
                if (!flaxchat.i.i.j) {
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
    
    public void e() {
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
        final Graphics graphics2 = this.a().getGraphics();
        graphics2.setClip(this.getBounds());
        graphics.setClip(this.getBounds());
        this.a(graphics2);
        graphics.drawImage(this.a(), 0, 0, this);
        graphics2.dispose();
    }
    
    public String a(final Graphics graphics) {
        final boolean j = flaxchat.i.i.j;
        final j i = new j();
        final Dimension size = this.getSize();
        int n = 0;
        int n2 = 0;
        Color black = Color.black;
        int n3 = 0;
        final j k = new j();
        Font s = super.s;
        final String name = s.getName();
        final int size2 = s.getSize();
        final FontMetrics fontMetrics = this.getFontMetrics(s);
        final Font font = s;
        final Font font2 = new Font(name, 1, size2);
        this.e();
        if (this.F) {
            this.a(this.B, this.C, this.z, this.A);
        }
        this.b(graphics);
        final int height = size.height;
        int n4 = height - super.j + super.k;
        e e = super.d.a;
    Label_0244:
        while (true) {
            Label_0239: {
                if (!j) {
                    break Label_0239;
                }
                final k l = (k)e.a;
                n4 = l.d + height - super.j + super.k;
                if (l.e + height - super.j + super.k - super.i - fontMetrics.getAscent() >= 0) {
                    break Label_0244;
                }
                n2 += l.toString().length();
                e = e.b;
            }
            if (e != null) {
                continue;
            }
            break;
        }
        e b = e;
        while (true) {
            Label_1762: {
                if (!j) {
                    break Label_1762;
                }
                final String string = ((k)b.a).toString();
                int n6;
                int n5 = n6 = super.l + 2;
                int n7 = n4 - super.i;
                Font font3 = font;
                k.b(0);
                int n8 = 0;
                Color color = Color.black;
                Color color2 = Color.white;
                int n9 = 1;
                int n10 = 0;
                int n11 = 0;
                int n12 = 0;
                if (s != font) {
                    s = font;
                    graphics.setFont(s);
                }
                int n13 = 0;
            Label_1588:
                while (true) {
                    Label_1569: {
                        if (!j) {
                            break Label_1569;
                        }
                        if (string.charAt(n13) == '\u0001') {
                            if (n13 >= string.length() - 1) {
                                break Label_1588;
                            }
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u0002') {
                            if (n13 >= string.length() - 1) {
                                break Label_1588;
                            }
                            Label_0416: {
                                if (font3 == font2) {
                                    font3 = font;
                                    if (!j) {
                                        break Label_0416;
                                    }
                                }
                                font3 = font2;
                            }
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u001f') {
                            if (n13 >= string.length() - 1) {
                                break Label_1588;
                            }
                            Label_0459: {
                                if (n11 != 0) {
                                    n11 = 0;
                                    if (!j) {
                                        break Label_0459;
                                    }
                                }
                                n11 = 1;
                            }
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u0016') {
                            if (n13 >= string.length() - 1) {
                                break Label_1588;
                            }
                            color = Color.white;
                            color2 = Color.black;
                            ++n13;
                        }
                        if (string.charAt(n13) == '\u000f') {
                            if (n13 >= string.length() - 1) {
                                break Label_1588;
                            }
                            Label_0559: {
                                if (n12 != 0) {
                                    color = Color.white;
                                    color2 = Color.black;
                                    n12 = 0;
                                    if (!j) {
                                        break Label_0559;
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
                                break Label_1588;
                            }
                            ++n13;
                            final char c = (char)(string.charAt(n13) - '0');
                            Label_0681: {
                                if (this.a((int)c)) {
                                    if (string.length() - n13 < 2) {
                                        break Label_1588;
                                    }
                                    final char c2 = (char)(string.charAt(n13 + 1) - '0');
                                    if (this.a((int)c2)) {
                                        if (string.length() - n13 <= 2) {
                                            break Label_1588;
                                        }
                                        n9 = c2 + '\n' * c;
                                        n13 += 2;
                                        if (!j) {
                                            break Label_0681;
                                        }
                                    }
                                    n9 = c;
                                    ++n13;
                                }
                            }
                            Label_0809: {
                                if (string.charAt(n13) == ',' && n13 < string.length() - 1) {
                                    ++n13;
                                    final char c3 = (char)(string.charAt(n13) - '0');
                                    if (this.a((int)c3)) {
                                        if (string.length() - n13 < 2) {
                                            break Label_1588;
                                        }
                                        final char c4 = (char)(string.charAt(n13 + 1) - '0');
                                        if (this.a((int)c4)) {
                                            if (string.length() - n13 <= 2) {
                                                break Label_1588;
                                            }
                                            n10 = c4 + '\n' * c3;
                                            n13 += 2;
                                            if (!j) {
                                                break Label_0809;
                                            }
                                        }
                                        n10 = c3;
                                        ++n13;
                                        if (!j) {
                                            break Label_0809;
                                        }
                                    }
                                    --n13;
                                }
                            }
                            if (n9 >= 0 && n9 < 16) {
                                color = flaxchat.i.a.c(n9);
                            }
                            if (n10 >= 0 && n10 < 16) {
                                color2 = flaxchat.i.a.c(n10);
                            }
                        }
                        final char char1 = string.charAt(n13);
                        final int a = this.a(char1);
                        if (char1 != '\u0001' && char1 != '\u0003' && char1 != '\u000f' && char1 != '\u0002' && char1 != '\u001f' && char1 != '\u0016') {
                            if (n5 + a + 1 >= size.width) {
                                if (n7 - fontMetrics.getAscent() + super.j >= 0 && n7 - fontMetrics.getAscent() <= size.height && k.a() > 0) {
                                    Label_1092: {
                                        if (this.F && n2 + n13 >= this.H && n2 + n13 <= this.G) {
                                            if (n3 == 0) {
                                                graphics.drawString(k.toString(), n6, n7);
                                                n3 = 1;
                                                if (!j) {
                                                    break Label_1092;
                                                }
                                            }
                                            graphics.setColor(Color.gray);
                                            graphics.fillRect(n6, n, this.a(k), super.j);
                                            graphics.setColor(Color.white);
                                            graphics.drawString(k.toString(), n6, n7);
                                            i.a(k.toString());
                                            graphics.setColor(black);
                                            if (!j) {
                                                break Label_1092;
                                            }
                                        }
                                        graphics.drawString(k.toString(), n6, n7);
                                    }
                                    k.b(0);
                                }
                                n5 = (n6 = super.l + 2);
                                n4 += super.j;
                                n7 = n4 - super.i;
                                i.a("\n");
                            }
                            final int n14 = n4 - super.i;
                            n = n14 - fontMetrics.getAscent();
                            if (n + super.j >= 0 && n <= size.height) {
                                Label_1548: {
                                    if (this.F && n2 + n13 >= this.H && n2 + n13 <= this.G) {
                                        if (k.a() > 0) {
                                            Label_1296: {
                                                if (n3 != 0 || n8 == 1) {
                                                    graphics.setColor(Color.gray);
                                                    graphics.fillRect(n6, n, this.a(k), super.j);
                                                    graphics.setColor(Color.white);
                                                    graphics.drawString(k.toString(), n6, n7);
                                                    i.a(k.toString());
                                                    if (!j) {
                                                        break Label_1296;
                                                    }
                                                }
                                                graphics.drawString(k.toString(), n6, n7);
                                            }
                                            n3 = 1;
                                        }
                                        k.b(0);
                                        n6 = n5;
                                        n7 = n14;
                                        graphics.setColor(black);
                                        if (!j) {
                                            break Label_1548;
                                        }
                                    }
                                    Label_1384: {
                                        if (color2 != Color.white) {
                                            if (color2 != black) {
                                                graphics.setColor(color2);
                                                graphics.fillRect(n5, n, a, super.j);
                                                graphics.setColor(black);
                                                if (!j) {
                                                    break Label_1384;
                                                }
                                            }
                                            graphics.fillRect(n5, n, a, super.j);
                                        }
                                    }
                                    Label_1510: {
                                        if (s != font3) {
                                            if (k.a() > 0) {
                                                graphics.drawString(k.toString(), n6, n7);
                                            }
                                            if (color != black) {
                                                graphics.setColor(color);
                                                black = color;
                                            }
                                            k.b(0);
                                            n6 = n5;
                                            n7 = n14;
                                            s = font3;
                                            graphics.setFont(s);
                                            if (!j) {
                                                break Label_1510;
                                            }
                                        }
                                        if (color != black) {
                                            if (k.a() > 0) {
                                                graphics.drawString(k.toString(), n6, n7);
                                            }
                                            k.b(0);
                                            n6 = n5;
                                            n7 = n14;
                                            graphics.setColor(color);
                                            black = color;
                                        }
                                    }
                                    if (n11 != 0) {
                                        graphics.drawLine(n5, n14 + fontMetrics.getDescent() - 3, n5 + a - 1, n14 + fontMetrics.getDescent() - 3);
                                    }
                                }
                                k.a(char1);
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
                Label_1739: {
                    if (k.a() > 0) {
                        if (this.F && n2 >= this.H && n2 <= this.G) {
                            Label_1718: {
                                if (n3 != 0 || n8 == 1) {
                                    graphics.setColor(Color.gray);
                                    graphics.fillRect(n6, n, this.a(k), super.j);
                                    graphics.setColor(Color.white);
                                    graphics.drawString(k.toString(), n6, n7);
                                    i.a(k.toString());
                                    graphics.setColor(black);
                                    if (!j) {
                                        break Label_1718;
                                    }
                                }
                                graphics.drawString(k.toString(), n6, n7);
                            }
                            n3 = 1;
                            if (!j) {
                                break Label_1739;
                            }
                        }
                        graphics.drawString(k.toString(), n6, n7);
                    }
                }
                b = b.b;
                n4 += super.j;
                i.a("\n");
            }
            if (b == null || n > size.height) {
                this.a(graphics);
                this.c(graphics);
                return i.toString();
            }
            continue;
        }
    }
    
    public String c() {
        final Graphics graphics = this.a().getGraphics();
        graphics.setClip(this.getBounds());
        return this.a(graphics);
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
            return this.a(new URL(s), c.J[31], mouseEvent);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean b(final MouseEvent mouseEvent) {
        final String a = this.a(mouseEvent, super.e);
        return a != null && this.a(new flaxchat.f.c(a, "", 0), c.J[33], mouseEvent);
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
        return this.a(new g("", s), c.J[32], mouseEvent);
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
        final boolean j = flaxchat.i.i.j;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Dimension size = this.getSize();
        int n = 0;
        while (true) {
            Label_0140: {
                if (!j) {
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
    
    private void c(final int h) {
        this.H = h;
        if (h == -1) {
            h.c();
        }
    }
    
    private void f(final int g) {
        this.G = g;
        if (g == -1) {
            flaxchat.a.h.c();
        }
    }
    
    private void g(final int h) {
        this.H = h;
    }
    
    private void h(final int g) {
        this.G = g;
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4) {
        final boolean j = flaxchat.i.i.j;
        this.g(-1);
        this.h(0);
        int n5 = 0;
        final Dimension size = this.getSize();
        e e = super.d.a;
        while (true) {
            Label_0764: {
                if (!j) {
                    break Label_0764;
                }
                final k k = (k)e.a;
                final String string = k.toString();
                Label_0757: {
                    if (n2 > k.e + size.height - super.j + super.k - super.i - super.j) {
                        n5 += string.length();
                        if (!j) {
                            break Label_0757;
                        }
                    }
                    int d = k.d;
                    if (d + size.height - super.j + super.k - super.i - super.j > n4) {
                        if (this.G == -1) {
                            this.f(n5);
                        }
                        return;
                    }
                    int n6 = super.l + 2;
                    int n7 = 0;
                Label_0750:
                    while (true) {
                        Label_0740: {
                            if (!j) {
                                break Label_0740;
                            }
                            if (a(string.charAt(n7))) {
                                if (n7 >= string.length() - 1) {
                                    break Label_0750;
                                }
                                ++n7;
                            }
                            Label_0451: {
                                if (string.charAt(n7) == '\u0003') {
                                    if (n7 >= string.length() - 1) {
                                        break Label_0750;
                                    }
                                    ++n7;
                                    final char c = (char)(string.charAt(n7) - '0');
                                    Label_0329: {
                                        if (c > -1 && c < '\n') {
                                            if (string.length() - n7 < 2) {
                                                break Label_0750;
                                            }
                                            final char c2 = (char)(string.charAt(n7 + 1) - '0');
                                            if (c2 > -1 && c2 < '\n') {
                                                if (string.length() - n7 <= 2) {
                                                    break Label_0750;
                                                }
                                                n7 += 2;
                                                if (!j) {
                                                    break Label_0329;
                                                }
                                            }
                                            ++n7;
                                        }
                                    }
                                    if (string.charAt(n7) == ',' && n7 < string.length() - 1) {
                                        ++n7;
                                        final char c3 = (char)(string.charAt(n7) - '0');
                                        if (c3 > -1 && c3 < '\n') {
                                            if (string.length() - n7 < 2) {
                                                break Label_0750;
                                            }
                                            final char c4 = (char)(string.charAt(n7 + 1) - '0');
                                            if (c4 > -1 && c4 < '\n') {
                                                if (string.length() - n7 <= 2) {
                                                    break Label_0750;
                                                }
                                                n7 += 2;
                                                if (!j) {
                                                    break Label_0451;
                                                }
                                            }
                                            ++n7;
                                            if (!j) {
                                                break Label_0451;
                                            }
                                        }
                                        --n7;
                                    }
                                }
                            }
                            final char char1 = string.charAt(n7);
                            final int a = this.a(char1);
                            if (b(char1)) {
                                if (n6 + a + 1 >= size.width) {
                                    final int n8 = d + size.height - super.j + super.k - super.i - super.j;
                                    if (n2 > n8 && n2 < n8 + super.j && this.H == -1) {
                                        this.c(n5 + n7);
                                    }
                                    n6 = super.l + 2;
                                    d += super.j;
                                }
                                final int n9 = d + size.height - super.j + super.k - super.i - super.j;
                                if (n9 > n4) {
                                    return;
                                }
                                if (n2 > n9 && n2 < n9 + super.j && this.H == -1 && (n <= super.l + 2 || (n >= n6 && n <= n6 + a))) {
                                    this.c(n5 + n7);
                                }
                                if (n4 > n9 && n4 < n9 + super.j && this.H != -1 && (n3 <= super.l + 2 || (n3 >= n6 && n3 <= n6 + a))) {
                                    this.f(n5 + n7);
                                }
                                n6 += a;
                                ++n7;
                            }
                        }
                        if (n7 < string.length()) {
                            continue;
                        }
                        break;
                    }
                    n5 += n7;
                }
                e = e.b;
            }
            if (e == null) {
                if (this.H == -1) {
                    this.c(n5);
                }
                if (this.H != -1 && this.G == -1) {
                    this.f(n5);
                }
                return;
            }
            continue;
        }
    }
    
    public static boolean a(final char c) {
        return c == '\u0001' || c == '\u0002' || c == '\u000f' || c == '\u0016' || c == '\u001f';
    }
    
    public static boolean b(final char c) {
        return c != '\u0001' && c != '\u0002' && c != '\u0003' && c != '\u000f' && c != '\u0016' && c != '\u001f';
    }
    
    public synchronized void a(final k k) {
        final boolean j = flaxchat.i.i.j;
        final FontMetrics fontMetrics = this.getFontMetrics(super.s);
        final j i = new j();
        final j l = new j();
        final j m = new j();
        final j j2 = new j();
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
        char c = ' ';
        k.d = super.n;
        int n9 = super.n + super.j;
        int n10 = super.l + 2;
        final String string = k.toString();
        final int length = string.length();
        int n11 = 0;
    Label_1038:
        while (true) {
            Label_1031: {
                if (!j) {
                    break Label_1031;
                }
                final char char1 = string.charAt(n11);
                if (c(char1) && i.a() > 0) {
                    super.e.addElement(new k(i.toString(), n, n2 - super.j, 0));
                    i.b(0);
                }
                if (char1 == '>' && j2.a() > 0) {
                    super.f.addElement(new k(j2.toString(), n7, n8 - super.j, 0));
                    j2.b(0);
                }
                if (c(char1)) {
                    this.a(m, n5, n6);
                }
                if (c(char1) && l.a() > 0) {
                    Label_0379: {
                        if (l.b(flaxchat.i.c.J[29])) {
                            super.g.addElement(new k(l.toString(), n3, n4 - super.j, 0));
                            if (!j) {
                                break Label_0379;
                            }
                        }
                        if (l.b(flaxchat.i.c.J[28])) {
                            super.g.addElement(new k(l.toString(), n3, n4 - super.j, 0));
                        }
                    }
                    l.b(0);
                }
                if (char1 == '\u0001' || char1 == '\u0002' || char1 == '\u001f' || char1 == '\u0016' || char1 == '\u000f') {
                    if (n11 >= string.length() - 1) {
                        break Label_1038;
                    }
                    ++n11;
                }
                Label_0668: {
                    if (string.charAt(n11) == '\u0003') {
                        if (n11 >= string.length() - 1) {
                            break Label_1038;
                        }
                        ++n11;
                        final char c2 = (char)(string.charAt(n11) - '0');
                        Label_0546: {
                            if (c2 > -1 && c2 < '\n') {
                                if (string.length() - n11 < 2) {
                                    break Label_1038;
                                }
                                final char c3 = (char)(string.charAt(n11 + 1) - '0');
                                if (c3 > -1 && c3 < '\n') {
                                    if (string.length() - n11 <= 2) {
                                        break Label_1038;
                                    }
                                    n11 += 2;
                                    if (!j) {
                                        break Label_0546;
                                    }
                                }
                                ++n11;
                            }
                        }
                        if (string.charAt(n11) == ',' && n11 < string.length() - 1) {
                            ++n11;
                            final char c4 = (char)(string.charAt(n11) - '0');
                            if (c4 > -1 && c4 < '\n') {
                                if (string.length() - n11 < 2) {
                                    break Label_1038;
                                }
                                final char c5 = (char)(string.charAt(n11 + 1) - '0');
                                if (c5 > -1 && c5 < '\n') {
                                    if (string.length() - n11 <= 2) {
                                        break Label_1038;
                                    }
                                    n11 += 2;
                                    if (!j) {
                                        break Label_0668;
                                    }
                                }
                                ++n11;
                                if (!j) {
                                    break Label_0668;
                                }
                            }
                            --n11;
                        }
                    }
                }
                final char char2 = string.charAt(n11);
                final int a = this.a(char2);
                if (char2 != '\u0001' && char2 != '\u0003' && char2 != '\u000f' && char2 != '\u0002' && char2 != '\u001f' && char2 != '\u0016') {
                    if (n10 + a + 1 >= size.width) {
                        n10 = super.l + 2;
                        n9 += super.j;
                    }
                    Label_1017: {
                        if (c(c) || m.a() > 0 || i.a() > 0 || l.a() > 0 || j2.a() > 0) {
                            Label_0849: {
                                if (i.a() > 0) {
                                    i.a(char2);
                                    if (!j) {
                                        break Label_0849;
                                    }
                                }
                                if (d(char2) && a(l, m, j2)) {
                                    i.a(char2);
                                    n = n10;
                                    n2 = n9;
                                }
                            }
                            Label_0905: {
                                if (j2.a() > 0) {
                                    j2.a(char2);
                                    if (!j) {
                                        break Label_0905;
                                    }
                                }
                                if (e(char2) && a(l, m, i)) {
                                    j2.a(' ');
                                    n7 = n10;
                                    n8 = n9;
                                }
                            }
                            Label_0961: {
                                if (m.a() > 0) {
                                    m.a(char2);
                                    if (!j) {
                                        break Label_0961;
                                    }
                                }
                                if (f(char2) && a(l, i, j2)) {
                                    m.a(char2);
                                    n5 = n10;
                                    n6 = n9;
                                }
                            }
                            if (l.a() > 0) {
                                l.a(char2);
                                if (!j) {
                                    break Label_1017;
                                }
                            }
                            if (g(char2) && a(i, m, j2)) {
                                l.a(char2);
                                n3 = n10;
                                n4 = n9;
                            }
                        }
                    }
                    n10 += a;
                    ++n11;
                    c = char2;
                }
            }
            if (n11 < length) {
                continue;
            }
            break;
        }
        if (i.a() > 0) {
            super.e.addElement(new k(i.toString(), n, n2 - super.j, 0));
        }
        if (j2.a() > 0) {
            super.f.addElement(new k(j2.toString(), n7, n8 - super.j, 0));
        }
        this.a(m, n5, n6);
        Label_1211: {
            if (l.b(flaxchat.i.c.J[28])) {
                super.g.addElement(new k(l.toString(), n3, n4 - super.j, 0));
                if (!j) {
                    break Label_1211;
                }
            }
            if (l.b(flaxchat.i.c.J[29])) {
                super.g.addElement(new k(l.toString(), n3, n4 - super.j, 0));
            }
        }
        super.n = n9;
        k.e = n9;
    }
    
    private static boolean c(final char c) {
        return c == ' ' || c == '(' || c == '{' || c == '[' || c == ')' || c == '}' || c == ']';
    }
    
    private static boolean d(final char c) {
        return c == '#' || c == '+' || c == '&';
    }
    
    private static boolean e(final char c) {
        return c == '<';
    }
    
    private static boolean f(final char c) {
        return c == ':' || c == '8' || c == 'x' || c == 'X' || c == ';';
    }
    
    private static boolean g(final char c) {
        return c == 'w' || c == 'W' || c == 'h' || c == 'H';
    }
    
    private static boolean a(final j j, final j i, final j k) {
        return j.a() + i.a() + k.a() == 0;
    }
    
    private void a(final j j, final int n, final int n2) {
        final boolean i = flaxchat.i.i.j;
        final String string = j.toString();
        if (j.a() <= 1) {
            return;
        }
        int n3 = -1;
        Label_0510: {
            if (string.equals(c.J[25]) || string.equals(c.J[14])) {
                n3 = 0;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[24]) || string.equals(c.J[18])) {
                n3 = 5;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[8]) || string.equals(c.J[5])) {
                n3 = 11;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[5]) || string.equals(c.J[3])) {
                n3 = 11;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[1]) || string.equals(c.J[27]) || string.equals(c.J[13])) {
                n3 = 7;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[2]) || string.equals(c.J[6])) {
                n3 = 8;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[4]) || string.equals(c.J[17])) {
                n3 = 9;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[16]) || string.equals(c.J[21])) {
                n3 = 10;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[20]) || string.equals(c.J[7])) {
                n3 = 1;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.startsWith(c.J[22]) || string.equals(c.J[9]) || string.equals(c.J[15])) {
                n3 = 2;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equalsIgnoreCase(c.J[26]) || string.equalsIgnoreCase(c.J[12]) || string.equals(c.J[23]) || string.equalsIgnoreCase(c.J[10])) {
                n3 = 3;
                if (!i) {
                    break Label_0510;
                }
            }
            if (string.equals(c.J[19]) || string.equals(c.J[11])) {
                n3 = 4;
            }
        }
        if (n3 != -1) {
            super.h.addElement(new k(null, n, n2 - super.j, n3));
        }
        j.b(0);
    }
    
    static {
        c.J = new String[] { z(z("\u0018^2r\u00133K+t\n3H7y\u0015")), z(z("J\b")), z(z("J\u0018")), z(z("J\u0000o?")), z(z("J\r")), z(z("J\u0000j")), z(z("J\n}")), z(z("J\nj")), z(z("J\u0000")), z(z("Jc")), z(z("J\n\u0012")), z(z("K\nk")), z(z("JW")), z(z("J\n\u000b")), z(z("J\nk")), z(z("J\n\u0006")), z(z("J\u000ee")), z(z("J\nh")), z(z("H\nk")), z(z("K\u000e")), z(z("J\u000f")), z(z("J\u000e<")), z(z("(c")), z(z("J\u00f9")), z(z("H\u000e")), z(z("J\u000e")), z(z("KW")), z(z("Jn")), z(z("\u0018S6g[_\b")), z(z("\u0007P59")), z(z("\u001dT%Q\u000e\u001eS")), z(z("/E.v\u000f\u001b")), z(z("\u0005T'e")), z(z("\u0013O#y\u000f\u0015K")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'a';
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
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = '\'';
                    break;
                }
                case 2: {
                    c2 = 'B';
                    break;
                }
                case 3: {
                    c2 = '\u0017';
                    break;
                }
                default: {
                    c2 = 'a';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
