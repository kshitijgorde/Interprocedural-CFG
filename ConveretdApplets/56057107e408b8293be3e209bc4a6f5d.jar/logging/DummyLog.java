// 
// Decompiled by Procyon v0.5.30
// 

package logging;

public class DummyLog implements Log
{
    public void log(final int n, final int n2, final String s) {
    }
    
    public void setLogType(final int n) {
    }
    
    public int getLogType() {
        return LogType.NUL;
    }
    
    public void setLogLevel(final int n) {
    }
    
    public int getLogLevel() {
        return 0;
    }
}
