// 
// Decompiled by Procyon v0.5.30
// 

package platform;

import logging.LogHolder;
import logging.LogType;

public class UnknownOS extends AbstractOS
{
    public boolean openLink(final String s) {
        LogHolder.log(6, LogType.MISC, "Class is uncapable of opening links");
        return false;
    }
    
    public String getConfigPath(final String s) {
        return System.getProperty("user.home", "") + "/";
    }
}
