// 
// Decompiled by Procyon v0.5.30
// 

package jstella.applet;

public interface JavaScriptInterface
{
    void setSoundEnabled(final boolean p0);
    
    boolean isSoundEnabled();
    
    void setStereoSoundEnabled(final boolean p0);
    
    boolean isStereoSoundEnabled();
    
    void setAntiFlickerEnabled(final boolean p0);
    
    boolean isAntiFlickerEnabled();
    
    boolean isTVTypeBW();
    
    void setTVTypeBW(final boolean p0);
    
    boolean isPlayer0Amateur();
    
    void setPlayer0Amateur(final boolean p0);
    
    boolean isPlayer1Amateur();
    
    void setPlayer1Amateur(final boolean p0);
    
    void setPausedByUser(final boolean p0);
    
    boolean isPausedByUser();
    
    void doReset();
    
    void doSelect();
    
    void setSelectDown(final boolean p0);
    
    void refocusKeyboard();
    
    void setDefaultScreenModeToTestPattern();
    
    void setDefaultScreenModeToSnow();
    
    void setConsolePowerOn(final boolean p0);
    
    boolean isConsolePowerOn();
    
    boolean loadJStellaGame(final String p0);
    
    void insertCartridge(final String p0, final int p1);
    
    void insertCartridge(final String p0);
}
