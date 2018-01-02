// 
// Decompiled by Procyon v0.5.30
// 

package logging;

import org.apache.log4j.Logger;
import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.PatternLayout;

public final class FileLog extends AbstractLog4jLog
{
    public FileLog(final String s, final int n, final int maxBackupIndex) {
        final PatternLayout patternLayout = new PatternLayout("[%d{ISO8601} - %p] %m%n");
        try {
            final RollingFileAppender rollingFileAppender = new RollingFileAppender(patternLayout, s, true);
            rollingFileAppender.setMaximumFileSize(n);
            rollingFileAppender.setMaxBackupIndex(maxBackupIndex);
            rollingFileAppender.setBufferedIO(false);
            rollingFileAppender.activateOptions();
            this.getLogger().removeAllAppenders();
            this.getLogger().addAppender(rollingFileAppender);
        }
        catch (IOException ex) {}
    }
    
    protected Logger getLogger() {
        return Logger.getRootLogger();
    }
}
