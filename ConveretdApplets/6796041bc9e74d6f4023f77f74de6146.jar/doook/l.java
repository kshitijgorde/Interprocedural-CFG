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

public class l extends Panel implements aB
{
    private static final Color c;
    private static boolean m;
    private static boolean b;
    private Vector a;
    private Vector b;
    private g a;
    public Color d;
    public Color e;
    public Color f;
    public Color g;
    protected int j;
    protected int k;
    protected int l;
    protected int m;
    public e a;
    private String i;
    private String j;
    private String k;
    private int n;
    private j a;
    private j b;
    private j c;
    protected boolean n;
    protected boolean c;
    protected boolean o;
    boolean p;
    private j d;
    public t a;
    
    public void a(final j c) {
        if (this.o && c != this.c) {
            final j c2 = this.c;
            this.c = c;
            this.repaint();
            this.c();
        }
    }
    
    public void a(final boolean o) {
        if (!(this.o = o)) {
            this.c = null;
        }
        this.repaint();
        this.a.repaint();
    }
    
    void a() {
        if (this.c != null) {
            final aU a = this.a();
            this.a(this.a, 0, this.a.size() - 1, this.c);
            if (a != null) {
                this.l = this.a(a);
            }
        }
        this.p = false;
    }
    
    public synchronized void a(final long n) {
        this.p = true;
        this.b(n);
    }
    
    public synchronized void c() {
        this.a(0L);
    }
    
    synchronized void d() {
        for (int size = this.a.size(), i = 0; i < size / 2; ++i) {
            final Object element = this.a.elementAt(i);
            this.a.setElementAt(this.a.elementAt(size - i - 1), i);
            this.a.setElementAt(element, size - i - 1);
        }
        this.a.repaint();
    }
    
    int a(final aU au, final aU au2) {
        int a = 0;
        final int index = this.b.indexOf(this.c);
        for (int size = this.b.size(), i = 0; i < size; ++i) {
            final j j = this.b.elementAt((i + index) % size);
            if (j.c()) {
                a = au.a(au2, j.b());
                if (a != 0) {
                    break;
                }
            }
        }
        return this.c ? a : (-a);
    }
    
