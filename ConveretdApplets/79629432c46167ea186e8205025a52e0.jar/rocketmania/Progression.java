// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;
import sexy.util.MTRand;

public class Progression
{
    static final int MAXROCKETSTOLAUNCHPERLEVEL = 49;
    static final int _COINMULTIINTERPOLATIONLEVEL = 50;
    static final int PIECEFREQUENCIESTYPE_I = 0;
    static final int PIECEFREQUENCIESTYPE_L = 1;
    static final int PIECEFREQUENCIESTYPE_T = 2;
    static final int PIECEFREQUENCIESTYPE_X = 3;
    static final int PIECEFREQUENCIESTYPE_CULDESAC = 4;
    static final int LASTPIECEFREQUENCIESTYPE = 5;
    static final int COINSINTRODUCTIONLEVEL = 1;
    static final int GEMINTRODUCTIONLEVEL = 3;
    static final int BOMBINTRODUCTIONLEVEL = 5;
    static final int CLOCKINTRODUCTIONLEVEL = 8;
    static final int COINSPERROCKETUPGRADE = 5;
    static final int RU_RETURNNUMROCKETSTHATWILLBEUPGRADED = 0;
    static final int RU_RETURNROWOFNTHUPGRADE = 1;
    static final int[] mFrequenciesInterpolationLevel;
    static final int[][][] mFrequencies;
    static final int[] naOrder;
    int mNumBonusesWaitingToBeDropped;
    private MTRand mRand;
    private int[] mFrequenciesForThisLevel;
    private RocketManiaApplet mApp;
    private Board mBoard;
    private Stats mStats;
    
    void BonusPotentialInit() {
        this.BonusPotentialClear();
    }
    
    public Progression(final RocketManiaApplet mApp, final Board mBoard, final MTRand mRand, final Stats mStats) {
        this.mApp = mApp;
        this.mBoard = mBoard;
        this.mRand = mRand;
        this.mStats = mStats;
    }
    
    public void PieceFrequenciesRecalculate(final int n, final int n2) {
        Assert.assert(n >= 0);
        final int[][] array = Progression.mFrequencies[n2];
        double n3;
        if (n >= Progression.mFrequenciesInterpolationLevel[n2]) {
            n3 = 1.0;
        }
        else {
            n3 = n / Progression.mFrequenciesInterpolationLevel[n2];
            Assert.assert(n3 >= 0.0 && n3 <= 1.0);
        }
        int n4 = 0;
        int n5 = 0;
        do {
            n4 += (int)(Math.round((array[1][n5] - array[0][n5]) * n3) + array[0][n5]);
        } while (++n5 < 5);
        this.mFrequenciesForThisLevel = new int[n4];
        int n6 = 0;
        int n7 = 0;
        do {
            for (int n8 = (int)Math.round((array[1][n7] - array[0][n7]) * n3) + array[0][n7], i = 0; i < n8; ++i) {
                this.mFrequenciesForThisLevel[n6++] = n7;
            }
        } while (++n7 < 5);
        Assert.assert(this.mFrequenciesForThisLevel.length == n6);
    }
    
