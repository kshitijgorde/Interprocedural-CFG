// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.appender;

public class RollingFileAppender extends org.apache.log4j.RollingFileAppender
{
    public void setFile(final String filename) {
        FileAppender.Helper.makePath(filename);
        super.setFile(filename);
    }
}
