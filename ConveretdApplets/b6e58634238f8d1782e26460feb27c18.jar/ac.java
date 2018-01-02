import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class ac extends Container implements ComponentListener, MouseListener, MouseMotionListener
{
    public Image a;
    public d b;
    public Dimension c;
    public z d;
    public q e;
    public int f;
    public Color[] g;
    public String[] h;
    public String[] i;
    public Double j;
    public Double k;
    public String l;
    public boolean m;
    public String n;
    public int o;
    public int p;
    public int q;
    public boolean r;
    public an s;
    public int t;
    public Date u;
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.b == null) {
            super.paint(graphics);
            return;
        }
        final Dimension size = this.getSize();
        if ((this.a == null || this.a.getWidth(this) < size.width || this.a.getHeight(this) < size.height) && size.width > 0 && size.height > 0) {
            if (this.a != null) {
                this.a.flush();
                this.a = null;
                this.b.d();
                f.b();
            }
            this.a = this.createImage(size.width, size.height);
        }
        if (size.width > 0 && size.height > 0) {
            final Graphics graphics2 = this.a.getGraphics();
            final Rectangle clipBounds = graphics.getClipBounds();
            graphics2.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            this.a(graphics2);
            graphics2.dispose();
        }
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public ac(final d b, final q e) {
        this.m = true;
        this.t = 0;
        this.u = null;
        this.b = b;
        this.setBackground(this.b.i().c);
        this.b.d();
        this.setForeground(f.a(this.getBackground()));
        this.setFont(this.b.i().o);
        this.e = e;
        this.f = 1;
        this.g = new Color[] { this.getBackground() };
        final String[] array = { "" };
        this.i = array;
        this.h = array;
        this.m = this.b.i().g;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.d = new z(this.b, "T", null, e.f().c().a);
        this.l = "";
        this.c = new Dimension(0, 6 + (this.e.f().c().w ? 18 : 0) + 9 + 3 + (this.m ? 16 : 0));
        this.setLayout(null);
        this.add(this.s = new Container() {
            public ao a;
            public ap b;
            public SimpleDateFormat c = new SimpleDateFormat("h:mm:ss a, z");
            
            {
                final Dimension size = new Dimension();
                final boolean b2 = s != null && s.compareTo("") != 0;
                final Dimension dimension = size;
                dimension.width += (b ? 12 : 0);
                final Dimension dimension2 = size;
                dimension2.width += (b2 ? 150 : 0);
                final Dimension dimension3 = size;
                dimension3.height += ((b || b2) ? 12 : 0);
                this.setSize(size);
                this.setLayout(new BorderLayout());
                if (b2) {
                    this.add(this.a = new Component() {
                        public String a;
                        
                        {
                            this.a("");
                        }
                        
                        public void a(String a, final int n, final Date date) {
                            a = Container.this.a(a, n, date);
                            if (a != null) {
                                this.a(a);
                            }
                        }
                        
                        public Dimension getPreferredSize() {
                            final Dimension dimension = new Dimension();
                            dimension.width = 0;
                            final Dimension dimension2 = dimension;
                            dimension2.width += this.getFontMetrics(this.getFont()).stringWidth(this.a());
                            final Dimension dimension3 = dimension;
                            dimension3.height += this.getFontMetrics(this.getFont()).getMaxAscent();
                            return dimension;
                        }
                        
                        public void paint(final Graphics graphics) {
                            final Dimension size = this.getSize();
                            graphics.setColor(this.getBackground());
                            graphics.fillRect(0, 0, size.width, size.height);
                            graphics.setColor(this.getForeground());
                            graphics.setFont(this.getFont());
                            final FontMetrics fontMetrics = graphics.getFontMetrics();
                            final String a = this.a();
                            graphics.drawString(a, size.width - fontMetrics.stringWidth(a) - 1, fontMetrics.getMaxAscent());
                        }
                        
                        public void a(final String a) {
                            this.a = a;
                        }
                        
                        public String a() {
                            return this.a;
                        }
                    }, "Center");
                }
                if (b) {
                    this.add(this.b = new Component() {
                        public Dimension a = new Dimension(10, 10);
                        public int b = 0;
                        
                        {
                            this.setSize(this.getPreferredSize());
                        }
                        
                        public void a(final int b) {
                            this.b = b;
                            this.repaint();
                        }
                        
                        public void update(final Graphics graphics) {
                            this.paint(graphics);
                        }
                        
                        public void paint(final Graphics graphics) {
                            final Dimension size = this.getSize();
                            graphics.setColor(this.getBackground());
                            graphics.fillRect(0, 0, size.width, size.height);
                            final Rectangle intersection = new Rectangle((size.width - 10) / 2, (size.height - 10) / 2, 10, 10).intersection(new Rectangle(size));
                            switch (this.b) {
                                case 2: {
                                    graphics.setColor(Color.green.darker());
                                    break;
                                }
                                case 3: {
                                    graphics.setColor(Color.green);
                                    break;
                                }
                                case 1: {
                                    graphics.setColor(Color.yellow);
                                    break;
                                }
                                default: {
                                    graphics.setColor(Color.red);
                                    break;
                                }
                            }
                            graphics.fillOval(intersection.x, intersection.y, intersection.width, intersection.height);
                            graphics.setColor(Color.black);
                            graphics.drawOval(intersection.x, intersection.y, intersection.width - 1, intersection.height - 1);
                        }
                        
                        public Dimension getMinimumSize() {
                            return this.getPreferredSize();
                        }
                        
                        public Dimension getPreferredSize() {
                            return this.a;
                        }
                        
                        public Dimension getMaximumSize() {
                            return this.getPreferredSize();
                        }
                    }, "East");
                }
                this.a(0, null);
            }
            
            public void a(final int n, final Date date) {
                if (this.a != null && (ac.a(ac.this).i().j != null || !ac.a(ac.this).i().j.equals(""))) {
                    this.a.a(ac.a(ac.this).i().j, n, date);
                }
                if (this.b != null) {
                    this.b.a(n);
                }
            }
            
            public Dimension getMinimumSize() {
                return this.getPreferredSize();
            }
            
            public Dimension getPreferredSize() {
                final Dimension dimension = new Dimension();
                dimension.width = 0;
                dimension.height = 0;
                if (this.a != null) {
                    final Dimension dimension2 = dimension;
                    dimension2.width += this.getFontMetrics(this.getFont()).stringWidth(this.a.a()) + 2;
                }
                if (this.b != null) {
                    final Dimension dimension3 = dimension;
                    dimension3.width += 12;
                }
                if (dimension.width > 0) {
                    dimension.height = 12;
                }
                return dimension;
            }
            
            public Dimension getMaximumSize() {
                return this.getPreferredSize();
            }
            
            public void setBackground(final Color color) {
                super.setBackground(color);
                synchronized (this.getTreeLock()) {
                    for (int i = 0; i < this.getComponentCount(); ++i) {
                        this.getComponent(i).setBackground(color);
                    }
                }
            }
            
            public void setForeground(final Color color) {
                super.setForeground(color);
                synchronized (this.getTreeLock()) {
                    for (int i = 0; i < this.getComponentCount(); ++i) {
                        this.getComponent(i).setForeground(color);
                    }
                }
            }
            
            public void setFont(final Font font) {
                super.setFont(font);
                synchronized (this.getTreeLock()) {
                    for (int i = 0; i < this.getComponentCount(); ++i) {
                        this.getComponent(i).setFont(font);
                    }
                }
            }
            
            public String a(String s, final int n, final Date date) {
                if (s != null) {
                    String s2 = "";
                    switch (n) {
                        case 0: {
                            s2 = "Not connected to server.";
                            break;
                        }
                        case 2: {
                            s2 = "Connected to server.";
                            break;
                        }
                        case 1: {
                            s2 = "Attempting to reconnect to server.";
                            break;
                        }
                        case 3: {
                            s2 = "Receiving data.";
                            break;
                        }
                    }
                    if (date != null) {
                        s = ac.a(ac.this).d().a(s, "$UpdateTime", this.c.format(date));
                    }
                    else {
                        s = ac.a(ac.this).d().a(s, "$UpdateTime", "");
                    }
                    s = ac.a(ac.this).d().a(s, "$Status", s2);
                }
                return s;
            }
        });
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        for (int i = 0; i < this.s.getComponentCount(); ++i) {
            this.s.getComponent(i).addMouseListener(this);
            this.s.getComponent(i).addMouseMotionListener(this);
        }
        e.a().addComponentListener(this);
    }
    
    public void a(final int n) {
        if ((n & 0x1) != 0x0 || (n & 0x4) != 0x0 || (n & 0x20) != 0x0) {
            final s f = this.e.f();
            final y b;
            if ((b = f.f.b()) != null) {
                this.d = b.h;
                this.m = this.b.i().g;
                this.l = "";
                if (f.f.b() != null) {
                    this.l = f.f.c();
                }
                final int a = f.f.a();
                this.f = a;
                this.g = new Color[this.f];
                this.h = new String[this.f];
                this.i = new String[this.f];
                this.c.width = 10 * a;
                this.j = f.f.c(0)[0];
                this.k = f.f.c(a - 1)[1];
                for (int i = 0; i < a; ++i) {
                    this.g[i] = f.f.d(i);
                    final Double[] c = f.f.c(i);
                    Object o;
                    if ((o = this.d.a(c[0])) == null) {
                        o = f.c().a;
                    }
                    this.h[i] = "" + o;
                    Object o2;
                    if ((o2 = this.d.a(c[1])) == null) {
                        o2 = f.c().a;
                    }
                    this.i[i] = "" + o2;
                }
            }
            else {
                f.c().w = false;
                this.invalidate();
                this.validate();
            }
            this.invalidate();
            this.validate();
            this.repaint();
        }
        if ((n & 0x200) != 0x0) {
            this.invalidate();
            this.getParent().validate();
            this.getParent().repaint();
        }
        if ((n & 0x2) != 0x0) {
            this.a(this.getParent());
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        if (this.b != null) {
            this.a((Component)componentEvent.getSource());
        }
    }
    
    public void a(final Component component) {
        boolean visible;
        if ((this.e.f().c().w ? 0.25 : 0.15) * component.getSize().height <= this.getMinimumSize().height) {
            visible = this.isVisible();
            this.setVisible(false);
        }
        else {
            visible = !this.isVisible();
            this.setVisible(true);
        }
        if (visible) {
            this.invalidate();
            this.validate();
            this.repaint(5L);
            component.invalidate();
            component.validate();
            component.repaint(10L);
        }
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this) {
            if (this.n != null) {
                this.e.a(this.n, "_blank");
            }
            mouseEvent.consume();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.o = 0;
        if (this.r) {
            this.r = false;
        }
        this.repaint(5L);
        mouseEvent.consume();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        final Dimension size = this.getSize();
        if (mouseEvent.getSource() != this) {
            for (Component parent = (Component)mouseEvent.getSource(); parent != this; parent = parent.getParent()) {
                x += parent.getBounds().x;
                y += parent.getBounds().y;
            }
        }
        final int n = size.width - 24;
        this.o = x;
        this.q = y;
        if (x >= 12 && x < n + 12 && y > 6 && y < 21) {
            if (!this.r) {
                this.r = true;
                this.repaint(5L);
            }
            else {
                if (this.e.f().f.a(this.p - 12, n) != this.e.f().f.a(x - 12, n)) {
                    this.repaint(5L);
                }
                this.p = this.o;
            }
        }
        else if (this.r) {
            this.r = false;
            this.repaint(5L);
        }
        if (this.q < size.height - 5 && this.q > size.height - 12 - 5) {
            this.repaint(5L);
        }
        mouseEvent.consume();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public Dimension getMinimumSize() {
        this.c.height = 6 + (this.e.f().c().w ? 18 : 0) + 9 + 3 + (this.m ? 16 : 0);
        return new Dimension(this.c);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public void a(final Graphics graphics) {
        String string = "";
        String string2 = "";
        final boolean w = this.e.f().c().w;
        final boolean b = false;
        final Dimension size = this.getSize();
        graphics.setColor(this.b.i().c);
        graphics.fillRect(0, 0, size.width, size.height);
        final int n = size.width - 24;
        if (w) {
            for (int i = 0; i < this.f; ++i) {
                graphics.setColor(this.g[i]);
                graphics.fillRect(12 + this.e.f().f.b(i, n), 6, this.e.f().f.c(i, n) + 1, 15);
            }
        }
        graphics.setColor(this.b.i().f);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.setColor(f.a(this.getBackground()));
        final Font font = this.getFont();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int n2 = 6 + (w ? 15 : -3) + fontMetrics.getAscent();
        if (w) {
            Object o;
            if ((o = this.d.a(this.j)) == null) {
                o = this.e.f().c().a;
            }
            string = "" + o;
            graphics.drawString(string, 12, n2);
            Object o2;
            if ((o2 = this.d.a(this.k)) == null) {
                o2 = this.e.f().c().a;
            }
            string2 = "" + o2;
            graphics.drawString(string2, n + 12 - fontMetrics.stringWidth(string2), n2);
        }
        final int n3 = n - fontMetrics.stringWidth(string) - fontMetrics.stringWidth(string2) - 4;
        final int stringWidth = fontMetrics.stringWidth(this.l);
        if (stringWidth < n3) {
            graphics.drawString(this.l, (size.width - stringWidth) / 2, n2);
        }
        this.n = null;
        final Rectangle bounds = this.s.getBounds();
        bounds.setSize(this.s.getPreferredSize());
        if (bounds.getSize().width > 0) {
            final int n4;
            if (bounds.width > 0 && size.width - (bounds.width + 1 + 5) >= 0 && (n4 = n - (bounds.width + 1 + 5)) >= 0) {
                final Dimension dimension = size;
                dimension.width -= bounds.width + 1 + 5;
                final int n5 = n4 - (bounds.width + 1 + 5);
                bounds.x = size.width - 5;
                bounds.y = size.height - bounds.height - 1;
                this.s.setLocation(bounds.x, bounds.y);
                this.s.setSize(bounds.getSize());
                this.s.invalidate();
                this.s.validate();
                final Graphics create = graphics.create();
                create.translate(bounds.x, bounds.y);
                this.s.paint(create);
                create.dispose();
                if (bounds.x <= this.o && this.o <= bounds.x + bounds.width && bounds.y <= this.q && this.q <= bounds.y + bounds.height) {
                    this.b(graphics, fontMetrics);
                }
            }
            else {
                this.s.setSize(0, 0);
            }
        }
        if (this.m) {
            String s = "© 2002-2005 SS&C Technologies, Inc. - ";
            int n6 = fontMetrics.stringWidth(s);
            String s2 = "www.heatmaps.com";
            int n7 = fontMetrics.stringWidth(s2);
            final String s3 = "Control Panel";
            final int n8 = fontMetrics.stringWidth(s3) + 4;
            for (int n9 = n6 + (b ? (n8 + 6) : 0) + n7, n10 = 0; n10 <= 4 && n9 >= size.width - 15; n9 = n6 + (b ? (n8 + 6) : 0) + n7, ++n10) {
                switch (n10) {
                    case 0: {
                        s = "© 2002-2005 SS&C Technologies - ";
                        s2 = "www.heatmaps.com";
                        break;
                    }
                    case 1: {
                        s = "© 2002-2005 SS&C Technologies - ";
                        s2 = "heatmaps.com";
                        break;
                    }
                    case 2: {
                        s = "© 2002-2005 SS&C Technologies";
                        s2 = "";
                        break;
                    }
                    case 3: {
                        s = "© SS&C Technologies";
                        s2 = "";
                        break;
                    }
                    default: {
                        s = "";
                        s2 = "";
                        break;
                    }
                }
                n6 = fontMetrics.stringWidth(s);
                n7 = fontMetrics.stringWidth(s2);
            }
            if (n6 < size.width) {
                if (this.o > 12 && this.o < 12 + n6 + n7 && this.q < size.height - 5 && this.q > size.height - 5 - fontMetrics.getAscent() - 4) {
                    graphics.setColor(Color.red);
                    this.n = this.e.f().e().toString();
                }
                else {
                    this.b.d();
                    graphics.setColor(f.a(this.getBackground()));
                }
                graphics.drawString(s, 12, size.height - 5);
                final int n11 = n6 + 12;
                if (s2 != null && s2.trim().length() > 0) {
                    graphics.drawString(s2, n11, size.height - 5);
                    graphics.drawLine(n11, size.height - 5 + 1, n11 + n7, size.height - 5 + 1);
                }
            }
            else if (s2 != null && s2.trim().length() > 0) {
                if (this.o > 12 && this.o < 12 + n7 && this.q < size.height - 5 && this.q > size.height - 5 - fontMetrics.getAscent() - 4) {
                    graphics.setColor(Color.red);
                    this.n = this.e.f().e().toString();
                }
                else {
                    this.b.d();
                    graphics.setColor(f.a(this.getBackground()));
                }
                final int n12 = n6 + 12;
                graphics.drawString(s2, n12, size.height - 5);
                graphics.drawLine(n12, size.height - 5 + 1, n12 + n7, size.height - 5 + 1);
            }
            final int n13 = size.width / 2 - n8 / 2;
            if (12 + n6 < n13 && n13 < size.width - 12 - n7 && b) {
                if (this.o < n13 + n8 && this.o > n13 && this.q < size.height - 5 && this.q > size.height - 5 - fontMetrics.getAscent() - 4) {
                    this.n = "hmc://dialog";
                }
                graphics.setColor(this.b.i().f);
                graphics.fillRect(n13, size.height - 5 + 1 - fontMetrics.getAscent() - 4, n8, fontMetrics.getAscent() + 4);
                this.b.d();
                graphics.setColor(f.a(graphics.getColor()));
                graphics.drawString(s3, n13 + 2, size.height - 5 + 1 - 4 + 2);
                if (this.n != null && this.n.startsWith("hmc://")) {
                    graphics.setColor(Color.red);
                }
                else {
                    this.b.d();
                    graphics.setColor(f.a(this.getBackground()));
                }
                graphics.drawRect(n13, size.height - 5 + 1 - fontMetrics.getAscent() - 4, n8, fontMetrics.getAscent() + 4);
            }
        }
        if (this.n != null) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        if (w) {
            this.a(graphics, fontMetrics);
        }
    }
    
    public void a() {
        this.g = null;
        this.a = null;
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        this.e = null;
        this.i = null;
        this.l = null;
        this.h = null;
        this.k = null;
        this.j = null;
        this.c = null;
        this.n = null;
        this.b = null;
    }
    
    private void a(final Graphics graphics, final FontMetrics fontMetrics) {
        if (this.r) {
            final Dimension size = this.getSize();
            final int o = this.o;
            final int a = this.e.f().f.a(o - 12, size.width - 24);
            if (this.h[a].length() == 0 || this.i[a].length() == 0) {
                return;
            }
            final String string = this.h[a] + " to " + this.i[a];
            final int n = fontMetrics.stringWidth(string) + 4;
            int n2 = (o < size.width / 2) ? (o + 10) : (o - n - 10);
            if (n2 < 1) {
                n2 = 1;
            }
            else if (n2 > size.width - 1 - n) {
                n2 = size.width - 1 - n;
            }
            final int n3 = fontMetrics.getAscent() + 4;
            final int n4 = 2;
            graphics.setColor(this.b.i().d);
            graphics.fillRect(n2, n4, n, n3);
            this.b.d();
            graphics.setColor(f.a(graphics.getColor()));
            graphics.drawRect(n2, n4, n, n3);
            graphics.drawString(string, n2 + 2, n4 + n3 - 2);
        }
    }
    
    private void b(final Graphics graphics, final FontMetrics fontMetrics) {
        final String a = this.s.a(this.b.i().k, this.t, this.u);
        if (a != null && a.trim().length() > 0) {
            final Dimension size = this.getSize();
            final int n = size.width - 1 - 5;
            final int n2 = fontMetrics.stringWidth(a) + 4;
            int n3 = (n < size.width / 2) ? (n + 10) : (n - n2 - 10);
            if (n3 < 1) {
                n3 = 1;
            }
            else if (n3 > size.width - 1 - n2) {
                n3 = size.width - 1 - n2;
            }
            final int n4 = fontMetrics.getAscent() + 4;
            final int n5 = size.height - n4 - 5 - 1;
            graphics.setColor(this.b.i().d);
            graphics.fillRect(n3, n5, n2, n4);
            this.b.d();
            graphics.setColor(f.a(graphics.getColor()));
            graphics.drawRect(n3, n5, n2, n4);
            graphics.drawString(a, n3 + 2, n5 + n4 - 2);
        }
    }
    
    public void a(final int n, Date u) {
        int t = 0;
        switch (n) {
            case 2: {
                t = 2;
                break;
            }
            case 1: {
                t = 1;
                break;
            }
            case 3: {
                t = 3;
                break;
            }
            default: {
                t = 0;
                break;
            }
        }
        if (u == null) {
            u = this.u;
        }
        this.s.a(t, u);
        this.t = t;
        this.u = u;
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public static /* synthetic */ d a(final ac ac) {
        return ac.b;
    }
}
