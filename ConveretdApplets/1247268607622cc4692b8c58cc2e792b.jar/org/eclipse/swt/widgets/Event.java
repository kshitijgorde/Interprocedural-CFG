// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class Event
{
    public Display display;
    public Widget widget;
    public int type;
    public int detail;
    public Widget item;
    public int index;
    public GC gc;
    public int x;
    public int y;
    public int width;
    public int height;
    public int count;
    public int time;
    public int button;
    public char character;
    public int keyCode;
    public int keyLocation;
    public int stateMask;
    public int start;
    public int end;
    public String text;
    public boolean doit;
    public Object data;
    public Touch[] touches;
    public int xDirection;
    public int yDirection;
    public double magnification;
    public double rotation;
    
    public Event() {
        this.doit = true;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public void setBounds(final Rectangle rectangle) {
        this.x = rectangle.x;
        this.y = rectangle.y;
        this.width = rectangle.width;
        this.height = rectangle.height;
    }
    
    public String toString() {
        return "Event {type=" + this.type + " " + this.widget + " time=" + this.time + " data=" + this.data + " x=" + this.x + " y=" + this.y + " width=" + this.width + " height=" + this.height + " detail=" + this.detail + "}";
    }
}