    void a(final Vector vector, final int n, final int n2, final j j) {
        if (n2 > n) {
            this.b.indexOf(j);
            this.b.size();
            final int n3 = (n + n2) / 2;
            int n4 = n2;
            int i = n;
            final aU a = vector.elementAt(n3).a;
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
                    final d element = vector.elementAt(i);
                    final d element2 = vector.elementAt(n4);
                    vector.setElementAt(element, n4);
                    vector.setElementAt(element2, i);
                }
                if (i <= n4) {
                    ++i;
                    --n4;
                }
            }
            if (n < n4) {
                this.a(vector, n, n4, j);
            }
            if (i < n2) {
                this.a(vector, i, n2, j);
            }
        }
        this.p = false;
    }
    
    public String c() {
        return this.i;
    }
    
    public int c() {
        return this.a.size();
    }
    
    public synchronized int a(final int n) {
        return (n + this.k) / this.j;
    }
    
    public j a(final int n) {
        final Dimension size = this.size();
        final int n2 = this.o ? this.a.size().width : 2;
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
            final j j = this.b.elementAt(i);
            final int b = j.b();
            final int a = j.a();
            int n6 = n3 + b;
            if (n4 == 0) {
                n6 += n5;
            }
            if (n <= n6 && n >= n3) {
                return j;
            }
            n3 = n6;
            n4 = b;
            n5 = a;
        }
        return (j)this.b.elementAt(size2 - 1);
    }
    
    public int b(final int n) {
        final Dimension size = this.size();
        final int n2 = this.o ? this.a.size().width : 2;
        if (n < 0) {
            return -1;
        }
        if (n > size.width - n2 && n < size.width) {
            return -2;
        }
        int n3 = -2;
        for (int i = 0; i < this.b.size() - 1; ++i) {
            final int n4 = n3 + this.b.elementAt(i).a();
            if (n <= n4 && n >= n3) {
                return i;
            }
            n3 = n4;
        }
        return this.b.size() - 1;
    }
    
    public synchronized int c(final int n) {
        return n * this.j - this.k;
    }
    
    public j b(final int n) {
        return this.b.elementAt(n);
    }
    
    public int d() {
        return this.l;
    }
    
    public synchronized aU a() {
        if (this.l >= 0) {
            return this.a(this.l);
        }
        return null;
    }
    
    public void a(final long n, final aU au) {
        this.a(n, this.a(au));
    }
    
    public void a(final aU au) {
        this.a(0L, au);
    }
    
    public synchronized void e(final int n) {
        if (n >= 0 && n < this.a.size()) {
            final Graphics graphics = this.a.getGraphics();
            if (graphics != null) {
                this.a(n, graphics);
                graphics.dispose();
            }
        }
    }
    
    public void f(final int n) {
        this.a(0L, n);
    }
    
    public void a(final long n, final int n2) {
        this.a.repaint(n, 0, this.c(n2), this.a.size().width, this.j - 1);
    }
    
    protected synchronized void a(final int n, final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            final int c = this.c(n);
            final int size = this.b.size();
            final int n2 = this.o ? this.a.size().width : 2;
            final Dimension size2 = this.a.size();
            final d d = this.a.elementAt(n);
            graphics.setColor(Color.white);
            graphics.drawLine(0, c + this.j - 1, size2.width, c + this.j - 1);
            int n3 = 0;
            for (int i = 0; i < size; ++i) {
                final j j = this.b.elementAt(i);
                int a;
                if (i == size - 1) {
                    a = size2.width - n3;
                }
                else {
                    a = j.a();
                    if (i == 0) {
                        --a;
                    }
                }
                if (n == this.l) {
                    graphics.setColor(this.d);
                }
                else {
                    graphics.setColor(new Color(d.d));
                }
                graphics.fillRect(n3, c, a, this.j - 1);
                j.a(graphics, d, d.a.a(j.b()), n3, c, a, this.j, n == this.l);
                n3 += a;
                if (n3 > size2.width - n2) {
                    break;
                }
            }
        }
    }
    
    protected int d(final int n) {
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            int b = this.b.elementAt(i).b();
            if (i > 0) {
                final j j = this.b.elementAt(i - 1);
                if (j.b() == 0) {
                    b += j.a();
                }
            }
            n2 += b;
        }
        return n2;
    }
    
    public synchronized int a(final aU au) {
        for (int i = 0; i < this.a.size(); ++i) {
            if (((d)this.a.elementAt(i)).a.equals(au)) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized void b(final aU au) {
        final int a = this.a(au);
        if (a >= 0) {
            this.h(a);
        }
    }
    
    protected synchronized void g(final int n) {
        final Dimension size = this.a.size();
        this.k -= n;
        final Graphics a = this.a.a(0, 0, size.width, size.height - 1, n);
        this.a.paint(a);
        a.dispose();
    }
    
    protected synchronized void a(final int n, final int n2) {
        final int n3 = (n < 0) ? (-n) : n;
        for (int i = 0; i < n3 / n2; ++i) {
            this.g((n < 0) ? (-n2) : n2);
        }
        if (n3 % n2 != 0) {
            this.g((n < 0) ? (-(n3 % n2)) : (n3 % n2));
        }
    }
    
    public synchronized aU a(final int n) {
        return this.a.elementAt(n).a;
    }
    
    public synchronized void h(final int l) {
        if (l >= 0 && l < this.a.size()) {
            if (this.l != l) {
                final int i = this.l;
                this.l = l;
                this.e(i);
                this.e(l);
            }
            int n = 0;
            final int n2 = this.a.size().height - this.j + 2;
            final int c = this.c(l);
            if (c < 0) {
                n = -c;
            }
            else if (c > n2) {
                n = n2 - c;
            }
            if (n != 0) {
                this.g(n);
                this.a.setValue(this.k);
            }
            this.postEvent(new Event(this, 701, this.a(l)));
        }
        else {
            final int j = this.l;
            this.l = -1;
            this.e(j);
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public synchronized void a(final aU au, int b) {
        if (this.c != null) {
            b = this.b(au);
        }
        this.a.insertElementAt(new d(this, au), b);
        if (this.l >= b) {
            ++this.l;
        }
        final int c = this.c(b);
        if (this.isShowing()) {
            if (this.k == this.a.getMaximum() && this.k > 0 && b == this.a.size() - 1) {
                this.a(-this.j, this.j);
            }
            else {
                final Dimension size = this.a.size();
                if (c >= 0 && c < size.height) {
                    if (this.a.size() > 1) {
                        this.a.a(0, c - 1, size.width, size.height - c, this.j);
                    }
                    this.f(b);
                }
            }
        }
        if (c < 0) {
            this.k += this.j;
        }
        this.e();
    }
    
    j a(final Event event) {
        if (event.y < this.m) {
            int n = 0;
            for (int i = 0; i < this.b.size() - 1; ++i) {
                final j j = this.b.elementAt(i);
                final int n2 = n + j.a();
                if (event.x > n2 - 4 && event.x < n2 + 4 && j.b()) {
                    return j;
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
        return this.j;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0487: {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n = this.k - this.a.getValue();
                    if (n != 0) {
                        this.g(n);
                    }
                    return true;
                }
                case 401:
                case 403: {
                    final int size = this.a.size();
                    if (event.key == 1005 && this.l < size - 1) {
                        this.h(this.l + 1);
                    }
                    else if (event.key == 1004 && this.l > 0) {
                        this.h(this.l - 1);
                    }
                    else if ((event.key == 10 || event.key == 13) && this.l >= 0) {
                        this.postEvent(new Event(this, 1001, this.a()));
                    }
                    return true;
                }
                case 501: {
                    this.requestFocus();
                    final j a = this.a(event);
                    this.a = a;
                    if (a != null) {
                        this.n = event.x;
                    }
                    else if (this.o) {
                        this.b = this.a(event.x);
                        if (this.b.c()) {
                            final Graphics graphics = this.getGraphics();
                            this.a(graphics, this.b, true, this.b == this.c);
                            graphics.dispose();
                            this.n = true;
                        }
                        else {
                            this.b = null;
                        }
                    }
                    return true;
                }
                case 502: {
                    if (!this.n || !this.o || event.target != this || this.b == null) {
                        break Label_0487;
                    }
                    if (this.b != this.d && this.b != this.c) {
                        this.a(this.b);
                        break Label_0487;
                    }
                    if (this.b == this.d) {
                        this.c = !this.c;
                        this.d();
                    }
                    final Graphics graphics2 = this.getGraphics();
                    this.a(graphics2, this.b, false, this.b == this.c);
                    graphics2.dispose();
                    break Label_0487;
                }
                case 503: {
                    if (event.target == this && event.y < this.m) {
                        j j;
                        if (this.o && event.x > this.size().width - this.a.size().width) {
                            j = this.d;
                        }
                        else {
                            j = this.a(event.x);
                        }
                        if (j != null) {
                            this.j = j.a(this.c == j);
                            if (this.j == null || !this.j.equals(this.k)) {
                                this.postEvent(new Event(this, 7689, this.j));
                                this.k = this.j;
                            }
                        }
                    }
                    if (doook.l.m) {
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
                    if (event.target == this && this.a != null) {
                        final int a2 = this.a.a();
                        int x = event.x;
                        final int width = this.size().width;
                        final int n2 = this.o ? this.a.size().width : 2;
                        if (x > width - n2) {
                            x = width - n2;
                        }
                        int n3 = a2 + (x - this.n);
                        if (n3 < 15) {
                            x += 15 - n3;
                            n3 = 15;
                        }
                        if (n3 != a2) {
                            this.a.b(n3);
                        }
                        this.n = x;
                    }
                    if (event.target == this && this.b != null) {
                        final boolean b = event.y >= 0 && event.y < this.m && this.a(event.x) == this.b;
                        if (this.n && !b) {
                            this.n = false;
                            final Graphics graphics3 = this.getGraphics();
                            this.a(graphics3, this.b, false, this.b == this.c);
                            graphics3.dispose();
                        }
                        else if (!this.n && b) {
                            this.n = true;
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
    
    protected void e() {
        this.a.setValues(this.k, this.a.size().height, 0, this.a.size() * this.j - 2);
        this.k = this.a.getValue();
        if (this.l < 0) {
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public void resize(final int n, final int n2) {
        this.a.resize(n - this.a.size().width, n2 - this.m);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public int e() {
        return this.j;
    }
    
    public void i(final int n) {
        this.j = n;
        this.a.setLineIncrement(n);
        this.e();
        this.a.repaint();
    }
    
    public void b(final j j) {
        this.a(j, this.b.size());
    }
    
    public void a(final j j, final int n) {
        this.b.insertElementAt(j, n);
        j.c = this;
    }
    
    public void c(final aU au) {
        this.a(au, this.a.size());
    }
    
    public synchronized void b(final aU au, final int n) {
        this.a.setElementAt(new d(this, au), n);
        if (this.c != null) {
            this.a(200L);
        }
        else {
            this.a(200L, n);
        }
    }
    
    public boolean a(final aU au) {
        final int a = this.a(au);
        if (a != -1) {
            this.j(a);
            return true;
        }
        return false;
    }
    
    public void j(final int n) {
        if (this.l == n) {
            if (n == this.a.size() - 1) {
                this.h(n - 1);
            }
            else {
                this.h(n + 1);
                --this.l;
            }
        }
        else if (this.l > n) {
            this.h(n - 1);
        }
        this.a.elementAt(n);
        this.a.removeElementAt(n);
        int c = this.c(n);
        final Dimension size = this.a.size();
        if (c < size.height && c > -this.j && this.isShowing()) {
            int j;
            if (c < 0) {
                j = this.j + c;
                c = 0;
            }
            else {
                j = this.j;
            }
            final int maximum = this.a.getMaximum();
            if (this.k > maximum - this.j && maximum != 0 && this.k != 0) {
                if (this.k < this.j) {
                    final int n2 = c + this.k;
                    this.a(this.k, this.k);
                    this.a.paint(this.a.a(0, n2, size.width, size.height - n2 - 1, -j));
                }
                else {
                    this.k -= this.j;
                    this.a.paint(this.a.a(0, 0, size.width, c + this.j, this.j));
                }
            }
            else {
                this.a.paint(this.a.a(0, c, size.width, size.height - c - 1, -j));
            }
        }
        this.e();
    }
    
    public synchronized void f() {
        this.a.removeAllElements();
        this.l = -1;
        this.k = 0;
        this.a.setValues(0, 0, 0, 0);
        this.a.repaint();
        this.postEvent(new Event(this, 702, null));
    }
    
    public void c(final j j) {
        final int index = this.b.indexOf(j);
        if (index >= 0) {
            this.k(index);
        }
    }
    
    public void k(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += ((j)this.b.elementAt(i)).a();
        }
        this.a(n2, 0, this.b.elementAt(n).a(), this.a.size().height);
    }
    
    public void g() {
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
    
    public int b(final aU au) {
        int i = 0;
        int n = this.a.size() - 1;
        int n2 = 0;
        if (n == -1) {
            return 0;
        }
        int n3 = this.a(au, this.a.elementAt(n).a);
        if (n3 > 0) {
            return n + 1;
        }
        while (i <= n) {
            n2 = i + (n - i) / 2;
            n3 = this.a(au, this.a.elementAt(n2).a);
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
        return (n3 < 0) ? n2 : (n2 + 1);
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
        if (s != null && s.length() != 0 && y.b.containsKey(s)) {
            final Image image = y.b.get(s);
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
    
    protected void a(final Graphics graphics, final j j, final boolean b, final boolean b2) {
        final Dimension size = this.size();
        final int n = this.o ? this.a.size().width : 2;
        int a;
        int d;
        if (j == this.d) {
            a = n - 2;
            d = size.width - n + 1;
        }
        else {
            final int index = this.b.indexOf(j);
            d = this.d(index);
            if (index == this.b.size() - 1) {
                a = size.width - d - n + 1;
            }
            else {
                a = j.a();
                if (index > 0) {
                    final j i = this.b.elementAt(index - 1);
                    if (i.b() == 0) {
                        a += i.a();
                    }
                }
            }
        }
        this.a(graphics, j, d, a, this.m, b, b2);
    }
    
    protected void a(final Graphics graphics, final j j, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        final Color color = (b || b2) ? this.g : this.f;
        final Color brighter = color.brighter();
        final Color darker = color.darker();
        graphics.setColor(color);
        graphics.fillRect(n + 1, 2, n2 - 2, n3 - 3);
        graphics.setColor((b || b2) ? darker : brighter);
        graphics.drawLine(n, 1, n, n3 - 1);
        graphics.drawLine(n, 1, n + n2 - 1, 1);
        graphics.setColor((b || b2) ? brighter : darker);
        graphics.drawLine(n + n2 - 1, n3 - 1, n + n2 - 1, 1);
        graphics.drawLine(n, n3 - 1, n + n2 - 1, n3 - 1);
        if (j == this.d) {
            graphics.setColor(Color.black);
            for (int i = 0; i < 4; ++i) {
                final int n4 = n3 / 2 + (this.c ? (2 * i - 4) : (-(2 * i - 4)));
                graphics.drawLine(n + n2 / 2 - i - 1, n4, n + n2 / 2 + i - 1, n4);
            }
        }
        else {
            j.a(graphics, n, n2, n3, b, b2);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Color h = bR.h;
        h.brighter();
        h.darker();
        final int n = this.o ? this.a.size().width : 2;
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawLine(0, this.m, size.width - 1, this.m);
        if (this.m > 0) {
            final int size2 = this.b.size();
            int n2 = 1;
            int a = 0;
            int n3 = -1;
            j j;
            int b;
            for (int n4 = 0; n4 < size2 && n2 < size.width - 1; n2 += b, a = j.a(), n3 = b, ++n4) {
                j = this.b.elementAt(n4);
                if (n4 == size2 - 1) {
                    b = size.width - n2 - n + 1;
                }
                else {
                    b = j.b();
                    if (n3 == 0) {
                        b += a;
                    }
                    if (n2 + b >= size.width - n) {
                        b = size.width - n2 - n + 1;
                    }
                }
                if (b != 0) {
                    this.a(graphics, j, n2, b, this.m, false, j == this.c);
                }
            }
            if (this.o) {
                this.a(graphics, this.d, false, false);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized boolean a(final int n) {
        return this.a.elementAt(n).d;
    }
    
    public synchronized boolean b(final aU au) {
        return this.a(this.a(au));
    }
    
    public synchronized void a(final int n, final boolean d) {
        this.a.elementAt(n).d = d;
        this.f(n);
        if (n == this.l) {
            this.h(n);
        }
    }
    
    public synchronized void a(final aU au, final boolean b) {
        final int a = this.a(au);
        if (a >= 0) {
            this.a(a, b);
        }
    }
    
    public synchronized void b(final int n, final boolean e) {
        this.a.elementAt(n).e = e;
        this.f(n);
    }
    
    public synchronized void b(final aU au, final boolean b) {
        final int a = this.a(au);
        if (a >= 0) {
            this.b(a, b);
        }
    }
    
    public synchronized void a(final int n, final Color a, final Color b, final ab ab) {
        final d d = this.a.elementAt(n);
        d.a = a;
        d.b = b;
        if (ab != null) {
            d.f = ab.d(17);
            d.g = ab.d(18);
            d.b = ab.b;
            d.d = ab.d;
            d.e = ab.e;
            d.c = ab.c;
            d.c = ab.c;
            d.f = ab.f;
        }
        this.f(n);
    }
    
    public synchronized void a(final aU au, final Color a, final Color b, final int d) {
        final int a2 = this.a(au);
        if (a2 >= 0) {
            final d d2 = this.a.elementAt(a2);
            d2.a = a;
            d2.b = b;
            d2.d = d;
        }
        this.f(a2);
    }
    
    public synchronized void a(final aU au, final Color color, final Color color2) {
        final int a = this.a(au);
        if (a >= 0) {
            if (au instanceof ab) {
                this.a(a, color, color2, (ab)au);
            }
            else {
                this.a(a, color, color2, null);
            }
        }
    }
    
    static Vector a(final l l) {
        return l.a;
    }
    
    static Color a() {
        return l.c;
    }
    
    public l(final int m) {
        this.a = new Vector();
        this.b = new Vector();
        this.a = new g(this, this);
        this.d = new Color(3355545);
        this.e = bR.a;
        this.f = bR.h;
        this.g = new Color(9211020);
        this.k = 0;
        this.l = -1;
        this.c = true;
        this.o = false;
        this.p = false;
        (this.d = new j(null, null)).c(true);
        this.d.a(ao.e("Click here to reverse the sort order of items in this list."), null);
        if (!doook.l.b) {
            doook.l.b = true;
            try {
                this.setCursor(Cursor.getDefaultCursor());
                doook.l.m = true;
            }
            catch (Throwable t) {
                doook.l.m = false;
            }
        }
        this.m = m;
        this.setBackground(Color.white);
        this.a.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.a = new e(1);
        this.i(19);
        gridBagConstraints.insets = new Insets(1 + m, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.insets = new Insets(m, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.setFont(bL.f);
    }
    
    public l() {
        this(18);
    }
    
    static {
        c = new Color(16737894);
        l.b = false;
    }
}
