// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.game;

public interface MoveUndoer
{
    void undoMoves(final int p0);
    
    void redoMoves(final String[] p0);
}
