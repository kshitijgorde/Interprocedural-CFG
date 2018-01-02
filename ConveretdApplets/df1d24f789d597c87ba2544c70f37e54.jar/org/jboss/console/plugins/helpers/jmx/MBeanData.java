// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.jmx;

import javax.management.MBeanInfo;
import javax.management.ObjectName;

public class MBeanData implements Comparable
{
    private ObjectName objectName;
    private MBeanInfo metaData;
    
    public MBeanData(final ObjectName objectName, final MBeanInfo metaData) {
        this.objectName = objectName;
        this.metaData = metaData;
    }
    
    public ObjectName getObjectName() {
        return this.objectName;
    }
    
    public void setObjectName(final ObjectName objectName) {
        this.objectName = objectName;
    }
    
    public MBeanInfo getMetaData() {
        return this.metaData;
    }
    
    public void setMetaData(final MBeanInfo metaData) {
        this.metaData = metaData;
    }
    
    public String getName() {
        return this.objectName.toString();
    }
    
    public String getNameProperties() {
        return this.objectName.getCanonicalKeyPropertyListString();
    }
    
    public String getClassName() {
        return this.metaData.getClassName();
    }
    
    public int compareTo(final Object o) {
        final MBeanData md = (MBeanData)o;
        final String d1 = this.objectName.getDomain();
        final String d2 = md.objectName.getDomain();
        int compare = d1.compareTo(d2);
        if (compare == 0) {
            final String p1 = this.objectName.getCanonicalKeyPropertyListString();
            final String p2 = md.objectName.getCanonicalKeyPropertyListString();
            compare = p1.compareTo(p2);
        }
        return compare;
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof MBeanData && (this == o || this.compareTo(o) == 0);
    }
}
