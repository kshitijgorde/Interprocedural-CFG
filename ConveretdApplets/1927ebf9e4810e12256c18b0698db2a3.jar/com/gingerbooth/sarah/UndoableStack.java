// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import courseware.util.MemCleaner;
import java.util.Vector;

public class UndoableStack implements Undoable
{
    private static Vector stack;
    private static int depth;
    private final int depthIncrement = 10;
    private int maxDepth;
    private int pruneBy;
    
    public UndoableStack() {
        this.maxDepth = 50;
        this.pruneBy = 10;
        UndoableStack.stack = new Vector(10, 10);
        UndoableStack.depth = -1;
    }
    
    public Undoable getCurrent() {
        if (UndoableStack.depth < 0) {
            return null;
        }
        return UndoableStack.stack.elementAt(UndoableStack.depth);
    }
    
    public boolean isRedoable() {
        if (UndoableStack.stack.size() < UndoableStack.depth + 2) {
            return false;
        }
        final Undoable undoable = UndoableStack.stack.elementAt(UndoableStack.depth + 1);
        return undoable != null && undoable.isRedoable();
    }
    
    public boolean isUndoable() {
        return UndoableStack.depth > -1;
    }
    
    public void pop() {
        UndoableStack.stack.removeElementAt(UndoableStack.depth);
        --UndoableStack.depth;
    }
    
    public void popAll() {
        UndoableStack.stack = new Vector(10, 10);
        UndoableStack.depth = -1;
    }
    
    private void pruneStack() {
        if (UndoableStack.stack.size() < this.maxDepth) {
            return;
        }
        for (int i = this.pruneBy - 1; i > -1; --i) {
            UndoableStack.stack.removeElementAt(i);
        }
        UndoableStack.stack.trimToSize();
        UndoableStack.depth -= this.pruneBy;
        MemCleaner.cleanup();
    }
    
    public void push(final Undoable undoable) {
        if (UndoableStack.depth > -1 && !UndoableStack.stack.elementAt(UndoableStack.depth).isUndoable()) {
            --UndoableStack.depth;
        }
        ++UndoableStack.depth;
        if (UndoableStack.stack.size() > UndoableStack.depth) {
            UndoableStack.stack.setElementAt(undoable, UndoableStack.depth);
        }
        else {
            UndoableStack.stack.addElement(undoable);
            UndoableStack.depth = UndoableStack.stack.size() - 1;
        }
        for (int i = UndoableStack.depth + 1; i < UndoableStack.stack.size(); ++i) {
            UndoableStack.stack.setElementAt(null, i);
        }
        this.pruneStack();
    }
    
    public void redo() {
        final int n = UndoableStack.depth + 1;
        if (n > UndoableStack.stack.size() - 1) {
            return;
        }
        final Undoable undoable = UndoableStack.stack.elementAt(n);
        if (undoable == null) {
            return;
        }
        if (!undoable.isRedoable()) {
            return;
        }
        undoable.redo();
        ++UndoableStack.depth;
    }
    
    public void undo() {
        if (UndoableStack.depth < 0) {
            UndoableStack.depth = -1;
            return;
        }
        final Undoable undoable = UndoableStack.stack.elementAt(UndoableStack.depth);
        if (undoable == null) {
            return;
        }
        if (!undoable.isUndoable()) {
            return;
        }
        undoable.undo();
        --UndoableStack.depth;
    }
}
