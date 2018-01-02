// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public interface IState
{
    void setup(final int p0, final int p1);
    
    void pushState(final int p0);
    
    void popState(final int p0);
    
    void addMove(final int p0, final int p1, final int p2) throws Exception;
    
    void eliminateMove(final int p0, final int p1, final int p2);
    
    String toString();
}
