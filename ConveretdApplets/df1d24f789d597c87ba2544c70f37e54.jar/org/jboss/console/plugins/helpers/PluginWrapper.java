// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers;

import javax.servlet.ServletConfig;

public interface PluginWrapper
{
    void init(final ServletConfig p0) throws Exception;
    
    void destroy();
}
