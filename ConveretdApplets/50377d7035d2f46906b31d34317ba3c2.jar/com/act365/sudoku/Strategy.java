// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

public class Strategy
{
    public static final int FIRST_AVAILABLE = 0;
    public static final int LEAST_CANDIDATES_CELL = 1;
    public static final int RANDOM_LEAST_CANDIDATES_CELL = 2;
    public static final int LEAST_CANDIDATES_NUMBER = 3;
    public static final int RANDOM_LEAST_CANDIDATES_NUMBER = 4;
    public static final int LEAST_CANDIDATES_HYBRID = 5;
    public static final int RANDOM_LEAST_CANDIDATES_HYBRID = 6;
    public static final int LEAST_CANDIDATES_HYBRID_II = 7;
    public static final int RANDOM_LEAST_CANDIDATES_HYBRID_II = 8;
    public static final int MOST_CANDIDATES = 9;
    public static final int RANDOM_MOST_CANDIDATES = 10;
    public static final String[] strategyNames;
    
    static {
        strategyNames = new String[] { "First Available", "Least Candidates Cell", "Random Least Candidates Cell", "Least Candidates Number", "Random Least Candidates Number", "Least Candidates Hybrid", "Random Least Candidates Hybrid", "Least Candidates Hybrid II", "Random Least Candidates Hybrid II", "Most Candidates", "Random Most Candidates" };
    }
    
    public static IStrategy create(final int strategy) {
        switch (strategy) {
            case 0: {
                return new FirstAvailable();
            }
            case 1: {
                return new LeastCandidatesCell(false);
            }
            case 2: {
                return new LeastCandidatesCell(true);
            }
            case 3: {
                return new LeastCandidatesNumber(false);
            }
            case 4: {
                return new LeastCandidatesNumber(true);
            }
            case 5: {
                return new LeastCandidatesHybrid(false, true);
            }
            case 6: {
                return new LeastCandidatesHybrid(true, true);
            }
            case 7: {
                return new LeastCandidatesHybrid(false, true, true, true);
            }
            case 8: {
                return new LeastCandidatesHybrid(true, true, true, true);
            }
            case 9: {
                return new MostCandidates(null, false);
            }
            case 10: {
                return new MostCandidates(null, true);
            }
            default: {
                return null;
            }
        }
    }
    
    public static IStrategy create(final String strategy) {
        for (int i = 0; i < Strategy.strategyNames.length; ++i) {
            if (strategy.equalsIgnoreCase(Strategy.strategyNames[i])) {
                return create(i);
            }
        }
        return null;
    }
}
