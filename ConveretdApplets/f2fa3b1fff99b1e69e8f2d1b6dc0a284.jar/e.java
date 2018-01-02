import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public interface e
{
    void onQuoteHistory(final Vector p0, final Vector p1);
    
    void onQuoteUpdate(final Vector p0, final Vector p1);
    
    void onReload(final int p0);
    
    void onQuoteMonitorInfo(final String p0);
    
    void onQuoteMonitorError(final String p0);
}
