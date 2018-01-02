// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.appender;

import org.apache.log4j.helpers.LogLog;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;

public class FileAppender extends org.apache.log4j.FileAppender
{
    public void setFile(final String filename) {
        Helper.makePath(filename);
        super.setFile(filename);
    }
    
    public static class Helper
    {
        public static void makePath(final String filename) {
            File dir;
            try {
                final URL url = new URL(filename.trim());
                dir = new File(url.getFile()).getParentFile();
            }
            catch (MalformedURLException e) {
                dir = new File(filename.trim()).getParentFile();
            }
            if (!dir.exists()) {
                final boolean success = dir.mkdirs();
                if (!success) {
                    LogLog.error("Failed to create directory structure: " + dir);
                }
            }
        }
    }
}
