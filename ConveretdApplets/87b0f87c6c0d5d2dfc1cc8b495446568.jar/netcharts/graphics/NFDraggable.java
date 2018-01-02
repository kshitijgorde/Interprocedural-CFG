// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Enumeration;
import java.awt.Event;
import netcharts.util.NFDebug;
import java.util.Vector;

public abstract class NFDraggable
{
    protected Vector observers;
    protected double[] curCoord;
    protected double[] newCoord;
    protected long debugType;
    protected String debugName;
    private int a;
    private int b;
    private int c;
    private int d;
    private boolean e;
    
    public NFDraggable() {
        this.observers = null;
        this.curCoord = new double[2];
        this.newCoord = new double[2];
        this.debugType = 512L;
        this.debugName = "NFDraggable";
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = false;
    }
    
    public void debug(final String s) {
        NFDebug.print(this.debugType, this.debugName + ": " + s);
    }
    
    public abstract boolean isDraggable(final Event p0, final int p1, final int p2);
    
    public abstract double[] getCoords();
    
    public abstract double[] moveRelative(final Event p0, final int p1, final int p2);
    
    public abstract boolean dragTo(final double[] p0);
    
    public void addObserver(final Object o) {
        if (this.observers == null) {
            this.observers = new Vector();
        }
        if (this.observers.contains(o)) {
            return;
        }
        this.debug("add observer " + o);
        this.observers.addElement(o);
    }
    
    public void removeObserver(final Object o) {
        if (this.observers == null) {
            return;
        }
        if (!this.observers.contains(o)) {
            return;
        }
        this.debug("remove observer " + o);
        this.observers.removeElement(o);
    }
    
    public boolean mouseDown(final Event event, final int a, final int b) {
        if (!(this.e = this.isDraggable(event, a, b))) {
            return false;
        }
        this.debug("mouseDown: " + a + "," + b);
        this.a = a;
        this.b = b;
        this.c = 0;
        this.d = event.clickCount;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int a, final int b) {
        if (!this.e) {
            return false;
        }
        this.debug("mouseDrag: " + a + "," + b);
        if (a == this.a && b == this.b) {
            return true;
        }
        if (!this.a(event, a - this.a, b - this.b)) {
            return false;
        }
        this.a = a;
        this.b = b;
        ++this.c;
        return true;
    }
    
    private boolean a(final Event event, final int n, final int n2) {
        final double[] coords = this.getCoords();
        if (coords == null) {
            return false;
        }
        final double[] moveRelative = this.moveRelative(event, n, n2);
        return moveRelative != null && !this.abortPreDrag(coords, moveRelative) && this.dragTo(moveRelative);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.e) {
            return false;
        }
        this.debug("mouseUp: " + n + "," + n2);
        this.e = false;
        if ((n != this.a || n2 != this.b) && !this.a(event, n - this.a, n2 - this.b)) {
            return false;
        }
        if (this.c == 0) {
            event.clickCount = this.d;
            return false;
        }
        this.notifyPostDrag();
        return true;
    }
    
    public boolean abortPreDrag(final double[] array, final double[] array2) {
        if (this.observers == null) {
            return false;
        }
        final Enumeration<NFDragObserver> elements = this.observers.elements();
        while (elements.hasMoreElements()) {
            final NFDragObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFDragObserver)) {
                continue;
            }
            if (!nextElement.preDrag(this, array[0], array[1], array2[0], array2[1])) {
                return true;
            }
        }
        return false;
    }
    
    public void notifyPostDrag() {
        if (this.observers == null) {
            return;
        }
        final double[] coords = this.getCoords();
        final Enumeration<NFDragObserver> elements = this.observers.elements();
        while (elements.hasMoreElements()) {
            final NFDragObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFDragObserver)) {
                continue;
            }
            nextElement.postDrag(this, coords[0], coords[1]);
        }
    }
}
