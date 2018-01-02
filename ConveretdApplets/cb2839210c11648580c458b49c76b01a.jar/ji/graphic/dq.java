// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import ji.v1event.d7;
import ji.v1event.ag;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Rectangle;
import ji.util.i;
import java.awt.SystemColor;
import ji.util.e;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import ji.util.d;
import java.awt.Component;
import java.awt.Dimension;
import ji.awt.c;
import ji.document.ad;
import java.awt.Point;
import java.awt.Image;
import java.awt.Cursor;
import ji.v1base.bz;

public class dq extends bz
{
    static final Cursor a;
    static final Cursor b;
    static final Cursor c;
    static final Cursor d;
    static final Cursor e;
    boolean f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    int l;
    Image m;
    Image n;
    Image o;
    Image p;
    int[] q;
    int[] r;
    int[] s;
    int[] t;
    Point u;
    String v;
    ad w;
    uv x;
    c y;
    Dimension z;
    
    public dq(final String v, final ad w) {
        super(v);
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = 3;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = new Dimension(0, 0);
        this.v = v;
        this.w = w;
        this.setBorderStyle(0);
        ji.util.d.a((MouseListener)(this.x = new uv(this)));
    }
    
    public final void a(final int l) {
        this.l = l;
    }
    
    public final void f(final boolean h) {
        this.h = h;
    }
    
