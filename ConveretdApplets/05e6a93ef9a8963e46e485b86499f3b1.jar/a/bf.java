// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Scrollbar;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public final class bf extends Panel implements aB
{
    private static final Color e;
    private static boolean w;
    private static boolean e;
    private Vector q;
    private Vector w;
    private b q;
    public Color q;
    public Color w;
    private Color r;
    private Color t;
    private int q;
    private int w;
    private int e;
    private int r;
    private bH q;
    private String q;
    private String w;
    private int t;
    private av q;
    private av w;
    private av e;
    private boolean r;
    private boolean t;
    private boolean y;
    boolean q;
    private av r;
    private B q;
    private boolean u;
    protected cB q;
    protected long q;
    protected co q;
    
    public final void q(final cB q) {
        this.q = q;
    }
    
    public final void q(final av e) {
        if (this.y && e != this.e) {
            this.e = e;
            this.repaint();
            this.w();
        }
    }
    
    public final void q(final boolean b) {
        this.y = true;
        this.repaint();
        this.q.repaint();
    }
    
    final synchronized void q() {
        if (this.e != null) {
            final aX q = this.q();
            this.q(this.q, 0, this.q.size() - 1, this.e);
            if (q != null) {
                this.e = this.q(q);
            }
        }
        this.q = false;
    }
    
    private synchronized void q(final long n) {
        this.q = true;
        this.w(n);
    }
    
    public final synchronized void w() {
        this.q(0L);
    }
    
    private synchronized void u() {
        for (int size = this.q.size(), i = 0; i < size / 2; ++i) {
            final Object element = this.q.elementAt(i);
            this.q.setElementAt(this.q.elementAt(size - i - 1), i);
            this.q.setElementAt(element, size - i - 1);
        }
        this.q.repaint();
    }
    
    private int q(final aX ax, final aX ax2) {
        int q = 0;
        av av;
        for (int index = this.w.indexOf(this.e), size = this.w.size(), n = 0; n < size && (!(av = this.w.elementAt((n + index) % size)).q() || (q = ax.q(ax2, av.q())) == 0); ++n) {}
        if (this.t) {
            return q;
        }
        return -q;
    }
    
    private synchronized void q(final Vector vector, final int n, final int n2, final av av) {
        if (n2 > n) {
            final int n3 = (n + n2) / 2;
            int n4 = n2;
            int i = n;
            final aX q = vector.elementAt(n3).q;
            while (i <= n4) {
                while (i < n2 && this.q(q, vector.elementAt(i).q) > 0) {
                    ++i;
                }
                while (n4 > n && this.q(q, vector.elementAt(n4).q) < 0) {
                    --n4;
                }
                if (i < n4) {
                    final bP element = vector.elementAt(i);
                    final bP element2 = vector.elementAt(n4);
                    vector.setElementAt(element, n4);
                    vector.setElementAt(element2, i);
                }
                if (i <= n4) {
                    ++i;
                    --n4;
                }
            }
            if (n < n4) {
                this.q(vector, n, n4, av);
            }
            if (i < n2) {
                this.q(vector, i, n2, av);
            }
        }
        this.q = false;
    }
    
    public final String q() {
        return null;
    }
    
    public final int q() {
        return this.q.size();
    }
    
    public final synchronized int q(final int n) {
        return (n + this.w) / this.q;
    }
    
    private av w(final int n) {
        final Dimension size = this.size();
        final int n2 = this.y ? this.q.size().width : 2;
        final int size2 = this.w.size();
        if (n < 0) {
            return null;
        }
        if (n > size.width - n2 && n < size.width) {
            return this.r;
        }
        int n3 = -2;
        int n4 = -1;
        int n5 = 0;
        for (int i = 0; i < size2 - 1; ++i) {
            final av av;
            final int w = (av = this.w.elementAt(i)).w();
            final int q = av.q();
            int n6 = n3 + w;
            if (n4 == 0) {
                n6 += n5;
            }
            if (n <= n6 && n >= n3) {
                return av;
            }
            n3 = n6;
            n4 = w;
            n5 = q;
        }
        return (av)this.w.elementAt(size2 - 1);
    }
    
    public final int w(final int n) {
        final Dimension size = this.size();
        final int n2 = this.y ? this.q.size().width : 2;
        if (n < 0) {
            return -1;
        }
        if (n > size.width - n2 && n < size.width) {
            return -2;
        }
        int n3 = -2;
        for (int i = 0; i < this.w.size() - 1; ++i) {
            final int n4 = n3 + this.w.elementAt(i).q();
            if (n <= n4 && n >= n3) {
                return i;
            }
            n3 = n4;
        }
        return this.w.size() - 1;
    }
    
    public final synchronized int e(final int n) {
        return n * this.q - this.w;
    }
    
    public final av q(final int n) {
        return this.w.elementAt(n);
    }
    
    public final int w() {
        return this.e;
    }
    
    public final synchronized aX q() {
        if (this.e >= 0) {
            return this.q(this.e);
        }
        return null;
    }
    
    private synchronized void r(final int n) {
        final Graphics graphics;
        if (n >= 0 && n < this.q.size() && (graphics = this.q.getGraphics()) != null) {
            this.q(n, graphics);
            graphics.dispose();
        }
    }
    
    private void q(final long n, final int n2) {
        this.q.repaint(n, 0, this.e(n2), this.q.size().width, this.q - 1);
    }
    
    protected final synchronized void q(final int n, final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            final int e = this.e(n);
            final int size = this.w.size();
            final int n2 = this.y ? this.q.size().width : 2;
            final Dimension size2 = this.q.size();
            if (n >= this.q.size()) {
                return;
            }
            final bP bp = this.q.elementAt(n);
            graphics.setColor(Color.white);
            graphics.drawLine(0, e + this.q - 1, size2.width, e + this.q - 1);
            int n3 = 0;
            int n4 = 0;
            int width = size2.width;
            int n5 = 0;
            if (bp.w() == 8 || bp.w() == 9) {
                if (bp.w() == 8) {
                    n5 = Color.green.darker().getRGB();
                }
                else {
                    n5 = Color.yellow.darker().getRGB();
                }
                graphics.setColor(new Color(n5));
                graphics.fillRect(0, (e != 0) ? (e - 1) : 0, width, this.q + 1);
                n3 += 2;
                width -= 2;
            }
            if (n == this.e) {
                graphics.setColor(this.q);
            }
            else if (this.u && this.q.q((Object)bp.q)) {
                graphics.setColor(this.q);
            }
            else if (bp.q instanceof aK && ((aK)bp.q).f != 0) {
                graphics.setColor(new Color(((aK)bp.q).f));
            }
            else {
                graphics.setColor(this.w);
            }
            graphics.fillRect(n3, e, width, this.q - 1);
            if (n5 != 0) {
                graphics.setColor(new Color(n5));
                graphics.drawLine(n3, e, width, e);
            }
            for (int i = 0; i < size; ++i) {
                final av av = this.w.elementAt(i);
                int q;
                if (i == size - 1 && !av.w()) {
                    q = size2.width + 5 - n3;
                }
                else {
                    q = av.q();
                    if (i == 0) {
                        --q;
                    }
                }
                if (!av.w()) {
                    if (av.q(graphics, bp, bp.q.q(av.q()), n3, e, q, this.q, n == this.e)) {
                        n3 += q;
                    }
                }
                else if (av.q(graphics, bp, bp.q.q(av.q()), width - q - n4, e, q, this.q, n == this.e)) {
                    n4 += q;
                }
                if (n3 > size2.width - n2) {
                    break;
                }
            }
        }
    }
    
    public final synchronized int q(final aX ax) {
        for (int i = 0; i < this.q.size(); ++i) {
            if (((bP)this.q.elementAt(i)).q.equals(ax)) {
                return i;
            }
        }
        return -1;
    }
    
    private synchronized void t(final int n) {
        final Dimension size = this.q.size();
        this.w -= n;
        final Graphics q = this.q.q(0, 0, size.width, size.height - 1, n);
        this.q.paint(q);
        q.dispose();
    }
    
    private synchronized void q(final int n, final int n2) {
        final int n3 = (n >= 0) ? n : (-n);
        for (int i = 0; i < n3 / n2; ++i) {
            this.t((n >= 0) ? n2 : (-n2));
        }
        if (n3 % n2 != 0) {
            this.t((n >= 0) ? (n3 % n2) : (-(n3 % n2)));
        }
    }
    
    public final synchronized aX q(final int n) {
        return this.q.elementAt(n).q;
    }
    
    public final synchronized void q(final int e) {
        if (e >= 0 && e < this.q.size()) {
            if (this.e != e) {
                final int e2 = this.e;
                this.e = e;
                this.r(e2);
                this.r(e);
            }
            int n = 0;
            final int n2 = this.q.size().height - this.q + 2;
            final int e3;
            if ((e3 = this.e(e)) < 0) {
                n = -e3;
            }
            else if (e3 > n2) {
                n = n2 - e3;
            }
            if (n != 0) {
                this.t(n);
                this.q.setValue(this.w);
            }
            this.postEvent(new Event(this, 701, this.q(e)));
            return;
        }
        final int e4 = this.e;
        this.e = -1;
        this.r(e4);
        this.postEvent(new Event(this, 702, null));
    }
    
    private synchronized void w(final aX ax, int n) {
        if (this.e != null) {
            int i = 0;
            int n2 = this.q.size() - 1;
            int n3 = 0;
            int n4 = 0;
            Label_0161: {
                if (n2 == -1) {
                    n4 = 0;
                }
                else {
                    int n5;
                    if ((n5 = this.q(ax, this.q.elementAt(n2).q)) > 0) {
                        n4 = n2 + 1;
                    }
                    else {
                        while (true) {
                            while (i <= n2) {
                                n3 = i + (n2 - i) / 2;
                                if ((n5 = this.q(ax, this.q.elementAt(n3).q)) == 0) {
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
        this.q.insertElementAt(new bP(this, this, ax), n);
        if (this.e >= n) {
            ++this.e;
        }
        final int e = this.e(n);
        if (this.isShowing()) {
            if (this.w == this.q.getMaximum() && this.w > 0 && n == this.q.size() - 1) {
                this.q(-this.q, this.q);
            }
            else {
                final Dimension size = this.q.size();
                if (e >= 0 && e < size.height) {
                    if (this.q.size() > 1) {
                        this.q.q(0, e - 1, size.width, size.height - e, this.q);
                    }
                    this.q(0L, n);
                }
            }
        }
        if (e < 0) {
            this.w += this.q;
        }
        this.e();
    }
    
    private av q(final Event event) {
        if (event.y < this.r) {
            int n = 0;
            for (int i = 0; i < this.w.size() - 1; ++i) {
                final int n2 = n + this.w.elementAt(i).q();
                if (event.x > n2 - 4 && event.x < n2 + 4) {}
                if (event.x < n2) {
                    return null;
                }
                n = n2;
            }
        }
        return null;
    }
    
    public final String q(final Object o) {
        return this.q;
    }
    
    public final boolean handleEvent(final Event event) {
        Label_0497: {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n;
                    if ((n = this.w - this.q.getValue()) != 0) {
                        this.t(n);
                    }
                    return true;
                }
                case 401:
                case 403: {
                    final int size = this.q.size();
                    if (event.key == 1005 && this.e < size - 1) {
                        this.q(this.e + 1);
                    }
                    else if (event.key == 1004 && this.e > 0) {
                        this.q(this.e - 1);
                    }
                    else if ((event.key == 10 || event.key == 13) && this.e >= 0) {
                        this.postEvent(new Event(this, 1001, this.q()));
                    }
                    return true;
                }
                case 501: {
                    this.requestFocus();
                    final av q = this.q(event);
                    this.q = q;
                    if (q != null) {
                        this.t = event.x;
                    }
                    else if (this.y) {
                        this.w = this.w(event.x);
                        if (this.w.q()) {
                            final Graphics graphics = this.getGraphics();
                            this.q(graphics, this.w, true, this.w == this.e);
                            graphics.dispose();
                            this.r = true;
                        }
                        else {
                            this.w = null;
                        }
                    }
                    return true;
                }
                case 502: {
                    if (!this.r || !this.y || event.target != this || this.w == null) {
                        break Label_0497;
                    }
                    if (this.w != this.r && this.w != this.e) {
                        this.q(this.w);
                        break Label_0497;
                    }
                    if (this.w == this.r) {
                        this.t = !this.t;
                        this.u();
                        this.q(this.e);
                    }
                    final Graphics graphics2 = this.getGraphics();
                    this.q(graphics2, this.w, false, this.w == this.e);
                    graphics2.dispose();
                    break Label_0497;
                }
                case 503: {
                    if (event.target == this && event.y < this.r) {
                        av av;
                        if (this.y && event.x > this.size().width - this.q.size().width) {
                            av = this.r;
                        }
                        else {
                            av = this.w(event.x);
                        }
                        if (av != null) {
                            this.q = av.q(this.e == av);
                            if (this.q == null || !this.q.equals(this.w)) {
                                this.postEvent(new Event(this, 7689, this.q));
                                this.w = this.q;
                            }
                        }
                    }
                    if (bf.w) {
                        if (this.q(event) != null) {
                            this.setCursor(Cursor.getPredefinedCursor(11));
                        }
                        else {
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                    return false;
                }
                case 506: {
                    if (event.target == this && this.q != null) {
                        final int q2 = this.q.q();
                        int x = event.x;
                        final int width = this.size().width;
                        final int n2 = this.y ? this.q.size().width : 2;
                        if (x > width - n2) {
                            x = width - n2;
                        }
                        int n3;
                        if ((n3 = q2 + (x - this.t)) < 15) {
                            x += 15 - n3;
                            n3 = 15;
                        }
                        if (n3 != q2) {
                            this.q.q(n3);
                        }
                        this.t = x;
                    }
                    if (event.target == this && this.w != null) {
                        final boolean b = event.y >= 0 && event.y < this.r && this.w(event.x) == this.w;
                        if (this.r && !b) {
                            this.r = false;
                            final Graphics graphics3 = this.getGraphics();
                            this.q(graphics3, this.w, false, this.w == this.e);
                            graphics3.dispose();
                        }
                        else if (!this.r && b) {
                            this.r = true;
                            final Graphics graphics4 = this.getGraphics();
                            this.q(graphics4, this.w, true, this.w == this.e);
                            graphics4.dispose();
                        }
                    }
                    return true;
                }
                case 1005: {
                    if (this.q != null && System.currentTimeMillis() - this.q > 2000L) {
                        new ci(this).start();
                        break;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    protected final void e() {
        this.q.setValues(this.w, this.q.size().height, 0, this.q.size() * this.q - 2);
        this.w = this.q.getValue();
        if (this.e < 0) {
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public final void resize(final int n, final int n2) {
        this.q.resize(n - this.q.size().width, n2 - this.r);
    }
    
    public final void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public final void w(final int n) {
        ((Scrollbar)(this.q = n)).setLineIncrement(n);
        this.e();
        this.q.repaint();
    }
    
    public final void w(final av av) {
        this.w.insertElementAt(av, this.w.size());
        av.q = this;
    }
    
    public final void q(final aX ax) {
        this.w(ax, this.q.size());
    }
    
    public final synchronized void q(final aX ax, final int n) {
        this.q.setElementAt(new bP(this, this, ax), n);
        if (this.e != null) {
            this.q(200L);
            return;
        }
        this.q(200L, n);
    }
    
    public final boolean q(final aX ax) {
        final int q;
        if ((q = this.q(ax)) != -1) {
            this.e(q);
            return true;
        }
        return false;
    }
    
    public final void e(int e) {
        if (this.e == e) {
            if (e == this.q.size() - 1) {
                this.q(e - 1);
            }
            else {
                this.q(e + 1);
                --this.e;
            }
        }
        else if (this.e > e) {
            this.q(e - 1);
        }
        this.q.elementAt(e);
        this.q.removeElementAt(e);
        e = this.e(e);
        final Dimension size = this.q.size();
        if (e < size.height && e > -this.q && this.isShowing()) {
            int q;
            if (e < 0) {
                q = this.q + e;
                e = 0;
            }
            else {
                q = this.q;
            }
            final int maximum = this.q.getMaximum();
            if (this.w > maximum - this.q && maximum != 0 && this.w != 0) {
                if (this.w < this.q) {
                    e += this.w;
                    this.q(this.w, this.w);
                    this.q.paint(this.q.q(0, e, size.width, size.height - e - 1, -q));
                }
                else {
                    this.w -= this.q;
                    this.q.paint(this.q.q(0, 0, size.width, e + this.q, this.q));
                }
            }
            else {
                this.q.paint(this.q.q(0, e, size.width, size.height - e - 1, -q));
            }
        }
        this.e();
    }
    
    public final synchronized void r() {
        this.q.removeAllElements();
        this.e = -1;
        this.w = 0;
        this.q.setValues(0, 0, 0, 0);
        this.q.repaint();
        this.postEvent(new Event(this, 702, null));
    }
    
    public final void e(final av av) {
        final int index;
        if ((index = this.w.indexOf(av)) >= 0) {
            final int n = index;
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                n2 += ((av)this.w.elementAt(i)).q();
            }
            final int n3 = n2;
            final int q = this.w.elementAt(n).q();
            final int height = this.q.size().height;
            final int n4 = q;
            final int n5 = n3;
            if (this.isShowing()) {
                this.q.repaint(n5, 0, n4, height);
            }
        }
    }
    
    public final void t() {
        this.w(0L);
    }
    
    private void w(final long n) {
        if (this.isShowing()) {
            this.q.repaint(n);
        }
    }
    
    protected static void q(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        q(graphics, s, n, 0, n3, n4, 0, false, null);
    }
    
    protected static void q(final Graphics graphics, String string, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final bP bp) {
        final int n6 = n2 + (n4 + graphics.getFontMetrics().getAscent()) / 2 - 1;
        final FontMetrics fontMetrics;
        int stringWidth;
        if ((stringWidth = (fontMetrics = graphics.getFontMetrics()).stringWidth(string)) > n3) {
            final int stringWidth2 = fontMetrics.stringWidth("...");
            final int length = string.length();
            int n7 = 0;
            int i = length;
            int n8 = length;
            String substring = string;
            if ("...".length() < length) {
                while (i > n7) {
                    substring = string.substring(0, n8);
                    if ((stringWidth = fontMetrics.stringWidth(substring) + stringWidth2) <= n3 && n8 == length) {
                        break;
                    }
                    if (stringWidth < n3) {
                        n7 = n8 + 1;
                    }
                    else {
                        i = n8;
                    }
                    n8 = (n7 + i) / 2;
                }
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
        if (cs.q == 0 && b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n9, n2 + n4 / 2, n9 + stringWidth, n2 + n4 / 2);
        }
        int q = 0;
        if (bp != null) {
            q = bp.q();
        }
        if (q > 0) {
            graphics.drawImage(h.q().w[q], n9 - 8, n6 - 13, null);
            n9 += 22;
        }
        if (bp != null && bp.q() != null) {
            graphics.drawImage(bp.q(), n9 - 2, n6 - 17, null);
        }
        else {
            graphics.drawString(string, n9, n6);
        }
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n9, n2 + n4 / 2, n9 + stringWidth, n2 + n4 / 2);
        }
    }
    
    private void q(final Graphics graphics, final av av, final boolean b, final boolean b2) {
        final Dimension size = this.size();
        final int n = this.y ? this.q.size().width : 2;
        int q;
        int n2;
        if (av == this.r) {
            q = n - 2;
            n2 = size.width - n + 1;
        }
        else {
            final int index;
            final int n3 = index = this.w.indexOf(av);
            int n4 = 1;
            for (int i = 0; i < index; ++i) {
                int w = this.w.elementAt(i).w();
                final av av2;
                if (i > 0 && (av2 = this.w.elementAt(i - 1)).w() == 0) {
                    w += av2.q();
                }
                n4 += w;
            }
            n2 = n4;
            if (n3 == this.w.size() - 1) {
                q = size.width - n2 - n + 1;
            }
            else {
                q = av.q();
                final av av3;
                if (n3 > 0 && (av3 = this.w.elementAt(n3 - 1)).w() == 0) {
                    q += av3.q();
                }
            }
        }
        this.q(graphics, av, n2, q, this.r, b, b2);
    }
    
    private void q(final Graphics graphics, final av av, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        final Color color;
        final Color brighter = (color = ((b || b2) ? this.t : this.r)).brighter();
        final Color darker = color.darker();
        graphics.setColor(color);
        graphics.fillRect(n + 1, 2, n2 - 2, n3 - 3);
        graphics.setColor((!b && !b2) ? brighter : darker);
        graphics.drawLine(n, 1, n, n3 - 1);
        graphics.drawLine(n, 1, n + n2 - 1, 1);
        graphics.setColor((!b && !b2) ? darker : brighter);
        graphics.drawLine(n + n2 - 1, n3 - 1, n + n2 - 1, 1);
        graphics.drawLine(n, n3 - 1, n + n2 - 1, n3 - 1);
        if (av == this.r) {
            graphics.setColor(Color.black);
            for (int i = 0; i < 4; ++i) {
                final int n4 = n3 / 2 + (this.t ? (i * 2 - 4) : (-(i * 2 - 4)));
                graphics.drawLine(n + n2 / 2 - i - 1, n4, n + n2 / 2 + i - 1, n4);
            }
            return;
        }
        av.q(graphics, n, n2, n3, b);
    }
    
    public final void paint(final Graphics graphics) {
        final Color w;
        (w = ai.w).brighter();
        w.darker();
        final int n = this.y ? this.q.size().width : 2;
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawLine(0, this.r, size.width - 1, this.r);
        if (this.r > 0) {
            final int size2 = this.w.size();
            int n2 = 1;
            int q = 0;
            int n3 = -1;
            av av;
            int w2;
            for (int n4 = 0; n4 < size2 && n2 < size.width - 1; n2 += w2, q = av.q(), n3 = w2, ++n4) {
                av = this.w.elementAt(n4);
                if (n4 == size2 - 1) {
                    w2 = size.width - n2 - n + 1;
                }
                else {
                    w2 = av.w();
                    if (n3 == 0) {
                        w2 += q;
                    }
                    if (n2 + w2 >= size.width - n) {
                        w2 = size.width - n2 - n + 1;
                    }
                }
                if (w2 != 0) {
                    this.q(graphics, av, n2, w2, this.r, false, av == this.e);
                }
            }
            if (this.y) {
                this.q(graphics, this.r, false, false);
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private synchronized void q(final int n, final boolean w) {
        this.q.elementAt(n).w = w;
        this.q(0L, n);
    }
    
    public final synchronized void q(final aX ax, final boolean b) {
        final int q;
        if ((q = this.q(ax)) >= 0) {
            this.q(q, b);
        }
    }
    
    private synchronized void q(final int n, final Color q, final Color w) {
        final bP bp;
        (bp = this.q.elementAt(n)).q = q;
        bp.w = w;
        this.q(0L, n);
    }
    
    public final synchronized void q(final aX ax, final Color color, final Color color2) {
        final int q;
        if ((q = this.q(ax)) >= 0) {
            this.q(q, color, color2);
        }
    }
    
    static Vector q(final bf bf) {
        return bf.q;
    }
    
    static Color q() {
        return bf.e;
    }
    
    private bf(final int n) {
        this.q = new B(50, 0);
        this.u = false;
        this.q = 0L;
        this.q = new Vector();
        this.w = new Vector();
        this.q = new b(this, this);
        this.q = new Color(3355545);
        this.w = ai.e;
        this.r = ai.w;
        this.t = new Color(9211020);
        this.w = 0;
        this.e = -1;
        this.t = true;
        this.y = false;
        this.q = false;
        (this.r = new av(null, null)).q(true);
        this.r.q(al.q("Click here to reverse the sort order of items in this list."), null);
        if (!bf.e) {
            bf.e = true;
            try {
                this.setCursor(Cursor.getDefaultCursor());
                bf.w = true;
            }
            catch (Throwable t) {
                bf.w = false;
            }
        }
        this.r = 18;
        this.setBackground(Color.white);
        this.q.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.q = new bH(1);
        this.w(19);
        gridBagConstraints.insets = new Insets(19, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.insets = new Insets(18, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.setFont(be.r);
    }
    
    public bf() {
        this(18);
    }
    
    public bf(final boolean u) {
        this();
        this.u = u;
    }
    
    public bf(final co q) {
        this();
        this.q = q;
    }
    
    public final Vector q() {
        if (this.q.q > 0) {
            final Vector<Object> vector = new Vector<Object>();
            for (int i = 0; i < this.q.q; ++i) {
                vector.addElement(this.q.q(i));
            }
            return vector;
        }
        return null;
    }
    
    public final void y() {
        this.q.q();
        this.q.removeAllElements();
        this.e = -1;
    }
    
    static boolean q(final bf bf) {
        return bf.u;
    }
    
    static Vector w(final bf bf) {
        return bf.q;
    }
    
    static B q(final bf bf) {
        return bf.q;
    }
    
    static {
        e = new Color(16737894);
        bf.e = false;
    }
}
