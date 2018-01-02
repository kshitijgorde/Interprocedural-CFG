// 
// Decompiled by Procyon v0.5.30
// 

package logging;

public class ChainedLog implements Log
{
    Log m_Log1;
    Log m_Log2;
    
    public ChainedLog(final Log log1, final Log log2) {
        this.m_Log1 = log1;
        this.m_Log2 = log2;
    }
    
    public synchronized void log(final int n, final int n2, final String s) {
        this.m_Log1.log(n, n2, s);
        this.m_Log2.log(n, n2, s);
    }
    
    public void setLogType(final int n) {
    }
    
    public int getLogType() {
        return LogType.ALL;
    }
    
    public void setLogLevel(final int n) {
    }
    
    public int getLogLevel() {
        return 7;
    }
}
