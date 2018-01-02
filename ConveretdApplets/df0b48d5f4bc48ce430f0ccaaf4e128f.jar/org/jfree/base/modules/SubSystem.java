// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.modules;

import org.jfree.util.Configuration;
import org.jfree.util.ExtendedConfiguration;

public interface SubSystem
{
    ExtendedConfiguration getExtendedConfig();
    
    Configuration getGlobalConfig();
    
    PackageManager getPackageManager();
}
