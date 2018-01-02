// 
// Decompiled by Procyon v0.5.30
// 

package logging;

public final class SystemErrLog implements Log
{
    private int m_logLevel;
    private int m_logType;
    
    public SystemErrLog() {
        this(7, LogType.ALL);
    }
    
    public SystemErrLog(final int logLevel, final int logType) {
        this.m_logLevel = logLevel;
        this.m_logType = logType;
    }
    
    public void log(final int n, final int n2, final String s) {
        if (n <= this.m_logLevel && (n2 & this.m_logType) == n2) {
            System.err.println("[" + LogLevel.getLevelName(n) + "] " + s);
        }
    }
    
    public void setLogLevel(final int logLevel) {
        if (logLevel >= 0 && logLevel < LogLevel.getLevelCount()) {
            this.m_logLevel = logLevel;
        }
    }
    
    public void setLogType(final int logType) {
        this.m_logType = logType;
    }
    
    public int getLogType() {
        return this.m_logType;
    }
    
    public int getLogLevel() {
        return this.m_logLevel;
    }
}
