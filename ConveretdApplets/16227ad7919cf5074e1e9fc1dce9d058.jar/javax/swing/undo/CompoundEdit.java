// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.undo;

import java.util.Enumeration;
import java.util.Vector;

public class CompoundEdit extends AbstractUndoableEdit
{
    boolean inProgress;
    protected Vector edits;
    
    public CompoundEdit() {
        this.inProgress = true;
        this.edits = new Vector();
    }
    
    public boolean addEdit(final UndoableEdit undoableEdit) {
        if (!this.inProgress) {
            return false;
        }
        final UndoableEdit lastEdit = this.lastEdit();
        if (lastEdit == null) {
            this.edits.addElement(undoableEdit);
        }
        else if (!lastEdit.addEdit(undoableEdit)) {
            if (undoableEdit.replaceEdit(lastEdit)) {
                this.edits.removeElementAt(this.edits.size() - 1);
            }
            this.edits.addElement(undoableEdit);
        }
        return true;
    }
    
    public boolean canRedo() {
        return !this.isInProgress() && super.canRedo();
    }
    
    public boolean canUndo() {
        return !this.isInProgress() && super.canUndo();
    }
    
    public void die() {
        for (int i = this.edits.size() - 1; i >= 0; --i) {
            ((UndoableEdit)this.edits.elementAt(i)).die();
        }
        super.die();
    }
    
    public void end() {
        this.inProgress = false;
    }
    
    public String getPresentationName() {
        final UndoableEdit lastEdit = this.lastEdit();
        if (lastEdit != null) {
            return lastEdit.getPresentationName();
        }
        return super.getPresentationName();
    }
    
    public String getRedoPresentationName() {
        final UndoableEdit lastEdit = this.lastEdit();
        if (lastEdit != null) {
            return lastEdit.getRedoPresentationName();
        }
        return super.getRedoPresentationName();
    }
    
    public String getUndoPresentationName() {
        final UndoableEdit lastEdit = this.lastEdit();
        if (lastEdit != null) {
            return lastEdit.getUndoPresentationName();
        }
        return super.getUndoPresentationName();
    }
    
    public boolean isInProgress() {
        return this.inProgress;
    }
    
    public boolean isSignificant() {
        final Enumeration<UndoableEdit> elements = this.edits.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().isSignificant()) {
                return true;
            }
        }
        return false;
    }
    
    protected UndoableEdit lastEdit() {
        final int size = this.edits.size();
        if (size > 0) {
            return (UndoableEdit)this.edits.elementAt(size - 1);
        }
        return null;
    }
    
    public void redo() throws CannotRedoException {
        super.redo();
        final Enumeration<UndoableEdit> elements = this.edits.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().redo();
        }
    }
    
    public String toString() {
        return String.valueOf(super.toString()) + " inProgress: " + this.inProgress + " edits: " + this.edits;
    }
    
    public void undo() throws CannotUndoException {
        super.undo();
        int size = this.edits.size();
        while (size-- > 0) {
            ((UndoableEdit)this.edits.elementAt(size)).undo();
        }
    }
}
