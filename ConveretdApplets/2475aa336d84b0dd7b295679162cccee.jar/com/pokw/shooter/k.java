// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.net.URL;
import java.applet.Applet;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComponent;

public class k extends JComponent
{
    private static final Color b;
    private static final Color l;
    private static final Font a;
    private int g;
    private int f;
    private int e;
    private int h;
    private int o;
    private int d;
    private String j;
    private int k;
    private int c;
    private String q;
    private int i;
    private int p;
    private Rectangle s;
    private Applet r;
    private URL n;
    
    public k(final Applet r) {
        this.g = 0;
        this.f = 0;
        this.e = 0;
        this.h = 0;
        this.o = 0;
        this.d = 0;
        this.j = "Artwork By:";
        this.k = 0;
        this.c = 0;
        this.q = "Ari Feldman (ari@arifeldman.com)";
        this.i = 0;
        this.p = 0;
        this.s = new Rectangle();
        try {
            this.n = new URL("http://www.pokw.com");
        }
        catch (Exception ex) {
            this.n = null;
        }
        this.r = r;
        this.setBackground(com.pokw.shooter.k.b);
        this.setForeground(com.pokw.shooter.k.l);
        this.setFont(com.pokw.shooter.k.a);
        this.addMouseListener(new MouseAdapter() {
            private final k a = a;
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (com.pokw.shooter.k.a(this.a).contains(mouseEvent.getPoint())) {
                    if (com.pokw.shooter.k.c(this.a) != null && com.pokw.shooter.k.b(this.a) != null) {
                        com.pokw.shooter.k.c(this.a).getAppletContext().showDocument(com.pokw.shooter.k.b(this.a), "_blank");
                    }
                }
                else {
                    this.a.a();
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            private final k a = a;
            
            public void mouseMoved(final MouseEvent mouseEvent) {
                if (com.pokw.shooter.k.a(this.a).contains(mouseEvent.getPoint())) {
                    this.a.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    this.a.setCursor(Cursor.getDefaultCursor());
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            private final k a = a;
            
            public void keyPressed(final KeyEvent keyEvent) {
                this.a.a();
            }
        });
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Rectangle bounds = this.getBounds();
        if (this.g != bounds.width || this.f != bounds.height) {
            this.g = bounds.width;
            this.f = bounds.height;
            this.o = (this.g - 200) / 2;
            this.d = (this.f - 130) / 3;
            this.k = (this.g - graphics.getFontMetrics().stringWidth(this.j)) / 2;
            this.c = this.d + 130 + 60;
            this.i = (this.g - graphics.getFontMetrics().stringWidth(this.q)) / 2;
            this.p = this.c + 30;
            this.s.x = this.o;
            this.s.y = this.d;
            this.s.width = 200;
            this.s.height = 130;
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.g, this.f);
        graphics.setColor(this.getForeground());
        graphics.drawImage(com.pokw.shooter.y.X, this.o, this.d, this);
        graphics.drawString(this.j, this.k, this.c);
        graphics.drawString(this.q, this.i, this.p);
    }
    
    public void a() {
        throw new UnsupportedOperationException("CreditsScreen.goBack() method not supported");
    }
    
    static Rectangle a(final k k) {
        return k.s;
    }
    
    static Applet c(final k k) {
        return k.r;
    }
    
    static URL b(final k k) {
        return k.n;
    }
    
    static {
        b = new Color(0, 0, 192);
        l = Color.WHITE;
        a = new Font("Monospaced", 1, 12);
    }
}
