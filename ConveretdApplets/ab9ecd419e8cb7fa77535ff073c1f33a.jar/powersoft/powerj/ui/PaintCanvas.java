// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.ui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import powersoft.powerj.event.EventListener;
import powersoft.powerj.event.PaintListener;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import powersoft.powerj.event.PaintEvent;
import java.awt.Component;
import java.awt.Graphics;
import powersoft.powerj.event.EventListenerVector;
import java.awt.Canvas;

public class PaintCanvas extends Canvas
{
    static final long serialVersionUID = 5741527261760744957L;
    protected boolean _erase;
    protected transient EventListenerVector _paintVector;
    protected boolean _doubleBuffered;
    
    public PaintCanvas() {
        this._erase = true;
        this._paintVector = null;
        this._paintVector = new EventListenerVector();
    }
    
    public boolean getEraseBackground() {
        return this._erase;
    }
    
    public void setEraseBackground(final boolean erase) {
        this._erase = erase;
    }
    
    public boolean isDoubleBuffered() {
        return this._doubleBuffered;
    }
    
    public void setDoubleBuffered(final boolean doubleBuffered) {
        this._doubleBuffered = doubleBuffered;
    }
    
    public void paint(final Graphics gr) {
        Image buffer = null;
        Graphics g = gr;
        final Dimension s = this.getSize();
        if (this._doubleBuffered) {
            buffer = UiUtil.getOffscreenBuffer(this, s.width, s.height);
        }
        if (buffer != null) {
            g = buffer.getGraphics();
            if (g != null) {
                g.clipRect(0, 0, s.width, s.height);
            }
            else {
                g = gr;
            }
        }
        g.setFont(this.getFont());
        if (this._erase) {
            final Rectangle r = g.getClipBounds();
            if (r != null) {
                g.setColor(this.getBackground());
                g.fillRect(r.x, r.y, r.width, r.height);
            }
        }
        g.setColor(this.getForeground());
        final PaintEvent p = PaintEvent.allocate(this, g);
        this.handlePaint(p);
        PaintEvent.deallocate(p);
        if (buffer != null && g != gr) {
            gr.drawImage(buffer, 0, 0, this);
        }
        if (g != gr) {
            g.dispose();
        }
    }
    
    public synchronized void addPaintListener(final PaintListener listener) {
        this._paintVector = this._paintVector.addListener(listener);
    }
    
    public synchronized EventListenerVector getPaintListeners() {
        return (EventListenerVector)this._paintVector.clone();
    }
    
    public synchronized void handlePaint(final PaintEvent data) {
        final EventListenerVector v = this._paintVector;
        try {
            v.reference();
            final Enumeration e = v.elements();
            while (e.hasMoreElements()) {
                e.nextElement().paint(data);
                if (data.getHandled()) {
                    break;
                }
            }
        }
        finally {
            v.unreference();
        }
    }
    
    public synchronized void removePaintListener(final PaintListener listener) {
        this._paintVector = this._paintVector.removeListener(listener);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this._paintVector = new EventListenerVector();
    }
}
