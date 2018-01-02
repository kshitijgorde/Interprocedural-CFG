// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.io.PrintStream;
import java.io.InputStream;
import java.io.File;
import com.kenai.constantine.platform.Errno;

public interface POSIXHandler
{
    void error(final Errno p0, final String p1);
    
    void unimplementedError(final String p0);
    
    void warn(final WARNING_ID p0, final String p1, final Object... p2);
    
    boolean isVerbose();
    
    File getCurrentWorkingDirectory();
    
    String[] getEnv();
    
    InputStream getInputStream();
    
    PrintStream getOutputStream();
    
    int getPID();
    
    PrintStream getErrorStream();
    
    public enum WARNING_ID
    {
        DUMMY_VALUE_USED("DUMMY_VALUE_USED");
        
        private String messageID;
        
        private WARNING_ID(final String messageID) {
            this.messageID = messageID;
        }
    }
}
