// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.thirdparty;

import com.zylom.cipher.CipherInteger;
import com.zylom.gamekit.hostallowed.HostAllowed;
import java.applet.Applet;

public class ZylomHelper
{
    private boolean doDataGathering;
    private boolean doDebug;
    private boolean doHighScores;
    private boolean doHostCheck;
    private Applet mApplet;
    private long mAppletBirthTime;
    private long mAppletFinishedLoadingTime;
    private DataGathering mDataGathering;
    private int mGamesFinishedCount;
    private int mGamesStartedCount;
    private boolean mHasDied;
    private boolean mHasFinishedLoading;
    private boolean mIsMuted;
    private boolean mPaused;
    private long mPlayStartTime;
    private long mPlayTime;
    private boolean useOldCounting;
    
    public ZylomHelper(final Applet theApplet, final long theAppletBirthTime, GameProperties theProperties) {
        this.mHasDied = false;
        this.mIsMuted = false;
        this.mGamesFinishedCount = 0;
        this.mAppletBirthTime = 0L;
        this.mAppletFinishedLoadingTime = 0L;
        this.mPlayStartTime = 0L;
        this.mPlayTime = 0L;
        this.mPaused = true;
        this.mHasFinishedLoading = false;
        this.mGamesStartedCount = 0;
        this.useOldCounting = false;
        this.mApplet = theApplet;
        this.mAppletBirthTime = ((theAppletBirthTime >= 0L) ? theAppletBirthTime : System.currentTimeMillis());
        this.doDebug = !this.getParamBoolean(StaticStrings.PARAM_DEBUG, true);
        this.doHighScores = !this.getParamBoolean(StaticStrings.PARAM_SENDSCORES, false);
        this.doDataGathering = !this.getParamBoolean(StaticStrings.PARAM_SENDDATAGATHERING, false);
        if (theProperties == null) {
            theProperties = new ThirdPartyProperties(theApplet);
        }
        try {
            this.doHostCheck = GameProperties.getProperties().getBoolean(StaticStrings.PARAM_HOSTCHECK);
        }
        catch (Exception ex) {
            this.doHostCheck = true;
        }
        if (this.doDataGathering) {
            this.mDataGathering = new DataGathering(theApplet, theProperties);
        }
    }
    
    public ZylomHelper(final Applet theApplet, final GameProperties theProperties) {
        this(theApplet, -1L, theProperties);
    }
    
    public ZylomHelper(final Applet theApplet, final long theAppletBirthTime) {
        this(theApplet, theAppletBirthTime, null);
    }
    
    public ZylomHelper(final Applet theApplet) {
        this(theApplet, -1L, null);
    }
    
    public void doHostCheck() {
        if (this.doDebug) {
            System.out.print("\nHOSTCHECKING...");
            System.out.print("\nDo Host Check?                    = " + (this.doHostCheck ? "Yes" : "No"));
            System.out.print("\n");
        }
        if (this.doHostCheck) {
            HostAllowed.isAllowed(this.mApplet);
        }
    }
    
    public String getAdUrl() {
        return this.mApplet.getParameter(StaticStrings.PARAM_SHOWADS);
    }
    
    private int getAppletLoadTime() {
        if (this.mHasFinishedLoading) {
            return (int)((this.mAppletFinishedLoadingTime - this.mAppletBirthTime) / 1000L);
        }
        return (int)((System.currentTimeMillis() - this.mAppletBirthTime) / 1000L);
    }
    
    private int getGamesFinished() {
        return this.mGamesFinishedCount;
    }
    
    private int getGamesRestarted() {
        int gamesRestarted = 0;
        if (this.mGamesStartedCount != this.mGamesFinishedCount) {
            gamesRestarted = this.mGamesStartedCount - this.mGamesFinishedCount - 1;
        }
        if (gamesRestarted < 0) {
            System.out.println("Zylom Error: games restarted < 0!");
            gamesRestarted = 0;
        }
        return gamesRestarted;
    }
    
    private String getHMS(int theTime) {
        final int secs;
        final int secs_m = secs = theTime % 60;
        theTime -= secs_m;
        final int minutes_m = theTime % 3600;
        final int minutes = minutes_m / 60;
        theTime -= minutes_m;
        final int hours_m = theTime % 86400;
        final int hours = hours_m / 3600;
        return hours + ":" + minutes + ":" + secs;
    }
    
