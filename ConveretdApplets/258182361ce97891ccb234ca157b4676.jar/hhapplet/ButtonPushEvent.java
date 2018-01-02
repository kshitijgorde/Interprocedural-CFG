// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

public class ButtonPushEvent
{
    private int x;
    private int y;
    private Object source;
    
    public int getX() {
        return this.x;
    }
    
    public ButtonPushEvent(final Object source, final int x, final int y) {
        this.source = source;
        this.x = x;
        this.y = y;
    }
    
    public ButtonPushEvent(final Object o) {
        this(o, 0, 0);
    }
    
    public int getY() {
        return this.y;
    }
    
    public Object getSource() {
        return this.source;
    }
}
