// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import hypergraph.graphApi.Group;
import hypergraph.graphApi.Element;

public abstract class ElementImpl implements Element
{
    private Group group;
    private String name;
    
    ElementImpl(final String name) {
        this.name = name;
    }
    
    public boolean equals(final Object o) {
        return o != null && this.getName().equals(((Element)o).getName());
    }
    
    public int hashCode() {
        return this.getName().hashCode();
    }
    
    public int compareTo(final Object o) {
        return this.getName().compareTo(((Element)o).getName());
    }
    
    public String getName() {
        return this.name;
    }
    
    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(final Group group) {
        this.group = group;
    }
}
