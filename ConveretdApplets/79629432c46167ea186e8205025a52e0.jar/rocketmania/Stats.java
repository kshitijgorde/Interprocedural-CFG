// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

public class Stats
{
    int mRocketsLaunchedThisLevel;
    StatsGroup mLevelStats;
    StatsGroup mGameStats;
    
    void CheckMostRockets(final int n) {
        this.mLevelStats.CheckMostRockets(n);
        this.mGameStats.CheckMostRockets(n);
    }
    
    void RecordCoinsCollected(final int n) {
        this.mLevelStats.RecordCoinsCollected(n);
        this.mGameStats.RecordCoinsCollected(n);
    }
    
    public Stats() {
        this.mLevelStats = new StatsGroup();
        this.mGameStats = new StatsGroup();
    }
    
    void CheckLongestFuse(final int n) {
        this.mLevelStats.CheckLongestFuse(n);
        this.mGameStats.CheckLongestFuse(n);
    }
    
    void RecordGemsCollected(final int n) {
        this.mLevelStats.RecordGemsCollected(n);
        this.mGameStats.RecordGemsCollected(n);
    }
    
    class StatsGroup
    {
        int mLongestFuseNumSegments;
        int mMostRocketsFiredAtOnce;
        int mCoinValueCollected;
        int mGemsCollected;
        int mBombsCollected;
        int mClocksCollected;
        int mMatchesCollected;
        
        void CheckMostRockets(final int mMostRocketsFiredAtOnce) {
            if (mMostRocketsFiredAtOnce > this.mMostRocketsFiredAtOnce) {
                this.mMostRocketsFiredAtOnce = mMostRocketsFiredAtOnce;
            }
        }
        
        void RecordCoinsCollected(final int n) {
            this.mCoinValueCollected += n;
        }
        
        public StatsGroup() {
            Stats.this.getClass();
            this.Reset();
        }
        
        void CheckLongestFuse(final int mLongestFuseNumSegments) {
            if (mLongestFuseNumSegments > this.mLongestFuseNumSegments) {
                this.mLongestFuseNumSegments = mLongestFuseNumSegments;
            }
        }
        
        void Reset() {
            this.mLongestFuseNumSegments = 0;
            this.mMostRocketsFiredAtOnce = 0;
            this.mCoinValueCollected = 0;
            this.mGemsCollected = 0;
            this.mBombsCollected = 0;
            this.mClocksCollected = 0;
            this.mMatchesCollected = 0;
        }
        
        void RecordGemsCollected(final int n) {
            this.mGemsCollected += n;
        }
    }
}
