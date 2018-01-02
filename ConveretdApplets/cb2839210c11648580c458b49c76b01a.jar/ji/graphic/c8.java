// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Point;
import ji.annotate.dg;
import ji.v1base.bz;
import java.awt.Dimension;
import ji.util.m;
import ji.util.d;
import ji.document.ad;
import ji.image.cy;
import ji.util.i;
import java.awt.Component;
import ji.util.e;
import ji.v1event.d8;
import ji.v1base.jiPanel;
import java.awt.Color;
import ji.v1event.c9;

public class c8 implements c9
{
    private boolean a;
    private boolean b;
    private Color c;
    private ua d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private jiPanel i;
    private String j;
    
    public c8(final String s, final jiPanel i, final boolean b, final boolean g, final String j) {
        this.a = false;
        this.b = true;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = true;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = null;
        try {
            this.i = i;
            this.g = g;
            this.j = j;
            (this.d = new ua(this.j)).setEnabled(false);
            this.d.setAcceptFocus(false);
            if (!this.e) {
                this.a(i);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final d8 d8) {
        if (ji.util.e.af(this.j)) {
            try {
                if (d8.getSource() != null) {
                    switch (d8.c()) {
                        case 3: {
                            this.b = true;
                            break;
                        }
                        case 4: {
                            if (this.b) {
                                this.b = false;
                                this.a(d8.getSource());
                                break;
                            }
                            break;
                        }
                        case 1: {
                            this.b(d8);
                            break;
                        }
                        case 2: {
                            this.a(d8.getSource());
                            break;
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final void a(final jiPanel jiPanel) {
        try {
            if (!this.a && this.d != null) {
                jiPanel.add(this.d, 0);
                this.a = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final Color darkShade) {
        try {
            if (this.d != null) {
                this.d.setDarkShade(darkShade);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void b(final Color color) {
        try {
            this.c = color;
            if (this.d != null) {
                this.d.setBackground(color);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final Object o) {
        final wl wl = new wl(this, o);
        if (ji.util.i.c(142)) {
            ji.util.e.a(wl, this.j);
        }
        else {
            wl.run();
        }
    }
    
    private final void b(final Object o) {
        try {
            if (o instanceof cy) {
                ((cy)o).gu();
            }
            else if (o instanceof ad) {
                ((ad)o).gt();
            }
            ji.util.d.ew();
            if (this.d != null) {
                boolean b = true;
                if (ji.util.d.ao(this.j) && this.f) {
                    try {
                        final int countComponents = this.i.countComponents();
                        final m m = new m(this.i);
                        final Integer n = (Integer)m.a("getComponentZOrder", this.d);
                        final int n2 = countComponents - 1;
                        if (n != n2) {
                            m.a("setComponentZOrder", this.d, new Integer(n2));
                        }
                        ji.util.d.a3(false);
                        b = false;
                    }
                    catch (Exception ex) {}
                }
                if (b && this.d.getLocation().x >= 0) {
                    final Dimension size = this.d.getSize();
                    this.d.setLocation(-3 * size.width, -3 * size.height);
                    if (this.h && this.d.isVisible()) {
                        this.d.setVisible(false);
                    }
                    ji.util.d.a3(false);
                    ji.util.d.ex();
                    if (this.e) {
                        this.c();
                    }
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    public void a() {
        try {
            if (this.d != null) {
                this.c();
                this.d.releaseResources();
                this.d = null;
            }
            this.i = null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void c() {
        try {
            if (this.a && this.d != null) {
                this.i.remove(this.d);
                this.a = false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean b() {
        return ji.util.e.af(this.j);
    }
    
    private final void b(final d8 d8) {
        final aap aap = new aap(this, d8);
        if (ji.util.i.c(142)) {
            ji.util.e.a(aap, this.j);
        }
        else {
            aap.run();
        }
    }
    
    private final void c(final d8 d8) {
        if (ji.util.d.ax(this.j) && ji.util.d.dp()) {
            return;
        }
        try {
            if (this.b && this.b()) {
                if (d8.getSource() instanceof cy) {
                    ((cy)d8.getSource()).gu();
                }
                final Object source = d8.getSource();
                if (source instanceof cz && !ji.util.d.by()) {
                    return;
                }
                String s = null;
                ji.util.d.ew();
                if (this.d != null) {
                    if (source instanceof jiImageButton) {
                        if (((jiImageButton)source).getState() == 2) {
                            s = ((jiImageButton)source).getHelpTextRaised();
                        }
                        else {
                            s = ((jiImageButton)source).getHelpTextInset();
                        }
                    }
                    else if (source instanceof by) {
                        if (!((by)source).f()) {
                            return;
                        }
                        s = ((by)source).e();
                    }
                    else if (source instanceof bz) {
                        if (!((jiImageButton)source).isEnabled()) {
                            return;
                        }
                        s = ((jiImageButton)source).c(this.j);
                    }
                    else if (source instanceof cy) {
                        s = d8.b();
                    }
                    else if (source instanceof dg) {
                        s = d8.b();
                    }
                    if (s == null) {
                        s = null;
                    }
                    else if (s.length() < 1) {
                        s = null;
                    }
                }
                if (s != null) {
                    if (s.length() > ji.util.i.d(4)) {
                        s = String.valueOf(String.valueOf(s.substring(0, ji.util.i.d(4)))).concat("...");
                    }
                    if (this.e) {
                        this.a(this.i);
                    }
                    final Dimension size = this.i.getSize();
                    final Point e = d8.e();
                    final Point locationOnScreen = this.i.getLocationOnScreen();
                    final Dimension d9 = d8.d();
                    final Graphics graphics = this.i.getGraphics();
                    if (locationOnScreen != null && e != null && s != null && graphics != null) {
                        final Dimension dimension = new Dimension(size.width, size.height);
                        final Point point = e;
                        point.x -= locationOnScreen.x;
                        final Point point2 = e;
                        point2.y -= locationOnScreen.y;
                        dimension.width = Math.min(dimension.width, 9 * size.width / 10);
                        dimension.height = Math.min(dimension.height, 9 * size.height / 10);
                        this.d.a(s, graphics, d8.a(), dimension);
                        final Dimension size2 = this.d.getSize();
                        int max = Math.max(e.x + d9.width / 2, 1);
                        int max2 = Math.max(e.y + d9.height, 1);
                        if (max2 + size2.height + 2 > size.height) {
                            max2 = e.y - size2.height - 2;
                        }
                        if (max + size2.width + 3 > size.width) {
                            max = size.width - size2.width - 3;
                        }
                        final int max3 = Math.max(max, 1);
                        final int max4 = Math.max(max2, 1);
                        if (max3 >= 0 && max4 >= 0) {
                            boolean b = true;
                            if (ji.util.d.ao(this.j) && this.f) {
                                try {
                                    ji.util.e.a(this.d, max3, max4, size2.width, size2.height);
                                    final m m = new m(this.i);
                                    if ((int)m.a("getComponentZOrder", this.d) != 0) {
                                        m.a("setComponentZOrder", this.d, new Integer(0));
                                    }
                                    b = false;
                                }
                                catch (Exception ex) {}
                            }
                            if (b) {
                                ji.util.e.a(this.d, max3, max4, size2.width, size2.height);
                                if (this.h && !this.d.isVisible()) {
                                    this.d.setVisible(true);
                                }
                            }
                            ji.util.d.a3(true);
                            ji.util.d.ex();
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    class ua extends jiPanel
    {
        String a;
        String[] b;
        int c;
        int d;
        int e;
        int f;
        boolean g;
        
        public ua(final String s) {
            super(s);
            this.a = null;
            this.b = null;
            this.c = 0;
            this.d = 0;
            this.e = 1;
            this.f = 0;
            this.setOpaque(this.g = true);
            this.setAcceptFocus(false);
            this.setBorderStyle(6);
            this.setDarkShade(Color.black);
            c8.this.c = new Color(255, 255, 225);
            this.setBackground(c8.this.c);
        }
        
        public void a(final String a, final Graphics graphics, final boolean g, final Dimension dimension) {
            try {
                if (a != null && graphics != null) {
                    if (ji.util.e.c(this.a, a)) {
                        this.a = a;
                        this.g = g;
                        final StringTokenizer stringTokenizer = new StringTokenizer(a, "\n");
                        this.e = Math.max(1, stringTokenizer.countTokens());
                        this.b = new String[this.e];
                        this.c = 0;
                        int n = 0;
                        while (stringTokenizer.hasMoreTokens()) {
                            this.b[n++] = stringTokenizer.nextToken();
                            this.c = Math.max(this.c, graphics.getFontMetrics().stringWidth(this.b[n - 1]) + 6);
                        }
                        if (c8.this.g) {
                            if (ji.util.d.em() || ji.util.d.aj(c8.this.j)) {
                                this.f = graphics.getFontMetrics().getHeight() + 4;
                                this.d = this.f * this.e;
                            }
                            else {
                                this.f = graphics.getFontMetrics().getHeight() + 2;
                                this.d = this.f * this.e;
                            }
                        }
                        else {
                            this.f = graphics.getFontMetrics().getHeight();
                            this.d = this.f * this.e;
                        }
                        if (dimension != null) {
                            if (dimension.width > 0) {
                                this.c = Math.min(dimension.width, this.c);
                            }
                            if (dimension.height > 0) {
                                this.d = Math.min(dimension.height, this.d);
                            }
                        }
                        this.setSize(this.c, this.d);
                        this.repaint();
                    }
                }
                else {
                    this.a = null;
                }
            }
            catch (Exception ex) {}
        }
        
        public void paint(final Graphics graphics) {
            if (c8.this.e) {
                super.paint(graphics);
            }
            else {
                this.paintComponent(graphics);
            }
        }
        
        public void paintComponent(final Graphics graphics) {
            final Dimension size = this.getSize();
            ji.util.d.o = this.getBounds();
            if (c8.this.c != null && c8.this.e && graphics != null) {
                graphics.setColor(c8.this.c);
                graphics.fillRect(0, 0, size.width, size.height);
            }
            if (graphics != null) {
                super.paintComponent(graphics);
            }
            if (this.getDarkShade() != null) {
                graphics.setColor(this.getDarkShade());
            }
            if (this.a != null) {
                int n = 5 + this.f / 2;
                int n2 = 2;
                for (int i = 0; i < this.b.length; ++i) {
                    if (this.g) {
                        n2 = (size.width - graphics.getFontMetrics().stringWidth(this.b[i])) / 2;
                    }
                    if (this.b[i] != null) {
                        graphics.drawString(this.b[i], n2, n);
                    }
                    n += this.f;
                }
            }
        }
        
        public final void update(final Graphics graphics) {
            if (c8.this.e) {
                super.update(graphics);
            }
            else {
                this.paintComponent(graphics);
            }
        }
    }
}
