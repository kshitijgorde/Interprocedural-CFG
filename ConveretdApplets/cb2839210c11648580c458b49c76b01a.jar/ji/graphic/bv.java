// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.AdjustmentEvent;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseMotionListener;
import java.awt.Label;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Point;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import ji.util.e;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import ji.util.d;
import java.awt.Dialog;
import java.awt.Color;
import ji.v1event.af;
import java.awt.Component;
import java.awt.Frame;
import ji.v1base.jiPanel;
import ji.v1base.bl;

public class bv extends bl
{
    private s4 a;
    private s4 b;
    private s3 c;
    private jiPanel d;
    private bj e;
    private boolean f;
    private String g;
    private boolean h;
    private boolean i;
    private String j;
    private String k;
    private String l;
    
    public bv(final Frame frame, final bj bj, final String s, final Component component, final af af, final Color color, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s2) {
        super(frame, s, false);
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = true;
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = null;
        this.k = null;
        this.l = null;
        this.a(bj, s, component, af, color, b, b2, b3, b4, s2);
    }
    
    public bv(final Dialog dialog, final bj bj, final String s, final Component component, final af af, final Color color, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s2) {
        super(dialog, s, false);
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = true;
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = null;
        this.k = null;
        this.l = null;
        this.a(bj, s, component, af, color, b, b2, b3, b4, s2);
    }
    
    public void a(final bj e, final String s, final Component component, final af af, final Color color, final boolean b, final boolean b2, final boolean h, final boolean i, final String j) {
        try {
            this.j = j;
            this.i = i;
            this.h = h;
            if (color != null) {
                this.g = ji.util.d.l(color);
            }
            else {
                this.g = null;
            }
            this.d = new jiPanel(j, new FlowLayout(2));
            this.e = e;
            this.k = ji.util.d.b(232, j);
            this.l = ji.util.d.bc(this.k.toLowerCase());
            (this.a = new s4(j, this.k)).addKeyListener(new abw());
            this.addKeyListener(new abw());
            this.d.add(this.a);
            (this.b = new s4(j, ji.util.d.b(235, j))).addKeyListener(new abw());
            this.d.add(this.b);
            this.d().setLayout(new BorderLayout());
            this.d().add("Center", this.c = new s3(j, ji.util.d.b1(this.g), b, b2));
            this.d().add("South", this.d);
            this.pack();
            this.addWindowListener(new aby());
            this.f();
            this.setResizable(false);
            this.setBackground(e.aq());
        }
        catch (Exception ex) {}
    }
    
    public boolean a() {
        return this.f;
    }
    
    public void dispose() {
        try {
            this.hide();
            this.b();
        }
        catch (Exception ex) {}
    }
    
    public void requestFocus() {
        try {
            if (ji.util.e.at()) {
                ji.util.e.b(this.a);
            }
        }
        catch (Exception ex) {}
    }
    
    public void b() {
        try {
            if (this.a != null) {
                this.a.releaseResources();
                this.a = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.b != null) {
                this.b.releaseResources();
                this.b = null;
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.c != null) {
                this.c.releaseResources();
                this.c = null;
            }
            this.d = null;
        }
        catch (Exception ex3) {}
    }
    
