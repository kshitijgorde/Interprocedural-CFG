// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base;

import java.util.ArrayList;

public class BootableProjectInfo extends BasicProjectInfo
{
    private ArrayList dependencies;
    private String bootClass;
    private boolean autoBoot;
    
    public BootableProjectInfo() {
        this.dependencies = new ArrayList();
        this.autoBoot = true;
    }
    
    public BootableProjectInfo(final String name, final String version, final String licenceName, final String info) {
        this();
        this.setName(name);
        this.setVersion(version);
        this.setLicenceName(licenceName);
        this.setInfo(info);
    }
    
    public BootableProjectInfo(final String name, final String version, final String info, final String copyright, final String licenceName) {
        this();
        this.setName(name);
        this.setVersion(version);
        this.setLicenceName(licenceName);
        this.setInfo(info);
        this.setCopyright(copyright);
    }
    
    public BootableProjectInfo[] getDependencies() {
        return this.dependencies.toArray(new BootableProjectInfo[this.dependencies.size()]);
    }
    
    public void addDependency(final BootableProjectInfo bootableProjectInfo) {
        if (bootableProjectInfo == null) {
            throw new NullPointerException();
        }
        this.dependencies.add(bootableProjectInfo);
    }
    
    public String getBootClass() {
        return this.bootClass;
    }
    
    public void setBootClass(final String bootClass) {
        this.bootClass = bootClass;
    }
    
    public boolean isAutoBoot() {
        return this.autoBoot;
    }
    
    public void setAutoBoot(final boolean autoBoot) {
        this.autoBoot = autoBoot;
    }
}
