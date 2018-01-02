// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Canvas;

public class CanvasButton extends Canvas
{
    protected Rectangle inside;
    protected Image img;
    protected Vector listeners;
    protected boolean button_push_state;
    
    public CanvasButton(final Image img) {
        this();
        this.img = img;
    }
    
    public CanvasButton() {
        this.button_push_state = false;
        this.img = null;
        this.listeners = new Vector();
        this.button_push_state = false;
        this.setBackground(new Color(192, 192, 192));
    }
    
    public void paint(final Graphics graphics) {
        try {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
            if (this.img != null) {
                graphics.drawImage(this.img, 2, 2, null);
            }
            if (this.button_push_state) {
                this.paintBorderIn(graphics);
                return;
            }
            this.paintBorderOut(graphics);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void paintBorderOut(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        final Color brighter = this.getBackground().brighter();
        final Color darker = brighter.darker();
        final Color darker2 = darker.darker();
        final Color darker3 = darker2.darker();
        graphics.setColor(brighter);
        graphics.drawLine(0, 0, bounds.width - 1, 0);
        graphics.drawLine(0, 0, 0, bounds.height - 1);
        graphics.setColor(darker);
        graphics.drawLine(1, 1, bounds.width - 2, 1);
        graphics.drawLine(1, 1, 1, bounds.height - 2);
        graphics.setColor(darker3);
        graphics.drawLine(bounds.width - 2, 1, bounds.width - 2, bounds.height - 2);
        graphics.drawLine(1, bounds.height - 2, bounds.width - 2, bounds.height - 2);
        graphics.setColor(darker2);
        graphics.drawLine(bounds.width - 1, 1, bounds.width - 1, bounds.height - 1);
        graphics.drawLine(0, bounds.height - 1, bounds.width - 1, bounds.height - 1);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paintBorderIn(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        final Color brighter = this.getBackground().brighter();
        final Color darker = brighter.darker();
        final Color darker2 = darker.darker();
        graphics.setColor(darker2.darker());
        graphics.drawLine(0, 0, bounds.width - 1, 0);
        graphics.drawLine(0, 0, 0, bounds.height - 1);
        graphics.setColor(darker2);
        graphics.drawLine(1, 1, bounds.width - 2, 1);
        graphics.drawLine(1, 1, 1, bounds.height - 2);
        graphics.setColor(darker);
        graphics.drawLine(bounds.width - 2, 1, bounds.width - 2, bounds.height - 2);
        graphics.drawLine(1, bounds.height - 2, bounds.width - 2, bounds.height - 2);
        graphics.setColor(brighter);
        graphics.drawLine(bounds.width - 1, 1, bounds.width - 1, bounds.height - 1);
        graphics.drawLine(0, bounds.height - 1, bounds.width - 1, bounds.height - 1);
    }
    
    public void removeButtonPushEventListener(final ButtonPushEventListener buttonPushEventListener) {
        this.listeners.removeElement(buttonPushEventListener);
    }
    
    public Dimension preferredSize() {
        if (this.img != null) {
            return new Dimension(this.img.getWidth(this) + 4, this.img.getHeight(this) + 4);
        }
        return new Dimension(20, 20);
    }
    
    public void addButtonPushEventListener(final ButtonPushEventListener buttonPushEventListener) {
        this.listeners.addElement(buttonPushEventListener);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501) {
            this.button_push_state = true;
            this.repaint();
            return true;
        }
        if (event.id == 502 && this.button_push_state) {
            this.button_push_state = false;
            this.repaint();
            final Rectangle bounds = this.bounds();
            bounds.x = 0;
            bounds.y = 0;
            if (bounds.inside(event.x, event.y)) {
                this.doAction(event);
            }
            return true;
        }
        if (event.id == 506) {
            final Rectangle bounds2 = this.bounds();
            bounds2.x = 0;
            bounds2.y = 0;
            if (!bounds2.inside(event.x, event.y)) {
                if (this.button_push_state) {
                    this.button_push_state = false;
                    this.repaint();
                }
            }
            else if (!this.button_push_state) {
                this.button_push_state = true;
                this.repaint();
            }
            return true;
        }
        return false;
    }
    
    protected void doAction(final Event event) {
        final ButtonPushEvent buttonPushEvent = new ButtonPushEvent(this, event.x, event.y);
        final Vector vector;
        synchronized (this) {
            vector = (Vector)this.listeners.clone();
        }
        for (int i = 0; i < vector.size(); ++i) {
            ((ButtonPushEventListener)this.listeners.elementAt(i)).notifyButtonPushEvent(buttonPushEvent);
        }
    }
}
