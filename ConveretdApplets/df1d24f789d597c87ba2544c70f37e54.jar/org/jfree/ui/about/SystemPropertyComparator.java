// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui.about;

import java.util.Comparator;

class SystemPropertyComparator implements Comparator
{
    private boolean ascending;
    
    public SystemPropertyComparator(final boolean ascending) {
        this.ascending = ascending;
    }
    
    public int compare(final Object o, final Object o2) {
        if (!(o instanceof SystemProperty) || !(o2 instanceof SystemProperty)) {
            return 0;
        }
        final SystemProperty systemProperty = (SystemProperty)o;
        final SystemProperty systemProperty2 = (SystemProperty)o2;
        if (this.ascending) {
            return systemProperty.getName().compareTo(systemProperty2.getName());
        }
        return systemProperty2.getName().compareTo(systemProperty.getName());
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof SystemPropertyComparator && this.ascending == ((SystemPropertyComparator)o).ascending);
    }
    
    public int hashCode() {
        return this.ascending ? 1 : 0;
    }
}
