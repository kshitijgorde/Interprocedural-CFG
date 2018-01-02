// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.filter;

final class AndFilter extends AbstractFilter
{
    private static final String CVS_ID = "@(#) $RCSfile: AndFilter.java,v $ $Revision: 1.4 $ $Date: 2007/11/10 05:29:00 $";
    private Filter left;
    private Filter right;
    
    public AndFilter(final Filter left, final Filter right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("null filter not allowed");
        }
        this.left = left;
        this.right = right;
    }
    
    public boolean matches(final Object obj) {
        return this.left.matches(obj) && this.right.matches(obj);
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AndFilter) {
            final AndFilter filter = (AndFilter)obj;
            if ((this.left.equals(filter.left) && this.right.equals(filter.right)) || (this.left.equals(filter.right) && this.right.equals(filter.left))) {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        return 31 * this.left.hashCode() + this.right.hashCode();
    }
    
    public String toString() {
        return new StringBuffer(64).append("[AndFilter: ").append(this.left.toString()).append(",\n").append("            ").append(this.right.toString()).append("]").toString();
    }
}
