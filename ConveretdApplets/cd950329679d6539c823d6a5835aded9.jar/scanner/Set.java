// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import java.util.Vector;

public class Set implements Cloneable
{
    private Vector m_types;
    
    public Set(final TokenType[] tokenTypes) {
        this();
        for (int i = 0; i < tokenTypes.length; ++i) {
            this.m_types.addElement(tokenTypes[i]);
        }
    }
    
    private Set(final int initialCapacity) {
        this.m_types = null;
        this.m_types = new Vector(initialCapacity);
    }
    
    public Set() {
        this.m_types = null;
        this.m_types = new Vector();
    }
    
    public Set(final Set set) {
        this((Vector)set.m_types.clone());
    }
    
    public Set(final TokenType tokenType) {
        this();
        this.m_types.addElement(tokenType);
    }
    
    private Set(final Vector types) {
        this.m_types = null;
        this.m_types = types;
    }
    
    public TokenType[] getTypes() {
        final TokenType[] types = new TokenType[this.m_types.size()];
        for (int i = 0; i < this.m_types.size(); ++i) {
            types[i] = this.m_types.elementAt(i);
        }
        return types;
    }
    
    public int size() {
        return this.m_types.size();
    }
    
    public void add(final TokenType tokenType) {
        if (this.m_types.contains(tokenType)) {
            throw new IllegalStateException("This token " + tokenType + " is already contained in " + this);
        }
        this.m_types.addElement(tokenType);
    }
    
    public boolean remove(final TokenType tokenType) {
        return this.m_types.removeElement(tokenType);
    }
    
    public boolean contains(final TokenType tokenType) {
        for (int i = 0; i < this.m_types.size(); ++i) {
            if (this.m_types.elementAt(i).equals(tokenType)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(final Set aSet) {
        boolean contains = true;
        for (int i = 0; i < aSet.m_types.size() && contains; ++i) {
            if (!this.contains(aSet.m_types.elementAt(i))) {
                contains = false;
            }
        }
        return contains;
    }
    
    public void remove(final Set aSet) {
        final Vector tokensFromSet = aSet.m_types;
        for (int i = 0; i < tokensFromSet.size(); ++i) {
            if (this.m_types.contains(tokensFromSet.elementAt(i))) {
                this.remove(tokensFromSet.elementAt(i));
            }
        }
    }
    
    public Set createUnion(final Set aSet) {
        final Set set2return = new Set(this.size() + aSet.size());
        Vector tokensFromSet = aSet.m_types;
        TokenType tk = null;
        for (int i = 0; i < tokensFromSet.size(); ++i) {
            tk = tokensFromSet.elementAt(i);
            if (!set2return.m_types.contains(tk)) {
                set2return.add(tk);
            }
        }
        tokensFromSet = this.m_types;
        for (int i = 0; i < tokensFromSet.size(); ++i) {
            tk = tokensFromSet.elementAt(i);
            if (!set2return.m_types.contains(tk)) {
                set2return.add(tk);
            }
        }
        return set2return;
    }
    
    public Set createUnion(final TokenType tokenType) {
        final Set set2return = (Set)this.clone();
        if (!this.contains(tokenType)) {
            set2return.add(tokenType);
        }
        return set2return;
    }
    
    public Set union(final Set aSet) {
        final Vector tokensFromSet = aSet.m_types;
        for (int i = 0; i < tokensFromSet.size(); ++i) {
            if (!this.m_types.contains(tokensFromSet.elementAt(i))) {
                this.add(tokensFromSet.elementAt(i));
            }
        }
        return this;
    }
    
    public Set union(final TokenType tokenType) {
        if (!this.contains(tokenType)) {
            this.add(tokenType);
        }
        return this;
    }
    
    public Set intersect(final Set aSet) {
        final Set set2return = (Set)this.clone();
        final Vector tokensFromSet = aSet.m_types;
        for (int i = 0; i < this.m_types.size(); ++i) {
            if (!tokensFromSet.contains(this.m_types.elementAt(i))) {
                set2return.remove(this.m_types.elementAt(i));
            }
        }
        return set2return;
    }
    
    public Object clone() {
        return new Set(this);
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof Set)) {
            return super.equals(o);
        }
        final Set aSet = (Set)o;
        final int myTypesSize = this.m_types.size();
        if (myTypesSize == aSet.m_types.size()) {
            boolean areEqual = true;
            for (int i = 0; i < myTypesSize && areEqual; ++i) {
                if (!this.contains(aSet.m_types.elementAt(i))) {
                    areEqual = false;
                }
            }
            return areEqual;
        }
        return false;
    }
    
    public String toString() {
        return this.m_types.toString();
    }
}
