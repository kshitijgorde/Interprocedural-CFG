// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Scrollbar;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public final class y extends Panel implements ab
{
    private static final Color c;
    private static boolean b;
    private static boolean c;
    private Vector a;
    private Vector b;
    private boolean d;
    private boolean e;
    private p a;
    public Color a;
    public Color b;
    private Color d;
    private Color e;
    private int a;
    private int b;
    private int c;
    private int d;
    private r a;
    private String a;
    private String b;
    private int e;
    private w a;
    private w b;
    private w c;
    private boolean f;
    private boolean g;
    private boolean h;
    boolean a;
    private w d;
    
    public final void a(final w c) {
        if (this.h && c != this.c) {
            this.c = c;
            this.repaint();
            this.f();
        }
    }
    
    public final void a() {
        this.h = true;
        this.repaint();
        this.a.repaint();
    }
    
    final void b() {
        if (this.c != null) {
            final a a = this.a();
            this.a(this.a, 0, this.a.size() - 1, this.c);
            if (a != null) {
                this.c = this.a(a);
            }
        }
        this.a = false;
    }
    
    private synchronized void a(final long n) {
        this.a = true;
        this.b(n);
    }
    
    private synchronized void f() {
        this.a(0L);
    }
    
    private synchronized void g() {
        final int size = this.a.size();
        final a a = this.a();
        for (int i = 0; i < size / 2; ++i) {
            final Object element = this.a.elementAt(i);
            this.a.setElementAt(this.a.elementAt(size - i - 1), i);
            this.a.setElementAt(element, size - i - 1);
        }
        if (a != null) {
            this.c = this.a(a);
        }
        this.a.repaint();
    }
    
    private int a(final a a, final a a2) {
        int n = 0;
        final int index = this.b.indexOf(this.c);
        for (int size = this.b.size(), i = 0; i < size; ++i) {
            final w w;
            if ((w = this.b.elementAt((i + index) % size)).a) {
                if (a instanceof au && "name".equals(w.a)) {
                    if (!this.d) {
                        n = a.a(a2, "name2");
                    }
                    else {
                        n = a.a(a2, w.a);
                    }
                }
                else {
                    n = a.a(a2, w.a);
                }
                if (n != 0) {
                    break;
                }
            }
        }
        if (this.g) {
            return n;
        }
        return -n;
    }
    
    private void a(final Vector vector, final int n, final int n2, final w w) {
        if (n2 > n) {
            this.b.indexOf(w);
            this.b.size();
            final int n3 = (n + n2) / 2;
            int n4 = n2;
            int i = n;
            final a a = vector.elementAt(n3).a;
            while (i <= n4) {
                while (i < n2 && this.a(a, vector.elementAt(i).a) > 0) {
                    ++i;
                }
                while (n4 > n && this.a(a, vector.elementAt(n4).a) < 0) {
                    --n4;
                }
                if (i < n4) {
                    final s element = vector.elementAt(i);
                    final s element2 = vector.elementAt(n4);
                    vector.setElementAt(element, n4);
                    vector.setElementAt(element2, i);
                }
                if (i <= n4) {
                    ++i;
                    --n4;
                }
            }
            if (n < n4) {
                this.a(vector, n, n4, w);
            }
            if (i < n2) {
                this.a(vector, i, n2, w);
            }
        }
        this.a = false;
    }
    
    public final int a() {
        return this.a.size();
    }
    
    public final synchronized int a(final int n) {
        return (n + this.b) / this.a;
    }
    
    private w b(final int n) {
        final Dimension size = this.size();
        final int n2 = this.h ? this.a.size().width : 2;
        final int size2 = this.b.size();
        if (n < 0) {
            return null;
        }
        if (n > size.width - n2 && n < size.width) {
            return this.d;
        }
        int n3 = -2;
        int n4 = -1;
        int n5 = 0;
        for (int i = 0; i < size2 - 1; ++i) {
            final w w;
            final int a = (w = this.b.elementAt(i)).a;
            final int b = w.b;
            int n6 = n3 + a;
            if (n4 == 0) {
                n6 += n5;
            }
            if (n <= n6 && n >= n3) {
                return w;
            }
            n3 = n6;
            n4 = a;
            n5 = b;
        }
        return (w)this.b.elementAt(size2 - 1);
    }
    
    public final int b(final int n) {
        final Dimension size = this.size();
        final int n2 = this.h ? this.a.size().width : 2;
        if (n < 0) {
            return -1;
        }
        if (n > size.width - n2 && n < size.width) {
            return -2;
        }
        int n3 = -2;
        for (int i = 0; i < this.b.size() - 1; ++i) {
            final int n4 = n3 + this.b.elementAt(i).b;
            if (n <= n4 && n >= n3) {
                return i;
            }
            n3 = n4;
        }
        return this.b.size() - 1;
    }
    
    public final synchronized int c(final int n) {
        return n * this.a - this.b;
    }
    
    public final w a(final int n) {
        return this.b.elementAt(n);
    }
    
    public final int b() {
        return this.c;
    }
    
    public final synchronized a a() {
        if (this.c >= 0) {
            return this.a(this.c);
        }
        return null;
    }
    
    public final void a(final a a) {
        (this = this).a(0L, this.a(a));
    }
    
    private synchronized void c(final int n) {
        final Graphics graphics;
        if (n >= 0 && n < this.a.size() && (graphics = this.a.getGraphics()) != null) {
            this.a(n, graphics);
            graphics.dispose();
        }
    }
    
    private void a(final long n, final int n2) {
        this.a.repaint(n, 0, this.c(n2), this.a.size().width, this.a - 1);
    }
    
    protected final synchronized void a(final int n, final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            final int c = this.c(n);
            final int size = this.b.size();
            final int n2 = this.h ? this.a.size().width : 2;
            final Dimension size2 = this.a.size();
            final s s = this.a.elementAt(n);
            graphics.setColor(Color.white);
            graphics.drawLine(0, c + this.a - 1, size2.width, c + this.a - 1);
            int n3 = 0;
            for (int i = 0; i < size; ++i) {
                final w w = this.b.elementAt(i);
                int b;
                if (i == size - 1) {
                    b = size2.width - n3;
                }
                else {
                    b = w.b;
                    if (i == 0) {
                        --b;
                    }
                }
                if (n == this.c) {
                    graphics.setColor(this.a);
                }
                else {
                    graphics.setColor(s.c);
                }
                graphics.fillRect(n3, c, b, this.a - 1);
                if (s.c > 0) {
                    if (s.c == 1) {
                        graphics.setColor(new Color(39168));
                    }
                    else {
                        graphics.setColor(new Color(12566528));
                    }
                    graphics.drawRect(0, c, size2.width, this.a - 3);
                }
                w.a(graphics, s, s.a.a(w.a), n3, c, b, this.a, n == this.c);
                if ((n3 += b) > size2.width - n2) {
                    break;
                }
            }
        }
    }
    
    public final synchronized int a(final a a) {
        for (int i = 0; i < this.a.size(); ++i) {
            if (((s)this.a.elementAt(i)).a.equals(a)) {
                return i;
            }
        }
        return -1;
    }
    
    public final synchronized void b(final a a) {
        final int a2;
        if ((a2 = this.a(a)) >= 0) {
            this.a(a2);
        }
    }
    
    private synchronized void d(final int n) {
        final Dimension size = this.a.size();
        this.b -= n;
        final Graphics a = this.a.a(0, size.width, size.height - 1, n);
        this.a.paint(a);
        a.dispose();
    }
    
    private synchronized void a(final int n, final int n2) {
        final int n3 = (n >= 0) ? n : (-n);
        for (int i = 0; i < n3 / n2; ++i) {
            this.d((n >= 0) ? n2 : (-n2));
        }
        if (n3 % n2 != 0) {
            this.d((n >= 0) ? (n3 % n2) : (-(n3 % n2)));
        }
    }
    
    public final synchronized a a(final int n) {
        return this.a.elementAt(n).a;
    }
    
    public final synchronized void a(final int c) {
        if (c >= 0 && c < this.a.size()) {
            if (this.c != c) {
                final int c2 = this.c;
                this.c = c;
                this.c(c2);
                this.c(c);
            }
            int n = 0;
            final int n2 = this.a.size().height - this.a + 2;
            final int c3;
            if ((c3 = this.c(c)) < 0) {
                n = -c3;
            }
            else if (c3 > n2) {
                n = n2 - c3;
            }
            if (n != 0) {
                this.d(n);
                this.a.setValue(this.b);
            }
            this.postEvent(new Event(this, 701, this.a(c)));
            return;
        }
        final int c4 = this.c;
        this.c = -1;
        this.c(c4);
        this.postEvent(new Event(this, 702, null));
    }
    
    private synchronized void b(final a a, int n) {
        if (this.c != null) {
            int i = 0;
            int n2 = this.a.size() - 1;
            int n3 = 0;
            int n4 = 0;
            Label_0161: {
                if (n2 == -1) {
                    n4 = 0;
                }
                else {
                    int n5;
                    if ((n5 = this.a(a, this.a.elementAt(n2).a)) > 0) {
                        n4 = n2 + 1;
                    }
                    else {
                        while (true) {
                            while (i <= n2) {
                                n3 = i + (n2 - i) / 2;
                                if ((n5 = this.a(a, this.a.elementAt(n3).a)) == 0) {
                                    n4 = n3;
                                    break Label_0161;
                                }
                                if (n5 < 0) {
                                    n2 = n3 - 1;
                                }
                                else {
                                    i = n3 + 1;
                                }
                            }
                            if (n5 < 0) {
                                continue;
                            }
                            break;
                        }
                        n4 = n3 + 1;
                    }
                }
            }
            n = n4;
        }
        this.a.insertElementAt(new s(a), n);
        if (this.c >= n) {
            ++this.c;
        }
        final int c = this.c(n);
        if (this.isShowing()) {
            if (this.b == this.a.getMaximum() && this.b > 0 && n == this.a.size() - 1) {
                this.a(-this.a, this.a);
            }
            else {
                final Dimension size = this.a.size();
                if (c >= 0 && c < size.height) {
                    if (this.a.size() > 1) {
                        this.a.a(c - 1, size.width, size.height - c, this.a);
                    }
                    this.a(0L, n);
                }
            }
        }
        if (c < 0) {
            this.b += this.a;
        }
        this.c();
    }
    
    private w a(final Event event) {
        if (event.y < this.d) {
            int n = 0;
            for (int i = 0; i < this.b.size() - 1; ++i) {
                n += ((w)this.b.elementAt(i)).b;
                if (event.x < n) {
                    return null;
                }
            }
        }
        return null;
    }
    
    public final String a(final Object o) {
        return this.a;
    }
    
    public final boolean handleEvent(final Event event) {
        Label_0466: {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n;
                    if ((n = this.b - this.a.getValue()) != 0) {
                        this.d(n);
                    }
                    return true;
                }
                case 401:
                case 403: {
                    final int size = this.a.size();
                    if (event.key == 1005 && this.c < size - 1) {
                        this.a(this.c + 1);
                    }
                    else if (event.key == 1004 && this.c > 0) {
                        this.a(this.c - 1);
                    }
                    else if ((event.key == 10 || event.key == 13) && this.c >= 0) {
                        this.postEvent(new Event(this, 1001, this.a()));
                    }
                    return true;
                }
                case 501: {
                    this.requestFocus();
                    this.a = this.a(event);
                    if (this.h) {
                        this.b = this.b(event.x);
                        if (this.b.a) {
                            final Graphics graphics = this.getGraphics();
                            this.a(graphics, this.b, true, this.b == this.c);
                            graphics.dispose();
                            this.f = true;
                        }
                        else {
                            this.b = null;
                        }
                    }
                    return true;
                }
                case 502: {
                    if (!this.f || !this.h || event.target != this || this.b == null) {
                        break Label_0466;
                    }
                    if (this.b != this.d && this.b != this.c) {
                        this.a(this.b);
                        break Label_0466;
                    }
                    if (this.b == this.d) {
                        this.g = !this.g;
                        this.g();
                    }
                    final Graphics graphics2 = this.getGraphics();
                    this.a(graphics2, this.b, false, this.b == this.c);
                    graphics2.dispose();
                    break Label_0466;
                }
                case 503: {
                    if (event.target == this && event.y < this.d) {
                        w w;
                        if (this.h && event.x > this.size().width - this.a.size().width) {
                            w = this.d;
                        }
                        else {
                            w = this.b(event.x);
                        }
                        if (w != null) {
                            final w w2 = w;
                            final boolean b = this.c == w;
                            final w w3 = w2;
                            this.a = (b ? null : w3.b);
                            if (this.a == null || !this.a.equals(this.b)) {
                                this.postEvent(new Event(this, 7689, this.a));
                                this.b = this.a;
                            }
                        }
                    }
                    if (y.b) {
                        this.a(event);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                    return false;
                }
                case 506: {
                    if (event.target == this && this.a != null) {
                        final int b2 = this.a.b;
                        int x = event.x;
                        final int width = this.size().width;
                        final int n2 = this.h ? this.a.size().width : 2;
                        if (x > width - n2) {
                            x = width - n2;
                        }
                        int n3;
                        if ((n3 = b2 + (x - this.e)) < 15) {
                            x += 15 - n3;
                            n3 = 15;
                        }
                        if (n3 != b2) {
                            this.a.a(n3);
                        }
                        this.e = x;
                    }
                    if (event.target == this && this.b != null) {
                        final boolean b3 = event.y >= 0 && event.y < this.d && this.b(event.x) == this.b;
                        if (this.f && !b3) {
                            this.f = false;
                            final Graphics graphics3 = this.getGraphics();
                            this.a(graphics3, this.b, false, this.b == this.c);
                            graphics3.dispose();
                        }
                        else if (!this.f && b3) {
                            this.f = true;
                            final Graphics graphics4 = this.getGraphics();
                            this.a(graphics4, this.b, true, this.b == this.c);
                            graphics4.dispose();
                        }
                    }
                    return true;
                }
                default: {
                    return super.handleEvent(event);
                }
            }
        }
    }
    
    protected final void c() {
        this.a.setValues(this.b, this.a.size().height, 0, this.a.size() * this.a - 2);
        this.b = this.a.getValue();
        if (this.c < 0) {
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public final void resize(final int n, final int n2) {
        this.a.resize(n - this.a.size().width, n2 - this.d);
    }
    
    public final void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public final void b(final int n) {
        ((Scrollbar)(this.a = n)).setLineIncrement(n);
        this.c();
        this.a.repaint();
    }
    
    public final void b(w w) {
        final y y = this;
        final w w2 = w;
        final int size = this.b.size();
        w = w2;
        this = y;
        y.b.insertElementAt(w, size);
        w.a = this;
    }
    
    public final void c(final a a) {
        this.b(a, this.a.size());
    }
    
    public final synchronized void a(final a a, final int n) {
        this.a.setElementAt(new s(a), n);
        if (this.c != null) {
            this.a(200L);
            return;
        }
        this.a(200L, n);
    }
    
    public final boolean a(final a a) {
        final int a2;
        if ((a2 = this.a(a)) != -1) {
            if ((this = this).c == a2) {
                if (a2 == this.a.size() - 1) {
                    this.a(a2 - 1);
                }
                else {
                    this.a(a2 + 1);
                    final y y = this;
                    --y.c;
                }
            }
            else if (this.c > a2) {
                this.a(a2 - 1);
            }
            this.a.elementAt(a2);
            this.a.removeElementAt(a2);
            int c = this.c(a2);
            final Dimension size = this.a.size();
            if (c < size.height && c > -this.a && this.isShowing()) {
                int a3;
                if (c < 0) {
                    a3 = this.a + c;
                    c = 0;
                }
                else {
                    a3 = this.a;
                }
                final int maximum = this.a.getMaximum();
                if (this.b > maximum - this.a && maximum != 0 && this.b != 0) {
                    if (this.b < this.a) {
                        final int n = c + this.b;
                        this.a(this.b, this.b);
                        this.a.paint(this.a.a(n, size.width, size.height - n - 1, -a3));
                    }
                    else {
                        final y y2 = this;
                        y2.b -= this.a;
                        this.a.paint(this.a.a(0, size.width, c + this.a, this.a));
                    }
                }
                else {
                    this.a.paint(this.a.a(c, size.width, size.height - c - 1, -a3));
                }
            }
            this.c();
            return true;
        }
        return false;
    }
    
    public final synchronized void d() {
        this.a.removeAllElements();
        this.c = -1;
        this.b = 0;
        this.a.setValues(0, 0, 0, 0);
        this.a.repaint();
        this.postEvent(new Event(this, 702, null));
    }
    
    public final void c(final w w) {
        final int index;
        if ((index = this.b.indexOf(w)) >= 0) {
            int n = 0;
            for (int i = 0; i < index; ++i) {
                n += ((w)this.b.elementAt(i)).b;
            }
            final y y = this;
            final int n2 = n;
            final int b = this.b.elementAt(index).b;
            final int height = this.a.size().height;
            final int n3 = b;
            final int n4 = n2;
            this = y;
            if (y.isShowing()) {
                this.a.repaint(n4, 0, n3, height);
            }
        }
    }
    
    public final void e() {
        this.b(0L);
    }
    
    private void b(final long n) {
        if (this.isShowing()) {
            this.a.repaint(n);
        }
    }
    
    protected static void a(final Graphics graphics, final String s, final int n, final int n2, final int n3) {
        a(graphics, s, n, 0, n2, n3, 0, false, 0);
    }
    
    protected static void a(final Graphics graphics, String string, final int n, final int n2, final int n3, final int n4, int ascent, final boolean b, final int n5) {
        ascent = graphics.getFontMetrics().getAscent();
        ascent = n2 + (n4 + ascent) / 2 - 1;
        final FontMetrics fontMetrics;
        int n6 = (fontMetrics = graphics.getFontMetrics()).stringWidth(string);
        if (n5 != 0) {
            n6 = fontMetrics.stringWidth(string + "...");
        }
        if (n6 > n3) {
            final int stringWidth = fontMetrics.stringWidth("...");
            final int length = string.length();
            int n7 = 0;
            int n8;
            int i = n8 = length;
            String substring = string;
            while (i > n7) {
                substring = string.substring(0, n8);
                n6 = fontMetrics.stringWidth(substring) + stringWidth;
                if (n5 != 0) {
                    n6 = fontMetrics.stringWidth(substring + "...") + stringWidth;
                }
                if (n6 <= n3 && n8 == length) {
                    break;
                }
                if (n6 < n3) {
                    n7 = n8 + 1;
                }
                else {
                    i = n8;
                }
                n8 = (n7 + i) / 2;
            }
            string = substring + "...";
        }
        graphics.drawString(string, n, ascent);
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n, n2 + n4 / 2, n + n6, n2 + n4 / 2);
        }
    }
    
    private void a(final Graphics graphics, final w w, final boolean b, final boolean b2) {
        final Dimension size = this.size();
        final int n = this.h ? this.a.size().width : 2;
        int b3;
        int n2;
        if (w == this.d) {
            b3 = n - 2;
            n2 = size.width - n + 1;
        }
        else {
            final int index;
            final int n3 = index = this.b.indexOf(w);
            int n4 = 1;
            for (int i = 0; i < index; ++i) {
                int a = this.b.elementAt(i).a;
                final w w2;
                if (i > 0 && (w2 = this.b.elementAt(i - 1)).a == 0) {
                    a += w2.b;
                }
                n4 += a;
            }
            n2 = n4;
            if (n3 == this.b.size() - 1) {
                b3 = size.width - n2 - n + 1;
            }
            else {
                b3 = w.b;
                final w w3;
                if (n3 > 0 && (w3 = this.b.elementAt(n3 - 1)).a == 0) {
                    b3 += w3.b;
                }
            }
        }
        this.a(graphics, w, n2, b3, this.d, b, b2);
    }
    
    private void a(final Graphics graphics, final w w, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        final Color color;
        final Color brighter = (color = ((b || b2) ? this.e : this.d)).brighter();
        final Color darker = color.darker();
        graphics.setColor(color);
        graphics.fillRect(n + 1, 2, n2 - 2, n3 - 3);
        graphics.setColor((!b && !b2) ? brighter : darker);
        graphics.drawLine(n, 1, n, n3 - 1);
        graphics.drawLine(n, 1, n + n2 - 1, 1);
        graphics.setColor((!b && !b2) ? darker : brighter);
        graphics.drawLine(n + n2 - 1, n3 - 1, n + n2 - 1, 1);
        graphics.drawLine(n, n3 - 1, n + n2 - 1, n3 - 1);
        if (w == this.d) {
            graphics.setColor(Color.black);
            for (int i = 0; i < 4; ++i) {
                final int n4 = n3 / 2 + (this.g ? ((i << 1) - 4) : (-((i << 1) - 4)));
                graphics.drawLine(n + n2 / 2 - i - 1, n4, n + n2 / 2 + i - 1, n4);
            }
            return;
        }
        w.a(graphics, n, n2, n3, b, b2);
    }
    
    public final void a(final boolean d) {
        this.d = d;
    }
    
    public final void b(final boolean e) {
        this.e = e;
    }
    
    public final boolean a() {
        return this.e;
    }
    
    public final void paint(final Graphics graphics) {
        final Color b;
        (b = j.b).brighter();
        b.darker();
        final int n = this.h ? this.a.size().width : 2;
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawLine(0, this.d, size.width - 1, this.d);
        if (this.d > 0) {
            final int size2 = this.b.size();
            int n2 = 1;
            int b2 = 0;
            int n3 = -1;
            w w;
            int a;
            for (int n4 = 0; n4 < size2 && n2 < size.width - 1; n2 += a, b2 = w.b, n3 = a, ++n4) {
                w = this.b.elementAt(n4);
                if (n4 == size2 - 1) {
                    a = size.width - n2 - n + 1;
                }
                else {
                    a = w.a;
                    if (n3 == 0) {
                        a += b2;
                    }
                    if (n2 + a >= size.width - n) {
                        a = size.width - n2 - n + 1;
                    }
                }
                if (a != 0) {
                    this.a(graphics, w, n2, a, this.d, false, w == this.c);
                }
            }
            if (this.h) {
                this.a(graphics, this.d, false, false);
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private synchronized void a(final int n, final boolean b) {
        this.a.elementAt(n).b = b;
        this.a(0L, n);
    }
    
    public final synchronized void a(final a a, final boolean b) {
        final int a2;
        if ((a2 = this.a(a)) >= 0) {
            this.a(a2, b);
        }
    }
    
    private synchronized void a(final au au, final int n, final Color a, final Color b, final Color c, final int c2) {
        final s s;
        (s = this.a.elementAt(n)).a = a;
        s.b = b;
        s.c = c;
        if (au != null) {
            s.a = au.e;
            s.b = au.d;
            s.c = (au.a(41) && au.e != 0);
            s.e = au.a(23);
            s.d = au.a(0);
            s.c = c2;
        }
        this.a(0L, n);
    }
    
    public final synchronized void a(final a a, final Color color, final Color color2, final Color color3) {
        this.a(a, color, color2, color3, 0);
    }
    
    public final synchronized void a(final a a, final Color color, final Color color2, final Color color3, final int n) {
        final int a2;
        if ((a2 = this.a(a)) >= 0) {
            if (a instanceof au) {
                this.a((au)a, a2, color, color2, color3, n);
                return;
            }
            this.a(null, a2, color, color2, color3, n);
        }
    }
    
    static Vector a(final y y) {
        return y.a;
    }
    
    static Color a() {
        return y.c;
    }
    
    private y(final int n) {
        this.a = new Vector();
        this.b = new Vector();
        this.a = new p(this);
        this.a = new Color(3355545);
        this.b = j.c;
        this.d = j.b;
        this.e = new Color(9211020);
        this.b = 0;
        this.c = -1;
        this.g = true;
        this.h = false;
        this.a = false;
        this.d = false;
        this.e = false;
        this.d = new w(null, null);
        this.d.a = true;
        this.d.b = ak.a(464);
        if (!y.c) {
            y.c = true;
            try {
                this.setCursor(Cursor.getDefaultCursor());
                y.b = true;
            }
            catch (Throwable t) {
                y.b = false;
            }
        }
        this.d = 18;
        this.setBackground(Color.white);
        this.a.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.a = new r();
        this.b(19);
        gridBagConstraints.insets = new Insets(19, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.insets = new Insets(18, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.setFont(ay.c);
    }
    
    public y() {
        this(18);
    }
    
    static {
        c = new Color(16737894);
        y.c = false;
    }
}
