// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;

abstract class l extends JPanel
{
    private static final Color d;
    private static final Color h;
    private static final Font b;
    private final Color p;
    private final int a = 150;
    private final int k = 30;
    private final int l = 400;
    private final int n = 203;
    private int o;
    private int e;
    private int f;
    private Rectangle i;
    private Rectangle m;
    private Rectangle g;
    private Rectangle c;
    
    public l() {
        this.p = new Color(0, 0, 192);
        this.o = 0;
        this.e = 0;
        this.f = 0;
        this.i = new Rectangle();
        this.m = new Rectangle();
        this.g = new Rectangle();
        this.c = new Rectangle();
        this.setRequestFocusEnabled(true);
        this.setFont(com.pokw.shooter.l.b);
        this.setBackground(com.pokw.shooter.l.d);
        this.setForeground(com.pokw.shooter.l.h);
        this.addMouseListener(new MouseAdapter() {
            private final l a = a;
            
            public void mousePressed(final MouseEvent mouseEvent) {
                if (!this.a.hasFocus()) {
                    this.a.requestFocus();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            private final l a = a;
            
            public void keyPressed(final KeyEvent keyEvent) {
                final int keyCode = keyEvent.getKeyCode();
                if (keyCode == 40) {
                    com.pokw.shooter.y.a();
                    com.pokw.shooter.l.d(this.a);
                    if (com.pokw.shooter.l.c(this.a) > 2) {
                        com.pokw.shooter.l.a(this.a, 0);
                    }
                }
                else if (keyCode == 38) {
                    com.pokw.shooter.y.a();
                    com.pokw.shooter.l.b(this.a);
                    if (com.pokw.shooter.l.c(this.a) < 0) {
                        com.pokw.shooter.l.a(this.a, 2);
                    }
                }
                else if (keyCode == 10) {
                    com.pokw.shooter.y.a();
                    com.pokw.shooter.l.a(this.a);
                }
                this.a.repaint();
            }
        });
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Rectangle bounds = this.getBounds();
        if (this.f != bounds.width || this.e != bounds.height) {
            this.f = bounds.width;
            this.e = bounds.height;
            this.c.x = (this.f - 400) / 2;
            this.c.y = (this.e - 203) / 3;
            this.c.width = 400;
            this.c.height = 203;
            this.i.x = (this.f - 150) / 2;
            this.i.y = this.c.y + this.c.height + 20;
            this.i.width = 150;
            this.i.height = 30;
            this.m.x = this.i.x;
            this.m.y = this.i.y + 30;
            this.m.width = 150;
            this.m.height = 30;
            this.g.x = this.m.x;
            this.g.y = this.m.y + 30;
            this.g.width = 150;
            this.g.height = 30;
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.f, this.e);
        graphics.setColor(this.getForeground());
        graphics.drawImage(com.pokw.shooter.y.h, this.c.x, this.c.y, null);
        graphics.drawString("Created by: PokW.com", 20, 20);
        graphics.drawString("Artwork by: Ari Feldman", 20, 40);
        if (this.o == 0) {
            graphics.drawImage(com.pokw.shooter.y.K, this.i.x, this.i.y, this);
            graphics.drawImage(com.pokw.shooter.y.Q, this.m.x, this.m.y, this);
            graphics.drawImage(com.pokw.shooter.y.V, this.g.x, this.g.y, this);
        }
        else if (this.o == 1) {
            graphics.drawImage(com.pokw.shooter.y.S, this.i.x, this.i.y, this);
            graphics.drawImage(com.pokw.shooter.y.t, this.m.x, this.m.y, this);
            graphics.drawImage(com.pokw.shooter.y.V, this.g.x, this.g.y, this);
        }
        else if (this.o == 2) {
            graphics.drawImage(com.pokw.shooter.y.S, this.i.x, this.i.y, this);
            graphics.drawImage(com.pokw.shooter.y.Q, this.m.x, this.m.y, this);
            graphics.drawImage(com.pokw.shooter.y.j, this.g.x, this.g.y, this);
        }
    }
    
    public abstract void a();
    
    public abstract void d();
    
    public abstract void c();
    
    private void b() {
        if (this.o == 0) {
            this.a();
        }
        else if (this.o == 1) {
            this.d();
        }
        else if (this.o == 2) {
            this.c();
        }
    }
    
    static int d(final l l) {
        return l.o++;
    }
    
    static int c(final l l) {
        return l.o;
    }
    
    static int a(final l l, final int o) {
        return l.o = o;
    }
    
    static int b(final l l) {
        return l.o--;
    }
    
    static void a(final l l) {
        l.b();
    }
    
    static {
        d = new Color(0, 0, 192);
        h = Color.WHITE;
        b = new Font("SansSerif", 0, 11);
    }
}
