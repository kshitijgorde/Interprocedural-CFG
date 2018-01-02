// 
// Decompiled by Procyon v0.5.30
// 

package logging;

import org.apache.log4j.Priority;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public abstract class AbstractLog4jLog implements Log
{
    protected abstract Logger getLogger();
    
    public void log(final int n, final int n2, final String s) {
        Level level = Level.DEBUG;
        if (n == 7) {
            level = Level.DEBUG;
        }
        else if (n == 6 || n == 5) {
            level = Level.INFO;
        }
        else if (n == 4) {
            level = Level.WARN;
        }
        else if (n == 3 || n == 2) {
            level = Level.ERROR;
        }
        else if (n == 1 || n == 0) {
            level = Level.FATAL;
        }
        this.getLogger().log(null, level, s, null);
    }
    
    public void setLogType(final int n) {
    }
    
    public int getLogType() {
        return LogType.ALL;
    }
    
    public void setLogLevel(final int n) {
        Level level = Level.ALL;
        switch (n) {
            case 7: {
                level = Level.DEBUG;
                break;
            }
            case 5:
            case 6: {
                level = Level.INFO;
                break;
            }
            case 0:
            case 1: {
                level = Level.FATAL;
                break;
            }
            case 4: {
                level = Level.WARN;
                break;
            }
            case 2:
            case 3: {
                level = Level.ERROR;
                break;
            }
        }
        this.getLogger().setLevel(level);
    }
    
    public int getLogLevel() {
        int n = 0;
        if (this.getLogger().isEnabledFor(Level.DEBUG)) {
            n = 7;
        }
        else if (this.getLogger().isEnabledFor(Level.INFO)) {
            n = 6;
        }
        else if (this.getLogger().isEnabledFor(Level.WARN)) {
            n = 4;
        }
        else if (this.getLogger().isEnabledFor(Level.ERROR)) {
            n = 3;
        }
        else if (this.getLogger().isEnabledFor(Level.FATAL)) {
            n = 1;
        }
        return n;
    }
}
