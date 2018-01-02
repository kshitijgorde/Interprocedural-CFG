import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends Canvas implements KeyListener, MouseListener, FocusListener, ComponentListener
{
    private final esChat a;
    private static final long serialVersionUID = 22L;
    String b;
    ActionListener c;
    sb d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    String i;
    int j;
    boolean k;
    Graphics l;
    Image m;
    StringBuffer n;
    int o;
    String p;
    Font q;
    public static boolean r;
    
    public d(final esChat a, final String b, final int o) {
        this.a = a;
        this.d = new sb(this.a, this);
        this.e = false;
        this.f = true;
        this.g = false;
        this.h = false;
        this.i = "";
        this.j = 100;
        this.k = false;
        this.n = new StringBuffer("");
        this.o = 100;
        this.p = "";
        this.o = o;
        this.l = null;
        this.m = null;
        this.q = new Font(a.eb, a.d, a.hb);
        if (!d.r) {
            if (a.A == null) {
                this.b = b;
            }
            this.enableEvents(8L);
            this.addKeyListener(this);
            this.addFocusListener(this);
            this.addMouseListener(this);
            this.addComponentListener(this);
            this.d.start();
            Thread.interrupted();
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
        this.m = null;
        this.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.m = null;
        this.repaint();
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    private void a() {
        if (this.c != null) {
            this.c.actionPerformed(new ActionEvent(this, 1001, this.b));
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.h = true;
        this.repaint();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        final boolean r = d.r;
        this.h = false;
        d d = this;
        Label_0039: {
            if (!r) {
                if (this.k) {
                    d = this;
                    if (r) {
                        break Label_0039;
                    }
                    if (this.n.length() == 0) {
                        this.a();
                    }
                }
                d = this;
            }
        }
        d.repaint();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.o, 20);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.o, 20);
    }
    
    public String b() {
        return this.n.toString();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean r = d.r;
        int n7;
        int keyCode;
        int n6;
        int n5;
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = (n5 = (n6 = (keyCode = (n7 = (this.f ? 1 : 0)))))));
        if (!r) {
            if (n == 0) {
                return;
            }
            n3 = (n2 = (n4 = (n5 = (n6 = (keyCode = (n7 = keyEvent.getKeyCode()))))));
        }
        int n13;
        int n12;
        int n11;
        int n10;
        int n9;
        final int n8 = n9 = (n10 = (n11 = (n12 = (n13 = 8))));
        final int n14;
        final int a;
        Label_0423: {
            Label_0419: {
                if (!r) {
                    if (n2 == n8) {
                        this.e = true;
                        final int n15;
                        n14 = (n15 = this.n.length());
                        if (r) {
                            break Label_0423;
                        }
                        if (n14 <= 0) {
                            break Label_0419;
                        }
                        a = this.d.a();
                        if (r) {
                            break Label_0423;
                        }
                        if (a <= 0) {
                            break Label_0419;
                        }
                        try {
                            this.n = this.a.a(this.n, this.d.a() - 1);
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            final String string = this.n.toString();
                            this.n = new StringBuffer(String.valueOf(string.substring(0, this.d.a() - 1)) + string.substring(this.d.a()));
                        }
                        this.d.a(1);
                        if (!r) {
                            break Label_0419;
                        }
                    }
                    n4 = (n3 = (n5 = (n6 = (keyCode = (n7 = keyEvent.getKeyCode())))));
                    final int n16;
                    n9 = (n16 = (n10 = (n11 = (n12 = (n13 = 127)))));
                }
                if (!r) {
                    if (n3 == n8) {
                        this.e = true;
                        final int a2 = this.d.a();
                        if (r) {
                            break Label_0423;
                        }
                        if (a2 >= this.n.length()) {
                            break Label_0419;
                        }
                        try {
                            this.n = this.a.a(this.n, this.d.a());
                            break Label_0419;
                        }
                        catch (NoSuchMethodError noSuchMethodError2) {
                            final String string2 = this.n.toString();
                            this.n = new StringBuffer(String.valueOf(string2.substring(0, this.d.a())) + string2.substring(this.d.a() + 1));
                            if (!r) {
                                break Label_0419;
                            }
                        }
                    }
                    n5 = (n4 = (n6 = (keyCode = (n7 = keyEvent.getKeyCode()))));
                    n10 = (n9 = (n11 = (n12 = (n13 = 10))));
                }
                if (!r) {
                    if (n4 == n9) {
                        this.e = true;
                        this.a();
                        if (!r) {
                            break Label_0419;
                        }
                    }
                    n6 = (n5 = (keyCode = (n7 = keyEvent.getKeyCode())));
                    n11 = (n10 = (n12 = (n13 = 37)));
                }
                if (!r) {
                    if (n5 == n10) {
                        this.d.a(1);
                        if (!r) {
                            break Label_0419;
                        }
                    }
                    keyCode = (n6 = (n7 = keyEvent.getKeyCode()));
                    n12 = (n11 = (n13 = 39));
                }
                if (!r) {
                    if (n6 == n11) {
                        this.d.b(1);
                        if (!r) {
                            break Label_0419;
                        }
                    }
                    n7 = (keyCode = keyEvent.getKeyCode());
                    n13 = (n12 = 36);
                }
                if (!r) {
                    if (keyCode == n12) {
                        this.d.c(0);
                        if (!r) {
                            break Label_0419;
                        }
                    }
                    final int n15;
                    n7 = (n15 = keyEvent.getKeyCode());
                    if (r) {
                        break Label_0423;
                    }
                    n13 = 35;
                }
                if (n7 == n13) {
                    this.d.c(this.n.length());
                }
            }
            final boolean k = this.k;
        }
        d d = null;
        Label_0485: {
            int j = 0;
            Label_0476: {
                if (!r) {
                    Label_0461: {
                        if (n14 != 0) {
                            final int n15;
                            final int n17 = n15 = keyEvent.getKeyCode();
                            final int n18 = j = 48;
                            Label_0456: {
                                if (!r) {
                                    if (n17 < n18) {
                                        break Label_0456;
                                    }
                                    keyEvent.getKeyCode();
                                    final int n19;
                                    j = (n19 = 57);
                                }
                                if (r) {
                                    break Label_0476;
                                }
                                if (n17 <= n18) {
                                    break Label_0461;
                                }
                            }
                            this.e = true;
                        }
                    }
                    d = this;
                    if (r) {
                        break Label_0485;
                    }
                    this.n.length();
                }
                j = this.j;
            }
            if (a >= j) {
                this.e = true;
            }
            d = this;
        }
        d.repaint();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final boolean r = d.r;
        boolean b2;
        boolean consumed;
        final boolean b = consumed = (b2 = this.f);
        if (!r) {
            if (!b) {
                return;
            }
            final boolean b3;
            consumed = (b3 = (b2 = keyEvent.isActionKey()));
        }
        d d = null;
        Label_0096: {
            Label_0095: {
                if (!r) {
                    if (b) {
                        break Label_0095;
                    }
                    b2 = (consumed = keyEvent.isConsumed());
                }
                if (!r) {
                    if (consumed) {
                        break Label_0095;
                    }
                    d = this;
                    if (r) {
                        break Label_0096;
                    }
                    b2 = this.e;
                }
                if (!b2) {
                    this.n.insert(this.d.a(), keyEvent.getKeyChar());
                    this.d.b(1);
                    d d2 = this;
                    if (!r) {
                        if (this.k) {
                            this.a();
                        }
                        d2 = this;
                    }
                    d2.repaint();
                }
            }
            d = this;
        }
        d.e = false;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(2));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final int x = mouseEvent.getX();
        final FontMetrics fontMetrics = this.l.getFontMetrics();
        int n = 0;
        this.d.c(this.p.length());
        int n2 = 0;
        while (true) {
        Label_0104:
            while (true) {
                Label_0092: {
                    if (!r) {
                        break Label_0092;
                    }
                    n += fontMetrics.stringWidth(String.valueOf(this.n.charAt(n2)));
                    Label_0089: {
                        if (!r) {
                            if (n < x) {
                                break Label_0089;
                            }
                            this.d.c(n2);
                        }
                        if (!r) {
                            break Label_0104;
                        }
                    }
                    ++n2;
                }
                if (n2 < this.p.length()) {
                    continue;
                }
                break;
            }
            this.requestFocus();
            this.repaint();
            if (!r) {
                return;
            }
            continue;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final boolean r = d.r;
        final Dimension size = this.getSize();
        d d = this;
        if (!r) {
            if (this.m == null) {
                this.m = this.createImage(size.width, size.height);
                this.l = this.m.getGraphics();
            }
            this.l.setFont(this.q);
            this.l.clearRect(0, 0, this.getSize().width, this.getSize().height);
            this.l.clearRect(0, 0, this.getSize().width, this.getSize().height);
            this.l.setColor(Color.gray);
            this.l.drawImage(this.a.A, 0, 0, 10, 20, 0, 0, 10, 20, this);
            this.l.drawImage(this.a.A, 10, 0, this.getSize().width - 10, 20, 10, 0, 20, 20, this);
            this.l.drawImage(this.a.A, this.getSize().width - 10, 0, this.getSize().width, 20, 20, 0, 30, 20, this);
            this.p = this.n.toString();
            d = this;
        }
        final boolean equals = d.i.equals("");
        if (!r) {
            if (!equals) {
                this.p = "";
                int n = 0;
                while (true) {
                    Label_0297: {
                        if (!r) {
                            break Label_0297;
                        }
                        this.p = String.valueOf(this.p) + this.i;
                        ++n;
                    }
                    if (n < this.n.length()) {
                        continue;
                    }
                    break;
                }
            }
            this.l.setColor(this.a.f);
            this.l.drawString(this.p, 2, 14);
            this.d.e = this.l.getFontMetrics().stringWidth(this.p.substring(0, sb.a(this.d)));
            this.d.a(this.l);
            graphics.drawImage(this.m, 0, 0, this);
        }
    }
    
    public void a(final String s) {
        this.n.setLength(0);
        this.n.append(s);
        this.d.c(s.length());
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
