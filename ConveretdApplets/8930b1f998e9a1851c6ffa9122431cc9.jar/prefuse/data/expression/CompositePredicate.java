// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.event.ExpressionListener;
import java.util.Iterator;
import java.util.ArrayList;

public abstract class CompositePredicate extends AbstractPredicate
{
    protected ArrayList m_clauses;
    
    public CompositePredicate() {
        this.m_clauses = new ArrayList(2);
    }
    
    public CompositePredicate(final Predicate predicate, final Predicate predicate2) {
        (this.m_clauses = new ArrayList(2)).add(predicate);
        this.m_clauses.add(predicate2);
    }
    
    public void add(final Predicate predicate) {
        if (this.m_clauses.contains(predicate)) {
            throw new IllegalArgumentException("Duplicate predicate.");
        }
        this.m_clauses.add(predicate);
        this.fireExpressionChange();
    }
    
    public boolean remove(final Predicate predicate) {
        if (this.m_clauses.remove(predicate)) {
            this.fireExpressionChange();
            return true;
        }
        return false;
    }
    
    public void clear() {
        this.removeChildListeners();
        this.m_clauses.clear();
        this.fireExpressionChange();
    }
    
    public int size() {
        return this.m_clauses.size();
    }
    
    public Predicate get(final int n) {
        return this.m_clauses.get(n);
    }
    
    public void set(final Predicate predicate) {
        this.removeChildListeners();
        this.m_clauses.clear();
        this.m_clauses.add(predicate);
        if (this.hasListeners()) {
            this.addChildListeners();
        }
        this.fireExpressionChange();
    }
    
    public void set(final Predicate[] array) {
        this.removeChildListeners();
        this.m_clauses.clear();
        for (int i = 0; i < array.length; ++i) {
            if (!this.m_clauses.contains(array)) {
                this.m_clauses.add(array[i]);
            }
        }
        if (this.hasListeners()) {
            this.addChildListeners();
        }
        this.fireExpressionChange();
    }
    
    public Predicate getSubPredicate(final Predicate predicate) {
        CompositePredicate compositePredicate = null;
        try {
            compositePredicate = (CompositePredicate)this.getClass().newInstance();
        }
        catch (InstantiationException ex) {}
        catch (IllegalAccessException ex2) {}
        for (int i = 0; i < this.m_clauses.size(); ++i) {
            final Predicate predicate2 = this.m_clauses.get(i);
            if (predicate != predicate2) {
                compositePredicate.add(predicate2);
            }
        }
        return compositePredicate;
    }
    
    public void visit(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visitExpression(this);
        final Iterator<Expression> iterator = this.m_clauses.iterator();
        while (iterator.hasNext()) {
            expressionVisitor.down();
            iterator.next().visit(expressionVisitor);
            expressionVisitor.up();
        }
    }
    
    protected void addChildListeners() {
        final Iterator<Expression> iterator = this.m_clauses.iterator();
        while (iterator.hasNext()) {
            iterator.next().addExpressionListener(this);
        }
    }
    
    protected void removeChildListeners() {
        final Iterator<Expression> iterator = this.m_clauses.iterator();
        while (iterator.hasNext()) {
            iterator.next().removeExpressionListener(this);
        }
    }
    
    protected String toString(final String s) {
        if (this.m_clauses.size() == 1) {
            return this.m_clauses.get(0).toString();
        }
        final StringBuffer sb = new StringBuffer();
        sb.append('(');
        final Iterator<Object> iterator = (Iterator<Object>)this.m_clauses.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString());
            if (iterator.hasNext()) {
                sb.append(" ");
                sb.append(s);
                sb.append(" ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
