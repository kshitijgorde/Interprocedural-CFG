// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public interface IStrategy
{
    void setup(final Grid p0) throws Exception;
    
    int findCandidates();
    
    int getScore();
    
    void selectCandidate();
    
    void setCandidate();
    
    boolean updateState(final int p0, final int p1, final int p2, final String p3, final boolean p4) throws Exception;
    
    boolean unwind(final int p0, final boolean p1);
    
    void reset();
    
    void reset(final int p0);
    
    int getBestX();
    
    int getBestY();
    
    int getBestValue();
    
    String getBestReason();
    
    int getNumberOfCandidates();
    
    int getXCandidate(final int p0);
    
    int getYCandidate(final int p0);
    
    int getValueCandidate(final int p0);
    
    String getReasonCandidate(final int p0);
    
    int getThreadLength();
    
    int getThreadX(final int p0);
    
    int getThreadY(final int p0);
    
    String getReason(final int p0);
    
    boolean explainsReasoning();
    
    int getLastWrittenMove();
    
    String toString();
}
