// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import com.cc.applet.actions.GrayFilter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.FontMetrics;
import java.awt.Dimension;
import com.cc.gui.E;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.util.Date;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class A extends Canvas implements Runnable, MouseListener
{
    Font font;
    public String U;
    private String Q;
    public String S;
    public Image Y;
    public MediaTracker G;
    boolean C;
    public boolean I;
    boolean V;
    String[] M;
    int E;
    int A;
    private transient Thread P;
    private long F;
    public int O;
    public boolean L;
    public boolean D;
    int Z;
    private boolean K;
    private boolean R;
    public boolean J;
    private String T;
    private String B;
    public int W;
    public int H;
    private Image X;
    private String N;
    
    public void B(final String t) {
        this.T = t;
    }
    
    public void A(final int o) {
        this.Z = o * 1000;
        this.O = o;
    }
    
    public A() {
        this.font = null;
        this.Q = null;
        this.S = null;
        this.Y = null;
        this.G = null;
        this.C = false;
        this.I = false;
        this.V = false;
        this.A = 0;
        this.P = null;
        this.F = 0L;
        this.O = 0;
        this.L = false;
        this.D = false;
        this.Z = 0;
        this.K = false;
        this.J = true;
        this.T = "";
        this.B = "";
        this.X = null;
        this.C = true;
        this.addMouseListener(this);
    }
    
    public A(final Image y) {
        this.font = null;
        this.Q = null;
        this.S = null;
        this.Y = null;
        this.G = null;
        this.C = false;
        this.I = false;
        this.V = false;
        this.A = 0;
        this.P = null;
        this.F = 0L;
        this.O = 0;
        this.L = false;
        this.D = false;
        this.Z = 0;
        this.K = false;
        this.J = true;
        this.T = "";
        this.B = "";
        this.X = null;
        this.Y = y;
        (this.G = new MediaTracker(this)).addImage(y, 0);
        this.addMouseListener(this);
    }
    
    public void A() {
        if (this.P == null) {
            this.F = new Date().getTime() - this.Z;
            (this.P = new Thread(this)).start();
            this.Z = 0;
            this.V = false;
        }
    }
    
    public void run() {
        Label_0075: {
            if (this.G != null) {
                try {
                    this.G.waitForAll();
                    this.G = null;
                    this.repaint();
                    break Label_0075;
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
            if (this.P != null) {
                while (!this.I && !this.V) {
                    try {
                        Thread.sleep(1000L);
                        this.update(this.getGraphics());
                    }
                    catch (Exception ex2) {
                        this.I = true;
                    }
                }
            }
        }
        this.P = null;
    }
    
    public A(final String t, final String b) {
        this.font = null;
        this.Q = null;
        this.S = null;
        this.Y = null;
        this.G = null;
        this.C = false;
        this.I = false;
        this.V = false;
        this.A = 0;
        this.P = null;
        this.F = 0L;
        this.O = 0;
        this.L = false;
        this.D = false;
        this.Z = 0;
        this.K = false;
        this.J = true;
        this.T = "";
        this.B = "";
        this.X = null;
        this.U = t + b;
        this.B = b;
        if (b.length() > 0) {
            this.setCursor(new Cursor(12));
        }
        this.T = t;
        this.addMouseListener(this);
    }
    
    public A(final String s) {
        this("", s);
    }
    
    private static final String B(final int n) {
        String s = Integer.toString(n);
        if (s.length() < 2) {
            s = '0' + s;
        }
        return s;
    }
    
    private Color C() {
        return (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130) ? Color.white : Color.black;
    }
    
    private Color B() {
        if (this.K) {
            return (this.getBackground().getGreen() < 160 && this.getBackground().getRed() < 160) ? Color.white : Color.blue;
        }
        return this.C();
    }
    
    public void paint(final Graphics graphics) {
        if (this.X == null) {
            this.X = this.createImage(this.getBounds().width, this.getBounds().height);
        }
        final Graphics graphics2 = this.X.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics2.setColor(this.C());
        this.A(graphics2);
        graphics.drawImage(this.X, 0, 0, this);
        graphics2.dispose();
    }
    
    private void A(final Graphics graphics) {
        com.cc.gui.E.A(graphics);
        if (this.Y != null) {
            graphics.drawImage(this.Y, 0, 0, this);
            if (this.U != null) {
                if (this.isEnabled()) {
                    graphics.setColor(this.B());
                }
                else {
                    graphics.setColor(Color.gray);
                }
                graphics.setFont(this.getFont());
                graphics.drawString(this.U, this.Y.getWidth(this) + 3, this.Y.getHeight(this) / 2 + graphics.getFontMetrics().getHeight() / 4);
                if (this.R) {
                    graphics.drawRect(0, 0, this.getBounds().width - 1, this.getBounds().height - 1);
                }
            }
        }
        else if (this.C) {
            if (!this.I && !this.V && this.P != null) {
                this.O = (int)((new Date().getTime() - this.F) / 1000L);
            }
            String s = B(this.O % 3600 / 60) + ':' + B(this.O % 60);
            if (this.O > 3600) {
                s = Integer.toString(this.O / 3600) + ':' + s;
            }
            final int stringWidth = graphics.getFontMetrics().stringWidth(s);
            if (this.V) {
                graphics.setColor(Color.gray);
            }
            graphics.drawString(s, this.L ? ((this.getSize().width - stringWidth) / 2) : (this.D ? (this.getSize().width - stringWidth) : 0), this.W);
        }
        else if (this.A != 0) {
            final Dimension size = this.getSize();
            graphics.setColor(Color.red);
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(Color.white);
            graphics.fillRect(4, 4, size.width - 8, size.height - 8);
            graphics.setColor(Color.black);
            graphics.setFont(this.font);
            for (int i = 0; i < this.A; ++i) {
                if (this.M[i] != null) {
                    graphics.drawString(this.M[i], 20, 15 + (i + 1) * this.E);
                }
            }
        }
        else {
            final int stringWidth2 = graphics.getFontMetrics().stringWidth(this.T);
            graphics.setColor(this.C());
            graphics.drawString(this.T, 0, this.W);
            graphics.setColor(this.B());
            graphics.drawString(this.B, stringWidth2, this.W);
            if (this.K) {
                graphics.drawLine(stringWidth2, this.W + 1, this.H, this.W + 1);
            }
        }
    }
    
    public void A(final String q) {
        this.Q = q;
        if (q != null) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public A(final String s, final Font font) {
        this.font = null;
        this.Q = null;
        this.S = null;
        this.Y = null;
        this.G = null;
        this.C = false;
        this.I = false;
        this.V = false;
        this.A = 0;
        this.P = null;
        this.F = 0L;
        this.O = 0;
        this.L = false;
        this.D = false;
        this.Z = 0;
        this.K = false;
        this.J = true;
        this.T = "";
        this.B = "";
        this.X = null;
        String substring = s;
        this.A = 0;
        int n = 20;
        int n2 = 0;
        this.font = font;
        if (this.font.getSize() < 13) {
            this.font = new Font(this.font.getName(), 1, 13);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.font);
        this.E = fontMetrics.getHeight() + 4;
        this.M = new String[50];
        this.A = 0;
        while (substring != null) {
            final int index = substring.indexOf("\n");
            String substring2;
            if (index != -1) {
                substring2 = substring.substring(0, index);
            }
            else {
                substring2 = substring;
            }
            this.M[this.A] = substring2;
            ++this.A;
            final int stringWidth = fontMetrics.stringWidth(substring2);
            if (stringWidth > n2) {
                n2 = stringWidth;
            }
            n += this.E;
            if (index != -1 && index + 1 < substring.length()) {
                substring = substring.substring(index + 1);
            }
            else {
                substring = null;
            }
        }
        final int n3 = n;
        n2 += 40;
        this.setSize(n2, n3 + 20);
        this.addMouseListener(this);
    }
    
    public void C(final String n) {
        this.N = n;
    }
    
    public void A(final C c) {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (com.cc.applet.A.this.N != null) {
                    c.actionPerformed(new ActionEvent(com.cc.applet.A.this, 0, com.cc.applet.A.this.N));
                }
            }
        });
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        if (this.Y != null) {
            this.Y = GrayFilter.A(this.Y);
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.Q != null) {
            ((Grid_int)this.getParent()).showProcessedURL(this.Q, this.S, false, false);
        }
        else if (this.C && !this.I && this.J) {
            this.V = !this.V;
            this.Z = this.O * 1000;
            this.update(this.getGraphics());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.K = (this.B != null);
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.K = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public boolean D() {
        return this.R;
    }
    
    public void A(final boolean r) {
        this.R = r;
        this.repaint();
    }
}