    public void paintComponent(final Graphics graphics) {
        try {
            super.paintComponent(graphics);
            this.a(graphics);
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        try {
            super.paint(graphics);
            if (!this.a()) {
                this.a(graphics);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final Graphics graphics) {
        try {
            final boolean h = this.h();
            final Dimension size = this.getSize();
            if (this.f || this.i) {
                if (h) {
                    this.setCursor(dq.c);
                }
                else {
                    this.setCursor(dq.e);
                }
            }
            else if (this.g) {
                if (h) {
                    this.setCursor(dq.b);
                }
                else {
                    this.setCursor(dq.d);
                }
            }
            else {
                this.setCursor(dq.a);
            }
            if (h) {
                if (this.s == null) {
                    this.s = new int[size.width * size.height];
                }
                if (this.t == null) {
                    this.t = new int[size.width * size.height];
                }
                if (this.o == null) {
                    this.o = this.a(false, size, this.s);
                }
                if (this.p == null) {
                    this.p = this.a(true, size, this.t);
                }
                if (this.f) {
                    if (this.p != null) {
                        graphics.drawImage(this.p, 0, 0, null);
                    }
                }
                else if (this.o != null) {
                    graphics.drawImage(this.o, 0, 0, null);
                }
            }
            else {
                if (this.q == null) {
                    this.q = new int[size.width * size.height];
                }
                if (this.r == null) {
                    this.r = new int[size.width * size.height];
                }
                if (this.m == null) {
                    this.m = this.b(false, size, this.q);
                }
                if (this.n == null) {
                    this.n = this.b(true, size, this.r);
                }
                if (this.f) {
                    if (this.n != null) {
                        graphics.drawImage(this.n, 0, 0, null);
                    }
                }
                else if (this.m != null) {
                    graphics.drawImage(this.m, 0, 0, null);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final Image a(final boolean b, final Dimension dimension, final int[] array) {
        Image image = null;
        try {
            if (dimension.width > 0 && dimension.height > 0) {
                Color color;
                if (ji.util.e.g != null) {
                    color = ji.util.e.g;
                }
                else {
                    color = SystemColor.controlShadow;
                }
                if (ji.util.e.h != null) {
                    final Color h = ji.util.e.h;
                }
                else {
                    final SystemColor controlLtHighlight = SystemColor.controlLtHighlight;
                }
                final int n = dimension.width * dimension.height;
                final Color background = this.getBackground();
                final int rgb = background.getRGB();
                if (b && ji.util.i.c(7) && ji.util.e.t() && ji.util.d.ar()) {
                    for (int i = 0; i < n; ++i) {
                        array[i] = rgb;
                    }
                    color = background.darker().darker().darker();
                }
                else {
                    for (int j = 0; j < n; ++j) {
                        array[j] = rgb;
                    }
                }
                final int rgb2 = color.getRGB();
                final int n2 = 3;
                final int n3 = 8;
                int n4 = (dimension.width - n3 * 2 - n2) / 2;
                int n5 = n4 - n3 - n2;
                final int n6 = 2;
                int n7 = n4 + n3;
                int n8 = n5 + n3;
                final int n9 = n6 + n3;
                try {
                    for (int k = n6; k < n9; ++k) {
                        final int n10 = k * dimension.width;
                        for (int l = n4; l <= n7; ++l) {
                            final int n11 = n10 + l;
                            if (n11 < n && n11 > 0) {
                                array[n11] = rgb2;
                            }
                        }
                        final int n12 = (n9 - k - 2) * dimension.width;
                        for (int n13 = n5; n13 <= n8; ++n13) {
                            final int n14 = n12 + n13;
                            if (n14 < n && n14 > 0) {
                                array[n14] = rgb2;
                            }
                        }
                        ++n4;
                        ++n5;
                        --n7;
                        --n8;
                    }
                }
                catch (Exception ex) {}
                final int m = ji.util.d.j(background.darker().darker().darker());
                int n15;
                if (ji.util.e.h != null) {
                    n15 = ji.util.d.j(ji.util.e.h);
                }
                else {
                    n15 = ji.util.d.j(ji.util.e.c(ji.util.e.c(background.brighter())));
                }
                final int n16 = 4;
                this.a(new Rectangle(1, 1, n5 - n3 - 3, dimension.height - 2), dimension, n16, array, m, n15);
                this.a(new Rectangle(n4 + 3, 1, dimension.width - n4 - 5, dimension.height - 2), dimension, n16, array, m, n15);
                Color color2;
                if (ji.util.e.g != null) {
                    color2 = ji.util.e.g;
                }
                else {
                    color2 = SystemColor.controlShadow;
                }
                final int rgb3 = color2.getRGB();
                final int n17 = (dimension.height - 1) * dimension.width;
                for (int n18 = 0; n18 < dimension.width; ++n18) {
                    array[n18] = rgb3;
                }
                for (int n19 = 0; n19 < dimension.width; ++n19) {
                    array[n19 + n17] = rgb3;
                }
                image = this.createImage(new MemoryImageSource(dimension.width, dimension.height, ColorModel.getRGBdefault(), array, 0, dimension.width));
            }
        }
        catch (Exception ex2) {}
        return image;
    }
    
    private final Image b(final boolean b, final Dimension dimension, final int[] array) {
        Image image = null;
        try {
            if (dimension.width > 0 && dimension.height > 0) {
                Color color;
                if (ji.util.e.g != null) {
                    color = ji.util.e.g;
                }
                else {
                    color = SystemColor.controlShadow;
                }
                if (ji.util.e.h != null) {
                    final Color h = ji.util.e.h;
                }
                else {
                    final SystemColor controlLtHighlight = SystemColor.controlLtHighlight;
                }
                final int length = array.length;
                final Color background = this.getBackground();
                final int rgb = background.getRGB();
                if (b && ji.util.i.c(7) && ji.util.e.t() && ji.util.d.ar()) {
                    for (int i = 0; i < length; ++i) {
                        array[i] = rgb;
                    }
                    color = background.darker().darker().darker();
                }
                else {
                    for (int j = 0; j < length; ++j) {
                        array[j] = rgb;
                    }
                }
                final int rgb2 = color.getRGB();
                final int n = 3;
                final int n2 = 8;
                int n3 = (dimension.height - n2 * 2 - n) / 2;
                int n4 = n3 - n2 - n;
                final int n5 = 2;
                int n6 = n3 + n2;
                int n7 = n4 + n2;
                final int n8 = n5 + n2;
                try {
                    for (int k = n5; k < n8; ++k) {
                        for (int l = n3; l <= n6; ++l) {
                            final int n9 = l * dimension.width + (n8 - k - 2);
                            if (n9 < length && n9 > 0) {
                                array[n9] = rgb2;
                            }
                        }
                        for (int n10 = n4; n10 <= n7; ++n10) {
                            final int n11 = n10 * dimension.width + k;
                            if (n11 < length && n11 > 0) {
                                array[n11] = rgb2;
                            }
                        }
                        ++n3;
                        ++n4;
                        --n6;
                        --n7;
                    }
                }
                catch (Exception ex) {}
                final int m = ji.util.d.j(background.darker().darker().darker());
                int n12;
                if (ji.util.e.h != null) {
                    n12 = ji.util.d.j(ji.util.e.h);
                }
                else {
                    n12 = ji.util.d.j(ji.util.e.c(ji.util.e.c(background.brighter())));
                }
                final int n13 = 4;
                this.a(new Rectangle(1, 1, dimension.width - 2, n4 - n2 - 5), dimension, n13, array, m, n12);
                this.a(new Rectangle(1, n3 + n2 - 2, dimension.width - 2, dimension.height - n3 - 5), dimension, n13, array, m, n12);
                Color color2;
                if (ji.util.e.g != null) {
                    color2 = ji.util.e.g;
                }
                else {
                    color2 = SystemColor.controlShadow;
                }
                final int rgb3 = color2.getRGB();
                final int n14 = dimension.width - 1;
                for (int n15 = 0; n15 < dimension.height; ++n15) {
                    array[n15 * dimension.width] = rgb3;
                }
                for (int n16 = 0; n16 < dimension.height; ++n16) {
                    array[n16 * dimension.width + n14] = rgb3;
                }
                image = this.createImage(new MemoryImageSource(dimension.width, dimension.height, ColorModel.getRGBdefault(), array, 0, dimension.width));
            }
        }
        catch (Exception ex2) {}
        return image;
    }
    
    private final void a(final Rectangle rectangle, final Dimension dimension, final int n, final int[] array, final int n2, final int n3) {
        try {
            int n4 = 0;
            int n5 = n3;
            for (int i = rectangle.y; i < rectangle.y + rectangle.height; ++i) {
                final int n6 = i * dimension.width;
                for (int j = n4 + rectangle.x; j < rectangle.x + rectangle.width; j += n) {
                    array[n6 + j] = n5;
                }
                if (++n4 == n) {
                    n4 = 0;
                }
                if (n5 == n2) {
                    n5 = n3;
                }
                else {
                    n5 = n2;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final boolean h() {
        try {
            final Dimension size = this.getSize();
            return size.width > size.height;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    public final void a(final ag ag) {
        if (this.y != null && this.y.a(ag)) {
            this.y.b(ag);
        }
    }
    
    public final void b(final ag ag) {
        if (this.y == null) {
            this.y = new c("koSplitbar1", 2);
        }
        if (!this.y.a(ag)) {
            this.y.c(ag);
        }
    }
    
    public final void a(final d7 d7) {
        try {
            if (this.y != null) {
                final c y = this.y;
                for (int b = y.b(), i = 0; i < b; ++i) {
                    if (!d7.l()) {
                        ((ag)y.b(i)).a(d7);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void setBounds(final Rectangle bounds) {
        super.setBounds(bounds);
        if (this.z.width != bounds.width || this.z.height != bounds.height) {
            this.z.width = bounds.width;
            this.z.height = bounds.height;
            this.i();
            this.repaint();
        }
    }
    
    public final void setBounds(final int n, final int n2, final int width, final int height) {
        super.setBounds(n, n2, width, height);
        if (this.z.width != width || this.z.height != height) {
            this.z.width = width;
            this.z.height = height;
            this.i();
            this.repaint();
        }
    }
    
    private final void i() {
        try {
            try {
                if (this.m != null) {
                    this.m.flush();
                    this.m = null;
                }
            }
            catch (Exception ex) {}
            try {
                if (this.n != null) {
                    this.n.flush();
                    this.n = null;
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.o != null) {
                    this.o.flush();
                    this.o = null;
                }
            }
            catch (Exception ex3) {}
            try {
                if (this.p != null) {
                    this.p.flush();
                    this.p = null;
                }
            }
            catch (Exception ex4) {}
            this.s = null;
            this.t = null;
            this.q = null;
            this.r = null;
        }
        catch (Exception ex5) {}
    }
    
    public final void releaseResources() {
        try {
            if (this.x != null) {
                ji.util.d.b((MouseListener)this.x);
                if (this.k) {
                    ji.util.d.b((MouseMotionListener)this.x);
                    this.k = false;
                }
                this.x.a();
                this.x = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.y != null) {
                this.y.c();
            }
        }
        catch (Exception ex2) {}
        this.s = null;
        this.t = null;
        this.i();
        this.w = null;
        super.releaseResources();
    }
    
    static {
        a = new Cursor(0);
        b = new Cursor(8);
        c = new Cursor(8);
        d = new Cursor(11);
        e = new Cursor(11);
    }
    
    class uv implements MouseListener, MouseMotionListener
    {
        Component a;
        
        public uv(final Component a) {
            this.a = a;
        }
        
        public final void a() {
            this.a = null;
        }
        
        private final Point a(final MouseEvent mouseEvent) {
            Point point = null;
            try {
                final Point locationOnScreen = ((Component)mouseEvent.getSource()).getLocationOnScreen();
                final Point point2 = mouseEvent.getPoint();
                point = new Point(locationOnScreen.x + point2.x, locationOnScreen.y + point2.y);
            }
            catch (Exception ex) {}
            return point;
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            try {
                if (dq.this.f) {
                    dq.this.i = true;
                    if (!dq.this.h && dq.this.u != null) {
                        final Point a = this.a(mouseEvent);
                        if (dq.this.h()) {
                            dq.this.a(new d7(this.a, 46, a));
                        }
                        else {
                            dq.this.a(new d7(this.a, 45, a));
                        }
                    }
                    dq.this.u = this.a(mouseEvent);
                }
            }
            catch (Exception ex) {}
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            try {
                if (mouseEvent.getSource().equals(this.a)) {
                    dq.this.u = this.a(mouseEvent);
                    dq.this.f = true;
                    dq.this.a(dq.this.getGraphics());
                    if (!dq.this.k) {
                        ji.util.d.a((MouseMotionListener)dq.this.x);
                        dq.this.k = true;
                    }
                    this.b();
                }
            }
            catch (Exception ex) {}
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            try {
                if (dq.this.f) {
                    dq.this.u = null;
                    dq.this.i = false;
                    dq.this.f = false;
                    dq.this.a(dq.this.getGraphics());
                    if (dq.this.k) {
                        ji.util.d.b((MouseMotionListener)dq.this.x);
                        dq.this.k = false;
                    }
                    this.c();
                }
            }
            catch (Exception ex) {}
        }
        
        private final void b() {
            try {
                dq.this.j = true;
                dq.this.a(new d7(this.a, 43, 0));
            }
            catch (Exception ex) {}
        }
        
        private final void c() {
            try {
                if (dq.this.j) {
                    dq.this.j = false;
                    dq.this.a(new d7(this.a, 44, 0));
                }
            }
            catch (Exception ex) {}
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            try {
                if (mouseEvent.getSource().equals(this.a)) {
                    dq.this.g = true;
                    dq.this.a(dq.this.getGraphics());
                }
            }
            catch (Exception ex) {}
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            try {
                if (mouseEvent.getSource().equals(this.a)) {
                    dq.this.g = false;
                    dq.this.a(dq.this.getGraphics());
                }
            }
            catch (Exception ex) {}
        }
    }
}
