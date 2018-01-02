// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;

public class Ranks
{
    static final int skTouchHack = 1;
    static final String[] mNames;
    static final int[] mScoreCutoffs;
    
    static boolean HasNextRank(final int n) {
        return n < Ranks.mScoreCutoffs[Ranks.mScoreCutoffs.length - 1];
    }
    
    static String GetName(final int n) {
        int n2;
        for (n2 = 0; n2 < Ranks.mScoreCutoffs.length && Ranks.mScoreCutoffs[n2] <= n; ++n2) {}
        Assert.assert(--n2 < Ranks.mNames.length);
        return Ranks.mNames[n2];
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    static {
        mNames = new String[] { "Inquisitive Child", "Curious Badger", "Young Salamander", "Promising Youngster", "Junior Apprentice", "Apprentice", "Novice", "Initiate", "Assistant to the Master", "Freshman", "Student", "Graduate", "Wanderer", "Performer", "Crowd Pleaser", "Master Performer", "Disciple of the Lotus", "Disciple of the Pearl", "Master of Pyrotechnics", "Virtuose Pyrotechnist", "Imperial Pyrotechnist", "Master of Fire", "Master of the Elements", "Enlightened Sage", "Mystifying Sage", "Master Sage", "Brother of Dragons", "Dragon Whisperer", "Dragon Master", "Supreme Dragon Emperor" };
        mScoreCutoffs = new int[] { 0, 6000, 12000, 18000, 24000, 30000, 36000, 42000, 48000, 54000, 60000, 69000, 78000, 90000, 120000, 180000, 240000, 300000, 360000, 420000, 480000, 600000, 750000, 900000, 1200000, 1800000, 3000000 };
    }
    
    static int NextRankAt(final int n) {
        int n2;
        for (n2 = 0; n2 < Ranks.mScoreCutoffs.length && Ranks.mScoreCutoffs[n2] <= n; ++n2) {}
        return Ranks.mScoreCutoffs[n2];
    }
}
