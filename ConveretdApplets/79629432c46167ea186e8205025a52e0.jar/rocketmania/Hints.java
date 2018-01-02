// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;

public class Hints
{
    public static final int CLICK_ON_FUSES = 0;
    public static final int LAUNCH_ALL_ROCKETS = 1;
    public static final int COLLECT_GEMS = 2;
    public static final int EXPLODE_BOMBS = 3;
    public static final int COLLECT_CLOCKS = 4;
    public static final int COLLECT_COINS = 5;
    public static final int DAWN_APPROACHES = 6;
    public static final int GEMS_DISAPPEAR = 7;
    public static final int NUM_HINTS = 8;
    public static final String[][] skLabels;
    boolean mNoMoreHints;
    boolean[] mDone;
    float[] mDelay;
    
    void Update(final RocketManiaApplet rocketManiaApplet, final float n) {
        if (!this.mNoMoreHints) {
            int n2 = 0;
            do {
                if (this.mDelay[n2] > 0.0f) {
                    final float[] mDelay = this.mDelay;
                    final int n3 = n2;
                    mDelay[n3] -= n;
                    if (this.mDelay[n2] > 0.0f) {
                        continue;
                    }
                    this.mDelay[n2] = 0.0f;
                    this.InstantHint(rocketManiaApplet, n2);
                }
            } while (++n2 < 8);
        }
    }
    
    public Hints() {
        this.mDone = new boolean[8];
        this.mDelay = new float[8];
    }
    
    void Queue(final int n, final float n2) {
        Assert.assert(n >= 0 && n < 8);
        Assert.assert(n2 > 0.0f);
        if (!this.mNoMoreHints && !this.mDone[n] && this.mDelay[n] <= 0.0f) {
            this.mDelay[n] = n2;
        }
    }
    
    static {
        skLabels = new String[][] { { "How to Play", "Click on fuse tiles to rotate them. Complete fuses to launch rockets." }, { "Launch All Rockets", "Rockets remaining are shown on the left. Launch all rockets before dawn!" }, { "New Bonus Item!", "Gems give you extra points. Complete fuses through Gems to grab them!" }, { "New Bonus Item!", "Bombs blow up nearby fuses. Beware! They blow up coins and gems too!" }, { "New Bonus Item!", "Always running out of time? Grab the Clock to freeze the timer!" }, { "Collect Coins", "Launch two or more rockets at once to receive coins. Five coins upgrade one rocket." }, { "Dawn Approaches", "Time remaining is shown in the bar below. Launch all rockets before dawn!" }, { "Collect Gems", "Gems disappear after a short time. Get them before they go!" } };
    }
    
    void InstantHint(final RocketManiaApplet rocketManiaApplet, final int n) {
        Assert.assert(n >= 0 && n < 8);
        if (!this.mNoMoreHints && !this.mDone[n]) {
            rocketManiaApplet.DoHintDialog(Hints.skLabels[n][0], Hints.skLabels[n][1]);
            this.mDone[n] = true;
        }
    }
}
