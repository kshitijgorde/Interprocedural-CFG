// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

public class UndoableShape implements Undoable
{
    public static final int CHANGE = 0;
    public static final int ADD = 1;
    public static final int DELETE = 2;
    private Tableau tableau;
    private Shape stateBefore;
    private Shape stateAfter;
    private boolean undone;
    private int operation;
    
    public UndoableShape(final int operation, final Tableau tableau, final Shape stateBefore) {
        this.operation = operation;
        this.tableau = tableau;
        this.stateBefore = stateBefore;
        this.undone = false;
    }
    
    public int getOperation() {
        return this.operation;
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
        this.tableau.setNeedRepaint(true);
        switch (this.operation) {
            case 0: {
                this.tableau.removeOne(this.stateBefore);
                this.tableau.add(this.stateAfter);
            }
            case 1: {
                this.tableau.add(this.stateAfter);
            }
            case 2: {
                this.tableau.removeOne(this.stateBefore);
            }
            default: {}
        }
    }
    
    public void setOperation(final int operation) {
        this.operation = operation;
    }
    
    public void setStateAfter(final Shape stateAfter) {
        this.stateAfter = stateAfter;
    }
    
    public void setStateBefore(final Shape stateBefore) {
        this.stateBefore = stateBefore;
    }
    
    public void undo() {
        if (this.undone) {
            return;
        }
        this.undone = true;
        this.tableau.setNeedRepaint(true);
        switch (this.operation) {
            case 0: {
                this.tableau.removeOne(this.stateAfter);
                this.tableau.add(this.stateBefore);
            }
            case 1: {
                this.tableau.removeOne(this.stateAfter);
            }
            case 2: {
                this.tableau.add(this.stateBefore);
            }
            default: {}
        }
    }
}
