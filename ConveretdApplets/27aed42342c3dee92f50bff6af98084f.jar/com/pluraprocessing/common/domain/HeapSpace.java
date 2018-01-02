// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

public enum HeapSpace
{
    SMALL("SMALL", 0, 50, false), 
    MEDIUM("MEDIUM", 1, 100, false), 
    LARGE("LARGE", 2, 200, true);
    
    private final int maxGuaranteedHeapSpaceMB;
    private final boolean isHighestTier;
    
    private HeapSpace(final String s, final int n, final int id, final boolean isHighestTier) {
        this.maxGuaranteedHeapSpaceMB = id;
        this.isHighestTier = isHighestTier;
    }
    
    public int getValue() {
        return this.maxGuaranteedHeapSpaceMB;
    }
    
    public int getMinimumHeapNeeded() {
        return 0;
    }
    
    public int getMaximumHeapNeeded() {
        if (this.isHighestTier) {
            return Integer.MAX_VALUE;
        }
        return this.getValue();
    }
    
    public static HeapSpace getHeapSpace(final int value) {
        if (value <= HeapSpace.SMALL.getValue()) {
            return HeapSpace.SMALL;
        }
        if (value <= HeapSpace.MEDIUM.getValue()) {
            return HeapSpace.MEDIUM;
        }
        return HeapSpace.LARGE;
    }
    
    public boolean isHighestTier() {
        return this.isHighestTier;
    }
}
