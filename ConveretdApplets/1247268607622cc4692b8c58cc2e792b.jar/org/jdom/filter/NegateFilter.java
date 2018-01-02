// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.filter;

final class NegateFilter extends AbstractFilter
{
    private static final String CVS_ID = "@(#) $RCSfile: NegateFilter.java,v $ $Revision: 1.4 $ $Date: 2007/11/10 05:29:00 $";
    private Filter filter;
    
    public NegateFilter(final Filter filter) {
        this.filter = filter;
    }
    
    public boolean matches(final Object obj) {
        return !this.filter.matches(obj);
    }
    
    public Filter negate() {
        return this.filter;
    }
    
    public boolean equals(final Object obj) {
        return this == obj || (obj instanceof NegateFilter && this.filter.equals(((NegateFilter)obj).filter));
    }
    
    public int hashCode() {
        return ~this.filter.hashCode();
    }
    
    public String toString() {
        return new StringBuffer(64).append("[NegateFilter: ").append(this.filter.toString()).append("]").toString();
    }
}
