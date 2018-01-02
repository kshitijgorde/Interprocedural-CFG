// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Panel;

public final class E extends Panel
{
    private String q;
    private boolean q;
    private boolean w;
    Image q;
    private int q;
    private int w;
    private int e;
    private Rectangle q;
    private Rectangle w;
    private boolean e;
    private boolean r;
    private boolean t;
    private bE q;
    private TextArea q;
    
    public E(final int n, final int n2, final TextArea q, final Color background) {
        this(null, 25, 25);
        this.q = q;
        this.t = true;
        if ((cs.q == 0 && cs.t) || (cs.q == 1 && cs.y)) {
            final Canvas canvas;
            (canvas = new Canvas()).setSize(0, 0);
            this.q = new bE(q, canvas, new Color(238, 238, 238));
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.gridheight = -1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 1, 0, 1);
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
            this.q.setSize(10, 18);
            this.q.q(this.q.getForeground());
            this.add(canvas);
        }
        this.setBackground(background);
    }
    
    private E(final String s, final int n, int e) {
        this.q = null;
        this.q = false;
        this.w = false;
        this.q = null;
        this.t = false;
        this.e = false;
        this.r = false;
        this.q = null;
        this.w = null;
        this.e = this.getFontMetrics(bd.w).getHeight() + 7;
        final String q = null;
        if (this.q == null || (q != null && !q.equals(this.q))) {
            this.q = q;
            this.repaint();
        }
        this.setFont(bd.w);
        this.setBackground(ah.w);
        this.setForeground(Color.black);
        if (e < this.e) {
            e = this.e;
        }
        this.resize(n, e);
    }
    
    public final void resize(final int w, int e) {
        if (e < this.e) {
            e = this.e;
        }
        super.resize(w, e);
        this.q = e;
        this.w = w;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.w, this.q);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    private void q(final Graphics graphics) {
        final Dimension size;
        final int width = (size = this.size()).width;
        final int height = size.height;
        if (this.q != null) {
            final int width2 = this.q.getWidth(this);
            final int height2 = this.q.getHeight(this);
            if (this.e) {
                graphics.setColor(new Color(12702446));
            }
            else {
                graphics.setColor(new Color(14804712));
            }
            graphics.fillRect(this.q.x, this.q.y, this.q.width, this.q.height);
            graphics.setColor(new Color(3238597));
            graphics.drawRect(this.q.x, this.q.y, this.q.width - 1, this.q.height - 1);
            if (this.r) {
                graphics.setColor(new Color(12702446));
            }
            else {
                graphics.setColor(new Color(14804712));
            }
            graphics.fillRect(this.w.x, this.w.y, this.w.width, this.w.height);
            graphics.setColor(new Color(3238597));
            graphics.drawRect(this.w.x, this.w.y, this.w.width - 1, this.w.height - 1);
            if (width2 > 0 && height2 > 0) {
                graphics.drawImage(this.q, (width - width2) / 2, (height - height2) / 2, this);
                return;
            }
            graphics.drawImage(this.q, -2, -2, 1, 1, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final Dimension size;
            final int n = (size = this.size()).width - 1;
            final int n2 = size.height - 1;
            this.getBackground();
            final Color foreground = this.getForeground();
            final Color color = new Color(238, 238, 238);
            final Dimension size2;
            final int n3 = (size2 = this.size()).width - 1;
            final int n4 = size2.height - 1;
            if (!(this.getParent() instanceof Z)) {
                graphics.setColor(color.darker());
                graphics.drawRoundRect(0, 0, n3, n4, 6, 6);
            }
            graphics.setColor(color);
            graphics.fillRoundRect(1, 1, n3 - 1, n4 - 1, 6, 6);
            if (this.q != null) {
                final int width = this.q.getWidth(this);
                final int height = this.q.getHeight(this);
                if (this.t) {
                    this.q = new Rectangle(4, 3, 18, 18);
                    this.w = new Rectangle(this.q.x + this.q.width + 1, this.q.y, 18, 18);
                    this.q(graphics);
                }
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.q, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.q, -2, -2, 1, 1, this);
                }
            }
            if (this.q != null) {
                final FontMetrics fontMetrics;
                final int stringWidth = (fontMetrics = graphics.getFontMetrics()).stringWidth(this.q);
                final int height2 = fontMetrics.getHeight();
                final int ascent = fontMetrics.getAscent();
                graphics.setColor(foreground);
                graphics.drawString(this.q, (n - stringWidth) / 2 - 1, n2 / 2 + (ascent - height2 / 2));
            }
            final Point locationOnScreen;
            final Point point = locationOnScreen = this.getLocationOnScreen();
            point.x += 50;
            final Point point2 = locationOnScreen;
            point2.y -= 28;
            if (this.q != null) {
                this.q.q = locationOnScreen;
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final Graphics graphics = this.getGraphics();
        boolean inside = this.inside(event.x, event.y);
        switch (event.id) {
            case 501: {
                this.w = inside;
                this.q = true;
                if (this.q != null && this.q.inside(event.x, event.y)) {
                    if (cg.q('b', this.q)) {
                        this.e = !this.e;
                    }
                    if (graphics != null) {
                        this.q(graphics);
                    }
                }
                if (this.q != null && this.w.inside(event.x, event.y)) {
                    if (cg.q('i', this.q)) {
                        this.r = !this.r;
                    }
                    if (graphics != null) {
                        this.q(graphics);
                    }
                }
                if (graphics != null) {
                    break;
                }
                break;
            }
            case 502: {
                if (!this.q) {
                    break;
                }
                this.w = inside;
                this.q = false;
                if (this.w) {
                    this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, this.q));
                    break;
                }
                break;
            }
            case 505: {
                inside = false;
            }
            case 504:
            case 506: {
                if (this.w != inside) {
                    this.w = inside;
                    this.w = inside;
                    break;
                }
                break;
            }
        }
        if (graphics != null) {
            graphics.dispose();
        }
        return super.handleEvent(event);
    }
}
