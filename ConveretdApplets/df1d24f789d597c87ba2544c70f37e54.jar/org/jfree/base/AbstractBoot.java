// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base;

import org.jfree.util.Log;
import org.jfree.base.config.ModifiableConfiguration;
import org.jfree.base.config.HierarchicalConfiguration;
import org.jfree.base.modules.PackageManager;
import org.jfree.base.modules.SubSystem;

public abstract class AbstractBoot implements SubSystem
{
    private PackageManager singleton;
    private HierarchicalConfiguration globalConfig;
    private boolean bootInProgress;
    private boolean bootDone;
    
    public PackageManager getPackageManager() {
        if (this.singleton == null) {
            this.singleton = PackageManager.createInstance(this);
        }
        return this.singleton;
    }
    
    public ModifiableConfiguration getGlobalConfig() {
        if (this.globalConfig == null) {
            this.globalConfig = this.loadConfiguration();
            this.start();
        }
        return this.globalConfig;
    }
    
    public boolean isBootInProgress() {
        return this.bootInProgress;
    }
    
    public boolean isBootDone() {
        return this.bootDone;
    }
    
    protected abstract HierarchicalConfiguration loadConfiguration();
    
    public final void start() {
        if (this.isBootInProgress() || this.isBootDone()) {
            return;
        }
        this.bootInProgress = true;
        final BootableProjectInfo projectInfo = this.getProjectInfo();
        if (projectInfo != null) {
            Log.info(projectInfo.getName() + " " + projectInfo.getVersion());
            final BootableProjectInfo[] dependencies = projectInfo.getDependencies();
            for (int i = 0; i < dependencies.length; ++i) {
                final AbstractBoot loadBooter = this.loadBooter(dependencies[i].getBootClass());
                if (loadBooter != null) {
                    loadBooter.start();
                }
            }
        }
        this.performBoot();
        this.bootInProgress = false;
        this.bootDone = true;
    }
    
    protected abstract void performBoot();
    
    protected abstract BootableProjectInfo getProjectInfo();
    
    protected AbstractBoot loadBooter(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return (AbstractBoot)this.getClass().getClassLoader().loadClass(s).newInstance();
        }
        catch (Exception ex) {
            Log.info("Unable to boot dependent class: " + s);
            return null;
        }
    }
}
