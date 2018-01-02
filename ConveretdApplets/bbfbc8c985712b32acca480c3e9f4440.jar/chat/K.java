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

public final class K extends Panel implements aB
{
    private static final Color c;
    private static boolean b;
    private static boolean c;
    private boolean d;
    private Vector a;
    private Vector b;
    private boolean e;
    private boolean f;
    private w a;
    public Color a;
    public Color b;
    private Color d;
    private Color e;
    private int a;
    private int b;
    private int c;
    private int d;
    private cn a;
    private String a;
    private String b;
    private int e;
    private I a;
    private I b;
    private I c;
    private boolean g;
    private boolean h;
    private boolean i;
    boolean a;
    private I d;
    
    public final void a(final I c) {
        if (this.i && c != this.c) {
            this.c = c;
            this.repaint();
            this.c();
        }
    }
    
    public final void a() {
        this.i = true;
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
    
    public final synchronized void c() {
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
            final I j;
            if ((j = this.b.elementAt((i + index) % size)).b) {
                if (a instanceof aZ && "name".equals(j.a)) {
                    if (this.d) {
                        n = a.a(a2, "rp");
                    }
                    else if (!this.e) {
                        n = a.a(a2, "name2");
                    }
                    else {
                        n = a.a(a2, j.a);
                    }
                }
                else {
                    n = a.a(a2, j.a);
                }
                if (n != 0) {
                    break;
                }
            }
        }
        if (this.h) {
            return n;
        }
        return -n;
    }
    
