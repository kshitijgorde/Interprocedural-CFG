// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Canvas;

public class m extends Canvas
{
    private boolean char;
    private Image int;
    private int if;
    private int for;
    private boolean case;
    private String a;
    private ActionListener do;
    private Rectangle try;
    private String new;
    private boolean byte;
    
    public m(final Image int1, final int for1, final int if1, final ActionListener do1, final String new1, final boolean byte1) {
        this.byte = byte1;
        this.new = new1;
        this.case = false;
        this.char = false;
        this.if = if1;
        this.for = for1;
        this.a = null;
        this.do = do1;
        try {
            this.int = int1;
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.int, 0);
            if (!mediaTracker.waitForAll(2000L)) {
                System.out.println("MediaTracker timed out (menu icon)");
            }
            if (mediaTracker.isErrorAny()) {
                this.int = null;
            }
        }
        catch (Throwable t) {
            this.int = null;
        }
        this.try = new Rectangle();
        this.try.height = this.if;
        this.try.width = this.for;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.metaDown() || !this.try.contains(n, n2)) {
            return false;
        }
        if (!this.char) {
            this.char = true;
            this.case = this.byte;
            this.repaint();
        }
        else {
            this.case = false;
        }
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (event.metaDown()) {
            return false;
        }
        if (this.char && !this.case) {
            this.char = false;
            this.repaint();
            this.a(this.a);
        }
        else if (this.char && this.case) {
            this.a(this.a);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.new != null) {
            this.a("1000;" + this.new);
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.a(this.char, graphics);
    }
    
    private void a(final boolean b, final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (width == 0 || height == 0) {
            return;
        }
        try {
            final Image image = this.createImage(width, height);
            if (image == null) {
                return;
            }
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(this.getBackground());
            final int x = (width - this.for) / 2;
            final int y = (height - this.if) / 2;
            graphics2.draw3DRect(x, y, this.for - 1, this.if - 1, !b);
            this.try.x = x;
            this.try.y = y;
            if (this.int != null) {
                int n = (width - this.int.getWidth(this)) / 2;
                final int n2 = (height - this.int.getHeight(this)) / 2;
                if (b) {
                    ++n;
                }
                graphics2.drawImage(this.int, n, n2, this);
            }
            graphics.drawImage(image, 0, 0, this);
            graphics2.dispose();
        }
        catch (Throwable t) {}
    }
    
    public void if(final String a) {
        this.a = a;
    }
    
    public String a() {
        return this.a;
    }
    
    private void a(final String s) {
        if (this.do != null) {
            this.do.actionPerformed(new ActionEvent(this, 0, s));
        }
    }
    
    public void a(final boolean char1) {
        this.char = char1;
        this.repaint();
    }
    
    public boolean if() {
        return this.char;
    }
}
