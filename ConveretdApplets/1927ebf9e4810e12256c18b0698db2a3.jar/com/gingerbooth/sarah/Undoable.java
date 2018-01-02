// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

public interface Undoable
{
    boolean isRedoable();
    
    boolean isUndoable();
    
    void redo();
    
    void undo();
}
