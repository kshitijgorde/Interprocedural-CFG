// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.esial.util.d;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import com.diginet.digichat.util.l;
import com.diginet.digichat.common.bd;
import com.diginet.digichat.util.n;
import java.awt.Panel;

public class b1 extends Panel
{
    private n a;
    protected int b;
    protected int c;
    private b2 d;
    protected b3 e;
    protected b3 f;
    
    public void a() {
        this.a.c();
    }
    
    public void a(final bd bd) {
        this.a.a(bd);
    }
    
    public void a(final int n) {
        this.b(this.a.b(n));
    }
    
    public void b(final int b) {
        final int b2 = this.b;
        this.b = b;
        final int d = this.d();
        if (d == 0) {
            return;
        }
        final int c = d * (this.b / d);
        if (c != this.c) {
            this.c = c;
            this.d.repaint();
        }
        else {
            this.d.c(b2 - c);
            this.d.d(this.b - c);
        }
    }
    
    public bd b() {
        return (bd)this.a.c(this.b);
    }
    
    protected Dimension c() {
        final Dimension size = this.d.size();
        int n = size.width / 43;
        int n2 = size.height / 41;
        final int b = this.a.b();
        if (n * n2 > b) {
            for (int i = (int)Math.ceil(Math.sqrt(b)); i < n; ++i) {
                final int n3 = (int)Math.ceil(b / i);
                if (n3 <= n2) {
                    n = i;
                    n2 = n3;
                    break;
                }
            }
        }
        return new Dimension(n, n2);
    }
    
    protected int d() {
        final Dimension c = this.c();
        return c.height * c.width;
    }
    
    public void resize(final int n, final int n2) {
        this.d.resize(n - 2 * this.e.size().width, n2);
    }
    
    public boolean action(final Event event, final Object o) {
        final int d = this.d();
        final int b = this.a.b();
        if (event.target == this.f) {
            this.c += d;
        }
        else {
            this.c -= d;
        }
        if (this.c >= b) {
            this.c = 0;
        }
        else if (this.c < 0) {
            this.c = b - 1 - (b - 1) % d;
        }
        final Graphics graphics = this.d.getGraphics();
        final Dimension size = this.d.size();
        graphics.setColor(this.d.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.d.repaint();
        return true;
    }
    
    public b1() {
        this.a = new n();
        this.b = -1;
        this.c = 0;
        this.d = new Canvas() {
            void a(final int n) {
                final Graphics graphics = this.getGraphics();
                if (graphics != null) {
                    this.a(graphics, n);
                    graphics.dispose();
                }
            }
            
            Rectangle b(final int n) {
                final Dimension size = this.size();
                final Dimension c = b1.this.c();
                final int width = c.width;
                return new Rectangle((size.width + 6 - width * 43) / 2 + n % width * 43, (size.height + 6 - c.height * 41) / 2 + n / width * 41, 37, 35);
            }
            
            void c(final int n) {
                final Graphics graphics = this.getGraphics();
                if (graphics != null) {
                    final Rectangle b = this.b(n);
                    int x = b.x;
                    int y = b.y;
                    int height = b.height;
                    int width = b.width;
                    graphics.setColor(this.getBackground());
                    for (int i = 0; i <= 4; ++i) {
                        graphics.drawRect(x, y, width, height);
                        ++x;
                        ++y;
                        height -= 2;
                        width -= 2;
                    }
                    graphics.dispose();
                }
            }
            
            public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x20) != 0x0) {
                    this.repaint();
                    return false;
                }
                return true;
            }
            
