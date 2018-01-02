// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base;

import org.jfree.util.Log;
import org.jfree.base.log.DefaultLog;
import org.jfree.base.config.SystemPropertyConfiguration;
import org.jfree.base.config.PropertyFileConfiguration;
import org.jfree.base.config.HierarchicalConfiguration;

public class BaseBoot extends AbstractBoot
{
    private static BaseBoot singleton;
    private BootableProjectInfo bootableProjectInfo;
    static /* synthetic */ Class class$org$jfree$base$log$DefaultLogModule;
    
    protected synchronized HierarchicalConfiguration loadConfiguration() {
        final HierarchicalConfiguration hierarchicalConfiguration = new HierarchicalConfiguration();
        final PropertyFileConfiguration propertyFileConfiguration = new PropertyFileConfiguration();
        propertyFileConfiguration.load("/org/jfree/base/jcommon.properties");
        hierarchicalConfiguration.insertConfiguration(propertyFileConfiguration);
        hierarchicalConfiguration.insertConfiguration(this.getPackageManager().getPackageConfiguration());
        final PropertyFileConfiguration propertyFileConfiguration2 = new PropertyFileConfiguration();
        propertyFileConfiguration2.load("/jcommon.properties");
        hierarchicalConfiguration.insertConfiguration(propertyFileConfiguration2);
        hierarchicalConfiguration.insertConfiguration(new SystemPropertyConfiguration());
        return hierarchicalConfiguration;
    }
    
    public static AbstractBoot getInstance() {
        if (BaseBoot.singleton == null) {
            BaseBoot.singleton = new BaseBoot();
        }
        return BaseBoot.singleton;
    }
    
    public void performBoot() {
        this.getPackageManager().addModule(((BaseBoot.class$org$jfree$base$log$DefaultLogModule == null) ? (BaseBoot.class$org$jfree$base$log$DefaultLogModule = class$("org.jfree.base.log.DefaultLogModule")) : BaseBoot.class$org$jfree$base$log$DefaultLogModule).getName());
        this.getPackageManager().load("org.jfree.base.");
        this.getPackageManager().initializeModules();
        this.getPackageManager().printUsedModules(System.out);
    }
    
    protected BootableProjectInfo getProjectInfo() {
        return null;
    }
    
    public static void main(final String[] array) {
        DefaultLog.getInstance();
        getInstance().start();
        Log.debug("Hello world");
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
