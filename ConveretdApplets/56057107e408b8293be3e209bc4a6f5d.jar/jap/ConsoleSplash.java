// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import logging.LogHolder;
import logging.LogType;

public class ConsoleSplash implements ISplashResponse
{
    private String m_lastMessage;
    
    public void setText(final String lastMessage) {
        if (lastMessage != null && lastMessage.trim().length() > 0) {
            this.m_lastMessage = lastMessage;
            LogHolder.log(1, LogType.MISC, lastMessage + "...");
        }
    }
    
    public String getText() {
        return this.m_lastMessage;
    }
}
