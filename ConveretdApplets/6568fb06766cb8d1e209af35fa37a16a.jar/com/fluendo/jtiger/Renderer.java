// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jtiger;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Component;
import com.fluendo.jkate.Event;
import java.util.Vector;

public class Renderer
{
    private Vector items;
    
    public Renderer() {
        this.items = new Vector();
    }
    
    public void add(final Event event) {
        this.items.addElement(new Item(event));
    }
    
    public int update(final Component component, final Image image, final double n) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (!((Item)this.items.elementAt(i)).update(component, image, n)) {
                this.items.removeElementAt(i);
                --i;
            }
        }
        if (this.items.size() == 0) {
            return 1;
        }
        return 0;
    }
    
    public Image render(final Component component, Image image) {
        final Image image2 = component.createImage(image.getWidth(null), image.getHeight(null));
        final Graphics graphics = image2.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        image = image2;
        for (int i = 0; i < this.items.size(); ++i) {
            ((Item)this.items.elementAt(i)).render(component, image);
        }
        return image;
    }
}
