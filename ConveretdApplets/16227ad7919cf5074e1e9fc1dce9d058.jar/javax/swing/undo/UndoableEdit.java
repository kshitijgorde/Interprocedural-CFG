// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.undo;

public interface UndoableEdit
{
    boolean addEdit(final UndoableEdit p0);
    
    boolean canRedo();
    
    boolean canUndo();
    
    void die();
    
    String getPresentationName();
    
    String getRedoPresentationName();
    
    String getUndoPresentationName();
    
    boolean isSignificant();
    
    void redo() throws CannotRedoException;
    
    boolean replaceEdit(final UndoableEdit p0);
    
    void undo() throws CannotUndoException;
}
