// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.List;

class MLetVersion implements Comparable
{
    protected List versions;
    
    public MLetVersion(final List versions) {
        this.versions = versions;
    }
    
    public List getVersions() {
        return this.versions;
    }
    
    public boolean isNull() {
        return this.versions == null || this.versions.isEmpty();
    }
    
    public int compareTo(final Object o) {
        final MLetVersion other = (MLetVersion)o;
        if (this.isNull() || other.isNull()) {
            throw new IllegalArgumentException("MLet versions is null");
        }
        final String thisVersion = this.versions.get(0);
        final String otherVersion = other.getVersions().get(0);
        return thisVersion.compareTo(otherVersion);
    }
    
    public String toString() {
        return "Version " + this.versions.get(0);
    }
}
