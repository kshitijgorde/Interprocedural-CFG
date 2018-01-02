// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.undo;

import java.io.Serializable;

public class AbstractUndoableEdit implements UndoableEdit, Serializable
{
    protected static final String UndoName = "Undo";
    protected static final String RedoName = "Redo";
    boolean hasBeenDone;
    boolean alive;
    
    public AbstractUndoableEdit() {
        this.hasBeenDone = true;
        this.alive = true;
    }
    
    public boolean addEdit(final UndoableEdit undoableEdit) {
        return false;
    }
    
    public boolean canRedo() {
        return this.alive && !this.hasBeenDone;
    }
    
    public boolean canUndo() {
        return this.alive && this.hasBeenDone;
    }
    
    public void die() {
        this.alive = false;
    }
    
    public String getPresentationName() {
        return "";
    }
    
    public String getRedoPresentationName() {
        final String presentationName = this.getPresentationName();
        String string;
        if (presentationName != "") {
            string = "Redo " + presentationName;
        }
        else {
            string = "Redo";
        }
        return string;
    }
    
    public String getUndoPresentationName() {
        final String presentationName = this.getPresentationName();
        String string;
        if (presentationName != "") {
            string = "Undo " + presentationName;
        }
        else {
            string = "Undo";
        }
        return string;
    }
    
    public boolean isSignificant() {
        return true;
    }
    
    public void redo() throws CannotRedoException {
        if (!this.canRedo()) {
            throw new CannotRedoException();
        }
        this.hasBeenDone = true;
    }
    
    public boolean replaceEdit(final UndoableEdit undoableEdit) {
        return false;
    }
    
    public String toString() {
        return String.valueOf(super.toString()) + " hasBeenDone: " + this.hasBeenDone + " alive: " + this.alive;
    }
    
    public void undo() throws CannotUndoException {
        if (!this.canUndo()) {
            throw new CannotUndoException();
        }
        this.hasBeenDone = false;
    }
}
