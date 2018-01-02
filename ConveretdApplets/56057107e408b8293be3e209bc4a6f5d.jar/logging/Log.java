// 
// Decompiled by Procyon v0.5.30
// 

package logging;

public interface Log
{
    void log(final int p0, final int p1, final String p2);
    
    void setLogType(final int p0);
    
    int getLogType();
    
    void setLogLevel(final int p0);
    
    int getLogLevel();
}
