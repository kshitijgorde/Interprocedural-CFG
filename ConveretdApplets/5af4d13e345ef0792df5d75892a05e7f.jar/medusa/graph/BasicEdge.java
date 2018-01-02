// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graph;

import java.io.Serializable;

public abstract class BasicEdge implements Serializable
{
    public String n1;
    public String n2;
    
    public String getFromName() {
        return this.n1;
    }
    
    public String getToName() {
        return this.n2;
    }
    
    public String report() {
        return this.n1 + "\t" + this.n2;
    }
    
    public boolean sameName(final Object o) {
        final BasicEdge basicEdge = (BasicEdge)o;
        final int compareTo = this.n1.compareTo(basicEdge.n2);
        final int compareTo2 = this.n2.compareTo(basicEdge.n1);
        final int compareTo3 = this.n1.compareTo(basicEdge.n1);
        final int compareTo4 = this.n2.compareTo(basicEdge.n2);
        return (compareTo == 0 & compareTo2 == 0) || (compareTo3 == 0 & compareTo4 == 0);
    }
    
    public boolean contains(final String s) {
        return this.n1.compareTo(s) == 0 | this.n2.compareTo(s) == 0;
    }
    
    public boolean oppositeName(final Object o) {
        final BasicEdge basicEdge = (BasicEdge)o;
        return this.n1.compareTo(basicEdge.n2) == 0 & this.n2.compareTo(basicEdge.n1) == 0;
    }
    
    public String[] getNodes() {
        return new String[] { this.n1, this.n2 };
    }
}