            void d(final int n) {
                final Graphics graphics = this.getGraphics();
                if (graphics != null) {
                    final Rectangle b = this.b(n);
                    final int x = b.x;
                    final int y = b.y;
                    final int n2 = x + b.width;
                    final int n3 = y + b.height;
                    final Color color = new Color(115, 115, 115);
                    graphics.setColor(Color.black);
                    graphics.drawLine(x, n3 - 2, x, y + 2);
                    graphics.drawLine(x, y + 2, x + 2, y);
                    graphics.drawLine(x + 2, y, n2 - 2, y);
                    graphics.drawLine(n2 - 2, y, n2, y + 2);
                    graphics.drawLine(n2, y + 2, n2, n3 - 2);
                    graphics.drawLine(n2, n3 - 2, n2 - 2, n3);
                    graphics.drawLine(n2 - 2, n3, x + 2, n3);
                    graphics.drawLine(x + 2, n3, x, n3 - 2);
                    graphics.drawLine(x + 5, y + 4, n2 - 5, y + 4);
                    graphics.drawLine(n2 - 4, y + 5, n2 - 4, n3 - 5);
                    graphics.drawLine(n2 - 5, n3 - 4, x + 5, n3 - 4);
                    graphics.drawLine(x + 4, n3 - 5, x + 4, y + 5);
                    graphics.setColor(Color.white);
                    graphics.drawLine(x + 1, n3 - 2, x + 1, y + 2);
                    graphics.drawLine(x + 2, y + 1, n2 - 2, y + 1);
                    graphics.drawLine(n2 - 2, y + 5, n2 - 2, n3 - 5);
                    graphics.drawLine(n2 - 2, n3 - 5, n2 - 4, n3 - 3);
                    graphics.drawLine(n2 - 4, n3 - 3, x + 5, n3 - 3);
                    graphics.setColor(color);
                    graphics.drawLine(n2 - 1, y + 2, n2 - 1, n3 - 2);
                    graphics.drawLine(n2 - 2, n3 - 1, x + 2, n3 - 1);
                    graphics.drawLine(x + 4, n3 - 4, x + 3, n3 - 5);
                    graphics.drawLine(x + 3, n3 - 5, x + 3, y + 5);
                    graphics.drawLine(x + 3, y + 5, x + 5, y + 3);
                    graphics.drawLine(x + 5, y + 3, n2 - 5, y + 3);
                    graphics.drawLine(n2 - 5, y + 3, n2 - 4, y + 4);
                    graphics.dispose();
                }
            }
            
            void a(final Graphics graphics, final int n) {
                final Image a = ((bd)b1.this.a.c(n)).a;
                final Rectangle b = this.b(n - b1.this.c);
                graphics.drawImage(a, 1 + b.x + (b.width - 24) / 2, 1 + b.y + (b.height - 24) / 2, this);
            }
            
            public void paint(final Graphics graphics) {
                if (this.isShowing()) {
                    final int b = b1.this.a.b();
                    int d = b1.this.d();
                    if (b <= d) {
                        b1.this.c = 0;
                        d = b;
                        b1.this.e.c();
                        b1.this.f.c();
                    }
                    else {
                        b1.this.e.b();
                        b1.this.f.b();
                    }
                    int n = b1.this.c + d;
                    if (n > b) {
                        n = b;
                    }
                    if (b1.this.b >= b1.this.c && b1.this.b < n) {
                        this.d(b1.this.b - b1.this.c);
                    }
                    for (int i = b1.this.c; i < n; ++i) {
                        this.a(i);
                    }
                }
            }
            
            public void update(final Graphics graphics) {
                this.paint(graphics);
            }
            
            public boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 501:
                    case 506: {
                        final Dimension size = this.size();
                        final Dimension c = b1.this.c();
                        final int b = b1.this.a.b();
                        final int width = c.width;
                        final int height = c.height;
                        final int n = (size.width + 6 - width * 43) / 2;
                        int n2 = (event.y - (size.height + 6 - height * 41) / 2) / 41;
                        int n3 = (event.x - n) / 43;
                        if (n3 >= width) {
                            n3 = width - 1;
                        }
                        else if (n3 < 0) {
                            n3 = 0;
                        }
                        if (n2 >= height) {
                            n2 = height - 1;
                        }
                        else if (n2 < 0) {
                            n2 = 0;
                        }
                        final int n4 = n2 * width + n3 + b1.this.c;
                        if (n4 < b && n4 != b1.this.b) {
                            b1.this.b(n4);
                        }
                        return true;
                    }
                    default: {
                        return super.handleEvent(event);
                    }
                }
            }
            
            public Dimension minimumSize() {
                return this.a(1, 1);
            }
            
            public Dimension a(final int n, final int n2) {
                return new Dimension(37 * n, 35 * n2);
            }
            
            {
                this.resize(50, 50);
            }
        };
        this.e = new b3(16, 30, 0);
        this.f = new b3(16, 30, 1);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        this.e.a(com.esial.util.d.a("Click here to view more icons."), null);
        this.f.a(com.esial.util.d.a("Click here to view more icons."), null);
    }
}