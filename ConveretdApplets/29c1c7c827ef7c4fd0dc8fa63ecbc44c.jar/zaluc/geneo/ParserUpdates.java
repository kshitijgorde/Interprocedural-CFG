// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

interface ParserUpdates
{
    void notifyFileName(final String p0);
    
    void notifySetup(final PeopleList p0, final String p1, final int p2);
    
    void updateRecordCount(final int p0);
    
    void updateStatus(final String p0);
    
    void notifyDone();
}
