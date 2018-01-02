// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public class am extends Panel implements aj
{
    private static final Color f;
    private static boolean p;
    private static boolean n;
    private Vector d;
    private Vector f;
    private aF a;
    public Color a;
    public Color b;
    public Color c;
    public Color d;
    protected int c;
    protected int d;
    protected int g;
    protected int h;
    public bj a;
    private String p;
    private String q;
    private String r;
    private int x;
    private aB b;
    private aB c;
    private aB d;
    protected boolean f;
    protected boolean g;
    protected boolean q;
    boolean k;
    private aB e;
    public be g;
    
    public void a(final aB d) {
        if (this.q && d != this.d) {
            final aB d2 = this.d;
            this.d = d;
            this.repaint();
            this.a();
        }
    }
    
    public void a(final boolean q) {
        if (!(this.q = q)) {
            this.d = null;
        }
        this.repaint();
        this.a.repaint();
    }
    
    void c() {
        if (this.d != null) {
            final bk a = this.a();
            this.a(this.d, 0, this.d.size() - 1, this.d);
            if (a != null) {
                this.g = this.a(a);
            }
        }
        this.k = false;
    }
    
    public synchronized void a(final long n) {
        this.k = true;
        this.b(n);
    }
    
    public synchronized void a() {
        this.a(0L);
    }
    
    synchronized void b() {
        for (int size = this.d.size(), i = 0; i < size / 2; ++i) {
            final Object element = this.d.elementAt(i);
            this.d.setElementAt(this.d.elementAt(size - i - 1), i);
            this.d.setElementAt(element, size - i - 1);
        }
        this.a.repaint();
    }
    
    int a(final bk bk, final bk bk2) {
        int a = 0;
        final int index = this.f.indexOf(this.d);
        for (int size = this.f.size(), i = 0; i < size; ++i) {
            final aB ab = this.f.elementAt((i + index) % size);
            if (ab.b()) {
                a = bk.a(bk2, ab.c());
                if (a != 0) {
                    break;
                }
            }
        }
        return this.g ? a : (-a);
    }
    
    void a(final Vector vector, final int n, final int n2, final aB ab) {
        if (n2 > n) {
            this.f.indexOf(ab);
            this.f.size();
            final int n3 = (n + n2) / 2;
            int n4 = n2;
            int i = n;
            final bk a = vector.elementAt(n3).a;
            while (i <= n4) {
                while (i < n2) {
                    if (this.a(a, vector.elementAt(i).a) <= 0) {
                        break;
                    }
                    ++i;
                }
                while (n4 > n && this.a(a, vector.elementAt(n4).a) < 0) {
                    --n4;
                }
                if (i < n4) {
                    final bf element = vector.elementAt(i);
                    final bf element2 = vector.elementAt(n4);
                    vector.setElementAt(element, n4);
                    vector.setElementAt(element2, i);
                }
                if (i <= n4) {
                    ++i;
                    --n4;
                }
            }
            if (n < n4) {
                this.a(vector, n, n4, ab);
            }
            if (i < n2) {
                this.a(vector, i, n2, ab);
            }
        }
        this.k = false;
    }
    
    public String d() {
        return this.p;
    }
    
    public int e() {
        return this.d.size();
    }
    
    public synchronized int b(final int n) {
        return (n + this.d) / this.c;
    }
    
    public aB a(final int n) {
        final Dimension size = this.size();
        final int n2 = this.q ? this.a.size().width : 2;
        final int size2 = this.f.size();
        if (n < 0) {
            return null;
        }
        if (n > size.width - n2 && n < size.width) {
            return this.e;
        }
        int n3 = -2;
        int n4 = -1;
        int n5 = 0;
        for (int i = 0; i < size2 - 1; ++i) {
            final aB ab = this.f.elementAt(i);
            final int a = ab.a();
            final int j = ab.i();
            int n6 = n3 + a;
            if (n4 == 0) {
                n6 += n5;
            }
            if (n <= n6 && n >= n3) {
                return ab;
            }
            n3 = n6;
            n4 = a;
            n5 = j;
        }
        return (aB)this.f.elementAt(size2 - 1);
    }
    
    public int c(final int n) {
        final Dimension size = this.size();
        final int n2 = this.q ? this.a.size().width : 2;
        if (n < 0) {
            return -1;
        }
        if (n > size.width - n2 && n < size.width) {
            return -2;
        }
        int n3 = -2;
        for (int i = 0; i < this.f.size() - 1; ++i) {
            final int n4 = n3 + this.f.elementAt(i).i();
            if (n <= n4 && n >= n3) {
                return i;
            }
            n3 = n4;
        }
        return this.f.size() - 1;
    }
    
    public synchronized int d(final int n) {
        return n * this.c - this.d;
    }
    
    public aB b(final int n) {
        return this.f.elementAt(n);
    }
    
    public int f() {
        return this.g;
    }
    
    public synchronized bk a() {
        if (this.g >= 0) {
            return this.a(this.g);
        }
        return null;
    }
    
    public synchronized void d(final int n) {
        if (n >= 0 && n < this.d.size()) {
            final Graphics graphics = this.a.getGraphics();
            if (graphics != null) {
                this.a(n, graphics);
                graphics.dispose();
            }
        }
    }
    
    public void g(final int n) {
        this.a(0L, n);
    }
    
    public void a(final long n, final int n2) {
        this.a.repaint(n, 0, this.d(n2), this.a.size().width, this.c - 1);
    }
    
    protected synchronized void a(final int n, final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            final int d = this.d(n);
            final int size = this.f.size();
            final int n2 = this.q ? this.a.size().width : 2;
            final Dimension size2 = this.a.size();
            final bf bf = this.d.elementAt(n);
            graphics.setColor(Color.white);
            graphics.drawLine(0, d + this.c - 1, size2.width, d + this.c - 1);
            int n3 = 0;
            for (int i = 0; i < size; ++i) {
                final aB ab = this.f.elementAt(i);
                int j;
                if (i == size - 1) {
                    j = size2.width - n3;
                }
                else {
                    j = ab.i();
                    if (i == 0) {
                        --j;
                    }
                }
                if (n == this.g) {
                    graphics.setColor(this.a);
                }
                else {
                    graphics.setColor(new Color(bf.j));
                }
                graphics.fillRect(n3, d, j, this.c - 1);
                ab.a(graphics, bf, bf.a.a(ab.c()), n3, d, j, this.c, n == this.g);
                n3 += j;
                if (n3 > size2.width - n2) {
                    break;
                }
            }
        }
    }
    
    protected int e(final int n) {
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            int a = this.f.elementAt(i).a();
            if (i > 0) {
                final aB ab = this.f.elementAt(i - 1);
                if (ab.a() == 0) {
                    a += ab.i();
                }
            }
            n2 += a;
        }
        return n2;
    }
    
    public synchronized int a(final bk bk) {
        for (int i = 0; i < this.d.size(); ++i) {
            if (((bf)this.d.elementAt(i)).a.equals(bk)) {
                return i;
            }
        }
        return -1;
    }
    
    protected synchronized void h(final int n) {
        final Dimension size = this.a.size();
        this.d -= n;
        final Graphics a = this.a.a(0, 0, size.width, size.height - 1, n);
        this.a.paint(a);
        a.dispose();
    }
    
    protected synchronized void b(final int n, final int n2) {
        final int n3 = (n >= 0) ? n : (-n);
        for (int i = 0; i < n3 / n2; ++i) {
            this.h((n >= 0) ? n2 : (-n2));
        }
        if (n3 % n2 != 0) {
            this.h((n >= 0) ? (n3 % n2) : (-(n3 % n2)));
        }
    }
    
    public synchronized bk a(final int n) {
        return this.d.elementAt(n).a;
    }
    
    public synchronized void i(final int g) {
        if (g >= 0 && g < this.d.size()) {
            if (this.g != g) {
                final int g2 = this.g;
                this.g = g;
                this.d(g2);
                this.d(g);
            }
            int n = 0;
            final int n2 = this.a.size().height - this.c + 2;
            final int d = this.d(g);
            if (d < 0) {
                n = -d;
            }
            else if (d > n2) {
                n = n2 - d;
            }
            if (n != 0) {
                this.h(n);
                this.a.setValue(this.d);
            }
            this.postEvent(new Event(this, 701, this.a(g)));
        }
        else {
            final int g3 = this.g;
            this.g = -1;
            this.d(g3);
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public synchronized void a(final bk bk, int b) {
        if (this.d != null) {
            b = this.b(bk);
        }
        this.d.insertElementAt(new bf(this, bk), b);
        if (this.g >= b) {
            ++this.g;
        }
        final int d = this.d(b);
        if (this.isShowing()) {
            if (this.d == this.a.getMaximum() && this.d > 0 && b == this.d.size() - 1) {
                this.b(-this.c, this.c);
            }
            else {
                final Dimension size = this.a.size();
                if (d >= 0 && d < size.height) {
                    if (this.d.size() > 1) {
                        this.a.a(0, d - 1, size.width, size.height - d, this.c);
                    }
                    this.g(b);
                }
            }
        }
        if (d < 0) {
            this.d += this.c;
        }
        this.h();
    }
    
    aB a(final Event event) {
        if (event.y < this.h) {
            int n = 0;
            for (int i = 0; i < this.f.size() - 1; ++i) {
                final aB ab = this.f.elementAt(i);
                final int n2 = n + ab.i();
                if (event.x > n2 - 4 && event.x < n2 + 4 && ab.d()) {
                    return ab;
                }
                if (event.x < n2) {
                    return null;
                }
                n = n2;
            }
        }
        return null;
    }
    
    public String a(final Object o) {
        return this.q;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0487: {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n = this.d - this.a.getValue();
                    if (n != 0) {
                        this.h(n);
                    }
                    return true;
                }
                case 401:
                case 403: {
                    final int size = this.d.size();
                    if (event.key == 1005 && this.g < size - 1) {
                        this.i(this.g + 1);
                    }
                    else if (event.key == 1004 && this.g > 0) {
                        this.i(this.g - 1);
                    }
                    else if ((event.key == 10 || event.key == 13) && this.g >= 0) {
                        this.postEvent(new Event(this, 1001, this.a()));
                    }
                    return true;
                }
                case 501: {
                    this.requestFocus();
                    final aB a = this.a(event);
                    this.b = a;
                    if (a != null) {
                        this.x = event.x;
                    }
                    else if (this.q) {
                        this.c = this.a(event.x);
                        if (this.c.b()) {
                            final Graphics graphics = this.getGraphics();
                            this.a(graphics, this.c, true, this.c == this.d);
                            graphics.dispose();
                            this.f = true;
                        }
                        else {
                            this.c = null;
                        }
                    }
                    return true;
                }
                case 502: {
                    if (!this.f || !this.q || event.target != this || this.c == null) {
                        break Label_0487;
                    }
                    if (this.c != this.e && this.c != this.d) {
                        this.a(this.c);
                        break Label_0487;
                    }
                    if (this.c == this.e) {
                        this.g = !this.g;
                        this.b();
                    }
                    final Graphics graphics2 = this.getGraphics();
                    this.a(graphics2, this.c, false, this.c == this.d);
                    graphics2.dispose();
                    break Label_0487;
                }
                case 503: {
                    if (event.target == this && event.y < this.h) {
                        aB ab;
                        if (this.q && event.x > this.size().width - this.a.size().width) {
                            ab = this.e;
                        }
                        else {
                            ab = this.a(event.x);
                        }
                        if (ab != null) {
                            this.q = ab.a(this.d == ab);
                            if (this.q == null || !this.q.equals(this.r)) {
                                this.postEvent(new Event(this, 7689, this.q));
                                this.r = this.q;
                            }
                        }
                    }
                    if (am.p) {
                        if (this.a(event) != null) {
                            this.setCursor(Cursor.getPredefinedCursor(11));
                        }
                        else {
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                    return false;
                }
                case 506: {
                    if (event.target == this && this.b != null) {
                        final int i = this.b.i();
                        int x = event.x;
                        final int width = this.size().width;
                        final int n2 = this.q ? this.a.size().width : 2;
                        if (x > width - n2) {
                            x = width - n2;
                        }
                        int n3 = i + (x - this.x);
                        if (n3 < 15) {
                            x += 15 - n3;
                            n3 = 15;
                        }
                        if (n3 != i) {
                            this.b.b(n3);
                        }
                        this.x = x;
                    }
                    if (event.target == this && this.c != null) {
                        final boolean b = event.y >= 0 && event.y < this.h && this.a(event.x) == this.c;
                        if (this.f && !b) {
                            this.f = false;
                            final Graphics graphics3 = this.getGraphics();
                            this.a(graphics3, this.c, false, this.c == this.d);
                            graphics3.dispose();
                        }
                        else if (!this.f && b) {
                            this.f = true;
                            final Graphics graphics4 = this.getGraphics();
                            this.a(graphics4, this.c, true, this.c == this.d);
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
    
    protected void h() {
        this.a.setValues(this.d, this.a.size().height, 0, this.d.size() * this.c - 2);
        this.d = this.a.getValue();
        if (this.g < 0) {
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public void resize(final int n, final int n2) {
        this.a.resize(n - this.a.size().width, n2 - this.h);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public void j(final int n) {
        this.c = n;
        this.a.setLineIncrement(n);
        this.h();
        this.a.repaint();
    }
    
    public void b(final aB ab) {
        this.a(ab, this.f.size());
    }
    
    public void a(final aB ab, final int n) {
        this.f.insertElementAt(ab, n);
        ab.c = this;
    }
    
    public void a(final bk bk) {
        this.a(bk, this.d.size());
    }
    
    public synchronized void b(final bk bk, final int n) {
        this.d.setElementAt(new bf(this, bk), n);
        if (this.d != null) {
            this.a(200L);
        }
        else {
            this.a(200L, n);
        }
    }
    
    public boolean a(final bk bk) {
        final int a = this.a(bk);
        if (a != -1) {
            this.k(a);
            return true;
        }
        return false;
    }
    
    public void k(final int n) {
        if (this.g == n) {
            if (n == this.d.size() - 1) {
                this.i(n - 1);
            }
            else {
                this.i(n + 1);
                --this.g;
            }
        }
        else if (this.g > n) {
            this.i(n - 1);
        }
        this.d.elementAt(n);
        this.d.removeElementAt(n);
        int d = this.d(n);
        final Dimension size = this.a.size();
        if (d < size.height && d > -this.c && this.isShowing()) {
            int c;
            if (d < 0) {
                c = this.c + d;
                d = 0;
            }
            else {
                c = this.c;
            }
            final int maximum = this.a.getMaximum();
            if (this.d > maximum - this.c && maximum != 0 && this.d != 0) {
                if (this.d < this.c) {
                    final int n2 = d + this.d;
                    this.b(this.d, this.d);
                    this.a.paint(this.a.a(0, n2, size.width, size.height - n2 - 1, -c));
                }
                else {
                    this.d -= this.c;
                    this.a.paint(this.a.a(0, 0, size.width, d + this.c, this.c));
                }
            }
            else {
                this.a.paint(this.a.a(0, d, size.width, size.height - d - 1, -c));
            }
        }
        this.h();
    }
    
    public void c(final aB ab) {
        final int index = this.f.indexOf(ab);
        if (index >= 0) {
            this.l(index);
        }
    }
    
    public void l(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += ((aB)this.f.elementAt(i)).i();
        }
        this.a(n2, 0, this.f.elementAt(n).i(), this.a.size().height);
    }
    
    public void i() {
        this.b(0L);
    }
    
    public void b(final long n) {
        if (this.isShowing()) {
            this.a.repaint(n);
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        if (this.isShowing()) {
            this.a.repaint(n, n2, n3, n4);
        }
    }
    
    public int b(final bk bk) {
        int i = 0;
        int n = this.d.size() - 1;
        int n2 = 0;
        if (n == -1) {
            return 0;
        }
        int n3 = this.a(bk, this.d.elementAt(n).a);
        if (n3 > 0) {
            return n + 1;
        }
        while (i <= n) {
            n2 = i + (n - i) / 2;
            n3 = this.a(bk, this.d.elementAt(n2).a);
            if (n3 == 0) {
                return n2;
            }
            if (n3 < 0) {
                n = n2 - 1;
            }
            else {
                i = n2 + 1;
            }
        }
        return (n3 >= 0) ? (n2 + 1) : n2;
    }
    
    protected static void a(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        a(graphics, s, n, n2, n3, n4, n5, b, "");
    }
    
    protected static void a(final Graphics graphics, String string, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final String s) {
        final int n6 = n2 + (n4 + graphics.getFontMetrics().getAscent()) / 2 - 1;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(string);
        if (stringWidth > n3) {
            final int stringWidth2 = fontMetrics.stringWidth("...");
            final int length = string.length();
            int n7 = 0;
            int n8;
            int i = n8 = length;
            String substring = string;
            while (i > n7) {
                substring = string.substring(0, n8);
                stringWidth = fontMetrics.stringWidth(substring) + stringWidth2;
                if (stringWidth <= n3 && n8 == length) {
                    break;
                }
                if (stringWidth < n3) {
                    n7 = 1 + n8;
                }
                else {
                    i = n8;
                }
                n8 = (n7 + i) / 2;
            }
            string = substring + "...";
        }
        int n9 = 0;
        switch (n5) {
            case 2: {
                n9 = n + n3 - stringWidth;
                break;
            }
            case 1: {
                n9 = n + (n3 - stringWidth) / 2;
                break;
            }
            default: {
                n9 = n;
                break;
            }
        }
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n9, n2 + n4 / 2, n9 + stringWidth, n2 + n4 / 2);
        }
        if (s != null && s.length() != 0 && bq.h.containsKey(s)) {
            final Image image = bq.h.get(s);
            if (image != null) {
                graphics.drawImage(image, n9 - 2, n6 - 11, null);
                n9 += 14;
            }
        }
        graphics.drawString(string, n9, n6);
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n9, n2 + n4 / 2, n9 + stringWidth, n2 + n4 / 2);
        }
    }
    
    protected void a(final Graphics graphics, final aB ab, final boolean b, final boolean b2) {
        final Dimension size = this.size();
        final int n = this.q ? this.a.size().width : 2;
        int i;
        int e;
        if (ab == this.e) {
            i = n - 2;
            e = size.width - n + 1;
        }
        else {
            final int index = this.f.indexOf(ab);
            e = this.e(index);
            if (index == this.f.size() - 1) {
                i = size.width - e - n + 1;
            }
            else {
                i = ab.i();
                if (index > 0) {
                    final aB ab2 = this.f.elementAt(index - 1);
                    if (ab2.a() == 0) {
                        i += ab2.i();
                    }
                }
            }
        }
        this.a(graphics, ab, e, i, this.h, b, b2);
    }
    
    protected void a(final Graphics graphics, final aB ab, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        final Color color = (!b && !b2) ? this.c : this.d;
        final Color brighter = color.brighter();
        final Color darker = color.darker();
        graphics.setColor(color);
        graphics.fillRect(n + 1, 2, n2 - 2, n3 - 3);
        graphics.setColor((!b && !b2) ? brighter : darker);
        graphics.drawLine(n, 1, n, n3 - 1);
        graphics.drawLine(n, 1, n + n2 - 1, 1);
        graphics.setColor((!b && !b2) ? darker : brighter);
        graphics.drawLine(n + n2 - 1, n3 - 1, n + n2 - 1, 1);
        graphics.drawLine(n, n3 - 1, n + n2 - 1, n3 - 1);
        if (ab == this.e) {
            graphics.setColor(Color.black);
            for (int i = 0; i < 4; ++i) {
                final int n4 = n3 / 2 + (this.g ? (2 * i - 4) : (-(2 * i - 4)));
                graphics.drawLine(n + n2 / 2 - i - 1, n4, n + n2 / 2 + i - 1, n4);
            }
        }
        else {
            ab.a(graphics, n, n2, n3, b, b2);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Color b = aH.b;
        b.brighter();
        b.darker();
        final int n = this.q ? this.a.size().width : 2;
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawLine(0, this.h, size.width - 1, this.h);
        if (this.h > 0) {
            final int size2 = this.f.size();
            int n2 = 1;
            int i = 0;
            int n3 = -1;
            aB ab;
            int a;
            for (int n4 = 0; n4 < size2 && n2 < size.width - 1; n2 += a, i = ab.i(), n3 = a, ++n4) {
                ab = this.f.elementAt(n4);
                if (n4 == size2 - 1) {
                    a = size.width - n2 - n + 1;
                }
                else {
                    a = ab.a();
                    if (n3 == 0) {
                        a += i;
                    }
                    if (n2 + a >= size.width - n) {
                        a = size.width - n2 - n + 1;
                    }
                }
                if (a != 0) {
                    this.a(graphics, ab, n2, a, this.h, false, ab == this.d);
                }
            }
            if (this.q) {
                this.a(graphics, this.e, false, false);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void a(final int n, final boolean c) {
        this.d.elementAt(n).c = c;
        this.g(n);
    }
    
    public synchronized void a(final bk bk, final boolean b) {
        final int a = this.a(bk);
        if (a >= 0) {
            this.a(a, b);
        }
    }
    
    public synchronized void a(final int n, final Color c, final Color d, final aq aq) {
        final bf bf = this.d.elementAt(n);
        bf.c = c;
        bf.d = d;
        if (aq != null) {
            bf.d = aq.c(17);
            bf.i = aq.c(18);
            bf.d = aq.Q;
            bf.j = aq.ah;
            bf.g = aq.t;
            bf.u = aq.b;
        }
        this.g(n);
    }
    
    public synchronized void a(final bk bk, final Color c, final Color d, final int j) {
        final int a = this.a(bk);
        if (a >= 0) {
            final bf bf = this.d.elementAt(a);
            bf.c = c;
            bf.d = d;
            bf.j = j;
        }
        this.g(a);
    }
    
    public synchronized void a(final bk bk, final Color color, final Color color2) {
        final int a = this.a(bk);
        if (a >= 0) {
            if (bk instanceof aq) {
                this.a(a, color, color2, (aq)bk);
            }
            else {
                this.a(a, color, color2, null);
            }
        }
    }
    
    static Vector a(final am am) {
        return am.d;
    }
    
    static Color e() {
        return am.f;
    }
    
    public synchronized void j() {
        this.d.removeAllElements();
        this.g = -1;
        this.d = 0;
        this.a.setValues(0, 0, 0, 0);
        this.a.repaint();
        this.postEvent(new Event(this, 702, null));
    }
    
    public am(final int h) {
        this.d = new Vector();
        this.f = new Vector();
        this.a = new aF(this, this);
        this.a = new Color(3355545);
        this.b = aH.c;
        this.c = aH.b;
        this.d = new Color(9211020);
        this.d = 0;
        this.g = -1;
        this.g = true;
        this.q = false;
        this.k = false;
        (this.e = new aB(null, null)).b(true);
        this.e.a(aG.a("Click here to reverse the sort order of items in this list."), null);
        if (!am.n) {
            am.n = true;
            try {
                this.setCursor(Cursor.getDefaultCursor());
                am.p = true;
            }
            catch (Throwable t) {
                am.p = false;
            }
        }
        this.h = h;
        this.setBackground(Color.white);
        this.a.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.a = new bj(1);
        this.j(19);
        gridBagConstraints.insets = new Insets(1 + h, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.insets = new Insets(h, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.setFont(aK.d);
    }
    
    public am() {
        this(18);
    }
    
    static {
        f = new Color(16737894);
        am.n = false;
    }
}
