import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class gb extends Panel implements ActionListener, MouseListener
{
    private final esChat a;
    Graphics b;
    Image c;
    boolean d;
    boolean e;
    String f;
    boolean g;
    private static String z;
    
    gb(final esChat a) {
        this.a = a;
        this.d = false;
        this.e = false;
        this.f = "";
        this.g = false;
        this.setLayout(new BorderLayout());
        this.setForeground(a.i);
        final Panel panel = new Panel(new BorderLayout());
        this.f = a.wb;
        this.addMouseListener(this);
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int m = fb.m;
        boolean b2;
        boolean kb;
        final boolean b = kb = (b2 = (actionEvent.getSource() instanceof c));
        if (m == 0) {
            if (!b) {
                return;
            }
            final boolean b3;
            kb = (b3 = (b2 = actionEvent.getActionCommand().equals(" ")));
        }
        Label_0100: {
            if (m == 0) {
                if (!b) {
                    break Label_0100;
                }
                b2 = (kb = this.a.Kb);
            }
            final esChat a;
            Label_0093: {
                if (m == 0) {
                    if (!kb) {
                        this.a.Kb = true;
                        this.a.i();
                        if (m == 0) {
                            return;
                        }
                    }
                    a = this.a;
                    if (m != 0) {
                        break Label_0093;
                    }
                    b2 = a.Kb;
                }
                if (!b2) {
                    return;
                }
                this.a.Kb = false;
                final esChat a2 = this.a;
            }
            a.h();
            if (m == 0) {
                return;
            }
        }
        this.a.Tb.b.a(this.a.Tb.b.e(), this.a.Tb.b.d());
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
        final int m = fb.m;
        final int x = mouseEvent.getX();
        final Dimension size = this.getSize();
        final int n2;
        final int n = n2 = x;
        int n4;
        final int n3 = n4 = size.width - 23;
        final int n5;
        final int n6;
        Label_0074: {
            if (m == 0) {
                if (n > n3) {
                    n5 = x;
                    n6 = size.width - 7;
                    if (m != 0) {
                        break Label_0074;
                    }
                    if (n5 < n6) {
                        this.d = true;
                        this.e = false;
                        this.repaint();
                        if (m == 0) {
                            return;
                        }
                    }
                }
                final int n7 = size.width - 41;
            }
        }
        if (m == 0) {
            if (n <= n3) {
                return;
            }
            n4 = size.width - 26;
        }
        if (n5 < n6) {
            this.d = false;
            this.e = true;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int m = fb.m;
        this.d = false;
        this.e = false;
        this.repaint();
        final int x = mouseEvent.getX();
        final Dimension size = this.getSize();
        int n2;
        final int n = n2 = x;
        int n4;
        final int n3 = n4 = size.width - 23;
        final int n5;
        final int n6;
        Label_0113: {
            if (m == 0) {
                if (n > n3) {
                    n5 = x;
                    n6 = size.width - 7;
                    if (m != 0) {
                        break Label_0113;
                    }
                    if (n5 < n6) {
                        this.a.Tb.b.a(this.a.Tb.b.e(), this.a.Tb.b.d());
                        return;
                    }
                }
                final int n7 = size.width - 41;
            }
        }
        boolean kb2 = false;
        boolean kb = false;
        Label_0144: {
            if (m == 0) {
                if (n <= n3) {
                    return;
                }
                kb = ((n2 = ((kb2 = (x != 0)) ? 1 : 0)) != 0);
                if (m != 0) {
                    break Label_0144;
                }
                n4 = size.width - 26;
            }
            if (n5 >= n6) {
                return;
            }
            kb2 = (kb = this.a.Kb);
        }
        final esChat a;
        Label_0199: {
            if (m == 0) {
                if (!kb) {
                    this.a.Kb = true;
                    this.a.i();
                    if (m == 0) {
                        return;
                    }
                }
                a = this.a;
                if (m != 0) {
                    break Label_0199;
                }
                kb2 = a.Kb;
            }
            if (!kb2) {
                return;
            }
            this.a.Kb = false;
            final esChat a2 = this.a;
        }
        a.h();
    }
    
    public void paint(final Graphics graphics) {
        final int m = fb.m;
        gb gb = this;
        gb gb2 = this;
        if (m == 0) {
            if (this.a.l) {
                this.c = null;
            }
            gb = this;
            gb2 = this;
        }
        gb gb3 = null;
        Label_0088: {
            Label_0087: {
                if (m == 0) {
                    if (gb2.c != null) {
                        gb3 = this;
                        if (m != 0) {
                            break Label_0088;
                        }
                        if (this.b != null) {
                            break Label_0087;
                        }
                    }
                    this.c = this.a.Ib.createImage(this.getSize().width, 24);
                    this.b = this.c.getGraphics();
                    gb = this;
                }
                gb.a.l = false;
            }
            gb3 = this;
        }
        final Dimension size = gb3.getSize();
        this.b.drawImage(this.a.y, 0, 0, 10, 24, 125, 63, 135, 87, this);
        this.b.drawImage(this.a.y, 10, 0, size.width - 80, 24, 135, 63, 145, 87, this);
        this.b.drawImage(this.a.y, size.width - 80, 0, size.width, 24, 145, 63, 225, 87, this);
        final Image y = this.a.y;
        boolean b2 = false;
        final boolean b;
        Label_0329: {
            gb gb4 = null;
            Label_0326: {
                Label_0325: {
                    if (m == 0) {
                        gb gb5 = null;
                        Label_0266: {
                            if (y != null) {
                                gb4 = this;
                                gb5 = this;
                                if (m != 0) {
                                    break Label_0266;
                                }
                                if (this.f.startsWith("#")) {
                                    this.b.drawImage(this.a.y, 5, 4, 20, 18, 77, 41, 91, 54, this);
                                    if (m == 0) {
                                        break Label_0325;
                                    }
                                }
                            }
                            gb4 = this;
                            gb5 = this;
                        }
                        if (m != 0) {
                            break Label_0326;
                        }
                        final Image y2 = gb5.a.y;
                    }
                    if (y != null) {
                        b = (b2 = this.f.startsWith("#"));
                        if (m != 0) {
                            break Label_0329;
                        }
                        if (!b) {
                            this.b.drawImage(this.a.y, 5, 4, 20, 18, 61, 41, 75, 54, this);
                        }
                    }
                }
                gb4 = this;
            }
            final boolean e;
            b2 = (e = gb4.e);
        }
        if (m == 0) {
            if (b) {
                this.b.drawImage(this.a.y, size.width - 41, 5, size.width - 25, 21, 186, 20, 202, 36, this);
            }
            b2 = this.d;
        }
        if (m == 0) {
            if (b2) {
                this.b.drawImage(this.a.y, size.width - 23, 5, size.width - 7, 21, 210, 20, 226, 36, this);
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
        final int m = fb.m;
        final int n = (this.getSize().width - 10) / this.getFontMetrics(this.getFont()).charWidth('a');
        final int length = f.length();
        gb gb = null;
        Label_0123: {
            if (m == 0) {
                if (length > n) {
                    f = f.substring(0, n);
                    f = String.valueOf(f) + gb.z;
                }
                this.f = f;
                gb = this;
                if (m != 0) {
                    break Label_0123;
                }
                final boolean kb = this.a.Kb;
            }
            if (length != 0) {
                gb = this;
                if (m != 0) {
                    break Label_0123;
                }
                if (this.a.Hb != null) {
                    this.a.Hb.setTitle(this.f);
                }
            }
            gb = this;
        }
        gb.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final char[] charArray = "\u0007_r\t".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\'';
                    break;
                }
                case 1: {
                    c2 = 'q';
                    break;
                }
                case 2: {
                    c2 = '\\';
                    break;
                }
                case 3: {
                    c2 = '\'';
                    break;
                }
                default: {
                    c2 = ',';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        gb.z = new String(charArray).intern();
    }
}