    void BonusPotentialDetermine(final int n) {
        switch (n) {
            case 3: {
                this.mBoard.AddTextFloater("Triple Launch!", 125, Board.GetColX(3), 30);
                break;
            }
            case 4: {
                this.mBoard.AddTextFloater("Quadruple Launch!", 125, Board.GetColX(3), 30);
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9: {
                final int n2 = Scores.MULTILAUNCH[n];
                this.mBoard.AddTextFloater("" + n + "x Launch! " + "" + n2 + " points bonus!", 125, Board.GetColX(3), 35);
                this.mBoard.AddScore(n2);
                break;
            }
        }
        if (n > 1) {
            this.mNumBonusesWaitingToBeDropped += n * (n - 1);
        }
        this.mNumBonusesWaitingToBeDropped *= (int)(1.0 + Math.min(this.mBoard.mLevel / 50.0, 1.0));
        this.BonusPotentialClear();
    }
    
    int GetRandomPickupType() {
        int pickupFrequency;
        if (this.mBoard.mLevel >= 3) {
            pickupFrequency = this.pickupFrequency(0, this.mBoard.mLevel, this.mApp.mGameType);
        }
        else {
            pickupFrequency = 0;
        }
        int pickupFrequency2;
        if (this.mBoard.mLevel >= 5) {
            pickupFrequency2 = this.pickupFrequency(1, this.mBoard.mLevel, this.mApp.mGameType);
        }
        else {
            pickupFrequency2 = 0;
        }
        int pickupFrequency3;
        if (this.mBoard.mLevel >= 8) {
            pickupFrequency3 = this.pickupFrequency(2, this.mBoard.mLevel, this.mApp.mGameType);
        }
        else {
            pickupFrequency3 = 0;
        }
        final int n = pickupFrequency + pickupFrequency2 + pickupFrequency3;
        if (n == 0) {
            return -1;
        }
        final int n2 = this.mRand.Next() % n;
        if (n2 < pickupFrequency) {
            return 0;
        }
        if (n2 < pickupFrequency + pickupFrequency2) {
            return 1;
        }
        Assert.assert(n2 < pickupFrequency + pickupFrequency2 + pickupFrequency3);
        return 2;
    }
    
    int UpgradeRocketHighestType() {
        int n = 9;
        do {
            int n2 = 0;
            int n3 = 0;
            do {
                if (this.mBoard.mRockets[n3].mSize == n) {
                    ++n2;
                }
            } while (++n3 < 9);
            if (n2 >= 3) {
                return n;
            }
        } while (--n >= 0);
        Assert.assert(false);
        return 0;
    }
    
    boolean ShouldAddRandomPickup() {
        final int pickupFrequencyForCurrentGameType = this.PickupFrequencyForCurrentGameType();
        return pickupFrequencyForCurrentGameType != 0 && this.mRand.Next() % pickupFrequencyForCurrentGameType == 0;
    }
    
    static {
        mFrequenciesInterpolationLevel = new int[] { 40, 30, 20 };
        mFrequencies = new int[][][] { { { 160, 120, 40, 20, 0 }, { 130, 150, 20, 10, 4 } }, { { 160, 120, 40, 20, 0 }, { 130, 150, 20, 10, 8 } }, { { 160, 120, 40, 20, 0 }, { 130, 150, 20, 10, 8 } } };
        naOrder = new int[] { 4, 5, 3, 6, 2, 7, 1, 8, 0 };
    }
    
    boolean RocketsAllAtMaxUpgrade() {
        int n = 0;
        while (this.mBoard.mRockets[n].mSize >= 9) {
            if (++n >= 9) {
                return true;
            }
        }
        return false;
    }
    
    private int pickupFrequency(final int n, final int n2, final int n3) {
        if (n == 0) {
            return 15;
        }
        if (n == 1) {
            return 15;
        }
        if (n == 2) {
            return 10;
        }
        Assert.assert(false);
        return 0;
    }
    
    int PickupFrequencyForCurrentGameType() {
        if (this.mBoard.mLevel == 0) {
            return 0;
        }
        if (this.mBoard.mLevel > 20) {
            return 30;
        }
        return 55 - (this.mBoard.mLevel - 1) * 1;
    }
    
    int GetGemType(final int n) {
        int n2;
        if (n < 3) {
            n2 = 0;
        }
        else if (n < 10) {
            n2 = this.mRand.Next() % 2;
        }
        else if (n < 15) {
            n2 = this.mRand.Next() % 3;
        }
        else if (n < 20) {
            n2 = this.mRand.Next() % 4;
        }
        else {
            n2 = this.mRand.Next() % 5;
        }
        Assert.assert(n2 >= 0 && n2 <= 4);
        return n2;
    }
    
    void BonusPotentialClear() {
    }
    
    void UpgradeRocketAtRow(final int n) {
        Assert.assert(this.mBoard.mRockets[n].mSize < 9);
        if (this.mBoard.mRockets[n].mSize < 9) {
            final Rocket rocket = this.mBoard.mRockets[n];
            ++rocket.mSize;
        }
    }
    
    void SetBoardVars() {
        this.BonusPotentialInit();
        Assert.assert(this.mApp.mSkillLevel >= 0 && this.mApp.mSkillLevel <= 2);
        switch (this.mApp.mSkillLevel) {
            case 0: {
                this.mBoard.mLevelTimerStart = 130.0;
                break;
            }
            case 1: {
                this.mBoard.mLevelTimerStart = 100.0;
                break;
            }
            case 2: {
                this.mBoard.mLevelTimerStart = 80.0;
                break;
            }
            default: {
                this.mBoard.mLevelTimerStart = 80.0;
                Assert.assert(false);
                break;
            }
        }
        this.mBoard.mRocketsNeededToLevel = Math.min(49, 9 + (this.mBoard.mLevel + 1) * 1);
    }
    
    int PossiblyUpgradeRockets(int n, final int n2, final int n3) {
        int n4 = 0;
        final int upgradeRocketHighestType = this.UpgradeRocketHighestType();
        int n5 = 0;
        do {
            Assert.assert(true);
            if (n >= 5 && this.mBoard.mRockets[Progression.naOrder[n5]].mSize < 9 && this.mBoard.mRockets[Progression.naOrder[n5]].mSize <= upgradeRocketHighestType) {
                n -= 5;
                if (n2 == 1 && n3 == n4) {
                    return Progression.naOrder[n5];
                }
                ++n4;
            }
        } while (++n5 < 9);
        if (n2 == 0) {
            return n4;
        }
        Assert.assert(false);
        return 1;
    }
    
    public int RandomPieceType(final int n) {
        Assert.assert(n == 0);
        return this.mFrequenciesForThisLevel[this.mRand.Next() % this.mFrequenciesForThisLevel.length];
    }
}
