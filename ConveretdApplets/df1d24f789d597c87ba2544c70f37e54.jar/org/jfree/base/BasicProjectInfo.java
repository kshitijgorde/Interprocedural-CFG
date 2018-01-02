// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base;

import java.util.ArrayList;
import java.util.List;

public class BasicProjectInfo extends Library
{
    private String copyright;
    private List libraries;
    
    public BasicProjectInfo() {
        this.libraries = new ArrayList();
    }
    
    public BasicProjectInfo(final String name, final String version, final String licenceName, final String info) {
        this();
        this.setName(name);
        this.setVersion(version);
        this.setLicenceName(licenceName);
        this.setInfo(info);
    }
    
    public BasicProjectInfo(final String s, final String s2, final String s3, final String copyright, final String s4) {
        this(s, s2, s4, s3);
        this.setCopyright(copyright);
    }
    
    public String getCopyright() {
        return this.copyright;
    }
    
    public void setCopyright(final String copyright) {
        this.copyright = copyright;
    }
    
    public void setInfo(final String info) {
        super.setInfo(info);
    }
    
    public void setLicenceName(final String licenceName) {
        super.setLicenceName(licenceName);
    }
    
    public void setName(final String name) {
        super.setName(name);
    }
    
    public void setVersion(final String version) {
        super.setVersion(version);
    }
    
    public Library[] getLibraries() {
        return this.libraries.toArray(new Library[this.libraries.size()]);
    }
    
    public void addLibrary(final Library library) {
        if (library == null) {
            throw new NullPointerException();
        }
        this.libraries.add(library);
    }
}
