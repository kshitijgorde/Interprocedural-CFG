// 
// Decompiled by Procyon v0.5.30
// 

package en.gui.components;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.util.Vector;
import java.awt.Image;
import java.awt.Canvas;

public class xMemoryDots extends Canvas implements xGUI
{
    Image dotDefault;
    Image dotOn;
    Image dotOff;
    int dotWidth;
    int dotHeight;
    Object[] memory;
    Vector listeners;
    
    public xMemoryDots() {
        this(Toolkit.getDefaultToolkit().createImage("d:/HyperNet/classes/volumetriclights/memorydotDefault.jpg"), Toolkit.getDefaultToolkit().createImage("d:/HyperNet/classes/volumetriclights/memorydotOn.jpg"), Toolkit.getDefaultToolkit().createImage("d:/HyperNet/classes/volumetriclights/memorydotOff.jpg"));
    }
    
    public xMemoryDots(final Image dotDefault, final Image dotOn, final Image dotOff) {
        this.memory = new Object[9];
        this.listeners = new Vector();
        this.dotDefault = dotDefault;
        this.dotOn = dotOn;
        this.dotOff = dotOff;
        while (true) {
            if (dotDefault.getWidth(null) != -1) {
                if (dotDefault.getHeight(null) == -1) {
                    continue;
                }
                break;
            }
        }
        this.dotWidth = dotDefault.getWidth(null);
        this.dotHeight = dotDefault.getHeight(null);
        this.setSize(this.dotWidth * 3, this.dotHeight * 3);
        this.enableEvents(48L);
    }
    
    public final void memorise(final Object o, final int n) {
        if (n < 0 || n > 8) {
            return;
        }
        this.memory[n] = o;
    }
    
    public final Object recall(final int n) {
        if (n < 0 || n > 8) {
            return null;
        }
        return this.memory[n];
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.dotDefault != null && this.dotOn != null && this.dotOff != null) {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (i == 1 && j == 1) {
                        graphics.drawImage(this.dotDefault, this.dotWidth * j, this.dotHeight * i, this);
                    }
                    else if (this.memory[i * 3 + j] != null) {
                        graphics.drawImage(this.dotOn, this.dotWidth * j, this.dotHeight * i, this);
                    }
                    else {
                        graphics.drawImage(this.dotOff, this.dotWidth * j, this.dotHeight * i, this);
                    }
                }
            }
        }
        else {
            graphics.drawRect(0, 0, 40, 40);
        }
    }
    
    protected final void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                final int n = mouseEvent.getX() / this.dotWidth;
                final int n2 = mouseEvent.getY() / this.dotHeight;
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    for (int i = 0; i < this.listeners.size(); ++i) {
                        ((MemoryDotListener)this.listeners.elementAt(i)).memorizing(new MemoryDotEvent(this, n2 * 3 + n));
                    }
                }
                else if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
                    for (int j = 0; j < this.listeners.size(); ++j) {
                        ((MemoryDotListener)this.listeners.elementAt(j)).recalling(new MemoryDotEvent(this, n2 * 3 + n));
                    }
                }
                this.forceRepaint();
                this.forceRepaint();
                this.forceRepaint();
                this.forceRepaint();
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public final void addMemoryDotListener(final MemoryDotListener memoryDotListener) {
        if (!this.listeners.contains(memoryDotListener)) {
            this.listeners.addElement(memoryDotListener);
        }
    }
    
    public final void removeMemoryDotListener(final MemoryDotListener memoryDotListener) {
        this.listeners.removeElement(memoryDotListener);
    }
    
    public final Dimension getPreferredSize() {
        return this.getSize();
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public final void forceRepaint() {
        this.update(this.getGraphics());
    }
}
