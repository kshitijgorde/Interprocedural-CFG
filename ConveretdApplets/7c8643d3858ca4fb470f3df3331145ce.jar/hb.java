import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class hb extends Panel implements ActionListener, MouseListener, ComponentListener
{
    private final esChat a;
    Graphics b;
    Image c;
    boolean d;
    boolean e;
    String f;
    boolean g;
    boolean h;
    private static String z;
    
    hb(final esChat a) {
        this.a = a;
        this.d = false;
        this.e = false;
        this.f = "";
        this.g = false;
        this.h = false;
        this.setLayout(new BorderLayout());
        this.setForeground(a.i);
        final Panel panel = new Panel(new BorderLayout());
        this.f = a.Bb;
        this.addMouseListener(this);
        this.addComponentListener(this);
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean r = d.r;
        boolean b2;
        boolean qb;
        final boolean b = qb = (b2 = (actionEvent.getSource() instanceof c));
        if (!r) {
            if (!b) {
                return;
            }
            final boolean b3;
            qb = (b3 = (b2 = actionEvent.getActionCommand().equals(" ")));
        }
        Label_0100: {
            if (!r) {
                if (!b) {
                    break Label_0100;
                }
                b2 = (qb = this.a.Qb);
            }
            final esChat a;
            Label_0093: {
                if (!r) {
                    if (!qb) {
                        this.a.Qb = true;
                        this.a.i();
                        if (!r) {
                            return;
                        }
                    }
                    a = this.a;
                    if (r) {
                        break Label_0093;
                    }
                    b2 = a.Qb;
                }
                if (!b2) {
                    return;
                }
                this.a.Qb = false;
                final esChat a2 = this.a;
            }
            a.h();
            if (!r) {
                return;
            }
        }
        this.a.Zb.b.a(this.a.Zb.b.e(), this.a.Zb.b.d());
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.h = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(460, 24);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(460, 24);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final int x = mouseEvent.getX();
        final Dimension size = this.getSize();
        final int n2;
        final int n = n2 = x;
        int n4;
        final int n3 = n4 = size.width - 38;
        final int n5;
        final int n6;
        Label_0074: {
            if (!r) {
                if (n > n3) {
                    n5 = x;
                    n6 = size.width - 10;
                    if (r) {
                        break Label_0074;
                    }
                    if (n5 < n6) {
                        this.d = true;
                        this.e = false;
                        this.repaint();
                        if (!r) {
                            return;
                        }
                    }
                }
                final int n7 = size.width - 68;
            }
        }
        if (!r) {
            if (n <= n3) {
                return;
            }
            n4 = size.width - 40;
        }
        if (n5 < n6) {
            this.d = false;
            this.e = true;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        this.d = false;
        this.e = false;
        this.repaint();
        final int x = mouseEvent.getX();
        final Dimension size = this.getSize();
        int n2;
        final int n = n2 = x;
        int n4;
        final int n3 = n4 = size.width - 38;
        final int n5;
        final int n6;
        Label_0113: {
            if (!r) {
                if (n > n3) {
                    n5 = x;
                    n6 = size.width - 10;
                    if (r) {
                        break Label_0113;
                    }
                    if (n5 < n6) {
                        this.a.Zb.b.a(this.a.Zb.b.e(), this.a.Zb.b.d());
                        return;
                    }
                }
                final int n7 = size.width - 68;
            }
        }
        boolean qb2 = false;
        boolean qb = false;
        Label_0144: {
            if (!r) {
                if (n <= n3) {
                    return;
                }
                qb = ((n2 = ((qb2 = (x != 0)) ? 1 : 0)) != 0);
                if (r) {
                    break Label_0144;
                }
                n4 = size.width - 30;
            }
            if (n5 >= n6) {
                return;
            }
            qb2 = (qb = this.a.Qb);
        }
        final esChat a;
        Label_0199: {
            if (!r) {
                if (!qb) {
                    this.a.Qb = true;
                    this.a.i();
                    if (!r) {
                        return;
                    }
                }
                a = this.a;
                if (r) {
                    break Label_0199;
                }
                qb2 = a.Qb;
            }
            if (!qb2) {
                return;
            }
            this.a.Qb = false;
            final esChat a2 = this.a;
        }
        a.h();
    }
    
    public void paint(final Graphics graphics) {
        final boolean r = d.r;
        hb hb = this;
        hb hb2 = this;
        if (!r) {
            if (this.h) {
                this.c = null;
            }
            hb = this;
            hb2 = this;
        }
        hb hb3 = null;
        Label_0090: {
            Label_0089: {
                if (!r) {
                    if (hb2.c != null) {
                        hb3 = this;
                        if (r) {
                            break Label_0090;
                        }
                        if (this.b != null) {
                            break Label_0089;
                        }
                    }
                    this.c = this.a.Ob.createImage(this.getSize().width, 24);
                    this.b = this.c.getGraphics();
                    this.h = false;
                    hb = this;
                }
                hb.a.l = false;
            }
            hb3 = this;
        }
        final Dimension size = hb3.getSize();
        this.b.drawImage(this.a.z, 0, 0, 10, 24, 125, 63, 135, 87, this);
        this.b.drawImage(this.a.z, 10, 0, size.width - 80, 24, 135, 63, 145, 87, this);
        this.b.drawImage(this.a.z, size.width - 80, 0, size.width, 24, 145, 63, 225, 87, this);
        final Image z = this.a.z;
        boolean b2 = false;
        final boolean b;
        Label_0331: {
            hb hb4 = null;
            Label_0328: {
                Label_0327: {
                    if (!r) {
                        hb hb5 = null;
                        Label_0268: {
                            if (z != null) {
                                hb4 = this;
                                hb5 = this;
                                if (r) {
                                    break Label_0268;
                                }
                                if (this.f.startsWith("#")) {
                                    this.b.drawImage(this.a.z, 5, 4, 20, 18, 77, 41, 91, 54, this);
                                    if (!r) {
                                        break Label_0327;
                                    }
                                }
                            }
                            hb4 = this;
                            hb5 = this;
                        }
                        if (r) {
                            break Label_0328;
                        }
                        final Image z2 = hb5.a.z;
                    }
                    if (z != null) {
                        b = (b2 = this.f.startsWith("#"));
                        if (r) {
                            break Label_0331;
                        }
                        if (!b) {
                            this.b.drawImage(this.a.z, 5, 4, 20, 18, 61, 41, 75, 54, this);
                        }
                    }
                }
                hb4 = this;
            }
            final boolean e;
            b2 = (e = hb4.e);
        }
        if (!r) {
            if (b) {
                this.b.drawImage(this.a.z, size.width - 68, 7, size.width - 40, 22, 165, 92, 193, 107, this);
            }
            b2 = this.d;
        }
        if (!r) {
            if (b2) {
                this.b.drawImage(this.a.z, size.width - 38, 7, size.width - 10, 22, 200, 110, 228, 125, this);
            }
            this.b.setColor(this.a.i);
            this.b.drawString(String.valueOf(this.f), 23, 17);
            graphics.drawImage(this.c, 0, 0, this);
        }
    }
    
    public void a() {
        this.repaint();
    }
    
    void a(String f) {
        final boolean r = d.r;
        final int n = (this.getSize().width - 10) / this.getFontMetrics(this.getFont()).charWidth('a');
        final int length = f.length();
        hb hb = null;
        Label_0136: {
            Label_0103: {
                if (!r) {
                    if (length > 5) {
                        final int length2 = f.length();
                        if (r) {
                            break Label_0103;
                        }
                        if (length2 > n) {
                            f = f.substring(0, n);
                            f = String.valueOf(f) + hb.z;
                        }
                    }
                    this.f = f;
                    hb = this;
                    if (r) {
                        break Label_0136;
                    }
                    final boolean qb = this.a.Qb;
                }
            }
            if (length != 0) {
                hb = this;
                if (r) {
                    break Label_0136;
                }
                if (this.a.Nb != null) {
                    this.a.Nb.setTitle(this.f);
                }
            }
            hb = this;
        }
        hb.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final char[] charArray = "\u0003}1\u0003".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '#';
                    break;
                }
                case 1: {
                    c2 = 'S';
                    break;
                }
                case 2: {
                    c2 = '\u001f';
                    break;
                }
                case 3: {
                    c2 = '-';
                    break;
                }
                default: {
                    c2 = 'Q';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        hb.z = new String(charArray).intern();
    }
}
