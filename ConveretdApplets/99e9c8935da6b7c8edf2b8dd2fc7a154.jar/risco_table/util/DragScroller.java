// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.util;

import java.awt.event.KeyEvent;
import java.util.Date;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.Component;

public class DragScroller
{
    private Component source;
    private KeyListener listener;
    private Rectangle componentArea;
    private int modifiers;
    private int dragHorizontally;
    private int dragVertically;
    
    public DragScroller(final Rectangle componentArea, final Component source, final KeyListener listener) {
        this.componentArea = componentArea;
        this.source = source;
        this.listener = listener;
    }
    
    public boolean drag(final int mouseX, final int mouseY, final int modifiers) {
        int distance = 1;
        double xDelta = 0.0;
        double yDelta = 0.0;
        this.modifiers = modifiers;
        if (mouseX > this.componentArea.x + this.componentArea.width) {
            this.dragHorizontally = 1;
            xDelta = mouseX - (this.componentArea.x + this.componentArea.width);
        }
        else if (mouseX < this.componentArea.x) {
            this.dragHorizontally = -1;
            xDelta = this.componentArea.x - mouseX;
        }
        else {
            this.dragHorizontally = 0;
        }
        if (mouseY > this.componentArea.y + this.componentArea.height) {
            this.dragVertically = 1;
            yDelta = mouseY - (this.componentArea.y + this.componentArea.height);
        }
        else if (mouseY < this.componentArea.y) {
            this.dragVertically = -1;
            yDelta = this.componentArea.y - mouseY;
        }
        else {
            this.dragVertically = 0;
        }
        if (this.dragHorizontally != 0 || this.dragVertically != 0) {
            distance = (int)Math.sqrt(xDelta * xDelta + yDelta * yDelta);
            this.postKeyEvent();
            return true;
        }
        return false;
    }
    
    public void postKeyEvent() {
        int keyCode = 0;
        if (this.dragHorizontally > 0) {
            keyCode = 39;
        }
        else if (this.dragHorizontally < 0) {
            keyCode = 37;
        }
        if (keyCode != 0) {
            this.listener.keyPressed(new KeyEvent(this.source, 401, new Date().getTime(), this.modifiers, keyCode));
        }
        keyCode = 0;
        if (this.dragVertically > 0) {
            keyCode = 40;
        }
        else if (this.dragVertically < 0) {
            keyCode = 38;
        }
        if (keyCode != 0) {
            this.listener.keyPressed(new KeyEvent(this.source, 401, new Date().getTime(), this.modifiers, keyCode));
        }
    }
}
