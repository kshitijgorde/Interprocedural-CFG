// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.jmx;

import java.util.Collection;
import java.util.Arrays;
import java.util.TreeSet;

public class DomainData
{
    String domainName;
    TreeSet domainData;
    
    public DomainData(final String domainName) {
        this.domainData = new TreeSet();
        this.domainName = domainName;
    }
    
    public DomainData(final String domainName, final MBeanData[] data) {
        this.domainData = new TreeSet();
        this.domainName = domainName;
        this.domainData.addAll(Arrays.asList(data));
    }
    
    public int hashCode() {
        return this.domainName.hashCode();
    }
    
    public boolean equals(final Object obj) {
        final DomainData data = (DomainData)obj;
        return this.domainName.equals(data.domainName);
    }
    
    public String getDomainName() {
        return this.domainName;
    }
    
    public MBeanData[] getData() {
        final MBeanData[] data = new MBeanData[this.domainData.size()];
        this.domainData.toArray(data);
        return data;
    }
    
    public void addData(final MBeanData data) {
        this.domainData.add(data);
    }
}
