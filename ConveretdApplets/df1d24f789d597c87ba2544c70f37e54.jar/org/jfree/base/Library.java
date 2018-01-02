// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base;

public class Library
{
    private String name;
    private String version;
    private String licenceName;
    private String info;
    
    public Library(final String name, final String version, final String licenceName, final String info) {
        this.name = name;
        this.version = version;
        this.licenceName = licenceName;
        this.info = info;
    }
    
    protected Library() {
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public String getLicenceName() {
        return this.licenceName;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    protected void setInfo(final String info) {
        this.info = info;
    }
    
    protected void setLicenceName(final String licenceName) {
        this.licenceName = licenceName;
    }
    
    protected void setName(final String name) {
        this.name = name;
    }
    
    protected void setVersion(final String version) {
        this.version = version;
    }
}
