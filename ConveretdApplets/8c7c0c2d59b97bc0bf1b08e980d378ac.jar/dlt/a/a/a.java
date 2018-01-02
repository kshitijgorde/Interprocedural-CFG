// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import java.awt.event.ComponentEvent;
import dlt.a.f.c;
import dlt.a.f.d;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

public class a extends Canvas
{
    private BufferedImage a;
    private Vector if;
    
    public a(final int n, final int n2) {
        this.setSize(new Dimension(n, n2));
        this.enableEvents(1L);
        this.if = new Vector();
    }
    
    public void a(final int n, final int n2) {
        this.a.getRaster().getDataBuffer().setElem(n, n2);
    }
    
    public void a(final String s) {
        if (this.a != null) {
            final Graphics graphics = this.a.getGraphics();
            graphics.setColor(Color.black);
            final Rectangle2D stringBounds = graphics.getFontMetrics().getStringBounds(s, graphics);
            graphics.drawString(s, (int)((this.getWidth() - stringBounds.getWidth()) / 2.0), (int)((this.getHeight() - stringBounds.getHeight()) / 2.0));
        }
    }
    
    public void a() {
        if (this.a != null) {
            this.getGraphics().drawImage(this.a, 0, 0, this);
        }
        this.if();
    }
    
    public void if(final d d) {
        this.if.addElement(d);
        d.a(new c(this, this.getWidth(), this.getHeight(), 0));
    }
    
    public void a(final d d) {
        this.if.removeElement(d);
    }
    
    private void if() {
        if (this.a == null) {
            if (this.getWidth() > 0 && this.getHeight() > 0) {
                this.a = (BufferedImage)this.createImage(this.getWidth(), this.getHeight());
            }
        }
        else {
            this.a.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
            for (int i = 0; i < this.if.size(); ++i) {
                ((d)this.if.elementAt(i)).a(new c(this, this.getWidth(), this.getHeight(), 1));
            }
        }
    }
    
    protected void processComponentEvent(final ComponentEvent componentEvent) {
        super.processComponentEvent(componentEvent);
        if (componentEvent.getID() == 101) {
            this.a = null;
            this.if();
            for (int i = 0; i < this.if.size(); ++i) {
                ((d)this.if.elementAt(i)).a(new c(this, this.getWidth(), this.getHeight(), 0));
            }
        }
    }
}
