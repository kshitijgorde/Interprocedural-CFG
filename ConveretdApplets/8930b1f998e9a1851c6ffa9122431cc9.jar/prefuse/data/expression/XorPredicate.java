// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import java.util.Iterator;
import prefuse.data.Tuple;

public class XorPredicate extends CompositePredicate
{
    public XorPredicate() {
    }
    
    public XorPredicate(final Predicate predicate) {
        this.add(predicate);
    }
    
    public XorPredicate(final Predicate predicate, final Predicate predicate2) {
        super(predicate, predicate2);
    }
    
    public boolean getBoolean(final Tuple tuple) {
        if (this.m_clauses.size() == 0) {
            return false;
        }
        boolean boolean1 = false;
        final Iterator<Predicate> iterator = (Iterator<Predicate>)this.m_clauses.iterator();
        if (iterator.hasNext()) {
            boolean1 = iterator.next().getBoolean(tuple);
        }
        while (iterator.hasNext()) {
            boolean1 ^= iterator.next().getBoolean(tuple);
        }
        return boolean1;
    }
    
    public String toString() {
        return (this.size() == 0) ? "FALSE" : this.toString("XOR");
    }
}
