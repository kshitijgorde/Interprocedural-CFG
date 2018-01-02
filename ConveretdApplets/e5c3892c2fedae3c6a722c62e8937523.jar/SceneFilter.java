// 
// Decompiled by Procyon v0.5.30
// 

interface SceneFilter
{
    void nextDate();
    
    void nextDate(final Metadata p0);
    
    void prevDate();
    
    void prevDate(final Metadata p0);
    
    void gotoDate(final int p0, final int p1);
    
    void filter();
    
    boolean isNextDateAvailable();
    
    boolean isNextDateAvailable(final Metadata p0);
    
    boolean isPrevDateAvailable();
    
    boolean isPrevDateAvailable(final Metadata p0);
    
    void gotoFirstDate();
    
    void gotoLastDate();
    
    int getFirstYear();
    
    int getLastYear();
}
