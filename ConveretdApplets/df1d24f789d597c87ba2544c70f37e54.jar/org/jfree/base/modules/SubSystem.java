// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.modules;

import org.jfree.base.config.ModifiableConfiguration;

public interface SubSystem
{
    ModifiableConfiguration getGlobalConfig();
    
    PackageManager getPackageManager();
}
