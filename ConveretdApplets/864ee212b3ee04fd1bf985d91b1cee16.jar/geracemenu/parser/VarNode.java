// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import java.util.Hashtable;

public class VarNode extends ItemNode
{
    private Hashtable space;
    
    public VarNode put(final String s, final Type type) throws VariableSpaceException {
        if (this.space.get(s) != null) {
            throw new VariableSpaceException("Variable \"" + s + "\" already defined.");
        }
        this.space.put(s, type);
        return this;
    }
    
    public Type get(final String s) {
        return this.space.get(s);
    }
    
    public Hashtable getSpace() {
        return this.space;
    }
    
    public String toString() {
        return this.space.toString();
    }
    
    public VarNode() {
        super("");
        this.space = new Hashtable(5, 0.5f);
    }
}
