// 
// Decompiled by Procyon v0.5.30
// 

public class BJOptions
{
    public boolean resplitPairsAllowed;
    public boolean resplitAcesAllowed;
    public boolean doubleAfterSplitAllowed;
    public boolean doubleOn_10_or_11_only;
    public boolean surrenderAllowed;
    public boolean hitSoft17;
    public int numberDecks;
    public int penetration;
    public int limitIndex;
    private int numHandsPlayed;
    private boolean changed;
    public final int[] minLimits;
    public final int[] maxLimits;
    static final int HIT = 1;
    static final int STAND = 2;
    static final int SPLIT = 3;
    static final int DOUBLEDOWN = 4;
    static final int SURRENDER = 5;
    static final int INSURANCE = 6;
    private boolean check;
    private int dealersUpCard;
    private static final int[][] basicStrategy;
    private boolean tempDouble10or11;
    private boolean tempDoubleAfterSplit;
    private boolean tempSurrenderAllowed;
    private boolean tempMultiDeck;
    
    public int getMaxBet() {
        return this.maxLimits[this.limitIndex];
    }
    
    public int getMinBet() {
        if (this.numHandsPlayed == 1) {
            return this.minLimits[this.limitIndex];
        }
        return this.minLimits[this.limitIndex] * 2;
    }
    
    public void setnumHandsPlayed(final int numHandsPlayed) {
        this.numHandsPlayed = numHandsPlayed;
    }
    
    public void setChanged(final boolean changed) {
        this.changed = changed;
    }
    
    public boolean isChanged() {
        final boolean changed = this.changed;
        this.changed = false;
        return changed;
    }
    
    public void setBasicStrategy(final boolean check, final int dealersUpCard) {
        this.check = check;
        this.dealersUpCard = dealersUpCard;
    }
    
    public boolean isChecking() {
        return this.check;
    }
    
    public int checkBasicStrategy(final int n, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        return checkAll(this.dealersUpCard, n, b, b2, b3, b4, this.doubleAfterSplitAllowed, this.numberDecks > 1);
    }
    
    private static int checkAll(final int n, final int n2, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        if (n2 >= 20) {
            return 2;
        }
        int n3 = n2 - 4;
        if (b) {
            n3 += 7;
        }
        int n4 = BJOptions.basicStrategy[n3][n - 2];
        if (b5) {
            n4 >>= 1;
        }
        if (b6) {
            n4 >>= 2;
        }
        if (b2 && (n4 & 0x1) == 0x1) {
            return 4;
        }
        final int n5 = n4 >> 4;
        if (b3 && (n5 & 0x1) == 0x1) {
            return 3;
        }
        final int n6 = n5 >> 4;
        if (b4 && (n6 & 0x1) == 0x1) {
            return 5;
        }
        if ((n6 >> 4 & 0x1) == 0x1) {
            return 1;
        }
        return 2;
    }
    
    public void setTempOptions(final boolean tempDouble10or11, final boolean tempDoubleAfterSplit, final boolean tempSurrenderAllowed, final boolean tempMultiDeck) {
        this.tempDouble10or11 = tempDouble10or11;
        this.tempDoubleAfterSplit = tempDoubleAfterSplit;
        this.tempSurrenderAllowed = tempSurrenderAllowed;
        this.tempMultiDeck = tempMultiDeck;
    }
    
    public String getTempBasicStrategy(final int n, final int n2, final boolean b, final boolean b2) {
        String s;
        if (b2) {
            if (checkAll(n, n2, b, false, true, false, this.tempDoubleAfterSplit, this.tempMultiDeck) == 3) {
                s = "Y";
            }
            else {
                s = "N";
            }
        }
        else {
            final boolean b3 = !this.tempDouble10or11 || n2 == 10 || n2 == 11;
            String s2;
            if (checkAll(n, n2, b, false, false, this.tempSurrenderAllowed, this.tempDoubleAfterSplit, this.tempMultiDeck) == 5) {
                s2 = "X";
            }
            else if (checkAll(n, n2, b, b3, false, false, this.tempDoubleAfterSplit, this.tempMultiDeck) == 4) {
                s2 = "D";
            }
            else {
                s2 = " ";
            }
            if (checkAll(n, n2, b, false, false, false, this.tempDouble10or11, this.tempMultiDeck) == 1) {
                s = String.valueOf(s2) + "H";
            }
            else {
                s = String.valueOf(s2) + "S";
            }
        }
        return s;
    }
    
    public BJOptions() {
        this.resplitPairsAllowed = true;
        this.resplitAcesAllowed = false;
        this.doubleAfterSplitAllowed = true;
        this.doubleOn_10_or_11_only = false;
        this.surrenderAllowed = true;
        this.hitSoft17 = true;
        this.numberDecks = 2;
        this.penetration = 3;
        this.changed = false;
        this.minLimits = new int[] { 2, 5, 25, 100 };
        this.maxLimits = new int[] { 200, 1000, 3000, 5000 };
    }
    
    static {
        basicStrategy = new int[][] { { 61600, 61616, 61680, 61680, 61680, 61680, 61440, 61440, 61440, 61440 }, { 61440, 61440, 61440, 61440, 61440, 61440, 61440, 61440, 61440, 61440 }, { 61600, 61600, 61680, 61680, 61680, 61680, 61440, 61440, 61440, 61440 }, { 61440, 61440, 61440, 61440, 61440, 61440, 61440, 61440, 61440, 61440 }, { 61440, 61440, 61472, 61600, 61600, 61440, 61440, 61440, 61440, 61440 }, { 61443, 61455, 61455, 61455, 61455, 61440, 61440, 61440, 61440, 61440 }, { 61455, 61455, 61455, 61455, 61455, 61455, 61455, 61455, 61440, 61440 }, { 61455, 61455, 61455, 61455, 61455, 61455, 61455, 61455, 61455, 61443 }, { 61616, 61680, 240, 240, 240, 61472, 61440, 61440, 61440, 61440 }, { 0, 0, 0, 0, 0, 61440, 61440, 61440, 61440, 61440 }, { 240, 240, 240, 240, 240, 61680, 61472, 61440, 61440, 61440 }, { 0, 0, 0, 0, 0, 61440, 61440, 61440, 65280, 61440 }, { 240, 240, 240, 240, 240, 61680, 61680, 65520, 65520, 65520 }, new int[10], { 240, 240, 240, 240, 240, 0, 240, 240, 0, 0 }, { 240, 240, 240, 240, 240, 240, 240, 240, 240, 240 }, { 61440, 61440, 61443, 61455, 61455, 61440, 61440, 61440, 61440, 61440 }, { 61440, 61440, 61443, 61455, 61455, 61440, 61440, 61440, 61440, 61440 }, { 61440, 61440, 61455, 61455, 61455, 61440, 61440, 61440, 61440, 61440 }, { 61440, 61440, 61455, 61455, 61455, 61440, 61440, 61440, 61440, 61440 }, { 61443, 61455, 61455, 61455, 61455, 61440, 61440, 61440, 61440, 61440 }, { 0, 15, 15, 15, 15, 0, 0, 61440, 61440, 49152 }, { 0, 0, 0, 0, 3, 0, 0, 0, 0, 0 } };
    }
}
