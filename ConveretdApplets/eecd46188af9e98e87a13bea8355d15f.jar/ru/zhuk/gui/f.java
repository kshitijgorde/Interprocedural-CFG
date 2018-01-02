// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

public class f extends Component
{
    public static int a;
    public static int h;
    public static int g;
    private Color j;
    private boolean d;
    private boolean b;
    private Image i;
    private int e;
    private boolean k;
    private ActionListener f;
    private String c;
    
    public f(final Color color, final Image i, final int e) {
        this.j = ((color != null) ? color : Color.lightGray);
        this.i = i;
        this.e = e;
        this.enableEvents(16L);
    }
    
    public Dimension getPreferredSize() {
        int n = 8;
        int n2 = 8;
        if (this.i != null) {
            n += this.i.getWidth(this);
            n2 += this.i.getHeight(this);
        }
        return new Dimension(n, n2);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public void a(final ActionListener f) {
        this.f = f;
    }
    
    public void a(final String c) {
        this.c = c;
    }
    
    public String b() {
        return this.c;
    }
    
    public void a(final boolean d) {
        this.d = d;
    }
    
    public void b(final boolean k) {
        if (k != this.k) {
            this.k = k;
            if ((this.k || this.e == ru.zhuk.gui.f.g) && this.f != null) {
                this.f.actionPerformed(new ActionEvent(this, 1001, this.c));
            }
            this.b = false;
            this.repaint();
        }
    }
    
    public boolean c() {
        return this.k;
    }
    
    public Image d() {
        return this.i;
    }
    
    public int a() {
        return this.e;
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        if (!mouseEvent.isConsumed()) {
            switch (mouseEvent.getID()) {
                case 501: {
                    if (this.e != ru.zhuk.gui.f.g) {
                        this.b(true);
                        break;
                    }
                    this.b(!this.c());
                    break;
                }
                case 502: {
                    if (this.e == ru.zhuk.gui.f.a) {
                        this.b(false);
                        break;
                    }
                    break;
                }
                case 504: {
                    this.b = true;
                    this.repaint();
                    break;
                }
                case 505: {
                    this.b = false;
                    this.repaint();
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x1) != 0x0 && (n & 0x2) != 0x0) {
            this.invalidate();
        }
        final boolean b = (n & 0x20) != 0x0;
        if (b) {
            this.repaint();
        }
        return !b;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (!this.d) {
            final Dimension dimension = size;
            --dimension.width;
            final Dimension dimension2 = size;
            --dimension2.height;
        }
        graphics.setColor(this.j);
        graphics.fillRect(0, 0, size.width, size.height);
        if (this.i != null) {
            final int width = this.i.getWidth(this);
            final int height = this.i.getHeight(this);
            if (width > 0 && height > 0) {
                int n = (size.width - width) / 2;
                int n2 = (size.height - height) / 2;
                if (n < 0 || n2 < 0) {
                    final double min = Math.min(size.width / width, size.height / height);
                    final int n3 = (int)(width * min);
                    final int n4 = (int)(height * min);
                    int n5 = (size.width - n3) / 2;
                    int n6 = (size.height - n4) / 2;
                    if (this.c()) {
                        ++n5;
                        ++n6;
                    }
                    graphics.drawImage(this.i, n5, n6, n3, n4, this);
                }
                else {
                    if (this.c()) {
                        ++n;
                        ++n2;
                    }
                    graphics.drawImage(this.i, n, n2, this);
                }
            }
        }
        if (!this.d) {
            graphics.setColor(Color.white);
            graphics.drawRect(0, 0, size.width, size.height);
            graphics.setColor(Color.lightGray);
            graphics.drawRect(1, 1, size.width - 2, size.height - 2);
            graphics.setColor(Color.black);
            if (this.c()) {
                graphics.drawLine(0, 0, size.width - 1, 0);
                graphics.drawLine(0, 0, 0, size.height - 1);
                graphics.setColor(Color.gray);
                graphics.drawLine(1, 1, size.width - 2, 1);
                graphics.drawLine(1, 1, 1, size.height - 2);
            }
            else {
                graphics.drawLine(0, size.height, size.width, size.height);
                graphics.drawLine(size.width, 0, size.width, size.height);
                graphics.setColor(Color.gray);
                graphics.drawLine(1, size.height - 1, size.width - 1, size.height - 1);
                graphics.drawLine(size.width - 1, 1, size.width - 1, size.height - 1);
            }
        }
        else if (this.b && !this.c()) {
            graphics.setColor(Color.white);
            graphics.setXORMode(Color.black);
            graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        }
    }
    
    static {
        f.a = 0;
        f.h = 1;
        f.g = 2;
    }
}