    private void a(final Vector vector, final int n, final int n2, final I i) {
        if (n2 > n) {
            this.b.indexOf(i);
            this.b.size();
            final int n3 = (n + n2) / 2;
            int n4 = n2;
            int j = n;
            final a a = vector.elementAt(n3).a;
            while (j <= n4) {
                while (j < n2 && this.a(a, vector.elementAt(j).a) > 0) {
                    ++j;
                }
                while (n4 > n && this.a(a, vector.elementAt(n4).a) < 0) {
                    --n4;
                }
                if (j < n4) {
                    final y element = vector.elementAt(j);
                    final y element2 = vector.elementAt(n4);
                    vector.setElementAt(element, n4);
                    vector.setElementAt(element2, j);
                }
                if (j <= n4) {
                    ++j;
                    --n4;
                }
            }
            if (n < n4) {
                this.a(vector, n, n4, i);
            }
            if (j < n2) {
                this.a(vector, j, n2, i);
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
    
    private I b(final int n) {
        final Dimension size = this.size();
        final int n2 = this.i ? this.a.size().width : 2;
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
            final I j;
            final int a = (j = this.b.elementAt(i)).a;
            final int b = j.b;
            int n6 = n3 + a;
            if (n4 == 0) {
                n6 += n5;
            }
            if (n <= n6 && n >= n3) {
                return j;
            }
            n3 = n6;
            n4 = a;
            n5 = b;
        }
        return (I)this.b.elementAt(size2 - 1);
    }
    
    public final int b(final int n) {
        final Dimension size = this.size();
        final int n2 = this.i ? this.a.size().width : 2;
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
    
    public final I a(final int n) {
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
            final int n2 = this.i ? this.a.size().width : 2;
            final Dimension size2 = this.a.size();
            graphics.setColor(Color.white);
            if (n >= this.a.size()) {
                return;
            }
            final y y = this.a.elementAt(n);
            graphics.drawLine(0, c + this.a - 1, size2.width, c + this.a - 1);
            int n3 = 0;
            for (int i = 0; i < size; ++i) {
                final I j = this.b.elementAt(i);
                int b;
                if (i == size - 1) {
                    b = size2.width - n3;
                }
                else {
                    b = j.b;
                    if (i == 0) {
                        --b;
                    }
                }
                if (n == this.c) {
                    graphics.setColor(this.a);
                }
                else {
                    graphics.setColor(y.c);
                }
                graphics.fillRect(n3, c, b, this.a - 1);
                if (y.c > 0) {
                    if (y.c == 1) {
                        graphics.setColor(new Color(39168));
                    }
                    else {
                        graphics.setColor(new Color(12566528));
                    }
                    graphics.drawRect(0, c, size2.width, this.a - 3);
                }
                j.a(graphics, y, y.a.a(j.a), n3, c, b, this.a, n == this.c);
                if ((n3 += b) > size2.width - n2) {
                    break;
                }
            }
        }
    }
    
    public final void a(final boolean d) {
        this.d = d;
    }
    
    public final boolean a() {
        return this.d;
    }
    
    private int d(final int n) {
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            int a = this.b.elementAt(i).a;
            final I j;
            if (i > 0 && (j = this.b.elementAt(i - 1)).a == 0) {
                a += j.b;
            }
            n2 += a;
        }
        return n2;
    }
    
    public final synchronized int a(final a a) {
        for (int i = 0; i < this.a.size(); ++i) {
            if (((y)this.a.elementAt(i)).a.equals(a)) {
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
        this.a.insertElementAt(new y(a), n);
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
        this.d();
    }
    
    private I a(final Event event) {
        if (event.y < this.d) {
            int n = 0;
            for (int i = 0; i < this.b.size() - 1; ++i) {
                final I j = this.b.elementAt(i);
                n += j.b;
                if (event.x > n - 4 && event.x < n + 4 && j.a) {
                    return j;
                }
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
        Label_0481: {
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
                    final I a = this.a(event);
                    this.a = a;
                    if (a != null) {
                        this.e = event.x;
                    }
                    else if (this.i) {
                        this.b = this.b(event.x);
                        if (this.b.b) {
                            final Graphics graphics = this.getGraphics();
                            this.a(graphics, this.b, true, this.b == this.c);
                            graphics.dispose();
                            this.g = true;
                        }
                        else {
                            this.b = null;
                        }
                    }
                    return true;
                }
                case 502: {
                    if (!this.g || !this.i || event.target != this || this.b == null) {
                        break Label_0481;
                    }
                    if (this.b != this.d && this.b != this.c) {
                        this.a(this.b);
                        break Label_0481;
                    }
                    if (this.b == this.d) {
                        this.h = !this.h;
                        this.g();
                    }
                    final Graphics graphics2 = this.getGraphics();
                    this.a(graphics2, this.b, false, this.b == this.c);
                    graphics2.dispose();
                    break Label_0481;
                }
                case 503: {
                    if (event.target == this && event.y < this.d) {
                        I i;
                        if (this.i && event.x > this.size().width - this.a.size().width) {
                            i = this.d;
                        }
                        else {
                            i = this.b(event.x);
                        }
                        if (i != null) {
                            final I j = i;
                            final boolean b = this.c == i;
                            final I k = j;
                            this.a = (b ? null : k.b);
                            if (this.a == null || !this.a.equals(this.b)) {
                                this.postEvent(new Event(this, 7689, this.a));
                                this.b = this.a;
                            }
                        }
                    }
                    if (K.b) {
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
                        final int b2 = this.a.b;
                        int x = event.x;
                        final int width = this.size().width;
                        final int n2 = this.i ? this.a.size().width : 2;
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
                        if (this.g && !b3) {
                            this.g = false;
                            final Graphics graphics3 = this.getGraphics();
                            this.a(graphics3, this.b, false, this.b == this.c);
                            graphics3.dispose();
                        }
                        else if (!this.g && b3) {
                            this.g = true;
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
    
    protected final void d() {
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
    
    public final int c() {
        return this.a;
    }
    
    public final void b(final int n) {
        ((Scrollbar)(this.a = n)).setLineIncrement(n);
        this.d();
        this.a.repaint();
    }
    
    public final void b(final I i) {
        this.a(i, this.b.size());
    }
    
    public final void a(final I i, final int n) {
        this.b.insertElementAt(i, n);
        i.a = this;
    }
    
    public final void c(final a a) {
        this.b(a, this.a.size());
    }
    
    public final synchronized void a(final a a, final int n) {
        this.a.setElementAt(new y(a), n);
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
                    final K k = this;
                    --k.c;
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
                        final K i = this;
                        i.b -= this.a;
                        this.a.paint(this.a.a(0, size.width, c + this.a, this.a));
                    }
                }
                else {
                    this.a.paint(this.a.a(c, size.width, size.height - c - 1, -a3));
                }
            }
            this.d();
            return true;
        }
        return false;
    }
    
    public final synchronized void e() {
        this.a.removeAllElements();
        this.c = -1;
        this.b = 0;
        this.a.setValues(0, 0, 0, 0);
        this.a.repaint();
        this.postEvent(new Event(this, 702, null));
    }
    
    public final void c(final I i) {
        final int index;
        if ((index = this.b.indexOf(i)) >= 0) {
            int n = 0;
            for (int j = 0; j < index; ++j) {
                n += ((I)this.b.elementAt(j)).b;
            }
            if (index == this.b.size() - 1) {
                this.a(n, this.size().width - this.d(index) - (this.i ? this.a.size().width : 2) + 1, this.a.size().height);
                return;
            }
            this.a(n, this.b.elementAt(index).b, this.a.size().height);
        }
    }
    
    public final void f() {
        this.b(0L);
    }
    
    private void b(final long n) {
        if (this.isShowing()) {
            this.a.repaint(n);
        }
    }
    
    private void a(final int n, final int n2, final int n3) {
        if (this.isShowing()) {
            this.a.repaint(n, 0, n2, n3);
        }
    }
    
    protected static void a(final Graphics graphics, final String s, final int n, final int n2, final int n3) {
        a(graphics, s, n, 0, n2, n3, 0, false, 0);
    }
    
    protected static void a(final Graphics graphics, String string, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final int n6) {
        final int n7 = n2 + (n4 + graphics.getFontMetrics().getAscent()) / 2 - 1;
        final FontMetrics fontMetrics;
        int n8 = (fontMetrics = graphics.getFontMetrics()).stringWidth(string);
        if (n6 != 0) {
            n8 = fontMetrics.stringWidth(string + "...");
        }
        if (n8 > n3) {
            final int stringWidth = fontMetrics.stringWidth("...");
            final int length = string.length();
            int n9 = 0;
            int n10;
            int i = n10 = length;
            String substring = string;
            while (i > n9) {
                substring = string.substring(0, n10);
                n8 = fontMetrics.stringWidth(substring) + stringWidth;
                if (n6 != 0) {
                    n8 = fontMetrics.stringWidth(substring + "...") + stringWidth;
                }
                if (n8 <= n3 && n10 == length) {
                    break;
                }
                if (n8 < n3) {
                    n9 = n10 + 1;
                }
                else {
                    i = n10;
                }
                n10 = (n9 + i) / 2;
            }
            string = substring + "...";
        }
        int n11 = 0;
        switch (n5) {
            case 2: {
                n11 = n + n3 - n8;
                break;
            }
            case 1: {
                n11 = n + (n3 - n8) / 2;
                break;
            }
            default: {
                n11 = n;
                break;
            }
        }
        graphics.drawString(string, n11, n7);
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n11, n2 + n4 / 2, n11 + n8, n2 + n4 / 2);
        }
    }
    
    private void a(final Graphics graphics, final I i, final boolean b, final boolean b2) {
        final Dimension size = this.size();
        final int n = this.i ? this.a.size().width : 2;
        int b3;
        int d;
        if (i == this.d) {
            b3 = n - 2;
            d = size.width - n + 1;
        }
        else {
            final int index = this.b.indexOf(i);
            d = this.d(index);
            if (index == this.b.size() - 1) {
                b3 = size.width - d - n + 1;
            }
            else {
                b3 = i.b;
                final I j;
                if (index > 0 && (j = this.b.elementAt(index - 1)).a == 0) {
                    b3 += j.b;
                }
            }
        }
        this.a(graphics, i, d, b3, this.d, b, b2);
    }
    
    private void a(final Graphics graphics, final I i, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
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
        if (i == this.d) {
            graphics.setColor(Color.black);
            for (int j = 0; j < 4; ++j) {
                final int n4 = n3 / 2 + (this.h ? ((j << 1) - 4) : (-((j << 1) - 4)));
                graphics.drawLine(n + n2 / 2 - j - 1, n4, n + n2 / 2 + j - 1, n4);
            }
            return;
        }
        i.a(graphics, n, n2, n3, b, b2);
    }
    
    public final void b(final boolean e) {
        this.e = e;
    }
    
    public final void c(final boolean f) {
        this.f = f;
    }
    
    public final boolean b() {
        return this.f;
    }
    
    public final void paint(final Graphics graphics) {
        final Color b;
        (b = o.b).brighter();
        b.darker();
        final int n = this.i ? this.a.size().width : 2;
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawLine(0, this.d, size.width - 1, this.d);
        if (this.d > 0) {
            final int size2 = this.b.size();
            int n2 = 1;
            int b2 = 0;
            int n3 = -1;
            I i;
            int a;
            for (int n4 = 0; n4 < size2 && n2 < size.width - 1; n2 += a, b2 = i.b, n3 = a, ++n4) {
                i = this.b.elementAt(n4);
                if (n4 == size2 - 1) {
                    a = size.width - n2 - n + 1;
                }
                else {
                    a = i.a;
                    if (n3 == 0) {
                        a += b2;
                    }
                    if (n2 + a >= size.width - n) {
                        a = size.width - n2 - n + 1;
                    }
                    if (n4 == 1 && n4 < size2 - 1 && this.a(n4 + 1).c && (a = size.width - n2 - n - (this.d(this.b.size()) - this.d(1) - i.b) + 1) != i.b) {
                        i.a(a);
                    }
                }
                if (a != 0) {
                    this.a(graphics, i, n2, a, this.d, false, i == this.c);
                }
            }
            if (this.i) {
                this.a(graphics, this.d, false, false);
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private synchronized boolean a(final int n) {
        return this.a.elementAt(n).a;
    }
    
    public final synchronized boolean b(final a a) {
        return this.a(this.a(a));
    }
    
    private synchronized void a(final int n, final boolean a) {
        this.a.elementAt(n).a = a;
        this.a(0L, n);
        if (n == this.c) {
            this.a(n);
        }
    }
    
    public final synchronized void a(final a a, final boolean b) {
        final int a2;
        if ((a2 = this.a(a)) >= 0) {
            this.a(a2, b);
        }
    }
    
    private synchronized void b(final int n, final boolean b) {
        this.a.elementAt(n).b = b;
        this.a(0L, n);
    }
    
    public final synchronized void b(final a a, final boolean b) {
        final int a2;
        if ((a2 = this.a(a)) >= 0) {
            this.b(a2, b);
        }
    }
    
    private synchronized void a(final aZ az, final int n, final Color a, final Color b, final Color c, final int c2) {
        final y y = this.a.elementAt(n);
        if (a != null) {
            y.a = a;
        }
        if (b != null) {
            y.b = b;
        }
        if (c != null) {
            y.c = c;
        }
        if (az != null) {
            y.a = az.e;
            y.b = az.d;
            y.d = az.g;
            y.c = (az.a(41) && az.e != 0);
            y.e = az.a(23);
            y.d = az.a(0);
            y.c = c2;
        }
        this.a(0L, n);
    }
    
    public final synchronized void a(final a a, final Color color, final Color color2, final Color color3) {
        this.a(a, color, color2, color3, 0);
    }
    
    public final synchronized void a(final a a, final Color color, final Color color2, final Color color3, final int n) {
        final int a2;
        if ((a2 = this.a(a)) >= 0) {
            if (a instanceof aZ) {
                this.a((aZ)a, a2, color, color2, color3, n);
                return;
            }
            this.a(null, a2, color, color2, color3, n);
        }
    }
    
    static Vector a(final K k) {
        return k.a;
    }
    
    static Color a() {
        return K.c;
    }
    
    private K(final int n) {
        this.a = new Vector();
        this.b = new Vector();
        this.a = new w(this);
        this.a = new Color(3355545);
        this.b = o.c;
        this.d = o.b;
        this.e = new Color(9211020);
        this.b = 0;
        this.c = -1;
        this.h = true;
        this.i = false;
        this.a = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.d = new I(null, null);
        this.d.b = true;
        this.d.b = aS.a(464);
        if (!K.c) {
            K.c = true;
            try {
                this.setCursor(Cursor.getDefaultCursor());
                K.b = true;
            }
            catch (Throwable t) {
                K.b = false;
            }
        }
        this.d = 18;
        this.setBackground(Color.white);
        this.a.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.a = new cn();
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
        this.setFont(bk.c);
    }
    
    public K() {
        this(18);
    }
    
    static {
        c = new Color(16737894);
        K.c = false;
    }
}