    private int getIdleTime() {
        if (this.mHasFinishedLoading) {
            long idleTime = System.currentTimeMillis();
            idleTime -= this.mAppletFinishedLoadingTime;
            idleTime -= this.mPlayTime;
            return (int)(idleTime / 1000L);
        }
        return 0;
    }
    
    private boolean getMuted() {
        return this.mIsMuted;
    }
    
    public boolean getParamBoolean(final String theParam, final boolean defValue) {
        final String aString = this.mApplet.getParameter(theParam);
        if (aString == null) {
            return defValue;
        }
        return aString.equalsIgnoreCase("true");
    }
    
    private int getPlayTime() {
        return (int)(this.mPlayTime / 1000L);
    }
    
    public void indicateAppletDeath() {
        this.stopPlayTimer();
        if (this.doDebug) {
            System.out.print("\nSUBMINT DATA GATHERING");
            System.out.print("\nDo DataGathering?                 = " + (this.doDataGathering ? "Yes" : "No"));
            System.out.print("\nSound                             = " + (this.getMuted() ? "Off" : "On"));
            System.out.print("\nGames finished                    = " + this.getGamesFinished());
            System.out.print("\nGames started but not finished    = " + this.getGamesRestarted());
            System.out.print("\nLoad time H:M:S                   = " + this.getHMS(this.getAppletLoadTime()) + " ms:" + (this.mHasFinishedLoading ? ((this.mAppletFinishedLoadingTime - this.mAppletBirthTime) % 1000L) : ((System.currentTimeMillis() - this.mAppletBirthTime) % 1000L)));
            System.out.print("\nPlay time H:M:S                   = " + this.getHMS(this.getPlayTime()));
            System.out.print("\nIdle time H:M:S                   = " + this.getHMS(this.getIdleTime()));
            System.out.print("\n");
        }
        if (this.doDataGathering && !this.mHasDied) {
            this.mHasDied = true;
            this.mDataGathering.submitDataGathering(this.getMuted(), this.getGamesFinished(), this.getGamesRestarted(), this.getAppletLoadTime(), this.getPlayTime(), this.getIdleTime());
        }
    }
    
    public void indicateFinishedLoading() {
        if (!this.mHasFinishedLoading) {
            this.mAppletFinishedLoadingTime = System.currentTimeMillis();
            this.mHasFinishedLoading = true;
        }
    }
    
    public void indicateGameFinished() {
        ++this.mGamesFinishedCount;
    }
    
    public void indicateGameOver() {
        ++this.mGamesFinishedCount;
    }
    
    public void indicateGameStart() {
        this.useOldCounting = true;
        ++this.mGamesStartedCount;
    }
    
    public boolean isDebug() {
        return this.doDebug;
    }
    
    public boolean isPremium() {
        return this.getParamBoolean(StaticStrings.PARAM_ISPREMIUM, true);
    }
    
    public boolean isShowAds() {
        return this.getParamBoolean(StaticStrings.PARAM_SHOWADS, false);
    }
    
    public void setMuted(final boolean isMuted) {
        this.mIsMuted = isMuted;
    }
    
    public void startPlayTimer() {
        if (this.mPaused) {
            this.mPlayStartTime = System.currentTimeMillis();
            this.mPaused = false;
        }
    }
    
    public void stopPlayTimer() {
        if (!this.mPaused) {
            this.mPlayTime += System.currentTimeMillis() - this.mPlayStartTime;
            this.mPaused = true;
        }
    }
    
    public void submitHighscore(final int score, final int level) {
        if (this.doDebug) {
            System.out.print("\nSUBMINT HIGH SCORE");
            System.out.print("\nDo High Scores?                   = " + (this.doHighScores ? "Yes" : "No"));
            System.out.print("\nScore                             = " + score);
            System.out.print("\nLevel                             = " + level);
            System.out.print("\n");
        }
        if (this.doHighScores) {
            this.mDataGathering.submitHighscore(new CipherInteger(score), new CipherInteger(level));
        }
    }
}