    private void f() {
        try {
            this.pack();
            final Dimension size = this.getSize();
            this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - size.width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - size.height / 2, size.width, size.height);
        }
        catch (Exception ex) {}
    }
    
    public Dimension getSize() {
        if (!this.e()) {
            return super.getSize();
        }
        if (this.i) {
            return new Dimension(325, 250);
        }
        return new Dimension(325, 250);
    }
    
    public void show() {
        try {
            ji.util.e.a(this);
            super.show();
            if (ji.util.e.at()) {
                ji.util.e.b(this.a);
            }
        }
        catch (Exception ex) {}
    }
    
    public void c() {
        this.f = true;
        this.g();
    }
    
    private void g() {
        try {
            if (this.e != null) {
                try {
                    ji.util.d.bz(this.c.a());
                    this.e.a(this, this.c.b());
                }
                catch (Exception ex) {}
            }
            try {
                this.dispose();
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
    
    public void setBackground(final Color background) {
        try {
            if (background != null) {
                super.setBackground(background);
                if (this.d != null) {
                    this.d.setBackground(background);
                }
                if (this.a != null) {
                    this.a.setBackground(background);
                }
                if (this.b != null) {
                    this.b.setBackground(background);
                }
                if (this.c != null) {
                    this.c.setBackground(background);
                }
                this.repaint();
            }
        }
        catch (Exception ex) {}
    }
    
    class s4 extends jiPanel
    {
        String a;
        Dimension b;
        adw c;
        Color d;
        
        public s4(final String s, final String a) {
            super(s);
            this.a = null;
            this.b = new Dimension(80, 25);
            this.c = new adw();
            this.d = null;
            this.a = a;
            this.setLayout(new FlowLayout());
            this.setBorderStyle(2);
            this.addMouseListener(this.c);
            this.setSize(this.b);
            this.setInsets(new Insets(0, 0, 0, 0));
        }
        
        public void paint(final Graphics graphics) {
            try {
                super.paint(graphics);
                this.a(graphics);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
        
        public void paintComponent(final Graphics graphics) {
            try {
                super.paintComponent(graphics);
                this.a(graphics);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
        
        public void a(final Graphics graphics) {
            try {
                final char[] charArray = this.a.toCharArray();
                final int charsWidth = graphics.getFontMetrics().charsWidth(charArray, 0, charArray.length);
                final int height;
                final int n = height = graphics.getFontMetrics().getHeight();
                int n2;
                if (ji.util.d.em()) {
                    n2 = height + n / 5;
                }
                else if (ji.util.d.av(bv.this.j)) {
                    n2 = height + n / 10;
                }
                else {
                    n2 = height - n / 10;
                }
                graphics.setColor(Color.black);
                graphics.drawString(this.a, (this.b.width - charsWidth) / 2, n2);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
        
        public Dimension getSize() {
            return this.b;
        }
        
        public Dimension getMinimumSize() {
            return this.getSize();
        }
        
        public Dimension getPreferredSize() {
            return this.getSize();
        }
        
        public void releaseResources() {
            try {
                this.removeMouseListener(this.c);
            }
            catch (Exception ex) {}
        }
        
        private final void a() {
            try {
                this.d = this.getBackground();
                this.setBackground(this.d.darker());
                this.repaint();
            }
            catch (Exception ex) {}
        }
        
        private final void b() {
            try {
                if (this.d != null) {
                    this.setBackground(this.d);
                }
                this.repaint();
            }
            catch (Exception ex) {}
        }
        
        class adw extends MouseAdapter
        {
            public void mousePressed(final MouseEvent mouseEvent) {
                s4.this.a();
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                s4.this.a();
                if (ji.util.d.bc(s4.this.a).toLowerCase().indexOf(bv.this.l) >= 0) {
                    bv.this.f = false;
                }
                else {
                    bv.this.f = true;
                    bv.this.g = null;
                }
                bv.this.g();
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                s4.this.b();
            }
        }
    }
    
    class abx extends jiPanel
    {
        public abx(final bv bv, final String s) {
            super(s);
        }
        
        public void paint(final Graphics graphics) {
            try {
                super.paint(graphics);
                this.a(graphics);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
        
        public void paintComponent(final Graphics graphics) {
            try {
                super.paintComponent(graphics);
                this.a(graphics);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
        
        public void a(final Graphics graphics) {
            try {
                final Dimension size = this.getSize();
                graphics.setColor(Color.black);
                final int n = 5;
                graphics.drawLine(n, n, size.width - n, size.height - n);
                graphics.drawLine(size.width - n, n, n, size.height - n);
            }
            catch (Exception ex) {}
        }
    }
    
    class s5 extends jiPanel
    {
        int[] a;
        Image b;
        int c;
        int d;
        
        public s5(final bv bv, final String s) {
            super(s);
            this.a = null;
            this.b = null;
            this.c = 0;
            this.d = 0;
        }
        
        public final void releaseResources() {
            try {
                this.a = null;
                this.b = null;
            }
            catch (Exception ex) {}
        }
        
        public void paint(final Graphics graphics) {
            try {
                super.paint(graphics);
                this.a(graphics);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
        
        public void paintComponent(final Graphics graphics) {
            try {
                super.paintComponent(graphics);
                this.a(graphics);
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
        
        public final Color a(final Point point) {
            try {
                this.getSize();
                return new Color(this.a[(point.y - 2) * this.c + (point.x - 2)]);
            }
            catch (Exception ex) {
                return new Color(0, 0, 0);
            }
        }
        
        private void a(final Graphics graphics) {
            try {
                if (this.b == null) {
                    final Dimension size;
                    final Dimension dimension = size = this.getSize();
                    size.width -= 4;
                    final Dimension dimension2 = dimension;
                    dimension2.height -= 4;
                    this.a = new int[dimension.width * dimension.height];
                    final int n = dimension.width / 6;
                    final int n2 = dimension.height / 2;
                    for (int i = 0; i < dimension.height; ++i) {
                        int n3 = 0;
                        int n4 = 0;
                        final int n5 = 255 * i / n2;
                        int n6;
                        if (i <= n2) {
                            n6 = 0;
                        }
                        else {
                            n6 = 255 * (i - n2) / n2;
                        }
                        int max = 255;
                        int max2 = n6;
                        int max3 = n6;
                        final int max4 = Math.max(Math.min(n5, 255), 0);
                        for (int j = 0; j < dimension.width; ++j) {
                            if (i <= n2) {
                                final int n7 = max4 * n3 / n;
                                switch (n4) {
                                    case 0: {
                                        max2 = n6 + n7;
                                        break;
                                    }
                                    case 1: {
                                        max = n6 + max4 - n7;
                                        break;
                                    }
                                    case 2: {
                                        max3 = n6 + n7;
                                        break;
                                    }
                                    case 3: {
                                        max2 = n6 + max4 - n7;
                                        break;
                                    }
                                    case 4: {
                                        max = n6 + n7;
                                        break;
                                    }
                                    case 5: {
                                        max3 = n6 + max4 - n7;
                                        break;
                                    }
                                }
                            }
                            else {
                                final int n8 = (255 - n6) * n3 / n;
                                switch (n4) {
                                    case 0: {
                                        max2 = n6 + n8;
                                        break;
                                    }
                                    case 1: {
                                        max = 255 - n8;
                                        break;
                                    }
                                    case 2: {
                                        max3 = n6 + n8;
                                        break;
                                    }
                                    case 3: {
                                        max2 = 255 - n8;
                                        break;
                                    }
                                    case 4: {
                                        max = n6 + n8;
                                        break;
                                    }
                                    case 5: {
                                        max3 = 255 - n8;
                                        break;
                                    }
                                }
                            }
                            max = Math.max(Math.min(max, max4), 0);
                            max2 = Math.max(Math.min(max2, max4), 0);
                            max3 = Math.max(Math.min(max3, max4), 0);
                            this.a[i * dimension.width + j] = (0xFF000000 | max << 16 | max2 << 8 | max3);
                            if (++n3 >= n) {
                                ++n4;
                                n3 = 0;
                            }
                        }
                    }
                    this.c = dimension.width;
                    this.d = dimension.height;
                    this.b = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(dimension.width, dimension.height, ColorModel.getRGBdefault(), this.a, 0, dimension.width));
                }
                graphics.drawImage(this.b, 2, 2, null);
            }
            catch (Exception ex) {}
        }
    }
    
    class s3 extends jiPanel
    {
        Label[] a;
        Dimension b;
        Label c;
        Label d;
        s5 e;
        adx f;
        int g;
        int h;
        jiPanel[] i;
        abx j;
        jiPanel k;
        by l;
        ady m;
        boolean n;
        boolean o;
        jiPanel p;
        int q;
        int r;
        int s;
        int t;
        int u;
        
        public boolean a() {
            return this.o;
        }
        
        public void setBackground(final Color color) {
            try {
                if (color != null) {
                    super.setBackground(color);
                    if (this.c != null) {
                        this.c.setBackground(color);
                    }
                    if (this.d != null) {
                        this.d.setBackground(color);
                    }
                    if (this.e != null) {
                        this.e.setBackground(color);
                    }
                    if (this.l != null) {
                        this.l.setBackground(color);
                    }
                    if (this.j != null) {
                        this.j.setBackground(color);
                    }
                    this.repaint();
                }
            }
            catch (Exception ex) {}
            try {
                super.setBackground(color);
                for (int i = 0; i < this.a.length; ++i) {
                    if (this.a[i] != null) {
                        this.a[i].setBackground(color);
                        this.a[i].repaint();
                    }
                }
                this.repaint();
            }
            catch (Exception ex2) {}
        }
        
        private final int d() {
            if (this.s > 0) {
                return Math.min(Math.max(this.s * this.q / this.r, 0), 255);
            }
            return Math.min(Math.max(255 * (this.q - 50) / this.r, 0), 255);
        }
        
        private final int e() {
            if (this.t > 0) {
                return Math.min(Math.max(this.t * this.q / this.r, 0), 255);
            }
            return Math.min(Math.max(255 * (this.q - 50) / this.r, 0), 255);
        }
        
        private final int f() {
            if (this.u > 0) {
                return Math.min(Math.max(this.u * this.q / this.r, 0), 255);
            }
            return Math.min(Math.max(255 * (this.q - 50) / this.r, 0), 255);
        }
        
        public final String b() {
            if (this.o && !bv.this.h) {
                return "";
            }
            return String.valueOf(String.valueOf(new StringBuffer("").append(this.d()).append(", ").append(this.e()).append(", ").append(this.f())));
        }
        
        public void a(final int s, final int t, final int u, final boolean o) {
            try {
                this.s = s;
                this.t = t;
                this.u = u;
                this.o = o;
                if (this.c != null) {
                    if (o) {
                        this.c.setText(String.valueOf(String.valueOf(new StringBuffer("  (").append(ji.util.d.b(491, bv.this.j)).append(")"))));
                        if (bv.this.h) {
                            this.k.setBackground(new Color(this.d(), this.e(), this.f()));
                        }
                        else {
                            this.k.setBackground(ji.util.e.ao());
                        }
                    }
                    else {
                        this.c.setText(String.valueOf(String.valueOf(new StringBuffer("  (").append(this.d()).append(", ").append(this.e()).append(", ").append(this.f()).append(")"))));
                        this.k.setBackground(new Color(this.d(), this.e(), this.f()));
                    }
                    this.k.repaint();
                }
                this.l.setValue(this.q);
            }
            catch (Exception ex) {}
        }
        
        public void c() {
            try {
                this.p.setBackground(ji.util.e.ao());
                this.p.repaint();
            }
            catch (Exception ex) {}
        }
        
        public void b(final int n, final int n2, final int n3, final boolean b) {
            try {
                if (this.d != null) {
                    if (b) {
                        this.d.setText(String.valueOf(String.valueOf(new StringBuffer("(").append(ji.util.d.b(491, bv.this.j)).append(")"))));
                        if (bv.this.h) {
                            this.p.setBackground(new Color(n, n2, n3));
                        }
                        else {
                            this.p.setBackground(ji.util.e.ao());
                        }
                    }
                    else {
                        this.d.setText(String.valueOf(String.valueOf(new StringBuffer("(").append(n).append(", ").append(n2).append(", ").append(n3).append(")"))));
                        this.p.setBackground(new Color(n, n2, n3));
                    }
                    this.p.repaint();
                }
            }
            catch (Exception ex) {}
        }
        
        public s3(final String s, final Color background, final boolean n, final boolean o) {
            super(s);
            this.a = null;
            this.c = new Label();
            this.d = new Label();
            this.e = null;
            this.f = new adx();
            this.g = 10;
            this.h = 120;
            this.i = new jiPanel[this.g * 6];
            this.j = null;
            this.k = null;
            this.l = new by(0, 1, bv.this.j);
            this.m = new ady();
            this.n = false;
            this.o = false;
            this.p = null;
            this.q = 50;
            this.r = 50;
            this.s = 0;
            this.t = 0;
            this.u = 0;
            try {
                this.n = n;
                this.o = o;
                this.p = new jiPanel(s);
                this.k = new jiPanel(s);
                bv.this.getClass();
                this.j = new abx(s);
                bv.this.getClass();
                this.e = new s5(s);
                if (bv.this.i) {
                    this.h = 0;
                }
                else {
                    this.e.addMouseListener(this.f);
                    this.e.addMouseMotionListener(this.f);
                    this.l.addAdjustmentListener(this.m);
                }
                this.c.setAlignment(0);
                this.d.setAlignment(0);
                this.c.setBackground(ji.util.e.ao());
                this.d.setBackground(ji.util.e.ao());
                this.e.setBorderStyle(0);
                this.p.setBorderStyle(2);
                this.c();
                this.k.setBorderStyle(2);
                this.j.setBorderStyle(2);
                this.setBorderStyle(0);
                this.setLayout(null);
                int n2 = 0;
                int n3 = 0;
                int n4 = 0;
                int n5 = 0;
                final int n6 = this.i.length / this.g;
                final int[] array = new int[this.g];
                final int[] array2 = new int[array.length];
                array2[0] = -1;
                array2[1] = -4144960;
                array2[2] = -10000537;
                array2[3] = -65536;
                array2[4] = -26368;
                array2[5] = -256;
                array2[6] = -16711936;
                array2[7] = -16711681;
                array2[8] = -16776961;
                array2[9] = -65281;
                for (int i = 0; i < array.length; ++i) {
                    array[i] = array2[i];
                }
                int n7 = 0;
                int n8 = 0;
                for (int j = 0; j < this.i.length; ++j) {
                    (this.i[j] = new jiPanel(s)).addMouseListener(this.f);
                    this.i[j].addMouseMotionListener(this.f);
                    this.i[j].setBorderStyle(2);
                    this.i[j].setBounds(n3 + this.h + 2, n4, 20, 20);
                    if (bv.this.i) {
                        this.i[j].setBackground(ji.util.d.n(new Color(array2[n7])));
                    }
                    else {
                        this.i[j].setBackground(new Color(array2[n7]));
                    }
                    this.add(this.i[j]);
                    ++n2;
                    if (++n7 >= this.g) {
                        ++n8;
                        n7 = 0;
                        for (int k = 0; k < array2.length; ++k) {
                            if (array[k] != 0) {
                                int n9 = array2[k] >> 16 & 0xFF;
                                int n10 = array2[k] >> 8 & 0xFF;
                                int n11 = array2[k] & 0xFF;
                                if (n9 != 0) {
                                    n9 -= 21;
                                }
                                if (n10 != 0) {
                                    n10 -= 21;
                                }
                                if (n11 != 0) {
                                    n11 -= 21;
                                }
                                array2[k] = (0xFF000000 | Math.max(n9, 0) << 16 | Math.max(n10, 0) << 8 | Math.max(n11, 0));
                            }
                        }
                    }
                    if (n2 >= this.g) {
                        n3 = 0;
                        n4 += 22;
                        n2 = 0;
                        ++n5;
                    }
                    else {
                        n3 += 22;
                    }
                }
                ++n5;
                this.l.a(this.q, 1, 0, 100);
                this.e.setBounds(0, 0, this.h, n5 * 22 - 2);
                this.p.setBounds(0, n5 * 22, this.h, 20);
                this.d.setBounds(0, n5 * 22 + 22, this.h, 20);
                int n12 = 0;
                if (n) {
                    n12 = 22;
                }
                final int n13 = 110 - n12;
                this.c.setBounds(this.h + n13 + 2, n5 * 22, this.g * 22 - n13 - 2, 20);
                this.l.setBounds(this.h + 2 + n12, n5 * 22 - 22, this.g * 22 - 2 - n12, 20);
                if (n) {
                    this.j.setBounds(this.h + 2, n5 * 22 - 22, 20, 20);
                    this.j.addMouseListener(this.f);
                    this.j.addMouseMotionListener(this.f);
                }
                this.k.setBounds(this.h + 2, n5 * 22, n13, 20);
                this.k.setBackground(background);
                this.k.repaint();
                if (!bv.this.i) {
                    this.add(this.e);
                }
                if (bv.this.i) {
                    final Rectangle bounds = this.l.getBounds();
                    this.p.setBounds(bounds.x, bounds.y, bounds.width / 2, bounds.height);
                    this.k.setBounds(bounds.x + bounds.width / 2, bounds.y, bounds.width / 2, bounds.height);
                    this.c.setLocation(bounds.x + bounds.width / 2, this.c.getLocation().y);
                    this.d.setBounds(bounds.x, this.c.getLocation().y, bounds.width / 2, 20);
                }
                this.add(this.p);
                this.add(this.c);
                this.add(this.d);
                if (n) {
                    this.add(this.j);
                }
                if (!bv.this.i) {
                    this.add(this.l);
                }
                this.add(this.k);
                this.a(background.getRed(), background.getGreen(), background.getBlue(), o);
                this.b = new Dimension(this.h + this.g * 22, n5 * 22 + 22 + 22);
                if (bv.this.i) {
                    final Dimension b = this.b;
                    b.height -= 22;
                }
            }
            catch (Exception ex) {}
        }
        
        public Dimension getSize() {
            return this.b;
        }
        
        public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
        }
        
        private final void a(final int n) {
            this.q = this.l.getValue();
            this.a(this.s, this.t, this.u, this.o);
        }
        
        public final void releaseResources() {
            try {
                if (this.p != null) {
                    this.p.releaseResources();
                    this.p = null;
                }
                if (this.k != null) {
                    this.k.releaseResources();
                    this.k = null;
                }
                if (bv.this.d != null) {
                    bv.this.d.releaseResources();
                    bv.this.d = null;
                }
                if (this.e != null) {
                    this.e.releaseResources();
                    this.e = null;
                }
                if (this.j != null) {
                    this.remove(this.j);
                    this.j.removeMouseListener(this.f);
                    this.j.removeMouseMotionListener(this.f);
                    this.j.releaseResources();
                    this.j = null;
                }
            }
            catch (Exception ex) {}
            try {
                if (this.p != null) {
                    this.remove(this.e);
                    this.e.releaseResources();
                    this.p = null;
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.e != null) {
                    this.remove(this.e);
                    this.e.removeMouseListener(this.f);
                    this.e.removeMouseMotionListener(this.f);
                    this.e.releaseResources();
                    this.e = null;
                }
            }
            catch (Exception ex3) {}
            try {
                if (this.l != null) {
                    this.remove(this.l);
                    this.l.removeAdjustmentListener(this.m);
                    this.l.releaseResources();
                    this.l = null;
                }
            }
            catch (Exception ex4) {}
            try {
                if (this.i != null) {
                    for (int i = 0; i < this.i.length; ++i) {
                        this.remove(this.i[i]);
                        this.i[i].removeMouseListener(this.f);
                        this.i[i].removeMouseMotionListener(this.f);
                        this.i[i].releaseResources();
                        this.i[i] = null;
                    }
                }
                this.i = null;
            }
            catch (Exception ex5) {}
            try {
                ji.util.d.a(this, bv.this.j);
            }
            catch (Exception ex6) {}
        }
        
        class adx extends MouseAdapter implements MouseMotionListener
        {
            public void mouseEntered(final MouseEvent mouseEvent) {
                this.mouseMoved(mouseEvent);
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent.getSource() instanceof abx) {
                    Color a;
                    if (bv.this.h) {
                        a = new Color(s3.this.d(), s3.this.e(), s3.this.f());
                    }
                    else {
                        a = s3.this.e.a(mouseEvent.getPoint());
                    }
                    s3.this.q = 50;
                    s3.this.b(a.getRed(), a.getGreen(), a.getBlue(), true);
                    s3.this.a(a.getRed(), a.getGreen(), a.getBlue(), true);
                }
                else if (mouseEvent.getSource() instanceof s5) {
                    final Color a2 = s3.this.e.a(mouseEvent.getPoint());
                    s3.this.q = 50;
                    s3.this.b(a2.getRed(), a2.getGreen(), a2.getBlue(), false);
                    s3.this.a(a2.getRed(), a2.getGreen(), a2.getBlue(), false);
                }
                else if (mouseEvent.getSource() instanceof Component) {
                    final Color background = ((Component)mouseEvent.getSource()).getBackground();
                    s3.this.q = 50;
                    s3.this.b(background.getRed(), background.getGreen(), background.getBlue(), false);
                    s3.this.a(background.getRed(), background.getGreen(), background.getBlue(), false);
                }
                if (mouseEvent.getClickCount() > 1) {
                    bv.this.f = false;
                    bv.this.g();
                    ji.util.d.ew();
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (mouseEvent.getSource() instanceof jiPanel) {
                    if (mouseEvent.getSource().equals(s3.this.e)) {
                        ((jiPanel)mouseEvent.getSource()).setBorderStyle(0);
                    }
                    else {
                        ((jiPanel)mouseEvent.getSource()).setBorderStyle(2);
                    }
                }
                if (mouseEvent.getSource() instanceof abx) {
                    s3.this.q = 50;
                    if (bv.this.h) {
                        final Color color = new Color(s3.this.d(), s3.this.e(), s3.this.f());
                        s3.this.a(color.getRed(), color.getGreen(), color.getBlue(), true);
                    }
                    else {
                        final Color a = s3.this.e.a(mouseEvent.getPoint());
                        s3.this.a(a.getRed(), a.getGreen(), a.getBlue(), true);
                    }
                }
                else if (mouseEvent.getSource() instanceof s5) {
                    s3.this.q = 50;
                    final Color a2 = s3.this.e.a(mouseEvent.getPoint());
                    s3.this.a(a2.getRed(), a2.getGreen(), a2.getBlue(), false);
                }
                else if (mouseEvent.getSource() instanceof Component) {
                    s3.this.q = 50;
                    final Color background = ((Component)mouseEvent.getSource()).getBackground();
                    s3.this.a(background.getRed(), background.getGreen(), background.getBlue(), false);
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                s3.this.c();
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                if (mouseEvent.getSource() instanceof jiPanel) {
                    if (mouseEvent.getSource().equals(s3.this.e)) {
                        ((jiPanel)mouseEvent.getSource()).setBorderStyle(6);
                    }
                    else {
                        ((jiPanel)mouseEvent.getSource()).setBorderStyle(1);
                    }
                }
                this.mouseMoved(mouseEvent);
            }
            
            public void mouseDragged(final MouseEvent mouseEvent) {
                this.mouseMoved(mouseEvent);
            }
            
            public void mouseMoved(final MouseEvent mouseEvent) {
                if (mouseEvent.getSource() instanceof abx) {
                    final Color a = s3.this.e.a(mouseEvent.getPoint());
                    s3.this.b(a.getRed(), a.getGreen(), a.getBlue(), true);
                }
                else if (mouseEvent.getSource() instanceof s5) {
                    final Color a2 = s3.this.e.a(mouseEvent.getPoint());
                    s3.this.b(a2.getRed(), a2.getGreen(), a2.getBlue(), false);
                }
                else if (mouseEvent.getSource() instanceof Component) {
                    final Color background = ((Component)mouseEvent.getSource()).getBackground();
                    s3.this.b(background.getRed(), background.getGreen(), background.getBlue(), false);
                }
            }
        }
        
        class ady implements AdjustmentListener
        {
            public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                s3.this.a(adjustmentEvent.getValue());
            }
        }
    }
    
    class aby extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            try {
                bv.this.g();
                ji.util.d.ew();
            }
            catch (Exception ex) {}
        }
    }
    
    class abw extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            try {
                if (keyEvent.getKeyCode() == 10) {
                    bv.this.f = false;
                }
                else {
                    bv.this.f = true;
                    bv.this.g = null;
                }
                bv.this.g();
            }
            catch (Exception ex) {}
        }
    }
}
