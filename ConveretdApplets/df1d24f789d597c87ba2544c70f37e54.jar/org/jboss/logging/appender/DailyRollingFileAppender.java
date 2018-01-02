// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.appender;

public class DailyRollingFileAppender extends org.apache.log4j.DailyRollingFileAppender
{
    public void setFile(final String filename) {
        FileAppender.Helper.makePath(filename);
        super.setFile(filename);
    }
}
