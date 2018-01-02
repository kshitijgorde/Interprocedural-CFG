// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class UndoableColorAll implements Undoable
{
    private int colorTo;
    private Vector shapes;
    private Vector colorWas;
    private boolean undone;
    
    public UndoableColorAll(final int colorTo, final Shape shape) {
        this.shapes = new Vector(10, 10);
        this.colorWas = new Vector(10, 10);
        this.colorTo = colorTo;
        this.add(shape);
        this.undone = false;
    }
    
    public void add(final Shape shape) {
        this.shapes.addElement(shape);
        this.colorWas.addElement(new Integer(shape.getColorId()));
    }
    
    public boolean isRedoable() {
        return this.undone;
    }
    
    public boolean isUndoable() {
        return this.undone ^ true;
    }
    
    public void redo() {
        if (!this.undone) {
            return;
        }
        this.undone = false;
        for (int i = 0; i < this.shapes.size(); ++i) {
            ((Shape)this.shapes.elementAt(i)).setColor(this.colorTo);
        }
    }
    
    public void undo() {
        if (this.undone) {
            return;
        }
        this.undone = true;
        for (int i = 0; i < this.shapes.size(); ++i) {
            ((Shape)this.shapes.elementAt(i)).setColor((int)this.colorWas.elementAt(i));
        }
    }
}
