// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector.osx;

import java.util.EventListener;

interface SkypeFrameworkListener extends EventListener
{
    void becameAvailable();
    
    void becameUnavailable();
    
    void attachResponse(final int p0);
    
    void notificationReceived(final String p0);
}
